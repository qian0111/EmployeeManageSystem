package service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.sun.corba.se.impl.encoding.BufferQueue;

import dao.IStaffDao;
import dao.impl.StaffDao;
import model.Staff;
import service.IStaffService;
import sun.misc.Perf.GetPerfAction;
import util.MD5Util;
import util.StrUtil;

public class StaffService implements IStaffService{

	private IStaffDao staffDao = new StaffDao();
	
	@Override
	public boolean login(HttpServletRequest req) {
		//获取请求中的参数
		String staffName = req.getParameter("staffName");
		String pass = MD5Util.encode(req.getParameter("pass"));
		String phone = req.getParameter("phone");		
		
		//组装查询实体对象
		Staff s = new Staff();
		if(staffName != null) s.setStaffName(staffName);
		if(phone != null) s.setPhone(phone); 
		s.setPass(pass);
		
		//执行查询
		Staff staff = staffDao.queryOne(s);
		if(staff == null) {
			//登陆失败
			return false;
		}
		if(staff.getStaffType() == 0) {
			//普通员工不允许登录
			return false;
		}
		
		//向session添加用户信息
		HttpSession session = req.getSession();
		session.setAttribute("staff", staff);
		session.setMaxInactiveInterval(1500);//30-60min
		
		return true;
	}

	@Override
	public boolean register(HttpServletRequest req) {
		//获取请求中的参数
		String staffName = req.getParameter("staffName");
		String pass = MD5Util.encode(req.getParameter("pass"));
		String phone = req.getParameter("phone");
		
		//封装到staff对象中
		Staff staff = new Staff(staffName, pass, phone);
		
		//调用dao层,执行insert插入语句
		int res = staffDao.add(staff);
		if(res == 1) {
			//向session添加用户信息
			HttpSession session = req.getSession();
			session.setAttribute("staff", staff);
			session.setMaxInactiveInterval(1500);//30-60min
			
			return true;
		}
		return false;
		
	}

	public List<Staff> list(HttpServletRequest req) {
		String id = req.getParameter("id");
		String staffName = req.getParameter("staffName");
		String phone = req.getParameter("phone");
		String deptId = req.getParameter("deptId");

		Staff s = new Staff(id, staffName, phone, deptId);
		
		//分页处理
		//页码
		String pageNo = req.getParameter("pageNo");
		//页数：每页显示几条记录
		String pageCount = req.getParameter("pageCount");
		//查询总记录数
        int totalCount = staffDao.queryTotalCount(s);
		
        if(StrUtil.isEmpty(pageNo)) {//当没有传页码时，默认页码时第1页
        	pageNo = "1";
        	pageCount = "7";
        }
        //添加分页信息到请求中
        req.setAttribute("totalCount", totalCount);
        req.setAttribute("pageNo", pageNo);
        req.setAttribute("pageCount", pageCount);
        req.setAttribute("queryDemo", s);
        
		return staffDao.vagueQuery(s, Integer.valueOf(pageNo), Integer.valueOf(pageCount));
	}

	@Override
	public Staff queryOne(Staff s) {
		return staffDao.queryOne(s);
	}

	@Override
	public boolean update(HttpServletRequest req) {
		//获取请求中的参数
		String id = req.getParameter("staffId");
		String staffName = req.getParameter("staffName");
		String phone = req.getParameter("phone");
		String deptId = req.getParameter("deptId");		

		if(StrUtil.isEmpty(staffName)) return false;
		if(StrUtil.isEmpty(phone)) return false; 		
		if(deptId.equals("0")) {
			deptId = null;
		}		
		
		Staff s = new Staff(id, staffName, phone, deptId);
				
		int row = staffDao.update(s);
		if(row == 0) {
			return false;
		}
		return true;
	}

	@Override
	public boolean add(HttpServletRequest req) {
		//获取请求中的参数		
		String staffName = req.getParameter("staffName");
		String pass = MD5Util.encode("123");
		String phone = req.getParameter("phone");
		String deptId = req.getParameter("deptId");				
		
		if(StrUtil.isEmpty(staffName)) return false;
		if(StrUtil.isEmpty(phone)) return false; 		
		if(deptId.equals("0")) {
			deptId = null;
		}

		Staff s = new Staff();
		s.setStaffName(staffName);
		s.setPhone(phone);
		s.setPass(pass);
		if(!StrUtil.isEmpty(deptId)) {
			s.setDeptId(Integer.valueOf(deptId));
		}
		int row = staffDao.add(s);
		if(row == 0) {
			return false;
		}
		return true;
	}
}
