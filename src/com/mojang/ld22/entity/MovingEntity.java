package com.mojang.ld22.entity;

import com.mojang.ld22.gfx.Color;
import com.mojang.ld22.gfx.Screen;
import com.mojang.ld22.gfx.Sprite;
import com.mojang.ld22.level.Level;
import java.util.List;

/**
 * Se encarga de indicar un objeto con una direccion y velocidad Fija, es un
 * proyectil
 *
 * @author Jose Antonio Duarte Pérez
 */
public class MovingEntity extends Entity {

    private int lifeTime;
    private int dir;
    private int dmg;
    
    private Sprite[] spritePack;
    /*
        xa y ya indican la direccion a la que se movera la entidad
    */
    public double xa, ya;
    public double xx, yy;
    
    /*
        Es la Entidad que ha generado el proyectil
    */
    private Mob owner;

    public MovingEntity(Mob owner, double xa, double ya, Sprite[] spritePack, Level level, int dmg) {
        this.dir = owner.dir;
        this.level = level;
        this.owner = owner;
        this.dmg = dmg;
        lifeTime = 300;
        
        this.spritePack = spritePack;
        
        xx = this.x = owner.x;
        yy = this.y = owner.y;
        xr = 0;
        yr = 0;

        this.xa = xa;
        this.ya = ya;
    }

    /**
     * Es la Actualizacion por ticks de la unidad, 1 tin es un lapso de tiempo,
     * cada este lapso de tiempo se volvera a ejecutar
     */
    public void tick() {
        if (lifeTime <= 0) {
            remove();
            return;
        }

        if (!move((int) xa, (int) ya)) {
            lifeTime = 0;
        }

        List<Entity> toHit = level.getEntities(x, y, x, y);
        for (int i = 0; i < toHit.size(); i++) {
            Entity e = toHit.get(i);
            if (e instanceof Mob && !e.equals(owner)) {
                e.hurt(owner, dmg, ((Mob) e).dir ^ 1);
                lifeTime = 0;
            }
        }
        lifeTime--;
    }

    /**
     * Permite a una Entidad Colocar esta Entidad, en este caso, es falso
     * @param mob
     * @return Falso
     */
    public boolean isBlockableBy(Mob mob) {
        return false;
    }

    /**
     * Dibuja En la Pantalla la Entidad del Proyectil
     * teniendo en cuenta la posicion de xt y yt
     * @param screen 
     */
    public void render(Screen screen) {
        if (lifeTime == 0) {
            return;
        }
        
        screen.render(x - 4, y - 4 + 2, spritePack[dir], Color.get(-1, 100, 321, 431));
        
    }
}
