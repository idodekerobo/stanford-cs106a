import acm.graphics.*;
import acm.program.*;
import acm.util.*;

public class FlipImage extends GraphicsProgram {

	public void run() {
		GImage pic = new GImage("unnamed.jpg");
		double x = (getWidth() - pic.getWidth())/2;
		double y = (getHeight() - pic.getHeight())/2;
		
		add(flipHorizontal(pic), x, y);
	}
	
	private GImage flipVertical(GImage image) {
		int[][] array = image.getPixelArray();
		int height = array.length;
		//int width = array[0].length;
		
		for (int p1 = 0; p1 < height/2; p1++) {
			int p2 = height - 1 - p1;
			int[] temp = array[p1];
			array[p1] = array[p2];
			array[p2] = temp;
		}
		
		return new GImage(array);
	}
	
	
	private GImage flipHorizontal(GImage image) {
		int[][] array = image.getPixelArray();
		int width = array[0].length;
		int height = array.length;
		//have to work all the way across - doing half for the height will only flip half the image
		for (int i=0; i < height; i++) {
			for (int p1 = 0; p1 < width/2; p1++) {
				int p2 = width - 1 - p1;
				int temp = array[i][p1];
				array[i][p1] = array[i][p2];
				array[i][p2] = temp;
			}
		}
		return new GImage(array);
	}
	
	
	
}
