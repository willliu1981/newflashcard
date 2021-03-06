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
import com.model.Vocabulary;

public class VocabularyDao extends Dao<Vocabulary> {

	@Override
	public void add(Vocabulary t) {
		Conn conn = new Conn();
		Connection myConn = conn.conn();
		PreparedStatement st = null;
		String sql = "insert into vocabulary (vocabulary,translation,box_id,create_date,update_date,explanation,example) values (?,?,?,?,?,?,?)";
		try {
			st = myConn.prepareStatement(sql);
			st.setString(1, t.getVocabulary());
			st.setString(2, t.getTranslation());
			st.setInt(3, t.getBox_id());
			st.setString(4, new Date(new java.util.Date().getTime()).toString());
			st.setString(5, t.getUpdate_date());
			st.setString(6, t.getExplanation());
			st.setString(7, t.getExample());
			st.executeUpdate();
			st.close();
			myConn.close();
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
				m.setExplanation(rs.getString("explanation"));
				m.setExample(rs.getString("example"));
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

	public List<Vocabulary> queryExceptHadBoxID() {
		Conn conn = new Conn();
		Connection myConn = conn.conn();
		PreparedStatement st = null;
		String sql = "select * from vocabulary where box_id=?";
		ResultSet rs = null;
		List<Vocabulary> ms = new ArrayList<>();
		Vocabulary m = null;
		try {
			st = myConn.prepareStatement(sql);
			st.setInt(1, -1);
			rs = st.executeQuery();
			while (rs.next()) {
				m = new Vocabulary();
				m.setId(rs.getInt("id"));
				m.setVocabulary(rs.getString("vocabulary"));
				m.setTranslation(rs.getString("translation"));
				m.setBox_id(rs.getInt("box_id"));
				m.setCreate_date(rs.getString("create_date"));
				m.setUpdate_date(rs.getString("update_date"));
				m.setExplanation(rs.getString("explanation"));
				m.setExample(rs.getString("example"));
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
			m.setExplanation(rs.getString("explanation"));
			m.setExample(rs.getString("example"));
			rs.close();
			st.close();
			myConn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return m;
	}

	public List<Vocabulary> queryByBoxID(int id) {
		Conn conn = new Conn();
		Connection myConn = conn.conn();
		PreparedStatement st = null;
		String sql = "select * from vocabulary where box_id=?";
		ResultSet rs = null;
		List<Vocabulary> ms = new ArrayList<>();
		Vocabulary m = null;
		try {
			st = myConn.prepareStatement(sql);
			st.setInt(1, id);
			rs = st.executeQuery();
			while (rs.next()) {
				m = new Vocabulary();
				m.setId(rs.getInt("id"));
				m.setVocabulary(rs.getString("vocabulary"));
				m.setTranslation(rs.getString("translation"));
				m.setBox_id(rs.getInt("box_id"));
				m.setCreate_date(rs.getString("create_date"));
				m.setUpdate_date(rs.getString("update_date"));
				m.setExplanation(rs.getString("explanation"));
				m.setExample(rs.getString("example"));
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

	/*
	 * vocabulary to lowerCcase
	 */
	public List<Vocabulary> queryByVocabulary(String vocabuary) {
		Conn conn = new Conn();
		Connection myConn = conn.conn();
		PreparedStatement st = null;
		String sql = "select * from vocabulary where lower(vocabulary) = ?";
		ResultSet rs = null;
		List<Vocabulary> ms = new ArrayList<>();
		Vocabulary m = null;
		try {
			st = myConn.prepareStatement(sql);
			st.setString(1, vocabuary.toLowerCase());
			rs = st.executeQuery();
			while (rs.next()) {
				m = new Vocabulary();
				m.setId(rs.getInt("id"));
				m.setVocabulary(rs.getString("vocabulary"));
				m.setTranslation(rs.getString("translation"));
				m.setBox_id(rs.getInt("box_id"));
				m.setCreate_date(rs.getString("create_date"));
				m.setUpdate_date(rs.getString("update_date"));
				m.setExplanation(rs.getString("explanation"));
				m.setExample(rs.getString("example"));
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

	/*
	 * vocabulary to lowerCcase, fuzzy search
	 */
	public List<String> queryByFuzzyVocabulary(String vocabuary) {
		return queryByFuzzyVocabulary(vocabuary, -1);
	}

	/*
	 * vocabulary to lowerCcase, fuzzy search , limit chause
	 */
	public List<String> queryByFuzzyVocabulary(String vocabuary, int limit) {
		Conn conn = new Conn();
		Connection myConn = conn.conn();
		PreparedStatement st = null;
		String sql = String.format("select distinct vocabulary from vocabulary where lower(vocabulary) like ? %s",
				limit == -1 ? "" : " limit ?");
		ResultSet rs = null;
		List<String> ms = new ArrayList<>();
		try {
			st = myConn.prepareStatement(sql);
			st.setString(1, String.format("%%%s%%", vocabuary.toLowerCase()));
			if (limit != -1) {
				st.setInt(2, limit);
			}
			rs = st.executeQuery();
			while (rs.next()) {
				ms.add(rs.getString("vocabulary"));
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
	public void update(Vocabulary t, int id) {
		Conn conn = new Conn();
		Connection myConn = conn.conn();
		PreparedStatement st = null;
		String sql = "update vocabulary set translation=?,box_id=?,update_date=?,explanation=?,example=? where id=?";
		try {
			st = myConn.prepareStatement(sql);
			st.setString(1, t.getTranslation());
			st.setInt(2, t.getBox_id());
			st.setString(3, new Date(new java.util.Date().getTime()).toString());
			st.setString(4, t.getExplanation());
			st.setString(5, t.getExample());
			st.setInt(6, id);
			st.executeUpdate();
			st.close();
			myConn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateClearBoxID(int ori_boxid) {
		Conn conn = new Conn();
		Connection myConn = conn.conn();
		PreparedStatement st = null;
		String sql = "update vocabulary set box_id=? where box_id=?";
		try {
			st = myConn.prepareStatement(sql);
			st.setInt(1, -1);
			st.setInt(2, ori_boxid);
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
		String sql = "delete from vocabulary where id=?";
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

}
