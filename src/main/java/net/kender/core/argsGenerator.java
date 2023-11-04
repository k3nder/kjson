/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.kender.core;

import static net.kender.logger.Log.*;
import static net.kender.lt.cons.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


/**
 *
 * @author krist
 */
public class argsGenerator {
    private static final String SEPARATOR = "-------------------";

    private static Logger logger = Logger.getLogger(argsGenerator.class.getName());

    public static String RutaJava;
    public static String genrateArgsCommand(String version,String pathOfLibsCarpet, String pathOfTheJar, String name, String ram, String accesstoken, String gamedir, String MainClass, String lastVersionID,boolean Custom,String vers) throws IOException {
        if(Custom) {
            String command = RutaJava + "bin\\java -Xmx" + ram + "G -Xms2G -XX:+UnlockExperimentalVMOptions -XX:+UseG1GC -XX:G1NewSizePercent=20 -XX:G1ReservePercent=20 -XX:MaxGCPauseMillis=50 -XX:G1HeapRegionSize=32M -cp \".;" + pathOfTheJar + ";" + pathOfLibsCarpet + "\" " + MainClass + " --accessToken " + accesstoken + " --Username " + name + " --version " + version + " --assetIndex " + JsonUtils.getAssesNum(MINECRAFT_PATH + "\\versions\\" + version + "\\" + vers + ".json") + " --gameDir"  + gamedir + " -WorkingDirectory " + MINECRAFT_PATH + "\\versions\\" + version + "\\" + version + " -DFabricMcEmu= net.minecraft.client.main.Main --assetsDir" + DISC + USER_NAME + "\\AppData\\Roaming\\.minecraft\\assets\"";
            log(logger ,command);
            return command;
        }else{
            String command = RutaJava + "bin\\java -Xmx" + ram + "G -Xms2G -XX:+UnlockExperimentalVMOptions -XX:+UseG1GC -XX:G1NewSizePercent=20 -XX:G1ReservePercent=20 -XX:MaxGCPauseMillis=50 -XX:G1HeapRegionSize=32M -cp \".;" + pathOfTheJar + ";" + pathOfLibsCarpet + "\" " + MainClass + " --accessToken " + accesstoken + " --Username " + name + " --version " + version + " --assetIndex " + JsonUtils.getAssesNum(MINECRAFT_PATH + "\\versions\\" + version + "\\" + vers + ".json") + " --gameDir"  + gamedir + " -WorkingDirectory " + MINECRAFT_PATH + "\\versions\\" + version + "\\" + version + " -DFabricMcEmu= net.minecraft.client.main.Main --assetsDir" + DISC + USER_NAME + "\\AppData\\Roaming\\.minecraft\\assets\"";
            log(logger ,command);
            return command;
        }
        
    }
    public static String getJREVersion(String version,String JsonPath, String DirectoyOfBin) throws IOException {
        // Crear un ObjectMapper para leer el JSON
        ObjectMapper objectMapper = new ObjectMapper();
        int versionJava = 0;
        // Leer el archivo JSON y convertirlo en un objeto JsonNode
        JsonNode rootNode = objectMapper.readTree(new File(JsonPath));
        JsonNode JVersionNode = null;
        JsonNode JavaJsonNode = rootNode.get("javaVersion");
        if(JavaJsonNode == null){
            versionJava = 0;
        }else{
            JVersionNode = JavaJsonNode.get("majorVersion");
            versionJava = JVersionNode.asInt();
        }
        Path bin = Path.of(DirectoyOfBin);

        if(Files.exists(bin)){
            log(logger ,"bin exist");
        }else{
            UtilsFiles.createDirectory(DirectoyOfBin, "");
        }


        String UUIDB;
        if(versionJava <= 8){
            UUID JreUUID8 = UUID.randomUUID();
            UtilsFiles.download("https://github.com/adoptium/temurin8-binaries/releases/download/jdk8u382-b05/OpenJDK8U-jre_x64_windows_hotspot_8u382b05.zip", JRE8W, JreUUID8.toString() , ".zip");
            UtilsFiles.createDirectory(JRE8W + "/", JreUUID8.toString());
            UtilsFiles.unZIP(JRE8W + "/" + JreUUID8.toString() + ".zip", JRE8W + "/" + JreUUID8.toString() + "/");
            Path binSource = Paths.get(JRE8W + "/" + JreUUID8.toString() + "/" + "jdk8u382-b05-jre");
            Path binDest = Paths.get( DirectoyOfBin + "\\" + JreUUID8.toString());
            Files.move(binSource,binDest);
            log(logger ,"is java 8");
            RutaJava = DirectoyOfBin + "\\" + JreUUID8.toString()+ "\\";
            UUIDB =  DirectoyOfBin + "\\" + JreUUID8.toString()+ "\\";
            int JavaV = 8;
            UtilsFiles.getnatives(DISC + USER_NAME + "\\DOCUMENTS\\LauncherTrinity\\libs\\" + version + "\\",  DirectoyOfBin + "\\" + JreUUID8.toString(), ".zip");

        }else{
            UUID JreUUID20 = UUID.randomUUID();
            UtilsFiles.download("https://github.com/adoptium/temurin20-binaries/releases/download/jdk-20.0.1%2B9/OpenJDK20U-jre_x64_windows_hotspot_20.0.1_9.zip", JRE20W, JreUUID20.toString() , ".zip");
            UtilsFiles.createDirectory(JRE20W + "/", JreUUID20.toString());
            UtilsFiles.unZIP(JRE20W + "/" + JreUUID20.toString() + ".zip", JRE20W + "/" + JreUUID20.toString() + "/");
            Path binSource = Paths.get(JRE20W + "/" + JreUUID20.toString() + "/" + "jdk-20.0.1+9-jre");
            Path binDest = Paths.get( DirectoyOfBin +  "\\" + JreUUID20.toString());
            Files.move(binSource,binDest);
            log(logger ,"is java 20");
            RutaJava =  DirectoyOfBin + "\\" + JreUUID20.toString()+ "\\";
            UUIDB = DirectoyOfBin + "\\" + JreUUID20.toString()+ "\\";

            int JavaV = 20;

        }

        return UUIDB;
    }

}
