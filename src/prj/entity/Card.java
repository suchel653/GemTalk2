package prj.entity;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.Random;

import prj.canvas.GameCanvas;

public abstract class Card {

	// private CardListener listener;
	// private Random rand;

	// move 메소드를 위한 좌표
	private int x;
	private int y;
	private int dx;
	private int dy;
	private int vx;
	private int vy;
	private int speed;

	private int questionOrder; // 10개의 질문을 구분하는 변수
	private int cardType; // 0:red, 1:orange, 2:green, 3:blue, 4:chance, 5:action

	// 카드 사이즈
	private int width;
	private int height;
	private Image img;
	private double sizeIndex;

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
		speed = 3;
		sizeIndex = 1;

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

	public void move(int dx, int dy) {

		this.dx = dx;
		this.dy = dy;

		int w = this.dx - this.x;
		int h = this.dy - this.y;
		int d = (int) Math.sqrt(w * w + h * h);

//		setVx(w / d * speed);
//		setVy(h / d * speed);
		this.vx = w * speed / d;
		this.vy = h * speed / d;
		System.out.println(vx);
		System.out.println(vy);

	}

	public void update() {

		if ((dx - 15 <= x && x <= dx + 15) && (dy - 15 <= y && y <= dy + 15)) {
			vx = 0;
			vy = 0;
		}

		x += vx;
		y += vy;

		sizeIndex *= 0.9;

	}

	public void zoomIn() {
		x = 370;
		y = 45;
		
		width = 500;
		height = 640;

	}

	public void zoomOut() {
		// 만약 card1를 클릭시 card1 위치
		x = 544;
		y = 245;

		width = 154;
		height = 218;
	}

	public void zoomOut2() {
		// 만약 card2를 클릭시 card2 위치
		 x = 718;
		 y = 245;

		width = 154;
		height = 218;
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

	public int getDx() {
		return dx;
	}

	public void setDx(int dx) {
		this.dx = dx;
	}

	public int getDy() {
		return dy;
	}

	public void setDy(int dy) {
		this.dy = dy;
	}

	public int getVx() {
		return vx;
	}

	public void setVx(int vx) {
		this.vx = vx;
	}

	public int getVy() {
		return vy;
	}

	public void setVy(int vy) {
		this.vy = vy;
	}

	public double getSizeIndex() {
		return sizeIndex;
	}

	public void setSizeIndex(double sizeIndex) {
		this.sizeIndex = sizeIndex;
	}

}
