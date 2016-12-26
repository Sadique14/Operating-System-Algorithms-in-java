package pageReplacementAlgorithms;

import java.util.Scanner;

public class MainClass {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Page Replacement Algorithm");
		System.out.println("---------------------------");
		while(true)
		{
			PageFrame.setFull(false);
			System.out.println("\nEnter 1: Not Recently Used");
			System.out.println("Enter 2: The First-In, First-Out (FIFO)");
			System.out.println("Enter 3: The Second-Chance");
			System.out.println("Enter 0: Exit");
			int flag = sc.nextInt();
			if(flag == 1)
			{
				System.out.println("Not Recently Used Algorithm");
				System.out.println("---------------------------");
				System.out.print("Number of pages to find: ");
				int numOfPages = sc.nextInt();
				int[] pages = new int[numOfPages];
				System.out.println("Enter pages:");
				for(int i=0; i<pages.length; i++)
				{
					pages[i] = sc.nextInt();
				}
				System.out.print("Number of page frame: ");
				int numOfFrame = sc.nextInt();
				NotRecenlyUsed nru = new NotRecenlyUsed(pages, numOfPages, numOfFrame);
				nru.implementation();
			}
			else if (flag == 2) 
			{
				System.out.println("The First-In, First-Out (FIFO) algorithm");
				System.out.println("----------------------------------------");
				System.out.print("Number of pages to find: ");
				int numOfPages = sc.nextInt();
				int[] pages = new int[numOfPages];
				System.out.println("Enter pages:");
				for(int i=0; i<pages.length; i++)
				{
					pages[i] = sc.nextInt();
				}
				System.out.print("Number of page frame: ");
				int numOfFrame = sc.nextInt();
				FIFO f = new FIFO(pages, numOfPages, numOfFrame);
				f.implementation();
			}
			else if (flag == 3) 
			{
				System.out.println("Second Chance Algorithm");
				System.out.println("---------------------------");
				System.out.print("Number of pages to find: ");
				int numOfPages = sc.nextInt();
				int[] pages = new int[numOfPages];
				System.out.println("Enter pages:");
				for(int i=0; i<pages.length; i++)
				{
					pages[i] = sc.nextInt();
				}
				System.out.print("Number of page frame: ");
				int numOfFrame = sc.nextInt();
				SecondChance sch = new SecondChance(pages, numOfPages, numOfFrame);
				sch.implementation();
			}
			else if (flag == 0) 
			{
				break;
			}
		}
		sc.close();
	}
}
