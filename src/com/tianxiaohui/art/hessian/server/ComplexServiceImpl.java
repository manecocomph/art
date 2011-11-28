package com.tianxiaohui.art.hessian.server;

import java.util.Date;

import com.caucho.hessian.server.HessianServlet;
import com.tianxiaohui.art.hessian.ComplexService;
import com.tianxiaohui.art.hessian.Employee;

public class ComplexServiceImpl extends HessianServlet implements ComplexService {
	private static final long serialVersionUID = 1L;

	@Override
	public Employee getEmployee(long id) {
		Employee employee = new Employee();
		employee.setId(id);
		employee.setBirthDay(new Date());
		employee.setHeight(90);
		employee.setName("Jason");
		employee.setOther(new Object());
		return employee;
	}

	@Override
	public Date today() {
		//Not recommended, consider time zone
		return new Date();
	}

}
