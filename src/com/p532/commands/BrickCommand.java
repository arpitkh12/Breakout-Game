package com.p532.commands;
import com.p532.model.Brick;

public class BrickCommand implements Command{
	private Brick brick;
	
	public BrickCommand(Brick brick) {
		super();
		this.brick = brick;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void unexecute() {
		// TODO Auto-generated method stub
		
	}

}
