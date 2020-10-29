package prj.entity;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.Random;

import prj.canvas.GameCanvas;

public abstract class Card {

	private int x;
	private int y;
	private double dx;
	private double dy;
	private double vx;
	private double vy;
	private int speed;

	private int questionOrder; // 10개의 질문을 구분하는 변수
	private int cardType; // 0:red, 1:orange, 2:green, 3:blue, 4:chance, 5:action

	private int width;
	private int height;
	private Image img;
	private double sizeIndex;

	private int missionCount;

	private ZoomOutListener zoomOutListener;

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

	public Card(int x, int y, String imgSrc) {

		this.x = x;
		this.y = y;

		width = 154; 
		height = 218;

		Toolkit tk = Toolkit.getDefaultToolkit();
		img = tk.getImage(imgSrc);
		speed = 3;
		sizeIndex = 1;

	}

	public abstract void paint(Graphics g);

	public boolean choiceCard(int x, int y) {

		int w = width;
		int h = height;
		int x1 = this.x - w / 2;
		int y1 = this.y - h / 2;
		int x2 = x1 + w;
		int y2 = y1 + h;

		if ((x1 < x && x < x2) && (y1 < y && y < y2)) {
			return true;
		} else {
			return false;
		}
	}

	public void move(int playTurn) {

		switch (playTurn) {
		case 0:
			dx = 350 + 75;
			dy = 45 + 75;
			break;
		case 1:
			dx = 700 + 75 + 200;
			dy = 45 + 75;
			break;
		case 2:
			dx = 350 + 75;
			dy = 445 + 75;
			break;
		case 3:
			dx = 700 + 75;
			dy = 445 + 75;
			break;
		}

		double w = this.dx - this.x;
		double h = this.dy - this.y;
		double d = (int) Math.sqrt(w * w + h * h);
		this.vx = w * 3 / d;
		this.vy = h * 3 / d;

	}

	public void update() {

		if (width <= 10 && height <= 10) {
			vx = 0;
			vy = 0;
		}

		if (vx != 0 || vy != 0) {
			width *= 0.97;
			height *= 0.97;
		}

		if (zoomOutListener != null && vx == 0 && vy == 0)
			zoomOutListener.zoomOut();

		x += vx;
		y += vy;

	}

	public void zoomIn() {

		x = 544 + 77;
		y = 245 + 109;

		width = 500;
		height = 640;

	}

	public void zoomOut() {
		x = 544 + 77;
		y = 245 + 109;

		width = 154;
		height = 218;
	}

	public void zoomOut2() {
		x = 718 + 77;
		y = 245 + 109;

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

	public void setWidth(double width) {
		this.width = (int) width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public double getDx() {
		return dx;
	}

	public void setDx(double dx) {
		this.dx = dx;
	}

	public double getDy() {
		return dy;
	}

	public void setDy(double dy) {
		this.dy = dy;
	}

	public void setVy(double vy) {
		this.vy = vy;
	}

	public double getVx() {
		return vx;
	}

	public void setVx(double vx2) {
		this.vx = vx2;
	}

	public double getVy() {
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

	public void setZoomOutListener(ZoomOutListener zoomOutListener) {
		this.zoomOutListener = zoomOutListener;
	}

}
