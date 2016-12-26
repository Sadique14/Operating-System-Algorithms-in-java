 package firstComeFirstServed;

public class FCFS {
	Process[] p;
	Process[] out;
	int numOfProcess;
	int totalWaitingTime;
	float averageWaitingTime;
	int index;
	public FCFS(Process[] x){
		p = x;
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
	public void generateGanttChart()
	{
		index=0;
		for(int i=0; i<numOfProcess; i++)
		{
			if(i==0)
			{
				p[i].setStartTime(p[i].getArrivalTime());
				p[i].setEndTime(p[i].getArrivalTime() + p[i].getBurstTime());
				
			}
			else
			{
				if(p[i-1].getEndTime() > p[i].getArrivalTime())
					p[i].setStartTime(p[i-1].getEndTime());
				else
					p[i].setStartTime(p[i].getArrivalTime());
				p[i].setEndTime(p[i].getStartTime() + p[i].getBurstTime());
			}
		}
		for(int i=0; i<numOfProcess; i++)
		{
			if(i==0)
			{
				if(p[i].getStartTime()>0)
				{
					out[index].setStartTime(0);
					out[index].setName(null);
					out[index].setEndTime(p[i].getStartTime());
					index++;
				}
				out[index].setStartTime(p[i].getStartTime());
				out[index].setName(p[i].getName());
				out[index].setEndTime(p[i].getEndTime());
				index++;
			}
			else
			{
				if(p[i].getStartTime() > p[i-1].getEndTime())
				{
					out[index].setStartTime(p[i-1].getEndTime());
					out[index].setName(null);
					//out[index].setData(p[i].getData());
					out[index].setEndTime(p[i].getStartTime());
					index++;
				}
				out[index].setStartTime(p[i].getStartTime());
				out[index].setName(p[i].getName());
				out[index].setEndTime(p[i].getEndTime());
				index++;
			}
		}
	}
	
	public void printAll()
	{
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
		System.out.println("\n");
		for(int i=0; i<index; i++)
		{
			for(int j=0; j<(out[i].getEndTime()-out[i].getStartTime()); j++)
			{
		        if(out[i].getName() != null)
		        	System.out.print(out[i].getName());
		        else
		        	System.out.print(" ");
			}
		}
	}
	public void sort()
	{
		for(int i=1; i<numOfProcess; i++)
	    {
	        int j=i-1;
	        Process key = p[i];
	        while(j>=0 && p[j].getArrivalTime() > key.getArrivalTime())
	        {
	            p[j+1] = p[j];
	            j--;
	        }
	        p[j+1] = key;
	    }
	}
}
