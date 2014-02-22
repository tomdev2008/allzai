package check.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.log4j.Logger;

public class AccessFilter implements Filter {
	
	private static final Logger logger = Logger.getLogger(AccessFilter.class);

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		
		logger.info(req.getRemoteAddr());
		chain.doFilter(req, res);

//		if(adminMap != null) {
//			
//			logger.info(adminMap.toString());
//			
//			if(adminMap.containsKey(req.getRemoteAddr())) {
//				chain.doFilter(req, res);
//				return;
//			}
//		}
//		res.getWriter().append("<font size='24' color='red'><B>403</B></font> Access Forbidden ! " + req.getRemoteAddr());
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
