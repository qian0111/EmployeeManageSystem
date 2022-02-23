package dao.impl;

import java.util.List;

import dao.IDeptDao;
import model.Dept;
import model.Staff;
import util.DBHelper;
import util.StrUtil;

public class DeptDao implements IDeptDao {

	@Override
	public Dept queryOne(Dept t) {
		List<Dept> list = vagueQuery(t, null, null);
		if (list == null || list.size() == 0) {
			return null;
		}
		return list.get(0);
	}
	
	@Override
	public List<Dept> vagueQuery(Dept t, Integer pageNo, Integer pageCount) {
		StringBuffer sql = new StringBuffer("select t.*,s.staff_name as manager_name,count(*) as dept_number \r\n"
				+ "FROM dept as t\r\n"
				+ "LEFT JOIN staff as s ON t.manager_id = s.id\r\n"
				+ "LEFT JOIN staff as s2 ON t.id = s2.dept_id\r\n"
				+ "where 1=1");
		
		if (t.getId() != null) {
			sql.append(" and cast(t.id as char) like '%" + t.getId() + "%'");
		}
		if (!StrUtil.isEmpty(t.getDeptName())) {
			sql.append(" and t.dept_name like '%" + t.getDeptName() + "%'");
		}
		if (!StrUtil.isEmpty(t.getManagerName())) {
			sql.append(" and s.staff_name like '%" + t.getManagerName() + "%'");
		}
		sql.append(" GROUP BY t.id ");			
			
		//分页处理
		if (pageNo != null && pageCount != null) {
			sql.append(" LIMIT " + (pageNo-1)*pageCount + "," + pageCount);
		}
		return DBHelper.queryAll(sql.toString(), Dept.class, null);
	}
	
	@Override
	public int queryTotalCount(Dept t) {
		StringBuffer sql = new StringBuffer("select t.*, s.staff_name\r\n"
				+ "FROM dept as t\r\n"
				+ "LEFT JOIN staff as s2 ON t.id = s2.dept_id\r\n"
				+ "LEFT JOIN staff as s ON t.manager_id = s.id\r\n"
				+ "where 1=1");
		if (t.getId() != null) {
			sql.append(" and cast(t.id as char) like '%" + t.getId() + "%'");
		}
		if(!StrUtil.isEmpty(t.getDeptName())) {
			sql.append(" and t.dept_name like '%" + t.getDeptName() + "%'");
		}
		if (!StrUtil.isEmpty(t.getManagerName())) {
			sql.append(" and s.staff_name like '%" + t.getManagerName() + "%'");
		}
		sql.append(" GROUP BY t.id ");
		int totalCount = DBHelper.count(sql.toString());
		return totalCount;
	}

	@Override
	public int update(Dept t) {
		String sql = new String("update dept set dept_name = ?, manager_id = ? where id = ?");
		return DBHelper.deal(sql, t.getDeptName(), t.getManagerId(), t.getId());
	}

	@Override
	public int add(Dept t) {
		String sql = new String("insert into dept(dept_name, manager_id)values(?,?)");
		return DBHelper.deal(sql, t.getDeptName(), t.getManagerId());
	}

	@Override
	public List<Dept> queryInner() {
		StringBuffer sql = new StringBuffer("select t.id FROM dept as t\r\n"
				+ "INNER JOIN staff as s2 ON t.id = s2.dept_id\r\n"
				+ "LEFT JOIN staff as s ON t.manager_id = s.id\r\n"
				+ "GROUP BY t.id;");
		return DBHelper.queryAll(sql.toString(), Dept.class, null);
	}
	
}
