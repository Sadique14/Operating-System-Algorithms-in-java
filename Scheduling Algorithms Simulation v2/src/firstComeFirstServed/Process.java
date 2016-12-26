package firstComeFirstServed;

public class Process {

	String name;
	String Data;
	int track;
	int burstTime;
	int arrivalTime;
	int startTime;
	int endTime;
	int turnAroundTime;
	int waitingTime;
	public Process() 
	{
		this.name = null;
		this.burstTime = 0;
		this.arrivalTime = 0;
		this.track = 0;
	}
	public void running(int runningTime)
	{
		for(int i=track; i<(track+runningTime); i++)
		{
			System.out.print(Data.charAt(i));
		}
	}
	
	public String getData() {
		return Data;
	}

	public void setData(String data) {
		Data = data;
	}

	public int getTrack() {
		return track;
	}

	public void setTrack(int track) {
		this.track = track;
	}

	public int getStartTime() {
		return startTime;
	}
	public void setStartTime(int startTime) {
		this.startTime = startTime;
	}
	public int getEndTime() {
		return endTime;
	}
	public void setEndTime(int endTime) {
		this.endTime = endTime;
	}
	public int getTurnAroundTime() {
		return turnAroundTime;
	}
	public void setTurnAroundTime(int turnAroundTime) {
		this.turnAroundTime = turnAroundTime;
	}
	public int getWaitingTime() {
		return waitingTime;
	}
	public void setWaitingTime(int waitingTime) {
		this.waitingTime = waitingTime;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getBurstTime() {
		return burstTime;
	}
	public void setBurstTime(int burstTime) {
		this.burstTime = burstTime;
	}
	public int getArrivalTime() {
		return arrivalTime;
	}
	public void setArrivalTime(int arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
	
}
