package org.catplugin.catdisplayeditor;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.BlockDisplay;
//import org.bukkit.entity.Cat;
import org.bukkit.entity.Display;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
//import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Transformation;
//import org.catplugin.catdisplayeditorymal.QuaternionRotation;
import org.catplugin.catdisplayeditorymal.dataload;
import org.joml.AxisAngle4f;
import org.joml.Quaternionf;
import org.joml.Vector3f;

import java.util.*;

import static org.bukkit.event.inventory.ClickType.MIDDLE;

public class MenuListener implements Listener {
    //private JavaPlugin plugin;

//    public void ChatListener(JavaPlugin plugin) {
//        this.plugin = plugin;
//    }
    public static Map<UUID, String> playerChatMap = new HashMap<>();
    public static Map<UUID, Float> playernum = new HashMap<>();
    public static Map<UUID, String> playeraction = new HashMap<>();
    public static Map<UUID, String> setro = new HashMap<>();
    public static Map<UUID,Float> playerjiao = new HashMap<>();
    //private Map<UUID,BlockDisplay> playertarget = new HashMap<>();
    //private static double num = 0;
    private static final boolean isgetnum = false;
    //编辑界面事件
    @EventHandler
    public void onClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        int rawSlot = e.getRawSlot();
        //Catdisplayeditor.blockd.get(p.getUniqueId())play Catdisplayeditor.blockd.get(p.getUniqueId()) = Catdisplayeditor.blockmain;
        if (Catdisplayeditor.guida.containsKey(p.getUniqueId())) {
            if (rawSlot == 10) {
                if (e.isLeftClick() && p.getItemOnCursor().getType() == Material.FLINT_AND_STEEL) {
                    BlockData firedata = Bukkit.createBlockData(Material.FIRE);
                    Catdisplayeditor.blockd.get(p.getUniqueId()).setBlock(firedata);
                } else if (e.isRightClick() && p.getItemOnCursor().getType() == Material.FLINT_AND_STEEL) {
                    BlockData firedata = Bukkit.createBlockData(Material.SOUL_FIRE);
                    Catdisplayeditor.blockd.get(p.getUniqueId()).setBlock(firedata);
                } else if (canPlace(p.getItemOnCursor().getType())) {
                    BlockData blockdata = Bukkit.createBlockData(p.getItemOnCursor().getType());
                    Catdisplayeditor.blockd.get(p.getUniqueId()).setBlock(blockdata);
                } else {
                    p.sendMessage(dataload.notplace);
                }
                Catdisplayeditor.openCowGUI(p, Catdisplayeditor.blockd.get(p.getUniqueId()));
                //更新菜单
            }//更换物品格
            else if (rawSlot == 13) {
                p.sendMessage(dataload.printnum);
                playerChatMap.put(p.getUniqueId(), "setvalue");
                p.closeInventory();
            }
            else if (rawSlot == 5) {
                if (e.getClick() == MIDDLE) {
                    p.sendMessage(dataload.printnum);
                    playerChatMap.put(p.getUniqueId(), "LocX");
                    p.closeInventory();
                } else {
                    playeraction.put(p.getUniqueId(), "LocX");
                    p.getInventory().setHeldItemSlot(4);
                    p.sendMessage(dataload.editmessage);
                    p.closeInventory();
                }
            } else if (rawSlot == 6) {
                if (e.getClick() == MIDDLE) {
                    p.sendMessage(dataload.printnum);
                    playerChatMap.put(p.getUniqueId(), "LocY");
                    p.closeInventory();
                } else {
                    playeraction.put(p.getUniqueId(), "LocY");
                    p.getInventory().setHeldItemSlot(4);
                    p.sendMessage(dataload.editmessage);
                    p.closeInventory();
                }
            } else if (rawSlot == 7) {
                if (e.getClick() == MIDDLE) {
                    p.sendMessage(dataload.printnum);
                    playerChatMap.put(p.getUniqueId(), "LocZ");
                    p.closeInventory();
                } else {
                    playeraction.put(p.getUniqueId(), "LocZ");
                    p.getInventory().setHeldItemSlot(4);
                    p.sendMessage(dataload.editmessage);
                    p.closeInventory();
                }
            }
            //XYZ坐标
            else if (rawSlot == 20) {
                if (e.getClick() == MIDDLE) {
                    p.sendMessage(dataload.printnum);
                    playerChatMap.put(p.getUniqueId(), "TarX");
                    p.closeInventory();
                } else {
                    playeraction.put(p.getUniqueId(), "TarX");
                    p.getInventory().setHeldItemSlot(4);
                    p.sendMessage(dataload.editmessage);
                    p.closeInventory();
                }
            } else if (rawSlot == 21) {
                if (e.getClick() == MIDDLE) {
                    p.sendMessage(dataload.printnum);
                    playerChatMap.put(p.getUniqueId(), "TarY");
                    p.closeInventory();
                } else {
                    playeraction.put(p.getUniqueId(), "TarY");
                    p.getInventory().setHeldItemSlot(4);
                    p.sendMessage(dataload.editmessage);
                    p.closeInventory();
                }
            } else if (rawSlot == 22) {
                if (e.getClick() == MIDDLE) {
                    p.sendMessage(dataload.printnum);
                    playerChatMap.put(p.getUniqueId(), "TarZ");
                    p.closeInventory();
                } else {
                    playeraction.put(p.getUniqueId(), "TarZ");
                    p.getInventory().setHeldItemSlot(4);
                    p.sendMessage(dataload.editmessage);
                    p.closeInventory();
                }
            }//相对生成坐标
            else if (rawSlot == 25) {
                Transformation transf = Catdisplayeditor.blockd.get(p.getUniqueId()).getTransformation();
                Vector3f tarlocat = transf.getTranslation();
                Quaternionf lelo = transf.getLeftRotation();
                Quaternionf rolo = transf.getRightRotation();
                Vector3f sca = transf.getScale();
                //p.sendMessage(String.valueOf(lelo));
                //p.sendMessage(String.valueOf(rolo));
                //tarlocat.set(QuaternionRotation.getlo(sca,lelo,rolo));
                //p.sendMessage(String.valueOf(angl));
                //p.sendMessage(String.valueOf(angr));
                //tarlocat.set(vx, vy, vz);
                tarlocat.set(-sca.x/2,-sca.y/2,-sca.z/2);
                Transformation ntransf = new Transformation(tarlocat, transf.getLeftRotation(), transf.getScale(), transf.getRightRotation());
                Catdisplayeditor.blockd.get(p.getUniqueId()).setTransformation(ntransf);
                Catdisplayeditor.openCowGUI(p, Catdisplayeditor.blockd.get(p.getUniqueId()));
            }//中心点
            else if (rawSlot == 29) {
                if (e.getClick() == MIDDLE) {
                    p.sendMessage(dataload.printnum);
                    playerChatMap.put(p.getUniqueId(), "ScaX");
                    p.closeInventory();
                } else {
                    playeraction.put(p.getUniqueId(), "ScaX");
                    p.getInventory().setHeldItemSlot(4);
                    p.sendMessage(dataload.editmessage);
                    p.closeInventory();
                }
            } else if (rawSlot == 30) {
                if (e.getClick() == MIDDLE) {
                    p.sendMessage(dataload.printnum);
                    playerChatMap.put(p.getUniqueId(), "ScaY");
                    p.closeInventory();
                } else {
                    playeraction.put(p.getUniqueId(), "ScaY");
                    p.getInventory().setHeldItemSlot(4);
                    p.sendMessage(dataload.editmessage);
                    p.closeInventory();
                }
            } else if (rawSlot == 31) {
                if (e.getClick() == MIDDLE) {
                    p.sendMessage(dataload.printnum);
                    playerChatMap.put(p.getUniqueId(), "ScaZ");
                    p.closeInventory();
                } else {
                    playeraction.put(p.getUniqueId(), "ScaZ");
                    p.getInventory().setHeldItemSlot(4);
                    p.sendMessage(dataload.editmessage);
                    p.closeInventory();
                }
            }//大小
            else if (rawSlot == 34) {
                if (e.getClick() == MIDDLE) {
                    p.sendMessage(dataload.printnum);
                    playerChatMap.put(p.getUniqueId(), "ScaALL");
                    p.closeInventory();
                } else {
                    playeraction.put(p.getUniqueId(), "ScaALL");
                    p.getInventory().setHeldItemSlot(4);
                    p.sendMessage(dataload.editmessage);
                    p.closeInventory();
                }
            }//同步大小提升
            else if (rawSlot == 38) {
               float roY = Catdisplayeditor.blockd.get(p.getUniqueId()).getLocation().getPitch();
                if (e.isLeftClick() && !e.isShiftClick()){
                    roY += 0.1F;
                    Catdisplayeditor.blockd.get(p.getUniqueId()).setRotation(roY,Catdisplayeditor.blockd.get(p.getUniqueId()).getLocation().getYaw());
                }
                else if (e.isRightClick() && !e.isShiftClick()){
                    roY -= 0.1F;
                    Catdisplayeditor.blockd.get(p.getUniqueId()).setRotation(roY,Catdisplayeditor.blockd.get(p.getUniqueId()).getLocation().getYaw());
                }
                else if (e.isLeftClick() && e.isShiftClick()){
                    roY += 1;
                    Catdisplayeditor.blockd.get(p.getUniqueId()).setRotation(roY,Catdisplayeditor.blockd.get(p.getUniqueId()).getLocation().getYaw());
                }
                else if (e.isRightClick() && e.isShiftClick()){
                    roY -= 1;
                    Catdisplayeditor.blockd.get(p.getUniqueId()).setRotation(roY,Catdisplayeditor.blockd.get(p.getUniqueId()).getLocation().getYaw());
                }
                else if (e.getClick() == MIDDLE) {
                    p.sendMessage(dataload.printnum);
                    playerChatMap.put(p.getUniqueId(), "RoX");
                    p.closeInventory();
                }
            } else if (rawSlot == 40) {
                float roY = Catdisplayeditor.blockd.get(p.getUniqueId()).getLocation().getYaw();
                if (e.isLeftClick() && !e.isShiftClick()){
                    roY += 0.1F;
                    Catdisplayeditor.blockd.get(p.getUniqueId()).setRotation(Catdisplayeditor.blockd.get(p.getUniqueId()).getLocation().getPitch(),roY);
                }
                else if (e.isRightClick() && !e.isShiftClick()){
                    roY -= 0.1F;
                    Catdisplayeditor.blockd.get(p.getUniqueId()).setRotation(Catdisplayeditor.blockd.get(p.getUniqueId()).getLocation().getPitch(),roY);
                }
                else if (e.isLeftClick() && e.isShiftClick()){
                    roY += 1;
                    Catdisplayeditor.blockd.get(p.getUniqueId()).setRotation(Catdisplayeditor.blockd.get(p.getUniqueId()).getLocation().getPitch(),roY);
                }
                else if (e.isRightClick() && e.isShiftClick()){
                    roY -= 1;
                    Catdisplayeditor.blockd.get(p.getUniqueId()).setRotation(Catdisplayeditor.blockd.get(p.getUniqueId()).getLocation().getPitch(),roY);
                }
                else if (e.getClick() == MIDDLE) {
                    p.sendMessage(dataload.printnum);
                    playerChatMap.put(p.getUniqueId(), "");
                    p.closeInventory();
                }
            } else if (rawSlot == 43) {
                Display.Brightness lt = Catdisplayeditor.blockd.get(p.getUniqueId()).getBrightness();
                int ltb = 0;
                if (lt != null) {
                    ltb = lt.getBlockLight();
                }
                int lts = 0;
                if (lt != null) {
                    lts = lt.getSkyLight();
                }
                if (e.isLeftClick() && !e.isShiftClick()){
                    if(ltb+1<=15) {
                        Display.Brightness nlt = new Display.Brightness(ltb + 1, lts);
                        Catdisplayeditor.blockd.get(p.getUniqueId()).setBrightness(nlt);
                        Catdisplayeditor.openCowGUI(p, Catdisplayeditor.blockd.get(p.getUniqueId()));
                    }
                }
                else if (e.isRightClick() && !e.isShiftClick()){
                    if(ltb-1>=0) {
                        Display.Brightness nlt = new Display.Brightness(ltb - 1, lts);
                        Catdisplayeditor.blockd.get(p.getUniqueId()).setBrightness(nlt);
                        Catdisplayeditor.openCowGUI(p, Catdisplayeditor.blockd.get(p.getUniqueId()));
                    }
                }
                else if (e.isLeftClick() && e.isShiftClick()){
                    if (lts + 1 <= 15) {
                        Display.Brightness nlt = new Display.Brightness(ltb, lts + 1);
                        Catdisplayeditor.blockd.get(p.getUniqueId()).setBrightness(nlt);
                        Catdisplayeditor.openCowGUI(p, Catdisplayeditor.blockd.get(p.getUniqueId()));
                    }
                }
                else if (e.isRightClick() && e.isShiftClick()){
                    if (lts - 1 >= 0) {
                        Display.Brightness nlt = new Display.Brightness(ltb, lts - 1);
                        Catdisplayeditor.blockd.get(p.getUniqueId()).setBrightness(nlt);
                        Catdisplayeditor.openCowGUI(p, Catdisplayeditor.blockd.get(p.getUniqueId()));
                    }
                }
            }
            else if (rawSlot == 53) {
                Catdisplayeditor.blockd.get(p.getUniqueId()).remove();
                p.closeInventory();
            }
            else if (rawSlot == 27){
                setro.put(p.getUniqueId(),"le");
                RocationInv.openrotationinv(p,Catdisplayeditor.blockd.get(p.getUniqueId()));

                //旋转菜单
            }
            else if (rawSlot == 36){
                setro.put(p.getUniqueId(),"ri");
                RocationInv.openrotationinv(p,Catdisplayeditor.blockd.get(p.getUniqueId()));

            }
            if (rawSlot >= 0 && rawSlot <= 53) {
                e.setCancelled(true);
            }
        }
        if (RocationInv.roin.containsKey(p.getUniqueId())) {
            if(Objects.equals(setro.get(p.getUniqueId()), "le")) {
                if (rawSlot == 10) {
                    if (e.isLeftClick()) {
                        playeraction.put(p.getUniqueId(), "jiao");
                        p.closeInventory();
                        p.getInventory().setHeldItemSlot(4);
                        p.sendMessage(dataload.editmessage);
                    }
                    else if(e.isRightClick()){
                        p.sendMessage(dataload.printnum);
                        playerChatMap.put(p.getUniqueId(), "numjiao");
                        p.closeInventory();
                    }
                    else if (e.getClick() == MIDDLE) {
                        p.sendMessage(dataload.printnum);
                        playerChatMap.put(p.getUniqueId(), "jiao");
                        p.closeInventory();
                    }
                }
//                else if (rawSlot == 5) {
//
//                }
                else if (rawSlot == 6) {
                    BlockDisplay bl = Catdisplayeditor.blockd.get(p.getUniqueId());
                    Quaternionf lelo = bl.getTransformation().getLeftRotation();
                    AxisAngle4f ang = new AxisAngle4f();
                    lelo.get(ang);
                    if(ang.angle==0){
                        lelo.setAngleAxis(Math.PI, 0, 0, 0);
                    }
                    else {
                        lelo.setAngleAxis(ang.angle, 0, 0, 0);
                    }
                    bl.setTransformation(new Transformation(bl.getTransformation().getTranslation(), lelo, bl.getTransformation().getScale(), bl.getTransformation().getRightRotation()));
                }
//                else if (rawSlot == 7) {
//
//                }
                else if (rawSlot == 23) {
                    BlockDisplay bl = Catdisplayeditor.blockd.get(p.getUniqueId());
                    Quaternionf lelo = bl.getTransformation().getLeftRotation();
                    AxisAngle4f ang = new AxisAngle4f();
                    lelo.get(ang);
                    if(ang.angle==0){
                        lelo.setAngleAxis(Math.PI, 1, 0, 0);
                    }
                    else {
                        lelo.setAngleAxis(ang.angle, 1, 0, 0);
                    }
                    bl.setTransformation(new Transformation(bl.getTransformation().getTranslation(), lelo, bl.getTransformation().getScale(), bl.getTransformation().getRightRotation()));
                } else if (rawSlot == 24) {
                    BlockDisplay bl = Catdisplayeditor.blockd.get(p.getUniqueId());
                    Quaternionf lelo = bl.getTransformation().getLeftRotation();
                    AxisAngle4f ang = new AxisAngle4f();
                    lelo.get(ang);
//                    if(ang.angle==0){
//                        lelo.setAngleAxis(Math.PI, 0, 1, 0);
//                    }
//                    else {
//                        lelo.setAngleAxis(ang.angle, 0, 1, 0);
//                    }
                    lelo.set(0,0,0,1);
                    bl.setTransformation(new Transformation(bl.getTransformation().getTranslation(), lelo, bl.getTransformation().getScale(), bl.getTransformation().getRightRotation()));
                } else if (rawSlot == 25) {
                    BlockDisplay bl = Catdisplayeditor.blockd.get(p.getUniqueId());
                    Quaternionf lelo = bl.getTransformation().getLeftRotation();
                    AxisAngle4f ang = new AxisAngle4f();
                    lelo.get(ang);
                    if(ang.angle==0){
                        lelo.setAngleAxis(Math.PI, 0, 0, 1);
                    }
                    else {
                        lelo.setAngleAxis(ang.angle, 0, 0, 1);
                    }
                    bl.setTransformation(new Transformation(bl.getTransformation().getTranslation(), lelo, bl.getTransformation().getScale(), bl.getTransformation().getRightRotation()));
                }
            }
            else if (Objects.equals(setro.get(p.getUniqueId()), "ri")){
                if (rawSlot == 10) {
                    if (e.isLeftClick()) {
                    playeraction.put(p.getUniqueId(), "jiao");
                    p.closeInventory();
                    p.getInventory().setHeldItemSlot(4);
                    p.sendMessage(dataload.editmessage);
                }
                else if(e.isRightClick()){
                    p.sendMessage(dataload.printnum);
                    playerChatMap.put(p.getUniqueId(), "numjiao");
                    p.closeInventory();
                }
                else if (e.getClick() == MIDDLE) {
                    p.sendMessage(dataload.printnum);
                    playerChatMap.put(p.getUniqueId(), "jiao");
                    p.closeInventory();
                }
                }
//                else if (rawSlot == 5) {
//
//                }
                else if (rawSlot == 6) {
                    BlockDisplay bl = Catdisplayeditor.blockd.get(p.getUniqueId());
                    Quaternionf lelo = bl.getTransformation().getRightRotation();
                    //AxisAngle4f ang = new AxisAngle4f();
                    //lelo.get(ang);
//                    if(ang.angle==0){
//                        lelo.setAngleAxis(Math.PI, 0, 0, 0);
//                    }
//                    else {
//                        lelo.setAngleAxis(ang.angle, 0, 0, 0);
//                    }
                    lelo.set(0,0,0,1);
                    bl.setTransformation(new Transformation(bl.getTransformation().getTranslation(), bl.getTransformation().getLeftRotation(), bl.getTransformation().getScale(), lelo));
                }
//                else if (rawSlot == 7) {
//
//                }
                else if (rawSlot == 23) {
                    BlockDisplay bl = Catdisplayeditor.blockd.get(p.getUniqueId());
                    Quaternionf lelo = bl.getTransformation().getRightRotation();
                    AxisAngle4f ang = new AxisAngle4f();
                    lelo.get(ang);
                    if(ang.angle==0){
                        lelo.setAngleAxis(Math.PI, 1, 0, 0);
                    }
                    else {
                        lelo.setAngleAxis(ang.angle, 1, 0, 0);
                    }
                    bl.setTransformation(new Transformation(bl.getTransformation().getTranslation(), bl.getTransformation().getLeftRotation(), bl.getTransformation().getScale(), lelo));
                } else if (rawSlot == 24) {
                    BlockDisplay bl = Catdisplayeditor.blockd.get(p.getUniqueId());
                    Quaternionf lelo = bl.getTransformation().getRightRotation();
                    AxisAngle4f ang = new AxisAngle4f();
                    lelo.get(ang);
                    if(ang.angle==0){
                        lelo.setAngleAxis(Math.PI, 0, 1, 0);
                    }
                    else {
                        lelo.setAngleAxis(ang.angle, 0, 1, 0);
                    }
                    bl.setTransformation(new Transformation(bl.getTransformation().getTranslation(), bl.getTransformation().getLeftRotation(), bl.getTransformation().getScale(), lelo));
                } else if (rawSlot == 25) {
                    BlockDisplay bl = Catdisplayeditor.blockd.get(p.getUniqueId());
                    Quaternionf lelo = bl.getTransformation().getRightRotation();
                    AxisAngle4f ang = new AxisAngle4f();
                    lelo.get(ang);
                    if(ang.angle==0){
                        lelo.setAngleAxis(Math.PI, 0, 0, 1);
                    }
                    else {
                        lelo.setAngleAxis(ang.angle, 0, 0, 1);
                    }
                    bl.setTransformation(new Transformation(bl.getTransformation().getTranslation(), bl.getTransformation().getLeftRotation(), bl.getTransformation().getScale(), lelo));
                }
            }
            e.setCancelled(true);
        }
    }

    public Boolean cancontinue(){
        return isgetnum;
    }

    @EventHandler
    public void Onmessage(AsyncPlayerChatEvent event) {

        Player player = event.getPlayer();
        UUID playerUUID = player.getUniqueId();

        // 检查玩家是否正在等待发送聊天消息
        if (playerChatMap.containsKey(playerUUID)) {
            // 获取玩家发送的聊天消息
            String message = event.getMessage();

            // 在这里处理玩家发送的消息，例如记录日志、发送消息给其他玩家等
            if(!canParseDouble(message)) {
                player.sendMessage(dataload.numE);
            }
            else {
                double numme = Double.parseDouble(message);
                Bukkit.getScheduler().runTaskLater(Catdisplayeditor.Plugin, () -> {
                            //player.sendMessage(playerChatMap.get(player.getUniqueId()));
                            //player.sendMessage(playerChatMap.get(player.getUniqueId()));
                            if (Objects.equals(playerChatMap.get(playerUUID), "setvalue")) {
                                //playernum.remove(playerUUID);
                                playernum.put(playerUUID, (float) numme);
                                player.sendMessage("已经更改值");
                                event.setCancelled(true);
                                //playerChatMap.remove(playerUUID);
                                Catdisplayeditor.openCowGUI(player, Catdisplayeditor.blockd.get(player.getUniqueId()));
                                //event.setCancelled(true);
                            } else if (Objects.equals(playerChatMap.get(playerUUID), "LocX")) {
                                Location dislocation = Catdisplayeditor.blockd.get(player.getUniqueId()).getLocation();
                                dislocation.add(numme, 0, 0);
                                Catdisplayeditor.blockd.get(player.getUniqueId()).teleport(dislocation);
                                Catdisplayeditor.openCowGUI(player, Catdisplayeditor.blockd.get(player.getUniqueId()));
                                event.setCancelled(true);
                            } else if (Objects.equals(playerChatMap.get(playerUUID), "LocY")) {
                                Location dislocation = Catdisplayeditor.blockd.get(player.getUniqueId()).getLocation();
                                dislocation.add(0, numme, 0);
                                Catdisplayeditor.blockd.get(player.getUniqueId()).teleport(dislocation);
                                Catdisplayeditor.openCowGUI(player, Catdisplayeditor.blockd.get(player.getUniqueId()));
                                event.setCancelled(true);
                            } else if (Objects.equals(playerChatMap.get(playerUUID), "LocZ")) {
                                Location dislocation = Catdisplayeditor.blockd.get(player.getUniqueId()).getLocation();
                                dislocation.add(0, 0, numme);
                                Catdisplayeditor.blockd.get(player.getUniqueId()).teleport(dislocation);
                                Catdisplayeditor.openCowGUI(player, Catdisplayeditor.blockd.get(player.getUniqueId()));
                                event.setCancelled(true);
                            } else if (Objects.equals(playerChatMap.get(playerUUID), "TarX")) {
                                Transformation transf = Catdisplayeditor.blockd.get(player.getUniqueId()).getTransformation();
                                Vector3f tarlocat = transf.getTranslation();
                                tarlocat.set((float) numme, tarlocat.y, tarlocat.z);
                                Transformation ntransf = new Transformation(tarlocat, transf.getLeftRotation(), transf.getScale(), transf.getRightRotation());
                                Catdisplayeditor.blockd.get(player.getUniqueId()).setTransformation(ntransf);
                                Catdisplayeditor.openCowGUI(player, Catdisplayeditor.blockd.get(player.getUniqueId()));
                            } else if (Objects.equals(playerChatMap.get(playerUUID), "TarY")) {
                                Transformation transf = Catdisplayeditor.blockd.get(player.getUniqueId()).getTransformation();
                                Vector3f tarlocat = transf.getTranslation();
                                tarlocat.set(tarlocat.x, (float) numme, tarlocat.z);
                                Transformation ntransf = new Transformation(tarlocat, transf.getLeftRotation(), transf.getScale(), transf.getRightRotation());
                                Catdisplayeditor.blockd.get(player.getUniqueId()).setTransformation(ntransf);
                                Catdisplayeditor.openCowGUI(player, Catdisplayeditor.blockd.get(player.getUniqueId()));
                            } else if (Objects.equals(playerChatMap.get(playerUUID), "TarZ")) {
                                Transformation transf = Catdisplayeditor.blockd.get(player.getUniqueId()).getTransformation();
                                Vector3f tarlocat = transf.getTranslation();
                                tarlocat.set(tarlocat.x, tarlocat.y, (float) numme);
                                Transformation ntransf = new Transformation(tarlocat, transf.getLeftRotation(), transf.getScale(), transf.getRightRotation());
                                Catdisplayeditor.blockd.get(player.getUniqueId()).setTransformation(ntransf);
                                Catdisplayeditor.openCowGUI(player, Catdisplayeditor.blockd.get(player.getUniqueId()));
                            } else if (Objects.equals(playerChatMap.get(playerUUID), "ScaX")) {
                                Transformation transf = Catdisplayeditor.blockd.get(player.getUniqueId()).getTransformation();
                                Vector3f tarlocat = transf.getScale();
                                tarlocat.set((float) numme, tarlocat.y, tarlocat.z);
                                Transformation ntransf = new Transformation(transf.getTranslation(), transf.getLeftRotation(), tarlocat, transf.getRightRotation());
                                Catdisplayeditor.blockd.get(player.getUniqueId()).setTransformation(ntransf);
                                Catdisplayeditor.openCowGUI(player, Catdisplayeditor.blockd.get(player.getUniqueId()));
                            } else if (Objects.equals(playerChatMap.get(playerUUID), "ScaY")) {
                                Transformation transf = Catdisplayeditor.blockd.get(player.getUniqueId()).getTransformation();
                                Vector3f tarlocat = transf.getScale();
                                tarlocat.set(tarlocat.x, (float) numme, tarlocat.z);
                                Transformation ntransf = new Transformation(transf.getTranslation(), transf.getLeftRotation(), tarlocat, transf.getRightRotation());
                                Catdisplayeditor.blockd.get(player.getUniqueId()).setTransformation(ntransf);
                                Catdisplayeditor.openCowGUI(player, Catdisplayeditor.blockd.get(player.getUniqueId()));
                            } else if (Objects.equals(playerChatMap.get(playerUUID), "ScaZ")) {
                                Transformation transf = Catdisplayeditor.blockd.get(player.getUniqueId()).getTransformation();
                                Vector3f tarlocat = transf.getScale();
                                tarlocat.set(tarlocat.x, tarlocat.y, (float) numme);
                                Transformation ntransf = new Transformation(transf.getTranslation(), transf.getLeftRotation(), tarlocat, transf.getRightRotation());
                                Catdisplayeditor.blockd.get(player.getUniqueId()).setTransformation(ntransf);
                                Catdisplayeditor.openCowGUI(player, Catdisplayeditor.blockd.get(player.getUniqueId()));
                            } else if (Objects.equals(playerChatMap.get(playerUUID), "ScaALL")) {
                                Transformation transf = Catdisplayeditor.blockd.get(player.getUniqueId()).getTransformation();
                                Vector3f tarlocat = transf.getScale();
                                tarlocat.set((float) numme, (float) numme, (float) numme);
                                Transformation ntransf = new Transformation(transf.getTranslation(), transf.getLeftRotation(), tarlocat, transf.getRightRotation());
                                Catdisplayeditor.blockd.get(player.getUniqueId()).setTransformation(ntransf);
                                Catdisplayeditor.openCowGUI(player, Catdisplayeditor.blockd.get(player.getUniqueId()));
                            } else if (Objects.equals(playerChatMap.get(playerUUID), "RoX")) {
                                float roY;
                                roY = (float) numme;
                                Catdisplayeditor.blockd.get(player.getUniqueId()).setRotation(roY, Catdisplayeditor.blockd.get(player.getUniqueId()).getLocation().getYaw());
                                Catdisplayeditor.openCowGUI(player, Catdisplayeditor.blockd.get(player.getUniqueId()));
                            } else if (Objects.equals(playerChatMap.get(playerUUID), "RoY")) {
                                float roY;
                                roY = (float) numme;
                                Catdisplayeditor.blockd.get(player.getUniqueId()).setRotation(Catdisplayeditor.blockd.get(player.getUniqueId()).getLocation().getPitch(), roY);
                                Catdisplayeditor.openCowGUI(player, Catdisplayeditor.blockd.get(player.getUniqueId()));
                            }
                            else if (Objects.equals(playerChatMap.get(playerUUID), "jiao")){
                                if(Objects.equals(setro.get(player.getUniqueId()), "ri")){
                                        float jiao;
                                        jiao = (float) numme;
                                        BlockDisplay bl = Catdisplayeditor.blockd.get(player.getUniqueId());
                                        Quaternionf lelo = bl.getTransformation().getRightRotation();
                                        AxisAngle4f ang = new AxisAngle4f();
                                        lelo.get(ang);
                                        //player.sendMessage(String.valueOf(ang));
                                        lelo.setAngleAxis(jiao/180*Math.PI, ang.x, ang.y, ang.z);
                                        bl.setTransformation(new Transformation(bl.getTransformation().getTranslation(), bl.getTransformation().getLeftRotation(), bl.getTransformation().getScale(), lelo));
                                    Catdisplayeditor.openCowGUI(player, Catdisplayeditor.blockd.get(player.getUniqueId()));
                                }
                                else if((Objects.equals(setro.get(player.getUniqueId()), "le"))){
                                    float jiao;
                                    jiao = (float) numme;
                                    BlockDisplay bl = Catdisplayeditor.blockd.get(player.getUniqueId());
                                    Quaternionf lelo = bl.getTransformation().getLeftRotation();
                                    AxisAngle4f ang = new AxisAngle4f();
                                    lelo.get(ang);
                                    //player.sendMessage(String.valueOf(ang));
                                    lelo.setAngleAxis(jiao/180*Math.PI, ang.x, ang.y, ang.z);
                                    //lelo.rotateLocalX((float) (jiao/180*Math.PI));
                                    //player.sendMessage(String.valueOf(lelo));
                                    bl.setTransformation(new Transformation(bl.getTransformation().getTranslation(), lelo, bl.getTransformation().getScale(), bl.getTransformation().getRightRotation()));
                                    Catdisplayeditor.openCowGUI(player, Catdisplayeditor.blockd.get(player.getUniqueId()));
                                }
                            }
                            else if (Objects.equals(playerChatMap.get(playerUUID), "numjiao")){
                                float jiao;
                                jiao = (float) numme;
                                MenuListener.playerjiao.put(player.getUniqueId(), jiao);
                                Catdisplayeditor.openCowGUI(player, Catdisplayeditor.blockd.get(player.getUniqueId()));
                            }
                            playerChatMap.remove(playerUUID);
                        },1);

                // 从等待发送聊天消息的Map中移除玩家
            }
            event.setCancelled(true);
            //e2.setCancelled(true);
        }
    }

    private boolean canPlace(Material material) {
        // 在这里实现你的逻辑，判断物品是否可以放置
        // 例如：检查物品是否是可放置的方块类型
        return material.isBlock();
    }
    public boolean canParseDouble(String input) {
        try {
            // 尝试将字符串转换为 double 类型
            Double.parseDouble(input);
            // 如果转换成功，则返回 true
            return true;
        } catch (NumberFormatException e) {
            // 如果转换失败，则返回 false
            return false;
        }
    }

    @EventHandler
    public void onchangeslot(PlayerItemHeldEvent event) {
        Player player = event.getPlayer();
        //player.sendMessage("你改变了物品栏");
        if (playeraction.containsKey(player.getUniqueId())) {
            BlockDisplay tarblock = Catdisplayeditor.blockd.get(player.getUniqueId());
            Float playervalue = playernum.get(player.getUniqueId());
            //修改XYZ坐标
            if (Objects.equals(playeraction.get(player.getUniqueId()), "LocX")) {
                if (event.getNewSlot() < 4) {
                    Location dislocation = tarblock.getLocation();
                    dislocation.add(-playervalue, 0, 0);
                    tarblock.teleport(dislocation);
                } else if (event.getNewSlot() > 4) {
                    Location dislocation = tarblock.getLocation();
                    dislocation.add(playervalue, 0, 0);
                    tarblock.teleport(dislocation);
                }
            } else if (Objects.equals(playeraction.get(player.getUniqueId()), "LocY")) {
                if (event.getNewSlot() < 4) {
                    Location dislocation = tarblock.getLocation();
                    dislocation.add(0, -playervalue, 0);
                    tarblock.teleport(dislocation);
                } else if (event.getNewSlot() > 4) {
                    Location dislocation = tarblock.getLocation();
                    dislocation.add(0, playervalue, 0);
                    tarblock.teleport(dislocation);
                }
            } else if (Objects.equals(playeraction.get(player.getUniqueId()), "LocZ")) {
                if (event.getNewSlot() < 4) {
                    Location dislocation = tarblock.getLocation();
                    dislocation.add(0, 0, -playervalue);
                    tarblock.teleport(dislocation);
                } else if (event.getNewSlot() > 4) {
                    Location dislocation = tarblock.getLocation();
                    dislocation.add(0, 0, playervalue);
                    tarblock.teleport(dislocation);
                }
            } else if (Objects.equals(playeraction.get(player.getUniqueId()), "TarX")) {
                if (event.getNewSlot() < 4) {
                    Transformation transf = Catdisplayeditor.blockd.get(player.getUniqueId()).getTransformation();
                    Vector3f tarlocat = transf.getTranslation();
                    tarlocat.add(-playervalue, 0, 0);
                    Transformation ntransf = new Transformation(tarlocat, transf.getLeftRotation(), transf.getScale(), transf.getRightRotation());
                    Catdisplayeditor.blockd.get(player.getUniqueId()).setTransformation(ntransf);
                } else if (event.getNewSlot() > 4) {
                    Transformation transf = Catdisplayeditor.blockd.get(player.getUniqueId()).getTransformation();
                    Vector3f tarlocat = transf.getTranslation();
                    tarlocat.add(playervalue, 0, 0);
                    Transformation ntransf = new Transformation(tarlocat, transf.getLeftRotation(), transf.getScale(), transf.getRightRotation());
                    Catdisplayeditor.blockd.get(player.getUniqueId()).setTransformation(ntransf);
                }
            } else if (Objects.equals(playeraction.get(player.getUniqueId()), "TarY")) {
                if (event.getNewSlot() < 4) {
                    Transformation transf = Catdisplayeditor.blockd.get(player.getUniqueId()).getTransformation();
                    Vector3f tarlocat = transf.getTranslation();
                    tarlocat.add(0, -playervalue, 0);
                    Transformation ntransf = new Transformation(tarlocat, transf.getLeftRotation(), transf.getScale(), transf.getRightRotation());
                    Catdisplayeditor.blockd.get(player.getUniqueId()).setTransformation(ntransf);
                } else if (event.getNewSlot() > 4) {
                    Transformation transf = Catdisplayeditor.blockd.get(player.getUniqueId()).getTransformation();
                    Vector3f tarlocat = transf.getTranslation();
                    tarlocat.add(0, playervalue, 0);
                    Transformation ntransf = new Transformation(tarlocat, transf.getLeftRotation(), transf.getScale(), transf.getRightRotation());
                    Catdisplayeditor.blockd.get(player.getUniqueId()).setTransformation(ntransf);
                }
            } else if (Objects.equals(playeraction.get(player.getUniqueId()), "TarZ")) {
                if (event.getNewSlot() < 4) {
                    Transformation transf = Catdisplayeditor.blockd.get(player.getUniqueId()).getTransformation();
                    Vector3f tarlocat = transf.getTranslation();
                    tarlocat.add(0, 0, -playervalue);
                    Transformation ntransf = new Transformation(tarlocat, transf.getLeftRotation(), transf.getScale(), transf.getRightRotation());
                    Catdisplayeditor.blockd.get(player.getUniqueId()).setTransformation(ntransf);
                } else if (event.getNewSlot() > 4) {
                    Transformation transf = Catdisplayeditor.blockd.get(player.getUniqueId()).getTransformation();
                    Vector3f tarlocat = transf.getTranslation();
                    tarlocat.add(0, 0, playervalue);
                    Transformation ntransf = new Transformation(tarlocat, transf.getLeftRotation(), transf.getScale(), transf.getRightRotation());
                    Catdisplayeditor.blockd.get(player.getUniqueId()).setTransformation(ntransf);
                }
            } else if (Objects.equals(playeraction.get(player.getUniqueId()), "ScaX")) {
                if (event.getNewSlot() < 4) {
                    Transformation transf = Catdisplayeditor.blockd.get(player.getUniqueId()).getTransformation();
                    Vector3f tarlocat = transf.getScale();
                    tarlocat.add(-playervalue, 0, 0);
                    Transformation ntransf = new Transformation(transf.getTranslation(), transf.getLeftRotation(), tarlocat, transf.getRightRotation());
                    Catdisplayeditor.blockd.get(player.getUniqueId()).setTransformation(ntransf);
                    //Catdisplayeditor.getInstance().openCowGUI(player, Catdisplayeditor.blockd.get(player.getUniqueId()));
                } else if (event.getNewSlot() > 4) {
                    Transformation transf = Catdisplayeditor.blockd.get(player.getUniqueId()).getTransformation();
                    Vector3f tarlocat = transf.getScale();
                    tarlocat.add(playervalue, 0, 0);
                    Transformation ntransf = new Transformation(transf.getTranslation(), transf.getLeftRotation(), tarlocat, transf.getRightRotation());
                    Catdisplayeditor.blockd.get(player.getUniqueId()).setTransformation(ntransf);
                    //Catdisplayeditor.getInstance().openCowGUI(player, Catdisplayeditor.blockd.get(player.getUniqueId()));
                }
            } else if (Objects.equals(playeraction.get(player.getUniqueId()), "ScaY")) {
                if (event.getNewSlot() < 4) {
                    Transformation transf = Catdisplayeditor.blockd.get(player.getUniqueId()).getTransformation();
                    Vector3f tarlocat = transf.getScale();
                    tarlocat.add(0, -playervalue, 0);
                    Transformation ntransf = new Transformation(transf.getTranslation(), transf.getLeftRotation(), tarlocat, transf.getRightRotation());
                    Catdisplayeditor.blockd.get(player.getUniqueId()).setTransformation(ntransf);
                    //Catdisplayeditor.getInstance().openCowGUI(player, Catdisplayeditor.blockd.get(player.getUniqueId()));
                } else if (event.getNewSlot() > 4) {
                    Transformation transf = Catdisplayeditor.blockd.get(player.getUniqueId()).getTransformation();
                    Vector3f tarlocat = transf.getScale();
                    tarlocat.add(0, playervalue, 0);
                    Transformation ntransf = new Transformation(transf.getTranslation(), transf.getLeftRotation(), tarlocat, transf.getRightRotation());
                    Catdisplayeditor.blockd.get(player.getUniqueId()).setTransformation(ntransf);
                    //Catdisplayeditor.getInstance().openCowGUI(player, Catdisplayeditor.blockd.get(player.getUniqueId()));
                }
            } else if (Objects.equals(playeraction.get(player.getUniqueId()), "ScaZ")) {
                if (event.getNewSlot() < 4) {
                    Transformation transf = Catdisplayeditor.blockd.get(player.getUniqueId()).getTransformation();
                    Vector3f tarlocat = transf.getScale();
                    tarlocat.add(0, 0, -playervalue);
                    Transformation ntransf = new Transformation(transf.getTranslation(), transf.getLeftRotation(), tarlocat, transf.getRightRotation());
                    Catdisplayeditor.blockd.get(player.getUniqueId()).setTransformation(ntransf);
                    //Catdisplayeditor.getInstance().openCowGUI(player, Catdisplayeditor.blockd.get(player.getUniqueId()));
                } else if (event.getNewSlot() > 4) {
                    Transformation transf = Catdisplayeditor.blockd.get(player.getUniqueId()).getTransformation();
                    Vector3f tarlocat = transf.getScale();
                    tarlocat.add(0, 0, playervalue);
                    Transformation ntransf = new Transformation(transf.getTranslation(), transf.getLeftRotation(), tarlocat, transf.getRightRotation());
                    Catdisplayeditor.blockd.get(player.getUniqueId()).setTransformation(ntransf);
                    //Catdisplayeditor.getInstance().openCowGUI(player, Catdisplayeditor.blockd.get(player.getUniqueId()));
                }
            } else if (Objects.equals(playeraction.get(player.getUniqueId()), "ScaALL")) {
                if (event.getNewSlot() < 4) {
                    Transformation transf = Catdisplayeditor.blockd.get(player.getUniqueId()).getTransformation();
                    Vector3f tarlocat = transf.getScale();
                    tarlocat.add(-playervalue, -playervalue, -playervalue);
                    Transformation ntransf = new Transformation(transf.getTranslation(), transf.getLeftRotation(), tarlocat, transf.getRightRotation());
                    Catdisplayeditor.blockd.get(player.getUniqueId()).setTransformation(ntransf);
                    //Catdisplayeditor.getInstance().openCowGUI(player, Catdisplayeditor.blockd.get(player.getUniqueId()));
                } else if (event.getNewSlot() > 4) {
                    Transformation transf = Catdisplayeditor.blockd.get(player.getUniqueId()).getTransformation();
                    Vector3f tarlocat = transf.getScale();
                    tarlocat.add(playervalue, playervalue, playervalue);
                    Transformation ntransf = new Transformation(transf.getTranslation(), transf.getLeftRotation(), tarlocat, transf.getRightRotation());
                    Catdisplayeditor.blockd.get(player.getUniqueId()).setTransformation(ntransf);
                    //Catdisplayeditor.getInstance().openCowGUI(player, Catdisplayeditor.blockd.get(player.getUniqueId()));
                }
            }
            else if (Objects.equals(playeraction.get(player.getUniqueId()), "jiao")) {
                if (Objects.equals(MenuListener.setro.get(player.getUniqueId()), "le")) {
                    if (event.getNewSlot() < 4) {
                        Transformation transf = Catdisplayeditor.blockd.get(player.getUniqueId()).getTransformation();
                        Quaternionf tarlocat = transf.getLeftRotation();
                        AxisAngle4f ang = new AxisAngle4f();
                        tarlocat.get(ang);
                        ang.set((float) (ang.angle-playerjiao.get(player.getUniqueId())/180*Math.PI),ang.x,ang.y,ang.z);
                        tarlocat.setAngleAxis(ang.angle,ang.x,ang.y,ang.z);
                        Transformation ntransf = new Transformation(transf.getTranslation(), tarlocat, transf.getScale(), transf.getRightRotation());
                        Catdisplayeditor.blockd.get(player.getUniqueId()).setTransformation(ntransf);
                        //Catdisplayeditor.getInstance().openCowGUI(player, Catdisplayeditor.blockd.get(player.getUniqueId()));
                    } else if (event.getNewSlot() > 4) {
                        Transformation transf = Catdisplayeditor.blockd.get(player.getUniqueId()).getTransformation();
                        Quaternionf tarlocat = transf.getLeftRotation();
                        AxisAngle4f ang = new AxisAngle4f();
                        tarlocat.get(ang);
                        ang.set((float) (ang.angle+playerjiao.get(player.getUniqueId())/180*Math.PI),ang.x,ang.y,ang.z);
                        tarlocat.setAngleAxis(ang.angle,ang.x,ang.y,ang.z);
                        Transformation ntransf = new Transformation(transf.getTranslation(), tarlocat, transf.getScale(), transf.getRightRotation());
                        Catdisplayeditor.blockd.get(player.getUniqueId()).setTransformation(ntransf);
                    }
                }
                else if (Objects.equals(MenuListener.setro.get(player.getUniqueId()), "ro")) {
                    if (Objects.equals(MenuListener.setro.get(player.getUniqueId()), "le")) {
                        if (event.getNewSlot() < 4) {
                            Transformation transf = Catdisplayeditor.blockd.get(player.getUniqueId()).getTransformation();
                            Quaternionf tarlocat = transf.getRightRotation();
                            AxisAngle4f ang = new AxisAngle4f();
                            tarlocat.get(ang);
                            ang.set((float) (ang.angle-playerjiao.get(player.getUniqueId())/180*Math.PI),ang.x,ang.y,ang.z);
                            tarlocat.setAngleAxis(ang.angle,ang.x,ang.y,ang.z);
                            Transformation ntransf = new Transformation(transf.getTranslation(), transf.getLeftRotation(), transf.getScale(), tarlocat);
                            Catdisplayeditor.blockd.get(player.getUniqueId()).setTransformation(ntransf);
                            //Catdisplayeditor.getInstance().openCowGUI(player, Catdisplayeditor.blockd.get(player.getUniqueId()));
                        } else if (event.getNewSlot() > 4) {
                            Transformation transf = Catdisplayeditor.blockd.get(player.getUniqueId()).getTransformation();
                            Quaternionf tarlocat = transf.getRightRotation();
                            AxisAngle4f ang = new AxisAngle4f();
                            tarlocat.get(ang);
                            ang.set((float) (ang.angle+playerjiao.get(player.getUniqueId())/180*Math.PI),ang.x,ang.y,ang.z);
                            tarlocat.setAngleAxis(ang.angle,ang.x,ang.y,ang.z);
                            Transformation ntransf = new Transformation(transf.getTranslation(), transf.getLeftRotation(), transf.getScale(), tarlocat);
                            Catdisplayeditor.blockd.get(player.getUniqueId()).setTransformation(ntransf);
                        }
                    }
                }
            }
            event.setCancelled(true);
        }
    }
}
