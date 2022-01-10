package model;

import application.Simulator;

public class PageReplacement {
	
	
	Page pageToAdd;
	
	
	public PageReplacement(Page pageToAdd) {
		this.pageToAdd = pageToAdd;
	}

	public void simualte() {
		// increase number of page faults for process
		Simulator.time += 0.3;
		Thread sc = new Thread(new SchedulerThread(new Scheduler(3, null)));
		sc.start();

		if (Simulator.filledSize == Simulator.physicalMemorySize) {// we need page replacement
			// see input for algorithm to use
			int algorithm = 1; // !fix here

			if (algorithm == 1) { // Second chance
				while (Simulator.memory[Simulator.memoryIndex].bitReference == 1) {
					Simulator.memory[Simulator.memoryIndex].bitReference = 0;
					Simulator.memoryIndex = (Simulator.memoryIndex + 1) % Simulator.physicalMemorySize;
				}
				int position = Simulator.memoryIndex;
				
				Thread memo = new Thread(new MemoryManagementThread(new MemoryManagement(1, pageToAdd, position, 0)));
				memo.start();

				Simulator.memoryIndex = (Simulator.memoryIndex + 1) % Simulator.physicalMemorySize;

			} else { // clock
				int minIndex = 0;
				for (int i = 0; i < Simulator.physicalMemorySize; i++) {
					if (Simulator.memory[i].lastTimeUsed < Simulator.memory[minIndex].lastTimeUsed) {
						minIndex = i;
					}
				}

				int position = minIndex;
				Thread memo = new Thread(new MemoryManagementThread(new MemoryManagement(1, pageToAdd, position, 0)));
				memo.start();

			}
		} else { // normal page fault
			int i = 0;
			while (Simulator.memory[i].page != null) {
				i++;
			}
			int position = i;
			Simulator.filledSize++;
			Thread memo = new Thread(new MemoryManagementThread(new MemoryManagement(1, pageToAdd, position, 0)));
			memo.start();


		}

	}
}
