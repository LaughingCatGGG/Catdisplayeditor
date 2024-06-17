package org.catplugin.catdisplayeditor;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.BlockDisplay;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.catplugin.catdisplayeditorymal.dataload;
import org.joml.AxisAngle4f;
import org.joml.Quaternionf;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public class RocationInv {
    public static Map<UUID,Inventory> roin = new HashMap<>();
    public static void openrotationinv(Player player, BlockDisplay block){
        String[] guitem = new String[]{
                "      y  ",
                " j       ",
                "     sdf ",
        };
        Inventory gui = Bukkit.createInventory(player, 9*guitem.length, dataload.getname());
        ItemStack bk = new ItemStack(Material.PINK_STAINED_GLASS_PANE);
        ItemMeta bks = bk.getItemMeta();
        Objects.requireNonNull(bks);
        bks.setDisplayName(" ");
        bk.setItemMeta(bks);
        chestitem x = new chestitem();
        x.setitem(Material.GREEN_STAINED_GLASS_PANE);
        chestitem y = new chestitem();
        y.setitem(Material.GREEN_STAINED_GLASS_PANE);
        chestitem z = new chestitem();
        z.setitem(Material.GREEN_STAINED_GLASS_PANE);
        if (Objects.equals(MenuListener.setro.get(player.getUniqueId()), "le")){
            Quaternionf left = block.getTransformation().getLeftRotation();
            AxisAngle4f ang = new AxisAngle4f();
            left.get(ang);
//            x.setname(dataload.mrx+"Angle: "+ang.angle/Math.PI*180+"X: "+ang.x+"Y: "+ang.y+"Z: "+ang.z);
            y.setname(dataload.mry+"Angle: "+ang.angle/Math.PI*180+"X: "+ang.x+"Y: "+ang.y+"Z: "+ang.z);
//            z.setname(dataload.mrz+"Angle: "+ang.angle/Math.PI*180+"X: "+ang.x+"Y: "+ang.y+"Z: "+ang.z);
        }
        else {
            Quaternionf left = block.getTransformation().getRightRotation();
            AxisAngle4f ang = new AxisAngle4f();
            left.get(ang);
//            x.setname(dataload.mrx+"Angle: "+ang.angle/Math.PI*180+"X: "+ang.x+"Y: "+ang.y+"Z: "+ang.z);
            y.setname(dataload.mry+"Angle: "+ang.angle/Math.PI*180+"X: "+ang.x+"Y: "+ang.y+"Z: "+ang.z);
//            z.setname(dataload.mrz+"Angle: "+ang.angle/Math.PI*180+"X: "+ang.x+"Y: "+ang.y+"Z: "+ang.z);
        }
        chestitem sx = new chestitem();
        sx.setitem(Material.GREEN_STAINED_GLASS_PANE);
        sx.setname(dataload.mrsx);
        chestitem sy = new chestitem();
        sy.setitem(Material.GREEN_STAINED_GLASS_PANE);
        sy.setname(dataload.mrsy);
        chestitem sz = new chestitem();
        sz.setitem(Material.GREEN_STAINED_GLASS_PANE);
        sz.setname(dataload.mrsz);
        if(!MenuListener.playerjiao.containsKey(player.getUniqueId())){
            MenuListener.playerjiao.put(player.getUniqueId(),5f);
        }
        chestitem ji = new chestitem();
        ji.setitem(Material.YELLOW_STAINED_GLASS_PANE);
        ji.setname(dataload.mrjiao+MenuListener.playerjiao.get(player.getUniqueId()));
        ji.addlore(dataload.jaolore[0]);
        ji.addlore(dataload.jaolore[1]);
        ji.addlore(dataload.jaolore[2]);
        //ji.addlore("啊啊啊啊啊啊");
        for (int i = 0;i <guitem.length;i++){
            char[] arr =guitem[i].toCharArray();
            for (int j = 0;j<arr.length;j++) {
                if (arr[j] == ' '){
                    gui.setItem(i*9+j,bk);
                }
                if (arr[j] == 'j'){
                    gui.setItem(i*9+j,ji.getitem());
                }
                if (arr[j] == 'x'){
                    gui.setItem(i*9+j,x.getitem());
                }
                if (arr[j] == 'y'){
                    gui.setItem(i*9+j,y.getitem());
                }
                if (arr[j] == 'z'){
                    gui.setItem(i*9+j,z.getitem());
                }
                if (arr[j] == 's'){
                    gui.setItem(i*9+j,sx.getitem());
                }
                if (arr[j] == 'd'){
                    gui.setItem(i*9+j,sy.getitem());
                }
                if (arr[j] == 'f'){
                    gui.setItem(i*9+j,sz.getitem());
                }
            }
            }
        roin.put(player.getUniqueId(),gui);
        player.openInventory(roin.get(player.getUniqueId()));
        Catdisplayeditor.guida.remove(player.getUniqueId());
    }
}
