import acm.graphics.*;
import acm.program.*;
import acm.util.RandomGenerator;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.plugins.jpeg.JPEGImageWriteParam;
import javax.imageio.stream.FileImageOutputStream;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GraphicsProgramCapture extends GraphicsProgram {
	private GCanvas gc;
	private JPEGImageWriteParam jpegParams;
	private ImageWriter writer;
	private String dirName = "output";
	private int i = 0;
	
	public GraphicsProgramCapture() {
		super();
		gc = this.getGCanvas();
		jpegParams = new JPEGImageWriteParam(null);
		jpegParams.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
		jpegParams.setCompressionQuality(1f);
		writer = ImageIO.getImageWritersByFormatName("jpg").next();
		
		// Make directory
		File theDir = new File(dirName);
		// if the directory does not exist, create it
		if (!theDir.exists()) {
		    try{
		        theDir.mkdir();
		    } 
		    catch(SecurityException se){
		        //handle it
		    }      
		}
	}
	
	private BufferedImage getImage() {
		BufferedImage img = new BufferedImage(
				getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
		Graphics g = img.getGraphics();
		if (gc.isOpaque()) {
			g.setColor(gc.getBackground());
			g.fillRect(0, 0, getWidth(), getHeight());
		}
		gc.paint(g);
		return img;
	}
	
	protected void saveFrame() {
		BufferedImage img = getImage();
		String fileName = String.format("output/%04d.jpg", i);
		File outputfile = new File(fileName);
		try {
			writer.setOutput(new FileImageOutputStream(outputfile));
			writer.write(null, new IIOImage(img, null, null), jpegParams);
		} catch (IOException e) {
			System.out.println(e);
		}
		i++;
	}
}
