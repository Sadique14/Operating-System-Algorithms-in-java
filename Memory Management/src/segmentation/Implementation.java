package segmentation;

public class Implementation{
	
	public void generateLogicalAddressSpace(Segment[] segment)
	{
		for(int i=segment.length-1; i>=0; i--)
		{
			segment[i].setSize(convertToByte(segment[i].getSize(),segment[i].getUnit()));
			//System.out.println("                 ----");
			if(i<10){
				System.out.println("|  "+i+"   | 0B - "+(segment[i].getSize()-1)+"B");
			}
			else
				System.out.println("|  "+i+"  | 0B - "+(segment[i].getSize()-1)+"B");
			//System.out.println("_____");
		}
	}
	public void generatePhysicalAddressSpace(Segment[] segment)
	{
		Segment[] tempSegment = new Segment[segment.length];
		tempSegment = segment;
		sort(tempSegment);
		for(int i=tempSegment.length-1; i>=0; i--)
		{
			if(tempSegment[i].getBase() != -1)
			{
				tempSegment[i].setLimit(convertToByte(tempSegment[i].getLimit(),tempSegment[i].getLimitUnit()));
				//System.out.println("                 ----");
				if(i<10){
					System.out.println("|  "+tempSegment[i].getBase()+"   |");
				}
				else
					System.out.println("|  "+tempSegment[i].getBase()+"  |");
				//System.out.println("_____");
			}
		}
	}
	public void calculatePhysicalAddress(Segment[] segment,int segmentNo,int offset)
	{
		long base = segment[segmentNo].getBase();
		long address = base+offset;
		System.out.println("base address: "+base);
		System.out.println("Physical address: "+address);
	}
	public long convertToByte(long size, String u)
	{
		if(u.equals("kb")){
			size = size * 1024;
		}
		else if(u.equals("mb")){
			size = size * 1024 * 1024;
		}
		else if(u.equals("gb")){
			size = size * 1024 * 1024 * 1024;
		}
		else if(u.equals("tb")){
			size = size * 1024 * 1024 * 1024 * 1024;
		}
		return size;
	}
	public void sort(Segment[] s)
	{
		for(int i=1; i<s.length; i++)
	    {
	        int j=i-1;
	        Segment key = s[i];
	        while(j>=0 && s[j].getBase() > key.getBase())
	        {
	            s[j+1] = s[j];
	            j--;
	        }
	        s[j+1] = key;
	    }
	}
	
}
