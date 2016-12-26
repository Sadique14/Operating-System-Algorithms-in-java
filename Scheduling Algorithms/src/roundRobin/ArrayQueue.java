package roundRobin;

public class ArrayQueue<E> {

	E[] data;
	int front;
	int rear;
	int size;
	int n;
	public ArrayQueue(int capacity)
	{
		n = capacity;
		data = (E[])new Object[n];
		front = 0;
		rear = 0;
		size = 0;
	}
	public int size()
	{
		return size;
	}
	public boolean isEmpty()
	{
		return (size == 0);
	}
	
	//Basic queue operation- insert
	public boolean AddQ(E item)
	{
		rear = (rear+1)%n;
		if(front == rear)
		{
			System.out.println("Queue is full");
			if(front == 0)
				rear = n - 1;
			else {
				rear = rear - 1;
			}
			return false;
		}
		else
		{
			data[rear] = item;
			size++;
			return true;
		}
	}
	
	//Basic queue operation- delete
	public E DeleteQ()
	{
		if(front == rear)
		{
			System.out.println("Queue is empty");
			return null;
		}
		else {
			E deleteItem = data[front];
			front = (front+1)%n;
			//size--;
			return deleteItem;
		}
	}
	public E first()
	{
		if(isEmpty())
			return null;
		return data[front+1];
	}
}
