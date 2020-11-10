package prj.canvas;

import java.awt.Canvas;
import java.awt.Event;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import prj.entity.RuleButton;
import prj.entity.RuleBackground;

public class RuleCanvas extends Canvas {
	
	public static RuleCanvas instance;
	private RuleBackground background;
	private RuleButton button;

	public RuleCanvas() {
		instance = this;
		background = new RuleBackground();
		button = new RuleButton();

		addMouseListener(new MouseAdapter() {

			@Override
			public void mouseReleased(MouseEvent e) {
				int x = e.getX();
				int y = e.getY();

				if (button.click(x, y)) {
					try {
						prj.GameFrame.instance.switchCanvas(RuleCanvas.this, IntroCanvas.class);
					} catch (InstantiationException e1) {
						e1.printStackTrace();
					} catch (IllegalAccessException e1) {
						e1.printStackTrace();
					}
				}

			}

		});

	}

	@Override
	public void update(Graphics g) {
		paint(g);
	}

	@Override
	public void paint(Graphics g) {
		Image buf = this.createImage(this.getWidth(), getHeight());
		Graphics bg = buf.getGraphics();

		background.paint(bg);
		button.paint(bg);

		g.drawImage(buf, 0, 0, this);//
	}
}
