package prj.entity;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.Random;

import prj.canvas.GameCanvas;

public class ActionCard extends Card {

	private Random rand;
	private int actionCardType;
	private ActionCardListener actionListener;

	public ActionCard() {
		this(0);
	}

	public ActionCard(int actionCardType) {
		super(actionCardType, "res/actionCard.png");
		setCardType(5);
		this.actionCardType = actionCardType;
		rand = new Random();
	}

	@Override
	public void giveOrTake(int playTurn) {
		int randomPlayer = rand.nextInt(4);
		int randomCard = rand.nextInt(5);

		boolean ch = true;

		while (ch) {
			if (randomPlayer == playTurn)
				randomPlayer = rand.nextInt(4);
			else
				ch = false;
		}
		System.out.println("현재 플레이어 번호(턴): " + (playTurn + 1) + "번");
		System.out.println("랜덤 플레이어 번호: " + (randomPlayer + 1) + "번");

		if (actionCardType == 0) { // 나의 카드 1개를 플레이어 중 1명에게 주기
			if (actionListener != null) {
				System.out.println(
						"actionCardType: 0번 -> 나의 카드 " + randomCard + "를  " + (randomPlayer + 1) + "번 플레이어에게 주기 ");
				actionListener.give(randomPlayer, randomCard);
			}
		} else {
			if (actionListener != null) { // 플레이어 중 1명이 나에게 카드를 준다
				System.out.println(
						"actionCardType: 1번 -> 내가 " + (randomPlayer + 1) + "번 플레이어로부터 " + randomCard + "를 뺏어오기");
				actionListener.take(randomPlayer, randomCard);
			}
		}
	}

	@Override
	public void paint(Graphics g) {
		Image img = getImg();
		int x = getX();
		int y = getY();
		int w = img.getWidth(null) / 6;
		int h = img.getHeight(null);
		int order = getQuestionOrder();
		int cardW = 154;
		int cardH = 218;

		g.drawImage(img, x, y, x+getWidth(),y+getHeight(), 0 + w * order, 0, w + w * order, h, GameCanvas.instance);
	}

	public void setActionListener(ActionCardListener actionListener) {
		this.actionListener = actionListener;
	}

}
