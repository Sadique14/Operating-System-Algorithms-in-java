package paging;
import java.util.Scanner;

public class MainClass {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		LogicalToPhysical lp = new LogicalToPhysical();
		System.out.println("Byte = b, Kilobyte = kb, Megabyte = mb, Gigabyte = gb, Terabyte = tb");
		System.out.print("Size of virtual address space: ");
		String vs = sc.nextLine();
		long virtualSize = 0;
		String unit = "";
		for(int i=0; i<vs.length(); i++){
			if(vs.charAt(i)>='0' && vs.charAt(i)<='9'){
				virtualSize = (virtualSize*10) + Character.getNumericValue(vs.charAt(i));
			}
			else{
				unit = unit + vs.charAt(i);
			}
		}
		unit = unit.trim();
		lp.virtualSize = virtualSize;
		lp.u = unit;

		System.out.print("Size of physical memory: ");
		String pm = sc.nextLine();
		long physicalSize = 0;
		String unit2 = "";
		for(int i=0; i<pm.length(); i++){
			if(pm.charAt(i)>='0' && pm.charAt(i)<='9'){
				physicalSize = (physicalSize*10) + Character.getNumericValue(pm.charAt(i));
			}
			else{
				unit2 = unit2 + pm.charAt(i);
			}
		}
		unit2 = unit2.trim();
		lp.physicalSize = physicalSize;
		lp.u2 = unit2;
		
		System.out.print("Page Size: ");
		String ps = sc.nextLine();
		long pageSize = 0;
		String unit3 = "";
		for(int i=0; i<ps.length(); i++){
			if(ps.charAt(i)>='0' && ps.charAt(i)<='9'){
				pageSize = (pageSize*10) + Character.getNumericValue(ps.charAt(i));
			}
			else{
				unit3 = unit3 + ps.charAt(i);
			}
		}
		unit3 = unit3.trim();
		lp.pageSize = pageSize;
		lp.u3 = unit3;

		lp.generateAddressSpace();
		
		System.out.println("\nNumber of mapping:");
		int x = sc.nextInt();
		Pages[] pageTable = new Pages[x];
		for(int i=0; i<pageTable.length; i++)
		{
			pageTable[i] = new Pages();
		}
		System.out.println("Page table:");
		System.out.println("Page number  Frame Number");
		System.out.println("-----------  ------------");
		for(int i=0; i<pageTable.length; i++)
		{
			pageTable[i].setPageNumber(sc.nextInt());
			pageTable[i].setFrameNumber(sc.nextInt());
		}
		System.out.println("\nVirtual address:");
		long v = sc.nextLong();
		lp.generatePhysicalAddress(pageTable, v);
		sc.close();
	}
}
