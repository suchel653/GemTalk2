package prj.entity;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import prj.canvas.RuleCanvas;

public class RuleButton {

	private double x;
	private double y;
	private double width;
	private double height;
	private String text;
	private Image img;

	public RuleButton() {
		this(950, 560, 150, 80);
	}

	public RuleButton(double x, double y, double width, double height) {

		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
		Toolkit tk = Toolkit.getDefaultToolkit();
		img = tk.getImage("res/backButton.png");


	}

	public void paint(Graphics g) {
		
		int x = (int) this.x;
		int y = (int) this.y;
		int w = (int) this.width;
		int h = (int) this.height;
		int iw = img.getWidth(null);
		int ih = img.getHeight(null);
		
		g.drawImage(img, x, y, x + w, y + h, 0, 0, iw, ih, RuleCanvas.instance);
	}

	public boolean click(int x2, int y2) {
		if ((x <= x2 && x2 <= x + width) && (y <= y2 && y2 <= y + height))
			return true;

		return false;
	}


}
