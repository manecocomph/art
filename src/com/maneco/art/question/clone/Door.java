package com.maneco.art.question.clone;

import java.io.Serializable;

public class Door implements Cloneable, Serializable {
	private Lock lock;

	public Lock getLock() {
		return lock;
	}

	public void setLock(Lock lock) {
		this.lock = lock;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		Door cloned = (Door) super.clone();
		//TODO need check if null or not
		cloned.setLock((Lock) this.getLock().clone());
		
		return cloned;
	}
	
	
}
