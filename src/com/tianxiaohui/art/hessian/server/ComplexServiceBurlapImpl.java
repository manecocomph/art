package com.tianxiaohui.art.hessian.server;

import java.util.Date;

import com.caucho.burlap.server.BurlapServlet;
import com.tianxiaohui.art.hessian.ComplexService;
import com.tianxiaohui.art.hessian.Employee;

public class ComplexServiceBurlapImpl extends BurlapServlet implements ComplexService {
	private static final long serialVersionUID = 1L;

	@Override
	public Employee getEmployee(long id) {
		Employee employee = new Employee();
		employee.setId(id);
		employee.setBirthDay(new Date());
		employee.setHeight(90);
		employee.setName("Kevin with Burlap ");
		employee.setOther(new Object());
		return employee;
	}

	@Override
	public Date today() {
		return new Date();
	}

}
