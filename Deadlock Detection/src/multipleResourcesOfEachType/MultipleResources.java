package multipleResourcesOfEachType;

import java.util.*;

public class MultipleResources {

	static boolean[] process;
	static int numberOfProcess;
	static int numberOfResources;
	static int[] E;
	static int[] A;
	static int[][] C;
	static int[][] R;
	static int[] tempA;
	public static void implementation()
	{
		int checkCompletion = 0;
		while(true)
		{
			int flag = 0;
			checkCompletion++;
			for(int m=0; m<numberOfProcess; m++)
			{
				if(process[m] == true){
					flag++;
				}
			}
			if(flag == numberOfProcess)
				break;
			
			for(int i=0; i<numberOfProcess; i++)
			{
				flag = 0;
				if(!process[i])
				{
					for(int j=0; j<numberOfResources; j++)
					{
						if(R[i][j] <= A[j])
						{
							flag++;
						}
					}
					if(flag == (numberOfResources))
					{
						//process[i] = true;
						for(int k=0; k<numberOfResources; k++)
						{
							tempA[k] = A[k] - R[i][k];
							C[i][k] = C[i][k] + R[i][k];
							A[k] = tempA[k] + C[i][k];
						}
						System.out.println("\nProcess "+(i+1)+":\nAvailable vector (A):");
						for(int k=0; k<numberOfResources; k++)
						{
							System.out.print(" "+tempA[k]);
						}
						System.out.println();
						System.out.println("current allocation matrix (C):");
						for(int p=0; p<numberOfProcess; p++)
						{
							if(!process[p])
							{
								for(int q=0;q<numberOfResources;q++)
								{
									
										System.out.print(" "+C[p][q]);
										//C[p][q]=0;
									
								}
							}
							else{
								System.out.print(" - - -");
							}
						    System.out.println();
						}
						process[i] = true;
						System.out.println("After ending of Process "+(i+1)+":\nAvailable vector (A):");
						for(int u=0; u<numberOfResources; u++)
						{
							System.out.print(" "+A[u]);
						}
						System.out.println("\n");
						break;
					}
				}		
			}
			if(checkCompletion > numberOfProcess)
			{
				System.out.println("Deadlock occurs:");
				System.out.println("Deadlock processes:");
				for(int l=0; l<numberOfProcess; l++)
				{
					if(process[l] == false)
					{
						System.out.println(l+1);
					}
				}
				System.exit(0);
			}
		}
	}
	public static void main(String[] args) 
	{
		Scanner sc = new Scanner(System.in);
		System.out.print("Number of process: ");
		numberOfProcess = sc.nextInt();
		System.out.print("Number of resource: ");
		numberOfResources = sc.nextInt();
		E = new int[numberOfResources];
	    A = new int[numberOfResources];
	    tempA = new int[numberOfResources];
	    C = new int[numberOfProcess][numberOfResources];
	    R = new int[numberOfProcess][numberOfResources];
	    process = new boolean[numberOfProcess];
		System.out.println("existing resource vector (E): ");
		for(int i=0; i<numberOfResources; i++)
		{
			int x = sc.nextInt();
			E[i] = x;
		}
		System.out.println("current allocation matrix (C): ");
		for(int i=0; i<numberOfProcess; i++)
		{	
			for(int j=0; j<numberOfResources; j++)
			{
				int x = sc.nextInt();
				C[i][j] = x;
			}
		}
		//error checking
		
		
		System.out.println("available resource vector (A): ");
		int sum = 0;
		for(int i=0; i<numberOfResources; i++)
		{
			sum = 0;
			for(int j=0; j<numberOfProcess; j++)
			{
				sum += C[j][i];
			}
			//error checking
			if(sum > E[i])
			{
				System.out.println("Error... allocated resources can not be greater than existing resources.");
				System.exit(0);
			}
			//...
			A[i] = E[i] - sum;
			System.out.print(" "+A[i]);
		}
		System.out.println("\nrequest matrix (R): ");
		for(int i=0; i<numberOfProcess; i++)
		{
			sum=0;
			for(int j=0; j<numberOfResources; j++)
			{
				int x = sc.nextInt();
				R[i][j] = x;
			}
		}	
		
	
		sc.close();
		implementation();
	}
}
