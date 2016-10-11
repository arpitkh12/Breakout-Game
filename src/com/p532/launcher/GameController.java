package com.p532.launcher;
import static com.p532.constants.Constants.*;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.p532.View.CanvasView;
import com.p532.View.GamePanel;
import com.p532.commands.MoveBallCommand;
import com.p532.constants.Constants;
import com.p532.model.Ball;
import com.p532.model.Brick;
import com.p532.model.Paddle;
import com.p532.timer.ObservableClass;
import com.p532.timer.Observer;

public class GameController implements Observer, KeyListener, ActionListener {

	private Ball ball;
	private Paddle paddle;
	private GamePanel gamePanel;
	private Brick brick;
	private CanvasView canvasView;
	private ObservableClass observableClass;
	private int count = 0, flagcount;
	boolean flagUndo = false, flagReplay = false, flagUndoON = false;
	private JSONArray frames;
	private int timer;

	GameController(CanvasView canvasView) {
		this.canvasView = canvasView;
		this.canvasView.addPaddleMovement(this);
		this.canvasView.timerPanel.addReplayListner(this);
		this.canvasView.timerPanel.addStartListner(this);
		this.canvasView.timerPanel.addPauseListner(this);
		this.canvasView.timerPanel.addUndoListner(this);
		observableClass = new ObservableClass();
		frames = new JSONArray();
		gamePanel = this.canvasView.gamePanel;
		brick = gamePanel.getBrick();
		ball = gamePanel.getBall();
		paddle = gamePanel.getPaddle();
		brick.createBricks();
		gamePanel.repaint();
		observableClass.registerObserver(canvasView.timerPanel);
		observableClass.registerObserver(this);
		observableClass.StartTimer();
		addObjtoJSONArray(-1);
	}

	public void collisionBat() {
		if (getBounds().intersects(paddle.getPaddle())) {
			int vely = ball.getVely();
			vely = -vely;
			ball.setVely(vely);
		}
	}

	public void collisionBrick() {
		if (flagUndo == false) {
			for (int i = 0; i < brick.getBrick().length; i++) {
				if (getBounds().intersects(brick.getBrick()[i]) && brick.getVisibleBrick()[i] == true) {
					int vely = ball.getVely();
					count++;
					vely = -vely;
					ball.setVely(vely);
					brick.getVisibleBrick()[i] = false;
					addObjtoJSONArray(i);
					break;
				}
			}
		}

	}

	public void undoCollisionBrick() {
		if (flagUndo == true) {
			for (int i = 0; i < brick.getBrick().length; i++) {
				if (getBounds().intersects(brick.getBrick()[i]) && brick.getVisibleBrick()[i] == false) {
					int vely = ball.getVely();
					count--;
					vely = -vely;
					ball.setVely(vely);
					brick.getVisibleBrick()[i] = true;
					break;
				}
			}
		}
	}

	@Override
	public void update(int counter) {
		if (flagUndo == false) {
			timer = counter;
			ball.moveBall();
			collisionBat();
			collisionBrick();
			gamePanel.repaint();
			if (count == brick.getBrick().length) {
				gamePanel.gameWon();
			}
		}

		else {
			undo(counter);
		}

		if (flagReplay == true) {
			replay(counter);
		}

	}

	public void undo(int counter) {
		if (flagcount == counter) {
			observableClass.invertTimer();
			flagUndoON = false;
			flagUndo = false;
			ball.setVelx(-ball.getVelx());
			ball.setVely(-ball.getVely());
		}
		int index = frames.size() - 1;
		for (int i = index; i > 0; i--) {
			JSONObject obj = (JSONObject) frames.get(i);
			int time;

			time = (int) obj.get("time");
			if (time == counter) {
				paddle.getPaddle().x = (int) obj.get("paddlex");
				gamePanel.repaint();
			}

		}

		ball.moveBall();
		undoCollisionBrick();
		collisionBat();
		gamePanel.repaint();
	}

	public void replay(int counter) {
		int index = frames.size();
		for (int i = 0; i < index; i++) {
			JSONObject obj = (JSONObject) frames.get(i);
			int time;
			time = (int) obj.get("time");
			if (time == counter - 1) {
				paddle.getPaddle().x = (int) obj.get("paddlex");
				gamePanel.repaint();
			}

		}

		collisionBrick();
		gamePanel.repaint();
	}

	public Rectangle getBounds() {
		return (new Rectangle(ball.getBallx(), ball.getBally(), ball.getBall().getIconWidth(), ball.getBall().getIconHeight()));
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		paddle.setE(e);
		paddle.movePaddle();
		gamePanel.repaint();
		addObjtoJSONArray(-1);

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@SuppressWarnings("unchecked")
	public void addObjtoJSONArray(int brickId) {
		JSONObject obj = new JSONObject();
		obj.put("time", timer);
		obj.put("velx", ball.getVelx());
		obj.put("vely", ball.getVely());
		obj.put("brickId", brickId);
		obj.put("paddlex", paddle.getPaddle().x);
		frames.add(obj);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		switch (command) {
		case "Start/Restart":
			flagUndo = false;
			flagReplay = false;
			flagUndoON = false;
			paddle.getPaddle().x = Constants.paddleX;
			paddle.getPaddle().y = Constants.paddleY;
			ball.setBallx(Constants.ballX);
			ball.setBally(Constants.ballY);
			ball.setVelx(Constants.ballVelX);
			ball.setVely(Constants.ballVelY);
			for (int i = 0; i < Constants.brickNumber; i++) {
				brick.getVisibleBrick()[i] = true;
			}
			count = 0;
			observableClass.setCounter(0);
			observableClass.setIncrementor(1);
			break;

		case "Pause":
			canvasView.timerPanel.btnPause.setText("UnPause");
			observableClass.removeObserver(canvasView.timerPanel);
			observableClass.removeObserver(this);
			break;

		case "UnPause":
			canvasView.timerPanel.btnPause.setText("Pause");
			observableClass.registerObserver(canvasView.timerPanel);
			observableClass.registerObserver(this);
			break;

		case "Undo":
			if (flagUndoON == true) {
				flagcount -= 1000;
			}
			if (flagUndoON == false) {
				flagUndoON = true;
				flagUndo = true;
				observableClass.invertTimer();
				flagcount = timer - 100;
				MoveBallCommand m = new MoveBallCommand(ball);
				m.unexecute();
			}
			break;

		case "Replay":
			count = 0;
			ball.setBallx(ballX);
			ball.setBally(ballY);
			ball.setVelx(ballVelX);
			ball.setVely(ballVelY);
			Arrays.fill(brick.getVisibleBrick(), true);
			flagReplay = true;
			observableClass.setCounter(0);
			addObjtoJSONArray(-1);
			break;

		}

	}
}