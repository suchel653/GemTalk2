package prj.entity;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.Random;

public abstract class Card {

//	private CardListener listener;
//	private Random rand;

	// move 메소드를 위한 좌표
	private int x;
	private int y;

	private int questionOrder; // 10개의 질문을 구분하는 변수
	private int cardType; // 0:red, 1:orange, 2:green, 3:blue, 4:chance, 5:action

	// 카드 사이즈
	private int width;
	private int height;
	private Image img;

	// 카드 갯수 카운트?
	private int missionCount;

	// 인자가 없는 생성자
	public Card() {
		this(0, 0);
	}

	public Card(int x, int y) {
		this(x, y, null);
	}

	public Card(int order, String string) {
		this(0, 0, string);
		questionOrder = order;
	}

	// 인자가 있는 생성자
	public Card(int x, int y, String imgSrc) {

		this.x = x;
		this.y = y;

		width = 154; // 카드 이미지 너비 ->이거는 생성자에
		height = 218; // 카드 이미지 높이 ->이거는 생성자에

		Toolkit tk = Toolkit.getDefaultToolkit();
		img = tk.getImage(imgSrc);

//		rand = new Random();

	}

	public abstract void paint(Graphics g);

	public boolean choiceCard(int x, int y) {
		int w = width;
		int h = height;
		int x1 = this.x;
		int y1 = this.y;
		int x2 = x1 + w;
		int y2 = y1 + h;

		if ((x1 < x && x < x2) && (y1 < y && y < y2)) {

			return true;
		} else {

			return false;
		}
	}

	public void zoomIn() {

	}

	public void zoomOut() {

	}

	public void giveOrTake(int playTurn) {
		
	}

	public int getMissionCount() {
		return missionCount;
	}

	public void setMissionCount(int missionCount) {
		this.missionCount = missionCount;
	}

	public int getQuestionOrder() {
		return questionOrder;
	}

	public void setQuestionOrder(int questionOrder) {
		this.questionOrder = questionOrder;
	}

	public int getCardType() {
		return cardType;
	}

	public void setCardType(int cardType) {
		this.cardType = cardType;
	}

	public Image getImg() {
		return img;
	}

	public void setImg(Image img) {
		this.img = img;
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

//	public void setListener(CardListener listener) {
//		this.listener = listener;
//	}

}
