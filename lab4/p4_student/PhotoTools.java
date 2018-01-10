package p4_student;

import cmsc131PhotoLibrary.Photograph;
import cmsc131PhotoLibrary.Pixel;

/**
 * This class will be written by the Student.  It provides various
 * static methods that take a photograph and produce a new one based
 * on it, but with various modifications.
 * 
 * See the project description for details of method implementations.
 * 
 * @author CMSC 131 Student
 *
 */
public class PhotoTools {


	//THIS METHOD IS PROVIDED AS A STARTING POINT.  PLEASE READ THROUGH
	//  IT AND THINK ABOUT WHAT IT DOES AND WHY - IF YOU AREN'T SURE OF
	//  SOMETHING, ASK US IN OFFICE HOURS!
	public static Photograph copy(Photograph photo) {
		Photograph newPhoto = new Photograph(photo.getWd(), photo.getHt());
		for (int x=0; x<photo.getWd(); x++) {
			for (int y=0; y<photo.getHt(); y++) {
				newPhoto.setPixel(x, y, photo.getPixel(x, y));
			}
		}
		return newPhoto;
	}


	public static Photograph isolateColor(Photograph photo, int type) {
		//Create an instance of Photograph
		Photograph newPhoto = new Photograph(photo.getWd(), photo.getHt());
		
		//The loop goes through each pixel
		for (int row=0; row<photo.getHt(); row++){
			for (int col=0; col<photo.getWd(); col++){
				//for red picture
				if (type==0){ 
					//create a new pixel with only red pixel showing
					Pixel p=new Pixel(photo.getPixel(col, row).getRed(), 0, 0);
					newPhoto.setPixel(col, row, p);
					//for blue picture
				}else if (type==1){
					newPhoto.setPixel(col, row, 
							//create a new pixel with only blue pixel showing
							new Pixel(0,0, photo.getPixel(col, row).getBlue()));
					//for green picture
				}else if (type==2){ 
					//create a new pixel with only green showing
					newPhoto.setPixel(col, row, 
							new Pixel(0, photo.getPixel(col, row).getGreen(),0));
				}
			}
		}
return newPhoto;
	}


	public static Photograph makeGrayscale(Photograph photo) {
		//Creating a new instance of the picture
		Photograph newPhoto = new Photograph(photo.getWd(), photo.getHt());
		//iterating through the pictures. 
		for (int row=0; row<photo.getHt(); row++){
			for (int col=0; col<photo.getWd(); col++){
						//selecting the pixel 
					Pixel pixel= photo.getPixel(col, row);
						//new pixel with the secret ingredient
						int grayValue = (int)(pixel.getRed() * 0.3) +
					               (int)(pixel.getGreen() * 0.6) +
					               (int)(pixel.getBlue() * 0.1);
						//changing the selected pixel to grey
					Pixel p=new Pixel(grayValue, grayValue, grayValue);
						newPhoto.setPixel(col, row, p);}
			}
		return newPhoto;
	}
	

	public static Photograph makeArtistic(Photograph photo) {
		//Creating a new instance of the photo
		Photograph newPhoto = new Photograph(photo.getWd(), photo.getHt());
		//Iterating through the pixel
		for (int row=0; row<photo.getHt(); row++){
			for (int col=0; col<photo.getWd(); col++){
				//selecting the pixel
						Pixel pixel= photo.getPixel(col, row);
						//finding the value of the color, adding it, and making the selection.
						int grayValue = (int)(pixel.getRed()) +
					               (int)(pixel.getGreen()) +
					               (int)(pixel.getBlue());
						//if else statement to go through the different option
						if (0<=grayValue && grayValue<=153 )
						{
							grayValue=0;
						}
						else if (154<=grayValue && grayValue<=306 )
						{
							grayValue=63;
						}
						else if (307<=grayValue && grayValue<=459 )
						{
							grayValue=127;
						}
						else if (460<=grayValue && grayValue<=612 )
						{
							grayValue=191;
						}
						else if (613<=grayValue && grayValue<=765 )
						{
							grayValue=255;
						}
						//Setting the new pixel to the appropriate value
				Pixel p=new Pixel(grayValue, grayValue, grayValue);
					newPhoto.setPixel(col, row, p);}
			}
		return newPhoto;
	}



	public static Photograph censorIt(Photograph photo) {
		//Creating a ne instance of the photo
		Photograph newPhoto = new Photograph(photo.getWd(), photo.getHt());
		//finding the edge of the picture
		int reWidth= (photo.getWd()%10);
		int reHight= (photo.getHt()%10);
		//Initializing the color value
		int red=0, blue=0, green=0;
		//for finding the average
		int count=0;
		//stepping through the picture 10 row at a time
		for(int row=0; row<(photo.getHt()-reHight); row+=10){
			//stepping through the picture 10 column at a time
			for (int col=0; col<(photo.getWd()-reWidth);col+=10)
			{
				//going inside the block of pixel by row
				for (int row1=row; row1<row+10; row1++)
				{
					//going inside the block of pixel in column
					for (int col1=col; col1<col+10; col1++){
						//selecting the individual pixel
						Pixel pixel= photo.getPixel(col1, row1);
						//finding the red, blue, and the green pixel value and adding them together
						red += (int)(pixel.getRed());
					     green+= (int)(pixel.getGreen());
					      blue+=  (int)(pixel.getBlue());
					      //the number of pixel
					      count++;}
				}
				//finding the average of the pixel
					red= (red/count);
					green=(green/count);
					blue=(blue/count);
					//setting each pixel on the block to the average color
					Pixel p= new Pixel(red, green, blue);
					for (int row11=row; row11<row+10; row11++)
					{
						for (int col1=col; col1<col+10; col1++){
					newPhoto.setPixel(col1, row11, p);}
					}
			//resetting all the value as you move through the block 		
			count=0;
			red=0;
			green=0;
			blue=0;
					}
			}
		return newPhoto;	
		//HINT: To deal with the edge cases, you'll want to 
		//      check you aren't going out of bounds in the
		//      middle of your nested nesting of loops...


	}


	public static Photograph stretched(Photograph photo, int type) {
		//for horizontal stretch
		if (type==0)
		{
		//new instance of photo thats twice as wide
		Photograph newPhoto = new Photograph(2*photo.getWd(), photo.getHt());
			for (int x=0; x<photo.getWd(); x++) {
				for (int y=0; y<photo.getHt(); y++) {
					//creating two setters as the pixel go through the loop
					//each column is doubled. 
					newPhoto.setPixel((2*x+1), y, photo.getPixel(x, y));
					newPhoto.setPixel(2*x, y, photo.getPixel(x, y));
				}
		}
		return newPhoto;
		}
		
		else{
			//for vertical stretch, creating a new instance of photo thats twice as long
			Photograph newPhoto = new Photograph(photo.getWd(), 2*photo.getHt());
			//iterating through each pixel
			for (int x=0; x<photo.getWd(); x++) {
				for (int y=0; y<photo.getHt(); y++) {
					//creating two setters as the pixel go through the loop
					//each row is doubled. 
					newPhoto.setPixel(x, (2*y+1), photo.getPixel(x, y));
					newPhoto.setPixel(x, 2*y, photo.getPixel(x, y));
				}
			}
			return newPhoto;
		}
		
	}



	public static Photograph mirrorIt(Photograph photo) {
		//creating a new instance of the photo
		Photograph newPhoto = new Photograph(photo.getWd(), photo.getHt());
		for (int x=0; x<photo.getWd(); x++) {
			for (int y=0; y<photo.getHt(); y++) {
				//as we go through the loop, the pixel order in each column are reversed and set to k
				//this will mirror the image. 
				int k=photo.getWd()-x-1;
				//instead of getting the location of x in the photo, we get the pixel of where k would be
				//this allows us to reverse order each column
				newPhoto.setPixel(x, y, photo.getPixel(k, y));
			}
		}
		return newPhoto;
	}



	public static Photograph makeDoubleWithMirror(Photograph photo) {
		//new instance of the photo thats twice a wide
		Photograph newPhoto = new Photograph(2*photo.getWd(), photo.getHt());
		//looping through each pixel in the photo
		for (int x=0; x<photo.getWd(); x++) {
			for (int y=0; y<photo.getHt(); y++) {
				//creating a reverse order of pixel location and setting it to K
				int k=photo.getWd()-x-1;
				//creating a reverse/mirror image and setting it to the first half of the column
				newPhoto.setPixel(x, y, photo.getPixel(k, y));
				//starting on the second half of the column and setting a normal image layout
				newPhoto.setPixel(photo.getWd()+x, y, photo.getPixel(x, y));
			}
		}
		return newPhoto;
	}

	public static Photograph rotated(Photograph photo) {
		throw new RuntimeException("You'll implement this...");
	}

	public static Photograph upsideDown(Photograph photo) {
		throw new RuntimeException("You'll implement this...");
		
		//DON'T FORGET - THIS ONE SHOULD BE VERY SHORT!
		//  MORE THINKING THAN CODING!
	}



}
