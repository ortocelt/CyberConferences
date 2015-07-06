package net.etfbl.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.etfbl.bean.KorisnikBean;

/**
 * Servlet Filter implementation class LoginFilter
 */
@WebFilter("/LoginFilter")
public class LoginFilter implements Filter {

    /**
     * Default constructor. 
     */
    public LoginFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		 HttpServletRequest req = (HttpServletRequest) request;
		 HttpServletResponse res = (HttpServletResponse) response;
		KorisnikBean korisnik = (KorisnikBean)((HttpServletRequest)request).getSession().getAttribute("korisnikBean");
		String path = req.getRequestURI().substring(req.getContextPath().length());
		if (korisnik == null || !korisnik.isLoggedIn()) {

			String contextPath = ((HttpServletRequest) request).getContextPath();
			((HttpServletResponse)response).sendRedirect(contextPath + "/gost/login.xhtml");
		}
		if (path.startsWith("/organizator/") && korisnik.getLoginPrivilegija() != 1) {
			res.sendError(HttpServletResponse.SC_UNAUTHORIZED);
		}
		if (path.startsWith("/recezent/") && korisnik.getLoginPrivilegija() != 2) {
			res.sendError(HttpServletResponse.SC_UNAUTHORIZED);
		}
		if (path.startsWith("/kandidat/") && korisnik.getLoginPrivilegija() != 3) {
			res.sendError(HttpServletResponse.SC_UNAUTHORIZED);
		}
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
