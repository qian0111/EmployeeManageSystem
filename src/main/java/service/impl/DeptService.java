package service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.sun.xml.internal.fastinfoset.algorithm.IEEE754FloatingPointEncodingAlgorithm;

import dao.IDeptDao;
import dao.IStaffDao;
import dao.impl.DeptDao;
import dao.impl.StaffDao;
import model.Dept;
import model.Staff;
import service.IDeptService;
import util.MD5Util;
import util.StrUtil;

public class DeptService implements IDeptService{

	private IDeptDao deptDao = new DeptDao();
	private IStaffDao staffDao = new StaffDao();
	
	@Override
	public List<Dept> list(HttpServletRequest req) {		
		if (req != null) {
			Dept t = new Dept();
			String id          = req.getParameter("id");
			String deptName    = req.getParameter("deptName");
			String managerName = req.getParameter("managerName");
			
			if(!StrUtil.isEmpty(id)) t.setId(Integer.valueOf(id));
			if(!StrUtil.isEmpty(deptName)) t.setDeptName(deptName);
			if(!StrUtil.isEmpty(managerName)){
				t.setManagerName(managerName);
				if(deptDao.queryOne(t) != null) {
					t.setManagerId(deptDao.queryOne(t).getManagerId());
				}				
			}
			//分页处理
			//查询总记录数
			int totalCount;
			if(deptDao.queryOne(t) == null) {
				totalCount = 0;
			}else {
				totalCount = deptDao.queryTotalCount(t);
			}	      
			//页码
			String pageNo = req.getParameter("pageNo");
			//页数：每页显示几条记录
			String pageCount = req.getParameter("pageCount");
			
			//当没有传码时，默认页码时第1页
	        if(StrUtil.isEmpty(pageNo)) {
	        	pageNo = "1";
	        	pageCount = "7";
	        }
	        //添加分页信息到请求中
	        req.setAttribute("totalCount", totalCount);
	        req.setAttribute("pageNo", pageNo);
	        req.setAttribute("pageCount", pageCount);
	        req.setAttribute("queryDemo", t);
	        
	        List<Dept> dLeft = deptDao.vagueQuery(t, Integer.valueOf(pageNo), Integer.valueOf(pageCount));
			
	        List<Dept> dInner = deptDao.queryInner();
	        for(Dept itemLeft : dLeft)
	        { 
	        	int flag = 0; 
	        	for(Dept itemInner : dInner) {				 
	        		if(itemLeft.getId().equals(itemInner.getId())) { 
	        			flag = 1;//有交集 break; 
	        		} 
	        	}			 
	        	if(flag == 0) { 
	        		itemLeft.setDeptNumber(0l); 
	        	} 
	       }			 
	        return dLeft;
		}
		Dept t = new Dept();	
		return deptDao.vagueQuery(t, null, null);
	}

	@Override
	public Dept queryOne(Dept t) {
		return deptDao.queryOne(t);
	}

	@Override
	public boolean update(HttpServletRequest req) {
		//获取请求中的参数
		String id = req.getParameter("deptId");
		String deptName = req.getParameter("deptName");
		String managerName = req.getParameter("managerName");
		
		if(StrUtil.isEmpty(deptName)) return false;	
			
		Dept t = new Dept(id, deptName, managerName);
		
		if(!StrUtil.isEmpty(managerName)) {
			//根据主管名称获取主管id
			Staff s = new Staff();			
			s.setStaffName(managerName);
			Staff sOne = staffDao.queryOne(s);
			if(StrUtil.isEmpty(sOne)) {			
				return false;
			}		
			t.setManagerId(sOne.getId());		
		}else {
			t.setManagerId(null);
		}
		//查询该主管是否已是其他部门主管，将其他部门主管的身份撤下
		Dept dTemp = new Dept();
		dTemp.setManagerId(t.getManagerId());
		Dept result = deptDao.queryOne(dTemp);
		if(result != null) {
			dTemp.setDeptName(result.getDeptName());
			dTemp.setId(result.getId());
			dTemp.setManagerId(null);
			int row = deptDao.update(dTemp);
			if(row == 0) {				
				return false;
			}
		}
		
		int row = deptDao.update(t);
		if(row == 0) {
			return false;
		}
		
		if(t.getManagerId() != null) {
			//将员工表中把对应主管的部门更改为当前部门
			Staff sTemp = new Staff();			
			sTemp.setDeptId(t.getId());
			sTemp.setId(t.getManagerId());
			int temp = staffDao.updateOne(sTemp);
			if(temp == 0) {
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean add(HttpServletRequest req) {
		//获取请求中的参数		
		String deptName = req.getParameter("deptName");
		String managerName = req.getParameter("managerName");	

		if(StrUtil.isEmpty(deptName)) {
			return false;
		}
		
		Dept t = new Dept();
		t.setDeptName(deptName);
		//根据主管名称获取主管id
		if(!StrUtil.isEmpty(managerName)) {
			Staff s = new Staff();			
			s.setStaffName(managerName);
			Staff sOne = staffDao.queryOne(s);
			if(StrUtil.isEmpty(sOne)) {	
				return false;
			}		
			t.setManagerId(sOne.getId());	
		}else {
			t.setManagerId(null);
		}
		
		//查询该主管是否已是其他部门主管，将其他部门主管的身份撤下
		Dept dTemp = new Dept();
		dTemp.setManagerId(t.getManagerId());
		Dept result = deptDao.queryOne(dTemp);
		if(result != null) {
			dTemp.setDeptName(result.getDeptName());
			dTemp.setId(result.getId());
			dTemp.setManagerId(null);
			int row = deptDao.update(dTemp);
			if(row == 0) {				
				return false;
			}
		}
	
		int row = deptDao.add(t);
		if(row == 0) {
			return false;
		}
		if(t.getManagerId() != null) {
			//用部门名称查询出新加入的部门的id
			Dept tTemp = new Dept();
			tTemp.setDeptName(deptName);
			if(!StrUtil.isEmpty(deptDao.queryOne(tTemp))) {
				tTemp.setId(deptDao.queryOne(tTemp).getId());
			}
			//将员工表中根据查询出的ManagerId把对应主管的部门更改为当前部门
			Staff sTemp = new Staff();			
			sTemp.setDeptId(tTemp.getId());
			sTemp.setId(t.getManagerId());
			int temp = staffDao.updateOne(sTemp);
			
			if(temp == 0) {
				return false;
			}
		}
		return true;
	}

}
