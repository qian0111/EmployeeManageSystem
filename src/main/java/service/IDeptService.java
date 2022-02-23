package service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.Dept;

public interface IDeptService {

	public List<Dept> list(HttpServletRequest req);
	
	public Dept queryOne(Dept t);
	
	public boolean update(HttpServletRequest req);

	public boolean add(HttpServletRequest req);
	
}
