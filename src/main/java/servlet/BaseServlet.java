package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BaseServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// http://localhost:8080/project1/demoServlet?method=loginPage
		//设置请求编码
		req.setCharacterEncoding("utf-8");
		String methodName = req.getParameter("method");
		
		try {
			/* 通过此对象获取类，再获取类中指定名称并传入参数类型的对应Class对象 */
		    Method method = this.getClass().getDeclaredMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
			 /* 执行方法，this实际上是StudentManage对象 */
		    method.invoke(this, req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	
	public void responseObject(Object obj, HttpServletResponse res) {
		//设置响应编码
		res.setCharacterEncoding("utf-8");
		PrintWriter out = null;
		try {
			out = res.getWriter();
			out.write(obj.toString());
			out.flush();
			if (out != null) {
				out.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
}
