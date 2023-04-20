package com.seseok.Member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

// DB와 연결되는 모든동작 수행
// > DB가 필요한 모든 동작을 수행하기 위해서는 MemberDAO 객체를 생성해야한다!
public class MemberDAO {
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private String sql = "";
	
	// DB연결 메서드
	private Connection getCon() throws Exception{
		// Context객체 > 프로젝트(Context)에 대한 정보 저장
		Context initCTX = new InitialContext(); // 초기화된 프로젝트정보를 initCTX안에 저장!
		// context.xml파일 안의 DB연결정보를 불러와서 사용한다.
		DataSource ds
			= (DataSource)initCTX.lookup("java:comp/env/jdbc/el-mvc");
		con = ds.getConnection(); // DB 연결정보를 Connection타입 변수 con에 저장!
		
		System.out.println("DAO: DB연결 성공!!  con: "+ con);
		return con;
	}// getCon() method end
	
	// DB 자원 해제
	public void closeDB() {
			try {
				// 사용의 역순대로 close()를 실행해줘야 한다!
				if(rs != null) rs.close();
				if(pstmt != null ) pstmt.close();
				if(con != null) con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}// t-c end
	}// closeDB() method end
	
	// 회원가입동작 - memberJoin()
	public void memberJoin(MemberDTO dto) {
		try {
			con = getCon();
			sql = "insert into itwill_member (id,pw,name,gender,age,email,regdate)"
					+ " values (?,?,?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			// 3. SQL 쿼리작성 & pstmt객체
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getPw());
			pstmt.setString(3, dto.getName());
			pstmt.setString(4, dto.getGender());
			pstmt.setInt(5, dto.getAge());
			pstmt.setString(6, dto.getEmail());
			pstmt.setDate(7, dto.getRegdate());
			// 4. SQL쿼리 실행
			pstmt.executeUpdate();
			System.out.println("DAO: memberJoin() 메서드 정상작동");
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeDB();
		} // t-c-f end
	}// memberJoin() method end
	
	// 로그인 - memberLogin()
	public int memberLogin(MemberDTO dto) {
		int result = -1;
		try {
			con = getCon();
			sql = "select pw from itwill_member where id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getId());
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				if(dto.getPw().equals(rs.getString("pw"))) {
					// 정상 로그인
					result = 1;
				}else {
					// pw 불일치
					result = 0;
				}// i-e end
			}else {
				// id 불일치
				result = -1;
			}//i-e end
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeDB();
		}// tcf end
		return result;
	}// memberLogin() method end
	
	// 회원정보 조회 - getMember()
	public MemberDTO getMember(String id) {
		MemberDTO dto = null;
		
		// 동작
		// DB 불러오고, dto안에 값넣기.
		// memberInfo.jsp파일 만들어서 dto내용 출력하기.
		
		try {
			con = getCon();
			sql="select * from itwill_member where id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			dto = new MemberDTO();
			if(rs.next()) {
				dto.setId(rs.getString("id"));
				dto.setPw(rs.getString("pw"));
				dto.setName(rs.getString("name"));
				dto.setGender(rs.getString("gender"));
				dto.setAge(rs.getInt("age"));
				dto.setEmail(rs.getString("email"));
				dto.setRegdate(rs.getDate("regdate"));
				
				System.out.println("DAO: DTO안에 정보 저장 완료!");
			}// rsnext if end
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeDB();
		}// tcf end

		return dto;
	}// getMember() method end
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}// public class end
