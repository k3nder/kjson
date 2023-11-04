package net.kender.core.sample.EXTRAS;

import static net.kender.core.sample.EXTRAS.EXTRAS.*;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Path;

import com.fasterxml.jackson.databind.JsonNode;

import net.kender.logger.log5k.Logger;

public class LIBS {

    private static String SEPARATOR = "----------";
    private static Logger logger = new Logger(LIBS.class);

    public static String getlibs(Path __DEST__, JsonNode libs) {
        logger.INFO("dowloading the libs");
        logger.INFO(SEPARATOR);

        String pathLibs = "";

        Path libsPath = __DEST__;
        for (JsonNode libraryNode : libs) {
            // Paso 3: Acceder a los elementos "downloads" y "name" de cada objeto
            // "libraries"
            JsonNode dowloads = libraryNode.get("downloads");

            // Verificar si "downloads" no es nulo
            if (dowloads != null && dowloads.isObject()) {

                boolean artexi = dowloads.has("artifact");
                if (artexi) {
                    artexi(dowloads, libsPath.toString());
                }

                boolean cassexist = dowloads.has("classifiers");

                if (cassexist) {
                    cassexist(dowloads, libsPath.toString());
                }
            } else {
                logger.WARN("no");
            }

        }
        return pathLibs;

    }

    private static final String SAVE_IN = "save in: ";

    private static void cassexist(JsonNode dowloads, String pathOfLibs) {
        JsonNode classifiersNode = dowloads.get("classifiers");
        boolean nativesOS = classifiersNode.has("natives-windows");
        if (nativesOS) {
            nativesos(classifiersNode, pathOfLibs);
        }
        boolean nativesArch = classifiersNode.has("natives-windows-64");
        if (nativesArch) {
            try {
                JsonNode archNode = classifiersNode.get("natives-windows-64");
                JsonNode urlArch = archNode.get("url");
                JsonNode pathArch = archNode.get("path");

                String url = urlArch.asText();
                URI urlU = new URI(url);
                logger.INFO( "dowloading: " + urlU);
                download(urlU, new File(pathOfLibs + "\\" + obtenerNombreArchivo(pathArch.asText())));
                logger.INFO("download completed");
            } catch (URISyntaxException e) {
                logger.ERROR(e);
            }
        } else {
            logger.ERROR("windows natives dosent exist return null");
        }

    }

    private static void artexi(JsonNode dowloads, String pathOfLibs) {
        JsonNode art = dowloads.get("artifact");

        JsonNode urlNode = art.get("url");
        JsonNode packagE = art.get("path");
        try {
            String url = urlNode.asText();
            URI urlI = new URI(url);
            String paths = packagE.asText();

            logger.INFO( SEPARATOR);
            logger.INFO( "dowloadig: " + url);
            if (!paths.contains("macos")) {
                if (!paths.contains("linux")) {
                    if (!paths.contains("86")) {
                        logger.INFO("on: " + paths);
                        logger.INFO( SEPARATOR);
                        Path p = Path.of(pathOfLibs + "\\" + paths, "");
                        if (p.toFile().exists()) {
                            logger.INFO( "the dependecy alredy dowloaded");
                            logger.INFO(SEPARATOR);
                        } else {

                            download(urlI, new File(pathOfLibs + "\\" + obtenerNombreArchivo(paths)));

                            logger.INFO( "dowloaded completed");
                            logger.INFO( SEPARATOR);
                        }
                    }
                }
            }
        } catch (URISyntaxException e) {
            logger.ERROR(e);
        }

    }

    private static void nativesos(JsonNode classifiersNode, String pathOfLibs) {
        try {
            JsonNode natives = classifiersNode.get("natives-windows");
            JsonNode nativesPathNode = natives.get("path");
            JsonNode urlnatives = natives.get("url");
            String urlNatives = urlnatives.asText();
            URI url = new URI(urlNatives);
            // String nativesPath = nativesPathNode.asText();
            // String nameos = quitarUltimaParteRuta(nativesPath);
            logger.INFO( "dowloading: " + urlnatives);
            download(url, new File(pathOfLibs + "\\" + obtenerNombreArchivo(nativesPathNode.asText())));
            logger.INFO( SAVE_IN + pathOfLibs + "\\");
            logger.INFO( "dowloaded completed");
        } catch (URISyntaxException e) {
            logger.ERROR(e);
        }
    }

    // private static String quitarUltimaParteRuta(String ruta) {
    // if (ruta == null || ruta.isEmpty()) {
    // return ruta;
    // }

    // int ultimoSeparador = ruta.lastIndexOf(File.separator);
    // if (ultimoSeparador != -1) {
    // return ruta.substring(0, ultimoSeparador);
    // } else {
    // return ruta;
    // }
    // }
    private static String obtenerNombreArchivo(String ruta) {
        // Crear un objeto File a partir de la ruta
        File archivo = new File(ruta);
        // Obtener el nombre del archivo
        return archivo.getName();

    }
}
