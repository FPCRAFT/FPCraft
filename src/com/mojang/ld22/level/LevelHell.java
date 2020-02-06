/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mojang.ld22.level;

import com.mojang.ld22.entity.Mob;
import com.mojang.ld22.level.tile.Tile;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author Administrador
 */
public class LevelHell extends Level {

    public static final Collection<Class<? extends Mob>> MobList = new ArrayList<Class<? extends Mob>>();
    public static final Collection<Class<? extends Tile>> TileSheet = new ArrayList<Class<? extends Tile>>();

    public LevelHell(int width, int height, int level, Level parentLevel) {
        super(width, height, level, parentLevel);
    }

    @Override
    protected void setExits(Level parentLevel) {

        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                if (parentLevel.getTile(x, y) == Tile.stairsDown) {

                    setTile(x, y, Tile.stairsUp, 0);
                    if (depth == 0) {
                        setTile(x - 1, y, Tile.hardRock, 0);
                        setTile(x + 1, y, Tile.hardRock, 0);
                        setTile(x, y - 1, Tile.hardRock, 0);
                        setTile(x, y + 1, Tile.hardRock, 0);
                        setTile(x - 1, y - 1, Tile.hardRock, 0);
                        setTile(x - 1, y + 1, Tile.hardRock, 0);
                        setTile(x + 1, y - 1, Tile.hardRock, 0);
                        setTile(x + 1, y + 1, Tile.hardRock, 0);
                    } else {
                        setTile(x - 1, y, Tile.dirt, 0);
                        setTile(x + 1, y, Tile.dirt, 0);
                        setTile(x, y - 1, Tile.dirt, 0);
                        setTile(x, y + 1, Tile.dirt, 0);
                        setTile(x - 1, y - 1, Tile.dirt, 0);
                        setTile(x - 1, y + 1, Tile.dirt, 0);
                        setTile(x + 1, y - 1, Tile.dirt, 0);
                        setTile(x + 1, y + 1, Tile.dirt, 0);
                    }
                }

            }
        }
    }
}
