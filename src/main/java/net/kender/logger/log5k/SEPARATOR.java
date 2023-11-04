package net.kender.logger.log5k;
import static net.kender.logger.log5k.conf.data.data;

import java.io.File;
import java.io.IOException;

import net.kender.logger.log5k.EXTRAS.LOGS;

public class SEPARATOR {
    private File arhcr;
    private boolean Save;
    public SEPARATOR(boolean save,File arch){
        arhcr = arch;
        Save = save;
        System.out.println(Logger.config.__COLOR_DEBUG__ + "-------------");
        System.out.println(Logger.config.__COLOR_DEBUG__ + "[" + data + "]");
        if(Save){
            LOGS r = new LOGS(arch,arch);
            try {
                r.registerLog("-------------");
                r.registerLog("[" + data + "]");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void log(String key,Object msg){
        if(Save){
            LOGS r = new LOGS(arhcr,arhcr);
            try {
                r.registerLog("    " + key + ": " + msg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Logger.config.__COLOR_DEBUG__ + "    " + key + ": " + msg);
    }
    public void close(){
        if(Save){
            LOGS r = new LOGS(arhcr,arhcr);
            try {
                r.registerLog("-------------");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Logger.config.__COLOR_DEBUG__ + "-------------");
    }
}
