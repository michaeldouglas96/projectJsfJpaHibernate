package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import connection.JPAUtil;
import entitys.UserRegister;

@WebFilter
public class FilterAutentica implements Filter{

	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();
		
		UserRegister userLogin = (UserRegister) session.getAttribute("userLogin");
		
		String url = req.getServletPath();
		
		if(!url.equalsIgnoreCase("home.xhtml") && userLogin == null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/home.xhtml");
			dispatcher.forward(req, response);
			return;
			
		}else {
		
		chain.doFilter(request, response);
		
		}
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
		//Init connection to Data Base
		JPAUtil.getEntityManager();
		
	}
	
	
}
