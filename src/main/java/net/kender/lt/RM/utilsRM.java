package net.kender.lt.RM;

import static net.kender.core.UtilsFiles.*;
import static net.kender.lt.cons.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
public class utilsRM {

    public static void setT(String texture){
        String path = LTP + "\\Textures\\" + texture + "\\Texture.TLT";
    }

    public static void getAssets(String url) throws IOException {
        File t = new File(LTP + "\\Textures\\minecraft");
        if(t.exists() != true){
            download(url,LTP + "\\Textures\\","minecraft",".zip");
            unZIP(LTP + "\\Textures\\minecraft.zip",LTP + "\\Textures\\");
            Files.delete(Path.of(LTP + "\\Textures\\minecraft.zip"));
        }

    }

}
