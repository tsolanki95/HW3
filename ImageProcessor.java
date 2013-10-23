/**
 * processes a Pic object and displays the image as greysacle, inverted, with watermark,
 * only with red, green, or blue, and posterized
 *
 * @author Tanmay Solanki
 * @version 1.0
 */
public class ImageProcessor {

	private Pic picture;

	/**
	 * Constructor to create an ImageProcessor class
	 *
	 * @param Picture that will be encapsulated in the ImageProcessor object
	 * @return none
	 */
	public ImageProcessor(Pic picture){
		this.picture = picture;
	}

	/**
	 * Displays greyscaled image
	 *
	 * @param none
	 * @return none
	 */
	public void greyscale() {
		Pic original = picture.deepCopy();
		for(int i = 0; i < original.getWidth(); i++) {
			for(int j = 0; j < original.getHeight(); j++) {
				Pixel tempPix = original.getPixel(i,j);
				int avg = (tempPix.getRed()+tempPix.getBlue()+tempPix.getGreen())/3;
				tempPix.setRed(avg);
				tempPix.setBlue(avg);
				tempPix.setGreen(avg);
			}
		}
		original.show();
	}

	/**
	 * Displays inverted image
	 *
	 * @param none
	 * @return none
	 */
	public void invert() {
		Pic original = picture.deepCopy();
		for(int i = 0; i < original.getWidth(); i++) {
			for(int j = 0; j < original.getHeight(); j++) {
				Pixel tempPix = original.getPixel(i,j);
				tempPix.setRed(255-tempPix.getRed());
				tempPix.setGreen(255-tempPix.getGreen());
				tempPix.setBlue(255-tempPix.getBlue());
			}
		}
		original.show();
	}

	/**
	 * Displays original image with watermark
	 *
	 * @param Pic object of the watermark picture
	 * @return none
	 */
	public void watermark(Pic watermark) {
		Pic original = picture.deepCopy();
		int width = 0;
		int height = 0;

		if(original.getWidth() > watermark.getWidth()) {
			width = original.getWidth();
		} else {
			width = watermark.getWidth();
		}

		if (original.getHeight() > watermark.getHeight()) {
			height = original.getHeight();
		} else {
			height = watermark.getHeight();
		}

		for(int i = 0; i < width; i++) {
			for(int j = 0; j < height; j++) {
				Pixel origPix = original.getPixel(i,j);
				Pixel watPix = watermark.getPixel(i,j);
				origPix.setGreen((origPix.getGreen()+watPix.getGreen())/2);
				origPix.setBlue((origPix.getBlue()+watPix.getBlue())/2);
				origPix.setRed((origPix.getRed()+watPix.getRed())/2);
			}
		}
		original.show();
	}

	/**
	 * Displays image with only red channel
	 *
	 * @param none
	 * @return none
	 */
	public void onlyRed() {
		Pic original = picture.deepCopy();
		for(int i = 0; i < original.getWidth(); i++) {
			for(int j = 0; j < original.getHeight(); j++) {
				Pixel tempPix = original.getPixel(i,j);
				tempPix.setGreen(0);
				tempPix.setBlue(0);
			}
		}
		original.show();
	}

	/**
	 * Displays image with only blue channel
	 *
	 * @param none
	 * @return none
	 */
	public void onlyBlue() {
		Pic original = picture.deepCopy();
		for(int i = 0; i < original.getWidth(); i++) {
			for(int j = 0; j < original.getHeight(); j++) {
				Pixel tempPix = original.getPixel(i,j);
				tempPix.setRed(0);
				tempPix.setGreen(0);
			}
		}
		original.show();
	}

	/**
	 * Displays image with only green channel
	 *
	 * @param none
	 * @return none
	 */
	public void onlyGreen() {
		Pic original = picture.deepCopy();
		for(int i = 0; i < original.getWidth(); i++) {
			for(int j = 0; j < original.getHeight(); j++) {
				Pixel tempPix = original.getPixel(i,j);
				tempPix.setRed(0);
				tempPix.setBlue(0);
			}
		}
		original.show();
	}

	/**
	 * Displays posterized image
	 *
	 * @param none
	 * @return none
	 */
	public void maximixe() {
		Pic original = picture.deepCopy();
		for(int i = 0; i < original.getWidth(); i++) {
			for(int j = 0; j < original.getHeight(); j++) {
				Pixel tempPix = original.getPixel(i,j);
				if(tempPix.getBlue() > tempPix.getGreen() && tempPix.getBlue() > tempPix.getRed()) {
					tempPix.setRed(0);
					tempPix.setGreen(0);
				} else if (tempPix.getRed() > tempPix.getBlue() && tempPix.getRed() > tempPix.getGreen()) {
					tempPix.setGreen(0);
					tempPix.setBlue(0);
				} else {
					tempPix.setRed(0);
					tempPix.setBlue(0);
				}
			}
		}
		original.show();
	}

	/**
	 * The main method that takes two command line arguments 
	 * and creates an ImageProcessor object and with that sequencially 
	 * displays the image as greysacle, inverted, with watermark,
 	 * only with red, green, or blue, and posterized 
	 *
	 * @param none
	 * @return none
	 */
	public static void main(String[] args) {
		ImageProcessor processor = new ImageProcessor(new Pic(args[0]));
		System.out.println("Displaying grayscale image.");
		processor.greyscale();
		System.out.println("Displaying inverted image.");
		processor.invert();
		System.out.println("Displaying image with watermark.");
		processor.watermark(new Pic(args[1]));
		System.out.println("Displaying image with only red.");
		processor.onlyRed();
		System.out.println("Displaying image with only green.");
		processor.onlyGreen();
		System.out.println("Displaying image with only blue.");
		processor.onlyBlue();
		System.out.println("Displaying postrized image.");
		processor.maximixe();
	}
}