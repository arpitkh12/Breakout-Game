package com.p532.commands;

import com.p532.model.Paddle;

public class MovePaddleCommand implements Command{
	
	private Paddle paddle;
	
	public MovePaddleCommand(Paddle paddle) {
		super();
		this.paddle = paddle;
	}
	
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		paddle.movePaddle();
	}

	@Override
	public void unexecute() {
		// TODO Auto-generated method stub
		
	}

}
