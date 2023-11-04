package net.kender.test;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;

import net.kender.Kjson.Json;
import net.kender.MCutils.players.Server.Server;
import net.kender.MCutils.players.Server.ServerIp;
import net.kender.core.sample.MC;
import java.nio.file.Path;

import net.kender.logger.log5k.Logger;
import net.kender.logger.log5k.conf.log5kConf;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javafx.scene.image.Image;
import static net.kender.logger.log5k.Logger.config;

import java.net.Socket;

public class Tester {
    public static void main(String[] args) throws IOException{
        log5kConf logConfig = new log5kConf(Tester.class.getResourceAsStream("/net/kender/lt/config/logger.properties"));
        config = logConfig;

       MC e = new MC(Path.of("C:\\Users\\krist\\Desktop\\çwer"),"1.20.2");
       e.Run();
    }

}