package com.allzai.code;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.*;
import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;
import javax.servlet.http.*;

import org.apache.commons.lang.RandomStringUtils;

public class SmartCode extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public SmartCode() {
		super();
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		int width = 90, height = 25;
		BufferedImage image = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		Graphics g = image.getGraphics();
		Random random = new Random();
		g.setColor(getRandColor(200, 250));
		g.fillRect(0, 0, width, height);
		g.setFont(new Font("Times New Roman", Font.BOLD, 25));
		g.setColor(new Color(255, 255, 255));
		g.drawRect(1, 1, width - 1, height - 1);
		g.setColor(getRandColor(160, 200));
		for (int i = 0; i < 77; i++) {
			int x = random.nextInt(width);
			int y = random.nextInt(height); 
			int xl = random.nextInt(12);
			int yl = random.nextInt(12);
			g.drawLine(x, y, x + xl, y + yl);
		}
		String s = RandomStringUtils.random(4, true, true);
		g.setColor(new Color(20 + random.nextInt(110),
				20 + random.nextInt(110), 20 + random.nextInt(110)));
		g.drawString(s, 12, 20);
		HttpSession session = request.getSession();
		session.setAttribute("rand", s);
		g.dispose();
		ImageIO.write(image, "JPEG", response.getOutputStream());
	}

	Color getRandColor(int fc, int bc) {
		Random random = new Random();
		if (fc > 255)
			fc = 255;
		if (bc > 255)
			bc = 255;
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
		return;
	}

}
