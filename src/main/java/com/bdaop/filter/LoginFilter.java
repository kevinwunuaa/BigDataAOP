package com.bdaop.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import com.bdaop.context.AppContext;

/**
 * 登录准入过滤器.
 * @author Wuyong
 *
 */
public class LoginFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * 过滤入口.
	 * <br/>
	 * 非登录状态下对/login.action、/loginPage.action、/ssoLogon.action放行，否则跳转到/loginPage.action
	 * <br/>
	 * 登录状态下放行所有请求
	 */
	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest httpReq = (HttpServletRequest) req;
		HttpServletResponse httpRes = (HttpServletResponse) res;
		HttpSession session = httpReq.getSession();
		Object userInfo = session.getAttribute(AppContext.SESSION_USER);
		if(userInfo != null && StringUtils.isNotEmpty(userInfo.toString())){
			filterChain.doFilter(req, res);
			return ;
		}
		
		String uri = httpReq.getRequestURI();

		if(!(uri.indexOf("/loginPage.action") >= 0 || uri.indexOf("/login.action") >= 0 || uri.indexOf("/ssoLogon.action") >= 0)){
			httpRes.sendRedirect(httpReq.getContextPath() + "/loginPage.action");
		}
		
		filterChain.doFilter(req, res);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}
	
}

