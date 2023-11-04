package net.kender.logger.log5k.conf;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.Properties;


public class log5kConf {

    public Path __OUTPUT_S = Path.of("logs");
    public boolean __INFO_ONs__ = true;
    public boolean __DEBUG_ONs__ = true;
    public boolean __ERROR_ONs__ = true;
    public boolean __FATAL_ONs__ = true;
    public boolean __DATA_ONdf__ = true;
    public boolean __WARN_ONs__ = true;
    public boolean __DATA_ONs__ = true;
    public Path __OUTPUT_D = Path.of("logs\\data");
    public boolean __INFO_ONf__ = true;
    public boolean __WARN_ONf__ = true;
    public boolean __DEBUG_ONf__ = true;
    public boolean __ERROR_ONf__ = true;
    public boolean __FATAL_ONf__ = true;

    public String __COLOR_INFO__ = "\u001B[32;1m";
    public String __COLOR_WARN__ = "\u001B[33;1m";
    public String __COLOR_ERROR__ = "\u001B[31;1m";
    public String __COLOR_DEBUG__ = "\u001B[36;1m";
    public String __COLOR_FATAL__ = "\u001B[35;1m";
    public String __COLOR_DATA__ = "\u001b[34;1m";

    public String __ONE_FILE__ = null;
    
    public log5kConf(InputStream config){
        if(config != null){
            Properties properties = new Properties();
        
            try {
                properties.load(config);

                // Accede a propiedades específicas
                if(properties.containsKey("OUTPUT_S")){
                    __OUTPUT_S = Path.of(properties.getProperty("OUTPUT_S").replace("\"",""));
                }
                if(properties.containsKey("__INFO_ONs__")){
                    __INFO_ONs__ = Boolean.parseBoolean(properties.getProperty("__INFO_ONs__"));
                }
                if(properties.containsKey("__DEBUG_ONs__")){
                    __DEBUG_ONs__ = Boolean.parseBoolean(properties.getProperty("__DEBUG_ONs__"));
                }
                if(properties.containsKey("__ERROR_ONs__")){
                    __ERROR_ONs__ = Boolean.parseBoolean(properties.getProperty("__ERROR_ONs__"));
                }
                if(properties.containsKey("__FATAL_ONs__")){
                    __FATAL_ONs__ = Boolean.parseBoolean(properties.getProperty("__FATAL_ONs__"));
                }
                if(properties.containsKey("__WARN_ONs__")){
                    __WARN_ONs__ = Boolean.parseBoolean(properties.getProperty("__WARN_ONs__"));
                }
                if(properties.containsKey("__DATA_ONdf__")){
                    __DATA_ONdf__ = Boolean.parseBoolean(properties.getProperty("__DATA_ONdf__"));
                }
                if(properties.containsKey("__DATA_ONs__")){
                    __DATA_ONs__ = Boolean.parseBoolean(properties.getProperty("__DATA_ONs__"));
                }
                if(properties.containsKey("OUTPUT_D")){
                    __OUTPUT_D = Path.of(properties.getProperty("OUTPUT_D").replace("\"",""));
                }
                if(properties.containsKey("__ERROR_ONf__")){
                    __INFO_ONf__ = Boolean.parseBoolean(properties.getProperty("__INFO_ONf__"));
                }
                if(properties.containsKey("__DEBUG_ONf__")){
                    __DEBUG_ONf__ = Boolean.parseBoolean(properties.getProperty("__DEBUG_ONf__"));
                }
                if(properties.containsKey("__WARN_ONf__")){
                    __WARN_ONs__ = Boolean.parseBoolean(properties.getProperty("__WARN_ONs__"));
                }
                if(properties.containsKey("__INFO_ONf__")){
                    __INFO_ONs__ = Boolean.parseBoolean(properties.getProperty("__INFO_ONf__"));
                }
                if(properties.containsKey("__FATAL_ONs__")){
                    __FATAL_ONs__ = Boolean.parseBoolean(properties.getProperty("__FATAL_ONf__"));
                }



                if(properties.containsKey("__FATAL_COLOR__")){
                    __COLOR_FATAL__ = properties.getProperty("__FATAL_COLOR__");
                }
                if(properties.containsKey("__INFO_COLOR__")){
                    __COLOR_INFO__ = properties.getProperty("__FATAL_COLOR__");
                }
                if(properties.containsKey("__WARN_COLOR__")){
                    __COLOR_WARN__ = properties.getProperty("__WARN_COLOR__");
                }
                if(properties.containsKey("__DEBUG_COLOR__")){
                    __COLOR_DEBUG__ = properties.getProperty("__DEBUG_COLOR__");
                }
                if(properties.containsKey("__DATA_COLOR__")){
                    __COLOR_DATA__ = properties.getProperty("__DATA_COLOR__");
                }
                if(properties.containsKey("__ERROR_COLOR__")){
                    __COLOR_ERROR__ = properties.getProperty("__ERROR_COLOR__");
                }

                if(properties.containsKey("ONE_FILE")){
                    __ONE_FILE__ = properties.getProperty("ONE_FILE");
                }
                
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
