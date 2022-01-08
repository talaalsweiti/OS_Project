package model;

import java.util.LinkedList;
import java.util.Queue;

import application.Simulator;

public class SchedulerThread extends Simulator implements Runnable {
	
	Frame[] memory;
	Queue<ProcessThread> readyQueue;
	int quantum;
	//1000 cycles in a second
	volatile static double time;
	
	public SchedulerThread() {
		memory = new Frame[physicalMemorySize];
		readyQueue = new LinkedList<ProcessThread>();
		quantum = 20; //!return to this
		time = 0;
	}
	
	@Override
	public void run() { //will run round robin
		memory = new Frame[physicalMemorySize];
		for(int i=0; i<physicalMemorySize; i++) {
			memory[i] = new Frame();
		}
		
		while(!allFinished()) {
			checkInNewProcess(time);
			if(readyQueue.size() == 0) {
				time++;
				continue;
			}
			time += 0.005;
			ProcessThread pt = readyQueue.peek();
			readyQueue.remove();
			
			int quantumForPt = quantum;
			for(int i=pt.p.pagePointer; i<quantumForPt+pt.p.pagePointer && pt.p.duration > 0; i++) {
				double timeBefore = time;
				if(!pageInMemory(pt.p.PID, pt.p.pages.get(i))) {
					//Thread replacement = new Thread(new PageReplacementThread());
					//replacement.start(); //pagefault => 300 in sync
				}
				quantumForPt -= (time-timeBefore);
				checkInNewProcess(++time);
				pt.p.duration--;
				pt.p.pagePointer++;
			}
			
			if(pt.p.duration <= 0) {
				pt.p.isFinished = true;
				//System.out.println("Done with Process " + pt.p.PID);
				pt.p.finishTime = time;
				pt.p.turnaround = pt.p.finishTime - pt.p.startTime;
			}
			updateWaitTime();
		}
		
	}
	
	
	public boolean allFinished() {
		for(ProcessThread pt: allThreads) {
			if(!pt.p.isFinished) {
				return false;
			}
		}
		return true;
	}
	
	
	public void checkInNewProcess(double time) {
		for(ProcessThread pt: allThreads) {
			if(pt.p.startTime <= time && pt.p.entered == false) {
				readyQueue.add(pt);
				pt.p.entered = true;
				//System.out.println(pt.p.PID + " Entered the ready queue");
			}
		}
	}
	
	public void updateWaitTime() {
		for(ProcessThread pt:allThreads) {
			pt.p.waitTime++;
		}
	}
	
	public boolean pageInMemory(int pid, Page page) {
		for(int i=0; i<memory.length; i++) {
			if(memory[i].page.pageNumber == page.pageNumber && memory[i].page.processID == pid) {
				return true;
			}
		}
		return false;
	}
	
	
	
	
}
