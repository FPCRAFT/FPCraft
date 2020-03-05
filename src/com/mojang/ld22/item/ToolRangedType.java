package com.mojang.ld22.item;

import com.mojang.ld22.entity.Mob;
import com.mojang.ld22.entity.MovingEntity;
import com.mojang.ld22.gfx.HojaSprites;
import com.mojang.ld22.gfx.Sprite;
import com.mojang.ld22.level.Level;

/**
 * Se Utiliza para definir herramientas capaces de realizar acciones con un 
 * mayor rango que las normales (Ej Arco)
 * @author Jose Antonio Duarte Pérez
 */
public class ToolRangedType extends ToolType{

    public static ToolRangedType bow = new ToolRangedType("Bow", 5, new Sprite(8, 6, 6, HojaSprites.SHEET_1), Sprite.ARROW, 10, 5);
    
    private int range;
    // posicion X - 1 del tile
    private Sprite sprite;
    private Sprite[] proyectileSprite;
    private int dmg;
    
    public ToolRangedType(String name, int itemSprite, Sprite sprite, Sprite[] proyectileSprite, int range, int dmg) {
        super(name, itemSprite);
        this.sprite = sprite;
        this.proyectileSprite = proyectileSprite;
        this.dmg = dmg;
        this.range = range;
    }
    
    /**
     * Genera la Entidad Proyectil que hace daño al chocar con otra entidad
     * @param itemOwner es el Mob que ha usado el objeto que genera el proyectil
     * @param dir es la direccion a la que mira dicho mob
     * @param level es el nivel actual en el que nos encontramos y donde se generara el proyectil
     */
    public void attack(Mob itemOwner, int dir, Level level) {
        int x = 0;
        int y = 0;
        int speed = 1;
        
        if(dir == 0) y+=speed;
        else if (dir == 1) y-=speed;
        else if (dir == 2) x-=speed;
        else x+=speed;
        
        MovingEntity movingEntity = new MovingEntity(itemOwner, x, y, proyectileSprite, level, dmg);
        
        movingEntity.level.add(movingEntity);
    }
    
    public int getRange() {
        return range;
    }
    
}
