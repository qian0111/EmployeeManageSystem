package filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import util.StrUtil;
/*
 * 登录过滤器
 * 所有用户登录之后才能访问的页面，需要判断session是否存在
 * 如果session不存在，则认为，登录失效，请求转发到登录页
 */
public class LoginFilter implements Filter{
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		//从请求中获取method参数
		String methodName = request.getParameter("method");
		
		if (StrUtil.isEmpty(methodName)) {
			PrintWriter out = response.getWriter();
			out.write("not find method param");
			out.flush();
			if (out != null) out.close();
			return;//return之后，请求不能到达servlet
		}
		//验证session是否失效
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();
		if (session.getAttribute("staff") == null) {
//			请求转发
//			req.getRequestDispatcher("/WEB-INF/page/login.jsp").forward(request, response);
			HttpServletResponse res = (HttpServletResponse) response;
			//重定向
			res.sendRedirect("http://localhost:8080/EmployeeManageSystem");
		} else {
			//放行，请求被放行之后到达servlet
			chain.doFilter(request, response);
		}
	}
}
