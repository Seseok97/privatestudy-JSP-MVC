package com.seseok.Action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.seseok.Commons.Action;
import com.seseok.Commons.ActionForward;
import com.seseok.Member.MemberDAO;
import com.seseok.Member.MemberDTO;

// 회원정보 조회 동작 수행
public class MemberInfoAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		System.out.println(" M : MemberInfoAction.execute()");
		
		HttpSession session = request.getSession();
		
		String id = (String)session.getAttribute("id");
		ActionForward forward = new ActionForward();
		if(id == null) {
			forward.setPath("./MemberLogin.me");
			forward.setRedirect(true); // me로 넘어가니까 true + request X
			return forward;
		}// idcheck if end
		
		MemberDAO dao = new MemberDAO();
		MemberDTO dto = dao.getMember(id);
		request.setAttribute("dto", dto); // .setAttribute("dto", dao.getMember(id));
		
		forward.setPath("./member/memberInfo.jsp");
		forward.setRedirect(false);
		
		return forward;
	}//execute() method end
	
	
	
}// public class end
