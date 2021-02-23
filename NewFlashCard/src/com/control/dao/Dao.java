package com.control.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import com.control.connection.Conn;

public abstract class Dao<T> {
	public abstract void add(T t);
	public abstract List<T> queryAll();
	public abstract T query(int id);
	public abstract void update(T t,int id);
	public abstract void delete(int id);

}
