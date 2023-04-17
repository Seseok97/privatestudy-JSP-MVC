package com.seseok.Action;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.seseok.Member.MemberDAO;
import com.seseok.Member.MemberDTO;

public class MemberJoinAction implements Action {
	
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
		System.out.println(" M : (Model)MemberJoinAction.execute() 실행!");
		request.setCharacterEncoding("UTF-8");
		
		MemberDTO dto = new MemberDTO();
		
		// 전달받은 정보 저장 (jsp페이지가 아니기때문에, 액션태그 사용이 불가능하다.)
		dto.setId(request.getParameter("id"));
		dto.setPw(request.getParameter("pw"));
		dto.setName(request.getParameter("name"));
		dto.setGender(request.getParameter("gender"));
		dto.setAge(Integer.parseInt(request.getParameter("age")));
		dto.setEmail(request.getParameter("email"));
		dto.setRegdate(new Date(System.currentTimeMillis()));
		
		System.out.println(" M :"+ dto);
		
		// MemberDAO객체 생성 (DB 사용)
		MemberDAO dao = new MemberDAO();
		dao.memberJoin(dto);
		
		// 페이지 이동
		ActionForward forward = new ActionForward();
		forward.setPath("./MemberLogin.me");
		forward.setRedirect(true);
		// .me로 이동하기때문에 true
		System.out.println(" M : 데이터처리 완료! 티켓가지고 이동.");
		return forward; // 이게 티켓임
	}// execute() method end
}// public class end
