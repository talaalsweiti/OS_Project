package model;
//know when all finished?
import application.Simulator;

public class Scheduler {

	/*
	 * state:
	 * 1 => from working to blocked
	 * 2 => from blocked to ready
	 */
	int state;
	ProcessThread pt;
	
	public Scheduler(int state, ProcessThread pt) {
		this.state = state;
		this.pt = pt;
	}
	
	public void Simulate() {
		checkInNewProcess();
		if(state == 1) { //from working to blocked
			//has context switch
			Simulator.time += 0.005;
			checkInNewProcess();
			
			//add old to blocked
			Simulator.blockedQueue.add(pt);
			
			//get peek from ready
			ProcessThread newProcess = Simulator.readyQueue.peek();
			Thread newProcessThread = new Thread(newProcess);
			newProcessThread.start();
			
		}
		else if(state == 2) { // from blocked to ready
			
		}
		else if(state == 3) {
			checkInNewProcess();
		}
		
	}
	
	
	public void checkInNewProcess() {
		for(ProcessThread pt: Simulator.allThreads) {
			if(pt.p.startTime <= Simulator.time && pt.p.entered == false) {
				Simulator.readyQueue.add(pt);
				pt.p.entered = true;
			}
		}
		if(Simulator.readyQueue.size() == 0 && !allFinished()) {
			Simulator.time+=0.001;
			checkInNewProcess();
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
	
}
