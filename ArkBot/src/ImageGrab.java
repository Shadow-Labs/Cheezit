import java.awt.Color;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageGrab {
	private Robot bot;
	private Rectangle chatLoc = new Rectangle();
	public ImageGrab () {
		this.bot = ArkBot.bot;
		chatLoc.setBounds(12, 260, 450, 140);
	}
	
	public void Grab(Rectangle loc) {
		BufferedImage img = bot.createScreenCapture(loc);
		try {
			ImageIO.write(img, "png", new File("ArkBotFiles/OCRImages/ImageGrab/grab.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void WhiteConvert() {
		BufferedImage inputFile = null;
        try {
            inputFile = ImageIO.read(new File("ArkBotFiles/OCRImages/ImageGrab/grab.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int x = 0; x < inputFile.getWidth(); x++) {
            for (int y = 0; y < inputFile.getHeight(); y++) {
                int rgba = inputFile.getRGB(x, y);
                Color col = new Color(rgba, true);
                int wl = 182;
                if (col.getRed() > wl && col.getGreen() > wl && col.getBlue() > wl) {
                	col = new Color(0, 0, 0);
                } else {
                    col = new Color(255,255,255);
                }
                inputFile.setRGB(x, y, col.getRGB());
            }
        }

        try {
            File outputFile = new File("ArkBotFiles/OCRImages/ImageGrab/grabConvert.png");
            ImageIO.write(inputFile, "png", outputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public void CyanConvert() {
		BufferedImage inputFile = null;
        try {
            inputFile = ImageIO.read(new File("ArkBotFiles/OCRImages/ImageGrab/grab.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int x = 0; x < inputFile.getWidth(); x++) {
            for (int y = 0; y < inputFile.getHeight(); y++) {
                int rgba = inputFile.getRGB(x, y);
                Color col = new Color(rgba, true);
                if (col.getRed() == 0 && col.getGreen() > 250 && col.getBlue() > 250) {
                	col = new Color(0, 0, 0);
                } else {
                    col = new Color(255,255,255);
                }
                inputFile.setRGB(x, y, col.getRGB());
            }
        }

        try {
            File outputFile = new File("ArkBotFiles/OCRImages/ImageGrab/grabConvert.png");
            ImageIO.write(inputFile, "png", outputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public void GPSConvert() {
		BufferedImage inputFile = null;
        try {
            inputFile = ImageIO.read(new File("ArkBotFiles/OCRImages/GPS/GPS5.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int x = 0; x < inputFile.getWidth(); x++) {
            for (int y = 0; y < inputFile.getHeight(); y++) {
                int rgba = inputFile.getRGB(x, y);
                Color col = new Color(rgba, true);
                int rl = 150;
                int ul = 100;
                int ll = 0;
                int bl = 20;
                if (col.getRed() > rl && 
                		col.getGreen() > ll && col.getGreen() < ul && 
                		col.getBlue() < bl) {
                	col = new Color(0, 0, 0);
                } else {
                    col = new Color(255,255,255);
                }
                inputFile.setRGB(x, y, col.getRGB());
            }
        }

        try {
            File outputFile = new File("ArkBotFiles/OCRImages/ImageGrab/GPS5C.png");
            ImageIO.write(inputFile, "png", outputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

}
