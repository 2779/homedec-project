package HelperPack;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ImgPack {
	public static Image getImage(String url,int width,int height) {
		BufferedImage img = null;
		try {
		    img = ImageIO.read(new File(url));
		} catch (IOException e) {
		    e.printStackTrace();
		}
		return img.getScaledInstance(width, height,Image.SCALE_SMOOTH);
	}
}
