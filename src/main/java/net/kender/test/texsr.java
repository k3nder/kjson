package net.kender.test;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Path;

import net.kender.core.sample.MC;
import net.kender.logger.log5k.DOWNLOADER;
import net.kender.logger.log5k.Logger;
import net.kender.logger.log5k.conf.CustomLogger;
import net.kender.logger.log5k.conf.log5kConf;
import net.kender.lt.Main;

public class texsr {
    // @param log
    private static Logger log = new Logger(texsr.class);
    public static void main(String[] args) throws InterruptedException {
        //log.setConfig(new log5kConf(Main.class.getResourceAsStream("/net/kender/lt/config/logger.properties")));
    //
        //log.WARN("WARN");
        //try {
        //    Thread.sleep(3000);
        //} catch (InterruptedException e) {
        //    e.printStackTrace();
        //}
        //log.INFO("INFO");
        //try {
        //    Thread.sleep(3000);
        //} catch (InterruptedException e) {
        //    e.printStackTrace();
        //}
        //log.ERROR("ERROR");
        //try {
        //    Thread.sleep(3000);
        //} catch (InterruptedException e) {
        //    e.printStackTrace();
        //}
        //log.FATAL("FATAL");
        //try {
        //    Thread.sleep(3000);
        //} catch (InterruptedException e) {
        //    e.printStackTrace();
        //}
        //log.DEBUG("DEBUG");
        //try {
        //    Thread.sleep(3000);
        //} catch (InterruptedException e) {
        //    e.printStackTrace();
        //}
        //log.DATA("DATA", "valor");
        //
        // Guarda la salida estándar actual

        DOWNLOADER r = new DOWNLOADER("\u001B[33;1m", "hola");

        Thread.sleep(2000);

        r.set(10, "proces10");

        Thread.sleep(2000);

        r.set(20, "proces20");

        Thread.sleep(2000);

        r.set(30, "proces30");

        Thread.sleep(2000);

        r.set(40, "proces40");

        Thread.sleep(2000);

        r.set(50, "proces50");

        Thread.sleep(2000);

        r.set(60, "proces60");

        Thread.sleep(2000);

        r.set(70, "proces70");

        Thread.sleep(2000);

        r.set(80, "proces80");

        Thread.sleep(2000);

        r.set(90, "proces90");

        Thread.sleep(2000);

        r.set(100, "proces100");

        Thread.sleep(2000);
        
        // TODO hola
        // * hola
        // ? hola
        // ! hola
        
    }
}
