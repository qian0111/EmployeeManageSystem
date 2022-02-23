package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/*
 * 字符编码过滤器：防止中文乱码
 */
public class CharacterEncodingFilter implements Filter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest)request;
        HttpServletResponse httpResponse = (HttpServletResponse)response;
        //设置请求编码
        httpRequest.setCharacterEncoding("UTF-8");
        //设置响应编码
        httpResponse.setCharacterEncoding("UTF-8");
        
        //调用doFIlter方法,如果还有别的过滤器会自动向下调用
		chain.doFilter(request, response);
	}

}
