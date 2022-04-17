/**
 * Create a gray scale version of an image by setting all color components of each pixel to the same value.
 * 
 * @author Duke Software Team 
 */
import edu.duke.*;
import java.io.*;

public class GrayScaleConverter {
	//I started with the image I wanted (inImage)
	public ImageResource makeGray(ImageResource inImage) {
		//I made a blank image of the same size
		ImageResource outImage = new ImageResource(inImage.getWidth(), inImage.getHeight());
		//for each pixel in outImage
		for (Pixel pixel: outImage.pixels()) {
			//look at the corresponding pixel in inImage
			Pixel inPixel = inImage.getPixel(pixel.getX(), pixel.getY());
			//compute inPixel's red + inPixel's blue + inPixel's green
			//divide that sum by 3 (call it average)
			int average = (inPixel.getRed() + inPixel.getBlue() + inPixel.getGreen())/3;
			//set pixel's red to average
			pixel.setRed(average);
			//set pixel's green to average
			pixel.setGreen(average);
			//set pixel's blue to average
			pixel.setBlue(average);
		}
		//outImage is your answer
		return outImage;
	}

	public void testGray() {
		ImageResource ir = new ImageResource();
		ImageResource gray = makeGray(ir);
		gray.draw();
	}
	
	public void selectAndConvert(){ //get selected files and draw each one of them one by one as gray. Later will learn to save these new gray images somewhere else.
	    DirectoryResource dr = new DirectoryResource();
	    for(File x : dr.selectedFiles()){
	        ImageResource inImage = new ImageResource(x);
	        ImageResource outImage = makeGray(inImage);
	        outImage.draw();
	       }
	    
	    
	}
	public void doSave(){
        DirectoryResource dr = new DirectoryResource();
        for(File x : dr.selectedFiles()){
            ImageResource image = new ImageResource(x);
            String fileName = image.getFileName();
            String newName = "copy-" + fileName;
            image.setFileName(newName);
            image.draw();
            image.save();
        }
    }
    
    public void allTogether(){
        DirectoryResource dr = new DirectoryResource();
        for(File x : dr.selectedFiles()){
            ImageResource inImage = new ImageResource(x);
            ImageResource outImage = new ImageResource(inImage.getWidth(), inImage.getHeight());
            String fileName = inImage.getFileName();
            String newName = "gray-" + fileName;
            for(Pixel pixel : outImage.pixels()){ //for each pixel in the now blank return image
                Pixel inPixel = inImage.getPixel(pixel.getX(), pixel.getY());
                
                int average = (inPixel.getRed() + inPixel.getBlue() + inPixel.getGreen())/3;
                pixel.setRed(average);
                pixel.setBlue(average);
                pixel.setGreen(average);
            }
            outImage.setFileName(newName);
            outImage.save();
        }
    }
}
