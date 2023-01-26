package listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import dao.CounterDao;

@WebListener
public class CountListener implements HttpSessionListener {
	private CounterDao counterDao;
	
	
	// 세션이 생성될 때
    public void sessionCreated(HttpSessionEvent se)  { 
    
    	// 현재 서브의 접속자 수 : 서버 속성을 이용
    	int num = (int)se.getSession().getServletContext().getAttribute("currentCount");
    	se.getSession().getServletContext().setAttribute("currentCount", num + 1);
    	System.out.println("세션생성 currentCount: " + (num + 1));
    
    	// 전체 or 날짜별 접속자 수 -> db가 필요
    	counterDao = new CounterDao();
    	try {
    		int todayCount = counterDao.selectTodayCount();
    		if(todayCount == 0) { // 오늘 첫 접속자
    			counterDao.insertCount();
    		} else {
    			counterDao.updateCount();
    		}
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
    }

    // 세션이 소멸될 때
    public void sessionDestroyed(HttpSessionEvent se)  { 
    	int num = (int)se.getSession().getServletContext().getAttribute("currentCount");
    	se.getSession().getServletContext().setAttribute("currentCount", num - 1);
    	System.out.println("세션소멸 currentCount: " + (num - 1));
    }
	
}
