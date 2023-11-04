package net.kender.lt.RM;

import static net.kender.lt.cons.*;
import static net.kender.lt.controlers.Controler.*;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Iterator;

import com.fasterxml.jackson.databind.JsonNode;

import net.kender.Kjson.Json;
import net.kender.logger.log5k.Logger;
import net.kender.lt.TexturesManager.Texture;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.paint.Color;
public class RM {

    //private static final String SEPARATOR = "-------------------";

    public static boolean cpbb = false;
    public static boolean playbb = false;
    public static boolean profilesbb = false;
    public static boolean centralpbb = false;
    public static boolean tabsbb = false;
    public static boolean downbb = false;
    public static boolean configbb = false;
    public static boolean changeNBbb = false;
    public static boolean pluspbb = false;
    public static boolean napbb = false;
    public static boolean impbb = false;

    private static Logger logger = new Logger(RM.class);

    public static void readTexture(String texture) {
        File jsonFile = new File(LTP + "\\Textures\\" + texture);
        logger.INFO(jsonFile);

        readT(new File("C:\\Users\\krist\\Desktop\\llamada\\assets\\"));

    }
    
        public static void setPane (String ubi){

        }
        public static void setMesure (String whith, String height, String index){

        }
        public static void setUbication (String x, String y){

        }
        public static ObservableList<String> getLT () {
            File folder = new File(LTP + "\\Textures\\");

            ObservableList<String> optionsT = FXCollections.observableArrayList();

            File[] files = folder.listFiles();


            // Itera sobre los archivos y carpetas en el directorio
            for (File file : files) {

                logger.INFO("Directorio: " + file.getName());
                optionsT.add(file.getName());

            }
            return optionsT;
        }
        public static File ese;
        private static void readT(File archivoZip){
            ese = archivoZip;
            Json j = new Json(new File(archivoZip + "\\config.json"));
            JsonNode root = null;
            try {
                root = j.getRootNode();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            if(new File(archivoZip + "\\config.json").exists()){
                if (root.isObject()) {
                    // Obtener las claves del objeto JSON
                    Iterator<String> tr = root.fieldNames();
                    tr.forEachRemaining(fieldName -> {
                        System.out.println("Clave: " + fieldName);
                        
                        JsonNode g = null;
                        try {
                            g = j.loadNode(fieldName);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        if(g.has("color")){
                            logger.DEBUG("COLOR");
                            Color c = Color.valueOf(g.get("color").asText());
                            new Texture(c,fieldName);
                            comp(fieldName);
                        } else if(g.has("file") || g.has("url")) {
                            logger.DEBUG("COLORNONO");
                            double whidt = g.get("width").asDouble();
                            double height = g.get("height").asDouble();
                            boolean complete = g.get("complete").asBoolean();
                            logger.DATA("complete",complete);
                            if(g.has("url")){
                                URI u;
                                try {
                                    u = new URI(g.get("url").asText());
                                
                                new Texture(new Image(u.toString()),whidt,height,complete,fieldName);
                                comp(fieldName);
                                } catch (URISyntaxException e) {
                                    e.printStackTrace();
                                }
                            } else {
                                if(g.get("file").asText().contains("assets/minecraft")){
                                    logger.DEBUG("file:///" + LTP + "\\Textures\\minecraft\\" + g.get("file"));
                                    new Texture(new Image("file:///" + LTP + "\\Textures\\minecraft\\" + g.get("file").asText()),whidt,height,complete,fieldName);
                                    comp(fieldName);
                                }else{
                                    new Texture(new Image("file:///" + ese + g.get("file")),whidt,height,complete,fieldName);
                                    comp(fieldName);
                                }
                            }
                        }
                    });
                }
                // TODO leer el json con la configuracion para las inagenes
            }

            String LTPO = "file:///" + LTP;
            archivoZip = new File("file:///" + archivoZip);

            if (!centralpbb) {
                new Texture(new Image(LTPO + "\\Textures\\minecraft\\assets\\minecraft\\textures\\gui\\options_background.png"), 0,0,false,"centralPb");
            }

            if (!playbb) {
                new Texture(new Image(LTPO + "\\Textures\\minecraft\\assets\\minecraft\\textures\\block\\emerald_block.png"), 0,0,true,"playBb");
            }

            if (!profilesbb) {
                
                new Texture(new Image(LTPO + "\\Textures\\minecraft\\assets\\minecraft\\textures\\block\\blast_furnace_top.png"), 0,0,false,"profilesb");
            }

            if (!cpbb) {
                new Texture(new Image(LTPO + "\\Textures\\minecraft\\assets\\minecraft\\textures\\block\\cobbled_deepslate.png"), 0,0,false,"cpb");
            }

            if (!tabsbb) {
                new Texture(new Image(LTPO + "\\Textures\\minecraft\\assets\\minecraft\\textures\\block\\chiseled_bookshelf_occupied.png"), 0,0,false,"tabsb");
            }

            if (!downbb) {
                new Texture(new Image(LTPO + "\\Textures\\minecraft\\assets\\minecraft\\textures\\block\\dark_oak_planks.png"),0,0,false,"downb");
            }

            if (!configbb) {
                new Texture(new Image(LTPO + "\\Textures\\minecraft\\assets\\minecraft\\textures\\item\\barrier.png"),0.5,1,true,"configBb");
            }

    }
    public static void comp(String index){
        switch (index) {
            case "cpb":
                cpbb = true;
                break;
            case "centralPb":
                centralpbb = true;
                break;
            case "tabsb":
                tabsbb = true;
                break;
            case "profilesb":
                profilesbb = true;
                break;
            case "downb":
                downbb = true;
                break;
            case "playBb":
                playbb = true;
                break;
            case "changeNBb":
                changeNBbb = true;
                break;
            case "configBb":
                configbb = true;
                break;
            case "plusPb":
                pluspbb = true;
                break;
            case "naPb":
                napbb = true;
                break;
            case "imPb":
                impbb = true;
                break;
    }
    }
}