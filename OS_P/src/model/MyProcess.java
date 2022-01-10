package model;

import java.util.ArrayList;

public class MyProcess {
	public int PID;
	public double startTime;
	public double duration;
	public int size;
	public ArrayList<Page> pages = new ArrayList<Page>();
	public boolean isFinished;
	public double turnaround;
	public double finishTime;
	public double waitTime;
	public boolean entered;
	public int pagePointer;
	public int pageFaults;
	public int pagesNum;
	
	public MyProcess(int PID, double startTime, double duration, int size, ArrayList<Page>pages) {
		this.PID = PID;
		this.startTime = startTime;
		this.duration = duration;
		this.size = size;
		this.pages = pages;
		this.isFinished = false;
		this.waitTime = 0;
		this.entered = false;
		pagePointer = 0;
		this.pageFaults = 0;
		pagesNum = pages.size();
	}
}
