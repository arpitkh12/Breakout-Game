package com.p532.View;

import javax.swing.*;

import com.p532.constants.Constants;
import com.p532.model.Ball;
import com.p532.model.Brick;
import com.p532.model.Paddle;

import java.awt.*;

public class GamePanel extends JPanel implements Constants {
	private Paddle paddle;
	private Ball ball;
	private Brick brick;

	GamePanel() {
		ball = new Ball();
		paddle = new Paddle();
		brick = new Brick();
		setSize(gamePanelWidth, gamePanelHeight);
		/* game background color */
		setBackground(Color.BLACK);
		setPreferredSize(new Dimension(gamePanelWidth, gamePanelHeight));
	}

	@Override
	public void paint(Graphics g) {
		super.paintComponent(g);
		/* Paddle color */
		g.setColor(new Color(41, 182, 255));
		g.fill3DRect(paddle.getPaddle().x, paddle.getPaddle().y, paddleWidth, paddleHeight, true);
		g.drawImage(ball.getBall().getImage(), ball.getBallx(), ball.getBally(), null);
		/* brick color */
		g.setColor(new Color(191, 54, 12));
		for (int i = 0; i < brick.getBrick().length; i++) {
			if (brick.getVisibleBrick()[i] == true) {
				g.fill3DRect(brick.getBrick()[i].x, brick.getBrick()[i].y, brick.getBrick()[i].width, brick.getBrick()[i].height, true);
			}
		}
		// getToolkit().getDefaultToolkit().sync();
		g.dispose();
	}

	public void gameWon() {
		JOptionPane.showMessageDialog(this, "You Won");
		System.exit(1);
	}

	public void gameOver() {
		JOptionPane.showMessageDialog(this, "You Lose");
		System.exit(1);
	}

	public Paddle getPaddle() {
		return paddle;
	}

	public Ball getBall() {
		return ball;
	}

	public Brick getBrick() {
		return brick;
	}
}