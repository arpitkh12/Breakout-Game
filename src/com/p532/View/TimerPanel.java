package com.p532.View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.p532.constants.Constants;
import com.p532.timer.Observer;

public class TimerPanel extends JPanel implements Observer,Constants {
	public JLabel timeLbl = new JLabel();
	public JButton btnUndo = new JButton("Undo");
	public JButton btnStart = new JButton("Start/Restart");
	public JButton btnPause = new JButton("Pause");
	public JButton btnReplay = new JButton("Replay");
	public SimpleDateFormat df = new SimpleDateFormat("mm:ss:SSS");

	public TimerPanel() {
		this.setSize(timerPanelWidth, timerPanelHeight);
		setPreferredSize(new Dimension(timerPanelWidth, timerPanelHeight));
		int clockTick = 0;
		double clockTime = ((double) clockTick) / 10.0;
		String clockTimeString = new Double(clockTime).toString();
		Font myClockFont = new Font("Serif", Font.BOLD, 50);
		btnUndo.setFocusable(false);
		btnPause.setFocusable(false);
		btnReplay.setFocusable(false);
		btnStart.setFocusable(false);
		timeLbl.setFont(myClockFont);
		timeLbl.setText(clockTimeString);
		timeLbl.setForeground(new Color(41, 182, 255));
		setBackground(new Color(20, 22, 90));
		add(timeLbl);
		add(btnUndo);
		add(btnPause);
		add(btnReplay);
		add(btnStart);

	}

	public void addUndoListner(ActionListener listenerForReplayButton) {
		btnUndo.addActionListener(listenerForReplayButton);
	}

	public void addReplayListner(ActionListener listenerForReplayButton) {
		btnReplay.addActionListener(listenerForReplayButton);
	}

	public void addPauseListner(ActionListener listenerForReplayButton) {
		btnPause.addActionListener(listenerForReplayButton);
	}

	public void addStartListner(ActionListener listenerForReplayButton) {
		btnStart.addActionListener(listenerForReplayButton);
	}

	@Override
	public void update(int counter) {

		timeLbl.setText(df.format(counter * 10));
	}

}
