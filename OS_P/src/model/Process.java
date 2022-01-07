package model;

import java.util.*;

public class Process {
	int PID;
	double startTime;
	double duration;
	int size;
	ArrayList<Page> pages = new ArrayList<Page>();
	
	public Process(int PID, double startTime, double duration, int size, ArrayList<Page>pages) {
		this.PID = PID;
		this.startTime = startTime;
		this.duration = duration;
		this.size = size;
		this.pages = pages;
	}
	
	public Process() {
		
	}
	
}
