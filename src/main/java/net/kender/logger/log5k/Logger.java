package net.kender.logger.log5k;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.fusesource.jansi.AnsiConsole;

import net.kender.logger.log5k.EXTRAS.LOGS;
import net.kender.logger.log5k.conf.CustomLogger;
import net.kender.logger.log5k.conf.log5kConf;

public class Logger {
    public static log5kConf config = new log5kConf(null);
    private String name = "";
    private Class<?> r;
    public Logger(Class<?> c){
        r = c;
    }
    public void WARN(Object msg){
        AnsiConsole.systemInstall();
        Date date = new Date();
        SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm:ss"); 
        String hora = formatoHora.format(date);
        LOGS t = new LOGS(config.__OUTPUT_S,config.__OUTPUT_D);
        if(config.__ONE_FILE__ != null){
            t = new LOGS(new File(config.__OUTPUT_S + "\\" + config.__ONE_FILE__),new File(config.__OUTPUT_D + "\\" + config.__ONE_FILE__));
        }
        
        if(config.__WARN_ONs__){
            System.out.println(config.__COLOR_WARN__ + hora + " [" + name + "." + Thread.currentThread().getName() + "] !WARN!: " + msg + "\u001B[0m");
        }
        if(config.__WARN_ONf__){
            try {
                
                t.registerLog("[" + hora + "] (" + name + "." + Thread.currentThread().getName() + ") !WARN!: " + msg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        AnsiConsole.systemUninstall();
    }
    public void INFO(Object msg){
        Date date = new Date();
        SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm:ss"); 
        String hora = formatoHora.format(date);
        LOGS t = new LOGS(config.__OUTPUT_S,config.__OUTPUT_D);
        if(config.__ONE_FILE__ != null){
            t = new LOGS(new File(config.__OUTPUT_S + "\\" + config.__ONE_FILE__),new File(config.__OUTPUT_D + "\\" + config.__ONE_FILE__));
        }
        if(config.__INFO_ONs__){
            System.out.println(config.__COLOR_INFO__ + hora + " [" + name + "." + Thread.currentThread().getName() + "] INFO: " + msg + "\u001B[0m");
        }
        if(config.__INFO_ONf__){
            try {
                t.registerLog("[" + hora + "] (" + name + "." + Thread.currentThread().getName() + ") INFO: " + msg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void DEBUG(Object msg){
        Date date = new Date();
        SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm:ss"); 
        String hora = formatoHora.format(date);
        LOGS t = new LOGS(config.__OUTPUT_S,config.__OUTPUT_D);
        if(config.__ONE_FILE__ != null){
            t = new LOGS(new File(config.__OUTPUT_S + "\\" + config.__ONE_FILE__),new File(config.__OUTPUT_D + "\\" + config.__ONE_FILE__));
        }
        if(config.__DEBUG_ONs__){
            System.out.println(config.__COLOR_DEBUG__ + hora + " [" + name + "." + Thread.currentThread().getName() + "] DEBUG: " + msg + "\u001B[0m");
        }
        if(config.__DEBUG_ONf__){
            try {
                t.registerLog("[" + hora + "] (" + name + "." + Thread.currentThread().getName() + ") DEBUG: " + msg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void ERROR(Object msg){
        Date date = new Date();
        SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm:ss"); 
        String hora = formatoHora.format(date);
        LOGS t = new LOGS(config.__OUTPUT_S,config.__OUTPUT_D);
        if(config.__ONE_FILE__ != null){
            t = new LOGS(new File(config.__OUTPUT_S + "\\" + config.__ONE_FILE__),new File(config.__OUTPUT_D + "\\" + config.__ONE_FILE__));
        }
        if(config.__ERROR_ONs__){
            System.out.println(config.__COLOR_ERROR__ + hora + " [" + name + "." + Thread.currentThread().getName() + "] ERROR: " + msg + "\u001B[0m");
        }
        if(config.__ERROR_ONf__){
            try {
                t.registerLog("[" + hora + "] (" + name + "." + Thread.currentThread().getName() + ") ERROR: " + msg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void FATAL(Object msg){
        Date date = new Date();
        SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm:ss"); 
        String hora = formatoHora.format(date);
        LOGS t = new LOGS(config.__OUTPUT_S,config.__OUTPUT_D);
        if(config.__ONE_FILE__ != null){
            t = new LOGS(new File(config.__OUTPUT_S + "\\" + config.__ONE_FILE__),new File(config.__OUTPUT_D + "\\" + config.__ONE_FILE__));
        }
        if(config.__FATAL_ONs__){
            System.out.println(config.__COLOR_FATAL__ + hora + " [" + name + "." + Thread.currentThread().getName() + "] FATAL: " + msg + "\u001B[0m");
        }
        if(config.__FATAL_ONf__){
            try {
                t.registerLog("[" + hora + "] (" + name + "." + Thread.currentThread().getName() + ") FATAL: " + msg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void DATA(String key,Object valor){
        Date date = new Date();
        SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm:ss"); 
        String hora = formatoHora.format(date);
        LOGS t = new LOGS(config.__OUTPUT_S,config.__OUTPUT_D);
        if(config.__ONE_FILE__ != null){
            t = new LOGS(new File(config.__OUTPUT_S + "\\" + config.__ONE_FILE__),new File(config.__OUTPUT_D + "\\" + config.__ONE_FILE__));
        }
        if(config.__DATA_ONs__){
            System.out.println(config.__COLOR_DATA__ + hora + " [" + name + "." + Thread.currentThread().getName() + "] DATA: " + valor + "\u001B[0m");
        }
        if(config.__DATA_ONdf__){
            try {
                t.registerData(key, valor);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void DATA(String key,Object valor,String comment){
        Date date = new Date();
        SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm:ss"); 
        String hora = formatoHora.format(date);
        LOGS t = new LOGS(config.__OUTPUT_S,config.__OUTPUT_D);
        if(config.__ONE_FILE__ != null){
            t = new LOGS(new File(config.__OUTPUT_S + "\\" + config.__ONE_FILE__),new File(config.__OUTPUT_D + "\\" + config.__ONE_FILE__));
        }
        if(config.__DATA_ONs__){
            System.out.println(config.__COLOR_DATA__ + hora + " [" + name + "." + Thread.currentThread().getName() + "] DATA: " + valor + "\u001B[0m");
        }
        if(config.__DATA_ONdf__){
            try {
                t.registerData(key, valor,comment);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void log(Object msg,CustomLogger a){
        Date date = new Date();
        SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm:ss"); 
        String hora = formatoHora.format(date);
        LOGS t = new LOGS(config.__OUTPUT_S,config.__OUTPUT_D);
        if(config.__ONE_FILE__ != null){
            t = new LOGS(new File(config.__OUTPUT_S + "\\" + config.__ONE_FILE__),new File(config.__OUTPUT_D + "\\" + config.__ONE_FILE__));
        }
        String mens = "";
        System.out.println(a.ansicolor_);
        if(a.dateOn_){
            System.out.print(hora + " ");
            mens += "[" + hora + "] ";
        }

        if(a.classComplete_){
            System.out.print("[" + r.getName());
            mens += "(" + r.getName();
        }else{
            System.out.print("[" + r.getSimpleName());
            mens += "(" + r.getSimpleName();

        }
        if(a.threadOn_){
            Thread hiloActual = Thread.currentThread();
            System.out.print("." + hiloActual.getName() + "] ");
            mens += "." + hiloActual.getName() + ") " + a.TypeMsg_ + ": " + msg;
        }
        if(a.registerOnFile_){
            try {
                t.registerLog(mens);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(a.RegisterOnDates_){
            try {
                t.registerData("variable", msg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        System.out.print(a.TypeMsg_ + ": " + msg + "\u001B[0m\n");
    }

    public void setConfig(log5kConf c){
        config = c;
    }
}
