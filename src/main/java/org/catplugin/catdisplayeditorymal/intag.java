package org.catplugin.catdisplayeditorymal;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Tag;

public class intag {
    public static boolean isintag(Material m){
        Tag<Material> tagm = Bukkit.getTag("blocks",m.getKey(),Material.class);
        if (tagm == Tag.FLOWER_POTS){
            return true;
        }
        else if (tagm == Tag.STAIRS){
            return true;
        }
        return false;
    }
}
