package image_processing;

import java.awt.image.BufferedImage;

public class ImageArithmetic extends ImageHandler{
	
//	additional image path extending image handler
	String image_path2;

	public ImageArithmetic() {
		super();
		}
	
	
	public ImageArithmetic(String image_path , String image_path2) {
		super(image_path);
		this.image_path2 = image_path2;
		}
	
//	Addition of images
	public  BufferedImage img_arithmetic_addition(int[][] imageDataFirstImage ,int[][] imageDataSecondImage){
		BufferedImage finalImage = new BufferedImage(imageDataFirstImage.length, imageDataFirstImage[0].length, BufferedImage.TYPE_USHORT_GRAY);

		int width = Math.min(imageDataFirstImage[0].length ,imageDataSecondImage[0].length );
		int height = Math.min(imageDataFirstImage.length , imageDataSecondImage.length);
		for(int x = 0; x < height; x++){
			for(int y = 0; y < width; y++){
				int f = imageDataFirstImage[x][y];
				int s = imageDataSecondImage[x][y];
				int val = f+s; // testssfdsfsf
				finalImage.setRGB(x, y, f+s );
			}
		}
		this.saveImage(finalImage);
		return  finalImage;
	}
//	Subtraction of images
	public  BufferedImage img_arithmetic_subtraction(int[][] imageDataFirstImage ,int[][] imageDataSecondImage ){
		BufferedImage finalImage = new BufferedImage(imageDataFirstImage.length, imageDataFirstImage[0].length, BufferedImage.TYPE_USHORT_GRAY);


		int width = Math.min(imageDataFirstImage[0].length ,imageDataSecondImage[0].length );
		int height = Math.min(imageDataFirstImage.length , imageDataSecondImage.length);
		for(int x = 0; x < height; x++){
			for(int y = 0; y < width; y++){
				int f = imageDataFirstImage[x][y];
				int s = imageDataSecondImage[x][y];
				int p = (f-s)<0?0:(f-s);
				finalImage.setRGB(x, y, p );
			}
		}
		this.saveImage(finalImage);
		return finalImage;
	}
//	Divison of images
	public  BufferedImage img_arithmetic_division(int[][] imageDataFirstImage ,int[][] imageDataSecondImage ){
		BufferedImage finalImage = new BufferedImage(imageDataFirstImage.length, imageDataFirstImage[0].length, BufferedImage.TYPE_USHORT_GRAY);
			
		int width = Math.min(imageDataFirstImage[0].length ,imageDataSecondImage[0].length );
		int height = Math.min(imageDataFirstImage.length , imageDataSecondImage.length);

		for(int x = 0; x < height; x++){
			for(int y = 0; y < width; y++){
				int f = imageDataFirstImage[x][y];
				int s = imageDataSecondImage[x][y];
				int val;
				if (s!=0){
					 val = f/s;
				}
				else{
					 val = f;
				}
				finalImage.setRGB(x, y,val
						);
			}
		}
		this.saveImage(finalImage);
		return finalImage;
	}
//	multiplication of images
	public  BufferedImage img_arithmetic_multiplication(int[][] imageDataFirstImage ,int[][] imageDataSecondImage , int maskSize){
		BufferedImage finalImage = new BufferedImage(imageDataFirstImage.length, imageDataFirstImage[0].length, BufferedImage.TYPE_USHORT_GRAY);
		System.out.println("her");
		int width = imageDataFirstImage[0].length;
		int height = imageDataFirstImage.length;

		int maxm=-1;
		for(int y = 0; y < height; y++){
			for(int x = 0; x < width; x++){
				int f = imageDataFirstImage[x][y];
				int s = imageDataSecondImage[x][y];
				maxm = Math.max(maxm , f*s);
				finalImage.setRGB(x, y, f*s );
			}
		}
		System.out.println(maxm);
		this.saveImage(finalImage);
		return finalImage;
	}

	public void doProcessing(String process){
		int[][] img1 = this.readimage(image_path);
		int[][] img2 = this.readimage(image_path2);
		switch(process)
		{
		case "addition" :
			this.img_arithmetic_addition(img1, img2);
			break;
		case "subtraction":
			this.img_arithmetic_subtraction(img1, img2);
			break;
		case "multiplication":
			this.img_arithmetic_subtraction(img1, img2);
		case "division":
			this.img_arithmetic_division(img1, img2);
		}
		
	}


}
