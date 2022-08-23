package image_processing;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageHandler {
	
	public String image_path;
	
	public ImageHandler(String image_path) {
		super();
		this.image_path = image_path;
	}
	public ImageHandler() {
		super();
		
	}
	

	public String getImage_path() {
		return image_path;
	}
	public void setImage_path(String image_path) {
		this.image_path = image_path;
	}
	public  int[][] readimage(String image_path){
		/*param filename : Image file object
		 *this function returns the grayscale equivalent of image
		 *as a 2D integer array . 
		 */
		File file;
		if(image_path.length() != 0)
		{
		 file = new File(image_path);
		}
		else{
			return null;
		}
		
		BufferedImage img;


		try {
			// read image using ImageIO 
			img = ImageIO.read(file);
			img.getScaledInstance(300, 300, Image.SCALE_DEFAULT);
			//get image dimensions
			int width = img.getWidth();
			int height = img.getHeight();
			int [][] readimageVal = new int [width][height];
			for (int x = 0; x < img.getWidth(); ++x)
		    for (int y = 0; y < img.getHeight(); ++y)
		    {
		        int rgb = img.getRGB(x, y);
		        int r = (rgb >> 16) & 0xFF;
		        int g = (rgb >> 8) & 0xFF;
		        int b = (rgb & 0xFF);
		        // Normalize and gamma correct:
		        double rr = Math.pow(r / 255.0, 2.2);
		        double gg = Math.pow(g / 255.0, 2.2);
		        double bb = Math.pow(b / 255.0, 2.2);
		        // Calculate luminance:
		        double lum = 0.2 * rr + 0.7 * gg + 0.1 * bb;
		        // Gamma compand and rescale to byte range:
		        int grayLevel = (int) (255.0 * Math.pow(lum, 1.0 / 2.2));
		        int gray = (grayLevel << 16) + (grayLevel << 8) + grayLevel; 
		        readimageVal[x][y] = gray;
		    }	// return the image thus convert
			return readimageVal;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;	
	}
	
	public void saveImage(BufferedImage img ){
		try {
			File f = new File("output.png");
			if(f.exists() == true){
				f.delete();
			}
			ImageIO.write(img , "png" ,new File( "output.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public void saveImage(BufferedImage img, String outputFileName ){
		try {
			
			ImageIO.write(img , "png" ,new File( outputFileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	

}
