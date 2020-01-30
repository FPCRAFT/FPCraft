package com.mojang.ld22.item;

import com.mojang.ld22.entity.Mob;
import com.mojang.ld22.entity.MovingEntity;
import com.mojang.ld22.level.Level;

/**
 * Se Utiliza para definir herramientas capaces de realizar acciones con un 
 * mayor rango que las normales (Ej Arco)
 * @author Jose Antonio Duarte Pérez
 */
public class ToolRangedType extends ToolType{

    public static ToolRangedType bow = new ToolRangedType("Bow", 5, 6, 6, 9, 13, 10);
    
    private int range;
    private int xt;
    private int yt;
    
    private int proyectileXTile;
    private int proyectileYTile;
    
    public ToolRangedType(String name, int itemSprite, int xt, int yt, int proyectileXTile,int proyectileYTile, int range) {
        super(name, itemSprite);
        this.proyectileXTile = proyectileXTile;
        this.proyectileYTile = proyectileYTile;
        this.xt = xt;
        this.yt = yt;
        this.range = range;
        
    }
    
    public void attack(Mob itemOwner, int dir, Level level) {
        int x = 0;
        int y = 0;
        
        if(dir == 0){
            y++;
        } else if (dir == 1) {
            y--;
        } else if (dir == 2) {
            x--;
        } else {
            x++;
        }
        
        
        MovingEntity movingEntity = new MovingEntity(itemOwner, x, y, proyectileXTile, proyectileYTile, level);
        
        movingEntity.level.add(movingEntity);
    }
    
    public int getRange() {
        return range;
    }
    
}
