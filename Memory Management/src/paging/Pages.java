package paging;

public class Pages {

	long startAddress;
	long endAddress;
	int pageNumber;
	int frameNumber;
	
	public int getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
	public int getFrameNumber() {
		return frameNumber;
	}
	public void setFrameNumber(int frameNumber) {
		this.frameNumber = frameNumber;
	}
	public long getStartAddress() {
		return startAddress;
	}
	public void setStartAddress(long startAddress) {
		this.startAddress = startAddress;
	}
	public long getEndAddress() {
		return endAddress;
	}
	public void setEndAddress(long endAddress) {
		this.endAddress = endAddress;
	}
	
}
