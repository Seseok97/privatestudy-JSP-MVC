package com.seseok.Action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * Controller > Servlet 구현
 * Model-View 연결
 */
public class MemberFrontController extends HttpServlet {
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		System.out.println("doProcess() 호출");
		// URL: http://localhost:8088/EL-MVC/*.me
		// URL: http://localhost:8088/EL-MVC/MemberJoin.me
		// URI: /EL-MVC/*.me // > 프로토콜, 포트번호 없음
		
		// 3가지 파트의 동작 구현 메서드
		//////////////////////////////////1. 가상주소 계산//////////////////////////////////////////////
		// 가상주소 가져오기.
		System.out.println("1. 가상주소 계산 >> 시작");
		// 1) URI 가져오기
		String requestURI = request.getRequestURI();
		System.out.println("> requestURI: "+requestURI);
		// 2) 프로젝트명 가져오기
		String ctxPath = request.getContextPath();
		System.out.println("> ctxPath: "+ctxPath );
		// 3) 가상주소 = URI - 프로젝트명
		String command = requestURI.substring(ctxPath.length());
		System.out.println("> command: "+ command);
		
		
		System.out.println("1. 가상주소 계산 >> 끝");
		//////////////////////////////////1. 가상주소 계산//////////////////////////////////////////////
		
		
		//////////////////////////////////2. 가상주소 매핑//////////////////////////////////////////////
		System.out.println("\n\n");
		System.out.println("2. 가상주소 매핑 >> 시작");
		
		
		Action action = null; // 행동
		ActionForward forward = null; // 티켓
		// 회원가입 동작 > ./MemberJoin.me
		if(command.equals("/MemberJoin.me")) {
			System.out.println("C: /MemberJoin.me 실행!");
			System.out.println("C: DB사용 X, view페이지로 이동O >> 패턴 1번!");
			
			forward = new ActionForward();
			forward.setPath("./member/insertForm.jsp"); // 목적지 (회원가입 view페이지)
			forward.setRedirect(false); // 주소가 ~jsp로 나오면 몬가 .. 잘못된것임. 
			// 티켓생성 완료			// jsp로 끝나는 주소이기때문에 포워딩으로 jsp 가리기
			System.out.println("티켓 생성 완료!");
		}// MemberJoin.me end
		// 회원가입 동작 실행 > ./MemberJoinAction.me
		else if(command.equals("/MemberJoinAction.me")){
			System.out.println("C: /MemberJoinAction.me 실행!");
			System.out.println("C: DB사용 O, 페이지 이동 O >> 패턴 2번!!");
			// 모델을 사용하여 동작을 분리.
			action = new MemberJoinAction(); // UPCASTING 발생.
			// >> 동작을 action 객체로만 실행함으로써, 결합을 약하게 유지하는것.
			// >> 문제가 생기면 전체를 바꿀 필요가 없어짐!
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}// t-c end
			
		} // MemberJoinAction.me end
		
		System.out.println("2. 가상주소 매핑 >> 끝");
		System.out.println("\n\n");
		//////////////////////////////////2. 가상주소 매핑//////////////////////////////////////////////
		
		
		//////////////////////////////////3. 가상주소 이동//////////////////////////////////////////////
		System.out.println("3. 가상주소 이동 >> 시작");
		if(forward != null) { // 티켓이 정상적으로 생성 되었을때.
			// 페이지 이동
			if(forward.isRedirect()) {
				System.out.println("forward > sendRedirect 방식, 목적지: "+forward.getPath());
				response.sendRedirect(forward.getPath());
			}else {
				System.out.println("forward > forward 방식, 목적지: "+forward.getPath());
				RequestDispatcher dis =
						request.getRequestDispatcher(forward.getPath());
				dis.forward(request, response);
			}// i-e end
			
		}// if end
		
		
		System.out.println("3. 가상주소 이동 >> 끝");
		//////////////////////////////////3. 가상주소 이동//////////////////////////////////////////////
		System.out.println("doProcess() Controller 종료!");
		
	}// doProcess() method end

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		System.out.println("doGet() 호출");
		doProcess(request, response);
	}// doGet() method end

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		System.out.println("doPost() 호출");
		doProcess(request, response);
	}// doPost() method end
	

}// public class end
