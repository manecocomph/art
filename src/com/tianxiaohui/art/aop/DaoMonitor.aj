package com.tianxiaohui.art.aop;

import com.tianxiaohui.peanut.dao.CategoryDao;
import com.tianxiaohui.peanut.dao.RawPriceDao;

public aspect DaoMonitor {
	// define pointcut 
	pointcut iceCountCall():
		call(public * CategoryDao.*(..)) || call(public * RawPriceDao.*(..));
	
	// define advice
	Object around(): iceCountCall() {
		long startTime = System.currentTimeMillis();  // get stat time
		Object obj = proceed();  					  // method call
		long endTime = System.currentTimeMillis();    // get end time
		// record this info
		PerformanceRecorder.record(new PerformanceUnit(thisJoinPoint.getSignature().toShortString(), (endTime - startTime)));
		return obj;
	}
	
	private boolean CategoryDao.using = false;
	
	pointcut callAny(CategoryDao dao):
		call (public * CategoryDao.*(..)) && target(dao);
	
	before(CategoryDao dao): callAny(dao) {
		if (dao.using) {
			System.out.println("now using");
		}
	}
}
