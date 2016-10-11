package com.p532.commands;

import com.p532.model.Ball;

public class MoveBallCommand implements Command{
	
	private Ball ball;
	
	public MoveBallCommand(Ball ball) {
		this.ball = ball;
	}
	
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		ball.moveBall();
		
	}

	

	@Override
	public void unexecute() {
		// TODO Auto-generated method stub
		ball.undoMoveBall();
	}

}
