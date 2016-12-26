package oneResourceOfEachType;

public class Stack {

	int top = -1;
	Node[] data;
	int n;
	public Stack(int capacity)
	{
		n = capacity;
		data = new Node[n];
		for(int i=0; i<data.length; i++)
		{
			data[i] = new Node(null, null);
		}
	}
	public boolean isEmpty()
	{
		return (top == -1);
	}
	public boolean push(Node item)
	{
		if(top >= n-1)
		{
			System.out.println("Stack is full");
			return false;
		}
		else
		{
			top = top+1;
			data[top] = item;
			return true;
		}
	}
	public Node pop()
	{
		Node deleteItem;
		if(top < 0)
		{
			System.out.println("stack is empty");
			return null;
		}
		else
		{
			deleteItem = data[top];
			data[top] = null;
			top--;
			return deleteItem;
		}
	}
	public Node peek()
	{
		Node peekItem;
		if(top < 0)
		{
			System.out.println("stack is empty");
			return null;
		}
		else
		{
			peekItem = data[top];
			return peekItem;
		}
	}
	public boolean search(Node item)
	{
		for(int i=0; i<data.length; i++)
		{
			if(item.equals(data[i]))
				return true;
		}
		return false;
	}
}
