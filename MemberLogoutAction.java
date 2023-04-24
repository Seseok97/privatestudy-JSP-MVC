package com.seseok.Action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.seseok.Commons.Action;
import com.seseok.Commons.ActionForward;

// 로그아웃 동작 수행
public class MemberLogoutAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(" M : MemberLogoutAction.execute()");
		
		// 세선정보 초기화
		HttpSession session = request.getSession();
		session.invalidate();
		
		System.out.println(" M : 세션정보 초기화.");
		
		// js를 활용하여 페이지 이동
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print("<script>");
		out.print("alert('로그아웃 성공!');");
		out.print("location.href='./Main.me'");
		out.print("</script>");

		return null;
		// js로 이동을 실행 한 경우 !!!! ActionForward(티켓)은 null이어야 한다.
		// 컨트롤러의 중복실행을 막기 위함!
		
	} // execute() method end
	

}// public class end
