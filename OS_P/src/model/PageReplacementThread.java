package model;
import application.Simulator;

public class PageReplacementThread extends Simulator implements Runnable {
	
	PageReplacement pr;
	
	public PageReplacementThread(PageReplacement pr) {
		this.pr = pr;
	}
	
	@Override
	public void run() {
		pr.simualte();
		
	}
	
	
}
