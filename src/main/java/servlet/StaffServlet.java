package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.org.apache.xml.internal.utils.StringToStringTableVector;

import model.Dept;
import model.Staff;
import service.IDeptService;
import service.IStaffService;
import service.impl.DeptService;
import service.impl.StaffService;
/*
 * 员工控制类
 */
public class StaffServlet extends BaseServlet{
	
	private IStaffService staffService = new StaffService();
	private IDeptService deptService = new DeptService();
	
	//跳转到主页
	public void mainPage(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/page/main.jsp").forward(req, res);
	}
	
	//跳转到员工列表页
	public void listPage(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//查询部门列表
		List<Dept> deptList = deptService.list(null);
		//查询员工列表
		List<Staff> list = staffService.list(req);
		req.setAttribute("deptList", deptList);
		req.setAttribute("list", list);
		
		req.getRequestDispatcher("/WEB-INF/page/staff/staffList.jsp").forward(req, res);
	}
	
	//跳转到员工更新页
	public void upPage(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String id = req.getParameter("id");//获取员工id
		//根据员工id查询员工信息
		Staff s = new Staff();
		s.setId(Integer.valueOf(id));
		//查询到员工对象
		Staff staff = staffService.queryOne(s);
		//添加查询到的员工对象到请求中去
		req.setAttribute("staffInfo", staff);
		
		//查询部门列表
		List<Dept> deptList = deptService.list(null);
		req.setAttribute("deptList", deptList);
		//请求转发
		req.getRequestDispatcher("/WEB-INF/page/staff/staffUp.jsp").forward(req, res);
	}
	
	//更新员工信息
	public void upStaffInfo(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		boolean y = staffService.update(req);		
		if(y) {
			responseObject("1", res);
		} else {
			responseObject("0", res);
		}
	}
	
	//跳转到添加员工页
	public void addStaffPage(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//查询部门列表
		List<Dept> deptList = deptService.list(null);
		req.setAttribute("deptList", deptList);
		//请求转发
		req.getRequestDispatcher("/WEB-INF/page/staff/staffAdd.jsp").forward(req, res);
	}
	
	//添加员工信息
	public void addStaffInfo(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		boolean y = staffService.add(req);		
		if(y) {
			responseObject("1", res);
		} else {
			responseObject("0", res);
		}
	}
	
	//跳转到部门列表页
	public void deptListPage(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//查询部门列表
		List<Dept> deptList = deptService.list(req);
		req.setAttribute("deptList", deptList);
		req.getRequestDispatcher("/WEB-INF/page/dept/deptList.jsp").forward(req, res);
	}
	
	//跳转到部门更新页
	public void deptUpPage(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//获取部门id
		String id = req.getParameter("id");
		//根据部门id查询部门信息
		Dept t = new Dept();
		t.setId(Integer.valueOf(id));
		//查询到部门对象
		Dept dept = deptService.queryOne(t);
		//添加查询到的部门对象到请求中去
		req.setAttribute("deptInfo", dept);
		//请求转发
		req.getRequestDispatcher("/WEB-INF/page/dept/deptUp.jsp").forward(req, res);
	}
	
	//更新部门信息
	public void upDeptInfo(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		boolean y = deptService.update(req);		
		if(y) {
			responseObject("1", res);
		} else {
			responseObject("0", res);
		}
	}
	
	//跳转到添加部门页
	public void deptAddPage(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//请求转发
		req.getRequestDispatcher("/WEB-INF/page/dept/deptAdd.jsp").forward(req, res);
	}
		
	//添加部门信息
	public void addDeptInfo(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		boolean y = deptService.add(req);		
		if(y) {			
			responseObject("1", res);
		} else {
			responseObject("0", res);
		}
	}
}
