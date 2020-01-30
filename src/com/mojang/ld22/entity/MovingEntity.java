package com.mojang.ld22.entity;

import com.mojang.ld22.gfx.Color;
import com.mojang.ld22.gfx.Screen;
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
    private int xt;
    private int yt;
    public double xa, ya;
    public double xx, yy;
    private Mob owner;

    public MovingEntity(Mob owner, double xa, double ya, int xt, int yt, Level level) {
        this.dir = owner.dir;
        this.level = level;
        this.owner = owner;
        lifeTime = 300;
        this.xt = xt;
        this.yt = yt;
        xx = this.x = owner.x;
        yy = this.y = owner.y;
        xr = 0;
        yr = 0;

        this.xa = xa;
        this.ya = ya;
    }

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
                e.hurt(owner, 1, ((Mob) e).dir ^ 1);
                lifeTime = 0;
            }
        }
        lifeTime--;
    }

    public boolean isBlockableBy(Mob mob) {
        return false;
    }

    public void render(Screen screen) {
        if (lifeTime == 0) {
            return;
        }
        int mas = 0;
        
        if (dir == 0 || dir == 1) {
            mas++;
            
            if(dir==1) mas++;
            
            screen.render(x - 4, y - 4 + 2, (mas + xt) + yt * 32, Color.get(-1, 000, 000, 000), dir);
            screen.render(x - 4, y - 4 - 2, (mas + xt) + yt * 32, Color.get(-1, 555, 321, 555), dir);
        }
        else {
            screen.render(x - 4, y - 4 - 2, xt + yt * 32, Color.get(-1, 555, 321, 555), dir);
            screen.render(x - 4, y - 4 + 2, xt + yt * 32, Color.get(-1, 000, 000, 000), dir);
        }
    }
}
