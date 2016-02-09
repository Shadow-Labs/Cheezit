import java.awt.Color;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ChatImageGrab {
	private Robot bot;
	private Rectangle chatLoc = new Rectangle();
	public ChatImageGrab (Robot bot) {
		this.bot = bot;
		chatLoc.setBounds(12, 260, 450, 140);
	}
	
	public Image Grab() {
		BufferedImage img = bot.createScreenCapture(chatLoc);
		try {
			ImageIO.write(img, "png", new File("ArkBotFiles/OCRImages/ChatImageGrab/grab.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		InvertGrab();
		return img;
	}
	
	public void InvertGrab() {
		BufferedImage inputFile = null;
        try {
            inputFile = ImageIO.read(new File("ArkBotFiles/OCRImages/ChatImageGrab/grab.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int x = 0; x < inputFile.getWidth(); x++) {
            for (int y = 0; y < inputFile.getHeight(); y++) {
                int rgba = inputFile.getRGB(x, y);
                Color col = new Color(rgba, true);
                if (col.getRed() > 180 && col.getGreen() > 180 && col.getBlue() > 180) {
                	col = new Color(0, 0, 0);
                } else {
                    col = new Color(255,255,255);
                }
//                col = new Color(255 - col.getRed(),
//                        255 - col.getGreen(),
//                        255 - col.getBlue());
                inputFile.setRGB(x, y, col.getRGB());
            }
        }

        try {
            File outputFile = new File("ArkBotFiles/OCRImages/ChatImageGrab/grabInvert.png");
            ImageIO.write(inputFile, "png", outputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

}
