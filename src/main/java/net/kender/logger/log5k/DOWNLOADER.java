package net.kender.logger.log5k;

public class DOWNLOADER {
    private String c;
    private String d;
    public DOWNLOADER(String ColorAusl,String msg){
        c = ColorAusl;
        d = msg;
        System.out.print(ColorAusl + msg + "<\u001B[30;1m==========" + ColorAusl + ">");
    }
    public void set(int r,String proceson){
        System.out.print("\r" + c + d + "<\u001B[30;1m" + calc(r) + c + "> \u001B[31;1m" + proceson + "                                                            ");
    }
    private String calc(int r){
        int s = r;
        //System.out.println(s);
        String re = "";
        if(s >= 10){
            re += "\u001B[32;1m=";
        }else{
            re += "\u001B[0m=";
        }
        if(s >= 20){
            re += "\u001B[32;1m=";
        }else{
            re += "\u001B[0m=";
        }
        if(s >= 30){
            re += "\u001B[32;1m=";
        }else{
            re += "\u001B[0m=";
        }
        if(s >= 40){
            re += "\u001B[32;1m=";
        }else{
            re += "\u001B[0m=";
        }
        if(s >= 50){
            re += "\u001B[32;1m=";
        }else{
            re += "\u001B[0m=";
        }
        if(s >= 60){
            re += "\u001B[32;1m=";
        }else{
            re += "\u001B[0m=";
        }
        if(s >= 70){
            re += "\u001B[32;1m=";
        }else{
            re += "\u001B[0m=";
        }
        if(s >= 80){
            re += "\u001B[32;1m=";
        }else{
            re += "\u001B[0m=";
        }
        if(s >= 90){
            re += "\u001B[32;1m=";
        }else{
            re += "\u001B[0m=";
        }
        if(s >= 100){
            re += "\u001B[32;1m=";
        }else{
            re += "\u001B[0m=";
        }
        return re;
    }
}
