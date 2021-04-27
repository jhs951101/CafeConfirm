package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Model;
import model.CafeInfo;

public class DBOperation {
	private DBConnection db;

	public DBOperation() {
		db = DBConnection.getInstance();
	}

	public boolean insert(CafeInfo m) {
		Connection conn = null;
		String sql = null;
		boolean success = true;
		
		sql = "insert into cafeinfo values(?, ?, ?, ?, ?, '-1')";

		PreparedStatement pstmt = null;
		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, m.getCafeName());
			pstmt.setString(2, m.getPassword());
			pstmt.setString(3, m.getMaxNum());
			pstmt.setString(4, m.getLatitude());
			pstmt.setString(5, m.getLongitude());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			success = false;
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return success;
	}

	public CafeInfo selectSingle(String id) {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		Model m = null;
		String sql = null;
		
		sql = "select * from cafeinfo where cafename=?";
		
		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if (rs.next())
				return new CafeInfo(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		
		return null;
	}
	
	public CafeInfo[] selectMulti() {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		Model m = null;
		String sql = null;
		
		sql = "select * from cafeinfo";
		
		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			ArrayList<CafeInfo> list = new ArrayList<>();
			
			while(rs.next()) {
				CafeInfo cafeInfo = new CafeInfo(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
				cafeInfo.setNumOfPeople(rs.getString(6));
				list.add(cafeInfo);
			}
				
			CafeInfo[] results = new CafeInfo[list.size()];
			
			for(int i=0; i<list.size(); i++)
				results[i] = list.get(i);
			
			return results;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		
		return null;
	}

	public boolean update(CafeInfo m) {
		Connection conn = null;
		String sql = null;
		boolean success = true;
		
		sql = "update cafeinfo set numofpeople=? where cafename=?";

		PreparedStatement pstmt = null;
		
		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, m.getNumOfPeople());
			pstmt.setString(2, m.getCafeName());
			
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			success = false;
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		
		return success;
	}

	public boolean delete(String id) {
		Connection conn = null;
		String sql = null;
		boolean success = true;
		
		sql = "delete cafeinfo where cafename=?";
		
		PreparedStatement pstmt = null;
		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			success = false;
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		
		return success;
	}
}
