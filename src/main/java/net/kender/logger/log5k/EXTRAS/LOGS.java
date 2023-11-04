package net.kender.logger.log5k.EXTRAS;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import net.kender.logger.log5k.conf.data;

import java.text.SimpleDateFormat;
import java.nio.file.Path;

import java.io.BufferedWriter;

public class LOGS {
    private static Path DestL;
    private static Path DestD;

    private static File DestLf = null;
    private static File DestDf = null;
    public LOGS(Path destLOGS,Path destData){
        DestL = destLOGS;
        DestD = destData;
    }

    public LOGS(File destLOGS,File destData){
        DestLf = destLOGS;
        DestDf = destData;
    }


    public void registerLog(Object line) throws IOException{
        SimpleDateFormat formato = new SimpleDateFormat("MM:dd HH:mm:ss");
        String fechaFormateada = formato.format(data.data);
        File g = new File(DestL + "\\" + fechaFormateada.replace(" ", "-").replaceAll(":","-") + ".Log");
        if(DestLf != null){
            g = DestLf;
        }
       
        if(g.exists() == false){
            FileWriter fileWriter = new FileWriter(g);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            // Escribir contenido en el archivo
            bufferedWriter.write(line.toString());
            //bufferedWriter.newLine(); // Agregar nueva línea
            // Cerrar el archivo
            bufferedWriter.close();
        }else{
            FileWriter fileWriterAppend = new FileWriter(g, true);
            BufferedWriter bufferedWriterAppend = new BufferedWriter(fileWriterAppend);

            // Añadir más contenido al archivo
            bufferedWriterAppend.newLine(); // Agregar nueva línea
            bufferedWriterAppend.write(line.toString());
            bufferedWriterAppend.close();
        }
        
    }
    public void registerData(String key,Object Data) throws IOException{
        SimpleDateFormat formato = new SimpleDateFormat("MM:dd HH:mm:ss");
        String fechaFormateada = formato.format(data.data);

        File g = new File(DestD + "\\" + fechaFormateada.replace(" ", "-").replaceAll(":","-") + ".Log");
        if(DestDf != null){
            g = DestDf;
        }
        if(g.exists() == false){
            FileWriter fileWriter = new FileWriter(g);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            // Escribir contenido en el archivo
            bufferedWriter.write("[" + fechaFormateada + "] " + key + ": " + Data.toString());
            //bufferedWriter.newLine(); // Agregar nueva línea
            // Cerrar el archivo
            bufferedWriter.close();
        }else{
            FileWriter fileWriterAppend = new FileWriter(g, true);
            BufferedWriter bufferedWriterAppend = new BufferedWriter(fileWriterAppend);

            // Añadir más contenido al archivo
            bufferedWriterAppend.newLine(); // Agregar nueva línea
            bufferedWriterAppend.write("[" + fechaFormateada + "] " + key + ": " + Data.toString());
            bufferedWriterAppend.close();
        }
    }

    public void registerData(String key,Object Data,String comment) throws IOException{
        SimpleDateFormat formato = new SimpleDateFormat("MM:dd HH:mm:ss");
        String fechaFormateada = formato.format(data.data);

        File g = new File(DestD + "\\" + fechaFormateada.replace(" ", "-").replaceAll(":","-") + ".Log");

        if(DestDf != null){
            g = DestDf;
        }
        if(g.exists() == false){
            FileWriter fileWriter = new FileWriter(g);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            // Escribir contenido en el archivo
            bufferedWriter.write("[" + fechaFormateada + "] " + key + ": " + Data.toString() + "//" + comment);
            //bufferedWriter.newLine(); // Agregar nueva línea
            // Cerrar el archivo
            bufferedWriter.close();
        }else{
            FileWriter fileWriterAppend = new FileWriter(g, true);
            BufferedWriter bufferedWriterAppend = new BufferedWriter(fileWriterAppend);

            // Añadir más contenido al archivo
            bufferedWriterAppend.newLine(); // Agregar nueva línea
            bufferedWriterAppend.write("[" + fechaFormateada + "] " + key + ": " + Data.toString() + "//" + comment);
            bufferedWriterAppend.close();
        }
    }
}
