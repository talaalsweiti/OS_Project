package model;

public class MemoryManagementThread implements Runnable{
	
	
	@Override
	public void run() {
		synchronized (this) {
			System.out.println("Starting memory Managment");
			//putting new page
			System.out.println(PageReplacementThread.position);
			SchedulerThread.memory[PageReplacementThread.position] = new Frame();
			SchedulerThread.memory[PageReplacementThread.position].page = PageReplacementThread.newPage;
			SchedulerThread.memory[PageReplacementThread.position].bitReference = 1;
			SchedulerThread.memory[PageReplacementThread.position].lastTimeUsed = SchedulerThread.time;
		}
	}
}
