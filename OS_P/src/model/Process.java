package model;

import java.util.*;

import application.Simulator;

public class Process {
	public int PID;
	public double startTime;
	public double duration;
	public int size;
	public ArrayList<Page> pages = new ArrayList<Page>();
	public boolean isFinished;
	public double turnaround;
	public double finishTime;
	public double waitTime;
	public boolean entered;
	public int pagePointer;
	public int pageFaults;
	
	public Process(int PID, double startTime, double duration, int size, ArrayList<Page>pages) {
		this.PID = PID;
		this.startTime = startTime;
		this.duration = duration;
		this.size = size;
		this.pages = pages;
		this.isFinished = false;
		this.waitTime = 0;
		this.entered = false;
		pagePointer = 0;
		this.pageFaults = 0;
	}
	
	
	public void simulate() throws InterruptedException {
		double quantumForPt = Simulator.quantum;
		for(int i=pagePointer; i<quantumForPt+ pagePointer && duration > 0; i++) {
			double timeBefore = Simulator.time;
			if(i < pages.size()) { //if we finish tracing and duration still not finished
				if(!pageInMemory(PID, pages.get(i))) {
	
					
					Scheduler newScheduler = new Scheduler(1, getProcess());
					SchedulerThread newSchedulerThread = new SchedulerThread(newScheduler);
					Thread workingSchedulerMainThread = new Thread(newSchedulerThread);
					workingSchedulerMainThread.start();
					
					workingSchedulerMainThread.join();
					workingSchedulerMainThread.stop();
					
					Simulator.time+=0.001; //for finding page fault
					pageFaults++;
					Simulator.totalPageFaults++;
					
					Thread pr = new Thread(new PageReplacementThread(new PageReplacement(pages.get(i))));
					pr.start();
					
					pr.join();
					
					Thread sc = new Thread(new SchedulerThread(new Scheduler(2, getProcess())));
					sc.start();
					
					break;
				}
				else {
					int index = memoryPageIndex(PID, pages.get(i));
					//page exists just update
					Thread pr = new Thread(new MemoryManagementThread(new MemoryManagement(2, null, index, -1)));
					pr.start();
					Simulator.time+=0.2;
					Thread sc = new Thread(new SchedulerThread(new Scheduler(3, null)));
					sc.start();
					
				}
			}
			quantumForPt -= (Simulator.time - timeBefore);
			Scheduler newScheduler = new Scheduler(3, getProcess());
			SchedulerThread newSchedulerThread = new SchedulerThread(newScheduler);
			Thread workingSchedulerMainThread = new Thread(newSchedulerThread);
			workingSchedulerMainThread.start();
			
			workingSchedulerMainThread.join();
			workingSchedulerMainThread.stop();
			
			duration--;
			pagePointer++;
		}
		
		System.out.println("Here");
		
		if(duration <= 0) {
			isFinished = true;
			finishTime = Simulator.time;
			turnaround = finishTime - startTime;
			Thread freeM = new Thread(new MemoryManagementThread(new MemoryManagement(3, null, 0, PID)));
			freeM.start();
			
			if(!Simulator.readyQueue.isEmpty()) {
				ProcessThread peekP =  Simulator.readyQueue.peek();
				Thread newActiveP = new Thread(peekP);
				newActiveP.start();
			}
			
			//?ifempty
			
		}
		
		updateWaitTime();
		
	}
	
	
	public boolean pageInMemory(int pid, Page page) {
		for(int i=0; i<Simulator.memory.length; i++) {
			if(Simulator.memory[i].page!= null && Simulator.memory[i].page.pageNumber == page.pageNumber && Simulator.memory[i].page.processID == pid) {
				return true;
			}
		}
		return false;
	}
	
	public ProcessThread getProcess() {
		for(int i=0; i<Simulator.allThreads.size(); i++) {
			if(Simulator.allThreads.get(i).p.PID == PID) {
				return Simulator.allThreads.get(i);
			}
		}
		return null;
	}
	
	
	public int memoryPageIndex(int pid, Page page) {
		for(int i=0; i<Simulator.memory.length; i++) {
			if(Simulator.memory[i].page != null && Simulator.memory[i].page.pageNumber == page.pageNumber && Simulator.memory[i].page.processID == pid) {
				return i;
			}
		}
		return -1;
	}
	
	
	
	public void updateWaitTime() {
		for(ProcessThread pt:Simulator.allThreads) {
			if(pt.p.entered == true && pt.p.isFinished == false && pt.p.PID != PID) {
				pt.p.waitTime++;
			}
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
