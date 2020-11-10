package prj.entity;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.Random;

import prj.canvas.GameCanvas;

public class MissionCard {

	private int gemWidth = 25; 
	private int gemHeight = 25;

	private int x;
	private int y;

	private int gemX;
	private int gemY;

	private int redGemX;
	private int redGemY;
	private int orangeGemX;
	private int orangeGemY;
	private int greenGemX;
	private int greenGemY;
	private int blueGemX;
	private int blueGemY;

	private Image missionCardImg;
	private Image redGemImg;
	private Image orangeGemImg;
	private Image greenGemImg;
	private Image blueGemImg;
	private CardStatus[] cardStatus;

	private int max = 1;
	private Card[] cards;
	private Random rand = new Random();

	public MissionCard() {
		this(0, 0);
	}

	public MissionCard(int x, int y) {
		this.x = x;
		this.y = y;

		gemX = x + 12;
		gemY = y + 60;

		redGemX = gemX;
		redGemY = gemY;
		orangeGemX = gemX;
		orangeGemY = redGemY + 35;
		greenGemX = gemX;
		greenGemY = orangeGemY + 35;
		blueGemX = gemX;
		blueGemY = greenGemY + 35;

		cards = new Card[4];

		Toolkit tk = Toolkit.getDefaultToolkit();
		missionCardImg = tk.getImage("res/missionCard.png");
		redGemImg = tk.getImage("res/redGem.png");
		orangeGemImg = tk.getImage("res/orangeGem.png");
		greenGemImg = tk.getImage("res/greenGem.png");
		blueGemImg = tk.getImage("res/blueGem.png");

		cards[0] = new RedCard();
		cards[1] = new OrangeCard();
		cards[2] = new GreenCard();
		cards[3] = new BlueCard();

		cardStatus = new CardStatus[4];

		for (int i = 0; i < max; i++) {
			boolean ch = true;
			int color = rand.nextInt(4);

			while (ch) {
				if (cards[color].getMissionCount() >= 3)
					color = rand.nextInt(4);
				else {
					cards[color].setMissionCount(cards[color].getMissionCount() + 1);
					ch = false;
				}
			}
		}

		int temp = gemY;
		for (int i = 0; i < 4; i++) {
			String status = "X " + Integer.toString(cards[i].getMissionCount());
			cardStatus[i] = new CardStatus(gemX + 35, temp + 20, status);
			temp += 35;
		}
	}

	public void paint(Graphics g) {
		g.drawImage(missionCardImg, x, y, GameCanvas.instance);
		g.drawImage(redGemImg, redGemX, redGemY, gemWidth, gemHeight, GameCanvas.instance);
		g.drawImage(orangeGemImg, orangeGemX, orangeGemY, gemWidth, gemHeight, GameCanvas.instance);
		g.drawImage(greenGemImg, greenGemX, greenGemY, gemWidth, gemHeight, GameCanvas.instance);
		g.drawImage(blueGemImg, blueGemX, blueGemY, gemWidth, gemHeight, GameCanvas.instance);

		for (int i = 0; i < 4; i++)
			cardStatus[i].paint(g);
	}

	public void update() {
	}

	public Card[] getCards() {
		return cards;
	}

	public void setCards(Card[] cards) {
		this.cards = cards;
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
	
	

}
