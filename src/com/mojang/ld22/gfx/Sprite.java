package com.mojang.ld22.gfx;

/**
 * Se encarga de buscar nuestro sprite a traves de un numero x e y en la
 * HojaSprites
 *
 * REGLA N�1 los sprites deven ser totalmente cuadrados, es decir ancho == alto
 *
 * @author Jose Antonio Duarte Perez
 * @see HojaSprites
 */
public class Sprite {

    private static final Sprite ARROW_SOURTH = new Sprite(8, 9, 13, HojaSprites.SHEET_1);
    private static final Sprite ARROW_NORTH = new Sprite(8, 9, 13, 2, HojaSprites.SHEET_1);
    private static final Sprite ARROW_EAST = new Sprite(8, 9, 13, 1, HojaSprites.SHEET_1);
    private static final Sprite ARROW_WEST = new Sprite(8, 9, 13, 3, HojaSprites.SHEET_1);

    public static final Sprite[] ARROW = {ARROW_NORTH, ARROW_SOURTH, ARROW_WEST, ARROW_EAST};

    private final int SIZE;
    private int graphicX;
    private int graphicY;
    private int[] spritePixels;
    private HojaSprites sheet;

    /**
     * Este constructor devuelve el valor por defecto del sprite de la
     * hojaSprites, NO LO ROTA
     *
     * @param size Tama�o del sprite (solor el ancho o el alto)
     * @param tileRow Posicion X del sprite en la hoja de sprites
     * @param tileColum Posicion Y del sprite en la hoja de sprites
     * @param sheet HojaSprites Utilizada
     *
     * @see HojaSprites
     * @see Sprite(int size, int tileRow, int tileColumn, int rotationVersion,
     * HojaSprites sheet)
     */
    public Sprite(int size, int tileRow, int tileColumn, HojaSprites sheet) {
        SIZE = size;

        spritePixels = new int[SIZE * SIZE];

        graphicX = tileRow * SIZE;
        graphicY = tileColumn * SIZE;
        this.sheet = sheet;

        loadSprite();
    }

    /**
     * Aun no Habilitado.
     *
     * Se utiliza en caso de querer Rotar el sprite, este constructor devuelve
     * el valor por defecto del sprite de la hojaSprites pero Rotado
     *
     * @param size Tama�o del sprite (solor el ancho o el alto)
     * @param tileRow Posicion X del sprite en la hoja de sprites
     * @param tileColum Posicion Y del sprite en la hoja de sprites
     * @param sheet HojaSprites Utilizada
     *
     * @see HojaSprites
     * @see Sprite(int size, int tileRow, int tileColumn, HojaSprites sheet)
     */
    public Sprite(int size, int tileRow, int tileColumn, int rotationVersion, HojaSprites sheet) {
        SIZE = size;

        spritePixels = new int[SIZE * SIZE];

        graphicX = tileRow * SIZE;
        graphicY = tileColumn * SIZE;
        this.sheet = sheet;

        loadSprite(rotationVersion);
    }

    /**
     * Devuelve el tama�o del Sprite
     *
     * @return int SIZE
     */
    public int getSIZE() {
        return SIZE;
    }

    /**
     * Devuelve el Sprite en Pixeles (Enteros)
     *
     * @return int[] SpritePixels
     */
    public int[] getSpritePixels() {
        return spritePixels;
    }

    /**
     * Carga El Sprite De forma Normal
     */
    private void loadSprite() {
        try {
            for (int Y = 0; Y < SIZE; Y++) {

                for (int X = 0; X < SIZE; X++) {

                    spritePixels[X + Y * SIZE]
                            = sheet.getPIXELS_IMAGE()[(X + graphicX) + (Y + graphicY) * sheet.getWIDTH()];
                }
            }
        } catch (Exception e) {
            System.err.println("OYE, CORRIGE LAS CORDENADAS DEL SPRITE!!!!!!!!!!!!!\n");
            e.printStackTrace();
        }
    }

    /**
     * Aun no Habilitado.
     *
     * Carga el Sprite y Mientras lo rota
     *
     * @param rotationVersion entero que indica el � de rotacion {0[0], 1[90],
     * 2[180], 3[270]}
     */
    private void loadSprite(int rotationVersion) {
        loadSprite();

        int[][] tempPixels = new int[SIZE][SIZE];

        for (int y = SIZE - 1; y >= 0; y--) {
            for (int x = 0; x < SIZE; x++) {
                tempPixels[y][x] = spritePixels[y + x * SIZE];
            }
        }

        int[] newPixels = null;
        
        int rotation = rotationVersion;
        while (rotation-- > 1) {
            newPixels = new int[SIZE * SIZE];

            int loader = 0;
            for (int X = SIZE - 1; X >= 0; X--) {

                for (int Y = 0; Y < SIZE; Y++) {
                    newPixels[loader + Y * SIZE] = tempPixels[Y][X];
                }

                loader++;
            }
        }
        
        spritePixels = newPixels;
    }

}
