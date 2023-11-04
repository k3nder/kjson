package net.kender.MCutils.players.Server;

//import java.io.DataInputStream;
//import java.io.DataOutputStream;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
//import javafx.scene.image.Image;
//import net.minecraft.nbt.NbtCompound;
//import net.minecraft.nbt.NbtIo;
//import net.minecraft.nbt.NbtList;

public class Server {
    public ServerIp ip;
    public String Name;
    public Server(ServerIp r){
         ip = r;
    }
    public Server(ServerIp r,String name){
        ip = r;
        Name = name;
    }
    //public void Register(ServerIp ip,String name){
    //    File serversDatFile = new File("path/to/your/servers.dat"); // Reemplaza con la ubicación de tu archivo servers.dat
//
    //    try (DataInputStream dataInputStream = new DataInputStream(new FileInputStream(serversDatFile))) {
    //        NbtCompound compoundTag = NbtIo.read(serversDatFile);
//
    //        // Verifica si el archivo contiene una lista de servidores
    //        if (compoundTag.contains("servers", 9)) { // 9 representa TAG_List en NBT
    //            NbtList serversList = compoundTag.getList("servers", 10); // 10 representa TAG_Compound en NBT
//
    //            // Identifica la entrada del servidor que deseas modificar (puedes usar un bucle para buscarla)
    //            int serverIndex = 0; // Cambia esto al índice del servidor que deseas modificar
//
    //            if (serverIndex >= 0 && serverIndex < serversList.size()) {
    //                NbtCompound serverEntry = serversList.getCompound(serverIndex);
//
    //                // Actualiza la dirección IP del servidor
    //                serverEntry.putString(ip.IP, name);
//
    //                // Guarda la estructura de datos actualizada en el archivo servers.dat
    //                try (DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(serversDatFile))) {
    //                    NbtIo.write(compoundTag, dataOutputStream);
    //                    //System.out.println("IP del servidor modificada con éxito.");
    //                } catch (IOException e) {
    //                    e.printStackTrace();
    //                }
    //            } else {
    //                System.out.println("Índice de servidor no válido.");
    //            }
    //        } else {
    //            System.out.println("No se encontró la lista de servidores en servers.dat.");
    //        }
    //    } catch (IOException e) {
    //        e.printStackTrace();
    //    }
    //}
    //public List<Server> open(File e){
    //    List<Server> l = new ArrayList();
    //    if (!e.exists()) {
    //        System.out.println("El archivo servers.dat no existe.");
    //        return null;
    //    }
//
    //    try (DataInputStream dataInputStream = new DataInputStream(new FileInputStream(e))) {
    //        NbtCompound compoundTag = NbtIo.read(e);
//
    //        // Verifica si el archivo contiene una lista de servidores
    //        if (compoundTag.contains("servers", 9)) { // 9 representa TAG_List en NBT
    //            NbtList serversList = compoundTag.getList("servers", 10); // 10 representa TAG_Compound en NBT
//
    //            // Itera a través de la lista de servidores
    //            for (int i = 0; i < serversList.size(); i++) {
    //                NbtCompound serverEntry = serversList.getCompound(i);
    //                String serverName = serverEntry.getString("name");
    //                String serverAddress = serverEntry.getString("ip");
//
    //                l.add(new Server(new ServerIp(serverAddress), serverName));
    //            }
    //            return l;
    //        } else {
    //            System.out.println("No se encontró la lista de servidores en servers.dat.");
    //            return null;
    //        }
    //    } catch (IOException er) {
    //        er.printStackTrace();
    //        return null;
    //    }
    //}

    // TODO añadir mas methodos
}
