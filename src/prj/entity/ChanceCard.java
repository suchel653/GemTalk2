package prj.entity;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import prj.canvas.GameCanvas;

public class ChanceCard extends Card {

	private Image img;

	public ChanceCard() {
		this(0);
	}

	public ChanceCard(int order) {
//		setQuestionOrder(order);
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
		int cardW = 154;
		int cardH = 218;

		g.drawImage(img, x, y, x + cardW, y + cardH, 0, 0, w, h, GameCanvas.instance);
	}

}
