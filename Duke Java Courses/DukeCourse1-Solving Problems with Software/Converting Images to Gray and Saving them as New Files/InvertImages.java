
/**
 * Write a description of InvertImages here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.*;

public class InvertImages {

    public ImageResource invertImage(ImageResource inImage){
        ImageResource outImage = new ImageResource(inImage.getWidth(), inImage.getHeight());
        for(Pixel pixel : outImage.pixels()){
            Pixel inPixel = inImage.getPixel(pixel.getX(), pixel.getY());
            int red = inPixel.getRed();
            int blue = inPixel.getBlue();
            int green = inPixel.getGreen();
            pixel.setRed(255 - red);
            pixel.setBlue(255 - blue);
            pixel.setGreen(255 - green);
        }
        return outImage;
    }
    
    public void saveInvertImage(){
        DirectoryResource dr = new DirectoryResource();
        for(File x : dr.selectedFiles()){
            ImageResource inImage = new ImageResource(x);
            inImage.draw();
            ImageResource outImage = invertImage(inImage);
            String fileName = inImage.getFileName();
            String newName = "invert-" + fileName;
            outImage.setFileName(newName);
            outImage.save();
        }
    }
    public void printFiles(){
        DirectoryResource dr = new DirectoryResource();
        for(File x : dr.selectedFiles()){
            System.out.println(x);
        }
    }
}
