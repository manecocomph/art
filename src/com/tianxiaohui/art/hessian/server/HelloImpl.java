package com.tianxiaohui.art.hessian.server;

import com.caucho.hessian.server.HessianServlet;
import com.tianxiaohui.art.hessian.Hello;

public class HelloImpl extends HessianServlet implements Hello {

	private static final long serialVersionUID = 1L;

	@Override
	public int howOld() {
		return 999;
	}

	@Override
	public String fatherName(String myName) {
		return "father" + myName;
	}
}
