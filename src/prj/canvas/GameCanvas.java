package prj.canvas;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javax.swing.plaf.metal.MetalBorders.PaletteBorder;

import prj.entity.ActionCard;
import prj.entity.ActionCardListener;
import prj.entity.BlueCard;
import prj.entity.Card;
import prj.entity.CardDeck;
import prj.entity.CardListener;
import prj.entity.ChanceCard;
import prj.entity.GameBackground;
import prj.entity.GameBoardBackground;
import prj.entity.GreenCard;
import prj.entity.MyCard;
import prj.entity.MyCardListener;
import prj.entity.OrangeCard;
import prj.entity.Player;
import prj.entity.PlayerListener;
import prj.entity.RedCard;
import prj.entity.TurnPointer;

public class GameCanvas extends Canvas {

	public static Canvas instance;

	private List<Card> cardList;
	private Card cardDeck;
	private Card card1;
	private Card card2;
	private Random rand;
	private GameBoardBackground gameBoardBackground;
	private Player[] players;

	private GameBackground gameBackground;
	private int playTurn;

	private ActionCard actionCard;
	private TurnPointer turnPointer;
	// private MyCard myCard; // 인터페이스 때문에 정의

//	private PlayerBoard[] playerBoards = new PlayerBoard[4]; // 플레이어 보드 4개 생성

	public GameCanvas() {
		instance = this;
		rand = new Random();

		gameBackground = new GameBackground();
		gameBoardBackground = new GameBoardBackground(350, 225);

		playTurn = 0;
		players = new Player[4];
		players[0] = new Player(0, 0, 1);
		players[1] = new Player(700, 0, 2);
		players[2] = new Player(0, 450, 3);
		players[3] = new Player(700, 450, 4);

		for (int i = 0; i < 4; i++)
			players[i].setPlayerListener(new PlayerListener() {

				@Override
				public void onWin() {
					System.out.println("소켓이 없어서 누군지는 모르지만 누군가 승리했습니다!");
//					if(win)
//						imgWin.paint
//					else(lose)
//						imgLose.paint

				}
			});

		cardList = new ArrayList<>();
		cardDeck = new CardDeck(370, 245);

		for (int i = 0; i < 50; i++)
			if (i < 10)
				cardList.add(new RedCard(i % 10));
			else if (i < 20)
				cardList.add(new GreenCard(i % 10));
			else if (i < 30)
				cardList.add(new OrangeCard(i % 10));
			else if (i < 40)
				cardList.add(new BlueCard(i % 10));
			else if (i < 45)
				cardList.add(new ActionCard(i % 2));
			else
				cardList.add(new ChanceCard());

		Collections.shuffle(cardList);

		check(cardList.get(0));
		card1 = cardList.get(0);
		cardList.remove(0);
		check(cardList.get(0));
		card2 = cardList.get(0);
		cardList.remove(0);

		turnPointer = new TurnPointer();

		addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				int x = e.getX();
				int y = e.getY();
				int voteCount = 0;
				int cardType = 0;

				if (card1.choiceCard(x, y)) {
					check(cardList.get(0)); // 체크하면서 찬스, 행동카드가 나오면 cardList 맨뒤로 보내는 작업
					Card temp = cardList.get(0);
//					card1.zoomIn();// zoomin

					players[playTurn].answer(playTurn); // 대답하고 대답한 내용을 띄우기 까지함
					for (int i = 0; i < 4; i++)
						if (i != playTurn)
							if (players[playTurn].vote() == 0)
								voteCount++;

					if (voteCount >= 2) {
						cardType = card1.getCardType();// move - myCard 연계
						players[playTurn].moveToPlayer(cardType);

						// 플레이어에게 카드가 채워지는 모션 코드 채워넣기

					} else {
						// 과반수를 넘지 못했으므로 카드를 획득하지 못했다는 알림 코드 채워넣기
					}

//					gameBoard.zoomOut1();// zoomout 객체는 살아있지만 paint는 안되는

					card1 = temp; // zoomout 직후에 다음 card1을 paint하기 위함
					cardList.remove(0); // 카드덱 맨위에 있는 card가 card1에 그려졌으므로 삭제

					playTurn = ++playTurn % 4; // playTurn: 0 ~ 3
					turnPointer.turn(playTurn);
				} else if (card2.choiceCard(x, y)) {
//					gameBoard.zoomIn2();
					check(cardList.get(0));
					Card temp = cardList.get(0);

					players[playTurn].answer(playTurn);
					for (int i = 0; i < 4; i++)
						if (i != playTurn)
							if (players[playTurn].vote() == 0)
								voteCount++;

					if (voteCount >= 2) {
						cardType = card2.getCardType();// move - myCard 연계
						players[playTurn].moveToPlayer(cardType);

						// 플레이어에게 카드가 채워지는 모션 코드 채워넣기

					} else {
						// 과반수를 넘지 못했으므로 카드를 획득하지 못했다는 알림 코드 채워넣기
					}

//					gameBoard.zoomOut2();

					card2 = temp;
					cardList.remove(0);

					playTurn = ++playTurn % 4;
					turnPointer.turn(playTurn);
				} else if (cardDeck.choiceCard(x, y)) {
//					cardList.get(0).zoomIn();

					if (cardList.get(0).getCardType() == 4) {
						voteCount = 4;
						System.out.println("lucky");

					} else if (cardList.get(0).getCardType() == 5) {
						actionCard = (ActionCard) cardList.get(0);
						actionCard.setActionListener(new ActionCardListener() {

							@Override
							public void give(int randomPlayer, int randomCard) {
								int cardType = players[playTurn].takeCard(randomCard);
								players[randomPlayer].moveToPlayer(cardType);

							}

							@Override
							public void take(int randomPlayer, int randomCard) {
								int cardType = players[randomPlayer].takeCard(randomCard);
								players[playTurn].moveToPlayer(cardType);

							}

						});

						cardList.get(0).giveOrTake(playTurn); // ActionCard의 giveOrTake 실행
						voteCount = 4;
					} else {
						players[playTurn].answer(playTurn);

						for (int i = 0; i < 4; i++)
							if (i != playTurn)
								if (players[playTurn].vote() == 0)
									voteCount++;
					}

					if (voteCount >= 2) {
						cardType = cardList.get(0).getCardType();// move - myCard 연계
						players[playTurn].moveToPlayer(cardType);

						// 플레이어에게 카드가 채워지는 모션 코드 채워넣기

					} else {
						// 과반수를 넘지 못했으므로 카드를 획득하지 못했다는 알림 코드 채워넣기
					}

					cardList.remove(0);
					playTurn = ++playTurn % 4;
					turnPointer.turn(playTurn);
				}

//				cardList.remove(0);// zoomout역할

			}

		});

		new Thread(new Runnable() {

			@Override
			public void run() {

				while (true) {

					for (int i = 0; i < 4; i++) {
						// 얘를 해줘야 repaint를 할때 변경된 부분이 적용되어 다시 그려진다.
						players[i].update();
					}
//					if(cardList.get(0) == null) {
//						
//						System.exit(0);
//					}
					// repaint() -> Canvas.update()가 화면을 지움 -> Canvas.paint(g)가 다시 그림
					repaint(); // 이걸 안하면 시작화면에서 그대로 멈춤(그린걸 지우고 다시 그리지를 않으므로)

					try {
						Thread.sleep(17); // 60fps(1초에 60번 while문 반복)
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

				}

			}
		}).start();

	}

	public void check(Card card) {
		boolean check = true;

		while (check) {
			if (cardList.get(0).getCardType() == 4 || cardList.get(0).getCardType() == 5) {
				cardList.remove(0);
				cardList.add(cardList.get(0));
				System.out.println(check);
			} else
				check = false;

		}
	}

	@Override
	public void paint(Graphics g) {
		Image buf = this.createImage(getWidth(), getHeight());
		Graphics bg = buf.getGraphics();

		gameBackground.paint(bg);
		gameBoardBackground.paint(bg);

		for (int i = 0; i < 4; i++)
			players[i].paint(bg);

		card1.setX(cardDeck.getX() + cardDeck.getWidth() + 20);
		card1.setY(cardDeck.getY());
		card2.setX(cardDeck.getX() + (cardDeck.getWidth() + 20) * 2);
		card2.setY(cardDeck.getY());

		cardDeck.paint(bg);
		card1.paint(bg);
		card2.paint(bg);
		turnPointer.paint(bg);

		g.drawImage(buf, 0, 0, this);
	}

	@Override
	public void update(Graphics g) {
		paint(g);
	}

}