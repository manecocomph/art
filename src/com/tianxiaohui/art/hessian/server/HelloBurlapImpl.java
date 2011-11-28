package com.tianxiaohui.art.hessian.server;

import com.caucho.burlap.server.BurlapServlet;
import com.tianxiaohui.art.hessian.Hello;

public class HelloBurlapImpl extends BurlapServlet implements Hello {

	private static final long serialVersionUID = 1L;

	@Override
	public int howOld() {
		return 888;
	}

	@Override
	public String fatherName(String myName) {
		return "fater name with BurlapImpl " + myName;
	}

}
