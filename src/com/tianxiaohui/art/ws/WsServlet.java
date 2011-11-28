package com.tianxiaohui.art.ws;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse rsps)
			throws ServletException, IOException {
		Publisher.publishWithEndpoint();
		PrintWriter pw = rsps.getWriter();
		pw.write("ok");
		pw.flush();
		pw.close();
	}

@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {
	//super.doPost(req, resp);
	this.doGet(req, resp);
}
}
