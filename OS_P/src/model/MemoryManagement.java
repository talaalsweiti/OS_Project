package model;

import application.Simulator;

public class MemoryManagement {
	int position;
	Page page;
	int state;
	int PID;
	
	public MemoryManagement(int state, Page pageToAdd, int position, int PID) {
		this.state = state;
		this.page = pageToAdd;
		this.position = position;
		this.PID = PID;
	}
	
	public void simulate() {
		synchronized (this) {
			if(state == 1) {
				Simulator.memory[position].page = page;
				Simulator.memory[position].bitReference = 1;
				Simulator.memory[position].lastTimeUsed = Simulator.time;
			}
			else if(state == 2) {
				Simulator.memory[position].bitReference = 1;
				Simulator.memory[position].lastTimeUsed = Simulator.time;
			}
			else {
				for(int i=0; i<Simulator.physicalMemorySize; i++) {
					if(Simulator.memory[i].page != null && Simulator.memory[i].page.processID == PID) {
						Simulator.memory[i].page = null;
						Simulator.memory[i].lastTimeUsed = -1;
						Simulator.memory[i].bitReference = 0;
						Simulator.filledSize--;
					}
				}
			}
		}
	}
	
	
	
	
	public ProcessThread getProcess() {
		for(int i=0; i<Simulator.allThreads.size(); i++) {
			if(Simulator.allThreads.get(i).p.PID == PID) {
				return Simulator.allThreads.get(i);
			}
		}
		return null;
	}
	
}
