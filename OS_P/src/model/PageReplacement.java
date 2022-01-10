package model;

import application.Simulator;

public class PageReplacement {
	
	Page primaryPage;
	
	
	public PageReplacement(Page primaryPage) {
		this.primaryPage = primaryPage;
	}
	
	public void simulate() {
		Simulator.time += 0.3; //page fault time
		if (Simulator.filledSize == Simulator.physicalMemorySize) {// we need page replacement
			Simulator.finalResult+="Memory is full! Page replacment needed\n"; 
			if (Simulator.algorithm == 1) { // Second chance
				Simulator.finalResult+="Second Choice FIFO Algorithm\n";
				while (Simulator.memory[Simulator.memoryIndex].bitReference == 1) {
					Simulator.memory[Simulator.memoryIndex].bitReference = 0;
					Simulator.memoryIndex = (Simulator.memoryIndex + 1) % Simulator.physicalMemorySize;
				}
				
				int position = Simulator.memoryIndex;
				Thread mm = new Thread(new MemoryManagementThread(new MemoryManagement(1, primaryPage, position, 0)));
				mm.run();
				try {
					mm.join();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				Simulator.finalResult+="Page "+ primaryPage.pageLine + " added\n";
				Simulator.memoryIndex = (Simulator.memoryIndex + 1) % Simulator.physicalMemorySize;
			} else { // clock
				int minIndex = 0;
				for (int i = 0; i < Simulator.physicalMemorySize; i++) {
					if (Simulator.memory[i].lastTimeUsed < Simulator.memory[minIndex].lastTimeUsed) {
						minIndex = i;
					}
				}

				int position = minIndex;
				Thread mm = new Thread(new MemoryManagementThread(new MemoryManagement(1, primaryPage, position, 0)));
				mm.run();
				try {
					mm.join();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				

			}
			
		}
		else { //Normal page fault
			int i = 0;
			while (Simulator.memory[i].page != null) {
				i++;
			}
			int position = i;
			Simulator.filledSize++;
			Thread mm = new Thread(new MemoryManagementThread(new MemoryManagement(1, primaryPage, position, 0)));
			mm.run();
			try {
				mm.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Simulator.finalResult+="Page "+ primaryPage.pageLine + " added\n";
		}
	}
}
















