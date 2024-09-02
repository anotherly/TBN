/*
 * Copyright 2008-2009 MOPAS(MINISTRY OF SECURITY AND PUBLIC ADMINISTRATION).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package kr.co.wizbrain.tbn.comm.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class HTMLTagFilter implements Filter {

	@SuppressWarnings("unused")
	private FilterConfig config;

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		
		if (checkUrl(req)) {
			/// minking/로 시작하는 url은 filter를 하지 않는다.
			chain.doFilter(request, response);
		} else {
			// 그 외의 경우 filter를 한다.
			chain.doFilter(new HTMLTagFilterRequestWrapper((HttpServletRequest) request), response);
		}
		//chain.doFilter(new HTMLTagFilterRequestWrapper((HttpServletRequest)request),response);
	}

	public void init(FilterConfig config) throws ServletException {
		this.config = config;
	}

	public void destroy() {

	}

	// "/minking/"로 시작하는 url인지 확인하는 method
	private boolean checkUrl(HttpServletRequest req) {
		String uri = req.getRequestURI().toString().trim();
		if (uri.startsWith("/minking/")) {
			return true;
		} else {
			return false;
		}
	}

}
