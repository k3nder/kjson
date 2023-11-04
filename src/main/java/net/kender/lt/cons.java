package net.kender.lt;



public class cons {

    private cons(){

    }
    
    public static final String OS = System.getProperty("os.name");
    public static final String USER_NAME = System.getProperty("user.name");
    public static final String DISC = "C:\\Users\\";
    public static final String GAME_DIR = DISC + USER_NAME + "\\AppData\\Roaming\\.minecraft";

    public static final String MINECRAFT_PATH = DISC + USER_NAME + "\\AppData\\Roaming\\.minecraft";
    public static final String LOCAL_PATH = DISC + USER_NAME + "\\DOCUMENTS\\LauncherTrinity";

    public static final String DOCUMENTS = DISC + USER_NAME + "\\DOCUMENTS\\";

    public static final String JRE8W = DISC + USER_NAME + "\\AppData\\Local\\Temp\\JREs_LauncherTrinity";
    public static final String JRE20W = DISC + USER_NAME + "\\AppData\\Local\\Temp\\JREs_LauncherTrinity";

    public static final String LTP = DISC + USER_NAME + "\\AppData\\Roaming\\kender\\LT";
    
}
