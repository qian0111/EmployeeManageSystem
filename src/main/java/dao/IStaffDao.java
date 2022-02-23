package dao;

import java.util.List;

import model.Staff;

public interface IStaffDao {
	//查询一个对象
	public Staff queryOne(Staff s);
	
	//模糊查询
	public List<Staff> vagueQuery(Staff s, Integer pageNo, Integer pageCount);
	
	//查询多个对象
	public List<Staff> queryAll(Staff s, Integer pageNo, Integer pageCount);
	
	//更新
	public int update(Staff s);
	
	//插入
	public int add(Staff s);

	//查询总数
	public int queryTotalCount(Staff s);
	
	//更新主管所属部门
	public int updateOne(Staff s);
	
}
