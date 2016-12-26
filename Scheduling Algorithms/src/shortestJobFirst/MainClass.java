package shortestJobFirst;

import java.util.Scanner;

public class MainClass {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println(".............................");
		System.out.println("Shortest-Job-First Scheduling");
		System.out.println(".............................");
		System.out.print("Enter 1 for preemptive version, Enter 0 for nonpreemptive version: ");
		int flag = sc.nextInt();
		System.out.print("Numer of process: ");
		int num = sc.nextInt();
		Process[] p = new Process[num];
		for(int i=0; i<num; i++){
			p[i] = new Process();
		}
		System.out.println("Name ArrivalTime BurstTime");
		System.out.println("---- --------- -----------");
		for(int i=0; i<num; i++){
			p[i].setName(sc.next());
			p[i].setArrivalTime(sc.nextInt());
			p[i].setBurstTime(sc.nextInt());
		}
		sc.close();
		SJF f = new SJF(p,flag);
		f.printAll();
		/*Process p=new Process();
		Process q=new Process();
		q.setArrivalTime(100);
		p =q;
		p.setArrivalTime(222);
		System.out.println(p.getArrivalTime()+"   "+q.getArrivalTime());*/
	}
}
