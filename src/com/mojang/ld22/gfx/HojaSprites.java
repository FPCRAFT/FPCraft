package com.mojang.ld22.gfx;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * Carga la una imagen del sistema en el programa pixel a pixel, para acceder a dicha imagen se utiliza getPIXELS_IMAGE()
 * 
 * @author Jose Antonio Duarte Perez
 * @see Sprite
 */
public class HojaSprites {
	
	/*
	 * Declarar Aqui arriba las "hojas" o mas bien Imagenes que vayais a utilizar
	 */
	public static final HojaSprites SHEET_1 = new HojaSprites("/icons.png", 256, 256);
	
	private final int WIDTH;
	private final int HEIGHT;
	
	/**
	 * Es la Imagen en pixeles, utilizada para la recoleccion de sprites
	 */
	private final int[] PIXELS_IMAGE;
	
	/**
	 * Crea una HojaSprites para recoger de esta los sprites que necesites y cargarlas al juego
	 * 
	 * @param rute Ruta de la imagen del sistema (en String)
	 * @param width Ancho de la imagen
	 * @param height Alto de la imagen
	 * 
	 * @see Sprite
	 */
	public HojaSprites(String rute, int width, int height) {
		
		WIDTH = width;
		HEIGHT = height;
		
		PIXELS_IMAGE = new int[WIDTH * HEIGHT];
		
		BufferedImage img = null;
		try 
		{
			img = ImageIO.read(HojaSprites.class.getResourceAsStream(rute));
			img.getRGB(0, 0, WIDTH, HEIGHT, PIXELS_IMAGE, 0, WIDTH);
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Devuelve la Imagen como un vector de enteros (en entero representa el color de dicho pixel)
	 * @return int[] los pixeles de la imagen
	 */
	public int[] getPIXELS_IMAGE() {
		return PIXELS_IMAGE;
	}
	
	/**
	 * Devuelve el ancho de la Imagen
	 * @return int Ancho
	 */
	public int getWIDTH() {
		return WIDTH;
	}
	
	/**
	 * Devuelve el alto de la Imagen
	 * @return int Alto
	 */
	public int getHEIGHT() {
		return HEIGHT;
	}
}
