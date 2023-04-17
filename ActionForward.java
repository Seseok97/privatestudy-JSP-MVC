package com.seseok.Action;

public class ActionForward {
	// 티켓을 생성하는 클래스.
	private String path;		// 이동할 페이지 주소
	private boolean isRedirect; // 이동할 방식
	// true > sendRedirect
	// false > forward
	
	public ActionForward() {
		System.out.println("---------------------------------------");
		System.out.println("페이지 이동을 위한 티켓 생성");
		System.out.println("목적지, 이동방식 정보 저장 !!");
		System.out.println("---------------------------------------");
	}// ActionForward() method end

	// getter / setter 
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public boolean isRedirect() {
		return isRedirect;
	}
	public void setRedirect(boolean isRedirect) {
		this.isRedirect = isRedirect;
	}
	// getter / setter end
	
	
}// public class end
