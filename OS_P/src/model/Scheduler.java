package model;

import application.Simulator;

public class Scheduler {
	   
	
	
	public void simulate() {
		while(!allFinished()) {
			checkInNewProcess();
			if(Simulator.readyQueue.isEmpty()) {
				//!fix time
				Simulator.time+=1;
				checkInNewProcess();
				continue;
			}
			Simulator.time+=0.005; //time for context switch
			ProcessThread workingProcess = Simulator.readyQueue.peek();
			Simulator.readyQueue.remove();
			System.out.println("Entered Process " + workingProcess.p.PID);
			double quantumForWorkingProcess = Simulator.quantum;
			int oldCount = 0;
			for(int i=workingProcess.p.pagePointer; i<workingProcess.p.pagePointer + quantumForWorkingProcess && workingProcess.p.duration > 0; i++) {
				double timeBefore = Simulator.time;
				if(i>=workingProcess.p.pages.size()) { continue;}
				if(!pageInMemory(workingProcess.p.PID, workingProcess.p.pages.get(i))) {  //page fault
					System.out.println("Page fault for page " + workingProcess.p.pages.get(i).pageLine + " Process " + workingProcess.p.PID);
					Simulator.blockedQueue.add(workingProcess);
					Simulator.time+=0.5; //time for checking page before page fault
					//!memory management get page
					//{
					
					
					PageReplacement pr = new PageReplacement(workingProcess.p.pages.get(i));
					pr.simulate();
					Simulator.blockedQueue.remove();
					Simulator.readyQueue.add(workingProcess);
					
					//}
					workingProcess.p.pageFaults++;
					Simulator.totalPageFaults++;
					break;
				}
				else {
					System.out.println("Yay " + workingProcess.p.pages.get(i).pageLine);
					oldCount ++;
					int position = memoryPageIndex(workingProcess.p.PID, workingProcess.p.pages.get(i));
					Thread mm = new Thread(new MemoryManagementThread(new MemoryManagement(2, workingProcess.p.pages.get(i), position, 0)));
					mm.run();
					try {
						mm.join();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				quantumForWorkingProcess -= (Simulator.time - timeBefore);
				Simulator.time+=1; //!return to time
				workingProcess.p.duration -=0.5;
				
				
			}
			workingProcess.p.pagePointer += oldCount;
			
			
			if(workingProcess.p.duration <= 0 || workingProcess.p.pagePointer >= workingProcess.p.pages.size()) {
				System.out.println(" BYE process " + workingProcess.p.PID);
				workingProcess.p.isFinished = true;
				workingProcess.p.finishTime = Simulator.time;
				workingProcess.p.turnaround = workingProcess.p.finishTime - workingProcess.p.startTime;
				Thread mm = new Thread(new MemoryManagementThread(new MemoryManagement(3, null, 0, workingProcess.p.PID)));
				mm.run();
				try {
					mm.join();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//!!!!!!!!!!!!!!!!!!!PUT IN MANAGEMENT
			}

			updateWaitTime();
			
		}
	}
	
	
	
	public boolean allFinished() {
		for(ProcessThread ptt: Simulator.allThreads) {
			if(!ptt.p.isFinished) {
				return false;
			}
		}
		return true;
	}
	
	
	public void checkInNewProcess() {
		for(ProcessThread pt: Simulator.allThreads) {
			if(pt.p.startTime <= Simulator.time && pt.p.entered == false) {
				Simulator.readyQueue.add(pt);
				pt.p.entered = true;
				//System.out.println(pt.p.PID + " Entered the ready queue");
			}
		}
	}
	
	public boolean pageInMemory(int pid, Page page) {
		for(int i=0; i<Simulator.memory.length; i++) {
			if(Simulator.memory[i].page!= null && Simulator.memory[i].page.pageLine.equals(page.pageLine) && Simulator.memory[i].page.processID == pid) {
				return true;
			}
		}
		return false;
	}

	
	public void updateWaitTime() {
		for(ProcessThread pt:Simulator.readyQueue) {
			pt.p.waitTime++;
		}
	}
	
	
	
	public int memoryPageIndex(int pid, Page page) {
		for(int i=0; i<Simulator.memory.length; i++) {
			if(Simulator.memory[i].page != null && Simulator.memory[i].page.pageNumber == page.pageNumber && Simulator.memory[i].page.processID == pid) {
				return i;
			}
		}
		return -1;
	}

	
	
	
}
