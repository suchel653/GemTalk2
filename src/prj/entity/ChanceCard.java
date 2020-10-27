package prj.entity;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import prj.canvas.GameCanvas;

public class ChanceCard extends Card {

	private Image img;

	public ChanceCard() {
		setCardType(4);
		Toolkit tk = Toolkit.getDefaultToolkit();
		img = tk.getImage("res/chanceCard.png");
	}

	@Override
	public void paint(Graphics g) {

		int x = getX();
		int y = getY();
		int w = img.getWidth(null) / 5;
		int h = img.getHeight(null);

		g.drawImage(img, x - getWidth() / 2, y - getHeight() / 2, x + getWidth() / 2, y + getHeight() / 2, 0, 0, w, h,
				GameCanvas.instance);
	}

}
