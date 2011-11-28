package com.tianxiaohui.peanut.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tianxiaohui.peanut.service.ServiceFactory;

public class RegionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse rsps)
			throws ServletException, IOException {
		//super.doGet(req, rsps);
		rsps.setContentType("text/html;charset=UTF-8");
		//rsps.setHeader("Cache-Control", "no-cache");
		
		//TODO 
		ServiceFactory.publishService();
		
		String str = null;
		
		Object obj = req.getParameter("city");
		if (null != obj) {
			str = ServiceFactory.getRegionService().getAreaDropdownOption(Integer.parseInt(obj.toString()));
		}
		
		obj = req.getParameter("province");
		if (null != obj) {
			str = ServiceFactory.getRegionService().getCityDropdownOption(Integer.parseInt(obj.toString()));
		}
		
		obj = req.getParameter("country");
		if (null != obj) {
			str = ServiceFactory.getRegionService().getProvinceDropdownOption(86);
		}
		
		PrintWriter pw = rsps.getWriter();
		pw.write(str);
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
