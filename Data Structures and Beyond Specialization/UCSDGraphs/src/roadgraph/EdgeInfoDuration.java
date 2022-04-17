package roadgraph;

public class EdgeInfoDuration {

	private String roadName;
	private String roadType;
	private double length; 
	private double duration; //in minutes
	private int speedLimit;
	
	public EdgeInfoDuration(String roadName, String roadType, double length) {
		
		this.roadName = roadName;
		this.roadType = roadType;
		//System.out.println(roadType);
		this.length = length;
		//System.out.println(this.length);
		if(roadType.equals("residential")) {
			speedLimit = 25;
		}else if(roadType.equals("primary")) {
			speedLimit = 70;
		}else if(roadType.equals("unclassified")) {
			speedLimit = 60;
		}else if(roadType.equals("motorway")) {
			speedLimit = 70;
		}else if(roadType.equals("motorway_link")) {
			speedLimit = 55;
		}else if(roadType.equals("secondary")) {
			speedLimit = 55;
		}else if(roadType.equals("trunk")) {
			speedLimit = 30;
		}else if(roadType.equals("tertiary")) {
			speedLimit = 45;
		}else if(roadType.equals("city street")) {
			speedLimit = 30;
		}else if(roadType.equals("connector")) {
			speedLimit = 35;
		}else if(roadType.equals("living_street")) {
			speedLimit = 40;
		}else {
			speedLimit = 50;
		}
		
		this.duration = this.length/speedLimit;
		
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
	
	public double getDuration() {
		return duration;
	}
	
}
