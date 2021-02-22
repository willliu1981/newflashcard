package com.control.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Conn {

	public static void main(String[] args) {
		Conn conn=new Conn();
		Connection connection=conn.conn();
		String sql="select * from cardbox";
		Statement st=null;
		try {
			st=connection.createStatement();
			ResultSet rs=st.executeQuery(sql);
			while(rs.next()) {
				System.out.format("id=%s , name=%s\n",rs.getInt("id"),rs.getString("name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	public Connection conn() {
		Connection conn = null;
		String url = "jdbc:sqlite:data\\sample.db";
		try {
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection(url);
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}

		return conn;
	}
}
