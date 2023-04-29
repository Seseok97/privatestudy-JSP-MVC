package com.seseok.Action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.seseok.Commons.Action;
import com.seseok.Commons.ActionForward;

/**
 * 
 * Controller > Servlet 구현
 * Model-View 연결
 */
public class MemberFrontController extends HttpServlet {
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		System.out.println("doProcess() 호출");
		// address
		// INDEX: http://localhost:8088/EL-MVC/index.jsp
		// 회원가입: http://localhost:8088/EL-MVC/MemberJoin.me
		// 로그인: http://localhost:8088/EL-MVC/MemberLogin.me
		// 메인: http://localhost:8088/EL-MVC/Main.me
		
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
		// 로그인동작 > 1) 로그인 페이지로 이동
		else if(command.equals("/MemberLogin.me")) {
			System.out.println("C: /MemberLogin.me 호출!");
			System.out.println("C: DB사용 X, view 페이지(정보입력 페이지) 이동 ! > 패턴 1번!");
			
			// 패턴 1
			forward = new ActionForward();
			forward.setPath("./member/loginForm.jsp");
			forward.setRedirect(false); // .jsp의 주소를 노출시켜선 안된다 !! >> forwarding 방식으로 이동.
			
		}// else if(./MemberLogin.me) end
		// 로그인동작 > 2) 로그인 실행
		else if(command.equals("/MemberLoginAction.me")) {
			System.out.println("C: /MemberLoginAction.me 호출!");
			System.out.println("C: DB사용 O, 페이지 이동 ! > 패턴 2번!");
			
			// MemberLoginAction() 객체 생성
			action = new MemberLoginAction(); // UPCASTING
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}// t-c end
		}// else if(./MemberLoginAction.me) end
		// 메인화면 출력
		else if(command.equals("/Main.me")) {
			System.out.println("C: /Main.me 호출");
			System.out.println("C: DB사용 X, view 페이지 이동 ! > 패턴 1번!");
			
			// 패턴1
			forward = new ActionForward();
			forward.setPath("./member/main.jsp");
			forward.setRedirect(false);
			// request영역객체의 정보 이용을 위하여 false로 이동.
		}// else if(./Main.me) end
		
		// 로그아웃
		else if(command.equals("/MemberLogoutAction.me")) {
			System.out.println("C: /MemberLogoutAction.me 호출");
			System.out.println("C: DB사용 O, view 페이지 이동 ! > 패턴 2번!"); 
			// 실제로 DB나 DAO 사용은 하지 않으나, 처리를 진행하기 때문에 패턴2로 분류한다.
			
			// MemberLogoutAction() 객체 생성
			action = new MemberLogoutAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}// t-c end
		}// else if(./MemberLogoutAction.me) end
		
		// 회원정보조회 > 1 /2 나누지 않고한번에 해야함.
		else if(command.equals("/MemberInfo.me")) {
			System.out.println("C: /MemberInfo.me 호출");
			System.out.println("C: DB사용 O, view 페이지 이동&출력 ! > 패턴 3번!");
			
			// MemberInfoAction() 객체 생성
			action = new MemberInfoAction(); // UPCASTING
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}// t-c end
		}// else if(./MemberInfo.me) end
		
		// 회원 탈퇴 페이지 > 1) 비밀번호 확인 페이지로 이동
		else if(command.equals("/MemberDelete.me")) {
			System.out.println("C: /MemberDelete.me 호출!");
			System.out.println("C: DB사용 X, view 페이지(정보입력 페이지) 이동 ! > 패턴 1번!");
			
			// 패턴 1
			forward = new ActionForward();
			forward.setPath("./member/memberDelete.jsp");
			forward.setRedirect(false);
			
		}// else if(./MemberDelete.me) end
		
		// 회원 탈퇴
		else if(command.equals("/MemberDeleteAction.me")) {
			System.out.println("C: /MemberDeleteAction.me 호출");
			System.out.println("C: DB사용 O, view 페이지 이동 ! > 패턴 2번!"); 
			// 실제로 DB나 DAO 사용은 하지 않으나, 처리를 진행하기 때문에 패턴2로 분류한다.
			
			// MemberLogoutAction() 객체 생성
			action = new MemberDeleteAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}// t-c end
		}// else if(./MemberDelete.me) end
		
		
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
