package model;
//blocked queue add remove
import application.Simulator;

public class PageReplacementThread extends Simulator implements Runnable {
	
	volatile static Page newPage;
	public static int position;
	
	public PageReplacementThread() {
		newPage = null;
		position = -1;
	}
	
	@Override
	public void run() {
		
//		//increase number of page faults for process
//		SchedulerThread.time +=0.3;
//		
//		if(SchedulerThread.filledSize == physicalMemorySize) {//we need page replacement
//			//see input for algorithm to use
//			int algorithm = 1; //!fix here
//			
//			if(algorithm == 1) { //Second chance
//				while(SchedulerThread.memory[SchedulerThread.memoryIndex].bitReference == 1) {
//					SchedulerThread.memory[SchedulerThread.memoryIndex].bitReference = 0;
//					SchedulerThread.memoryIndex = (SchedulerThread.memoryIndex + 1)%physicalMemorySize;
//				}
//				newPage = new Page(SchedulerThread.nextPage.processID, SchedulerThread.nextPage.pageNumber, SchedulerThread.nextPage.pageLine);
//				//position = SchedulerThread.memoryIndex;
//				//synchronized (this) {
//					position = SchedulerThread.memoryIndex;
//				//}
//				//System.out.println(position);
//				//Thread mmt = new Thread(new MemoryManagementThread());
//				//mmt.start();
//				SchedulerThread.memory[PageReplacementThread.position] = new Frame();
//				SchedulerThread.memory[PageReplacementThread.position].page = PageReplacementThread.newPage;
//				SchedulerThread.memory[PageReplacementThread.position].bitReference = 1;
//				SchedulerThread.memory[PageReplacementThread.position].lastTimeUsed = SchedulerThread.time;
//				SchedulerThread.memoryIndex = (SchedulerThread.memoryIndex + 1)%physicalMemorySize;
//				
//			}else { //clock
//				int minIndex = 0;
//				for(int i=0; i<physicalMemorySize; i++) {
//					if(SchedulerThread.memory[i].lastTimeUsed < SchedulerThread.memory[minIndex].lastTimeUsed) {
//						minIndex = i;
//					}
//				}
//				newPage = new Page(SchedulerThread.nextPage.processID, SchedulerThread.nextPage.pageNumber, SchedulerThread.nextPage.pageLine);
//				//position = minIndex;
//				//synchronized (this) {
//					position = minIndex;
//				//}
//				//Thread mmt = new Thread(new MemoryManagementThread());
//				//mmt.start();
//					SchedulerThread.memory[PageReplacementThread.position] = new Frame();
//					SchedulerThread.memory[PageReplacementThread.position].page = PageReplacementThread.newPage;
//					SchedulerThread.memory[PageReplacementThread.position].bitReference = 1;
//					SchedulerThread.memory[PageReplacementThread.position].lastTimeUsed = SchedulerThread.time;
//			}
//		}
//		else { //normal page fault
//			int i=0;
//			while(SchedulerThread.memory[i].page != null) {
//				i++;
//			}
//			newPage = SchedulerThread.nextPage;
//			//position = i;
//			//synchronized (this) {
//				position = i;
//			//}
//			//System.out.println(position);
//			SchedulerThread.filledSize++;
//			//Thread mmt = new Thread(new MemoryManagementThread());
//			//mmt.start();
//			SchedulerThread.memory[PageReplacementThread.position] = new Frame();
//			SchedulerThread.memory[PageReplacementThread.position].page = PageReplacementThread.newPage;
//			SchedulerThread.memory[PageReplacementThread.position].bitReference = 1;
//			SchedulerThread.memory[PageReplacementThread.position].lastTimeUsed = SchedulerThread.time;
//			
//		}
//		
//		
//		
//		//SchedulerThread.time +=300;
	}
	
	
}
