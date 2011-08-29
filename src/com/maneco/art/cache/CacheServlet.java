package com.maneco.art.cache;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jcs.JCS;
import org.apache.jcs.access.exception.CacheException;

public class CacheServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		super.doGet(req, resp);
		resp.setContentType("text/html;charset=UTF-8");
		
		PrintWriter pw = resp.getWriter();
		try {
			pw.write(JCS.getAccess("max_objects").getCacheAttributes().toString());
			pw.write("\n");
		} catch (CacheException e) {
			e.printStackTrace();
		}
		pw.flush();
		pw.close();
	}
	
	

}
