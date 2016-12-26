package oneResourceOfEachType;

public class List {

	Node first,last;
	int length;
	public List()
	{
		first = null;
		last = null;
		length = 0;
	}
	public void insert(String item)
	{
		Node newNode = new Node(item, null);
		if(last == null)
		{
			first = last = newNode;
		}
		else
		{
			last.setLink(newNode);
			last = last.getLink();
		}
		length++;
	}
	public Node getFirst()
	{
		return first;
	}
}
