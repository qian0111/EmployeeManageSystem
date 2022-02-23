package dao.impl;

import java.util.List;

import com.sun.org.apache.xpath.internal.operations.And;

import dao.IStaffDao;
import model.Staff;
import util.DBHelper;
import util.StrUtil;

public class StaffDao implements IStaffDao {

	@Override
	public Staff queryOne(Staff s) {
		List<Staff> list = queryAll(s, null, null);
		if (list == null || list.size() == 0) {
			return null;
		}
		return list.get(0);
	}

	@Override
	public List<Staff> queryAll(Staff s, Integer pageNo, Integer pageCount) {
		StringBuffer sql = new StringBuffer("select s.*,d.dept_name FROM staff as s\r\n"
				+ "LEFT JOIN dept as d ON s.dept_id = d.id where 1=1");
		
		if (s.getId() != null) {
			sql.append(" and s.id = " + s.getId());
		}
		if(!StrUtil.isEmpty(s.getStaffName())) {
			sql.append(" and s.staff_name = '" + s.getStaffName() + "'");
		}
		if(!StrUtil.isEmpty(s.getPass())) {
			sql.append(" and s.pass = '" + s.getPass() + "'");
		}
		if(!StrUtil.isEmpty(s.getPhone())) {
			sql.append(" and s.phone = '" + s.getPhone() + "'");
		}
		if(s.getDeptId() != null) {
			sql.append(" and s.dept_id = " + s.getDeptId());
		}
		
		
		//分页处理
		if (pageNo != null && pageCount != null) {
			sql.append(" LIMIT " + (pageNo-1)*pageCount + "," + pageCount);
		}
		List<Staff> list = DBHelper.queryAll(sql.toString(), Staff.class, null);
		return list;
	}
	
	@Override
	public List<Staff> vagueQuery(Staff s, Integer pageNo, Integer pageCount) {
		StringBuffer sql = new StringBuffer("select s.*,d.dept_name FROM staff as s\r\n"
				+ "LEFT JOIN dept as d ON s.dept_id = d.id where 1=1");
		
		if (s.getId() != null) {
			sql.append(" and cast(s.id as char) like '%" + s.getId() + "%'");
		}
		if(!StrUtil.isEmpty(s.getStaffName())) {
			sql.append(" and s.staff_name like '%" + s.getStaffName() + "%'");
		}
		if(!StrUtil.isEmpty(s.getPhone())) {
			sql.append(" and s.phone like '%" + s.getPhone() + "%'");
		}
		if(s.getDeptId() != null) {
			sql.append(" and s.dept_id = " + s.getDeptId());
		}
				
		//分页处理
		if (pageNo != null && pageCount != null) {
			sql.append(" LIMIT " + (pageNo-1)*pageCount + "," + pageCount);
		}
		List<Staff> list = DBHelper.queryAll(sql.toString(), Staff.class, null);
		return list;
	}
	
	@Override
	public int queryTotalCount(Staff s) {
		StringBuffer sql = new StringBuffer("select count(*) FROM staff where 1 = 1 ");
		if (s.getId() != null) {
			sql.append(" and cast(id as char) like '%" + s.getId() + "%'");
		}
		if(!StrUtil.isEmpty(s.getStaffName())) {
			sql.append(" and staff_name like '%" + s.getStaffName() + "%'");
		}
		if(!StrUtil.isEmpty(s.getPhone())) {
			sql.append(" and phone like '%" + s.getPhone() + "%'");
		}
		if(s.getDeptId() != null) {
			sql.append(" and dept_id = " + s.getDeptId());
		}
		int totalCount = DBHelper.count(sql.toString());
		return totalCount;
	}

	@Override
	public int update(Staff s) {
		// TODO Auto-generated method stub
		String sql = new String("update staff set staff_name = ?, phone = ?, dept_id = ? where id = ?");
		return DBHelper.deal(sql, s.getStaffName(), s.getPhone(), s.getDeptId(), s.getId());
	}

	@Override
	public int add(Staff s) {
		String sql = new String("insert into staff (staff_name, pass, phone, dept_id, staff_type)values(?,?,?,?,?)");
		return DBHelper.deal(sql, s.getStaffName(), s.getPass(), s.getPhone(), s.getDeptId(), 0);
	}

	@Override
	public int updateOne(Staff s) {
		String sql = new String("update staff set dept_id = ? where id = ?");
		return DBHelper.deal(sql, s.getDeptId(), s.getId());
	}
	
}
