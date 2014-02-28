package com.allzai.util;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class FileCompressUtil {

	private File file = null; 					// 文件对象
	private String inputDir; 					// 输入图路径
	private String outputDir; 					// 输出图路径
	private String inputFileName; 	 		// 输入图文件名
	private String outputFileName; 		// 输出图文件名
	private int outputWidth = 75; 	 		// 默认输出图片宽
	private int outputHeight = 75;  		// 默认输出图片高
	private boolean proportion = true;// 是否等比缩放标记(默认为等比缩放)
	
	public boolean doFileCompress(String inputDir, String outputDir, String inputFileName, String outputFileName, int width, int height, boolean gp) {
		this.inputDir = inputDir;
		this.outputDir = outputDir;
		this.inputFileName = inputFileName;
		this.outputFileName = outputFileName;
		this.outputWidth = width;
		this.outputHeight = height;
		this.proportion = gp;
		return dealFilecompress();
	}

	public boolean dealFilecompress() {
		try {
			file = new File(inputDir + inputFileName);
			if (!file.exists()) {return false;}
			
			Image img = ImageIO.read(file);
			if (img.getWidth(null) == -1) {
				return false;
			} else {
				int newWidth, newHeight;
				if (this.proportion == true) {
					/*** 等比压缩处理 */
					double rate1 = ((double) img.getWidth(null))	/ (double) outputWidth + 0.1;
					double rate2 = ((double) img.getHeight(null)) / (double) outputHeight + 0.1;
					double rate = rate1 > rate2 ? rate1 : rate2;
					newWidth = (int) (((double) img.getWidth(null)) / rate);
					newHeight = (int) (((double) img.getHeight(null)) / rate);
				} else {
					newWidth = outputWidth;
					newHeight = outputHeight;
				}
				/*** Image.SCALE_SMOOTH 的缩略算法 生成缩略图片的平滑度的 优先级比速度高 生成的图片质量比较好 但速度慢*/
				BufferedImage tag = new BufferedImage((int) newWidth, (int) newHeight, BufferedImage.TYPE_INT_RGB);
				tag.getGraphics().drawImage(img.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH), 0, 0, null);
				FileOutputStream out = new FileOutputStream(outputDir + outputFileName);
				JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
				encoder.encode(tag);
				out.close();
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return true;
	}

	public static void main(String[] arg) {

		FileCompressUtil mypic = new FileCompressUtil();
		mypic.doFileCompress("E:\\testFIle\\before\\", "E:\\testFIle\\after\\", "20111003111026695.jpg", "_123456.jpg", Constants.FileOutputWidth, Constants.FileOutputHeight, true);

	}

}
