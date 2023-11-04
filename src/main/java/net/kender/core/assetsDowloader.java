package net.kender.core;


import net.kender.logger.log5k.Logger;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class assetsDowloader {

    private static final String SEPARATOR = "-------------------";

    private static Logger logger = new Logger(assetsDowloader.class);

    public static String URL_ = "https://resources.download.minecraft.net/";
    public static String IndexJsonPath;

    public static int AssetsDowload(String pathJson, String PathDest, String PathObjects) throws IOException {
        IndexJsonPath = pathJson;
        int indexOfUassets = 0;
        File json = new File(IndexJsonPath);
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(json);

        JsonNode obj = rootNode.get("objects");

        int i = 0;
        indexOfUassets = obj.size();
        logger.DATA("index of",indexOfUassets);

        int pb = indexOfUassets/10000;
        logger.DATA("pb",pb);

        List<String> keysList = new ArrayList<>();
        Iterator<String> fieldNames = obj.fieldNames();
        while (fieldNames.hasNext()) {
            String key = fieldNames.next();
            keysList.add(key);
            logger.DATA("key",key);
        }

        int size = keysList.size();

        while(i != size){
            JsonNode resource = obj.get(keysList.get(i));

            JsonNode hash = resource.get("hash");
            logger.INFO("----------");
            logger.INFO("resource: " + keysList.get(i));
            logger.INFO("hash is: " + hash.asText());
            logger.INFO("----------");

            String hashS = hash.asText();

            String block = hashS.substring(0,2);

            String url = URL_ + block + "/" + hashS;

            logger.DATA("url",url);

            String path = keysList.get(i);

            String Path = PathDest + path;

            String name = quitarUltimaParteRuta(path);
            logger.DATA("name of file",name);
            int indexExtend = name.lastIndexOf(".");
            int indesul = name.length();
            logger.INFO(String.valueOf(indexExtend));
            logger.INFO(name);

            if (indexExtend == -1){
                logger.WARN("dot found '.' in name");

            }else{
                // Construir el nuevo String sin la parte que queremos quitar
                String newString = name.substring(0, indexExtend) + name.substring(indesul);
                logger.INFO(newString);

                logger.INFO("dowloading...");

                String PathO = PathObjects + "\\" + block + "\\" + hashS;

                Path pats = java.nio.file.Path.of(Path);
                Path pats0 = java.nio.file.Path.of(PathO);
                if(Files.exists(pats)){
                    logger.INFO("the asset " + path + " alredy exist");
                }else{
                    UtilsFiles.download(url, Path,"","");
                }
                if(Files.exists(pats0)){
                    logger.INFO("the asset " + PathO + " alredy exist");
                }else{
                    UtilsFiles.download(url, PathO, "", "");
                }
                pb = pb + pb;


            }

            i++;
        }
        return indexOfUassets;
    }
    public static String quitarUltimaParteRuta(String ruta) {
        if (ruta == null) {
            return null;
        }

        int ultimaBarra = ruta.lastIndexOf("/");
        if (ultimaBarra != -1) {
            return ruta.substring(ultimaBarra + 1);
        } else {
            return ruta; // La ruta es el nombre de archivo
        }
    }

}
