package oneResourceOfEachType;

import java.io.File;
import java.util.*;

public class SingleInstance {

	static int size;
	static Node[] headNodes;
	static Boolean visited[][];
    static Stack stack;
    static String temp3;

	public static Boolean checkForDeadlock(Node head)
	{
		stack = new Stack(50);
		clearVisited();
		Node currentNode = head;
		stack.push(currentNode);
		int in1 = searchIndex(currentNode.getInfo());
		int in2 = searchIndex(currentNode.getInfo());
		visited[in1][in2] = true;
		int index;  
		while(!stack.isEmpty())
		{
			currentNode = stack.peek();
		    while (currentNode.getLink() != null) 
		    {
		    	Node temp = currentNode;
		    	int flag = 0;
		    	while(currentNode.getLink()!=null)
		    	{
		    		currentNode = currentNode.getLink();
		    		in1 = searchIndex(temp.getInfo());
		    		in2 = searchIndex(currentNode.getInfo());
		    		if(visited[in1][in2] == true)
		    		{
		    			flag = 0;
		    		}
		    		else {
						flag = 1;
						break;
					}
		    	}
				if(flag == 0)
					break;
				index = searchIndex(currentNode.getInfo());
	    		currentNode = headNodes[index];
				if(stack.search(currentNode)){
					temp3 = currentNode.getInfo();
					return true;
				}
				stack.push(currentNode);
				in1 = searchIndex(temp.getInfo());
	    		in2 = searchIndex(currentNode.getInfo());
				visited[in1][in2] = true;
			}
		    currentNode = stack.pop();
		}
		return false;
	}
	public static int searchIndex(String info)
	{
		for(int i=0; i<size; i++)
		{
			if(info.equals(headNodes[i].getInfo()))
				return i;
		}
		return -1;
	}
	public static void clearVisited()
	{
		for(int i=0; i<size; i++)
		{
			Node temp = headNodes[i];
			while(temp!=null)
			{
				int in1 = searchIndex(headNodes[i].getInfo());
				int in2 = searchIndex(temp.getInfo());
				visited[in1][in2] = false;
				temp = temp.getLink();
			}
		}
		
	}
	public static void main(String[] args) {
		try {
			File f = new File("Graph.txt");
			Scanner sc = new Scanner(f);
			size = sc.nextInt();
			headNodes = new Node[size];
			visited = new Boolean[size][size];
			String adjacent;
			for(int i=0; i<size; i++)
			{
				adjacent = sc.next();
				List link = new List();
				while(!adjacent.equals("0"))
				{
					//System.out.println(i);
					link.insert(adjacent);
					adjacent = sc.next();
				}
				headNodes[i] = link.getFirst();
			}
			sc.close();
		
			int temp=0;
			for(int i=0; i<size; i++)
			{
				if(checkForDeadlock(headNodes[i]))
				{
					temp=1;
					System.out.println("Deadlock found.\nDeadlocked processes are- ");
					while(!stack.isEmpty())
					{
						String p = stack.peek().getInfo();
						//if(temp3.equals(p))
							//break;
						if(p.length() == 2)
							System.out.println(stack.pop().getInfo().charAt(0));
						else
							stack.pop();
						if(temp3.equals(p))
							break;
					}
					break;
				}
			}
			if(temp==0)
			{
				System.out.println("No deadlock found");
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
