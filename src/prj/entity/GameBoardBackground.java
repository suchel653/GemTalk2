package prj.entity;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import prj.canvas.GameCanvas;

public class GameBoardBackground {
	private static Image img;
	private int x;
	private int y;
	
	static {
		try {
			img = ImageIO.read(new File("res/gameBoardBackground.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public GameBoardBackground() {
		this(0,0);
	}
	
	public GameBoardBackground(int x,int y) {
		this.x = x;
		this.y = y;
	}

	public void paint(Graphics g) {
		
		g.drawImage(img, x, y, x + 530, y + 240, 0, 0, img.getWidth(null), img.getHeight(null), GameCanvas.instance);
		
	}
	
}
