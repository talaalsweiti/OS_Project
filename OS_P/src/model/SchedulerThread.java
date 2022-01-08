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
			for(int i=0; i<quantumForPt && pt.p.duration > 0; i++) {
				double timeBefore = time;
				//Thread replacement = new Thread(new PageReplacementThread());
				//replacement.start(); //pagefault => 300 in sync
				quantumForPt -= (time-timeBefore);
				checkInNewProcess(++time);
				pt.p.duration--;
			}
			
			if(pt.p.duration == 0) {
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
			if(pt.p.startTime == time) {
				readyQueue.add(pt);
				allThreads.remove(pt);
			}
		}
	}
	
	public void updateWaitTime() {
		for(ProcessThread pt:allThreads) {
			pt.p.waitTime++;
		}
	}
	
}
