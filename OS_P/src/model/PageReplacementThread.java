package model;

import application.Simulator;

public class PageReplacementThread extends Simulator implements Runnable {
	
	
	@Override
	public void run() {
		SchedulerThread.time +=300;
	}
}
