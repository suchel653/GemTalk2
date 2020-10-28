package prj.entity;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import prj.canvas.GameCanvas;

public class CardDeck extends Card {

	private int x;
	private int y;
	private int width;
	private int height;

	public CardDeck() {
		this(0, 0);
	}

	public CardDeck(int x, int y) {
		super(x, y, "res/cardDeck.png");

		this.x = x; // 355 + 20
		this.y = y; // 225 + 20
	}

	@Override
	public void paint(Graphics g) {
		Image img = getImg();
		width = img.getWidth(null);
		height = img.getHeight(null);

		g.drawImage(img, x - getWidth() / 2, y - getHeight() / 2, x + getWidth() / 2, y + getHeight() / 2, 0, 0, width,
				height, GameCanvas.instance);
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

}
