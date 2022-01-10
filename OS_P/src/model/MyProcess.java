package model;

import java.util.ArrayList;

public class MyProcess {
	public int getPID() {
		return PID;
	}

	public void setPID(int pID) {
		PID = pID;
	}

	public double getStartTime() {
		return startTime;
	}

	public void setStartTime(double startTime) {
		this.startTime = startTime;
	}

	public double getDuration() {
		return duration;
	}

	public void setDuration(double duration) {
		this.duration = duration;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public ArrayList<Page> getPages() {
		return pages;
	}

	public void setPages(ArrayList<Page> pages) {
		this.pages = pages;
	}

	public boolean isFinished() {
		return isFinished;
	}

	public void setFinished(boolean isFinished) {
		this.isFinished = isFinished;
	}

	public double getTurnaround() {
		return turnaround;
	}

	public void setTurnaround(double turnaround) {
		this.turnaround = turnaround;
	}

	public double getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(double finishTime) {
		this.finishTime = finishTime;
	}

	public double getWaitTime() {
		return waitTime;
	}

	public void setWaitTime(double waitTime) {
		this.waitTime = waitTime;
	}

	public boolean isEntered() {
		return entered;
	}

	public void setEntered(boolean entered) {
		this.entered = entered;
	}

	public int getPagePointer() {
		return pagePointer;
	}

	public void setPagePointer(int pagePointer) {
		this.pagePointer = pagePointer;
	}

	public int getPageFaults() {
		return pageFaults;
	}

	public void setPageFaults(int pageFaults) {
		this.pageFaults = pageFaults;
	}

	public int getPagesNum() {
		return pagesNum;
	}

	public void setPagesNum(int pagesNum) {
		this.pagesNum = pagesNum;
	}

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
