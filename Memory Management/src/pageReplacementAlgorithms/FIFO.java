package pageReplacementAlgorithms;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class FIFO {

	int pageFault;
	int numOfPages;
	int numberOfFrame;
	PageFrame[] pf;
	int[] pages;
	Scanner sc;
	Queue<Integer> queue;
	
	public FIFO(int[] pages, int numOfPages, int numOfFrame) {
		queue = new LinkedList<Integer>();
		sc = new Scanner(System.in);
		this.numberOfFrame = numOfFrame;
		this.pageFault = 0;
		this.numOfPages = numOfPages;
		this.pages = pages;
		pf = new PageFrame[numOfFrame];
		for(int i=0; i<pf.length; i++)
		{
			pf[i] = new PageFrame();
		}
	}

	public void implementation()
	{
		int index = 0;
		int i=0;
		print();
		while(true)
		{
			System.out.println("Page: "+pages[i]);
			if(!PageFrame.isFull())
			{
				if(!checkAvailability(pages[i]))
				{
					pageFault++;
					System.out.println("Page fault occurs- "+pageFault);
					queue.add(pages[i]);
					pf[index++].setData(pages[i++]); 
				}
				else
				{
					i++;
				}
				if(index == pf.length)
					PageFrame.setFull(true);
			}
			else
			{
				if(!checkAvailability(pages[i]))
				{
					pageFault++;
					System.out.println("Page fault occurs- "+pageFault);
					int replaceValue = queue.remove();
					int replaceIndex = -1;
					for(int j=0; j<pf.length; j++)
					{
						if(pf[j].getData() == replaceValue)
						{
							replaceIndex = j;
							break;
						}
					}
					queue.add(pages[i]);
					pf[replaceIndex].setData(pages[i++]); 
				}
				else
				{
					i++;
				}
			}
			print();
			try {
				TimeUnit.SECONDS.sleep(2);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			if(i==pages.length)
			{
				System.out.println("Total page faults:   "+pageFault);
				System.out.println("END");
				break;
			}
		}
	}
	public boolean checkAvailability(int page)
	{
		int k=0;
		for(int j=0; j<pf.length; j++)
		{
			if(pf[j].getData() == page)
			{
				k=1;
			}
		}
		if(k==1)
			return true;
		else
			return false;
	}
	public void print()
	{
		System.out.println("-------");
		for(int i=0; i<pf.length; i++)
		{
			if(pf[i].getData() == -1)
				System.out.println("|     |");
			else
				System.out.println("|  "+pf[i].getData()+"  |");
			System.out.println("-------");
		}
		System.out.println();
	}
}
