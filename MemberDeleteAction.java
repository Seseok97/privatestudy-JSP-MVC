package com.seseok.Action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.seseok.Commons.Action;
import com.seseok.Commons.ActionForward;
import com.seseok.Commons.JSForward;
import com.seseok.Member.MemberDAO;
import com.seseok.Member.MemberDTO;

public class MemberDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(" M : MemberDeleteAction.execute() 호출!");
//		// 세션 정보 제어
//		if(request.getParameter("id") == null) {
//			response.sendRedirect("/MemberLogin.me");
//		}// idcheck end
		// id 정보는 세션에 저장된 상태이기 때문에, 세션에서 정보를 받아와야 한다.
		HttpSession session = request.getSession();
		
		ActionForward forward= new ActionForward();
		String id = (String)session.getAttribute("id");
		if(id == null) {
			forward.setPath("/MemberLogin.me");
			forward.setRedirect(false);
			return forward;
		}// idCheck if end
		
		//정보 저장
		// id는 idcheck 진행하면서 저장했음.(session)
		String pw = request.getParameter("pw");
		
		// 회원탈퇴 동작
		MemberDAO dao = new MemberDAO();
//		MemberDTO dto = new MemberDTO(); // 세션과 파라미터를 이용하여 유저를 식별할수 있는 정보를 받아 왔으며,
										 // 별도로 저장할 데이터가 존재하지 않기 때문에 DTO는 필요없다.
		int result = dao.deleteMember(id,pw); // 결과에 따른 페이지 이동을 구현하기 위한 result값.
		
		System.out.println("result = "+result);
		
		if(result == 0) {
			// 아이디 정보 없음
			JSForward.alertAndBack(response, "NO ID info!");
			return null;
		}else if(result == -1) {
			// 비밀번호 오류
			JSForward.alertAndBack(response, "Wrong PW!");
			return null;
		}else {
			// 탈퇴 성공
			JSForward.alertAndMove(response, "Quit Success!","./MemberLogin.me");
			session.invalidate();
			return null;
		}// i-ei-e end
		
		
		
	}// execute() method end

}// public class end






















