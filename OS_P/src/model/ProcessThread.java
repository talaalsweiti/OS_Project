package model;

public class ProcessThread implements Runnable{
	
	public Process p;
	
	public ProcessThread(Process p) {
		this.p = p;
	}
	
	
	@Override
	public void run() {
		try {
			p.simulate();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
