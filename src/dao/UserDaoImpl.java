package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dto.User;
import util.DBUtil;


public class UserDaoImpl implements UserDao{
	private DBUtil util = DBUtil.getInstance();
	// 싱글톤 패턴 적용
	private UserDaoImpl() {}
	private static UserDaoImpl instance = new UserDaoImpl();
	public static UserDaoImpl getInstance() {
		return instance;
	}
	
	@Override
	public int idCheck(String id) {
		int cnt = 1;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = util.getConnection();
			StringBuilder loginMember = new StringBuilder();
			loginMember.append("SELECT COUNT(USERID) \n");
			loginMember.append("FROM USER \n");
			loginMember.append("WHERE USERID = ?");
			pstmt = conn.prepareStatement(loginMember.toString());
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			rs.next();
			cnt = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
			cnt = 1;
		} finally {
			util.close(rs, pstmt, conn);
		}
		return cnt;
	}

	@Override
	public int insert(User user) {
		Connection conn = null;
		PreparedStatement pstmt = null; 
		int result = 0;
		try {
			conn = util.getConnection();
			String sql = "INSERT INTO USER(USERID, USERPW, USERNAME) VALUES (?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getUserid());
			pstmt.setString(2, user.getUserpw());
			pstmt.setString(3, user.getUsername());
			result = pstmt.executeUpdate();
			System.out.println("쿼리 실행 결과 : " + result);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally { // 사용했던 자원들 반납!
			util.close(pstmt, conn);
		}
		return result;
	}

	@Override
	public int update(User user) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		try {
			conn = util.getConnection();
			String sql = "UPDATE USER SET "
					+ " USERPW=?, USERNAME=? "
					+ "  WHERE USERID=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getUserpw());
			pstmt.setString(2, user.getUsername());
			pstmt.setString(3, user.getUserid());
			result = pstmt.executeUpdate();
			System.out.println("쿼리 실행 결과 : " + result);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally { // 사용했던 자원들 반납!
			util.close(rs, pstmt, conn);
		}
		return result;
	}

	@Override
	public int delete(String id, String pw) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		try {
			conn = util.getConnection();
			String sql = "DELETE FROM USER WHERE USERID=? AND USERPW=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			System.out.println(id);
			System.out.println(pw);
			System.out.println(sql);
			result = pstmt.executeUpdate();
			System.out.println("실행결과 : " + result);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.close(conn, pstmt, rs);
		}
		return result;
	}

	@Override
	public User select(String id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		User result = null;
		try {
			conn = util.getConnection();
			String sql = "SELECT USERID, USERNAME FROM USER WHERE USERID=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				String userid = rs.getString(1);
				String name = rs.getString(2);
				result = new User(userid, "", name);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.close(conn, pstmt, rs);
		}
		return result;
	}
	
	@Override
	public User login(String id, String pw) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		User result = null;
		
		try {
			conn = util.getConnection();
			String sql = "SELECT USERID, USERPW, USERNAME FROM USER WHERE USERID=? AND USERPW=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				String userid = rs.getString(1);
				String userpw = rs.getString(2);
				String name = rs.getString(3);
				result = new User(userid, userpw, name);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.close(conn, pstmt, rs);
		}
		return result;
	}
}
