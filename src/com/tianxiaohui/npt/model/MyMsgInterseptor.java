package com.tianxiaohui.npt.model;

import com.tianxiaohui.npt.vo.Msg;

public class MyMsgInterseptor extends MsgInterseptor {

	@Override
	public Msg process(Msg msg) {
		if (msg.getContent().contains("288")) {
			return new Msg("Auto", "you contain 288");
		}
		return null;
	}

}
