package model;

public class MemoryManagementThread implements Runnable{
	
	
	@Override
	public void run() {
		synchronized (this) {
			//putting new page
			SchedulerThread.memory[PageReplacementThread.position] = new Frame();
			SchedulerThread.memory[PageReplacementThread.position].page = PageReplacementThread.newPage;
			SchedulerThread.memory[PageReplacementThread.position].bitReference = 1;
			SchedulerThread.memory[PageReplacementThread.position].lastTimeUsed = SchedulerThread.time;
		}
	}
}
