package com.algaworks.crm.tenant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class TenantInterceptor extends HandlerInterceptorAdapter {
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String serverName = request.getServerName();
		String tenantId = serverName.substring(0, serverName.indexOf("."));

		request.setAttribute("tenant", tenantId);
		
		return true;
	}
}