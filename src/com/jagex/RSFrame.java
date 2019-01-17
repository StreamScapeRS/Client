package com.jagex;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.MouseWheelEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

@SuppressWarnings("all")
final class RSFrame extends JFrame {

	public static boolean destroy;

	final RSApplet rsApplet;

	Toolkit toolkit = Toolkit.getDefaultToolkit();

	Dimension screenSize = toolkit.getScreenSize();

	int screenWidth = (int) screenSize.getWidth();

	int screenHeight = (int) screenSize.getHeight();

	public RSFrame(RSApplet rsapplet, int width, int height, boolean undecorative, boolean resizable) {
		rsApplet = rsapplet;
		setTitle(Configuration.CLIENT_NAME);
		setUndecorated(undecorative);
		setResizable(resizable);

		setVisible(true);
		Insets insets = this.getInsets();
		setSize(width + insets.left + insets.right, height + insets.top + insets.bottom);
		GameClient.getClient();
		// setLocation((screenWidth - width) / 2,
		// ((screenHeight - height) / 2) - screenHeight ==
		// GameClient.getMaxHeight() ? 0 : undecorative ? 0 : 70);
		requestFocus();
		toFront();
		this.setFocusTraversalKeysEnabled(false);
		setBackground(Color.BLACK);

		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent we) {
				String options[] = { "Yes", "No" };
				int userPrompt = JOptionPane.showOptionDialog(null, "Are you sure you wish to exit?", "StreamScape Client",
						JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[1]);
				if (userPrompt == JOptionPane.YES_OPTION) {
					// other actions here??
					System.exit(0);
				}
			}
		});

	}

	public int getFrameHeight() {
		Insets insets = this.getInsets();
		return getHeight() - (insets.top + insets.bottom);
	}

	public int getFrameWidth() {
		Insets insets = this.getInsets();
		return getWidth() - (insets.left + insets.right);
	}

	@Override
	public Graphics getGraphics() {
		Graphics g = super.getGraphics();
		Insets insets = this.getInsets();
		g.translate(insets.left, insets.top);
		return g;
	}

	public void mouseWheelMoved(MouseWheelEvent event) {
		rsApplet.mouseWheelMoved(event);
	}

	@Override
	public void paint(Graphics g) {
		rsApplet.paint(g);
	}

	public void setClientIcon(Image image) {
		setIconImage(image);
	}

	@Override
	public void update(Graphics g) {
		rsApplet.update(g);
	}
}