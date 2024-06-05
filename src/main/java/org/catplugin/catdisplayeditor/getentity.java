package org.catplugin.catdisplayeditor;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.BlockDisplay;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.catplugin.catdisplayeditorymal.dataload;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class getentity implements Listener {
    public static Map<UUID,playersdata> pldata = new HashMap<>();
    public static Map<UUID,Inventory> guida = new HashMap<>();
    public static void getneariset(Player p,int a){
        //Location lo = p.getLocation();
        List<Entity> ens = p.getNearbyEntities(6,6,6);
        playersdata guidata = new playersdata();
        ens.removeIf(en -> en.getType() != EntityType.BLOCK_DISPLAY);
        if(ens.isEmpty()){
            p.sendMessage(dataload.noen);
        }
        else {
            //p.sendMessage(ens.toString());
            guidata.setEntities(ens);
            guidata.setPlayer(p);
            guidata.setPage(a);
            pldata.put(p.getUniqueId(), guidata);
            showlist(p, pldata.get(p.getUniqueId()).getEntities(), pldata.get(p.getUniqueId()).getPage());
        }
    }
    public static void showlist(Player p,List<Entity> ensl,int a) {
        p.closeInventory();
        int totalPages = (int) Math.ceil((double) ensl.size() / 45);
        int Pages = a;
        if( a < 0){
            Pages = 1;
        }
        else if(a > totalPages){
            Pages = totalPages;
        }
        List<Entity> ens = null;
        if (totalPages > 1){
            ens = ensl.subList((Pages-1)*44,(Pages-1)*44+44);
        }
        else {
            ens = ensl;
        }
        String[] guitem = new String[]{
                "         ",
                "         ",
                "         ",
                "         ",
                "         ",
                "xlxxxxxnx",
        };
        // x 背景板 l 上一页 n 下一页
        Inventory gui = Bukkit.createInventory(p, 54, dataload.Lname + ":" + String.valueOf(Pages));
        ItemStack bk = new ItemStack(Material.LIGHT_GRAY_STAINED_GLASS_PANE);
        ItemMeta bks = bk.getItemMeta();
        if (bks != null) {
            bks.setDisplayName(" ");
        }
        bk.setItemMeta(bks);
        ItemStack lt = new ItemStack(Material.GREEN_STAINED_GLASS_PANE);
        ItemMeta lts = lt.getItemMeta();
        if (lts != null) {
            lts.setDisplayName("<-");
        }
        lt.setItemMeta(lts);
        ItemStack nt = new ItemStack(Material.GREEN_STAINED_GLASS_PANE);
        ItemMeta nts = nt.getItemMeta();
        if (nts != null) {
            nts.setDisplayName("->");
        }
        nt.setItemMeta(nts);
        for (int i = 0;i <guitem.length;i++){
            char[] arr =guitem[i].toCharArray();
            for (int j = 0;j<arr.length;j++){{
                if(i*9+j < ens.toArray().length) {
                    BlockDisplay bd = null;
                    if (ens.get(i * 9 + j) instanceof BlockDisplay) {
                        bd = (BlockDisplay) ens.get(i * 9 + j);
                    }
                    //Block bs = bd.getBlock();
                    ItemStack im;
                    if (bd != null && bd.getBlock().getMaterial() != Material.AIR) {
                        im = new ItemStack(bd.getBlock().getMaterial());
                        ItemMeta ims = im.getItemMeta();
                        if (ims != null) {
                            ims.setDisplayName("No." + String.valueOf(i * 9 + j));
                        }
                        im.setItemMeta(ims);
                        if (arr[j] == ' ') {
                            gui.setItem(i * 9 + j, im);
                        }
                    }
                    else if(bd != null && bd.getBlock().getMaterial() == Material.AIR){
                        im = new ItemStack(Material.BARRIER);
                        ItemMeta ims = im.getItemMeta();
                        if (ims != null) {
                            ims.setDisplayName("No." + String.valueOf(i * 9 + j));
                        }
                        im.setItemMeta(ims);
                        if (arr[j] == ' ') {
                            gui.setItem(i * 9 + j, im);
                        }
                    }
                }
                else if (arr[j]== ' '){
                    gui.setItem(i*9+j,bk);
                }
                else if (arr[j] == 'x'){
                    gui.setItem(i*9+j,bk);
                }
                else if (arr[j] == 'l'){
                    gui.setItem(i*9+j,lt);
                }
                else if (arr[j] == 'n'){
                    gui.setItem(i*9+j,nt);
                }
            }
            }
    }
        p.closeInventory();
        guida.put(p.getUniqueId(),gui);
        p.openInventory(guida.get(p.getUniqueId()));
}
    private void shownextpage(Player p){
        // 获取当前GUI显示的页码
        pldata.get(p.getUniqueId()).setPage(pldata.get(p.getUniqueId()).getPage()+1);

        showlist(p,pldata.get(p.getUniqueId()).getEntities(),pldata.get(p.getUniqueId()).getPage()+1);
    }
    private void showlastpage(Player p){
        // 获取当前GUI显示的页码
        pldata.get(p.getUniqueId()).setPage(pldata.get(p.getUniqueId()).getPage()+1);

        showlist(p,pldata.get(p.getUniqueId()).getEntities(),pldata.get(p.getUniqueId()).getPage()-1);
    }
    //实体列表GUI点击事件
    @EventHandler
    public void onClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        //p.sendMessage(dataload.Lname + ":" + String.valueOf(pldata.get(p.getUniqueId()).getPage()));
            if (guida.containsKey(p.getUniqueId())) {
                int rawSlot = e.getRawSlot();
                if (rawSlot == 52) {
                    showlastpage(p);
                    e.setCancelled(true);
                } else if (rawSlot == 46) {
                    shownextpage(p);
                    e.setCancelled(true);
                } else if (45 <= rawSlot && rawSlot <= 53) {
                    p.sendMessage("Don't Click!");
                    e.setCancelled(true);
                } else if (rawSlot < pldata.get(p.getUniqueId()).getEntities().size()) {
                    Catdisplayeditor.getInstance().openCowGUI(p, (BlockDisplay) pldata.get(p.getUniqueId()).getEntities().get(rawSlot + (pldata.get(p.getUniqueId()).getPage() - 1) * 44));
                    //p.closeInventory();
                    e.setCancelled(true);
                } else
                    e.setCancelled(true);
                    p.sendMessage(p.toString());
            }
        }
    }

