package com.fgbg.common.listenner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.concurrent.ConcurrentHashMap;

@WebListener
public class MyApplicationListener implements ServletContextListener {

	private Logger logger =  LoggerFactory.getLogger(MyApplicationListener.class);

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		logger.info("liting: contextInitialized");
		System.err.println("MyApplicationListener初始化成功");
		ServletContext context = sce.getServletContext();  
		// IP存储器
		ConcurrentHashMap<String, Long[]> ipMap = new ConcurrentHashMap<String, Long[]>();
		context.setAttribute("ipMap", ipMap);
		// 限制IP存储器：存储被限制的IP信息
		ConcurrentHashMap<String, Long> limitedIpMap = new ConcurrentHashMap<String, Long>();
		context.setAttribute("limitedIpMap", limitedIpMap);
		logger.info("ipmap："+ipMap.toString()+";limitedIpMap:"+limitedIpMap.toString()+"初始化成功。。。。。");
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		
	}
}