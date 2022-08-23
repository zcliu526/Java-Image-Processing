package image_processing;

import java.awt.image.BufferedImage;
import java.io.File;

public class ImageIntesity extends ImageHandler{
	
	public ImageIntesity() {
		super();
	}
	public ImageIntesity(String image_path) {
		super(image_path);
		// TODO Auto-generated constructor stub
	}
	
	// logarithmic transformation
	public  BufferedImage log_trans (int[][] imageData , int c){

        BufferedImage LogImage = new BufferedImage(imageData.length, imageData[0].length, BufferedImage.TYPE_BYTE_GRAY);
        double temp;
        for (int i =0 ; i<imageData.length ; i ++){
        for (int j=0 ; j<imageData[0].length ; j++){
                int rgb = imageData[i][j];
                rgb = (rgb<<16)|(rgb<<8)|(rgb);
                temp = Math.log10(rgb+1);
                rgb = (int) (c * temp); 
                LogImage.setRGB(i, j, rgb);
        }}
        
        // save the image 
        this.saveImage(LogImage,this.image_path+"-manipulated.png");
        return LogImage;
    }
	
//	Image negative transformation
	public BufferedImage negativeImage(int[][] imageData )
	{
		BufferedImage finalImage = new BufferedImage(imageData.length, imageData[0].length, BufferedImage.TYPE_USHORT_GRAY);
			
			 int width = imageData[0].length;
		      int height = imageData.length;
		
	        for(int y = 0; y < height; y++){
	            for(int x = 0; x < width; x++){
	            			
	            	
	            			finalImage.setRGB(y, x, 255 - imageData[y][x] );
	                    }
	                }
	        this.saveImage(finalImage,this.image_path+"-manipulated.png");
	        return  finalImage;
		}
	public BufferedImage powerLaw(int[][] imageData ,int c ,  float f)
	{
		BufferedImage finalImage = new BufferedImage(imageData.length, imageData[0].length, BufferedImage.TYPE_USHORT_GRAY);
			
			 int width = imageData[0].length;
		      int height = imageData.length;
		
	        for(int x = 0; x < height; x++){
	            for(int y = 0; y < width; y++){
	            			
//	            	finalImage.setRGB(x, y, Math.min( (int) (c*Math.pow(imageData[x][y], f)), 255));
	            	double img = imageData[x][y]/255.0;
	            	System.out.println(img);
	            	int pixel_value = (int) (  c*Math.pow(img, f) );
//	            	pixel_value = pixel_value>255?255:pixel_value;
	            	finalImage.setRGB(x, y, pixel_value);
	            	System.out.println(pixel_value);
	            
	            }
	             }
	        File new_ = new File(this.image_path + "-manipulated.png");
	        if(new_.exists()==true)new_.delete();
	        this.saveImage(finalImage , this.image_path+"-manipulated.png");
	        return  finalImage;
		}
		
//	do processing method to handle other options
	public void doProcessing(String process ,int c ,float f){
		int[][] img = this.readimage(image_path);
		// get the transformation
		switch(process)
		{
		case "logarithmc_transformation":
			log_trans(img, c);
			break;
		case "power_law":
			powerLaw(img, c, f);
		case "negative":
			negativeImage(img);
			
			
		}
		
	}
	
}
