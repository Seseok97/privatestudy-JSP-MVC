package com.seseok.Commons;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Action {
	// 상수 + 추상메서드
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
								throws Exception;
}// interface Action end
