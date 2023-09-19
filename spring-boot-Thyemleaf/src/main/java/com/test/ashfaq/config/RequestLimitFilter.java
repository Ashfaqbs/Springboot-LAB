/**
 * Code developed by Ashfaq (© [Year])
 * GitHub: https://github.com/DarkSharkAsh
 */

package com.test.ashfaq.config;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletResponse;

public class RequestLimitFilter implements Filter {

	private int requestCount = 0;
	private static final int LIMIT = 5;

	private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
//    private final ScheduledExecutorService hourlyScheduler = Executors.newScheduledThreadPool(1);
//	private final ScheduledExecutorService everyTenSecsScheduler = Executors.newScheduledThreadPool(1);

	public RequestLimitFilter() {
		scheduler.scheduleAtFixedRate(() -> requestCount = 0, 10, 10, TimeUnit.MINUTES);

//		hourlyScheduler.scheduleAtFixedRate(() -> requestCount = 0, 1, 1, TimeUnit.HOURS);
//		everyTenSecsScheduler.scheduleAtFixedRate(() -> requestCount = 0, 10, 10, TimeUnit.SECONDS);

		System.out.println("Creating RequestLimitFilter Object ");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		if (requestCount < LIMIT) {
			requestCount++;
			chain.doFilter(request, response);
		} else {
			((HttpServletResponse) response).sendError(429, "Request limit exceeded");
		}
	}
}
