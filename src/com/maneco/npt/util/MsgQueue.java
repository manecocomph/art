package com.maneco.npt.util;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import com.maneco.npt.vo.Msg;

//TODO not consider thread synchonize
public class MsgQueue {
	
	private Msg[] msgs;
	private static final int DEFAULT_SIZE = 5000;
	private int size = DEFAULT_SIZE;
	private float THRESHOLD_PERCENT = 0.9F;
	private AtomicInteger start = new AtomicInteger(0);
	private AtomicInteger end = new AtomicInteger(0);
	private int threshold = (int) (DEFAULT_SIZE * THRESHOLD_PERCENT);
	
	public MsgQueue() {
		this.msgs = new Msg[DEFAULT_SIZE];
	}
	
	public MsgQueue(int size) {
		if (size > 0) {
			this.msgs = new Msg[size];
			this.size = size;
			this.threshold = (int) (size * THRESHOLD_PERCENT);
		} else {
			this.msgs = new Msg[DEFAULT_SIZE];
		}
	}
	
	public void put(Msg msg) {
		if(null == msg) {
			return;
		}
		
		if (this.threshold < (this.end.get() - this.start.get())) {
			//TODO clean up & save to DB
			this.start.addAndGet(this.end.get() - this.threshold);
		}
		
		msg.setId(this.end.get());
		this.msgs[this.end.getAndIncrement() % this.size] = msg;
	}
	
	public String getLastest(int position) {
		int start = this.start.get() % this.size;
		int end = this.end.get() % this.size;
		position %= this.size;
		
		if (start < end) {
			if (position > start && position < end) {
				StringBuffer sb = new StringBuffer();
				for (int i = position; i < end; i++) {
					sb.append(this.msgs[i].getUserName() + ": " + this.msgs[i].getContent() + "\n");
				}
				
				return sb.toString();
			} else {
				return null;
			}
			
		} else {
			if (position > end && position < start) {
				return null;
			} else {
				StringBuffer sb = new StringBuffer();
				int t = 0;
				for (int i = position; i < (this.size + end); i++) {
					t = i % this.size;
					sb.append(this.msgs[t].getUserName() + ": " + this.msgs[t].getContent() + "\n");
				}
				
				return sb.toString();
			}
		}
	}
	
	public List<Msg> getLastestMsgList(int position) {
		return null;
	}
}
