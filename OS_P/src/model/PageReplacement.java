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
			int algorithm = 1; // !fix here
			if (algorithm == 1) { // Second chance
				System.out.println("<<<<<<<<<<<<<<<<<<<<<<");
				while (Simulator.memory[Simulator.memoryIndex].bitReference == 1) {
					Simulator.memory[Simulator.memoryIndex].bitReference = 0;
					Simulator.memoryIndex = (Simulator.memoryIndex + 1) % Simulator.physicalMemorySize;
				}
				
				int position = Simulator.memoryIndex;
				
				//{
				
//				Simulator.memory[position].page = primaryPage;
//				Simulator.memory[position].bitReference = 1;
//				Simulator.memory[position].lastTimeUsed = Simulator.time;
				
//				MemoryManagement mm = new MemoryManagement(1, primaryPage, position, 0);
//				mm.simulate();
				
				Thread mm = new Thread(new MemoryManagementThread(new MemoryManagement(1, primaryPage, position, 0)));
				mm.run();
				try {
					mm.join();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				System.out.println("Page "+ primaryPage.pageLine + " added");
				
				//}
				
				Simulator.memoryIndex = (Simulator.memoryIndex + 1) % Simulator.physicalMemorySize;
			} else { // clock
				int minIndex = 0;
				for (int i = 0; i < Simulator.physicalMemorySize; i++) {
					if (Simulator.memory[i].lastTimeUsed < Simulator.memory[minIndex].lastTimeUsed) {
						minIndex = i;
					}
				}

				int position = minIndex;
				
				//{
				
				Thread mm = new Thread(new MemoryManagementThread(new MemoryManagement(1, primaryPage, position, 0)));
				mm.run();
				try {
					mm.join();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				//}

			}
			
		}
		else { //Normal page fault
			System.out.println("Normal page fault");
			int i = 0;
			//System.out.println(Simulator.memory.length);
			while (Simulator.memory[i].page != null) {
				i++;
			}
			int position = i;
			System.out.println(position);
			Simulator.filledSize++;
			//{
			
			Thread mm = new Thread(new MemoryManagementThread(new MemoryManagement(1, primaryPage, position, 0)));
			mm.run();
			try {
				mm.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("PAge "+ primaryPage.pageLine + " added");
			
			//}
			
		}
	}
}
















