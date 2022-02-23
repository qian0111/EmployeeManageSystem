package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.IStaffService;
import service.impl.StaffService;
import util.DBHelper;
import util.MD5Util;

/*
 * 员工控制类
 */
public class PageServlet extends BaseServlet{

	private IStaffService staffService = new StaffService();
	
	//跳转到登录页面
	public void loginPage(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/page/login.jsp").forward(req, res);
	}
	
	//跳转到注册页面
	public void registerPage(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/page/register.jsp").forward(req, res);
	}
	
	//登录验证
	public void loginCheck(HttpServletRequest req, HttpServletResponse res) {
		boolean y = staffService.login(req);
		
		if(y) {
			responseObject("1", res);
		} else {
			responseObject("0", res);
		}
	}
	
	//注册处理
	public void register(HttpServletRequest req, HttpServletResponse res) {
		//注册员工信息
		boolean y = staffService.register(req);
		
		if(y) {
			responseObject("1", res);
		} else {
			responseObject("0", res);
		}
		
	}
	
}
