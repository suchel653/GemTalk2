package prj.entity;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import prj.canvas.GameCanvas;
import prj.canvas.RuleCanvas;

public class GameBackground {

	private int x;
	private int y;
	private int width;
	private int height;
	private Image img;

	public GameBackground() {
		this(0, 0);
	}

	public GameBackground(int x, int y) {
		this.x = x;
		this.y = y;

		Toolkit tk = Toolkit.getDefaultToolkit();
		img = tk.getImage("res/mapleBg.gif");

		width = img.getWidth(null);
		height = img.getHeight(null);
	}

	public void paint(Graphics g) {
		int w = GameCanvas.instance.getWidth();
		int h = GameCanvas.instance.getHeight();
		g.drawImage(img, 0, 0, w, h, x, y, w, h, GameCanvas.instance);
	}

	public void update() {

	}

	public Image getImage() {
		return img;
	}
}
