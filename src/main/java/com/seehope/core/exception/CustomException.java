package com.seehope.core.exception;

import com.seehope.core.global.CodeEnum;

public class CustomException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	public CodeEnum codeenum;

	public CustomException() {
	}

	public CustomException(CodeEnum codeenum) {
		super(codeenum.getMsg());
		this.codeenum = codeenum;
	}

	public CustomException(CodeEnum codeenum, String msg) {
		super(msg);
		this.codeenum = codeenum;
	}

	public CustomException(String msg) {
		super(msg);
	}

	public CustomException(Throwable t) {
		super(t);
	}
}
