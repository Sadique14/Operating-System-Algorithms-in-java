package oneResourceOfEachType;

public class Node {

	String info;
	Node Link;
	int length;
	public Node(String info, Node Link) 
	{
		this.info = info;
		this.Link = Link;
		this.length = 0;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public Node getLink() {
		return Link;
	}
	public void setLink(Node Link) {
		this.Link = Link;
		this.length++;
	}
}
