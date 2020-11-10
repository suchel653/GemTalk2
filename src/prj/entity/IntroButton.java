package prj.entity;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import prj.canvas.IntroCanvas;

public class IntroButton {

	private double x;
	private double y;
	private double width;
	private double height;
	private int i;
	private Image img;

	public IntroButton() {
		this(0, 0, 0, 0, 0);
	}

	public IntroButton(double x, double y, double width, double height, int i) {

		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.i = i;

	}

	public void paint(Graphics g) {
		Toolkit tk = Toolkit.getDefaultToolkit();
		if (this.i == 1) {
			img = tk.getImage("res/startButton.png");
		} else if (this.i == 2) {
			img = tk.getImage("res/ruleButton.png");
		} else if (this.i == 3) {
			img = tk.getImage("res/exitButton.png");
		}

		int x = (int) this.x;
		int y = (int) this.y;
		int w = (int) this.width;
		int h = (int) this.height;
		int iw = img.getWidth(null);
		int ih = img.getHeight(null);

		g.drawImage(img, x, y, x + w, y + h, 0, 0, iw, ih, IntroCanvas.instance);
	}

	public boolean isSelected(int x, int y) {

		int w = (int) this.width;
		int h = (int) this.height;
		int x1 = (int) this.x;
		int y1 = (int) this.y;
		int x2 = x1 + w;
		int y2 = y1 + h;

		if ((x1 <= x && x <= x2) && (y1 <= y && y <= y2))
			return true;

		return false;
	}

}
