package org.catplugin.catdisplayeditor;

import com.google.common.collect.Multimap;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.block.data.BlockData;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.tags.CustomItemTagContainer;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.catplugin.catdisplayeditorymal.dataload;

import java.lang.reflect.Array;
import java.util.*;

public final class Catdisplayeditor extends JavaPlugin implements Listener{
    public static Catdisplayeditor Plugin;
    private static Catdisplayeditor instance;
    public static String open;
    private static final String STICK_TAG = "catdisplay";
    public static BlockDisplay blockmain;
    public static Map<UUID,BlockDisplay> blockd = new HashMap<>();
    public static Map<UUID,Inventory> guida = new HashMap<>();

    @Override
    public void onEnable() {
        Plugin = this;
        // Plugin startup logic
        //say("欢迎使用展示方块编辑器");
        this.saveDefaultConfig();
        onLoadconfig();
        dataload.loadlang();
        dataload.load();
        say(open);
        getServer().getPluginManager().registerEvents(this, this);
        Objects.requireNonNull(getCommand("catdisplay")).setExecutor(this);
        Objects.requireNonNull(getCommand("catdisplay")).setTabCompleter(this);
        instance = this;
        Bukkit.getPluginManager().registerEvents(this, this);
        Bukkit.getPluginManager().registerEvents(new BlockdisplayListener(), this);
        Bukkit.getPluginManager().registerEvents(new MenuListener(),this);
        Bukkit.getPluginManager().registerEvents(new getentity(),this);
    }
    public void onLoadconfig(){
        open = this.getConfig().getString("test");
    }
    public static Catdisplayeditor getInstance() {
        return instance;
    }
    public void setLight(Player player){
        if (blockd.get(player.getUniqueId()).getBrightness() == null){
            Display.Brightness br = new Display.Brightness(15,0);
            blockd.get(player.getUniqueId()).setBrightness(br);
        }
    }

    public void openCowGUI(Player player,BlockDisplay blockdis) {
        blockd.put(player.getUniqueId(),blockdis);
        //blockmain = blockdis;
        setLight(player);
        String[] guitem = new String[]{
                " p   xyz ",
                " a       ",
                "  rtu  o ",
                "  bcd  e ",
                "  w h  l ",
                "        k",
        };
        // a 物品绑定 p 槽位介绍
        // x y z 实体坐标
        // b c d 三项大小
        // w h 实体视角/方向
        // r t y 相对生成点坐标
        // l 亮度渲染

        Inventory gui = Bukkit.createInventory(player, 9*guitem.length, dataload.getname());
        ItemStack bk = new ItemStack(Material.PINK_STAINED_GLASS_PANE);
        ItemMeta bks = bk.getItemMeta();
        Objects.requireNonNull(bks);
        bks.setDisplayName(" ");
        bk.setItemMeta(bks);
        ItemStack xbk = new ItemStack(Material.GREEN_STAINED_GLASS_PANE);
        ItemMeta xbks = xbk.getItemMeta();
        Objects.requireNonNull(xbks);
        xbks.setDisplayName(dataload.pos_x+"X:"+String.format("%.2f",blockd.get(player.getUniqueId()).getLocation().getX())+"Y:"+String.format("%.2f",blockd.get(player.getUniqueId()).getLocation().getY())+"Z"+String.format("%.2f",blockd.get(player.getUniqueId()).getLocation().getZ()));
        xbk.setItemMeta(xbks);
        xbks.setLore(Arrays.asList(dataload.pos_xlore));
        xbk.setItemMeta(xbks);
        ItemStack ybk = new ItemStack(Material.GREEN_STAINED_GLASS_PANE);
        ItemMeta ybks = ybk.getItemMeta();
        Objects.requireNonNull(ybks);
        ybks.setDisplayName(dataload.pos_y+"X:"+String.format("%.2f",blockd.get(player.getUniqueId()).getLocation().getX())+"Y:"+String.format("%.2f",blockd.get(player.getUniqueId()).getLocation().getY())+"Z"+String.format("%.2f",blockd.get(player.getUniqueId()).getLocation().getZ()));
        ybks.setLore(Arrays.asList(dataload.pos_ylore));
        ybk.setItemMeta(ybks);
        ItemStack zbk = new ItemStack(Material.GREEN_STAINED_GLASS_PANE);
        ItemMeta zbks = zbk.getItemMeta();
        Objects.requireNonNull(zbks);
        zbks.setDisplayName(dataload.pos_z+"X:"+String.format("%.2f",blockd.get(player.getUniqueId()).getLocation().getX())+"Y:"+String.format("%.2f",blockd.get(player.getUniqueId()).getLocation().getY())+"Z"+String.format("%.2f",blockd.get(player.getUniqueId()).getLocation().getZ()));
        zbks.setLore(Arrays.asList(dataload.pos_zlore));
        zbk.setItemMeta(zbks);
        ItemStack txbk = new ItemStack(Material.YELLOW_STAINED_GLASS_PANE);
        ItemMeta txbks = txbk.getItemMeta();
        Objects.requireNonNull(txbks);
        txbks.setDisplayName(dataload.tar_x+"X:"+String.format("%.2f",blockd.get(player.getUniqueId()).getTransformation().getTranslation().x())+"Y:"+String.format("%.2f",blockd.get(player.getUniqueId()).getTransformation().getTranslation().y())+"Z:"+String.format("%.2f",blockd.get(player.getUniqueId()).getTransformation().getTranslation().z()));
        txbks.setLore(Arrays.asList(dataload.tar_xlore));
        txbk.setItemMeta(txbks);
        ItemStack tybk = new ItemStack(Material.YELLOW_STAINED_GLASS_PANE);
        ItemMeta tybks =tybk.getItemMeta();
        Objects.requireNonNull(tybks);
        tybks.setDisplayName(dataload.tar_y+"X:"+String.format("%.2f",blockd.get(player.getUniqueId()).getTransformation().getTranslation().x())+"Y:"+String.format("%.2f",blockd.get(player.getUniqueId()).getTransformation().getTranslation().y())+"Z:"+String.format("%.2f",blockd.get(player.getUniqueId()).getTransformation().getTranslation().z()));
        tybks.setLore(Arrays.asList(dataload.tar_ylore));
        tybk.setItemMeta(tybks);
        ItemStack tzbk = new ItemStack(Material.YELLOW_STAINED_GLASS_PANE);
        ItemMeta tzbks = tzbk.getItemMeta();
        Objects.requireNonNull(tzbks);
        tzbks.setDisplayName(dataload.tar_z+"X:"+String.format("%.2f",blockd.get(player.getUniqueId()).getTransformation().getTranslation().x())+"Y:"+String.format("%.2f",blockd.get(player.getUniqueId()).getTransformation().getTranslation().y())+"Z:"+String.format("%.2f",blockd.get(player.getUniqueId()).getTransformation().getTranslation().z()));
        tzbks.setLore(Arrays.asList(dataload.tar_zlore));
        tzbk.setItemMeta(tzbks);
        ItemStack targ = new ItemStack(Material.ORANGE_STAINED_GLASS_PANE);
        ItemMeta targs = targ.getItemMeta();
        Objects.requireNonNull(targs);
        targs.setDisplayName(dataload.material);
        targ.setItemMeta(targs);
        ItemStack ori = new ItemStack(Material.ENDER_EYE);
        ItemMeta oris = ori.getItemMeta();
        Objects.requireNonNull(oris);
        oris.setDisplayName(dataload.origin+"X:"+String.format("%.2f",blockd.get(player.getUniqueId()).getTransformation().getTranslation().x())+"Y:"+String.format("%.2f",blockd.get(player.getUniqueId()).getTransformation().getTranslation().y())+"Z:"+String.format("%.2f",blockd.get(player.getUniqueId()).getTransformation().getTranslation().z()));
        ori.setItemMeta(oris);
        ItemStack sx = new ItemStack(Material.COMPASS);
        ItemMeta sxs = sx.getItemMeta();
        Objects.requireNonNull(sxs);
        sxs.setDisplayName(dataload.sizeX);
        sxs.setLore(Arrays.asList(dataload.sizelore));
        sx.setItemMeta(sxs);
        ItemStack sy = new ItemStack(Material.COMPASS);
        ItemMeta sys = sy.getItemMeta();
        Objects.requireNonNull(sys);
        sys.setDisplayName(dataload.sizeY);
        sys.setLore(Arrays.asList(dataload.sizelore));
        sy.setItemMeta(sys);
        ItemStack sz = new ItemStack(Material.COMPASS);
        ItemMeta szs = sz.getItemMeta();
        Objects.requireNonNull(szs);
        szs.setDisplayName(dataload.sizeZ);
        szs.setLore(Arrays.asList(dataload.sizelore));
        sz.setItemMeta(szs);
        ItemStack se = new ItemStack(Material.NETHER_STAR);
        ItemMeta ses = se.getItemMeta();
        Objects.requireNonNull(ses);
        ses.setDisplayName(dataload.sizeE);
        ses.setLore(Arrays.asList(dataload.sizelore));
        se.setItemMeta(ses);
        ItemStack wV = new ItemStack(Material.MAGMA_CREAM);
        ItemMeta wVs = wV.getItemMeta();
        Objects.requireNonNull(wVs);
        wVs.setDisplayName(dataload.wightV);
        wVs.setLore(Arrays.asList(dataload.Vlore));
        wV.setItemMeta(wVs);
        ItemStack hV = new ItemStack(Material.SLIME_BALL);
        ItemMeta hVs = hV.getItemMeta();
        Objects.requireNonNull(hVs);
        hVs.setDisplayName(dataload.heightV);
        hVs.setLore(Arrays.asList(dataload.Vlore));
        hV.setItemMeta(hVs);
        ItemStack lt = new ItemStack(Material.LIGHT);
        ItemMeta lts = lt.getItemMeta();
        Objects.requireNonNull(lts);
        lts.setDisplayName(dataload.light+ "BLOCK:" + Objects.requireNonNull(blockd.get(player.getUniqueId()).getBrightness()).getBlockLight() + "SKY:" + blockd.get(player.getUniqueId()).getBrightness().getSkyLight());
        lts.setLore(Collections.singletonList(Arrays.toString(dataload.lightlore)));
        lt.setItemMeta(lts);
        ItemStack aitem = new ItemStack(blockdis.getBlock().getMaterial());
        ItemStack kill = new ItemStack(Material.BARRIER);
        ItemMeta kim = kill.getItemMeta();
        if (kim != null) {
            kim.setDisplayName("§4§lKILL");
        }
        kill.setItemMeta(kim);
        for (int i = 0;i <guitem.length;i++){
            char[] arr =guitem[i].toCharArray();
            for (int j = 0;j<arr.length;j++){
                if (arr[j] == ' '){
                    gui.setItem(i*9+j,bk);
                }
                if (arr[j] == 'p'){
                    gui.setItem(i*9+j,targ);
                }
                if (arr[j] == 'x'){
                    gui.setItem(i*9+j,xbk);
                }
                if (arr[j] == 'y'){
                    gui.setItem(i*9+j,ybk);
                }
                if (arr[j] == 'z'){
                    gui.setItem(i*9+j,zbk);
                }
                if (arr[j] == 'r'){
                    gui.setItem(i*9+j,txbk);
                }
                if (arr[j] == 't'){
                    gui.setItem(i*9+j,tybk);
                }
                if (arr[j] == 'o'){
                    gui.setItem(i*9+j,ori);
                }
                if (arr[j] == 'b'){
                    gui.setItem(i*9+j,sx);
                }
                if (arr[j] == 'c'){
                    gui.setItem(i*9+j,sy);
                }
                if (arr[j] == 'd'){
                    gui.setItem(i*9+j,sz);
                }
                if (arr[j] == 'e'){
                    gui.setItem(i*9+j,se);
                }
                if (arr[j] == 'w'){
                    gui.setItem(i*9+j,wV);
                }
                if (arr[j] == 'h'){
                    gui.setItem(i*9+j,hV);
                }
                if (arr[j] == 'l'){
                    gui.setItem(i*9+j,lt);
                }
                if (arr[j] == 'u'){
                    gui.setItem(i*9+j,tzbk);
                }
                if (arr[j] == 'a'){
                    gui.setItem(i*9+j,aitem);
                }
                if (arr[j] == 'k'){
                    gui.setItem(i*9+j,kill);
                }
            }
        }
        player.closeInventory();
        guida.put(player.getUniqueId(),gui);
        player.openInventory(guida.get(player.getUniqueId()));
    }

//    @EventHandler
//    public void onInventoryClick(InventoryClickEvent event) {
//        if (event.getView().getTitle().equals("Cow Inventory")) {
//            event.setCancelled(true); // Prevent players from moving items
//        }
//    }
    @Override
    public void onDisable() {
        // Plugin shutdown logic
        say("展示方块编辑器已卸载");
    }
    public static void say(String s) {
        CommandSender sender = Bukkit.getConsoleSender();
        sender.sendMessage(s);
    }
    public boolean onCommand(CommandSender sender, Command cmd, String label, String args[]) {
        //...
        if (label.equalsIgnoreCase("catdisplay")) {
            if (!sender.isOp()) {
                sender.sendMessage(dataload.notop);
                return true;
            }
            if (args.length < 1) {
                sender.sendMessage(dataload.noarg);
                return true;
            }
            if (sender instanceof Player) {
                Player player = (Player) sender;
                switch (args[0]) {
                    case "getstack": {
                        sender.sendMessage(dataload.givesucee);
                        giveCatDisplayStick(player);
                        return true;
                    }
                    case "help": {
                        for (String i : dataload.help){
                            sender.sendMessage(i);
                        }
                        return true;
                    }
                    case "reload":{
                        reloadConfig();
                        dataload.reloadlang();
                        sender.sendMessage(dataload.reload1);
                        sender.sendMessage(dataload.reload2);
                    }
                }
            } else {
                sender.sendMessage("暂不支持控制台使用");
            }
            return true;
        }
        sender.sendMessage(dataload.noarg);
        return true;
    }
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        LinkedList<String> tips = new LinkedList<>();
        if (args.length == 1) {
            tips.add("getstack");
            tips.add("help");
            tips.add("reload");
        }
        return tips;
    }
    private void giveCatDisplayStick(Player player) {
        ItemStack stick = new ItemStack(Material.BLAZE_ROD);
        ItemMeta meta = stick.getItemMeta();
        if (meta != null) {
            meta.setDisplayName(dataload.itemname);
        }

        // 设置物品描述

        if (meta != null) {
            meta.setLore(Arrays.asList(dataload.itemlore));
        }
        if (meta != null) {
            meta.getPersistentDataContainer().set(new NamespacedKey(this, STICK_TAG), PersistentDataType.STRING, "true");
        }
        stick.setItemMeta(meta);
        player.getInventory().addItem(stick);
    }
    public boolean hasCatDisplayTag(ItemStack item) {
        ItemMeta meta = item.getItemMeta();
        if (meta != null && meta.getPersistentDataContainer().has(new NamespacedKey(this, STICK_TAG), PersistentDataType.STRING)) {
            String value = meta.getPersistentDataContainer().get(new NamespacedKey(this, STICK_TAG), PersistentDataType.STRING);
            return value != null && value.equals("true");
        }
        return false;
    }
    public class BlockdisplayListener implements Listener {
        @EventHandler
        public void onRightClick(PlayerInteractEvent event) {
            Player player = event.getPlayer();
            player.sendMessage(player.toString());
            ItemStack item = player.getInventory().getItemInMainHand();
            World world = player.getWorld();
            //BlockData bdta = Bukkit.createBlockData(Material.REDSTONE_BLOCK);;
            if(hasCatDisplayTag(item)){
                if(event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)){
                     getentity.getneariset(player,1);
                }
                else if(event.getAction().equals(Action.LEFT_CLICK_AIR) ||event.getAction().equals(Action.LEFT_CLICK_BLOCK)){
                    Location enlo = new Location(player.getLocation().getWorld(),player.getLocation().getX(),player.getLocation().getY(),player.getLocation().getZ(),0,0);
                    BlockDisplay en = (BlockDisplay) world.spawnEntity(enlo, EntityType.BLOCK_DISPLAY);
                    en.setBlock(Bukkit.createBlockData(Material.REDSTONE_BLOCK));
                    player.sendMessage(dataload.spawned);
                }
                event.setCancelled(true);
            }
            // Call the method in the main plugin class to open GUI
        }
        @EventHandler
        public void onclosegui(InventoryCloseEvent event){
            Player player = (Player) event.getPlayer();
            //player.sendMessage("你关闭了菜单");
            guida.remove(player.getUniqueId());
            getentity.guida.remove(player.getUniqueId());
        }
        @EventHandler
        public void onleave(PlayerQuitEvent event){
            Player player = (Player) event.getPlayer();
            //player.sendMessage("你关闭了菜单");
            guida.remove(player.getUniqueId());
            getentity.guida.remove(player.getUniqueId());
            blockd.remove(player.getUniqueId());
            getentity.pldata.remove(player.getUniqueId());
        }
    }
}
