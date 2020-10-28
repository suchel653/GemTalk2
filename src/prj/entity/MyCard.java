package prj.entity;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.Random;

import prj.canvas.GameCanvas;

public class MyCard {
	private int cardWidth = 185; // 보석 뒤 배경
	private int cardHeight = 200;
	private int sparkleTime; // 별이 깜빡이는 시간 2초
	private int sparkle;

	// 화면에 출력할 보석 크기
	private int gemWidth = 25;
	private int gemHeight = 25;

	// MyCard 좌표
	private int x;
	private int y;

	private int gemX;
	private int gemY;

	// 내가 모은 보석(카드)의 개수
	// red, orange, green, blue, chance 순
	private int[] gemsCount = { 0, 0, 0, 0, 0, 0 };

	// 보석 좌표
	private int redGemX;
	private int redGemY;
	private int orangeGemX;
	private int orangeGemY;
	private int greenGemX;
	private int greenGemY;
	private int blueGemX;
	private int blueGemY;
	private int chanceGemX;
	private int chanceGemY;

	// 별 좌표
	private int starX;
	private int starY;

	// 보석 이미지
	private Image myCardImg;
	private Image redGemImg;
	private Image orangeGemImg;
	private Image greenGemImg;
	private Image blueGemImg;
	private Image chanceGemImg;
	private Image starImg;

	private CardStatus[] cardStatus;

	private Random rand;

	public MyCard() {
		this(0, 0);
	}

	public MyCard(int x, int y) {
		sparkleTime = 0;
		sparkle = 0;

		this.x = x;
		this.y = y;

		gemX = x + 12;
		gemY = y + 60;

		redGemX = x + 12;
		redGemY = y + 60;
		orangeGemX = x + 12;
		orangeGemY = redGemY + 35;
		greenGemX = x + 12;
		greenGemY = orangeGemY + 35;
		blueGemX = x + 12;
		blueGemY = greenGemY + 35;
		chanceGemX = x + 95;
		chanceGemY = (orangeGemY + greenGemY) / 2;

		starX = x;
		starY = y;

		Toolkit tk = Toolkit.getDefaultToolkit();
		myCardImg = tk.getImage("res/myCard.png");
		redGemImg = tk.getImage("res/redGem.png");
		orangeGemImg = tk.getImage("res/orangeGem.png");
		greenGemImg = tk.getImage("res/greenGem.png");
		blueGemImg = tk.getImage("res/blueGem.png");
		chanceGemImg = tk.getImage("res/chanceGem.png");
		starImg = tk.getImage("res/star.png");

		cardStatus = new CardStatus[5];

		// 처음엔 내가 모은 보석(카드)의 개수가 0개이므로 0으로 초기화
		int temp = gemY; // gemY값이 보석마다 달라서 바뀌기 때문에 임시변수로 temp로 넣었음
		for (int i = 0; i < 5; i++) {
			String status = "X " + Integer.toString(gemsCount[i]);

			if (i == 4)
				cardStatus[i] = new CardStatus(chanceGemX + 35, chanceGemY + 20, status);
			else
				cardStatus[i] = new CardStatus(gemX + 35, temp + 20, status);

			temp += 35;
		}

		rand = new Random();
	}

	public void moveToPlayer(int cardType) {
		gemsCount[cardType]++;
		sparkleTime = 240; // 카드를 클릭할 때마다 깜빡이는 시간 4초로 설정
	}

	public int takeCard(int cardType) {
		boolean check = true;

		while (check) {
			if (gemsCount[cardType] <= 0) // 카드개수가 0이면 뺏을수가 없으니
				cardType = rand.nextInt(5);
			else {
				check = false;
				gemsCount[cardType]--;
			}
		}

		System.out.println("뺏기는 카드타입(0개로 인해 카드타입이 다를 수 있음): " + cardType);
		return cardType;
	}

	public void paint(Graphics g) {

		g.drawImage(myCardImg, x, y, GameCanvas.instance);
		g.drawImage(redGemImg, redGemX, redGemY, gemWidth, gemHeight, GameCanvas.instance);
		g.drawImage(orangeGemImg, orangeGemX, orangeGemY, gemWidth, gemHeight, GameCanvas.instance);
		g.drawImage(greenGemImg, greenGemX, greenGemY, gemWidth, gemHeight, GameCanvas.instance);
		g.drawImage(blueGemImg, blueGemX, blueGemY, gemWidth, gemHeight, GameCanvas.instance);
		g.drawImage(chanceGemImg, chanceGemX, chanceGemY, gemWidth, gemHeight, GameCanvas.instance);

		for (int i = 0; i < 5; i++)
			cardStatus[i].paint(g); // 얘가 바뀌어야함

//		if (starListener != null && sparkle % 2 == 1) 
//			starListener.showStar();

		if (sparkle % 2 == 1) {
			g.drawImage(starImg, starX - 40, starY - 40, 80, 80, GameCanvas.instance);
			g.drawImage(starImg, starX + cardWidth - 40, starY + cardHeight - 40, 80, 80, GameCanvas.instance);
		}
	}

	// MyCard의 보석 개수를 업데이트하는 메서드가 필요할 것 같아서 추가
	public void update() {
		// 보석 개수를 업뎃하면서 미션카드와 보석 개수가 일치하면 onWin 실행하기 MissionCard에서 설정된 젬들을 불러와야함(구분은
		// cardType)

		// 처음엔 내가 모은 보석(카드)의 개수를 생성자에서는 0으로 초기화했지만 여기서는 +1씩 해줘야함
		int temp = gemY; // gemY값이 보석마다 달라서 바뀌기 때문에 임시변수로 temp로 넣었음
		for (int i = 0; i < 5; i++) {
			String status = "X " + Integer.toString(gemsCount[i]);

			if (i == 4)
				cardStatus[i] = new CardStatus(chanceGemX + 35, chanceGemY + 20, status);
			else
				cardStatus[i] = new CardStatus(gemX + 35, temp + 20, status);

			temp += 35;
		}

		if (sparkleTime >= 0) // sparkleTime != 0으로 하면 무한 깜빡임
			sparkleTime--;

		if (sparkleTime % 30 == 0) // (1000/30)초 간격으로 깜빡임
			sparkle++;

	}

	public int[] getGemsCount() {
		return gemsCount;
	}

	public void setGemsCount(int[] gemsCount) {
		this.gemsCount = gemsCount;
	}

}
