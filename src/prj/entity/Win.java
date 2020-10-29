package prj.entity;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;

import javax.swing.JOptionPane;

import prj.canvas.GameCanvas;

public class Win {

	private int x;
	private int y;
	private int player;
	private Image img;

	public Win() {
		this(0);
	}

	public Win(int playTurn) {

		x = 350;
		y = 730;
		if (playTurn == 0)
			player = 3;
		else
			player = playTurn - 1;

		Toolkit tk = Toolkit.getDefaultToolkit();
		img = tk.getImage("res/win.png");
	}

	public void paint(Graphics g) {

		int w = img.getWidth(null) / 4;
		int h = img.getHeight(null);

		g.drawImage(img, x, y, x + 600, y + 600, 0 + player * w, 0, w + player * w, h, GameCanvas.instance);
	}

	public void update() throws InterruptedException {
		y = y - 5;

		if (y <= 50) {
			y = 50;
			JOptionPane.showMessageDialog(GameCanvas.instance, "축하합니다 palyer" + (player + 1) + "님이 승리하셨습니다!!",
					"victory!!", JOptionPane.PLAIN_MESSAGE);
			System.exit(0);
		}
	}

}
