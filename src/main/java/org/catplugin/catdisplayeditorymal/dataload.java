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
    public static String firstr;
    public static String secondr;
    public static String mrjiao;
    public static String mrx;
    public static String mry;
    public static String mrz;
    public static String mrsx;
    public static String mrsy;
    public static String mrsz;
    public static String[] jaolore;
    public static Boolean changed;
    private static YamlConfiguration lang;
    public static String clickblock;
    public static String clone;
    public static String clonesuc;
    public static String copy;
    public static String copysuc;
    public static String addtag;
    public static String taglist;
    public static String removetagsuc;
    public static String notag;
    public static String addtagsuc;
    public static void loadlang(){
        File file = new File(Catdisplayeditor.Plugin.getDataFolder(),"lang.yml");
        if(!file.exists()){
            Catdisplayeditor.Plugin.saveResource("lang.yml",false);
        }
        lang = YamlConfiguration.loadConfiguration(file);
        Version = lang.getString("Version");
        changed = lang.getBoolean("changed");
        if(!Objects.equals(Version, "1.0.3")){
            if(!changed){
                Catdisplayeditor.Plugin.saveResource("lang.yml",true);
                loadlang();
            }
            else {
                for (int i=0;i<20;i++) {
                    Catdisplayeditor.say("lang.yml文件版本错误，请尝试更新此文件");
                    Catdisplayeditor.say("lang.yml file version is incorrect, please try update this file");
                }
            }
        }
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
        firstr = lang.getString("menu.firstr");
        secondr = lang.getString("menu.secondr");
        mrjiao = lang.getString("menur.jiao");
        mrx = lang.getString("menur.x");
        mry = lang.getString("menur.y");
        mrz = lang.getString("menur.z");
        mrsx = lang.getString("menur.setx");
        mrsy = lang.getString("menur.sety");
        mrsz = lang.getString("menur.setz");
        jaolore  = lang.getStringList("menur.jiaolore").toArray(new String[3]);
        clone = lang.getString("menu.clone");
        clickblock = lang.getString("menu.clickblock");
        clonesuc = lang.getString("menu.clonesuc");
        copy = lang.getString("menu.copy");
        copysuc = lang.getString("menu.copysuc");
        addtag = lang.getString("menu.addtag");
        taglist = lang.getString("menu.taglist");
        notag = lang.getString("menu.notag");
        addtagsuc = lang.getString("menu.addtagsuc");
        removetagsuc = lang.getString("menu.removetagsuc");
    }
    public static String getname(){
        return name;
    }
    public static void reloadlang(){

        loadlang();
        load();
    }
}
