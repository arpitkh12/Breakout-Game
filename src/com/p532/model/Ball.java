package com.p532.model;

import javax.swing.ImageIcon;

import com.p532.constants.Constants;

public class Ball implements Constants {
	private ImageIcon ball = new ImageIcon(PIC_BALL);
	private int ballx = ballX, bally = ballY;
	private int velx = ballVelX, vely = ballVelY;

	public void moveBall() {
		if (bally >= gamePanelHeight - getBall().getIconHeight()) {
			System.exit(1);
		}

		if (ballx <= 0 || ballx > gamePanelWidth - getBall().getIconWidth()) {
			velx = -velx;
		}

		if (bally <= 0) {
			vely = -vely;
		}

		ballx+=velx;
		bally+=vely;
		//System.out.println(ballx+"  "+bally);
	}
	
	/*public void undoMoveBall(){
		velx=-velx;
		vely=-vely;
//		System.out.println(velx+ " "+ " ballx"+ ballx+ " vely" + vely);
//		ballx-=velx-velx;
//		System.out.println(velx+ " "+ " ballx"+ ballx+ " vely" + vely);
//		bally-=vely-vely;
//		if(bally>=BreakOut.gameHeightPanel-30){
//			System.exit(1);
//		}
//		
//		if(ballx<=0 || ballx>BreakOut.gameWidthPanel-30){
//			velx=-velx;
//		}
//		
//		if(bally<=0){
//			vely=-vely;
//		}
//		ballx+=velx;
//		bally+=vely;

		ballx += velx;
		bally += vely;

	}*/

	public void undoMoveBall() {
		velx = -velx;
		vely = -vely;
	}

	public int getBallx() {
		return ballx;
	}

	public void setBallx(int ballx) {
		this.ballx = ballx;
	}

	public int getBally() {
		return bally;
	}

	public void setBally(int bally) {
		this.bally = bally;
	}

	public int getVelx() {
		return velx;
	}

	public void setVelx(int velx) {
		this.velx = velx;
	}

	public int getVely() {
		return vely;
	}

	public void setVely(int vely) {
		this.vely = vely;
	}

	public ImageIcon getBall() {
		return ball;
	}

	public void setBall(ImageIcon ball) {
		this.ball = ball;
	}

}