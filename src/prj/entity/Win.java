package prj.entity;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;

import javax.swing.JOptionPane;

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
<<<<<<< HEAD
//		if(playTurn == 0)
//			order = 3;
//		else
//			order = playTurn-1;
		order = playTurn;
=======
		if (playTurn == 0)
			player = 3;
		else
			player = playTurn - 1;
>>>>>>> jaehee/main

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
<<<<<<< HEAD
//			Thread.sleep(10000);
			JOptionPane.showMessageDialog(GameCanvas.instance, "player"+(order+1)+"님이 승리하셨습니다!");
=======
			JOptionPane.showMessageDialog(GameCanvas.instance, "축하합니다 palyer" + (player + 1) + "님이 승리하셨습니다!!",
					"victory!!", JOptionPane.PLAIN_MESSAGE);
>>>>>>> jaehee/main
			System.exit(0);
		}
	}

}
