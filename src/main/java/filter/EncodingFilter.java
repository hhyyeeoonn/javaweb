package filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;

@WebFilter("/*")
public class EncodingFilter extends HttpFilter implements Filter {
   
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// doPost 요청일 때만 실행
		if(request instanceof HttpServletRequest) {
			HttpServletRequest req = (HttpServletRequest)request; //부모타입을 자식타입으로 형변환
			if(req.getMethod().equals("post")) {
				request.setCharacterEncoding("UTF-8");
				System.out.println("Encoding UTF-8 Filter");
			}
		}
		chain.doFilter(request, response);
	}
}
