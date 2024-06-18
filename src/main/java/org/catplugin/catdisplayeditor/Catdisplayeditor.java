package org.catplugin.catdisplayeditor;

import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.BlockDisplay;
import org.bukkit.entity.Display;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;
import org.catplugin.catdisplayeditorymal.dataload;

import java.util.*;

public final class Catdisplayeditor extends JavaPlugin implements Listener{
    public static Catdisplayeditor Plugin;
    private static Catdisplayeditor instance;
    public static String open;
    private static final String STICK_TAG = "catdisplay";
    public static BlockDisplay blockmain;
    public static Map<UUID,BlockDisplay> blockd = new HashMap<>();
    public static Map<UUID,Inventory> guida = new HashMap<>();
    public static Map<UUID, Boolean> isopen = new HashMap<>();

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
        Bukkit.getPluginManager().registerEvents(this, this);
        Bukkit.getPluginManager().registerEvents(new BlockdisplayListener(), this);
        Bukkit.getPluginManager().registerEvents(new MenuListener(),this);
        Bukkit.getPluginManager().registerEvents(new getentity(),this);
        instance = this;
    }
    public void onLoadconfig(){
        open = this.getConfig().getString("test");
    }
    public static Catdisplayeditor getInstance() {
        return instance;
    }
    public static void setLight(Player player){
        if (blockd.get(player.getUniqueId()).getBrightness() == null){
            Display.Brightness br = new Display.Brightness(15,0);
            blockd.get(player.getUniqueId()).setBrightness(br);
        }
    }

    public static void openCowGUI(Player player, BlockDisplay blockdis) {
        blockd.put(player.getUniqueId(),blockdis);
        //blockmain = blockdis;
        setLight(player);
        String[] guitem = new String[]{
                " p   xyz ",
                "5a4 0    ",
                " 1rtu  o ",
                "2 bcd  e ",
                "3 w h  l ",
                "        k",
        };
        // a 物品绑定 p 槽位介绍
        // x y z 实体坐标
        // b c d 三项大小
        // w h 实体视角/方向
        // r t y 相对生成点坐标
        // l 亮度渲染

        Inventory gui = Bukkit.createInventory(player, 9*guitem.length, dataload.getname());
        chestitem cd = new chestitem();
        cd.setitem(Material.PINK_STAINED_GLASS_PANE);
        cd.setname(dataload.clone);
        //cd.addlore(dataload.clone);
        chestitem cp = new chestitem();
        cp.setitem(Material.ARROW);
        cp.setname(dataload.copy);
        ItemStack bk = new ItemStack(Material.BROWN_STAINED_GLASS_PANE);
        ItemMeta bks = bk.getItemMeta();
        Objects.requireNonNull(bks);
        bks.setDisplayName(" ");
        bk.setItemMeta(bks);
        ItemStack xbk = new ItemStack(Material.GREEN_STAINED_GLASS_PANE);
        ItemMeta xbks = xbk.getItemMeta();
        Objects.requireNonNull(xbks);
        xbks.setDisplayName(dataload.pos_x+"X:"+String.format("%.2f",blockd.get(player.getUniqueId()).getLocation().getX())+"Y:"+String.format("%.2f",blockd.get(player.getUniqueId()).getLocation().getY())+"Z:"+String.format("%.2f",blockd.get(player.getUniqueId()).getLocation().getZ()));
        xbk.setItemMeta(xbks);
        xbks.setLore(Arrays.asList(dataload.pos_xlore));
        xbk.setItemMeta(xbks);
        ItemStack ybk = new ItemStack(Material.GREEN_STAINED_GLASS_PANE);
        ItemMeta ybks = ybk.getItemMeta();
        Objects.requireNonNull(ybks);
        ybks.setDisplayName(dataload.pos_y+"X:"+String.format("%.2f",blockd.get(player.getUniqueId()).getLocation().getX())+"Y:"+String.format("%.2f",blockd.get(player.getUniqueId()).getLocation().getY())+"Z:"+String.format("%.2f",blockd.get(player.getUniqueId()).getLocation().getZ()));
        ybks.setLore(Arrays.asList(dataload.pos_ylore));
        ybk.setItemMeta(ybks);
        ItemStack zbk = new ItemStack(Material.GREEN_STAINED_GLASS_PANE);
        ItemMeta zbks = zbk.getItemMeta();
        Objects.requireNonNull(zbks);
        zbks.setDisplayName(dataload.pos_z+"X:"+String.format("%.2f",blockd.get(player.getUniqueId()).getLocation().getX())+"Y:"+String.format("%.2f",blockd.get(player.getUniqueId()).getLocation().getY())+"Z:"+String.format("%.2f",blockd.get(player.getUniqueId()).getLocation().getZ()));
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
        lts.setDisplayName(dataload.light + "BLOCK:" + Objects.requireNonNull(blockd.get(player.getUniqueId()).getBrightness()).getBlockLight() + "SKY:" + Objects.requireNonNull(blockd.get(player.getUniqueId()).getBrightness()).getSkyLight());
        lts.setLore(Collections.singletonList(Arrays.toString(dataload.lightlore)));
        lt.setItemMeta(lts);
        BlockDisplay bd = blockd.get(player.getUniqueId());
        ItemStack aitem = new ItemStack(bd.getBlock().getMaterial());
        if (bd.getBlock().getMaterial().toString().contains("POTTED")) {
            aitem.setType(Material.FLOWER_POT);
            ItemMeta ims = aitem.getItemMeta();
            if (ims != null) {
                ims.setDisplayName((String.valueOf(bd.getBlock().getMaterial().getKey())));
            }
            aitem.setItemMeta(ims);
        } else if (bd.getBlock().getMaterial() == Material.FIRE) {
            aitem.setType(Material.FLINT_AND_STEEL);
            ItemMeta ims = aitem.getItemMeta();
            if (ims != null) {
                ims.setDisplayName("Fire");
            }
            aitem.setItemMeta(ims);
        } else if (bd.getBlock().getMaterial() == Material.SOUL_FIRE) {
            aitem.setType(Material.FLINT_AND_STEEL);
            ItemMeta ims = aitem.getItemMeta();
            if (ims != null) {
                ims.setDisplayName("Soul_Fire");
            }
            aitem.setItemMeta(ims);
        } //针对无物品方块
        ItemStack kill = new ItemStack(Material.BARRIER);
        ItemMeta kim = kill.getItemMeta();
        ItemStack changenum = new ItemStack(Material.GOLDEN_APPLE);
        ItemMeta changenumme = changenum.getItemMeta();
        ItemStack firstr = new ItemStack(Material.SUNFLOWER);
        ItemMeta firstrm = firstr.getItemMeta();
        Objects.requireNonNull(firstrm);
        firstrm.setDisplayName(dataload.firstr);
        firstr.setItemMeta(firstrm);
        ItemStack secondr = new ItemStack(Material.SUNFLOWER);
        ItemMeta secondrm = secondr.getItemMeta();
        Objects.requireNonNull(secondrm);
        secondrm.setDisplayName(dataload.secondr);
        secondr.setItemMeta(secondrm);
        Objects.requireNonNull(changenumme);
        changenumme.setDisplayName(dataload.Playvalue);
        chestitem changedata = new chestitem();
        changedata.setitem(Material.NAME_TAG);
        changedata.setname(dataload.addtag);
        changedata.addlore(dataload.taglist);
        for (String tag:blockd.get(player.getUniqueId()).getScoreboardTags()){
            changedata.addlore("§f[§a"+tag+"§f]");
        }
        if(!MenuListener.playernum.containsKey(player.getUniqueId())){
            MenuListener.playernum.put(player.getUniqueId(),0.1f);
        }
        changenumme.setLore(List.of(dataload.ValueLore +MenuListener.playernum.get(player.getUniqueId())));
        changenum.setItemMeta(changenumme);
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
                if (arr[j] == '0'){
                    gui.setItem(i*9+j,changenum);
                }
                if (arr[j] == '1') {
                    gui.setItem(i * 9 + j, changedata.getitem());
                }
                if (arr[j] == '2') {
                    gui.setItem(i * 9 + j, firstr);
                }
                if (arr[j] == '3') {
                    gui.setItem(i * 9 + j, secondr);
                }
                if (arr[j] == '4') {
                    gui.setItem(i * 9 + j, cd.getitem());
                }
                if (arr[j] == '5') {
                    gui.setItem(i * 9 + j, cp.getitem());
                }
            }
        }
        //player.closeInventory();
        guida.put(player.getUniqueId(),gui);
        player.openInventory(guida.get(player.getUniqueId()));
        getentity.pldata.remove(player.getUniqueId());
        getentity.guida.remove(player.getUniqueId());
        RocationInv.roin.remove(player.getUniqueId());
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
        guida.clear();
        getentity.guida.clear();
        blockd.clear();
        getentity.pldata.clear();
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
            //player.sendMessage(player.toString());
            ItemStack item = player.getInventory().getItemInMainHand();
            World world = player.getWorld();
            //BlockData bdta = Bukkit.createBlockData(Material.REDSTONE_BLOCK);;
            if(hasCatDisplayTag(item)){
                if(event.getAction().name().contains("RIGHT_CLICK") && !player.isSneaking()){
                     getentity.getneariset(player,1);
                } //右键判断
                else if(event.getAction().name().contains("LEFT_CLICK") && player.isSneaking()){
                    Location enlo = new Location(player.getLocation().getWorld(),player.getLocation().getBlockX(),player.getLocation().getBlockY(),player.getLocation().getBlockZ(),0,0);
                    if(MenuListener.playercopy.containsKey(player.getUniqueId())){
                        //BlockDisplay en = (BlockDisplay) world.spawnEntity(enlo, EntityType.BLOCK_DISPLAY);
                        MenuListener.playercopy.get(player.getUniqueId()).copy(enlo);
                    }
                    else {
                        BlockDisplay en = (BlockDisplay) world.spawnEntity(enlo, EntityType.BLOCK_DISPLAY);
                        en.setBlock(Bukkit.createBlockData(Material.REDSTONE_BLOCK));
                    }
                    player.sendMessage(dataload.spawned);
                }// shift右键判断
                else if (event.getAction().name().contains("LEFT_CLICK") && !player.isSneaking()){
                    MenuListener.playercopy.remove(player.getUniqueId());
                }
                event.setCancelled(true);
            }
            if(MenuListener.playeraction.containsKey(player.getUniqueId()) && (event.getAction().equals(Action.LEFT_CLICK_AIR) || event.getAction().equals(Action.LEFT_CLICK_BLOCK))){
                if(!Objects.equals(MenuListener.playeraction.get(player.getUniqueId()), "cloneblock")) {
                    event.setCancelled(true);
                    player.closeInventory(); //关闭GUI
                    MenuListener.playeraction.remove(player.getUniqueId()); //删除玩家动作Map
                    openCowGUI(player, Catdisplayeditor.blockd.get(player.getUniqueId())); //打开GUI
                }
            } //拥有动作MAP时，左键取消
            // Call the method in the main plugin class to open GUI
        }

        @EventHandler
        public void onopengui(InventoryOpenEvent event) {
            Player player = (Player) event.getPlayer();
            isopen.put(player.getUniqueId(), true);
        }
        @EventHandler
        public void onclosegui(InventoryCloseEvent event){
            Player player = (Player) event.getPlayer();
            isopen.remove(player.getUniqueId());
            //player.sendMessage("你关闭了菜单");
//            if(RocationInv.roin.containsKey(player.getUniqueId())){
//                guida.remove(player.getUniqueId());
//            }
//            if(guida.containsKey(player.getUniqueId())){
//                getentity.pldata.remove(player.getUniqueId());
//                RocationInv.roin.remove(player.getUniqueId());
//            }
            Bukkit.getScheduler().runTaskLater(Catdisplayeditor.Plugin, () -> {

//            player.sendMessage(String.valueOf(MenuListener.playerChatMap.containsKey(player.getUniqueId())));//false
//            player.sendMessage(String.valueOf(blockd.containsKey(player.getUniqueId())));//true
//            player.sendMessage(String.valueOf(MenuListener.playerjiao.containsKey(player.getUniqueId())));//true
//            player.sendMessage(String.valueOf(MenuListener.playeraction.containsKey(player.getUniqueId())));//false
//            player.sendMessage(String.valueOf(MenuListener.playernum.containsKey(player.getUniqueId())));//true
//            player.sendMessage(String.valueOf(MenuListener.setro.containsKey(player.getUniqueId())));//true
                if (!MenuListener.playeraction.containsKey(player.getUniqueId()) && !MenuListener.playerChatMap.containsKey(player.getUniqueId()) && !isopen.containsKey(player.getUniqueId())) {
                    //player.sendMessage("已清除map");
                    //player.sendMessage(String.valueOf(player.);
                    blockd.remove(player.getUniqueId());
                    getentity.pldata.remove(player.getUniqueId());
                    RocationInv.roin.remove(player.getUniqueId());
                    guida.remove(player.getUniqueId());
                    getentity.guida.remove(player.getUniqueId());
                }
            }, 2);
        }
        @EventHandler
        public void onleave(PlayerQuitEvent event){
            Player player = event.getPlayer();
            //wplayer.sendMessage("你关闭了菜单");
            MenuListener.playernum.remove(player.getUniqueId());
            guida.remove(player.getUniqueId());
            getentity.guida.remove(player.getUniqueId());
            blockd.remove(player.getUniqueId());
            getentity.pldata.remove(player.getUniqueId());
            MenuListener.setro.remove(player.getUniqueId());
            MenuListener.playercopy.remove(player.getUniqueId());
        }
    }
}
