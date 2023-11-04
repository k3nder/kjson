package net.kender.core.sample;

import java.nio.file.Path;
import java.util.UUID;

import net.kender.core.sample.Quikplays.Quikplay;

public class CommandConstructor {
    public String command;

    public CommandConstructor(String user, int maxRam, int minRam, String assetsIndex, String libs, String mainClass,
            String LastVersionId, String gameDir, String javaBin, Path __DEST__,UUID UUID,String accesToken, Path AssetsDir,String XUID,String clientID,String userType,String versionType) {
        command = javaBin + " -Xmx" + maxRam + "G -Xms" + minRam + "G " + "-cp \".;" + __DEST__ + "\\versions\\"
                + LastVersionId + "\\" + LastVersionId + ".jar;" + libs + "\" " + mainClass
                + " --gameDir " + gameDir
                + " --username " + user +
                " --assetIndex " + assetsIndex +
                " --accessToken " + accesToken +
                " --version " + LastVersionId +
                " --uuid " + UUID.toString() + 
                " --assetsDir " + AssetsDir + 
                " --xuid " + XUID + 
                " --clientId " + clientID +
                " --userType " + userType + 
                " --versionType " + versionType;
    }
    public CommandConstructor(String user, int maxRam, int minRam, String assetsIndex, String libs, String mainClass,
            String LastVersionId, String gameDir, String javaBin, Path __DEST__,UUID UUID,String accesToken, Path AssetsDir,String XUID,String clientID,String userType,String versionType,Quikplay r) {
        command = javaBin + " -Xmx" + maxRam + "G -Xms" + minRam + "G " + "-cp \".;" + __DEST__ + "\\versions\\"
                + LastVersionId + "\\" + LastVersionId + ".jar;" + libs + "\" " + mainClass
                + " --gameDir " + gameDir
                + " --username " + user +
                " --assetIndex " + assetsIndex +
                " --accessToken " + accesToken +
                " --version " + LastVersionId +
                " --uuid " + UUID.toString() + 
                " --assetsDir " + AssetsDir + 
                " --xuid " + XUID + 
                " --clientId " + clientID +
                " --userType " + userType + 
                " --versionType " + versionType +
                " --quickPlaySingleplayer " + r.w;
    }
    public String asText(){
        return command;
    }

}