package com.qin.crxl.comic.timer;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class WebContextFilter extends HttpServlet implements Filter {

	
	    public void init(FilterConfig filterConfig) throws ServletException {

	    }

	    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
	        HttpServletResponse res = (HttpServletResponse) servletResponse;
	        HttpServletRequest request=(HttpServletRequest)servletRequest;
	  
	        res.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
	        res.setHeader("Access-Control-Allow-Credentials","true");
	        res.setHeader("Access-Control-Allow-Methods","POST,GET,OPTIONS,DELETE");
	        res.setHeader("Access-Control-Allow-Headers","x-requested-with,content-type");
	        res.setHeader("Access-Control-Max-Age","3600");
	        res.setHeader("Access-Control-Allow-Headers", "Origin, No-Cache, X-Requested-With, If-Modified-Since, Pragma, Last-Modified, Cache-Control, Expires, Content-Type, X-E4M-With,userId,token");


	        filterChain.doFilter(servletRequest,servletResponse);

	    }

	}
