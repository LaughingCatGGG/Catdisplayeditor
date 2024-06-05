package org.catplugin.catdisplayeditor;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Light;
import org.bukkit.entity.BlockDisplay;
import org.bukkit.entity.Display;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.CrafterInventory;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.util.Transformation;
import org.catplugin.catdisplayeditorymal.dataload;
import org.joml.Vector3f;

import java.util.*;

import static org.bukkit.event.inventory.ClickType.MIDDLE;

class MenuListener implements Listener {
    private Map<UUID, String> playerChatMap = new HashMap<>();
    private static double num = 0;
    private static boolean isgetnum = false;
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
                Catdisplayeditor.getInstance().openCowGUI(p, Catdisplayeditor.blockd.get(p.getUniqueId()));
                //更新菜单
            }//更换物品格
            else if (rawSlot == 5) {
                if (e.isLeftClick() && !e.isShiftClick()) {
                    Location dislocation = Catdisplayeditor.blockd.get(p.getUniqueId()).getLocation();
                    dislocation.add(0.1, 0, 0);
                    Catdisplayeditor.blockd.get(p.getUniqueId()).teleport(dislocation);
                    Catdisplayeditor.getInstance().openCowGUI(p, Catdisplayeditor.blockd.get(p.getUniqueId()));
                } else if (e.isRightClick() && !e.isShiftClick()) {
                    Location dislocation = Catdisplayeditor.blockd.get(p.getUniqueId()).getLocation();
                    dislocation.add(-0.1, 0, 0);
                    Catdisplayeditor.blockd.get(p.getUniqueId()).teleport(dislocation);
                    Catdisplayeditor.getInstance().openCowGUI(p, Catdisplayeditor.blockd.get(p.getUniqueId()));
                } else if (e.isLeftClick() && e.isShiftClick()) {
                    Location dislocation = Catdisplayeditor.blockd.get(p.getUniqueId()).getLocation();
                    dislocation.add(1, 0, 0);
                    Catdisplayeditor.blockd.get(p.getUniqueId()).teleport(dislocation);
                    Catdisplayeditor.getInstance().openCowGUI(p, Catdisplayeditor.blockd.get(p.getUniqueId()));
                } else if (e.isRightClick() && e.isShiftClick()) {
                    Location dislocation = Catdisplayeditor.blockd.get(p.getUniqueId()).getLocation();
                    dislocation.add(-1, 0, 0);
                    Catdisplayeditor.blockd.get(p.getUniqueId()).teleport(dislocation);
                    Catdisplayeditor.getInstance().openCowGUI(p, Catdisplayeditor.blockd.get(p.getUniqueId()));
                } else if (e.getClick() == MIDDLE) {
                    p.closeInventory();
                    p.sendMessage(dataload.printnum);
                    playerChatMap.put(p.getUniqueId(), "");
                    Location dislocation = Catdisplayeditor.blockd.get(p.getUniqueId()).getLocation();
                    while (!isgetnum) {
                        // 等待isgetnum变为true
                        try {
                            Thread.sleep(100); // 避免CPU过度占用
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }
                    }
                    dislocation.add(num, 0, 0);
                    isgetnum = false;
                    num = 0;
                    Catdisplayeditor.blockd.get(p.getUniqueId()).teleport(dislocation);
                    Catdisplayeditor.getInstance().openCowGUI(p, Catdisplayeditor.blockd.get(p.getUniqueId()));
                } else {
                }
            } else if (rawSlot == 6) {
                if (e.isLeftClick() && !e.isShiftClick()) {
                    Location dislocation = Catdisplayeditor.blockd.get(p.getUniqueId()).getLocation();
                    dislocation.add(0, 0.1, 0);
                    Catdisplayeditor.blockd.get(p.getUniqueId()).teleport(dislocation);
                    Catdisplayeditor.getInstance().openCowGUI(p, Catdisplayeditor.blockd.get(p.getUniqueId()));
                } else if (e.isRightClick() && !e.isShiftClick()) {
                    Location dislocation = Catdisplayeditor.blockd.get(p.getUniqueId()).getLocation();
                    dislocation.add(0, -0.1, 0);
                    Catdisplayeditor.blockd.get(p.getUniqueId()).teleport(dislocation);
                    Catdisplayeditor.getInstance().openCowGUI(p, Catdisplayeditor.blockd.get(p.getUniqueId()));
                } else if (e.isLeftClick() && e.isShiftClick()) {
                    Location dislocation = Catdisplayeditor.blockd.get(p.getUniqueId()).getLocation();
                    dislocation.add(0, 1, 0);
                    Catdisplayeditor.blockd.get(p.getUniqueId()).teleport(dislocation);
                    Catdisplayeditor.getInstance().openCowGUI(p, Catdisplayeditor.blockd.get(p.getUniqueId()));
                } else if (e.isRightClick() && e.isShiftClick()) {
                    Location dislocation = Catdisplayeditor.blockd.get(p.getUniqueId()).getLocation();
                    dislocation.add(0, -1, 0);
                    Catdisplayeditor.blockd.get(p.getUniqueId()).teleport(dislocation);
                    Catdisplayeditor.getInstance().openCowGUI(p, Catdisplayeditor.blockd.get(p.getUniqueId()));
                } else if (e.getClick() == MIDDLE) {
                    p.closeInventory();
                    p.sendMessage(dataload.printnum);
                    playerChatMap.put(p.getUniqueId(), "");
                    Location dislocation = Catdisplayeditor.blockd.get(p.getUniqueId()).getLocation();
                    while (!isgetnum) {
                        // 等待isgetnum变为true
                        try {
                            Thread.sleep(100); // 避免CPU过度占用
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }
                    }
                    dislocation.add(0, num, 0);
                    isgetnum = false;
                    num = 0;
                    Catdisplayeditor.blockd.get(p.getUniqueId()).teleport(dislocation);
                    Catdisplayeditor.getInstance().openCowGUI(p, Catdisplayeditor.blockd.get(p.getUniqueId()));
                } else {
                }
            } else if (rawSlot == 7) {
                if (e.isLeftClick() && !e.isShiftClick()) {
                    Location dislocation = Catdisplayeditor.blockd.get(p.getUniqueId()).getLocation();
                    dislocation.add(0, 0, 0.1);
                    Catdisplayeditor.blockd.get(p.getUniqueId()).teleport(dislocation);
                    Catdisplayeditor.getInstance().openCowGUI(p, Catdisplayeditor.blockd.get(p.getUniqueId()));
                } else if (e.isRightClick() && !e.isShiftClick()) {
                    Location dislocation = Catdisplayeditor.blockd.get(p.getUniqueId()).getLocation();
                    dislocation.add(0, 0, -0.1);
                    Catdisplayeditor.blockd.get(p.getUniqueId()).teleport(dislocation);
                    Catdisplayeditor.getInstance().openCowGUI(p, Catdisplayeditor.blockd.get(p.getUniqueId()));
                } else if (e.isLeftClick() && e.isShiftClick()) {
                    Location dislocation = Catdisplayeditor.blockd.get(p.getUniqueId()).getLocation();
                    dislocation.add(0, 0, 1);
                    Catdisplayeditor.blockd.get(p.getUniqueId()).teleport(dislocation);
                    Catdisplayeditor.getInstance().openCowGUI(p, Catdisplayeditor.blockd.get(p.getUniqueId()));
                } else if (e.isRightClick() && e.isShiftClick()) {
                    Location dislocation = Catdisplayeditor.blockd.get(p.getUniqueId()).getLocation();
                    dislocation.add(0, 0, -1);
                    Catdisplayeditor.blockd.get(p.getUniqueId()).teleport(dislocation);
                    Catdisplayeditor.getInstance().openCowGUI(p, Catdisplayeditor.blockd.get(p.getUniqueId()));
                } else if (e.getClick() == MIDDLE) {
                    p.closeInventory();
                    p.sendMessage(dataload.printnum);
                    playerChatMap.put(p.getUniqueId(), "");
                    Location dislocation = Catdisplayeditor.blockd.get(p.getUniqueId()).getLocation();
                    while (!isgetnum) {
                        // 等待isgetnum变为true
                        try {
                            Thread.sleep(100); // 避免CPU过度占用
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }
                    }
                    dislocation.add(0, 0, num);
                    isgetnum = false;
                    num = 0;
                    Catdisplayeditor.blockd.get(p.getUniqueId()).teleport(dislocation);
                    Catdisplayeditor.getInstance().openCowGUI(p, Catdisplayeditor.blockd.get(p.getUniqueId()));
                } else {
                }
            }
            //XYZ坐标
            else if (rawSlot == 20) {
                if (e.isLeftClick() && !e.isShiftClick()) {
                    Transformation transf = Catdisplayeditor.blockd.get(p.getUniqueId()).getTransformation();
                    Vector3f tarlocat = transf.getTranslation();
                    tarlocat.add(0.1F, 0, 0);
                    Transformation ntransf = new Transformation(tarlocat, transf.getLeftRotation(), transf.getScale(), transf.getRightRotation());
                    Catdisplayeditor.blockd.get(p.getUniqueId()).setTransformation(ntransf);
                    //p.sendMessage(String.valueOf(ntransf));
                    Catdisplayeditor.getInstance().openCowGUI(p, Catdisplayeditor.blockd.get(p.getUniqueId()));
                } else if (e.isRightClick() && !e.isShiftClick()) {
                    Transformation transf = Catdisplayeditor.blockd.get(p.getUniqueId()).getTransformation();
                    Vector3f tarlocat = transf.getTranslation();
                    tarlocat.add(-0.1F, 0, 0);
                    Transformation ntransf = new Transformation(tarlocat, transf.getLeftRotation(), transf.getScale(), transf.getRightRotation());
                    Catdisplayeditor.blockd.get(p.getUniqueId()).setTransformation(ntransf);
                    Catdisplayeditor.getInstance().openCowGUI(p, Catdisplayeditor.blockd.get(p.getUniqueId()));
                } else if (e.isLeftClick() && e.isShiftClick()) {
                    Transformation transf = Catdisplayeditor.blockd.get(p.getUniqueId()).getTransformation();
                    Vector3f tarlocat = transf.getTranslation();
                    tarlocat.add(1F, 0, 0);
                    Transformation ntransf = new Transformation(tarlocat, transf.getLeftRotation(), transf.getScale(), transf.getRightRotation());
                    Catdisplayeditor.blockd.get(p.getUniqueId()).setTransformation(ntransf);
                    Catdisplayeditor.getInstance().openCowGUI(p, Catdisplayeditor.blockd.get(p.getUniqueId()));
                } else if (e.isRightClick() && e.isShiftClick()) {
                    Transformation transf = Catdisplayeditor.blockd.get(p.getUniqueId()).getTransformation();
                    Vector3f tarlocat = transf.getTranslation();
                    tarlocat.add(-1F, 0, 0);
                    Transformation ntransf = new Transformation(tarlocat, transf.getLeftRotation(), transf.getScale(), transf.getRightRotation());
                    Catdisplayeditor.blockd.get(p.getUniqueId()).setTransformation(ntransf);
                    Catdisplayeditor.getInstance().openCowGUI(p, Catdisplayeditor.blockd.get(p.getUniqueId()));
                } else if (e.getClick() == MIDDLE) {
                    p.closeInventory();
                    p.sendMessage(dataload.printnum);
                    playerChatMap.put(p.getUniqueId(), "");
                    while (!isgetnum) {
                        // 等待isgetnum变为true
                        try {
                            Thread.sleep(100); // 避免CPU过度占用
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }
                    }
                    Transformation transf = Catdisplayeditor.blockd.get(p.getUniqueId()).getTransformation();
                    Vector3f tarlocat = transf.getTranslation();
                    tarlocat.set((float) num, tarlocat.y, tarlocat.z);
                    Transformation ntransf = new Transformation(tarlocat, transf.getLeftRotation(), transf.getScale(), transf.getRightRotation());
                    Catdisplayeditor.blockd.get(p.getUniqueId()).setTransformation(ntransf);
                    isgetnum = false;
                    num = 0;
                    Catdisplayeditor.getInstance().openCowGUI(p, Catdisplayeditor.blockd.get(p.getUniqueId()));
                }
            } else if (rawSlot == 21) {
                if (e.isLeftClick() && !e.isShiftClick()) {
                    Transformation transf = Catdisplayeditor.blockd.get(p.getUniqueId()).getTransformation();
                    Vector3f tarlocat = transf.getTranslation();
                    tarlocat.add(0, 0.1F, 0);
                    Transformation ntransf = new Transformation(tarlocat, transf.getLeftRotation(), transf.getScale(), transf.getRightRotation());
                    Catdisplayeditor.blockd.get(p.getUniqueId()).setTransformation(ntransf);
                    //p.sendMessage(String.valueOf(ntransf));
                    Catdisplayeditor.getInstance().openCowGUI(p, Catdisplayeditor.blockd.get(p.getUniqueId()));
                } else if (e.isRightClick() && !e.isShiftClick()) {
                    Transformation transf = Catdisplayeditor.blockd.get(p.getUniqueId()).getTransformation();
                    Vector3f tarlocat = transf.getTranslation();
                    tarlocat.add(0, -0.1F, 0);
                    Transformation ntransf = new Transformation(tarlocat, transf.getLeftRotation(), transf.getScale(), transf.getRightRotation());
                    Catdisplayeditor.blockd.get(p.getUniqueId()).setTransformation(ntransf);
                    Catdisplayeditor.getInstance().openCowGUI(p, Catdisplayeditor.blockd.get(p.getUniqueId()));
                } else if (e.isLeftClick() && e.isShiftClick()) {
                    Transformation transf = Catdisplayeditor.blockd.get(p.getUniqueId()).getTransformation();
                    Vector3f tarlocat = transf.getTranslation();
                    tarlocat.add(0, 1F, 0);
                    Transformation ntransf = new Transformation(tarlocat, transf.getLeftRotation(), transf.getScale(), transf.getRightRotation());
                    Catdisplayeditor.blockd.get(p.getUniqueId()).setTransformation(ntransf);
                    Catdisplayeditor.getInstance().openCowGUI(p, Catdisplayeditor.blockd.get(p.getUniqueId()));
                } else if (e.isRightClick() && e.isShiftClick()) {
                    Transformation transf = Catdisplayeditor.blockd.get(p.getUniqueId()).getTransformation();
                    Vector3f tarlocat = transf.getTranslation();
                    tarlocat.add(0, -1F, 0);
                    Transformation ntransf = new Transformation(tarlocat, transf.getLeftRotation(), transf.getScale(), transf.getRightRotation());
                    Catdisplayeditor.blockd.get(p.getUniqueId()).setTransformation(ntransf);
                    Catdisplayeditor.getInstance().openCowGUI(p, Catdisplayeditor.blockd.get(p.getUniqueId()));
                } else if (e.getClick() == MIDDLE) {
                    p.closeInventory();
                    p.sendMessage(dataload.printnum);
                    playerChatMap.put(p.getUniqueId(), "");
                    while (!isgetnum) {
                        // 等待isgetnum变为true
                        try {
                            Thread.sleep(100); // 避免CPU过度占用
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }
                    }
                    Transformation transf = Catdisplayeditor.blockd.get(p.getUniqueId()).getTransformation();
                    Vector3f tarlocat = transf.getTranslation();
                    tarlocat.set(tarlocat.x, (float) num, tarlocat.z);
                    Transformation ntransf = new Transformation(tarlocat, transf.getLeftRotation(), transf.getScale(), transf.getRightRotation());
                    Catdisplayeditor.blockd.get(p.getUniqueId()).setTransformation(ntransf);
                    isgetnum = false;
                    num = 0;
                    Catdisplayeditor.getInstance().openCowGUI(p, Catdisplayeditor.blockd.get(p.getUniqueId()));
                }
            } else if (rawSlot == 22) {
                if (e.isLeftClick() && !e.isShiftClick()) {
                    Transformation transf = Catdisplayeditor.blockd.get(p.getUniqueId()).getTransformation();
                    Vector3f tarlocat = transf.getTranslation();
                    tarlocat.add(0, 0, 0.1F);
                    Transformation ntransf = new Transformation(tarlocat, transf.getLeftRotation(), transf.getScale(), transf.getRightRotation());
                    Catdisplayeditor.blockd.get(p.getUniqueId()).setTransformation(ntransf);
                    //p.sendMessage(String.valueOf(ntransf));
                    Catdisplayeditor.getInstance().openCowGUI(p, Catdisplayeditor.blockd.get(p.getUniqueId()));
                } else if (e.isRightClick() && !e.isShiftClick()) {
                    Transformation transf = Catdisplayeditor.blockd.get(p.getUniqueId()).getTransformation();
                    Vector3f tarlocat = transf.getTranslation();
                    tarlocat.add(0, 0, -0.1F);
                    Transformation ntransf = new Transformation(tarlocat, transf.getLeftRotation(), transf.getScale(), transf.getRightRotation());
                    Catdisplayeditor.blockd.get(p.getUniqueId()).setTransformation(ntransf);
                    Catdisplayeditor.getInstance().openCowGUI(p, Catdisplayeditor.blockd.get(p.getUniqueId()));
                } else if (e.isLeftClick() && e.isShiftClick()) {
                    Transformation transf = Catdisplayeditor.blockd.get(p.getUniqueId()).getTransformation();
                    Vector3f tarlocat = transf.getTranslation();
                    tarlocat.add(0, 0, 1F);
                    Transformation ntransf = new Transformation(tarlocat, transf.getLeftRotation(), transf.getScale(), transf.getRightRotation());
                    Catdisplayeditor.blockd.get(p.getUniqueId()).setTransformation(ntransf);
                    Catdisplayeditor.getInstance().openCowGUI(p, Catdisplayeditor.blockd.get(p.getUniqueId()));
                } else if (e.isRightClick() && e.isShiftClick()) {
                    Transformation transf = Catdisplayeditor.blockd.get(p.getUniqueId()).getTransformation();
                    Vector3f tarlocat = transf.getTranslation();
                    tarlocat.add(0, 0, -1F);
                    Transformation ntransf = new Transformation(tarlocat, transf.getLeftRotation(), transf.getScale(), transf.getRightRotation());
                    Catdisplayeditor.blockd.get(p.getUniqueId()).setTransformation(ntransf);
                    Catdisplayeditor.getInstance().openCowGUI(p, Catdisplayeditor.blockd.get(p.getUniqueId()));
                }
                else if (e.getClick() == MIDDLE) {
                    p.closeInventory();
                    p.sendMessage(dataload.printnum);
                    playerChatMap.put(p.getUniqueId(), "");
                    while (!isgetnum) {
                        // 等待isgetnum变为true
                        try {
                            Thread.sleep(100); // 避免CPU过度占用
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }
                    }
                    Transformation transf = Catdisplayeditor.blockd.get(p.getUniqueId()).getTransformation();
                    Vector3f tarlocat = transf.getTranslation();
                    tarlocat.set(tarlocat.x, tarlocat.y, (float) num);
                    Transformation ntransf = new Transformation(tarlocat, transf.getLeftRotation(), transf.getScale(), transf.getRightRotation());
                    Catdisplayeditor.blockd.get(p.getUniqueId()).setTransformation(ntransf);
                    isgetnum = false;
                    num = 0;
                    Catdisplayeditor.getInstance().openCowGUI(p, Catdisplayeditor.blockd.get(p.getUniqueId()));
                }
            }//相对生成坐标
            else if (rawSlot == 25) {
                Transformation transf = Catdisplayeditor.blockd.get(p.getUniqueId()).getTransformation();
                Vector3f tarlocat = transf.getTranslation();
                float vx = -transf.getScale().x() / 2;
                float vy = -transf.getScale().y() / 2;
                float vz = -transf.getScale().y() / 2;
                tarlocat.set(vx, vy, vz);
                Transformation ntransf = new Transformation(tarlocat, transf.getLeftRotation(), transf.getScale(), transf.getRightRotation());
                Catdisplayeditor.blockd.get(p.getUniqueId()).setTransformation(ntransf);
                Catdisplayeditor.getInstance().openCowGUI(p, Catdisplayeditor.blockd.get(p.getUniqueId()));
            }//中心点
            else if (rawSlot == 29) {
                if (e.isLeftClick() && !e.isShiftClick()) {
                    Transformation transf = Catdisplayeditor.blockd.get(p.getUniqueId()).getTransformation();
                    Vector3f tarlocat = transf.getScale();
                    tarlocat.add(0.01F, 0, 0);
                    Transformation ntransf = new Transformation(transf.getTranslation(), transf.getLeftRotation(), tarlocat, transf.getRightRotation());
                    Catdisplayeditor.blockd.get(p.getUniqueId()).setTransformation(ntransf);
                    Catdisplayeditor.getInstance().openCowGUI(p, Catdisplayeditor.blockd.get(p.getUniqueId()));
                }
                else if (e.isRightClick() && !e.isShiftClick()) {
                    Transformation transf = Catdisplayeditor.blockd.get(p.getUniqueId()).getTransformation();
                    Vector3f tarlocat = transf.getScale();
                    tarlocat.add(-0.01F, 0, 0);
                    Transformation ntransf = new Transformation(transf.getTranslation(), transf.getLeftRotation(), tarlocat, transf.getRightRotation());
                    Catdisplayeditor.blockd.get(p.getUniqueId()).setTransformation(ntransf);
                    Catdisplayeditor.getInstance().openCowGUI(p, Catdisplayeditor.blockd.get(p.getUniqueId()));
                }
                else if (e.isLeftClick() && e.isShiftClick()) {
                    Transformation transf = Catdisplayeditor.blockd.get(p.getUniqueId()).getTransformation();
                    Vector3f tarlocat = transf.getScale();
                    tarlocat.add(0.1F, 0, 0);
                    Transformation ntransf = new Transformation(transf.getTranslation(), transf.getLeftRotation(), tarlocat, transf.getRightRotation());
                    Catdisplayeditor.blockd.get(p.getUniqueId()).setTransformation(ntransf);
                    Catdisplayeditor.getInstance().openCowGUI(p, Catdisplayeditor.blockd.get(p.getUniqueId()));
                }
                else if (e.isLeftClick() && e.isShiftClick()) {
                    Transformation transf = Catdisplayeditor.blockd.get(p.getUniqueId()).getTransformation();
                    Vector3f tarlocat = transf.getScale();
                    tarlocat.add(-0.1F, 0, 0);
                    Transformation ntransf = new Transformation(transf.getTranslation(), transf.getLeftRotation(), tarlocat, transf.getRightRotation());
                    Catdisplayeditor.blockd.get(p.getUniqueId()).setTransformation(ntransf);
                    Catdisplayeditor.getInstance().openCowGUI(p, Catdisplayeditor.blockd.get(p.getUniqueId()));
                }
                else if (e.getClick() == MIDDLE) {
                    p.closeInventory();
                    p.sendMessage(dataload.printnum);
                    playerChatMap.put(p.getUniqueId(), "");
                    while (!isgetnum) {
                        // 等待isgetnum变为true
                        try {
                            Thread.sleep(100); // 避免CPU过度占用
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }
                    }
                    Transformation transf = Catdisplayeditor.blockd.get(p.getUniqueId()).getTransformation();
                    Vector3f tarlocat = transf.getScale();
                    tarlocat.set((float) num, tarlocat.y, tarlocat.z);
                    Transformation ntransf = new Transformation(transf.getTranslation(), transf.getLeftRotation(), tarlocat, transf.getRightRotation());
                    Catdisplayeditor.blockd.get(p.getUniqueId()).setTransformation(ntransf);
                    isgetnum = false;
                    num = 0;
                    Catdisplayeditor.getInstance().openCowGUI(p, Catdisplayeditor.blockd.get(p.getUniqueId()));
                }
            } else if (rawSlot == 30) {
                if (e.isLeftClick() && !e.isShiftClick()) {
                    Transformation transf = Catdisplayeditor.blockd.get(p.getUniqueId()).getTransformation();
                    Vector3f tarlocat = transf.getScale();
                    tarlocat.add(0, 0.01F, 0);
                    Transformation ntransf = new Transformation(transf.getTranslation(), transf.getLeftRotation(), tarlocat, transf.getRightRotation());
                    Catdisplayeditor.blockd.get(p.getUniqueId()).setTransformation(ntransf);
                    Catdisplayeditor.getInstance().openCowGUI(p, Catdisplayeditor.blockd.get(p.getUniqueId()));
                }
                else if (e.isRightClick() && !e.isShiftClick()) {
                    Transformation transf = Catdisplayeditor.blockd.get(p.getUniqueId()).getTransformation();
                    Vector3f tarlocat = transf.getScale();
                    tarlocat.add(0, -0.01F, 0);
                    Transformation ntransf = new Transformation(transf.getTranslation(), transf.getLeftRotation(), tarlocat, transf.getRightRotation());
                    Catdisplayeditor.blockd.get(p.getUniqueId()).setTransformation(ntransf);
                    Catdisplayeditor.getInstance().openCowGUI(p, Catdisplayeditor.blockd.get(p.getUniqueId()));
                }
                else if (e.isLeftClick() && e.isShiftClick()) {
                    Transformation transf = Catdisplayeditor.blockd.get(p.getUniqueId()).getTransformation();
                    Vector3f tarlocat = transf.getScale();
                    tarlocat.add(0, 0.1F, 0);
                    Transformation ntransf = new Transformation(transf.getTranslation(), transf.getLeftRotation(), tarlocat, transf.getRightRotation());
                    Catdisplayeditor.blockd.get(p.getUniqueId()).setTransformation(ntransf);
                    Catdisplayeditor.getInstance().openCowGUI(p, Catdisplayeditor.blockd.get(p.getUniqueId()));
                }
                else if (e.isLeftClick() && e.isShiftClick()) {
                    Transformation transf = Catdisplayeditor.blockd.get(p.getUniqueId()).getTransformation();
                    Vector3f tarlocat = transf.getScale();
                    tarlocat.add(0, -0.1F, 0);
                    Transformation ntransf = new Transformation(transf.getTranslation(), transf.getLeftRotation(), tarlocat, transf.getRightRotation());
                    Catdisplayeditor.blockd.get(p.getUniqueId()).setTransformation(ntransf);
                    Catdisplayeditor.getInstance().openCowGUI(p, Catdisplayeditor.blockd.get(p.getUniqueId()));
                }
                else if (e.getClick() == MIDDLE) {
                    p.closeInventory();
                    p.sendMessage(dataload.printnum);
                    playerChatMap.put(p.getUniqueId(), "");
                    while (!isgetnum) {
                        // 等待isgetnum变为true
                        try {
                            Thread.sleep(100); // 避免CPU过度占用
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }
                    }
                    Transformation transf = Catdisplayeditor.blockd.get(p.getUniqueId()).getTransformation();
                    Vector3f tarlocat = transf.getScale();
                    tarlocat.set(tarlocat.x, (float) num, tarlocat.z);
                    Transformation ntransf = new Transformation(transf.getTranslation(), transf.getLeftRotation(), tarlocat, transf.getRightRotation());
                    Catdisplayeditor.blockd.get(p.getUniqueId()).setTransformation(ntransf);
                    isgetnum = false;
                    num = 0;
                    Catdisplayeditor.getInstance().openCowGUI(p, Catdisplayeditor.blockd.get(p.getUniqueId()));
                }
            } else if (rawSlot == 31) {
                if (e.isLeftClick() && !e.isShiftClick()) {
                    Transformation transf = Catdisplayeditor.blockd.get(p.getUniqueId()).getTransformation();
                    Vector3f tarlocat = transf.getScale();
                    tarlocat.add(0, 0, 0.01F);
                    Transformation ntransf = new Transformation(transf.getTranslation(), transf.getLeftRotation(), tarlocat, transf.getRightRotation());
                    Catdisplayeditor.blockd.get(p.getUniqueId()).setTransformation(ntransf);
                    Catdisplayeditor.getInstance().openCowGUI(p, Catdisplayeditor.blockd.get(p.getUniqueId()));
                }
                else if (e.isRightClick() && !e.isShiftClick()) {
                    Transformation transf = Catdisplayeditor.blockd.get(p.getUniqueId()).getTransformation();
                    Vector3f tarlocat = transf.getScale();
                    tarlocat.add(0, 0, -0.01F);
                    Transformation ntransf = new Transformation(transf.getTranslation(), transf.getLeftRotation(), tarlocat, transf.getRightRotation());
                    Catdisplayeditor.blockd.get(p.getUniqueId()).setTransformation(ntransf);
                    Catdisplayeditor.getInstance().openCowGUI(p, Catdisplayeditor.blockd.get(p.getUniqueId()));
                }
                else if (e.isLeftClick() && e.isShiftClick()) {
                    Transformation transf = Catdisplayeditor.blockd.get(p.getUniqueId()).getTransformation();
                    Vector3f tarlocat = transf.getScale();
                    tarlocat.add(0, 0, 0.1F);
                    Transformation ntransf = new Transformation(transf.getTranslation(), transf.getLeftRotation(), tarlocat, transf.getRightRotation());
                    Catdisplayeditor.blockd.get(p.getUniqueId()).setTransformation(ntransf);
                    Catdisplayeditor.getInstance().openCowGUI(p, Catdisplayeditor.blockd.get(p.getUniqueId()));
                }
                else if (e.isLeftClick() && e.isShiftClick()) {
                    Transformation transf = Catdisplayeditor.blockd.get(p.getUniqueId()).getTransformation();
                    Vector3f tarlocat = transf.getScale();
                    tarlocat.add(0, 0, -0.1F);
                    Transformation ntransf = new Transformation(transf.getTranslation(), transf.getLeftRotation(), tarlocat, transf.getRightRotation());
                    Catdisplayeditor.blockd.get(p.getUniqueId()).setTransformation(ntransf);
                    Catdisplayeditor.getInstance().openCowGUI(p, Catdisplayeditor.blockd.get(p.getUniqueId()));
                }
                else if (e.getClick() == MIDDLE) {
                    p.closeInventory();
                    p.sendMessage(dataload.printnum);
                    playerChatMap.put(p.getUniqueId(), "");
                    while (!isgetnum) {
                        // 等待isgetnum变为true
                        try {
                            Thread.sleep(100); // 避免CPU过度占用
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }
                    }
                    Transformation transf = Catdisplayeditor.blockd.get(p.getUniqueId()).getTransformation();
                    Vector3f tarlocat = transf.getScale();
                    tarlocat.set(tarlocat.x, tarlocat.y, (float) num);
                    Transformation ntransf = new Transformation(transf.getTranslation(), transf.getLeftRotation(), tarlocat, transf.getRightRotation());
                    Catdisplayeditor.blockd.get(p.getUniqueId()).setTransformation(ntransf);
                    isgetnum = false;
                    num = 0;
                    Catdisplayeditor.getInstance().openCowGUI(p, Catdisplayeditor.blockd.get(p.getUniqueId()));
                }
            }//大小
            else if (rawSlot == 34) {
                if (e.isLeftClick() && !e.isShiftClick()) {
                    Transformation transf = Catdisplayeditor.blockd.get(p.getUniqueId()).getTransformation();
                    Vector3f tarlocat = transf.getScale();
                    tarlocat.add(0.01F, 0.01F, 0.01F);
                    Transformation ntransf = new Transformation(transf.getTranslation(), transf.getLeftRotation(), tarlocat, transf.getRightRotation());
                    Catdisplayeditor.blockd.get(p.getUniqueId()).setTransformation(ntransf);
                    Catdisplayeditor.getInstance().openCowGUI(p, Catdisplayeditor.blockd.get(p.getUniqueId()));
                }
                else if (e.isRightClick() && !e.isShiftClick()) {
                    Transformation transf = Catdisplayeditor.blockd.get(p.getUniqueId()).getTransformation();
                    Vector3f tarlocat = transf.getScale();
                    tarlocat.add(-0.01F, -0.01F, -0.01F);
                    Transformation ntransf = new Transformation(transf.getTranslation(), transf.getLeftRotation(), tarlocat, transf.getRightRotation());
                    Catdisplayeditor.blockd.get(p.getUniqueId()).setTransformation(ntransf);
                    Catdisplayeditor.getInstance().openCowGUI(p, Catdisplayeditor.blockd.get(p.getUniqueId()));
                }
                else if (e.isLeftClick() && e.isShiftClick()) {
                    Transformation transf = Catdisplayeditor.blockd.get(p.getUniqueId()).getTransformation();
                    Vector3f tarlocat = transf.getScale();
                    tarlocat.add(0.1F, 0.1F, 0.1F);
                    Transformation ntransf = new Transformation(transf.getTranslation(), transf.getLeftRotation(), tarlocat, transf.getRightRotation());
                    Catdisplayeditor.blockd.get(p.getUniqueId()).setTransformation(ntransf);
                    Catdisplayeditor.getInstance().openCowGUI(p, Catdisplayeditor.blockd.get(p.getUniqueId()));
                }
                else if (e.isLeftClick() && e.isShiftClick()) {
                    Transformation transf = Catdisplayeditor.blockd.get(p.getUniqueId()).getTransformation();
                    Vector3f tarlocat = transf.getScale();
                    tarlocat.add(-0.1F, -0.1F, -0.1F);
                    Transformation ntransf = new Transformation(transf.getTranslation(), transf.getLeftRotation(), tarlocat, transf.getRightRotation());
                    Catdisplayeditor.blockd.get(p.getUniqueId()).setTransformation(ntransf);
                    Catdisplayeditor.getInstance().openCowGUI(p, Catdisplayeditor.blockd.get(p.getUniqueId()));
                }
                else if (e.getClick() == MIDDLE) {
                    p.closeInventory();
                    p.sendMessage(dataload.printnum);
                    playerChatMap.put(p.getUniqueId(), "");
                    while (!isgetnum) {
                        // 等待isgetnum变为true
                        try {
                            Thread.sleep(100); // 避免CPU过度占用
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }
                    }
                    Transformation transf = Catdisplayeditor.blockd.get(p.getUniqueId()).getTransformation();
                    Vector3f tarlocat = transf.getScale();
                    tarlocat.set((float) num, (float) num, (float) num);
                    Transformation ntransf = new Transformation(transf.getTranslation(), transf.getLeftRotation(), tarlocat, transf.getRightRotation());
                    Catdisplayeditor.blockd.get(p.getUniqueId()).setTransformation(ntransf);
                    isgetnum = false;
                    num = 0;
                    Catdisplayeditor.getInstance().openCowGUI(p, Catdisplayeditor.blockd.get(p.getUniqueId()));
                }
            }//同步大小提升
            else if (rawSlot == 38) {
               float roY = Catdisplayeditor.blockd.get(p.getUniqueId()).getLocation().getPitch();
                if (e.isLeftClick() && !e.isShiftClick()){
                    roY += 0.1;
                    Catdisplayeditor.blockd.get(p.getUniqueId()).setRotation(roY,Catdisplayeditor.blockd.get(p.getUniqueId()).getLocation().getYaw());
                }
                else if (e.isRightClick() && !e.isShiftClick()){
                    roY -= 0.1;
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
                    p.closeInventory();
                    p.sendMessage(dataload.printnum);
                    playerChatMap.put(p.getUniqueId(), "");
                    while (!isgetnum) {
                        // 等待isgetnum变为true
                        try {
                            Thread.sleep(100); // 避免CPU过度占用
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }
                    }
                    roY = (float) num;
                    Catdisplayeditor.blockd.get(p.getUniqueId()).setRotation(roY,Catdisplayeditor.blockd.get(p.getUniqueId()).getLocation().getYaw());
                    isgetnum = false;
                    num = 0;
                    Catdisplayeditor.getInstance().openCowGUI(p, Catdisplayeditor.blockd.get(p.getUniqueId()));
                }
            } else if (rawSlot == 40) {
                float roY = Catdisplayeditor.blockd.get(p.getUniqueId()).getLocation().getYaw();
                if (e.isLeftClick() && !e.isShiftClick()){
                    roY += 0.1;
                    Catdisplayeditor.blockd.get(p.getUniqueId()).setRotation(Catdisplayeditor.blockd.get(p.getUniqueId()).getLocation().getPitch(),roY);
                }
                else if (e.isRightClick() && !e.isShiftClick()){
                    roY -= 0.1;
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
                    p.closeInventory();
                    p.sendMessage(dataload.printnum);
                    playerChatMap.put(p.getUniqueId(), "");
                    while (!isgetnum) {
                        // 等待isgetnum变为true
                        try {
                            Thread.sleep(100); // 避免CPU过度占用
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }
                    }
                    roY = (float) num;
                    Catdisplayeditor.blockd.get(p.getUniqueId()).setRotation(Catdisplayeditor.blockd.get(p.getUniqueId()).getLocation().getPitch(),roY);
                    isgetnum = false;
                    num = 0;
                    Catdisplayeditor.getInstance().openCowGUI(p, Catdisplayeditor.blockd.get(p.getUniqueId()));
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
                        Catdisplayeditor.getInstance().openCowGUI(p, Catdisplayeditor.blockd.get(p.getUniqueId()));
                    }
                }
                else if (e.isRightClick() && !e.isShiftClick()){
                    if(ltb-1>=0) {
                        Display.Brightness nlt = new Display.Brightness(ltb - 1, lts);
                        Catdisplayeditor.blockd.get(p.getUniqueId()).setBrightness(nlt);
                        Catdisplayeditor.getInstance().openCowGUI(p, Catdisplayeditor.blockd.get(p.getUniqueId()));
                    }
                }
                else if (e.isLeftClick() && e.isShiftClick()){
                    if(lts+1>=15) {
                        Display.Brightness nlt = new Display.Brightness(ltb, lts + 1);
                        Catdisplayeditor.blockd.get(p.getUniqueId()).setBrightness(nlt);
                        Catdisplayeditor.getInstance().openCowGUI(p, Catdisplayeditor.blockd.get(p.getUniqueId()));
                    }
                }
                else if (e.isRightClick() && e.isShiftClick()){
                    if(lts-1<=0) {
                        Display.Brightness nlt = new Display.Brightness(ltb, lts - 1);
                        Catdisplayeditor.blockd.get(p.getUniqueId()).setBrightness(nlt);
                        Catdisplayeditor.getInstance().openCowGUI(p, Catdisplayeditor.blockd.get(p.getUniqueId()));
                    }
                }
            }
            if (rawSlot == 53) {
                p.closeInventory();
                Catdisplayeditor.blockd.get(p.getUniqueId()).remove();
            }
            if (rawSlot >= 0 && rawSlot <= 53) {
                e.setCancelled(true);
            }
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
                num = Double.parseDouble(message);
                isgetnum = true;
                // 取消这次的发送消息事件
                event.setCancelled(true);

                // 从等待发送聊天消息的Map中移除玩家
            }
            playerChatMap.remove(playerUUID);
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
}
