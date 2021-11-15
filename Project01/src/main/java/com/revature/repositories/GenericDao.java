package com.revature.repositories;

import java.util.List;

public interface GenericDao<C> {
	List<C> getAll();
	C getById(int id);
	int add(C c);
	boolean edit(C c);
	boolean deleteById(int id);
}
