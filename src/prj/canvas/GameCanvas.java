package prj.canvas;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JOptionPane;

import prj.entity.ActionCard;
import prj.entity.ActionCardListener;
import prj.entity.Bgm;
import prj.entity.BgmListener;
import prj.entity.BlueCard;
import prj.entity.Card;
import prj.entity.CardDeck;
import prj.entity.ChanceCard;
import prj.entity.GameBackground;
import prj.entity.GreenCard;
import prj.entity.OrangeCard;
import prj.entity.Player;
import prj.entity.PlayerListener;
import prj.entity.RedCard;
import prj.entity.TurnPointer;
import prj.entity.Win;
import prj.entity.ZoomOutListener;

public class GameCanvas extends Canvas {

	public static Canvas instance;
	private GameCanvasListener canvasListener;

	private Win win;

	private List<Card> cardList;
	private Card cardDeck;
	private Card card1;
	private Card card2;
	private Card chanceCard;
	private Random rand;
	private Player[] players;
	private Card temp;

	private GameBackground gameBackground;
	private int playTurn;

	private ActionCard actionCard;
	private TurnPointer turnPointer;

	private Bgm bgm;

	public GameCanvas() {
		instance = this;
		rand = new Random();
		gameBackground = new GameBackground();

		playTurn = 0;
		players = new Player[4];
		players[0] = new Player(0, 0, 1);
		players[1] = new Player(700, 0, 2);
		players[2] = new Player(0, 450, 3);
		players[3] = new Player(700, 450, 4);

		for (int i = 0; i < 4; i++) {
			players[i].setPlayerListener(new PlayerListener() {

				@Override
				public void win() {
					if (win == null) {
						win = new Win(playTurn);

						bgm = new Bgm();
						bgm.setBgmListener(new BgmListener() {

							@Override
							public void playgetCardBgm() {
								File bgm = new File("res/우승.wav");

								try {
									AudioInputStream stream = AudioSystem.getAudioInputStream(bgm);
									Clip clip = AudioSystem.getClip();
									clip.open(stream);
									clip.start();
								} catch (Exception e) {

									e.printStackTrace();
								}
							}
							
						});
						bgm.play();
					}

					canvasListener.win();
				}
			});

		}

		cardList = new ArrayList<>();
		cardDeck = new CardDeck(370 + 77, 245 + 109);

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

		card1.setX(544 + 77);
		card1.setY(245 + 109);
		card2.setX(718 + 77);
		card2.setY(245 + 109);

		temp = card1;
		turnPointer = new TurnPointer();

		addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				int x = e.getX();
				int y = e.getY();
				int voteCount = 0;
				int cardType = 0;
				
				if (card1.choiceCard(x, y)) {
					check(cardList.get(0));
					temp = card1;
					temp.zoomIn();
					players[playTurn].answer(playTurn);
					for (int i = 0; i < 4; i++)
						if (i != playTurn)
							if (players[playTurn].vote() == 0)
								voteCount++;

					if (voteCount >= 2) {
						cardType = temp.getCardType();
						players[playTurn].moveToPlayer(cardType);
						temp.move(playTurn);
						bgm = new Bgm();
						bgm.setBgmListener(new BgmListener() {

							@Override
							public void playgetCardBgm() {
								File bgm = new File("res/뾰로롱.wav");

								try {
									AudioInputStream stream = AudioSystem.getAudioInputStream(bgm);
									Clip clip = AudioSystem.getClip();
									clip.open(stream);
									clip.start();

								} catch (Exception e) {

									e.printStackTrace();
								}
							}
						});
						bgm.play();

					} else {
						JOptionPane.showMessageDialog(GameCanvas.instance, "투표결과가 과반수를 넘지못하여 카드를 획득하지 못했습니다.", "알림",
								JOptionPane.WARNING_MESSAGE);
						temp = card1;
					}

					temp.setZoomOutListener(new ZoomOutListener() {

						@Override
						public void zoomOut() {
							card1 = cardList.get(0);
							temp = card1;
							temp.zoomOut();
							card1.zoomOut();
							cardList.remove(0);
						}
					});

					playTurn = ++playTurn % 4;
					turnPointer.turn(playTurn);

				} else if (card2.choiceCard(x, y)) {
					check(cardList.get(0));
					temp = card2;
					temp.zoomIn();

					players[playTurn].answer(playTurn);
					for (int i = 0; i < 4; i++)
						if (i != playTurn)
							if (players[playTurn].vote() == 0)
								voteCount++;

					if (voteCount >= 2) {
						cardType = card2.getCardType();
						players[playTurn].moveToPlayer(cardType);
						temp.move(playTurn);
						bgm = new Bgm();
						bgm.setBgmListener(new BgmListener() {

							@Override
							public void playgetCardBgm() {
								File bgm = new File("res/뾰로롱.wav");

								try {
									AudioInputStream stream = AudioSystem.getAudioInputStream(bgm);
									Clip clip = AudioSystem.getClip();
									clip.open(stream);
									clip.start();

								} catch (Exception e) {

									e.printStackTrace();
								}
							}
						});
						bgm.play();

					} else {
						JOptionPane.showMessageDialog(GameCanvas.instance, "투표결과가 과반수를 넘지못하여 카드를 획득하지 못했습니다.", "알림",
								JOptionPane.WARNING_MESSAGE);
						temp = card2;
					}

					temp.setZoomOutListener(new ZoomOutListener() {

						@Override
						public void zoomOut() {
							card2 = cardList.get(0);
							temp = card2;
							temp.zoomOut2();
							card2.zoomOut2();
							cardList.remove(0);
						}
					});

					playTurn = ++playTurn % 4;
					turnPointer.turn(playTurn);

				} else if (cardDeck.choiceCard(x, y)) {
					temp = cardList.get(0);
					temp.zoomIn();

					if (cardList.get(0).getCardType() == 4) {
<<<<<<< HEAD
//						JOptionPane.showMessageDialog(instance, "찬스카드를 획득하셨습니다!");
						JOptionPane.showMessageDialog(instance, "찬스카드를 획득하셨습니다!", "축하합니다!", JOptionPane.INFORMATION_MESSAGE);
=======
						JOptionPane.showMessageDialog(instance, "찬스카드를 획득하셨습니다.", "축하합니다!!",
								JOptionPane.INFORMATION_MESSAGE);
>>>>>>> jaehee/main
						voteCount = 4;

					} else if (cardList.get(0).getCardType() == 5) {
<<<<<<< HEAD
//						JOptionPane.showMessageDialog(instance, "행동카드를 획득하셨습니다!");
						JOptionPane.showMessageDialog(instance, "행동카드를 획득하셨습니다!", "좋을지? 나쁠지?", JOptionPane.WARNING_MESSAGE);
=======
						JOptionPane.showMessageDialog(instance, "행동카드를 획득하셨습니다.", "좋을지? 나쁠지?",
								JOptionPane.WARNING_MESSAGE);
>>>>>>> jaehee/main
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

						cardList.get(0).giveOrTake(playTurn);
						voteCount = 4;
					} else {
						players[playTurn].answer(playTurn);

						for (int i = 0; i < 4; i++)
							if (i != playTurn)
								if (players[playTurn].vote() == 0)
									voteCount++;
					}

					if (voteCount >= 2) {
						cardType = cardList.get(0).getCardType();
						players[playTurn].moveToPlayer(cardType);
						temp.move(playTurn);
						bgm = new Bgm();
						bgm.setBgmListener(new BgmListener() {

							@Override
							public void playgetCardBgm() {
								File bgm = new File("res/뾰로롱.wav");

								try {
									AudioInputStream stream = AudioSystem.getAudioInputStream(bgm);
									Clip clip = AudioSystem.getClip();
									clip.open(stream);
									clip.start();
								} catch (Exception e) {

									e.printStackTrace();
								}
							}
							
						});
						bgm.play();

					} else {
						JOptionPane.showMessageDialog(GameCanvas.instance, "투표결과가 과반수를 넘지못하여 카드를 획득하지 못했습니다.", "알림",
								JOptionPane.WARNING_MESSAGE);
						temp = card2;
					}

					temp.setZoomOutListener(new ZoomOutListener() {

						@Override
						public void zoomOut() {
							temp = card2;
							cardList.remove(0);
						}
					});

					playTurn = ++playTurn % 4;
					turnPointer.turn(playTurn);
				}

			}

		});

		new Thread(new Runnable() {

			@Override
			public void run() {

				while (true) {

					for (int i = 0; i < 4; i++)
						players[i].update();

					card1.update();
					card2.update();
					cardDeck.update();
					temp.update();
					if (win != null) {
						try {
							win.update();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}

					repaint();

					try {
						Thread.sleep(17);
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
			} else
				check = false;

		}
	}

	@Override
	public void paint(Graphics g) {
		Image buf = this.createImage(getWidth(), getHeight());
		Graphics bg = buf.getGraphics();

		gameBackground.paint(bg);

		for (int i = 0; i < 4; i++)
			players[i].paint(bg);

		turnPointer.paint(bg);
		cardDeck.paint(bg);
		card1.paint(bg);
		card2.paint(bg);
		temp.paint(bg);
		if (win != null)
			win.paint(bg);

		g.drawImage(buf, 0, 0, this);
	}

	@Override
	public void update(Graphics g) {
		paint(g);
	}

	public void setCanvasListener(GameCanvasListener canvasListener) {
		this.canvasListener = canvasListener;
	}

}