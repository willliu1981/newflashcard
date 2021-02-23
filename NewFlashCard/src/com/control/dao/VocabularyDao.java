package com.control.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.control.connection.Conn;
import com.control.model.Vocabulary;

public class VocabularyDao extends Dao<Vocabulary> {

	@Override
	public void add(Vocabulary t) {
		Conn conn = new Conn();
		Connection myConn = conn.conn();
		PreparedStatement st = null;
		String sql = "insert into vocabulary (vocabulary,translation,box_id,create_date,update_date) values (?,?,?,?,?)";
		try {
			st = myConn.prepareStatement(sql);
			st.setString(1, t.getVocabulary());
			st.setString(2, t.getTranslation());
			st.setInt(3, t.getBox_id());
			st.setString(4, t.getCreate_date());
			st.setString(5, t.getUpdate_date());
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Vocabulary> queryAll() {
		Conn conn = new Conn();
		Connection myConn = conn.conn();
		Statement st = null;
		String sql = "select * from vocabulary";
		ResultSet rs = null;
		List<Vocabulary> ms = new ArrayList<>();
		Vocabulary m = null;
		try {
			st = myConn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				m = new Vocabulary();
				m.setId(rs.getInt("id"));
				m.setVocabulary(rs.getString("vocabulary"));
				m.setTranslation(rs.getString("translation"));
				m.setBox_id(rs.getInt("box_id"));
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
	public Vocabulary query(int id) {
		Conn conn = new Conn();
		Connection myConn = conn.conn();
		PreparedStatement st = null;
		String sql = "select * from vocabulary where id=?";
		ResultSet rs = null;
		Vocabulary m = null;
		try {
			st = myConn.prepareStatement(sql);
			st.setInt(1, id);
			rs = st.executeQuery();
			m = new Vocabulary();
			m.setId(rs.getInt("id"));
			m.setVocabulary(rs.getString("vocabulary"));
			m.setTranslation(rs.getString("translation"));
			m.setBox_id(rs.getInt("box_id"));
			m.setCreate_date(rs.getString("create_date"));
			m.setUpdate_date(rs.getString("update_date"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return m;
	}

	@Override
	public void update(Vocabulary t, int id) {
		Conn conn = new Conn();
		Connection myConn = conn.conn();
		PreparedStatement st = null;
		String sql = "update vocabulary set translation=?,box_id=?,update_date=? where id=?";
		try {
			st = myConn.prepareStatement(sql);
			st.setString(1, t.getTranslation());
			st.setInt(2, t.getBox_id());
			st.setString(3, t.getUpdate_date());
			st.setInt(4, id);
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
		String sql = "delete from vocabulary where id=?";
		try {
			st=myConn.prepareStatement(sql);
			st.setInt(1, id);
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
