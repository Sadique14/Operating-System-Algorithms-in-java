package roundRobin2;

import java.util.Scanner;

public class MainClass {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("......................");
		System.out.println("Round-Robin Schedulimg");
		System.out.println("......................");
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
		System.out.print("Time Quantum: ");
		int quantum = sc.nextInt();
		sc.close();
		RR f = new RR(p,quantum);
		f.printAll();
		/*ArrayQueue<Process> availableProcess = new ArrayQueue<>(2000);
		ArrayQueue<Process> saveProcess = new ArrayQueue<>(2000);
		availableProcess.AddQ(p[0]);
		availableProcess.AddQ(p[1]);
		saveProcess.AddQ(p[2]);
		saveProcess = availableProcess;
		saveProcess.DeleteQ();
		System.out.println(saveProcess.size()+" "+saveProcess.first().getName()+" "+availableProcess.first().getName());*/
	}
}
