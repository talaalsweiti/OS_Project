package model;

public class Page {
	
	public int processID;
	public String pageLine;
	public int pageNumber;
	
	public Page() {
		
	}
	
	public Page(int processID, int pageNumber, String pageLine) {
		this.processID = processID;
		this.pageNumber = pageNumber;
		this.pageLine = pageLine;
	}
}
