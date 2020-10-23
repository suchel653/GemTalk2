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
		super(actionCardType,"res/actionCard.png");
		setCardType(5);
		this.actionCardType = actionCardType;
		rand = new Random();
	}

	@Override
	public void giveOrTake(int playTurn) {
		int randomPlayer = rand.nextInt(4);
		int randomCard = rand.nextInt(5);
		
		boolean ch=true;
		
		while(ch) {
			if(randomPlayer == playTurn)
				randomPlayer = rand.nextInt(4);
			else
				ch = false;
		}
		System.out.println("give or take");
		System.out.println("rand: " + (randomPlayer+1)+"번");
		System.out.println("play: " + (playTurn+1)+"번");
		
		if(actionCardType == 0) {
			if(actionListener != null) {
				System.out.println("give");
				actionListener.give(randomPlayer,randomCard);
			}
		}
		else {
			if(actionListener != null) {
				System.out.println("take");
				actionListener.take(randomPlayer,randomCard);
			}
		}	
	}

	@Override
	public void paint(Graphics g) {
		Image img = getImg(); 
		int x = getX();
		int y = getY();
		int w = img.getWidth(null)/6;
		int h = img.getHeight(null);
		int order = getQuestionOrder();
//		System.out.println("or: "+order);
		
		g.drawImage(img, x, y,x+154,y+218,0+w*order,0,w+w*order,h, GameCanvas.instance);
	}

	public void setActionListener(ActionCardListener actionListener) {
		this.actionListener = actionListener;
	}

	
}
