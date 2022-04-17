import edu.duke.*;
import java.io.File;

public class PerimeterAssignmentRunner {
    public double getPerimeter (Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }

    public int getNumPoints (Shape s) {
        int count = 0;
        for(Point currPt : s.getPoints()){
           count++;
        }
        return count;
    }

    public double getAverageLength(Shape s) {
        double perm = getPerimeter(s);
        int sides = getNumPoints(s);        
        return perm/sides;
    }

    public double getLargestSide(Shape s) {
        double longSide = 0.0;
        Point prevPoint = s.getLastPoint();
        
        for(Point currPt : s.getPoints()){
            double distance = prevPoint.distance(currPt);
            if(distance > longSide){
                longSide = distance;
        }
            prevPoint = currPt;
        return longSide;
    }

    public double getLargestX(Shape s) {
        double largestX = 0.0;
        for(Point p : s.getPoints()){
            if(p.getX() > largestX){
                largestX = p.getX();
        }
        return largestX;
    }

    public double getLargestPerimeterMultipleFiles() {
        DirectoryResource dr = new DirectoryResource();
        double largest = 0;
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double temp = getPerimeter(s);
            if(temp > largest){
                largest = temp;
        }
        return largest;
    }

    public String getFileWithLargestPerimeter(){
        DirectoryResource dr = new DirectoryResource();
        int largest = 0;
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double temp = getPerimeter(s);
            if(temp > largest){
                largest = temp;
                File tempName = fr;
        }  
        return tempName.getName();
    }

    public void testPerimeter(){
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        System.out.println("perimeter = " + length);
        System.out.println(getNumPoints(s));
        System.out.println(getAverageLength(s));
        System.out.println(getLargesrSide(s));
        System.out.println(getLargestX(s));
    }
    
    public void testPerimeterMultipleFiles() {
        System.out.println(getLargestPerimeterMultipleFiles());
        
    }

    public void testFileWithLargestPerimeter() {
        System.out.println(getFileWithLargestPerimeter());
    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle(){
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        for (Point p : triangle.getPoints()){
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = "+peri);
    }

    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void main (String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        pr.testPerimeter();
    }
}
