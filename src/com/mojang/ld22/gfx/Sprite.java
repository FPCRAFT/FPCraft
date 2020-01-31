package com.mojang.ld22.gfx;

/**
 * Se encarga de buscar nuestro sprite a traves de un numero x e y en la HojaSprites
 * 
 * REGLA Nº1 los sprites deven ser totalmente cuadrados, es decir ancho == alto
 * 
 * @author Jose Antonio Duarte Perez
 * @see HojaSprites
 */
public class Sprite {
	
	private final int SIZE;
	private int graphicX;
	private int graphicY;
	private int[] spritePixels;
	private HojaSprites sheet;
	
	/**
	 * Este constructor devuelve el valor por defecto del sprite de la hojaSprites, NO LO ROTA
	 * 
	 * @param size Tamaño del sprite (solor el ancho o el alto)
	 * @param tileRow Posicion X del sprite en la hoja de sprites
	 * @param tileColum Posicion Y del sprite en la hoja de sprites
	 * @param sheet HojaSprites Utilizada
	 * 
	 * @see HojaSprites
	 * @see Sprite(int size, int tileRow, int tileColumn, int rotationVersion, HojaSprites sheet)
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
	 *  Aun no Habilitado.
	 * 
	 * Se utiliza en caso de querer Rotar el sprite, este constructor 
	 * devuelve el valor por defecto del sprite de la hojaSprites pero Rotado
	 * 
	 * @param size Tamaño del sprite (solor el ancho o el alto)
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
	 * Devuelve el tamaño del Sprite
	 * @return int SIZE
	 */
	public int getSIZE() {
		return SIZE;
	}
	
	/**
	 * Devuelve el Sprite en Pixeles (Enteros)
	 * @return int[] SpritePixels
	 */
	public int[] getSpritePixels() {
		return spritePixels;
	}
	
	/**
	 * Carga El Sprite De forma Normal
	 */
	private void loadSprite() {
		for (int Y = 0; Y < SIZE; Y++) {
			
			for (int X = 0; X < SIZE; X++) {
				
				spritePixels[X + Y * SIZE] = 
						sheet.getPIXELS_IMAGE()[
						                        (X + graphicX) + (Y + graphicY) * sheet.getWIDTH()
						                        ];
			}
		}
	}
	
	/**
	 * Aun no Habilitado.
	 * 
	 * Carga el Sprite y Mientras lo rota
	 * 
	 * @param rotationVersion entero que indica el º de rotacion {0[0º], 1[90º], 2[180º], 3[270º]}
	 */
	private void loadSprite(int rotationVersion) {
		
	}
	
	
	
}
