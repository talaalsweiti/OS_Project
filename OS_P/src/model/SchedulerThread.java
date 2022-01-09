package model;


public class SchedulerThread implements Runnable {
	
	Scheduler sc;
	
	public SchedulerThread(Scheduler sc) {
		this.sc = sc;
	}
	
	@Override
	public void run() { //will run round robin		
		sc.Simulate();
		
	}
	

	
	
	

	
	
//	public void goGetPage(ProcessThread pt) {
//		Thread replacement = new Thread(new PageReplacementThread());
//		replacement.start(); //pagefault => 300 in sync
//		try {
//			replacement.join();
//			readyQueue.add(pt);
//		}catch(Exception InterruptedException) {
//			
//		}
//		
//	}
	
	
}
