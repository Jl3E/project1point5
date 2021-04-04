package com.project1point5.dao;

import java.security.NoSuchAlgorithmException;
import java.util.List;

public interface GenericDao <T> {
	List<T> getList();
	T getById(int id);
	List<T> getByUserId(int id);
	T getByUsername(String username);
	void insert(T t) throws NoSuchAlgorithmException;
	void delete(T t);
}
