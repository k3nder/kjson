package net.kender.core.sample;


import java.io.File;
import java.io.IOException;
import java.util.List;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.kender.core.UtilsFiles;
import net.kender.logger.log5k.Logger;

public class JsonOfVersion {


    private static Logger logger = new Logger(UtilsFiles.class);

    public List<String> gameArgs;
    public List<String> jvmArgs;
    public JsonNode assetsIndex;
    public String assets;
    public int compilanceLevel;
    public JsonNode downloads;
    public String lastVersionId;
    public JsonNode javaVersion;
    public JsonNode libraries;
    public JsonNode logging;
    public String mainClass;
    public int minimumLauncherVersion;
    public String relaseTime;
    public String time;
    public String type;
    public String inheritsFrom;

    public JsonOfVersion(File Json) {
        try {
            if (Json.exists() != true) {
                logger.WARN( "fallo al leer el archivo: " + Json + " El archivo no existe");
            }

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(Json);

            // set args variables
            //JsonNode args = rootNode.get("arguments");

            //JsonNode GameArgs = args.get("game");
            //JsonNode JvmArgs = args.get("jvm");

            // gameArgs = convert(GameArgs);
            // jvmArgs = convert(JvmArgs);

            // set assets variables
            JsonNode assetsIndexNode = rootNode.get("assetIndex");
            if (assetsIndexNode != null) {
                assetsIndex = assetsIndexNode;
            }

            JsonNode assetsNode = rootNode.get("assets");
            if (assetsNode != null) {
                assets = assetsNode.asText();
            }

            JsonNode compilanceLevelNode = rootNode.get("complianceLevel");
            if (compilanceLevelNode != null) {
                compilanceLevel = compilanceLevelNode.asInt();
            }

            // downloads set variables
            JsonNode downloadsNode = rootNode.get("downloads");
            if (downloadsNode == null) {
                inheritsFrom = rootNode.get("inheritsFrom").asText();
            } else {
                downloads = downloadsNode;
            }
            // last version id set variable

            JsonNode lastVersionIdNode = rootNode.get("id");
            if (lastVersionIdNode != null) {
                lastVersionId = lastVersionIdNode.asText();
            }

            // java version set variable
            javaVersion = rootNode.get("javaVersion");

            // libraries variable set
            libraries = rootNode.get("libraries");

            // set logging variables
            logging = rootNode.get("logging");

            // set mainclass variable
            JsonNode mainClassNode = rootNode.get("mainClass");
            if (mainClassNode != null) {
                mainClass = mainClassNode.asText();
            }

            // set minimumLauncherVersion variable
            JsonNode minimumLauncherVersionNode = rootNode.get("minimumLauncherVersion");
            if (minimumLauncherVersionNode != null) {
                minimumLauncherVersion = minimumLauncherVersionNode.asInt();
            }

            // releaseTime set variable
            JsonNode relaseTimeNode = rootNode.get("releaseTime");
            if (relaseTimeNode != null) {
                relaseTime = relaseTimeNode.asText();
            }

            // set time variable
            JsonNode timeNode = rootNode.get("time");
            if (timeNode != null) {
                time = timeNode.asText();
            }

            // set type variable
            JsonNode typeNode = rootNode.get("type");
            if (typeNode != null) {
                type = typeNode.asText();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //private List<String> convert(JsonNode g) {
    //    List<String> result = null;
    //    if (g.isArray()) {
    //        for (JsonNode element : g) {
    //            System.out.println(element.asText());
    //            result.add(element.asText());
    //        }

    //    } else {
    //        System.out.println("El JSON no representa un array.");
    //    }
    //    return result;
    //}
}