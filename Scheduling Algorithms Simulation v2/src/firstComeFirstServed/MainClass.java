package firstComeFirstServed;

import java.util.Scanner;

public class MainClass {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("..................................");
		System.out.println("First-Come First-Served Scheduling Simulation");
		System.out.println(".............................................");
		System.out.print("Number of process: ");
		int num = sc.nextInt();
		Process[] p = new Process[num];
		for(int i=0; i<num; i++){
			p[i] = new Process();
		}
		System.out.println("Name ArrivalTime Data");
		System.out.println("---- --------- ----");
		for(int i=0; i<num; i++){
			p[i].setName(sc.next());
			p[i].setArrivalTime(sc.nextInt());
			p[i].setData(sc.next());
			p[i].setBurstTime(p[i].getData().length());
		}
		sc.close();
		FCFS f = new FCFS(p);
		f.printAll();
	}
}
