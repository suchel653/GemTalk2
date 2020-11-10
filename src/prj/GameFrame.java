package prj;

import java.awt.Canvas;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JOptionPane;

import prj.canvas.GameCanvas;
import prj.canvas.GameCanvasListener;
import prj.canvas.IntroCanvas;
import prj.canvas.RuleCanvas;

public class GameFrame extends Frame {
	public static GameFrame instance;
	private IntroCanvas introCanvas;
	private RuleCanvas ruleCanvas;
	private GameCanvas gameCanvas;
	private Clip clip;

	public GameFrame() {
		instance = this;

		introCanvas = new IntroCanvas();
		gameCanvas = new GameCanvas();
		add(introCanvas);
		setSize(1250, 730);
		setVisible(true);

		File bgm = new File("res/헤네시스.wav");

		try {
			AudioInputStream stream = AudioSystem.getAudioInputStream(bgm);
			clip = AudioSystem.getClip();
			clip.open(stream);
			clip.start();
			clip.loop(Clip.LOOP_CONTINUOUSLY);

		} catch (Exception e) {

			e.printStackTrace();
		}

		gameCanvas.setCanvasListener(new GameCanvasListener() {

			@Override
			public void win() {
				clip.stop();

			}
		});

		addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				int input = JOptionPane.showConfirmDialog(GameFrame.this, "게임을 종료하시겠습니까?", "게임 종료",
						JOptionPane.OK_CANCEL_OPTION);

				if (input == JOptionPane.OK_OPTION)
					System.exit(0);
			}

		});

	}

	public void switchCanvas(Canvas oldCanvas, Class newCanvas) throws InstantiationException, IllegalAccessException {
		Canvas canvas;
//		remove(oldCanvas);
		if (newCanvas.getName().equals("prj.canvas.GameCanvas"))
			canvas = this.gameCanvas;
		else
			canvas = (Canvas) newCanvas.newInstance();

		add(canvas);
		oldCanvas.setVisible(false);
		revalidate();

	}

}
