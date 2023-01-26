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

@WebFilter("/*") // ("/*") : "/로 시작되는 모든 것" -> 모든 요청 가로채기 
public class GlobalFilter extends HttpFilter implements Filter {
       
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		/*
		 * 
		 * 1) 클라이언트 /hello 라고 요청
		 * 2) /hello 문자열도 "/*" 표현식에 포함 -> GlobalFilter가 가장 먼저 작동 GlobalFilter.doFileter()메소드 실행 -> chain.doFilter() 윗부분 코드
		 * 
		 * -- 서블릿 전 실행
		 * 3) chain.doFilter() 실행 -> "/hello"가 맵핑되어있는 서블릿 실행
		 * 4) 서블릿 실행 후 다시 chain.doFilter() 아래 코드가 실행
		 * 
		 * -- 서블릿 후 실행
		 *  
		 *   
		 *  
		 */
		chain.doFilter(request, response);
	}
}
