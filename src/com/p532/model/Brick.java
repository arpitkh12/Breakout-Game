package com.p532.model;

import java.awt.Rectangle;

import com.p532.constants.Constants;

public class Brick implements Constants {
	private int brickx = brickX, bricky = brickY;
	private Rectangle[] brick;
	private boolean[] visibleBrick;

	public Brick() {
		setBrick(new Rectangle[brickNumber]);
		setVisibleBrick(new boolean[brickNumber]);
	}

	// To Create Array of Bricks
	public void createBricks() {
		int count = 0, x = brickx, y = bricky;
		for (int i = 0; i < getBrick().length; i++) {
			getBrick()[i] = new Rectangle(x, y, brickWidth, brickHeight);
			getVisibleBrick()[i] = true;
			x += getBrick()[i].width;
			count++;
			if (count == 5) {
				y += getBrick()[i].height;
				x = brickx;
				count = 0;
			}
		}
	}

	public boolean[] getVisibleBrick() {
		return visibleBrick;
	}

	public void setVisibleBrick(boolean[] visibleBrick) {
		this.visibleBrick = visibleBrick;
	}

	public Rectangle[] getBrick() {
		return brick;
	}

	public void setBrick(Rectangle[] brick) {
		this.brick = brick;
	}
}
