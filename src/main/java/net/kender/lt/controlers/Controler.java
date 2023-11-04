package net.kender.lt.controlers;

import static net.kender.core.UtilsFiles.*;
import static net.kender.lt.cons.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.web.WebView;
import javafx.util.Duration;
import net.kender.Kjson.Json;
import net.kender.MCutils.players.worlds.World;
import net.kender.core.sample.MC;
import net.kender.core.sample.Quikplays.SinglePlayer;
import net.kender.logger.log5k.Logger;
import net.kender.lt.Funtions;
import net.kender.lt.cons;
import net.kender.lt.RM.RM;

public class Controler {

    private static final String SEPARATOR = "-------------------";

    // panels
    @FXML
    private Pane cp;

    @FXML
    private Pane centralP;

    @FXML
    private VBox tabs;

    @FXML
    private HBox profiles;

    @FXML
    private Pane down;
    // button
    @FXML
    private Button playB;

    @FXML
    private Button changeNB;

    @FXML
    private Button configB;

    @FXML
    private Button plusP;
    // etc
    @FXML
    private Text naP;

    @FXML
    private ImageView imP;

    @FXML
    private ListView<String> listmods;

    @FXML
    private TextArea console;

    public static Background cpb;

    public static Background centralPb;

    public static Background tabsb;

    public static Background profilesb;

    public static Background downb;
    // button

    public static Background playBb;

    public static Background changeNBb;

    public static Background configBb;

    public static Background plusPb;
    // etc

    public static Background naPb;

    public static Background imPb;

    @FXML
    private Pane slidingPanel;

    public static Boolean F = false;

    // config panel

    public static Boolean ift = false;
    public static Boolean whit = true;

    @FXML
    private WebView webb;
    public static Background webbb;

    @FXML
    private AnchorPane anp;

    @FXML
    private Text por;

    public static Boolean cancel = false;

    // buttons functions
    private TranslateTransition slideOut;
    private TranslateTransition slideIn;

    public static final File config = new File(LTP + "\\config\\config.json");
    private static final Logger logger = new Logger(Controler.class);

    @FXML
    protected void play() throws IOException {
        slideIn.play();
        slidingPanel.setDisable(false);
        slidingPanel.setVisible(true);

        por.setText("0%");
        MC runable = new MC(Path.of("C:\\Users\\krist\\Documents\\.LT\\LT-2.0\\LT"), "1.20.2");
        runable.Run(() -> {
            setPC(por, 16);
        },
                () -> {
                    por.setText("canleled");
                    slideOut.play();
                    por.setText("");
                    cancel = false;
                },
                slideOut,
                por);
    }

    @FXML
    protected void config() throws IOException, InterruptedException {
        Funtions.config();
    }

    @FXML
    protected void plusPr() throws IOException, InterruptedException {

    }

    @FXML
    protected void changeN() throws IOException, InterruptedException {

    }
    // inatlizing method

    public void initialize() throws IOException {
        //readP();
        slidingPanel.setDisable(true);
        slidingPanel.setVisible(false);

        // Especifica la ruta del directorio que deseas buscar
        String directorio = MINECRAFT_PATH + "\\resourcepacks\\";

        // Llama a la función para listar archivos .zip en el directorio
        List<String> archivosZip = listarArchivosZip(directorio);

        // Imprime los nombres de los archivos .zip encontrados
        slidingPanel.setPrefSize(100, 100);
        slidingPanel.setTranslateX(-100); // Inicialmente, fuera del panel principal

    
        
        BackgroundImage background = new BackgroundImage(
                new Image(new File(LTP + "\\Textures\\minecraft\\assets\\minecraft\\textures\\gui\\hanging_signs\\jungle.png").toURI().toURL().toExternalForm()),
                BackgroundRepeat.NO_REPEAT, // No repetir la imagen en ninguna dirección
                BackgroundRepeat.NO_REPEAT, // No repetir la imagen en ninguna dirección
                BackgroundPosition.CENTER, // Centrar la imagen en el contenedor
                new BackgroundSize(1, 1, true, true, false, false) // Estirar la imagen para que ocupe todo el espacio
        );

        // Animación de deslizamiento al ocultar el panel
        slideOut = new TranslateTransition(Duration.seconds(1), slidingPanel);
        slideOut.setToX(-100); // Vuelve a ocultar el panel (fuera del panel principal)

        // Animación de deslizamiento al abrir el programa
        slideIn = new TranslateTransition(Duration.seconds(1), slidingPanel);
        slideIn.setToX(0); // Desliza hacia la esquina superior izquierda

        slidingPanel.setOnMouseClicked(event -> {
            // Aquí puedes colocar el código que deseas ejecutar cuando se hace clic en el
            // StackPane.
            slideOut.play();
            slidingPanel.setDisable(true);
            slidingPanel.setVisible(false);
            cancel = true;
        });

        slidingPanel.setBackground(new Background(background));
        Json json = new Json(config);
        webb.setVisible(false);
        RM.readTexture(json.loadString("Texture").replace("\"", ""));

        profiles.setBackground(profilesb);
        cp.setBackground(cpb);
        centralP.setBackground(centralPb);
        tabs.setBackground(tabsb);
        down.setBackground(downb);
        playB.setBackground(playBb);
        changeNB.setBackground(changeNBb);
        configB.setBackground(configBb);
        plusP.setBackground(plusPb);

        for (String archivo : archivosZip) {
            unZIP(MINECRAFT_PATH + "\\resourcepacks\\" + archivo,
                    LTP + "\\Textures\\" + archivo.replace(".zip", "") + "\\");
        }
        readTabs();
    }

    public void addtab(String iconI, String color, String dat, String type) throws IOException {
        Button boton = new Button("");
        Json json = new Json(config);
        logger.DEBUG("file:///" + LTP + "\\Textures\\" + json.loadString("Texture").replace("\"", "")
                + "\\assets\\minecraft\\textures\\item\\" + iconI);
        File ic = new File("file:///" + LTP + "\\Textures\\" + json.loadString("Texture").replace("\"", "")
                + "\\assets\\minecraft\\textures\\item\\" + iconI);
        Image image = null;
        if (ic.exists()) {
            image = new Image("file:///" + LTP + "\\Textures\\" + json.loadString("Texture").replace("\"", "")
                    + "\\assets\\minecraft\\textures\\item\\" + iconI); // Reemplaza con la ruta de tu imagen
        } else {
            image = new Image("file:///" + LTP + "\\Textures\\minecraft\\assets\\minecraft\\textures\\item\\" + iconI);
        }
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(32); // Ajusta el ancho de la imagen según tus necesidades
        imageView.setFitHeight(32); // Ajusta el alto de la imagen según tus necesidades

        boton.setGraphic(imageView); // Establece la imagen como gráfico del botón

        BackgroundFill backgroundFill = new BackgroundFill(Color.web(color), new CornerRadii(5), null);
        Background background = new Background(backgroundFill);

        boton.setBackground(background);

        // Crear un contenedor HBox para organizar la imagen y el botón
        HBox hbox = new HBox(10); // Espacio entre la imagen y el botón
        hbox.setAlignment(Pos.CENTER);
        hbox.getChildren().addAll(imageView, boton);
        boton.setDisable(false);

        // Crear un Tooltip con el texto que quieres mostrar
        Tooltip tooltip = new Tooltip("");

        switch (type) {
            case "web":
                tooltip.setText("open: " + dat);
                break;
            case "mods":
                tooltip.setText("manager of mods: " + dat);
                break;
            case "modpacks":
                tooltip.setText("modpacks managar of: " + dat);
                break;
            case "console":
                tooltip.setText("console of: " + dat);
                break;
            case "options":
                tooltip.setText("options of: " + dat);
                break;
            case "quickplay":
                tooltip.setText("openWorld: " + dat);
                break;

        }

        // Establecer el Tooltip en el botón
        Tooltip.install(boton, tooltip);

        boton.setOnAction(eve -> {
            logger.INFO( "bootoa" + boton.getText());
            switch (type) {
                case "web":

                    listmods.setVisible(false);
                    listmods.setDisable(true);
                    console.setVisible(false);
                    console.setDisable(true);
                    webb.setVisible(true);
                    webb.setDisable(false);

                    webb.getEngine().load(dat);
                    break;
                case "mods":
                    listmods.setVisible(true);
                    listmods.setDisable(false);
                    console.setVisible(false);
                    console.setDisable(true);
                    webb.setVisible(false);
                    webb.setDisable(true);

                    addlistM(dat);

                    break;
                case "modpacks":
                    break;
                case "console":
                    break;
                case "options":
                    break;
                case "quickplaySingle":
                    slidingPanel.setDisable(false);
                    slidingPanel.setVisible(true);

                    por.setText("0%");
                    MC quik = new MC(Path.of(MINECRAFT_PATH),"1.20.2");            
                    slideIn.play();
       
                    quik.Run(new SinglePlayer(new World(Path.of(dat))));
                    break;
                }
            });
        tabs.getChildren().add(hbox);
    }

    public void readTabs() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        File jsonFile = new File(cons.LTP + "\\config\\config.json");
        JsonNode jsonNode = objectMapper.readTree(jsonFile);
        JsonNode tabs = jsonNode.get("tabs");
        ObjectNode objectNode = (ObjectNode) tabs;

        Iterator<String> fieldNames = objectNode.fieldNames();
        while (fieldNames.hasNext()) {
            String key = fieldNames.next();

            JsonNode r = tabs.get(key);

            JsonNode type = r.get("type");
            JsonNode color = r.get("color");
            JsonNode url = r.get("url");

            logger.DEBUG( SEPARATOR + "\nkey: " + key + "\ntab Icon: " + r.get("Icon") + "\ntype: " + type + "\ncolor: "
                    + color + "\nurl: " + url + "\n" + SEPARATOR);
            addtab(r.get("Icon").toString().replace("\"", ""), color.toString().replace("\"", ""),
                    url.toString().replace("\"", ""), type.toString().replace("\"", ""));

        }

    }

    public void addlistM(String GAME_DIR) {
        File folder = new File(GAME_DIR + "\\mods\\");

        File[] files = folder.listFiles();

        // Itera sobre los archivos y carpetas en el directorio
        for (File file : files) {

            logger.INFO( "Directorio: " + file.getName());
            listmods.getItems().add("   " + file.getName());

            listmods.setCellFactory(param -> new ListCell<String>() {
                private final CheckBox checkBox = new CheckBox();

                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);

                    if (empty || item == null) {
                        setText(null);
                        setGraphic(null);
                    } else {
                        if (item.contains("jar")) {
                            checkBox.setSelected(true); // Puedes establecer el estado inicial del CheckBox aquí
                        } else {
                            checkBox.setSelected(false);
                        }

                        setGraphic(new HBox(checkBox, new javafx.scene.text.Text(item)));

                        // Agregar un manejador de eventos al CheckBox
                        checkBox.selectedProperty().addListener(new ChangeListener<Boolean>() {
                            @Override
                            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue,
                                    Boolean newValue) {
                                if (newValue) {
                                    // CheckBox seleccionado
                                    logger.INFO( "CheckBox seleccionado para: " + item);
                                    seton(GAME_DIR, file.getName());
                                } else {
                                    // CheckBox deseleccionado
                                    logger.INFO( "CheckBox deseleccionado para: " + item);
                                    setoff(GAME_DIR, file.getName());
                                }
                            }
                        });
                    }
                }
            });

        }

    }

    private void setoff(String GAME_DIR, String item) {
        File sourceFile = new File(GAME_DIR + "\\mods\\" + item.replace(".jer", ".jar"));
        File destFile = new File(GAME_DIR + "\\mods\\" + item.replace(".jar", ".jer").replace("  ", ""));

        try {
            FileUtils.moveFile(sourceFile, destFile);
            logger.INFO( "Archivo renombrado exitosamente.");
        } catch (IOException e) {
            logger.ERROR(e);
        }
    }

    private void seton(String GAME_DIR, String item) {
        File sourceFile = new File(GAME_DIR + "\\mods\\" + item.replace(".jar", ".jer"));
        File destFile = new File(GAME_DIR + "\\mods\\" + item.replace(".jer", ".jar").replace("  ", ""));

        try {
            FileUtils.moveFile(sourceFile, destFile);
            logger.INFO( "Archivo renombrado exitosamente.");
        } catch (IOException e) {
            System.err.println("ERROR al renombrar el archivo: " + e.getMessage());
        }
    }

    public static List<String> listarArchivosZip(String directorio) {
        List<String> archivosZip = new ArrayList<>();

        File carpeta = new File(directorio);

        // Verifica que el directorio exista y sea un directorio
        if (carpeta.exists() && carpeta.isDirectory()) {
            File[] archivos = carpeta.listFiles();

            if (archivos != null) {
                for (File archivo : archivos) {
                    // Verifica si el archivo tiene extensión .zip
                    if (archivo.isFile() && archivo.getName().toLowerCase().endsWith(".zip")) {
                        archivosZip.add(archivo.getName());
                    }
                }
            }
        }

        return archivosZip;
    }

    public void setPC(Text r, int set) {

        if (r.getText() == "" || r.getText() == "100%") {
            r.setText("0%");
        }
        Timeline timeline = new Timeline();
        try {
            timeline = new Timeline(
                    new KeyFrame(Duration.millis(40), event -> {
                        int t = Integer.parseInt(r.getText().replace("%", ""));
                        logger.INFO( String.valueOf(t));
                        t++;
                        r.setText(t + "%");

                    }));
        } catch (NumberFormatException e) {
            logger.ERROR( "stop");
            timeline.stop();
        }
        timeline.setCycleCount(set);
        timeline.play();

    }

    public void readP() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        File json = new File(MINECRAFT_PATH + "\\launcher_profiles.json");
        JsonNode rootNode = objectMapper.readTree(json);

        JsonNode pro = rootNode.get("profiles");

        Iterator<Map.Entry<String, JsonNode>> fieldsIterator = pro.fields();
        while (fieldsIterator.hasNext()) {
            Map.Entry<String, JsonNode> entry = fieldsIterator.next();
            String key = entry.getKey();
            JsonNode obj = pro.get(key);

            JsonNode created = obj.get("created");
            JsonNode GAME_DIR = obj.get("GAME_DIR");
            JsonNode icon = obj.get("icon");
            JsonNode javaDir = obj.get("javaDir");
            JsonNode lastUsed = obj.get("lastUsed");
            JsonNode lastVersionId = obj.get("lastVersionId");
            JsonNode name = obj.get("name");
            JsonNode type = obj.get("type");

            JsonNode resolution = obj.get("resolution");

            String createds = "";
            String GAME_DIRs = "";
            String icons = "";
            String lastUseds = "";
            String lastVersionss = "";
            String types = "";
            String names = "";
            int widhts = 0;
            int Heights = 0;
            String javadir = "";

            logger.INFO( "-----------------");

            logger.INFO( "profile: " + key);
            if (created != null) {
                createds = created.asText();
                logger.INFO( "created: " + createds);
            }
            if (GAME_DIR == null) {
                logger.INFO( "normal game dir");
                GAME_DIRs = MINECRAFT_PATH;
            } else {
                logger.INFO( "GAME_DIR: " + GAME_DIR.toString());
                GAME_DIRs = GAME_DIR.asText();
            }

            if (icon != null) {
                logger.INFO( "icon: " + icon.toString());
                icons = icon.asText();
            }
            if (lastUsed != null) {
                lastUseds = lastUsed.asText();
                logger.INFO( "lastUsed: " + lastUseds);
            }
            if (lastVersionId != null) {
                lastVersionss = lastVersionId.asText();
                logger.INFO( "lastVersion: " + lastVersionss);
            }
            if (name != null) {
                names = name.asText();
                logger.INFO( "name: " + names);
            }
            if (type != null) {
                logger.INFO( "type: " + type.toString());
                types = type.asText();
            }
            if (resolution != null) {
                JsonNode with = resolution.get("width");
                JsonNode height = resolution.get("height");
                logger.INFO( "width: " + with.asInt());
                logger.INFO( "height: " + height.asInt());
                widhts = with.asInt();
                Heights = height.asInt();
            }
            if (javaDir != null) {
                logger.INFO( "javaDir: " + javaDir.toString());
                javadir = javaDir.asText();
            }
            logger.INFO( "-----------------");
            addProfile(GAME_DIRs, types, icons, javadir, widhts, Heights, key);

        }
    }

    public void addProfile(String GAME_DIR, String type, String icon, String javadir, int resoW, int resoH,
            String key) {
        Button p = new Button();
        if (new File(icon).exists() != true) {
            icon = DISC + cons.USER_NAME
                    + "\\AppData\\Roaming\\.minecraft\\assets\\virtual\\legacy\\icons\\snapshot\\icon_32x32.png";
        }
        // Crea un ImageView con la imagen que deseas superponer
        logger.INFO(icon);
        ImageView imageView = new ImageView(new Image(icon)); // Reemplaza con la ruta de tu imagen
        imageView.setFitWidth(32); // Ajusta el ancho de la imagen según tus necesidades
        imageView.setFitHeight(32); // Ajusta el alto de la imagen según tus necesidades

        BackgroundFill g = new BackgroundFill(Color.TRANSPARENT, null, null);
        Background grt = new Background(g);

        AtomicBoolean on = new AtomicBoolean(false);

        p.setGraphic(imageView);
        p.setBackground(grt);
        p.setOnAction(e -> {
            if (on.get()) {
                logger.INFO( "off: " + key);
                on.set(false);
            } else {
                logger.INFO( "on: " + key);
                on.set(true);
            }
        });
        profiles.getChildren().add(p);
    }

}