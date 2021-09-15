package com.control.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.control.connection.Conn;
import com.model.CardBox;
import com.model.Vocabulary;

public class CardBoxDao extends Dao<CardBox> {

	@Override
	public void add(CardBox t) {
		Conn conn = new Conn();
		Connection myConn = conn.conn();
		PreparedStatement st = null;
		String sql = "insert into cardbox (name,create_date,update_date) values (?,?,?)";
		try {
			st = myConn.prepareStatement(sql);
			st.setString(1, t.getName());
			st.setString(2, new Date(new java.util.Date().getTime()).toString());
			st.setString(3, t.getUpdate_date());
			st.executeUpdate();
			st.close();
			myConn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<CardBox> queryAll() {
		Conn conn = new Conn();
		Connection myConn = conn.conn();
		Statement st = null;
		String sql = "select * from cardbox";
		ResultSet rs = null;
		List<CardBox> ms = new ArrayList<>();
		CardBox m = null;
		try {
			st = myConn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				m = new CardBox();
				m.setId(rs.getInt("id"));
				m.setName(rs.getString("name"));
				m.setTest_times(rs.getInt("test_times"));
				m.setTest_date(rs.getString("test_date"));
				m.setState(rs.getInt("state"));
				m.setCreate_date(rs.getString("create_date"));
				m.setUpdate_date(rs.getString("update_date"));
				ms.add(m);
			}
			rs.close();
			st.close();
			myConn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ms;
	}

	@Override
	public CardBox query(int id) {
		Conn conn = new Conn();
		Connection myConn = conn.conn();
		PreparedStatement st = null;
		String sql = "select * from cardbox where id=?";
		ResultSet rs = null;
		CardBox m = null;
		try {
			st = myConn.prepareStatement(sql);
			st.setInt(1, id);
			rs = st.executeQuery();
			if(rs.next()) {
				m = new CardBox();
				m.setId(rs.getInt("id"));
				m.setName(rs.getString("name"));
				m.setTest_times(rs.getInt("test_times"));
				m.setTest_date(rs.getString("test_date"));
				m.setState(rs.getInt("state"));
				m.setCreate_date(rs.getString("create_date"));
				m.setUpdate_date(rs.getString("update_date"));
			}
			rs.close();
			st.close();
			myConn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return m;
	}

	@Override
	public void update(CardBox t, int id) {
		Conn conn = new Conn();
		Connection myConn = conn.conn();
		PreparedStatement st = null;
		String sql = "update cardbox set name=?,update_date=?,test_times=?,test_date=?,state=? where id=?";
		try {
			st = myConn.prepareStatement(sql);
			st.setString(1, t.getName());
			st.setString(2, new Date(new java.util.Date().getTime()).toString());
			st.setInt(3, t.getTest_times());
			st.setString(4, t.getTest_date());
			st.setInt(5, t.getState());
			st.setInt(6, id);
			st.executeUpdate();
			st.close();
			myConn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/*
	 * 測驗完成後更新
	 */
	public void updateTest(CardBox t, int id) {
		Conn conn = new Conn();
		Connection myConn = conn.conn();
		PreparedStatement st = null;
		String sql = "update cardbox set name=?,update_date=?,test_times=?,test_date=?,state=? where id=?";
		try {
			st = myConn.prepareStatement(sql);
			st.setString(1, t.getName());
			st.setString(2, t.getUpdate_date());
			st.setInt(3, t.getTest_times());
			st.setString(4, new Date(new java.util.Date().getTime()).toString());
			st.setInt(5, t.getState());
			st.setInt(6, id);
			st.executeUpdate();
			st.close();
			myConn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(int id) {
		Conn conn = new Conn();
		Connection myConn = conn.conn();
		PreparedStatement st = null;
		String sql = "delete from cardbox where id=?";
		try {
			st = myConn.prepareStatement(sql);
			st.setInt(1, id);
			st.executeUpdate();
			st.close();
			myConn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean isExist(int id) {
		Conn conn = new Conn();
		Connection myConn = conn.conn();
		PreparedStatement st = null;
		ResultSet rs = null;
		boolean isExist = false;
		String sql = "select * from cardbox where id=?";
		try {
			st = myConn.prepareStatement(sql);
			st.setInt(1, id);
			rs = st.executeQuery();
			isExist = rs.next();
			rs.close();
			st.close();
			myConn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isExist;
	}

}
