package myPlaylist.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import myPlaylist.db.util.DBUtil;

public class MysqlDao {
	
	Connection connection;
	Statement statement;
	ResultSet resultSet;
	
	public MysqlDao(String query) {
		try {
			this.connection = DBUtil.getConnection();
			this.statement =  connection.createStatement();
		}
		catch(SQLException e) {
			System.out.println("db 연결실패");
			e.printStackTrace();
		}
	}
		
	public ResultSet selectQuery(String query) throws SQLException {
		resultSet = statement.executeQuery(query);
		return resultSet;
	}
	
	public int deleteQuery(String query) throws SQLException {
		int result = statement.executeUpdate(query);
		return result;
	}
	
	public void cleanUp() {
		try {
			resultSet.close();
			statement.close();
			connection.close();
		} catch (Exception e) {
			System.out.println("cleanup 실패");
		}
		
	}

		
		
	
}