package model;

import application.Simulator;

public class PageReplacementThread extends Simulator implements Runnable {
	
	
	@Override
	public void run() {
		
		//increase number of page faults for process
		SchedulerThread.time +=0.3;
		
		if(SchedulerThread.filledSize == physicalMemorySize) {//we need page replacement
			//see input for algorithm to use
			int algorithm = 1; //!fix here
			
			if(algorithm == 1) { //Second chance
				while(SchedulerThread.memory[SchedulerThread.memoryIndex].bitReference == 1) {
					SchedulerThread.memory[SchedulerThread.memoryIndex].bitReference = 0;
					SchedulerThread.memoryIndex = (SchedulerThread.memoryIndex + 1)%physicalMemorySize;
				}
				Page newPage = new Page(SchedulerThread.nextPage.processID, SchedulerThread.nextPage.pageNumber, SchedulerThread.nextPage.pageLine);
				SchedulerThread.memory[SchedulerThread.memoryIndex] = new Frame();
				SchedulerThread.memory[SchedulerThread.memoryIndex].page = newPage;
				SchedulerThread.memory[SchedulerThread.memoryIndex].bitReference = 1;
				SchedulerThread.memoryIndex = (SchedulerThread.memoryIndex + 1)%physicalMemorySize;
				SchedulerThread.memory[SchedulerThread.memoryIndex].lastTimeUsed = SchedulerThread.time;
				
			}else { //clock
				int minIndex = 0;
				for(int i=0; i<physicalMemorySize; i++) {
					if(SchedulerThread.memory[i].lastTimeUsed < SchedulerThread.memory[minIndex].lastTimeUsed) {
						minIndex = i;
					}
				}
				Page newPage = new Page(SchedulerThread.nextPage.processID, SchedulerThread.nextPage.pageNumber, SchedulerThread.nextPage.pageLine);
				SchedulerThread.memory[minIndex] = new Frame();
				SchedulerThread.memory[minIndex].page = newPage;
				SchedulerThread.memory[minIndex].bitReference = 1;
				SchedulerThread.memory[minIndex].lastTimeUsed = SchedulerThread.time;
			}
		}
		else { //normal page fault
			int i=0;
			while(SchedulerThread.memory[i].page != null) {
				i++;
			}
			SchedulerThread.memory[i].page = SchedulerThread.nextPage;
			SchedulerThread.memory[i].bitReference = 1;
			SchedulerThread.filledSize++;
			SchedulerThread.memory[i].lastTimeUsed = SchedulerThread.time;
			
		}
		
		
		
		//SchedulerThread.time +=300;
	}
	
	
}
