package priority;

public class Priority {
	Process[] p;
	Process[] out;
	int numOfProcess;
	int totalWaitingTime;
	float averageWaitingTime;
	int flag;
	public Priority(Process[] x, int y)
	{
		p = x;
		flag = y;
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
	int index;
	public void generateGanttChart()
	{
		if(flag == 1)
		{
			Process runningProcess = null;
			int k=0;
			for(int i=0; i<numOfProcess; i++)
			{
				p[i].setRemainingTime(p[i].getBurstTime());
				p[i].setFinished(false);
			}
			int j = 0;
			int temp2 = 0;
			while(true)
			{
				int temp;
				//cheking if all processes are complete
				temp=0;
				for(int i=0; i<numOfProcess; i++)
				{
					if(!p[i].isFinished)
						temp=1;
				}
				if(temp==0)
					break;         //all process complete
				if(runningProcess == null)
				{
					//processor idle, set new process
					Process minimum = new Process();
					minimum.setPriority(1000000);
					temp = 0;
				    for(int i=0; i<numOfProcess; i++)
				    {
				    	if(!p[i].isFinished() && p[i].getPriority()<minimum.getPriority() && p[i].getArrivalTime() <= j)
				    	{
				    		runningProcess = p[i];
				    		minimum.setPriority(runningProcess.getPriority());
				    		temp = 1;
				    	}
				    }
				    if(temp == 0)
				    {
				    	temp2++;
				    	runningProcess = null;   //pro. idle
				    	if(temp2==1)
				    	{
				    		out[index].setName(null);
				    		out[index].setStartTime(j);
				    	}
				    }
				    	//runningProcess = null;   //pro. idle
				    else if(temp == 1)
				    {
				    	if(temp2>0)
				    	{
				    		out[index].setEndTime(j);
				    		index++;
				    		temp2=0;
				    	}
				    	out[index].setName(runningProcess.getName());
			    		out[index].setStartTime(j);
				    	if(k==j)
				    	{
				    	    //System.out.print("---"+j+"---");   //idle finish
				    	}
				    }
				}
				//checking if a process is replaced by another
				if(runningProcess != null)
				{
					k=0;
					for(int i=0; i<numOfProcess; i++)
					{
						if(!p[i].isFinished() && p[i].getArrivalTime() <= j && p[i].getPriority() < runningProcess.getPriority())
						{
							//runningProcess.setEndTime(j);
							runningProcess = p[i];
							k=1;				
						}
						
					}
					if(k==1)
					{
						out[index].setEndTime(j);
						index++;
						out[index].setName(runningProcess.getName());
						out[index].setStartTime(j);
						//System.out.print(j+"\t");
					}
				}
				if(runningProcess!=null)
					System.out.print(runningProcess.getName());
				else
					System.out.print(" ");
				j++;
				k=j;
				if(runningProcess != null)
					runningProcess.setRemainingTime(runningProcess.getRemainingTime() - 1);
				if(runningProcess != null)
				{
					if(runningProcess.getRemainingTime() == 0){
						//System.out.print(j+"\t");
						out[index].setEndTime(j);
						index++;
						k=0;
						runningProcess.setFinished(true);
						runningProcess.setEndTime(j);
						runningProcess = null;
					}
				}
			}
		}
		else
		{
			Process runningProcess = null;
			for(int i=0; i<numOfProcess; i++)
			{
				p[i].setRemainingTime(p[i].getBurstTime());
				p[i].setFinished(false);
			}
			int k=0;
			int j = 0;
			index = 0;
			int temp2 = 0;
			while(true)
			{
				int temp;
				//cheking if all processes are complete
				temp=0;
				for(int i=0; i<numOfProcess; i++)
				{
					if(!p[i].isFinished)
						temp=1;
				}
				if(temp==0)
					break;         //all process complete
				if(runningProcess == null)
				{
					//processor idle, set new process
					Process minimum = new Process();
					minimum.setPriority(1000000);
					temp = 0;
				    for(int i=0; i<numOfProcess; i++)
				    {
				    	if(!p[i].isFinished() && p[i].getPriority()<minimum.getPriority() && p[i].getArrivalTime() <= j)
				    	{
				    		runningProcess = p[i];
				    		minimum.setPriority(runningProcess.getPriority());
				    		temp = 1;
				    	}
				    }
				    if(temp == 0)
				    {
				    	temp2++;
				    	runningProcess = null;   //pro. idle
				    	if(temp2==1)
				    	{
				    		out[index].setName(null);
				    		out[index].setStartTime(j);
				    	}
				    }
				    else if(temp == 1)
				    {
				    	if(temp2>0)
				    	{
				    		out[index].setEndTime(j);
				    		index++;
				    		temp2=0;
				    	}
				    	out[index].setName(runningProcess.getName());
			    		out[index].setStartTime(j);
				    	if(k==j)
				    	{
				    	   // System.out.print("---"+j+"---");   //idle finish
				    	}
				    }
				}
				if(runningProcess!=null)
					System.out.print(runningProcess.getName());
				else
					System.out.print(" ");
				j++;
				k=j;
				if(runningProcess != null)
					runningProcess.setRemainingTime(runningProcess.getRemainingTime() - 1);
				if(runningProcess != null)
				{
					if(runningProcess.getRemainingTime() == 0){
						//System.out.print(j+"\t");
						out[index].setEndTime(j);
						index++;
						k=0;
						runningProcess.setFinished(true);
						runningProcess.setEndTime(j);
						runningProcess = null;
					}
				}
			}
		}
	}
	public void printAll()
	{
		generateGanttChart();
		System.out.println("\n\n-----------------------Gantt Chart---------------------");
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

