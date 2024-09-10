package kr.co.wizbrain.tbn.cid;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class OracleConnector {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private String URL;
	private String ID;
	private String PWD;
	
	public OracleConnector() throws IOException {
		init();
		conn = getConnection();
	}
	
	public void init() throws IOException {
		Properties prop = ConfigLoader.loadConfigFileFrom();
		URL = prop.getProperty("URL");
		ID = prop.getProperty("ID");
		PWD = prop.getProperty("PWD");
	}
	
	private Connection getConnection() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(URL, ID, PWD);
			System.out.println("----------Connected to OracleDB----------");
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public Connection getConn() {
		return conn;
	}
	
	public String classifyAreaCodeByNum(String telNum) throws SQLException {
		String AREA_CODE = "";
		StringBuffer query = new StringBuffer();
		query.append("SELECT AREA_CODE FROM CODE_AREACODE WHERE RP_TEL = ?");
		System.out.println("Query: " + query.toString());
		
		pstmt = conn.prepareStatement(query.toString());
		pstmt.setString(1, telNum);
		
		ResultSet rs = pstmt.executeQuery();
		if(rs.next()) {
			AREA_CODE = rs.getString(1);
		} else {
			AREA_CODE = "020";
		}
		
		rs.close();
		pstmt.close();
		return AREA_CODE;
	}
	
	//여기
	public int insertMissedCall(MissedCallVO vo) throws SQLException {
		int result = 0;
		conn.setAutoCommit(false);
		
		StringBuffer query = new StringBuffer();
		query.append("INSERT INTO MISSED_CALL (MISSED_CALL_ID, MISSED_CALL_TIME, MISSED_CALL_TEL, INFORMER_NAME, STT_CONTENT, FLAG_READ, INFORMER_ID, AREA_CODE, MISSED_CALL_DAY) "
				+ "VALUES((TO_CHAR(SYSDATE , 'YYYYMM') || TO_CHAR(SEQ_MISSED_CALL.NEXTVAL, 'FM0000009'))"
				+ ", TO_CHAR(SYSDATE, 'YYYY-MM-DD HH24:MI:SS')"
				+ ", ?, ?, NULL, NULL, ?, ?"
				+ ", TO_CHAR(SYSDATE, 'YYYYMMDD'))");
		System.out.println("Query: " + query.toString());
		
		pstmt = conn.prepareStatement(query.toString());
		pstmt.setString(1, vo.getMISSED_CALL_TEL());
		pstmt.setString(2, vo.getINFORMER_NAME());
		pstmt.setString(3, vo.getINFORMER_ID());
		pstmt.setString(4, vo.getAREA_CODE());
		
		result = pstmt.executeUpdate();
		if(result == 1) {
			System.out.println("data commited");
			conn.commit();
		}else {
			System.out.println("data rollback");
			conn.rollback();
		}
		
		return result;
	}
	
	public MissedCallVO selectInformerBy(String informerTel) throws SQLException {
		StringBuffer query = new StringBuffer();
		query.append("SELECT INFORMER_ID, INFORMER_NAME, ACT_ID, AREA_CODE "
				+ "FROM INFORMER "
				+ "WHERE PHONE_CELL = ? "
					+ "OR PHONE_HOME = ? "
					+ "OR PHONE_OFFICE = ?");
		System.out.println("Query: " + query.toString());
		
		pstmt = conn.prepareStatement(query.toString());
		pstmt.setString(1, informerTel);
		pstmt.setString(2, informerTel);
		pstmt.setString(3, informerTel);
		
		MissedCallVO vo = new MissedCallVO();
		vo.setMISSED_CALL_TEL(informerTel);
		
		ResultSet rs = pstmt.executeQuery();
		if(rs.next()) {
			do {
				vo.setINFORMER_ID(rs.getString(1));
				vo.setINFORMER_NAME(rs.getString(2));
			} while(rs.next());
		} else {
			vo.setINFORMER_NAME("시민");
		}
		System.out.println("InfromerVO: " + vo);
		return vo;
	}
	
//	public int deleteMissedCall(String MISSED_CALL_ID) throws SQLException {
//		int result = 0;
//		StringBuffer query = new StringBuffer();
//		query.append("DELETE FROM MISSED_CALL WHERE MISSED_CALL_ID = ?");
//		System.out.println("Query: " + query.toString());
//		
//		pstmt = conn.prepareStatement(query.toString());
//		pstmt.setString(1, MISSED_CALL_ID);
//		
//		result = pstmt.executeUpdate();
//		return result;
//	}
	
}























