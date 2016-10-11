package com.p532.launcher;

import com.p532.View.CanvasView;

public class BreakOut {
	public static void main(String[] args) {
		CanvasView canvasView = new CanvasView();
		GameController bricksController = new GameController(canvasView);
	}
}
