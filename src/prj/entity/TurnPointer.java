package prj.entity;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import prj.canvas.GameCanvas;

public class TurnPointer {

	private int playTurn;

	private Image img;

	public TurnPointer() {
	}

	public void turn(int playTurn) {
		this.playTurn = playTurn;
	}

	public int getPlayTurn() {
		return playTurn;
	}

	public void setPlayTurn(int playTurn) {
		this.playTurn = playTurn;
	}

	public void paint(Graphics g) {
		int x = 510;
		int y1 = 50;
		int y2 = 510;

		Toolkit tk = Toolkit.getDefaultToolkit();
		img = tk.getImage("res/arrowPointer" + this.playTurn + ".png");

		int w = img.getWidth(null); // 364
		int h = img.getHeight(null); // 208

		if (playTurn == 0) {
			g.drawImage(img, x, y1, x + w / 2, y1 + h / 2, 0, 0, w, h, GameCanvas.instance);
		} else if (playTurn == 1) {
			g.drawImage(img, x, y1, x + w / 2, y1 + h / 2, 0, 0, w, h, GameCanvas.instance);
		} else if (playTurn == 2) {
			g.drawImage(img, x, y2, x + w / 2, y2 + h / 2, 0, 0, w, h, GameCanvas.instance);
		} else if (playTurn == 3) {
			g.drawImage(img, x, y2, x + w / 2, y2 + h / 2, 0, 0, w, h, GameCanvas.instance);
		}

	}

}
