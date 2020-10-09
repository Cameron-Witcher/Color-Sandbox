package me.cameron.tp4;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Screen extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int DELAY = 1;
	Timer timer;
	Random random;

	double frequency = 0.05;

	double cx = 0;
	double cy = 0;
	
	double phase1 = 2/Math.PI/3;
	double phase2 = 4/Math.PI/3;

	public Screen() {
		setBackground(Color.WHITE);
		timer = new Timer(DELAY, this);
		timer.start();
		random = new Random();
		MouseListener mouseListener = new MouseListener();
		addKeyListener(new TAdapter());
		addMouseMotionListener(mouseListener);
		addMouseListener(mouseListener);
		setFocusable(true);
		requestFocusInWindow();
		load();

	}

	public void load() {

	}

	public void unload() {

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		repaint();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		int red = 0;
		int green = 0;
		int blue = 0;
		int amp = 127;
		int center = Math.abs(255-amp);
		int size = 5;
		
		for (int k = 0; k != getHeight()/size; k++) {
			for (int i = 0; i != getWidth()/size; i++) {
				red   = (int) (Math.sin((frequency)*i + 0) * amp + center);
				green = (int) (Math.sin((frequency)*i + phase1) * amp + center);
				blue  = (int) (Math.sin((frequency)*i + phase2) * amp + center);
				g.setColor(new Color(red,green,blue));
				g.fillRect(i*size, k*size, size, size);

			}
		}

		g.dispose();
	}

	private void reset() {
	}

	private class TAdapter extends KeyAdapter {

		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_R) {
				unload();
				load();
			}
			if (e.getKeyCode() == KeyEvent.VK_SPACE) {
				pause();
			}
			if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
				reset();
				load();
			}

		}
	}

	private class MouseListener extends MouseAdapter {

		@Override
		public void mouseClicked(MouseEvent e) {

		}

		@Override
		public void mouseMoved(MouseEvent e) {

			// mx = e.getX();
			// my = e.getY();

		}
	}

	public void pause() {
	}

}
