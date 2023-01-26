package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CounterDao;

@WebServlet("/hello")
public class GlobalHello extends HttpServlet {
	private CounterDao counterDao;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("GlovalHello");
	
		counterDao = new CounterDao();
		try {
			int todayCount = counterDao.selectTodayCount();
			request.setAttribute("todayCount", todayCount);
			int totalCount = counterDao.selectTotalCount();
			request.setAttribute("totalCount", totalCount);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		request.getRequestDispatcher("WEB-INF/view/hello.jsp").forward(request, response);
	}

}
