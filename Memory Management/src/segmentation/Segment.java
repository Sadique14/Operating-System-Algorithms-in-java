package segmentation;

public class Segment {

	long size;
	String unit;
	long base;
	long limit;
	String limitUnit;
	public Segment() {
		this.size = -1;
		this.unit = null;
		this.base = -1;
		this.limit = -1;
	}
	public void clearAll() {
		size = -1;
		unit = null;
		base = -1;
		limit = -1;
	}
	public long getSize() {
		return size;
	}
	public void setSize(long size) {
		this.size = size;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public long getBase() {
		return base;
	}
	public void setBase(long base) {
		this.base = base;
	}
	public long getLimit() {
		return limit;
	}
	public void setLimit(long limit) {
		this.limit = limit;
	}
	public String getLimitUnit() {
		return limitUnit;
	}
	public void setLimitUnit(String limitUnit) {
		this.limitUnit = limitUnit;
	}
	
	
}
