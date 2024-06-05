package org.catplugin.catdisplayeditor;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.util.List;

public class playersdata {
    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public List<Entity> getEntities() {
        return entities;
    }

    public void setEntities(List<Entity> entities) {
        this.entities = entities;
    }

    public int getPage() {
        if (page<=0){
            return 1;
        }
        else {
            return page;
        }
    }

    public void setPage(int page) {
        if(entities.size()%45+1 <= page){
            this.page = entities.size()%45;
        }
        else {
            this.page = page;
        }
    }

    private Player player;
    private List<Entity> entities;
    private int page;

}
