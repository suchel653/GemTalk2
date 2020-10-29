package prj.entity;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import prj.canvas.GameCanvas;

public class Player {

	private int x;
	private int y;
	private int width = 150;
	private int height = 150;
	private Image img;
	private int imgNum;
	private int moveIndex = 0;
	private int moveTempo = 6;
	private MissionCard missionCard;
	private MyCard myCard;

	private PlayerListener playerListener;

	public Player() {
		this(0, 0, 1);
	}

	public Player(int pbX, int pbY, int imgNum) {
		this.imgNum = imgNum;
		switch (imgNum) {
		case 1:
		case 3:
			x = pbX + 350;
			y = pbY + 45;

			missionCard = new MissionCard(x - 120, y - 25);
			myCard = new MyCard(x - 340, y - 25);
			break;
		case 2:
		case 4:
			x = pbX + 20;
			y = pbY + 45;

			missionCard = new MissionCard(x + width + 30, y - 25);
			myCard = new MyCard(x + width + 162, y - 25);
			break;
		}
		Toolkit tk = Toolkit.getDefaultToolkit();
		img = tk.getImage("res/player" + imgNum + ".png");

	}

	public int vote() {

		Object options[] = { "⭕", "❌" };
		int input = JOptionPane.showOptionDialog(GameCanvas.instance, "답변이 만족스럽다면 O, 아니라면 X를 눌러주세요", "O/X 선택",
				JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

		return input;
	}

	public void answer(int playTurn) {
		boolean isCancel = true;
		
		String text = "";
		String answer = JOptionPane.showInputDialog(GameCanvas.instance, "질문의 답변을 입력하세요", "답변", JOptionPane.QUESTION_MESSAGE);
		
		while(isCancel) {
			 if(answer != null) {
				  text = "player" + Integer.toString(playTurn + 1) + "의 대답입니다";
				 isCancel = false;
			 }
			 else
				 answer = JOptionPane.showInputDialog(GameCanvas.instance, "질문의 답변을 해주세요", "답변이 입력되지 않았습니다!!", JOptionPane.ERROR_MESSAGE);
		}

		JOptionPane.showMessageDialog(GameCanvas.instance, answer, text, JOptionPane.INFORMATION_MESSAGE);				 
	}

	// 윈을 체크하는 메소드
	public void onWin() {
		int trueCount = 0; // 각각의 나의 카드가 각각의 미션카드를 몇 개 만족시키는지

		int[] myCardsCount = new int[5];
		int[] missionCardsCount = new int[4];

		int myCardsTotal = 0;
		int missionCardsTotal = 0;

		for (int i = 0; i < 5; i++) {
			myCardsCount[i] = myCard.getGemsCount()[i];
			myCardsTotal += myCardsCount[i];
		}

		for (int i = 0; i < 4; i++) {
			missionCardsCount[i] = missionCard.getCards()[i].getMissionCount();
			missionCardsTotal += missionCardsCount[i];
		}

		if (myCardsTotal >= missionCardsTotal) {
			for (int i = 0; i < 4; i++) {
				if (myCardsCount[i] < missionCardsCount[i]) {
					if (myCardsCount[i] + myCardsCount[4] >= missionCardsCount[i]) {
						myCardsCount[4] -= missionCardsCount[i] - myCardsCount[i];
						trueCount++;
					}
				} else {
					trueCount++;
				}
			}
		}

		if (playerListener != null && trueCount == 4)
			playerListener.win();

	}


	public void paint(Graphics g) {
		missionCard.paint(g);
		myCard.paint(g);

		int h = img.getHeight(null);
		int x1 = x;
		int y1 = y;
		int x2 = x1 + width;
		int y2 = y1 + height;
		int order = 0;

		switch (imgNum) {
		case 1:
			order = 7;
			break;
		case 2:
			order = 4;
			break;
		case 3:
			order = 10;
			break;
		case 4:
			order = 5;
			break;
		}
		int w = img.getWidth(null) / order;
		if (moveTempo == 0) {
			moveIndex++;
			moveIndex = moveIndex % order;
			moveTempo = 6;
		} else
			moveTempo--;
		int offsetX = moveIndex * w;

		g.drawImage(img, x1, y1, x2, y2, 0 + offsetX, 0, w + offsetX, h, GameCanvas.instance);
	}

	public void update() {
		myCard.update();
		onWin();
	}

	public void moveToPlayer(int cardType) {
		myCard.moveToPlayer(cardType);
	}

	public int takeCard(int randomCard) {
		return myCard.takeCard(randomCard);
	}

	public void setPlayerListener(PlayerListener playerListener) {
		this.playerListener = playerListener;
	}

}