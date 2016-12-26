package pageReplacementAlgorithms;

public class PageFrame {

	int data;
	int rm;
	static boolean full;
	public PageFrame() {
		this.data = -1;
		this.rm = 0;
	}

	public static boolean isFull() {
		return full;
	}
	public static void setFull(boolean full) {
		PageFrame.full = full;
	}

	public int getData() {
		return data;
	}
	public void setData(int data) {
		this.data = data;
	}
	public int getRm() {
		return rm;
	}
	public void setRm(int rm) {
		this.rm = rm;
	}
	
}
