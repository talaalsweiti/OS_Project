package model;

public class Frame {
	public Page page;
	public int bitReference;
	public double lastTimeUsed;
	
	public Frame() {
		page = null;
		this.bitReference = 0;
		this.lastTimeUsed = -1;
	}
}
