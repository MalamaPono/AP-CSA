package roadgraph;

public class EdgeInfo {

	private String roadName;
	private String roadType;
	private double length;
	
	public EdgeInfo(String roadName, String roadType, double length) {
		
		this.roadName = roadName;
		this.roadType = roadType;
		this.length = length;
		
	}
	
	public String getRoadName() {
		return roadName;
	}
	
	public String getRoadType(){
		return roadType;
	}
	
	public double getLength() {
		return length;
	}
	
}
