package net.kender.core.sample;

import static net.kender.core.sample.EXTRAS.EXTRAS.*;
import static net.kender.core.sample.EXTRAS.LIBS.getlibs;
import static net.kender.lt.cons.*;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import javafx.animation.TranslateTransition;
import javafx.scene.text.Text;
import net.kender.core.JsonUtils;
import net.kender.core.UtilsFiles;
import net.kender.core.assetsDowloader;
import net.kender.core.sample.EXTRAS.EXTRAS;
import net.kender.core.sample.Quikplays.Quikplay;
import net.kender.logger.log5k.Logger;
import net.kender.lt.controlers.Controler;

public class MC {
    private static final String SEPARATOR = "-------------------";
    private static Logger logger = new Logger(UtilsFiles.class);
    private Path __DEST__;
    private String __VERSION__;
    private Path __LIBS__;
    private String __USER__ = "testName";
    private Path __GAMEDIR__ = Path.of(MINECRAFT_PATH);
    private Path __JRE__ = Path
            .of("C:\\Users\\krist\\Documents\\.LT\\LT-2.0\\LT\\MC\\versions\\1.20.2\\jrejdk-20.0.1+9-jre\\bin\\java");
    private UUID __UUID__ = UUID.fromString("d0db8a3d-c392-4ae7-96e5-9365de33ab52");
    private String XUID = "0";
    private Path __ASSETSDIR__ = Path.of(MINECRAFT_PATH + "\\assets\\");
    private String __AccesToken__ = "0";
    private String __CLIENTID__ = "";
    private String __USERTYPE__ = "";
    private String __VERSIONTYPE__ = "";

    private int MaxRam = 4;
    private int minRam = 2;

    public MC(Path __DEST__, String __VERSION__) {
        this.__DEST__ = __DEST__;
        this.__VERSION__ = __VERSION__;
    }

    public void Run(Runnable Counter, Runnable Finale,TranslateTransition e,Text ae) {
        Thread run = new Thread(new Runnable() {
            public void run() {
                logger.INFO( SEPARATOR);
                logger.INFO( __DEST__);
                logger.INFO( SEPARATOR);
                if (Controler.cancel != true) {
                    __LIBS__ = Path.of(__DEST__ + "\\versions\\" + __VERSION__ + "\\libraries\\");
                    createJsonVersion(__DEST__, __VERSION__);
                    Counter.run();
                    if (Controler.cancel != true) {
                        JsonOfVersion a = new JsonOfVersion(
                                new File(__DEST__.toString() + "\\versions\\" + __VERSION__ + "\\" + __VERSION__
                                        + ".json"));
                        if (a.inheritsFrom != null) {
                            if (new File(__DEST__ + "\\versions\\" + a.inheritsFrom + "\\" + a.inheritsFrom + ".json")
                                    .exists()) {
                                logger.INFO(
                                        "archive: " + __DEST__ + "\\versions\\" + a.inheritsFrom + "\\" + a.inheritsFrom
                                                + ".json"
                                                + "exist");
                                Counter.run();
                                if (Controler.cancel == true) {
                                    Finale.run();
                                }
                            } else {
                                try {
                                    if (Controler.cancel != true) {
                                        String c = JsonUtils.getUrlOfJsonOfVersion(
                                                "https://launchermeta.mojang.com/mc/game/version_manifest.json",
                                                a.inheritsFrom);
                                        ObjectMapper e = new ObjectMapper();
                                        JsonNode f = e.readTree(c);
                                        JsonNode t = f.get("downloads");
                                        JsonNode y = t.get("client");
                                        JsonNode u = y.get("url");
                                        URI URL = new URI(u.asText());
                                        download(URL,
                                                new File(__DEST__ + "\\versions\\" + __VERSION__ + "\\" + __VERSION__
                                                        + ".jar"));
                                        Counter.run();
                                    } else {
                                        Finale.run();
                                    }
                                } catch (JsonProcessingException | URISyntaxException e) {
                                    logger.ERROR(e);
                                }
                            }
                            try {
                                if (Controler.cancel != true) {
                                    String c = JsonUtils.getUrlOfJsonOfVersion(
                                            "https://launchermeta.mojang.com/mc/game/version_manifest.json",
                                            a.inheritsFrom);
                                    ObjectMapper e = new ObjectMapper();
                                    JsonNode f = e.readTree(c);
                                    getlibs(__LIBS__, f.get("libraries"));
                                    getlibs(__LIBS__, a.libraries);
                                    Counter.run();
                                } else {
                                    Finale.run();
                                }
                            } catch (JsonProcessingException e) {
                                logger.ERROR(e);
                            }
                        } else if (a.downloads != null) {
                            if (Controler.cancel != true) {
                                if (new File(
                                        __DEST__ + "\\versions\\" + a.lastVersionId + "\\" + a.lastVersionId + ".jar")
                                        .exists()) {
                                    logger.INFO( "the client has downloaded");
                                    Counter.run();
                                } else {
                                    try {
                                        if (Controler.cancel != true) {
                                            JsonNode y = a.downloads.get("client");
                                            JsonNode u = y.get("url");
                                            URI URL = new URI(u.asText());
                                            download(URL,
                                                    new File(__DEST__ + "\\versions\\" + a.lastVersionId + "\\",
                                                            a.lastVersionId + ".jar"));
                                            Counter.run();
                                        }
                                    } catch (URISyntaxException e) {
                                        logger.ERROR(e);
                                    }
                                }
                                if (Controler.cancel != true) {
                                    getlibs(__LIBS__, a.libraries);
                                    Counter.run();

                                    if (Controler.cancel != true) {
                                        getJRE(a.javaVersion);
                                        Counter.run();

                                        try {
                                            assetsDowloader.AssetsDowload(__DEST__ + "\\versions\\" + __VERSION__ + "\\" + __VERSION__ + ".json",MINECRAFT_PATH + "\\eassets\\" , MINECRAFT_PATH + "\\Eobjects\\");
                                        } catch (IOException e1) {
                                            e1.printStackTrace();
                                        }

                                        if (Controler.cancel != true) {
                                            CommandConstructor t = new CommandConstructor(
                                                    __USER__,
                                                    MaxRam,
                                                    minRam,
                                                    a.assetsIndex.get("id").asText(),
                                                    __LIBS__.toString() + "\\*",
                                                    a.mainClass, a.lastVersionId,
                                                    __GAMEDIR__.toString(),
                                                    __JRE__.toString(),
                                                    __DEST__,
                                                    __UUID__,
                                                    __AccesToken__,
                                                    __ASSETSDIR__,
                                                    XUID,
                                                    __CLIENTID__,
                                                    __USERTYPE__,
                                                    __VERSIONTYPE__);
                                            Counter.run();

                                            e.play();
                                            try {
                                                Thread.sleep(3000);
                                            } catch (InterruptedException e1) {
                                                e1.printStackTrace();
                                            }
                                            ae.setVisible(false);
                                            ae.setDisable(true);
                                            EXTRAS.run(t, null);
                                        } else {
                                            Finale.run();
                                        }
                                    } else {
                                        Finale.run();
                                    }
                                } else {
                                    Finale.run();
                                }
                            } else {
                                Finale.run();
                            }
                        } else {
                            Finale.run();
                        }
                    } else {
                        Finale.run();
                    }
                }
            }
        });
        run.start();

    }

    public void Run(Runnable Counter, Runnable Finale,TranslateTransition e,Text ae,Quikplay r) {
        Thread run = new Thread(new Runnable() {
            public void run() {
                logger.INFO( SEPARATOR);
                logger.INFO( __DEST__);
                logger.INFO( SEPARATOR);
                if (Controler.cancel != true) {
                    __LIBS__ = Path.of(__DEST__ + "\\versions\\" + __VERSION__ + "\\libraries\\");
                    createJsonVersion(__DEST__, __VERSION__);
                    Counter.run();
                    if (Controler.cancel != true) {
                        JsonOfVersion a = new JsonOfVersion(
                                new File(__DEST__.toString() + "\\versions\\" + __VERSION__ + "\\" + __VERSION__
                                        + ".json"));
                        if (a.inheritsFrom != null) {
                            if (new File(__DEST__ + "\\versions\\" + a.inheritsFrom + "\\" + a.inheritsFrom + ".json")
                                    .exists()) {
                                logger.INFO(
                                        "archive: " + __DEST__ + "\\versions\\" + a.inheritsFrom + "\\" + a.inheritsFrom
                                                + ".json"
                                                + "exist");
                                Counter.run();
                                if (Controler.cancel == true) {
                                    Finale.run();
                                }
                            } else {
                                try {
                                    if (Controler.cancel != true) {
                                        String c = JsonUtils.getUrlOfJsonOfVersion(
                                                "https://launchermeta.mojang.com/mc/game/version_manifest.json",
                                                a.inheritsFrom);
                                        ObjectMapper e = new ObjectMapper();
                                        JsonNode f = e.readTree(c);
                                        JsonNode t = f.get("downloads");
                                        JsonNode y = t.get("client");
                                        JsonNode u = y.get("url");
                                        URI URL = new URI(u.asText());
                                        download(URL,
                                                new File(__DEST__ + "\\versions\\" + __VERSION__ + "\\" + __VERSION__
                                                        + ".jar"));
                                        Counter.run();
                                    } else {
                                        Finale.run();
                                    }
                                } catch (JsonProcessingException | URISyntaxException e) {
                                    logger.ERROR(e);
                                }
                            }
                            try {
                                if (Controler.cancel != true) {
                                    String c = JsonUtils.getUrlOfJsonOfVersion(
                                            "https://launchermeta.mojang.com/mc/game/version_manifest.json",
                                            a.inheritsFrom);
                                    ObjectMapper e = new ObjectMapper();
                                    JsonNode f = e.readTree(c);
                                    getlibs(__LIBS__, f.get("libraries"));
                                    getlibs(__LIBS__, a.libraries);
                                    Counter.run();
                                } else {
                                    Finale.run();
                                }
                            } catch (JsonProcessingException e) {
                                logger.ERROR(e);
                            }
                        } else if (a.downloads != null) {
                            if (Controler.cancel != true) {
                                if (new File(
                                        __DEST__ + "\\versions\\" + a.lastVersionId + "\\" + a.lastVersionId + ".jar")
                                        .exists()) {
                                    logger.INFO( "the client has downloaded");
                                    Counter.run();
                                } else {
                                    try {
                                        if (Controler.cancel != true) {
                                            JsonNode y = a.downloads.get("client");
                                            JsonNode u = y.get("url");
                                            URI URL = new URI(u.asText());
                                            download(URL,
                                                    new File(__DEST__ + "\\versions\\" + a.lastVersionId + "\\",
                                                            a.lastVersionId + ".jar"));
                                            Counter.run();
                                        }
                                    } catch (URISyntaxException e) {
                                        logger.ERROR(e);
                                    }
                                }
                                if (Controler.cancel != true) {
                                    getlibs(__LIBS__, a.libraries);
                                    Counter.run();

                                    if (Controler.cancel != true) {
                                        getJRE(a.javaVersion);
                                        Counter.run();

                                        if (Controler.cancel != true) {

                                            try {
                                                assetsDowloader.AssetsDowload(__DEST__ + "\\versions\\" + __VERSION__ + "\\" + __VERSION__ + ".json",MINECRAFT_PATH + "\\eassets\\" , MINECRAFT_PATH + "\\Eobjects\\");
                                            } catch (IOException e1) {
                                                e1.printStackTrace();
                                            }

                                            CommandConstructor t = new CommandConstructor(
                                                    __USER__,
                                                    MaxRam,
                                                    minRam,
                                                    a.assetsIndex.get("id").asText(),
                                                    __LIBS__.toString() + "\\*",
                                                    a.mainClass, a.lastVersionId,
                                                    __GAMEDIR__.toString(),
                                                    __JRE__.toString(),
                                                    __DEST__,
                                                    __UUID__,
                                                    __AccesToken__,
                                                    __ASSETSDIR__,
                                                    XUID,
                                                    __CLIENTID__,
                                                    __USERTYPE__,
                                                    __VERSIONTYPE__,
                                                    r);
                                            Counter.run();

                                            e.play();
                                            try {
                                                Thread.sleep(3000);
                                            } catch (InterruptedException e1) {
                                                e1.printStackTrace();
                                            }
                                            ae.setVisible(false);
                                            ae.setDisable(true);
                                            EXTRAS.run(t, null);
                                        } else {
                                            Finale.run();
                                        }
                                    } else {
                                        Finale.run();
                                    }
                                } else {
                                    Finale.run();
                                }
                            } else {
                                Finale.run();
                            }
                        } else {
                            Finale.run();
                        }
                    } else {
                        Finale.run();
                    }
                }
            }
        });
        run.start();

    }

    public void Run() {
        Thread run = new Thread(new Runnable() {
            public void run() {
                logger.INFO( SEPARATOR);
                logger.INFO( __DEST__);
                logger.INFO( SEPARATOR);
                if (Controler.cancel != true) {
                    __LIBS__ = Path.of(__DEST__ + "\\versions\\" + __VERSION__ + "\\libraries\\");
                    createJsonVersion(__DEST__, __VERSION__);
                    if (Controler.cancel != true) {
                        JsonOfVersion a = new JsonOfVersion(
                                new File(__DEST__.toString() + "\\versions\\" + __VERSION__ + "\\" + __VERSION__
                                        + ".json"));
                        if (a.inheritsFrom != null) {
                            if (new File(__DEST__ + "\\versions\\" + a.inheritsFrom + "\\" + a.inheritsFrom + ".json")
                                    .exists()) {
                                logger.INFO(
                                        "archive: " + __DEST__ + "\\versions\\" + a.inheritsFrom + "\\" + a.inheritsFrom
                                                + ".json"
                                                + "exist");
                                if (Controler.cancel == true) {
                                }
                            } else {
                                try {
                                    if (Controler.cancel != true) {
                                        String c = JsonUtils.getUrlOfJsonOfVersion(
                                                "https://launchermeta.mojang.com/mc/game/version_manifest.json",
                                                a.inheritsFrom);
                                        ObjectMapper e = new ObjectMapper();
                                        JsonNode f = e.readTree(c);
                                        JsonNode t = f.get("downloads");
                                        JsonNode y = t.get("client");
                                        JsonNode u = y.get("url");
                                        URI URL = new URI(u.asText());
                                        download(URL,
                                                new File(__DEST__ + "\\versions\\" + __VERSION__ + "\\" + __VERSION__
                                                        + ".jar"));
                                       
                                    } else {
                                      
                                    }
                                } catch (JsonProcessingException | URISyntaxException e) {
                                    logger.ERROR(e);
                                }
                            }
                            try {
                                if (Controler.cancel != true) {
                                    String c = JsonUtils.getUrlOfJsonOfVersion(
                                            "https://launchermeta.mojang.com/mc/game/version_manifest.json",
                                            a.inheritsFrom);
                                    ObjectMapper e = new ObjectMapper();
                                    JsonNode f = e.readTree(c);
                                    getlibs(__LIBS__, f.get("libraries"));
                                    getlibs(__LIBS__, a.libraries);
                                    
                                } else {
                                   
                                }
                            } catch (JsonProcessingException e) {
                                logger.ERROR(e);
                            }
                        } else if (a.downloads != null) {
                            if (Controler.cancel != true) {
                                if (new File(
                                        __DEST__ + "\\versions\\" + a.lastVersionId + "\\" + a.lastVersionId + ".jar")
                                        .exists()) {
                                    logger.INFO( "the client has downloaded");
                                    
                                } else {
                                    try {
                                        if (Controler.cancel != true) {
                                            JsonNode y = a.downloads.get("client");
                                            JsonNode u = y.get("url");
                                            URI URL = new URI(u.asText());
                                            download(URL,
                                                    new File(__DEST__ + "\\versions\\" + a.lastVersionId + "\\",
                                                            a.lastVersionId + ".jar"));
                                           
                                        }
                                    } catch (URISyntaxException e) {
                                        logger.ERROR(e);
                                    }
                                }
                                if (Controler.cancel != true) {
                                    getlibs(__LIBS__, a.libraries);
                                    

                                    if (Controler.cancel != true) {
                                        getJRE(a.javaVersion);
                                       


                                        if (Controler.cancel != true) {
                                            Assets(__DEST__, __VERSION__,Integer.parseInt(a.assets));

                                            CommandConstructor t = new CommandConstructor(
                                                    __USER__,
                                                    MaxRam,
                                                    minRam,
                                                    a.assetsIndex.get("id").asText(),
                                                    __LIBS__.toString() + "\\*",
                                                    a.mainClass, a.lastVersionId,
                                                    __GAMEDIR__.toString(),
                                                    __JRE__.toString(),
                                                    __DEST__,
                                                    __UUID__,
                                                    __AccesToken__,
                                                    __ASSETSDIR__,
                                                    XUID,
                                                    __CLIENTID__,
                                                    __USERTYPE__,
                                                    __VERSIONTYPE__);
                                           

                                           
                                            try {
                                                Thread.sleep(3000);
                                            } catch (InterruptedException e1) {
                                                e1.printStackTrace();
                                            }
                                           
                                            EXTRAS.run(t, null);
                                        } else {
                                           
                                        }
                                    } else {

                                    }
                                } else {
                                   
                                }
                            } else {
                                
                            }
                        } else {
                           
                        }
                    } else {
                       
                    }
                }
            }
        });
        run.start();

    }

    

    public void Run(Quikplay e) {
        Thread run = new Thread(new Runnable() {
            public void run() {
                logger.INFO( SEPARATOR);
                logger.INFO( __DEST__);
                logger.INFO( SEPARATOR);
                if (Controler.cancel != true) {
                    __LIBS__ = Path.of(__DEST__ + "\\versions\\" + __VERSION__ + "\\libraries\\");
                    createJsonVersion(__DEST__, __VERSION__);
                    if (Controler.cancel != true) {
                        JsonOfVersion a = new JsonOfVersion(
                                new File(__DEST__.toString() + "\\versions\\" + __VERSION__ + "\\" + __VERSION__
                                        + ".json"));
                        if (a.inheritsFrom != null) {
                            if (new File(__DEST__ + "\\versions\\" + a.inheritsFrom + "\\" + a.inheritsFrom + ".json")
                                    .exists()) {
                                logger.INFO(
                                        "archive: " + __DEST__ + "\\versions\\" + a.inheritsFrom + "\\" + a.inheritsFrom
                                                + ".json"
                                                + "exist");
                                if (Controler.cancel == true) {
                                }
                            } else {
                                try {
                                    if (Controler.cancel != true) {
                                        String c = JsonUtils.getUrlOfJsonOfVersion(
                                                "https://launchermeta.mojang.com/mc/game/version_manifest.json",
                                                a.inheritsFrom);
                                        ObjectMapper e = new ObjectMapper();
                                        JsonNode f = e.readTree(c);
                                        JsonNode t = f.get("downloads");
                                        JsonNode y = t.get("client");
                                        JsonNode u = y.get("url");
                                        URI URL = new URI(u.asText());
                                        download(URL,
                                                new File(__DEST__ + "\\versions\\" + __VERSION__ + "\\" + __VERSION__
                                                        + ".jar"));
                                       
                                    } else {
                                      
                                    }
                                } catch (JsonProcessingException | URISyntaxException e) {
                                    logger.ERROR(e);
                                }
                            }
                            try {
                                if (Controler.cancel != true) {
                                    String c = JsonUtils.getUrlOfJsonOfVersion(
                                            "https://launchermeta.mojang.com/mc/game/version_manifest.json",
                                            a.inheritsFrom);
                                    ObjectMapper e = new ObjectMapper();
                                    JsonNode f = e.readTree(c);
                                    getlibs(__LIBS__, f.get("libraries"));
                                    getlibs(__LIBS__, a.libraries);
                                    
                                } else {
                                   
                                }
                            } catch (JsonProcessingException e) {
                                logger.ERROR(e);
                            }
                        } else if (a.downloads != null) {
                            if (Controler.cancel != true) {
                                if (new File(
                                        __DEST__ + "\\versions\\" + a.lastVersionId + "\\" + a.lastVersionId + ".jar")
                                        .exists()) {
                                    logger.INFO( "the client has downloaded");
                                    
                                } else {
                                    try {
                                        if (Controler.cancel != true) {
                                            JsonNode y = a.downloads.get("client");
                                            JsonNode u = y.get("url");
                                            URI URL = new URI(u.asText());
                                            download(URL,
                                                    new File(__DEST__ + "\\versions\\" + a.lastVersionId + "\\",
                                                            a.lastVersionId + ".jar"));
                                           
                                        }
                                    } catch (URISyntaxException e) {
                                        logger.ERROR(e);
                                    }
                                }
                                if (Controler.cancel != true) {
                                    getlibs(__LIBS__, a.libraries);
                                    

                                    if (Controler.cancel != true) {
                                        getJRE(a.javaVersion);
                                       

                                        if (Controler.cancel != true) {
                                            
                                            try {
                                                assetsDowloader.AssetsDowload(__DEST__ + "\\versions\\" + __VERSION__ + "\\" + __VERSION__ + ".json",MINECRAFT_PATH + "\\eassets\\" , MINECRAFT_PATH + "\\Eobjects\\");
                                            } catch (IOException e1) {
                                                e1.printStackTrace();
                                            }

                                            CommandConstructor t = new CommandConstructor(
                                                    __USER__,
                                                    MaxRam,
                                                    minRam,
                                                    a.assetsIndex.get("id").asText(),
                                                    __LIBS__.toString() + "\\*",
                                                    a.mainClass, a.lastVersionId,
                                                    __GAMEDIR__.toString(),
                                                    __JRE__.toString(),
                                                    __DEST__,
                                                    __UUID__,
                                                    __AccesToken__,
                                                    __ASSETSDIR__,
                                                    XUID,
                                                    __CLIENTID__,
                                                    __USERTYPE__,
                                                    __VERSIONTYPE__,
                                                    e);
                                           

                                           
                                            try {
                                                Thread.sleep(3000);
                                            } catch (InterruptedException e1) {
                                                e1.printStackTrace();
                                            }
                                           
                                            EXTRAS.run(t, null);
                                        } else {
                                           
                                        }
                                    } else {

                                    }
                                } else {
                                   
                                }
                            } else {
                                
                            }
                        } else {
                           
                        }
                    } else {
                       
                    }
                }
            }
        });
        run.start();

    }

    public void setRam(int maxRam, int MinRam) {
        MaxRam = maxRam;
        minRam = MinRam;
    }

    public void setDEST(Path e) {
        __DEST__ = e;
    }

    public void setUser(String user) {
        __USER__ = user;
    }

    public void setGameDir(Path GameDir) {
        __GAMEDIR__ = GameDir;
    }

    public void setJavaJRE(Path bin) {
        __JRE__ = bin;
    }

    public void setUUID(UUID e) {
        __UUID__ = e;
    }

    public void setXUID(String XUID) {
        this.XUID = XUID;
    }

    public void setAssetsDir(Path ASSETSDIR) {
        __ASSETSDIR__ = ASSETSDIR;
    }

    public void setAccesToken(String AccesToken) {
        __AccesToken__ = AccesToken;
    }

    public void setClientId(String ClientId) {
        __CLIENTID__ = ClientId;
    }

    public void setUserType(String UserType) {
        __USERTYPE__ = UserType;
    }

    public void setVersionType(String versionType) {
        __VERSIONTYPE__ = versionType;
    }

    private void getJRE(JsonNode javaVersion) {
        try {
            int versionJava = 0;
            JsonNode JVersionNode = null;
            if (javaVersion == null) {
                versionJava = 0;
            } else {
                JVersionNode = javaVersion.get("majorVersion");
                versionJava = JVersionNode.asInt();
            }
            if (versionJava <= 8) {
                download(new URI(
                        "https://github.com/adoptium/temurin8-binaries/releases/download/jdk8u382-b05/OpenJDK8U-jre_x64_windows_hotspot_8u382b05.zip"),
                        new File(__DEST__ + "\\versions\\" + __VERSION__ + "\\jre.zip"));
                unZIP(new File(__DEST__ + "\\versions\\" + __VERSION__ + "\\jre.zip"),
                        Path.of(__DEST__ + "\\versions\\" + __VERSION__ + "\\jre\\"));
                // int JavaV = 8;
                // UtilsFiles.getnatives(__DEST__ + "\\versions\\" + __VERSION__ +
                // "\\libraries\\", DirectoyOfBin + "\\" + JreUUID8.toString(), ".zip");
            } else {
                download(new URI(
                        "https://github.com/adoptium/temurin20-binaries/releases/download/jdk-20.0.1%2B9/OpenJDK20U-jre_x64_windows_hotspot_20.0.1_9.zip"),
                        new File(__DEST__ + "\\versions\\" + __VERSION__ + "\\jre.zip"));
                unZIP(new File(__DEST__ + "\\versions\\" + __VERSION__ + "\\jre.zip"),
                        Path.of(__DEST__ + "\\versions\\" + __VERSION__ + "\\jre\\"));
                logger.INFO( "is java 20");
                // int JavaV = 20;
            }
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }
    public static List<String> getListOfVersions(URL r){
        ObjectMapper objectMapper = new ObjectMapper();


        try {
            JsonNode rootNode = objectMapper.readTree(r);
            JsonNode versionsNode = rootNode.get("versions");
            if (versionsNode != null && versionsNode.isArray()) {
                List<String> versionIdsList = new ArrayList<>();
                for (JsonNode versionNode : versionsNode) {
                    JsonNode idNode = versionNode.get("id");
                    if (idNode != null) {
                        versionIdsList.add(idNode.asText());
                    }else{
                        logger.WARN("error list return null");
                        return null;
                    }
                }
                return versionIdsList;
            }else{
                logger.WARN("error list return null");
                return null;
            }
        } catch (IOException e) {
            logger.ERROR("error on get list of versions");
            logger.ERROR(e);
            return null;
        }

    }
    private void Assets(Path dest,String version,int index){
        String url = JsonUtils.readAssetsIndexUrlFromJson(dest + "\\versions\\" + version  + "\\" + version + ".json");
        logger.DATA("url",url);
        String cont = JsonUtils.getJsonVersion(url);
        EXTRAS.createFile(new File(dest + "\\assets\\indexes\\" + index + ".json"), cont);
        try {
            assetsDowloader.AssetsDowload(dest + "\\assets\\indexes\\" + index + ".json", dest + "\\assets\\virtual\\legacy\\",  dest + "\\assets\\object\\");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
