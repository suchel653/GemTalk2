package prj.entity;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import prj.canvas.GameCanvas;

public class Win {

	private int x;
	private int y;
	private int order;
	private Image img;

	public Win() {
		this(0);
	}

	public Win(int playTurn) {

		x = 350;
		y = 730;
		if(playTurn == 0)
			order = 3;
		else
			order = playTurn-1;

		Toolkit tk = Toolkit.getDefaultToolkit();
		img = tk.getImage("res/win.png");
		System.out.println("order"+order);
	}

	public void paint(Graphics g) {

		int w = img.getWidth(null) / 4;
		int h = img.getHeight(null);
		System.out.println("w:" + w);
		g.drawImage(img, 
				x, y, x + 600, y + 600, 
				0 + order * w, 0, w + order * w, h, 
				GameCanvas.instance);
	}

	public void update() throws InterruptedException {
		y = y-5;

		System.out.println(y);
		if (y <= 50) {
			y = 50;
			Thread.sleep(10000);
			System.exit(0);
		}
	}

}
