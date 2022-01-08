package model;

import java.util.*;

public class Process {
	public int PID;
	public double startTime;
	public double duration;
	public int size;
	public ArrayList<Page> pages = new ArrayList<Page>();
	public boolean isFinished;
	public double turnaround;
	public double finishTime;
	public double waitTime;
	
	public Process(int PID, double startTime, double duration, int size, ArrayList<Page>pages) {
		this.PID = PID;
		this.startTime = startTime;
		this.duration = duration;
		this.size = size;
		this.pages = pages;
		this.isFinished = false;
		this.waitTime = 0;
	}
	
	public Process() {
		
	}
	
}
