package net.kender.core;

import static net.kender.logger.Log.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.apache.commons.io.FileUtils;

import net.kender.logger.log5k.Logger;

/**
 *
 * @author krist
 */
public class UtilsFiles {
    private static final String SEPARATOR = "-------------------";

    private static Logger logger = new Logger(UtilsFiles.class);

    private UtilsFiles() {
    }

    public static void createFile(String path, String extension, String fileName, String fileContent) {

        fileName = fileName + "." + extension;

        try {
            // Crea un objeto File que representa el archivo en el directorio especificado
            File file = new File(path, fileName);

            // Crea el archivo con el contenido especificado
            FileUtils.writeStringToFile(file, fileContent, "UTF-8");
            logger.INFO( SEPARATOR);
            logger.INFO( "the arch is creted: " + file.getAbsolutePath());
            logger.INFO( SEPARATOR);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void download(String url, String path, String name, String exten) {

        try {
            // Crear una URL a partir de la dirección del archivo .jar
            URI uri = new URI(url);
            URL urlR = uri.toURL();

            // Descargar el archivo .jar y guardarlo en el directorio destino con el nuevo
            // nombre
            FileUtils.copyURLToFile(urlR, new File(path, name + exten));

            String mgs = "the download has completed: " + url;
            logger.INFO( mgs);

        } catch (URISyntaxException | IOException e) {
            logger.ERROR( e.toString());
        }

    }

    public static void run(String command) {
        String msg = "inatlizin the command: " + command;
        log( msg);
        try {
            ProcessBuilder processBuilder = new ProcessBuilder("cmd.exe", "/c", command);
            processBuilder.directory(new File("C:\\Users\\krist\\Documents\\.LT\\LT-2.0\\LT\\MC"));
            processBuilder.redirectErrorStream(true); // Redirige la salida de error al mismo flujo de entrada

            Process process = processBuilder.start();
            InputStream inputStream = process.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            String linea;
            while ((linea = reader.readLine()) != null) {
                log( linea);
            }

            int codigoSalida = process.waitFor();
            String codigoDeSalidaMgs = "exit code: " + codigoSalida;
            log( codigoDeSalidaMgs);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }

    public static void createDirectory(String path, String name) {
        if (name == null) {
            try {
                Path pathh = Paths.get(path);
                // Crea el directorio si no existe
                Files.createDirectories(pathh);

                String msg = "Directorio directory: " + path + " created";
                log( msg);
            } catch (IOException e) {
                e.printStackTrace();
                log( "directory arledy exist");
            }
        } else {
            File carpeta = new File(path + name);

            if (!carpeta.exists()) {
                if (carpeta.mkdir()) {
                    String mgs = "carpet: " + name + " created";
                    log( mgs);
                } else {
                    String mgs = "Error in create carpet: " + name;
                    log( mgs);
                }
            } else {
                log( "the carpet arledy exist");
            }
        }
    }

    public static void unZIP(String zipFilePath, String destDirectory) throws IOException {
        byte[] buffer = new byte[1024];

        try (ZipInputStream zis = new ZipInputStream(new FileInputStream(zipFilePath))) {
            ZipEntry zipEntry = zis.getNextEntry();
            while (zipEntry != null) {
                String fileName = zipEntry.getName();
                File newFile = new File(destDirectory + fileName);

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
            }
            String mgs = "the unzip has completed: " + zipFilePath;
            log( SEPARATOR);
            log( mgs);
            log( SEPARATOR);
        }
    }

    public static void getnatives(String directory, String nativesDesti, String extend) throws IOException {
        File carpeta = new File(directory);

        if (carpeta.exists() && carpeta.isDirectory()) {
            File[] archivos = carpeta.listFiles();
            if (archivos != null) {
                for (File archivo : archivos) {
                    if (archivo.isFile() && archivo.getName().contains("natives")) {
                        copyAndUnzipFile(archivo, directory, nativesDesti, extend);
                    }
                }
            }
        } else {
            log( "El directorio especificado no existe o no es válido.");
        }
    }

    private static void copyAndUnzipFile(File archivo, String directory, String nativesDesti, String extend)
            throws IOException {
        Path nativesSources = Paths.get(directory, archivo.getName());
        Path nativesDest = Paths.get(nativesDesti, archivo.getName() + extend);

        try {
            Files.copy(nativesSources, nativesDest);
            unZIP(nativesDest.toString(), nativesDesti);
        } catch (FileAlreadyExistsException e) {
            log( "----------");
            log( "El archivo ya existe.");
            log( "----------");
        }
    }

    public static String[] getListOfCarpets(String directory) {
        // Creamos un objeto File con la ruta del directorio
        File directorioActual = new File(directory);

        String[] nombresCarpetas = null;

        // Verificamos si el objeto File representa un directorio válido
        if (directorioActual.isDirectory()) {
            // Obtenemos un array de objetos File que representan las carpetas dentro del
            // directorio
            File[] carpetas = directorioActual.listFiles(File::isDirectory);

            // Verificamos si el array no es nulo y contiene elementos
            if (carpetas != null && carpetas.length > 0) {
                // Creamos un array de String para almacenar los nombres de las carpetas
                nombresCarpetas = new String[carpetas.length];

                // Iteramos sobre el array de carpetas y almacenamos sus nombres en el array de
                // String
                for (int i = 0; i < carpetas.length; i++) {
                    nombresCarpetas[i] = carpetas[i].getName();
                }

                // Ahora el array "nombresCarpetas" contiene los nombres de todas las carpetas
                // dentro del directorio y puedes utilizarlo según tus necesidades.
            } else {
                log( "No hay carpetas dentro del directorio.");
            }
        } else {
            log( "La ruta proporcionada no corresponde a un directorio válido.");
        }

        return nombresCarpetas;
    }
    
}
