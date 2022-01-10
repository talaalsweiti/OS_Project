package model;

import application.Simulator;

public class MemoryManagement {

	int state;
	Page pageToAdd;
	int position;
	int PID;
	
	public MemoryManagement(int state, Page pageToAdd, int position, int PID) {
		this.state = state;
		this.pageToAdd = pageToAdd;
		this.position = position;
	}
	
	public void simulate() {
		synchronized (this) {
			if(state == 1) {
				Simulator.memory[position].page = pageToAdd;
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
}
