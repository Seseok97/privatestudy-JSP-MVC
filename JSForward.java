package com.seseok.Commons;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

public class JSForward {
	// js 를 이용하여 페이지를 이동하는 경우의 동작
	// 1. 뒤로 가기
	public static void alertAndBack(HttpServletResponse response, String msg) {
		try {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print("alert('"+msg+"');");
			out.print("history.back();");
			out.print("</script>");
			out.close();
		}catch (Exception e) {
			e.printStackTrace();
		}// tc end
	}//alertAndBack() method end
	// 2. 지정한 페이지로 이동하기.
	public static void alertAndMove(HttpServletResponse response, String msg,String location) {
		try {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print("alert('"+msg+"');");
			out.print("location.href='"+location+"';");
			out.print("</script>");
			out.close();
		}catch (Exception e) {
			e.printStackTrace();
		}// tc end
	}//alertAndBack() method end
	

}// public class end
