package prj.entity;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import prj.canvas.GameCanvas;

public class BlueCard extends Card {

	public BlueCard() {
		this(0);
	}

	public BlueCard(int order) {
		super(order, "res/blueCard.png");

		setCardType(3);
	}

	@Override
	public void paint(Graphics g) {
		Image img = getImg();
		int x = getX();
		int y = getY();
		int w = img.getWidth(null) / 10;
		int h = img.getHeight(null);
		int order = getQuestionOrder();

		g.drawImage(img, x - getWidth() / 2, y - getHeight() / 2, x + getWidth() / 2, y + getHeight() / 2,
				0 + w * order, 0, w + w * order, h, GameCanvas.instance);
		
	}
}