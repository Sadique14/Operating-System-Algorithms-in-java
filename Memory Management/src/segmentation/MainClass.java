package segmentation;
import java.util.Scanner;

public class MainClass {
	static String unit = "";

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("SEGMENTATION");
		System.out.println("------------");
		System.out.println("Byte = b, Kilobyte = kb, Megabyte = mb, Gigabyte = gb, Terabyte = tb");
		System.out.print("Number of Segments: ");
		Implementation ip = new Implementation();
		int numOfSegment = sc.nextInt();
		Segment[] segment = new Segment[numOfSegment];
		
		String temp = null;
		long size = 0;
		long mainMemorySize = 0;
		for(int i=0; i<numOfSegment; i++)
		{
			segment[i] = new Segment();
			System.out.print("Size of segment "+i+": ");
			temp = sc.next();
			size = convert(temp);
			segment[i].setSize(size);
			segment[i].setUnit(unit);
		}
		System.out.println("Main memory size: ");
		temp = sc.next();
		size = convert(temp);
		mainMemorySize = size;
		String mainMemoryUnit = unit;
		mainMemorySize = convertToByte(mainMemorySize, unit);
		System.out.println("LOGICAL ADDRESS SPACE:");
		ip.generateLogicalAddressSpace(segment);
		System.out.println();
		int x=0;
		while(true)
		{
			System.out.print("Number of mapping in segment table: ");
			x = sc.nextInt();
			if(x>segment.length)
				System.out.println("Error... number mapping is greater than number of segment.");
			else {
				break;
			}
		}
		int flag=0;
		int flag2=0;
		long totalLimit = 0;
		while(true)
		{
			System.out.println("Segment_number Base_address Limit");
			System.out.println(".............. ............ .....");
			for (int i = 0; i < x; i++) {
				int segmentNo = sc.nextInt();
				if(segmentNo >= segment.length)
				{
					System.out.println("Segment Number is not in the logical memory.");
					clearSegments(segment);
					flag=1;
					break;
				}
				else
				{
					long baseAddress = sc.nextLong();
					String y = sc.next();
					long limit = convert(y);
					System.out.println(segmentNo);
					segment[segmentNo].setBase(baseAddress);
					segment[segmentNo].setLimit(limit);
					segment[segmentNo].setLimitUnit(unit);
					if(unit.equals("kb")){
						segment[segmentNo].limit = segment[segmentNo].limit * 1024;
					}
					else if(unit.equals("mb")){
						segment[segmentNo].limit = segment[segmentNo].limit * 1024 * 1024;
					}
					else if(unit.equals("gb")){
						segment[segmentNo].limit = segment[segmentNo].limit * 1024 * 1024 * 1024;
					}
					else if(unit.equals("tb")){
						segment[segmentNo].limit = segment[segmentNo].limit * 1024 * 1024 * 1024 * 1024;
					}
					long tempLimit = segment[segmentNo].limit;
					//long tempLimit = convertToByte(segment[segmentNo].getLimit(), segment[segmentNo].getLimitUnit());
					long tempBase = segment[segmentNo].getBase();
					long max = tempLimit + tempBase;
					if(tempLimit<segment[segmentNo].getSize())
					{
						System.out.println("Error at segment "+segmentNo+"... Limit of a segment can not be less than it's size.");
						flag=1;
						clearSegments(segment);
						break;
					}
					totalLimit += tempLimit;
					for(int j=0; j<segment.length; j++)
					{
						if(j!=segmentNo)
						{
							long checkLimit = segment[j].getLimit();
							long checkBase = segment[j].getBase();
							long checkMax = checkLimit + checkBase;
							if(tempBase>checkMax || max<checkBase){
								
							}
							else{
								System.out.println("Error... Address overlapped between "+j+" and "+segmentNo+" segment.");
								flag=1;
								flag2=1;
								clearSegments(segment);
								break;
							}
						}
					}
				}
				if(flag2==1)
				{
					flag2=0;
					break;
				}
			}
			if(totalLimit > mainMemorySize)
			{
				totalLimit = 0;
				flag = 1;
				clearSegments(segment);
			}
			if(flag==0)
				break;
			flag=0;
		}
		
		System.out.println("PHYSICAL ADDRESS SPACE:");
		ip.generatePhysicalAddressSpace(segment);
		System.out.println();
		int segmentNumber;
		int offset;
		flag=0;
		while (true) 
		{
			System.out.print("Segment No: ");
			segmentNumber = sc.nextInt();
			System.out.print("Offset: ");
			offset = sc.nextInt();
			System.out.println();
			if(segment[segmentNumber].getSize() == -1){
				System.out.println("Segment not found in the segment table.");
				flag=1;
			}
			if(segment[segmentNumber].getSize() <= offset )
			{
				System.out.println("Error... Offset is not valid.");
				flag=1;
			}	
			if(flag==0)
				break;
			flag=0;
		}	
		ip.calculatePhysicalAddress(segment,segmentNumber,offset);
		sc.close();
	}
	public static long convert(String stringSize){
		long size = 0;
		unit = "";
		for(int i=0; i<stringSize.length(); i++){
			if(stringSize.charAt(i)>='0' && stringSize.charAt(i)<='9'){
				size = (size*10) + Character.getNumericValue(stringSize.charAt(i));
			}
			else{
				unit = unit + stringSize.charAt(i);
			}
		}
		//unit = unit.trim();
		return size;
	}
	public static long convertToByte(long ss, String u)
	{
		if(u.equals("kb")){
			ss = ss * 1024;
		}
		else if(u.equals("mb")){
			ss = ss * 1024 * 1024;
		}
		else if(u.equals("gb")){
			ss = ss * 1024 * 1024 * 1024;
		}
		else if(u.equals("tb")){
			ss = ss * 1024 * 1024 * 1024 * 1024;
		}
		return ss;
	}
	public static void clearSegments(Segment[] s){
		for(int i=0; i<s.length; i++)
		{
			s[i].clearAll();
		}
	}
}
