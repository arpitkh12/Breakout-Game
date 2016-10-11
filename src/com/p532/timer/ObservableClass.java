package com.p532.timer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Timer;

public class ObservableClass implements Observable {

	private ActionListener action;
	private Timer Time;
	private int delay = 10;
	private ArrayList<Observer> observers = new ArrayList<Observer>();
	private int counter = 0, incrementor = 1;

	public void setIncrementor(int incrementor) {
		this.incrementor = incrementor;
	}

	public ObservableClass() {
		action = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (counter < 0) {
					Time.stop();
				} else {
					counter += incrementor;
					setCounter(counter);
				}

			}
		};
	}

	public void invertTimer() {
		incrementor = -incrementor;
	}

	public int StartTimer() {
		int s = 0;
		Time = new Timer(delay, action);
		s = s + delay;
		Time.setInitialDelay(0);
		Time.start();
		return s;
	}

	public int getCounter() {
		return counter;
	}

	public void setCounter(int counter) {
		this.counter = counter;
		notifyObservers();
	}

	@Override
	public void registerObserver(Observer observer) {
		incrementor = 1;
		observers.add(observer);
	}

	@Override
	public void removeObserver(Observer observer) {
		incrementor = 0;
		observers.remove(observer);
	}

	@Override
	public void notifyObservers() {
		for (Observer ob : observers) {
			ob.update(this.counter);
		}
	}
}