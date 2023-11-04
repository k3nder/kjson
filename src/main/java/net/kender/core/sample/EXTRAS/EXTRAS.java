package net.kender.core.sample.EXTRAS;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.nio.file.Path;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.databind.JsonNode;

import net.kender.core.JsonUtils;
import net.kender.core.UtilsFiles;
import net.kender.core.sample.CommandConstructor;
import net.kender.logger.log5k.Logger;
import net.kender.logger.log5k.conf.CustomLogger;

public class EXTRAS {
    private static String SEPARATOR = "----------";
    private static Logger logger = new Logger(EXTRAS.class);

    public static void createJsonVersion(Path __DEST__, String __VERSION__) {
        String url = JsonUtils.getUrlOfJsonOfVersion("https://launchermeta.mojang.com/mc/game/version_manifest.json",
                __VERSION__);
        System.out.println(url);
        logger.DATA("URL",url,"url of json");
        UtilsFiles.createFile(__DEST__.toString() + "\\versions\\" + __VERSION__ + "\\", "json", __VERSION__,
        JsonUtils.getJsonVersion(url));
    }

    public static String getlibs(Path __DEST__, JsonNode libs) {
        logger.INFO( "dowloading the libs");
        logger.INFO(SEPARATOR);

        String pathLibs = "";

        Path libsPath = Path.of(__DEST__ + "\\libraries\\");
        for (JsonNode libraryNode : libs) {
            // Paso 3: Acceder a los elementos "downloads" y "name" de cada objeto
            // "libraries"
            JsonNode dowloads = libraryNode.get("downloads");

            // Verificar si "downloads" no es nulo
            if (dowloads != null && dowloads.isObject()) {

                boolean artexi = dowloads.has("artifact");
                if (artexi) {
                    pathLibs = pathLibs + "libraries\\" + artexi(dowloads, libsPath.toString());
                }

                boolean cassexist = dowloads.has("classifiers");

                if (cassexist) {
                    pathLibs = pathLibs + "libraries\\" + cassexist(dowloads, libsPath.toString());
                }
            } else {
                logger.WARN("no");
            }

        }
        return pathLibs;

    }

    private static final String SAVE_IN = "save in: ";

    private static String cassexist(JsonNode dowloads, String pathOfLibs) {
        JsonNode classifiersNode = dowloads.get("classifiers");
        boolean nativesOS = classifiersNode.has("natives-windows");
        if (nativesOS) {
            nativesos(classifiersNode, pathOfLibs);
        }
        boolean nativesArch = classifiersNode.has("natives-windows-64");
        if (nativesArch) {
            JsonNode archNode = classifiersNode.get("natives-windows-64");

            JsonNode urlArch = archNode.get("url");
            JsonNode pathArch = archNode.get("path");

            String urlS = urlArch.asText();

            logger.INFO( "dowloading: " + urlS);

            UtilsFiles.download(urlS, pathOfLibs + "\\" + pathArch.asText(), "", "");

            logger.INFO( "download completed");
            return pathArch.asText() + ";";
        } else {
            logger.WARN("windows natives dosent exist return null");
            return null;
        }

    }

    private static String artexi(JsonNode dowloads, String pathOfLibs) {
        JsonNode art = dowloads.get("artifact");
       


        JsonNode urlNode = art.get("url");
        JsonNode packagE = art.get("path");

        String url = urlNode.asText();
        String paths = packagE.asText();

        logger.INFO( SEPARATOR);
        logger.INFO( "dowloadig: " + url);
   
        if (!paths.contains("macos")) {
            if (!paths.contains("linux")) {
                if (!paths.contains("86")) {
                    logger.INFO( "on: " + paths);
                    logger.INFO( SEPARATOR);
                    Path p = Path.of(pathOfLibs + "\\" + paths, "");
                    if (p.toFile().exists()) {
                        logger.INFO( "the dependecy alredy dowloaded");
                        logger.INFO( SEPARATOR);
                        return paths + ";";
                    } else {

                        UtilsFiles.download(url, pathOfLibs + "\\", paths, "");

                        logger.INFO( "dowloaded completed");
                        logger.INFO( SEPARATOR);
                        return paths + ";";
                    }
                } else {
                    return "";
                }
            } else {
                return "";
            }
        } else {
            return "";
        }

    }

    private static void nativesos(JsonNode classifiersNode, String pathOfLibs) {
        JsonNode natives = classifiersNode.get("natives-windows");
        JsonNode nativesPathNode = natives.get("url");
        JsonNode urlnatives = natives.get("url");
        String urlNatives = urlnatives.asText();
        String nativesPath = nativesPathNode.asText();
        String nameos = quitarUltimaParteRuta(nativesPath);
        logger.INFO( "dowloading: " + urlnatives);
        UtilsFiles.download(urlNatives, pathOfLibs + "\\", nameos, ".jar");
        logger.INFO( SAVE_IN + pathOfLibs + "\\");
        logger.INFO( "dowloaded completed");
    }

    private static String quitarUltimaParteRuta(String ruta) {
        if (ruta == null || ruta.isEmpty()) {
            return ruta;
        }

        int ultimoSeparador = ruta.lastIndexOf(File.separator);
        if (ultimoSeparador != -1) {
            return ruta.substring(0, ultimoSeparador);
        } else {
            return ruta;
        }
    }

    public static void createFile(File d, String fileContent) {
        // Crea el archivo con el contenido especificado
        try {
            FileUtils.writeStringToFile(d, fileContent, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        //log( SEPARATOR);
        //log( "the arch is creted: " + d.getAbsolutePath());
        //log( SEPARATOR);
    }

    public static void download(URI url, File h) {
        try {
            FileUtils.copyURLToFile(url.toURL(), h);

            logger.INFO("the download has completed: " + url);

        } catch (IOException e) {
            logger.ERROR( e.toString());
        }
    }

    public static void run(CommandConstructor command, File Dyrectory) {
        logger.DEBUG("inatlizin the command: " + command.asText());
        try {
            ProcessBuilder processBuilder = new ProcessBuilder("cmd.exe", "/c", command.asText());
            processBuilder.directory(Dyrectory);
            processBuilder.redirectErrorStream(true); // Redirige la salida de ERROR al mismo flujo de entrada

            Process process = processBuilder.start();
            InputStream inputStream = process.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            CustomLogger r = new CustomLogger("\u001B[144;238;144", "MC", false, false, false, true, false);

            String linea;
            while ((linea = reader.readLine()) != null) {
                logger.log(linea,r);
            }

            int codigoSalida = process.waitFor();
            String codigoDeSalidaMgs = "exit code: " + codigoSalida;
            logger.DEBUG( codigoDeSalidaMgs);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }

    public static void unZIP(File zipFilePath, Path destDirectory) throws IOException {
        byte[] buffer = new byte[1024];

        try (ZipInputStream zis = new ZipInputStream(new FileInputStream(zipFilePath))) {
            ZipEntry zipEntry = zis.getNextEntry();
            while (zipEntry != null) {
                String fileName = zipEntry.getName();
                File newFile = new File(destDirectory + "\\" + zipEntry.getName());

                // Si el nombre termina con "/" es un directorio
                if (fileName.endsWith("/")) {
                    newFile.mkdirs();
                } else {
                    // Si no, es un archivo y se descomprime
                    new File(newFile.getParent()).mkdirs();
                    try (FileOutputStream fos = new FileOutputStream(newFile)) {
                        int length;
                        while ((length = zis.read(buffer)) > 0) {
                            fos.write(buffer, 0, length);
                        }
                    }
                }

                zipEntry = zis.getNextEntry();
                File d = new File(zipFilePath.toString());
                d.renameTo(new File(zipFilePath.toString().replace(zipFilePath.getName(), "jre")));
            }

            logger.INFO( SEPARATOR);
            logger.INFO("the unzip has completed: " + zipFilePath);
            logger.INFO( SEPARATOR);
        }
    }

    public static String read(File e) {
        try {
            BufferedReader lector = new BufferedReader(new FileReader(e));
            String linea;

            while ((linea = lector.readLine()) != null) {
                System.out.println(linea); // Imprime cada línea del archivo
            }
            lector.close();
            return linea;
        } catch (IOException e1) {
            e1.printStackTrace();
            return "";
        }
    }
}
