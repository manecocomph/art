package com.maneco.art.aop;

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
}
