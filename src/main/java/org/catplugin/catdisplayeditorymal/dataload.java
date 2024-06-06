package org.catplugin.catdisplayeditorymal;

import org.bukkit.configuration.file.YamlConfiguration;
import org.catplugin.catdisplayeditor.Catdisplayeditor;

import java.io.File;
import java.util.Objects;

public class dataload {
    public static String name;
    public static String pos_x;
    public static String[] pos_xlore;
    public static String pos_y;
    public static String[] pos_ylore;
    public static String pos_z;
    public static String[] pos_zlore;
    public static String material;
    public static String tar_x;
    public static String[] tar_xlore;
    public static String tar_y;
    public static String[] tar_ylore;
    public static String tar_z;
    public static String[] tar_zlore;
    public static String origin;
    public static String sizeX;
    public static String[] sizelore;
    public static String sizeY;
    public static String sizeZ;
    public static String sizeE;
    public static String wightV;
    public static String heightV;
    public static String[] Vlore;
    public static String light;
    public static String[] lightlore;
    public static String Lname;
    public static String notplace;
    public static String printnum;
    public static String numE;
    public static String spawned;
    public static String itemname;
    public static String[] itemlore;
    public static String notop;
    public static String noarg;
    public static String givesucee;
    public static String[] help;
    public static String reload1;
    public static String reload2;
    public static String noen;
    public static String Version;
    public static String Playvalue;
    public static String ValueLore;
    public static String editmessage;
    private static YamlConfiguration lang;
    public static void loadlang(){
        File file = new File(Catdisplayeditor.Plugin.getDataFolder(),"lang.yml");
        if(!file.exists()){
            Catdisplayeditor.Plugin.saveResource("lang.yml",false);
        }
        lang = YamlConfiguration.loadConfiguration(file);
        Version = lang.getString("version");
//        if(!Objects.equals(Version, "1.0.0")){
//            for (int i=0;i<20;i++){
//                Catdisplayeditor.say("lang.yml文件版本错误，请删除或更新");
//                Catdisplayeditor.say("lang.yml file version is incorrect, please delete or update");
//            }
//        }
    }
    public static void load(){

        name = lang.getString("menu.name");
        material = lang.getString("menu.material");
        pos_x = lang.getString("menu.pos_x");
        pos_xlore = lang.getStringList("menu.pos_xlore").toArray(new String[0]);
        pos_y = lang.getString("menu.pos_y");
        pos_ylore = lang.getStringList("menu.pos_ylore").toArray(new String[2]);
        pos_z = lang.getString("menu.pos_z");
        pos_zlore = lang.getStringList("menu.pos_zlore").toArray(new String[2]);
        tar_x = lang.getString("menu.tar_x");
        tar_xlore = lang.getStringList("menu.tar_xlore").toArray(new String[2]);
        tar_z = lang.getString("menu.tar_z");
        tar_zlore = lang.getStringList("menu.tar_zlore").toArray(new String[2]);
        tar_y = lang.getString("menu.tar_y");
        tar_ylore = lang.getStringList("menu.tar_ylore").toArray(new String[2]);
        origin = lang.getString("menu.origin");
        sizeX = lang.getString("menu.sizeX");
        sizeY = lang.getString("menu.sizeY");
        sizeZ = lang.getString("menu.sizeZ");
        sizelore = lang.getStringList("menu.sizelore").toArray(new String[2]);
        sizeE = lang.getString("menu.sizeE");
        wightV = lang.getString("menu.wightV");
        heightV = lang.getString("menu.heightV");
        Vlore = lang.getStringList("menu.Vlore").toArray(new String[2]);
        light = lang.getString("menu.light");
        lightlore = lang.getStringList("menu.lightLore").toArray(new String[0]);
        Lname = lang.getString("menu2.name");
        notplace = lang.getString("menu.notPlace");
        printnum = lang.getString("menu.sendNumber");
        numE = lang.getString("menu.NumberE");
        spawned = lang.getString("menu.spawned");
        itemlore = lang.getStringList("Item.lore").toArray(new String[4]);
        itemname = lang.getString("Item.name");
        notop = lang.getString("cmd.notop");
        noarg = lang.getString("cmd.noarg");
        givesucee = lang.getString("cmd.givesucee");
        help = lang.getStringList("cmd.help").toArray(new String[2]);
        reload1 = lang.getString("cmd.reload1");
        reload2 = lang.getString("cmd.reload2");
        noen = lang.getString("cmd.noen");
        Playvalue = lang.getString("menu.Playvalue");
        ValueLore = lang.getString("menu.ValueLore");
        editmessage = lang.getString("menu.editmessage");
    }
    public static String getname(){
        return name;
    }
    public static void reloadlang(){

        loadlang();
        load();
    }
}
