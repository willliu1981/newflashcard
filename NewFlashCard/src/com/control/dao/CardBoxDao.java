package com.control.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.control.connection.Conn;
import com.control.model.CardBox;
import com.control.model.Vocabulary;

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
			st.setString(2, t.getCreate_date());
			st.setString(3, t.getUpdate_date());
			st.executeUpdate();
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
				m.setCreate_date(rs.getString("create_date"));
				m.setUpdate_date(rs.getString("update_date"));
				ms.add(m);
			}
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
			m = new CardBox();
			m.setId(rs.getInt("id"));
			m.setName(rs.getString("name"));
			m.setCreate_date(rs.getString("create_date"));
			m.setUpdate_date(rs.getString("update_date"));
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
		String sql = "update cardbox set name=?,update_date=? where id=?";
		try {
			st = myConn.prepareStatement(sql);
			st.setString(1, t.getName());
			st.setString(2, t.getUpdate_date());
			st.setInt(3, id);
			st.executeUpdate();
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
			st=myConn.prepareStatement(sql);
			st.setInt(1, id);
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


}
