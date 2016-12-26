package paging;

public class LogicalToPhysical {
	long virtualSize;
	String u;
	long physicalSize;
	String u2;
	long pageSize;
	String u3;

	public void generateAddressSpace()
	{
		convertToByte();
		Pages[] virtualPages = new Pages[(int)(virtualSize/pageSize)];
		Pages[] pageFrames = new Pages[(int)(physicalSize/pageSize)];
		long k=-1;
		for(int i=0; i<virtualPages.length; i++)
		{
			virtualPages[i] = new Pages();
			k++;
			virtualPages[i].setStartAddress(k);
			k = (k-1) + pageSize;
			virtualPages[i].setEndAddress(k);
		}
		k=-1;
		for(int i=0; i<pageFrames.length; i++)
		{
			pageFrames[i] = new Pages();
			k++;
			pageFrames[i].setStartAddress(k);
			k = (k-1) + pageSize;
			pageFrames[i].setEndAddress(k);
		}
		
		//Now print
		System.out.println("\nVirtual address space:");
		for(int i=virtualPages.length-1; i>=0; i--)
		{
			//System.out.println("                 ----");
			if(i<10){
				System.out.println("|  "+i+"   | "+virtualPages[i].getStartAddress()+"B - "+virtualPages[i].getEndAddress()+"B");
			}
			else
				System.out.println("|  "+i+"  | "+virtualPages[i].getStartAddress()+"B - "+virtualPages[i].getEndAddress()+"B");
			//System.out.println("_____");
		}
		System.out.println("\nPhysical memory address:");
		for(int i=pageFrames.length-1; i>=0; i--)
		{
			//System.out.println("                 ----");
			if(i<10){
				System.out.println("|  "+i+"   | "+pageFrames[i].getStartAddress()+"B - "+pageFrames[i].getEndAddress()+"B");
			}
			else
				System.out.println("|  "+i+"  | "+pageFrames[i].getStartAddress()+"B - "+pageFrames[i].getEndAddress()+"B");
			//System.out.println("_____");
		}

	}
	public void generatePhysicalAddress(Pages[] pageTable, long virtualAddress)
	{
		int pageNumber = (int)(virtualAddress/pageSize);
		int offset = (int)(virtualAddress%pageSize);
		int k=-1;
		for(int i=0; i<pageTable.length; i++)
		{
			if(pageNumber == pageTable[i].getPageNumber())
				k = i;
		}
		if(k==-1)
			System.out.println("Page not found in the memory.");
		else
		{
			long physicalAddress = (pageTable[k].getFrameNumber()*pageSize) + offset;
			System.out.println("Physical Address: "+physicalAddress);
		}
	}
	public void convertToByte()
	{
		if(u.equals("kb")){
			virtualSize = virtualSize * 1024;
		}
		else if(u.equals("mb")){
			virtualSize = virtualSize * 1024 * 1024;
		}
		else if(u.equals("gb")){
			virtualSize = virtualSize * 1024 * 1024 * 1024;
		}
		else if(u.equals("tb")){
			virtualSize = virtualSize * 1024 * 1024 * 1024 * 1024;
		}
		
		if(u2.equals("kb")){
			physicalSize = physicalSize * 1024;
		}
		else if(u2.equals("mb")){
			physicalSize = physicalSize * 1024 * 1024;
		}
		else if(u2.equals("gb")){
			physicalSize = physicalSize * 1024 * 1024 * 1024;
		}
		else if(u2.equals("tb")){
			physicalSize = physicalSize * 1024 * 1024 * 1024 * 1024;
		}
		
		if(u3.equals("kb")){
			pageSize = pageSize * 1024;
		}
		else if(u3.equals("mb")){
			pageSize = pageSize * 1024 * 1024;
		}
		else if(u3.equals("gb")){
			pageSize = pageSize * 1024 * 1024 * 1024;
		}
		else if(u3.equals("tb")){
			pageSize = pageSize * 1024 * 1024 * 1024 * 1024;
		}
	}
}
