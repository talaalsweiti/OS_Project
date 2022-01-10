package model;

public class MemoryManagementThread implements Runnable {
	MemoryManagement mm;
	
	public MemoryManagementThread(MemoryManagement mm) {
		this.mm = mm;
	}
	
	@Override
	public void run() {
		mm.simulate();
	}
}
