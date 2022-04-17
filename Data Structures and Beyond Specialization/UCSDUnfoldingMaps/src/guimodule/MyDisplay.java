package guimodule;

import processing.core.*;

public class MyDisplay extends PApplet {
	
	public void setup() {
		
		size(400,400);
		background(0,0,0);
		
		
	}
	
	public void draw() {
		//head
		fill(151,102,0);
		ellipse(width/2,height/2,width/2,height/2);
		
		//eyes
		fill(0,0,0);
		ellipse(width/2-25,height/2-50,width/15,height/20);
		ellipse(width/2+25,height/2-50,width/15,height/20);
		
		
		//pupils
		fill(255,255,255);
		ellipse(width/2+25,height/2-50,width/60,height/60);
		ellipse(width/2-25,height/2-50,width/60,height/60);
		
		//nose
		fill(200,102,0);
		triangle(width/2,height/2-10,width/2-10,height/2,width/2+10,height/2);
		
		//mouth
		noFill();
		arc(width/2,height/2+27,width/4,height/8,0,PI);
		
		

		
	}
}
