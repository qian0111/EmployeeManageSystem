package service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.Staff;

/*
 * 员工业务接口
 */
public interface IStaffService {

	public boolean login(HttpServletRequest req);
	
	public boolean register(HttpServletRequest req);
	
	public List<Staff> list(HttpServletRequest req);
	
	public Staff queryOne(Staff s);
	
	public boolean add(HttpServletRequest req);

	public boolean update(HttpServletRequest req);
}
