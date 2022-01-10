package model;

public class PageReplacementThread implements Runnable{
	
	PageReplacement pr;
	
	public PageReplacementThread(PageReplacement pr) {
		this.pr = pr;
	}
	
	@Override
	public void run() {
		pr.simulate();
	}
}
