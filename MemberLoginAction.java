package com.seseok.Action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.seseok.Commons.Action;
import com.seseok.Commons.ActionForward;
import com.seseok.Commons.JSForward;
import com.seseok.Member.MemberDAO;
import com.seseok.Member.MemberDTO;

// 유저의 로그인에 관한 정보처리
public class MemberLoginAction implements Action  {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		System.out.println(" M : MemberLoginAction.execute() 호출!");
		
		// 한글처리
		request.setCharacterEncoding("UTF-8"); // Post로 받아옴.
		// 전달정보 저장
		MemberDTO dto = new MemberDTO();
		dto.setId(request.getParameter("id"));
		dto.setPw(request.getParameter("pw"));
		
		MemberDAO dao = new MemberDAO();
		int result = dao.memberLogin(dto);
		// 페이지 이동
		if(result == -1) {
			// id오류
			JSForward.alertAndBack(response, "id 오류!");
			return null;
		}else if(result == 0) {
			// 비밀번호 오류
			JSForward.alertAndBack(response, "pw 오류!");
			return null;
		}// i-e end
		// >> 로그인 성공은 굳이 제어할 필요 없음.
		
		// 세션에 아이디 정보저장.
		HttpSession session = request.getSession();
		session.setAttribute("id", dto.getId());
		ActionForward forward = new ActionForward();
		forward.setPath("./Main.me");
		forward.setRedirect(true); // >> request객체 전달이 필요없는 경우

		return forward;
	}
	


}// public class end
