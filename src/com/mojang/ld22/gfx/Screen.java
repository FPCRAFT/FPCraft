package com.mojang.ld22.gfx;

public class Screen {
	
	public static final int BIT_MIRROR_X = 0x01;
	public static final int BIT_MIRROR_Y = 0x02;
	
	public int xOffset;
	public int yOffset;

	private int[] dither = new int[] { 0, 8, 2, 10, 12, 4, 14, 6, 3, 11, 1, 9, 15, 7, 13, 5, };

	public final int WIDTH; 
	public final int HEIGHT;
	public int[] pixels;

	private SpriteSheet sheet;

	public Screen(int w, int h, SpriteSheet sheet) {
		this.sheet = sheet;
		this.WIDTH = w;
		this.HEIGHT = h;

		pixels = new int[w * h];
	}

	public void clear(int color) {
		for (int i = 0; i < pixels.length; i++)
			pixels[i] = color;
	}

	/**
	 * Cambie el modo de renderizado a render(int,int,sprite) o (int,int,sprite,color)
	 * 
	 * @deprecated
	 * @see render(int positionX, int positionY, Sprite sprite, int colors)
	 * @see render(int positionX, int positionY, Sprite sprite)
	 */
	public void render(int positionX, int positionY, int tile, int colors, int bits) {
		positionX -= xOffset;
		positionY -= yOffset;
		boolean mirrorX = (bits & BIT_MIRROR_X) > 0;
		boolean mirrorY = (bits & BIT_MIRROR_Y) > 0;

		int xTile = tile % 32;
		int yTile = tile / 32;
		int toffs = xTile * 8 + yTile * 8 * sheet.width;

		for (int y = 0; y < 8; y++) {
			int ys = y;
			if (mirrorY) ys = 7 - y;
			if (y + positionY < 0 || y + positionY >= HEIGHT) continue;
			for (int x = 0; x < 8; x++) {
				if (x + positionX < 0 || x + positionX >= WIDTH) continue;

				int xs = x;
				if (mirrorX) xs = 7 - x;
				int col = (colors >> (sheet.pixels[xs + ys * sheet.width + toffs] * 8)) & 255;
				
				if (col < 255) pixels[(x + positionX) + (y + positionY) * WIDTH] = col;
				
			}
		}
	}
	
	/**
	 * Se Encargara De Dibujar un Sprite en la pantalla, solo si esta dibujado a color,
         * para los que esten en blanco/negro aniadir el parametro color al final
	 * 
	 * @author Jose Antonio Duarte Perez
	 * 
	 * @see render(int positionX, int positionY, Sprite sprite, int colors)
	 * 
	 * @param positionX Posicion X del sprite que se va a dibujar (En el mapa)
	 * @param positionY Posicion Y del sprite que se va a dibujar (En el mapa)
	 * @param sprite Es el Sprite que se dibujara
	 */
	public void render(int positionX, int positionY, Sprite sprite) {
		positionX -= xOffset;
		positionY -= yOffset;

		for (int screenY = 0; screenY < sprite.getSIZE(); screenY++) {
			
			if ((screenY + positionY) < 0 || (screenY + positionY) >= HEIGHT) continue;
			
			for (int screenX = 0; screenX < sprite.getSIZE(); screenX++) {
				
				if ((screenX + positionX) < 0 || (screenX + positionX) >= WIDTH) continue;
				
				// Calculamos la posicion final : 			(screenX + positionX) + (screenY + positionY) * WIDTH
				// y le agregamos los pixeles del sprite 1 a 1 : 	(screenX + screenY) * sprite.getSIZE()
				
				pixels[ (screenX + positionX) + (screenY + positionY) * WIDTH ] = 
						sprite.getSpritePixels()[ screenX + screenY * sprite.getSIZE() ]; 
			}
		}
	}
	
	/**
	 * Se Encargara De Dibujar un Sprite en la pantalla, solo si esta dibujado a blanco/negro,
         * para los que esten a color quitar el parametro colors al final
	 * 
	 * @author Jose Antonio Duarte Perez
	 * 
	 * @see render(int positionX, int positionY, Sprite sprite)
	 * 
	 * @param positionX Posicion X del sprite que se va a dibujar (En el mapa)
	 * @param positionY Posicion Y del sprite que se va a dibujar (En el mapa)
	 * @param sprite Es el Sprite que se dibujara
	 */
	public void render(int positionX, int positionY, Sprite sprite, int colors) {
		positionX -= xOffset;
		positionY -= yOffset;

		for (int screenY = 0; screenY < sprite.getSIZE(); screenY++) {
			
			if ((screenY + positionY) < 0 || (screenY + positionY) >= HEIGHT) continue;
			
			for (int screenX = 0; screenX < 8; screenX++) {
				
				if ((screenX + positionX) < 0 || (screenX + positionX) >= WIDTH) continue;

				
				int col = (colors >> (sprite.getSpritePixels()
							[screenX + screenY * sprite.getSIZE()]
							)) & 255;
				if (col < 255) pixels[(screenX + positionX) + (screenY + positionY) * WIDTH] = col;
			}
		}
	}


	public void setOffset(int xOffset, int yOffset) {
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}

	public void overlay(Screen screen2, int xa, int ya) {
		int[] oPixels = screen2.pixels;
		int i = 0;
		for (int y = 0; y < HEIGHT; y++) {
			for (int x = 0; x < WIDTH; x++) {
				if (oPixels[i] / 10 <= dither[((x + xa) & 3) + ((y + ya) & 3) * 4]) pixels[i] = 0;
				i++;
			}

		}
	}

	public void renderLight(int x, int y, int r) {
		x -= xOffset;
		y -= yOffset;
		int x0 = x - r;
		int x1 = x + r;
		int y0 = y - r;
		int y1 = y + r;

		if (x0 < 0) x0 = 0;
		if (y0 < 0) y0 = 0;
		if (x1 > WIDTH) x1 = WIDTH;
		if (y1 > HEIGHT) y1 = HEIGHT;
		// System.out.println(x0 + ", " + x1 + " -> " + y0 + ", " + y1);
		for (int yy = y0; yy < y1; yy++) {
			int yd = yy - y;
			yd = yd * yd;
			for (int xx = x0; xx < x1; xx++) {
				int xd = xx - x;
				int dist = xd * xd + yd;
				// System.out.println(dist);
				if (dist <= r * r) {
					int br = 255 - dist * 255 / (r * r);
					if (pixels[xx + yy * WIDTH] < br) pixels[xx + yy * WIDTH] = br;
				}
			}
		}
	}
}
