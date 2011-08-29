package com.maneco.npt.model;

import com.maneco.npt.vo.Msg;

public class MyMsgInterseptor extends MsgInterseptor {

	@Override
	public Msg process(Msg msg) {
		if (msg.getContent().contains("288")) {
			return new Msg("Auto", "you contain 288");
		}
		return null;
	}

}
