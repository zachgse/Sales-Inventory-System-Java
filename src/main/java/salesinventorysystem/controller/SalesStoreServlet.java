package salesinventorysystem.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import salesinventorysystem.dao.SalesDao;
import salesinventorysystem.model.Sales;

/**
 * Servlet implementation class SalesStoreServlet
 */
@WebServlet("/SalesStoreServlet")
public class SalesStoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SalesDao salesDao;
	
	public void init(ServletConfig config) throws ServletException {
		salesDao = new SalesDao();
	}
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();
		switch (action) {
			default:
				try {
					store(request,response);
				} catch (ServletException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				break;
		}
	}
	
	public void store(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		double amount = Double.parseDouble(request.getParameter("amount"));
		String type = request.getParameter("type");
		String dateStr = request.getParameter("date");
	    // Parse the date string into a LocalDate object
	    LocalDate localDate = LocalDate.parse(dateStr);
	    // Convert LocalDate to Date
	    Date created_at = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		Sales sales = new Sales(amount,type,created_at);
		salesDao.store(sales);
		RequestDispatcher rd = request.getRequestDispatcher("/SalesServlet");
		rd.forward(request, response);
	}



}
