package com;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class VerifyLoginServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String privateKey = "3e81ef75d797e93c3496736b457ea3f6";
		GeetestLib geetest = new GeetestLib(privateKey);
		boolean result = geetest.validate(
				request.getParameter("geetest_challenge"),
				request.getParameter("geetest_validate"),
				request.getParameter("geetest_seccode"));
		if (result) {
			System.out.println("Yes!");
		} else {
			System.out.println("No!");
		}
	}

}
