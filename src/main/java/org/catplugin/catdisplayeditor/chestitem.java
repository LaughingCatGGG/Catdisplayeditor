package org.catplugin.catdisplayeditor;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class chestitem {
    private ItemStack item ;

    public void setitem(Material m){
        item = new ItemStack(m);
    }
    public void setname(String name){
        ItemMeta m = item.getItemMeta();
        if (m != null) {
            m.setDisplayName(name);
        }
        item.setItemMeta(m);
    }
    public void addlore(String lore){
        ItemMeta m = item.getItemMeta();
        Objects.requireNonNull(m);
        List<String> olore = m.getLore();
        //olore = m.getLore();
        if (olore != null) {
            olore.add(lore);
        }
        else {
            olore = new ArrayList<>();
            olore.add(lore);
        }
        m.setLore(olore);
        item.setItemMeta(m);
    }
    public void setenchant(Boolean bo){
        ItemMeta m = item.getItemMeta();
        if (bo){
            if (m != null) {
                m.addEnchant(Enchantment.ARROW_INFINITE,1,true);
                m.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            }
        }
        else {
            if (m != null) {
                m.removeEnchant(Enchantment.ARROW_INFINITE);
                m.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            }
        }
    }
    public ItemStack getitem(){
        return item;
    }
}
