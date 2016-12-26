package roundRobin;

public class RR {
	Process[] p;
	Process[] out;
	int numOfProcess;
	int totalWaitingTime;
	float averageWaitingTime;
	int quantum;
    int index;
	
	public RR(Process[] x, int y) {
		p = x;
		quantum = y;
		totalWaitingTime = 0;
		averageWaitingTime = 0;
		numOfProcess = p.length;
		out = new Process[numOfProcess*5];
		for(int i=0; i<out.length; i++)
		{
			out[i] = new Process();
		}
		sort();
	}

	public void generateGanttChart() {
		ArrayQueue<Process> availableProcess = new ArrayQueue<>(2000);
		Process runningProcess = null;
		//int k = 0;
		for (int i = 0; i < numOfProcess; i++) {
			p[i].setRemainingTime(p[i].getBurstTime());
			p[i].setFinished(false);																	
			p[i].setRunningTime(0);
		}
		int j = 0;
		index=0;
		int temp2=0;
		while (true) {
			int temp;
			// cheking if all processes are complete
			temp = 0;
			for (int i = 0; i < numOfProcess; i++) {
				if (!p[i].isFinished)
					temp = 1;
			}
			if (temp == 0)
				break; // all process complete
			for (int i = 0; i < numOfProcess; i++) 
			{
				if (p[i].getArrivalTime() == j)
					availableProcess.AddQ(p[i]);
			}
			if(runningProcess == null)
			{
				if(!availableProcess.isEmpty()){
					runningProcess = availableProcess.first();
					availableProcess.DeleteQ();
					//System.out.println(j+"\t");
					if(temp2>0)
			    	{
			    		out[index].setEndTime(j);
			    		index++;
			    		temp2=0;
			    	}
			    	out[index].setName(runningProcess.getName());
		    		out[index].setStartTime(j);
				}
				else
				{
					temp2++;
			    	runningProcess = null;   //pro. idle
			    	if(temp2==1)
			    	{
			    		out[index].setName(null);
			    		out[index].setStartTime(j);
			    	}
				}
			}
			if(runningProcess != null && runningProcess.getRunningTime() == quantum)
			{
				runningProcess.setRunningTime(0);
				if(availableProcess.isEmpty()){
					runningProcess = null;
				}
				else{        
					availableProcess.AddQ(runningProcess);  
					//System.out.print(j+"\t");
					runningProcess = availableProcess.first();
					availableProcess.DeleteQ(); 
					out[index].setEndTime(j);
					index++;
					out[index].setName(runningProcess.getName());
					out[index].setStartTime(j);
				}								
			}
			j++;
			if (runningProcess != null)
			{
				runningProcess.setRemainingTime(runningProcess.getRemainingTime() - 1);
			    runningProcess.setRunningTime(runningProcess.getRunningTime() + 1);
			}
			if (runningProcess != null) {
				if (runningProcess.getRemainingTime() == 0) {
					//System.out.print(j + "\t");
					runningProcess.setFinished(true);
					runningProcess.setEndTime(j);
					out[index].setEndTime(j);
					index++;
					runningProcess = null;
					/*if(availableProcess.isEmpty())
						runningProcess = null;
					else
					{
						runningProcess = availableProcess.first();
						availableProcess.DeleteQ();
					}*/
				}
			}
		}
	}

	public void turnAroundTime() {	
		for (int i = 0; i < numOfProcess; i++) {
			p[i].setTurnAroundTime(p[i].getEndTime() - p[i].getArrivalTime());
		}
	}

	public void waitingTime() {
		for (int i = 0; i < numOfProcess; i++) {
			p[i].setWaitingTime((p[i].getEndTime() - p[i].arrivalTime) - p[i].getBurstTime());
		}
	}

	public void calculateTotalWaitingTime() {
		for (int i = 0; i < numOfProcess; i++) {
			totalWaitingTime += p[i].getWaitingTime();
		}
	}

	public void calculateAverageWaitingTime() {
		averageWaitingTime = (float) totalWaitingTime / numOfProcess;
	}

	public void printAll() {
		generateGanttChart();
		System.out.println("-----------------------Gantt Chart---------------------");
		System.out.println("\n_______________________________________________________");
		for(int i=0; i<index; i++)
		{
			if(out[i].getName()==null)
			{
				System.out.print(" IDLE |");
			}
			else
			{
				System.out.print("  "+out[i].getName()+"  |");
			}
		}
		System.out.println("\n_______________________________________________________");
		for(int i=0; i<index; i++)
		{
			if(i==0)
			{
				System.out.print(out[i].getStartTime()+"     ");
			}
			if(out[i].getEndTime()<10)
				System.out.print(out[i].getEndTime()+"      ");
			else
				System.out.print(out[i].getEndTime()+"     ");
		}
		System.out.println();
		turnAroundTime();
		waitingTime();
		System.out.println("\nProcess   Turn Around  Waiting");
		System.out.println("  Name        Time      Time");
		for (int i = 0; i < numOfProcess; i++) {
			System.out.println(
					"   " + p[i].getName() + "\t\t" + p[i].getTurnAroundTime() + "\t " + p[i].getWaitingTime());
		}
		calculateTotalWaitingTime();
		System.out.println("\nTotal Waiting Time: " + totalWaitingTime);
		calculateAverageWaitingTime();
		System.out.println("Average Waiting Time: " + averageWaitingTime);
	}

	public void sort() {
		for (int i = 1; i < numOfProcess; i++) {
			int j = i - 1;
			Process key = p[i];
			while (j >= 0 && p[j].getArrivalTime() > key.getArrivalTime()) {
				p[j + 1] = p[j];
				j--;
			}
			p[j + 1] = key;
		}
	}
}
