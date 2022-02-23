package dao;

import java.util.List;

import model.Dept;
import model.Staff;

public interface IDeptDao {
	
	//查询一个对象
	public Dept queryOne(Dept t);
	
	//模糊查询
	public List<Dept> vagueQuery(Dept t, Integer pageNo, Integer pageCount);
	
	//更新
	public int update(Dept t);
	
	//插入
	public int add(Dept t);
	
	//查询总数
	public int queryTotalCount(Dept t);
	
	//查询交集
	public List<Dept> queryInner();

	
}
