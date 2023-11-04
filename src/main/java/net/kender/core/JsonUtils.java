package net.kender.core;

import static net.kender.logger.Log.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.kender.logger.log5k.Logger;

/**
 *
 * @author krist
 */
public class JsonUtils {

    private static final String SEPARATOR = "-------------------";

    private static Logger logger = new Logger(JsonUtils.class);

    private JsonUtils(){}

    public static String getUrlOfJsonOfVersion(String url, String version) {
        String urlFromJson = "";
        try {
            URL urlReform = new URL(url);

            HttpURLConnection conn = (HttpURLConnection) urlReform.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                logger.ERROR("Error al conectar a la URL. Código de respuesta: " + conn.getResponseCode());
            }

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(conn.getInputStream());

            Iterator<JsonNode> versionsIterator = rootNode.get("versions").iterator();

            while (versionsIterator.hasNext()) {
                JsonNode versionNode = versionsIterator.next();
                if (version.equals(versionNode.get("id").asText())) {
                    urlFromJson = versionNode.get("url").asText();
                    String msg = "URL encontrada: " + urlFromJson;
                    logger.INFO(msg);
                    break;
                }
            }

            conn.disconnect();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return urlFromJson;
    }

    public static String getJsonVersion(String url) {
        StringBuilder jsonString = new StringBuilder();

        try {
            URI uri = new URI(url);
            URL urlReform = uri.toURL();
            
            BufferedReader reader = new BufferedReader(new InputStreamReader(urlReform.openStream()));
            jsonString = new StringBuilder();

            jsonString = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                jsonString.append(line);
            }
            reader.close();

            logger.INFO("JSON descargado:" + url);

        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
        return jsonString.toString();
    }

    public static String readJsonVersion(String filePath) throws IOException {
        String url = "";
        // Crear un ObjectMapper para leer el JSON
        ObjectMapper objectMapper = new ObjectMapper();

        // Leer el archivo JSON y convertirlo en un objeto JsonNode
        JsonNode rootNode = objectMapper.readTree(new File(filePath));

        // Obtener el nodo "downloads"
        JsonNode downloadsNode = rootNode.get("downloads");
        if (downloadsNode != null) {
            // Obtener el nodo "client" dentro del nodo "downloads"
            JsonNode clientNode = downloadsNode.get("client");

            // Obtener el valor de la propiedad "url" dentro del nodo "client"
            url = clientNode.get("url").asText();

            log(logger ,"URL del cliente: " + url);
        }
        return url;
    }

    public static String[] getListOfVersions(String jsonUrl) throws URISyntaxException ,IOException {

        ObjectMapper objectMapper = new ObjectMapper();

        URI uri = new URI(jsonUrl);
        URL urlReform = uri.toURL();

        JsonNode rootNode = objectMapper.readTree(urlReform);

        JsonNode versionsNode = rootNode.get("versions");
        if (versionsNode != null && versionsNode.isArray()) {
            List<String> versionIdsList = new ArrayList<>();
            for (JsonNode versionNode : versionsNode) {
                JsonNode idNode = versionNode.get("id");
                if (idNode != null) {
                    versionIdsList.add(idNode.asText());
                }
            }
            return versionIdsList.toArray(new String[0]);
        }

        return new String[0];
    }

    public static String readAssetsIndexUrlFromJson(String filePath) {
        String urlFromJson = "";
        try {
            File jsonFile = new File(filePath);
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(jsonFile);

            JsonNode assetsIndexNode = rootNode.get("assetIndex");
            if (assetsIndexNode != null && assetsIndexNode.has("url")) {
                urlFromJson = assetsIndexNode.get("url").asText();
                //logger.WARN(urlFromJson);
            } else {
                logger.WARN("No se encontró el nodo assetsIndex o el campo url en el JSON.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return urlFromJson;
    }

    public static String getlibs(String pathJson, String version, String pathOfLibs) throws IOException {
        int indexOfUlibs = 0;
        File json = new File(pathJson);
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(json);

        log(logger ,"dowloading the libs");
        log(logger ,"--------");

        JsonNode libs = rootNode.get("libraries");
        String pathLibs = "";

        for (JsonNode libraryNode : libs) {
            // Paso 3: Acceder a los elementos "downloads" y "name" de cada objeto "libraries"
            JsonNode dowloads = libraryNode.get("downloads");

            indexOfUlibs = dowloads.size();

            // Verificar si "downloads" no es nulo
            if (dowloads != null && dowloads.isObject()) {

                boolean artexi = dowloads.has("artifact");
                if (artexi) {
                    pathLibs = pathLibs + pathOfLibs + "\\" + artexi(dowloads,version,pathOfLibs);
                }


                boolean cassexist = dowloads.has("classifiers");

                if (cassexist) {
                    pathLibs = pathLibs + pathOfLibs + "\\" + cassexist(dowloads,version,pathOfLibs);
                }
            } else {
                log(logger ,"no");
            }

        }
        return pathLibs;

    }
    private static final String SAVE_IN = "save in: ";
    private static String cassexist(JsonNode dowloads,String version,String pathOfLibs){
        JsonNode classifiersNode = dowloads.get("classifiers");
        log(logger ,"classexists");
        boolean nativesOS = classifiersNode.has("natives-windows");
        if (nativesOS) {
            nativesos(classifiersNode, pathOfLibs, version);
        }
        boolean nativesArch = classifiersNode.has("natives-windows-64");
        if (nativesArch) {
            JsonNode archNode = classifiersNode.get("natives-windows-64");
            
            JsonNode urlArch = archNode.get("url");
            JsonNode pathArch = archNode.get("path");
            
            String urlS = urlArch.asText();
            
            
            
            log(logger ,"dowloading: " + urlS);
            
            UtilsFiles.download(urlS, pathOfLibs + "\\" + pathArch.asText(),"","");
            
            log(logger ,SAVE_IN + pathOfLibs + "\\" + version + "\\");
            
            log(logger ,"download completed");
            return pathArch.asText() + ";";
        }else{logW(logger, "windows natives dosent exist return null"); return null;}

    }
    private static String artexi(JsonNode dowloads,String version,String pathOfLibs){
        JsonNode art = dowloads.get("artifact");

        JsonNode urlNode = art.get("url");
        JsonNode packagE = art.get("path");

        String url = urlNode.asText();
        String paths = packagE.asText();


        log(logger ,SEPARATOR);
        log(logger ,"dowloadig: " + url);
        log(logger ,"on: " + paths);
        log(logger ,"-----");
        Path p = Path.of(pathOfLibs + "\\" + version + "\\" + paths ,"");
        if(Files.exists(p)){
            log(logger ,"the dependecy alredy dowloaded");
            log(logger ,SEPARATOR);
        }else {
            UtilsFiles.download(url, pathOfLibs + "\\" + version + "\\", paths, "");

            log(logger ,SAVE_IN + pathOfLibs + "\\" + version + "\\");

            log(logger ,"dowloaded completed");
            log(logger ,SEPARATOR);
        }
        return paths + ";";
    }
    private static void nativesos(JsonNode classifiersNode,String pathOfLibs,String version){
        log(logger ,"nodeOS");
                        JsonNode natives = classifiersNode.get("natives-windows");
                        JsonNode nativesPathNode = natives.get("url");
                        JsonNode urlnatives = natives.get("url");
                        String urlNatives = urlnatives.asText();
                        String nativesPath = nativesPathNode.asText();

                        String nameos = quitarUltimaParteRuta(nativesPath);

                        log(logger ,"dowloading: " + urlnatives);

                        UtilsFiles.download(urlNatives, pathOfLibs + "\\" + version + "\\", nameos, ".jar");

                        log(logger ,SAVE_IN + pathOfLibs + "\\" + version + "\\");

                        log(logger ,"dowloaded completed");
    }

    public static List<String> getProfiles(String path) throws IOException {
        List<String> profiles = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        File archivoJson = new File(path);

        JsonNode rootNode = objectMapper.readTree(archivoJson);

        JsonNode pro = rootNode.get("profiles");

        Iterator<Map.Entry<String, JsonNode>> fieldsIterator = pro.fields();
        while (fieldsIterator.hasNext()) {
            Map.Entry<String, JsonNode> entry = fieldsIterator.next();
            String key = entry.getKey();
            log(logger ,"version profile: " + key);
            profiles.add(key);

        }
        return profiles;
    }

}



    

