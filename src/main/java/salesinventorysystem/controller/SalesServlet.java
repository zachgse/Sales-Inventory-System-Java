package salesinventorysystem.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
 * Servlet implementation class SalesServlet
 */
@WebServlet("/SalesServlet")
public class SalesServlet extends HttpServlet {
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
					index(request,response);
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
	
	public void index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		List<Sales> salesList = new ArrayList<>();
		salesList = salesDao.index();
		double amount = 0;
		double totalAmount = 0;
		
		for (int i=0; i < salesList.size(); i++) {
		    Sales sales = salesList.get(i);
			if (sales.getType().equals("Profit")) {
				amount = sales.getAmount();
			} else {
				amount = -(sales.getAmount());
			}
			totalAmount += amount;
		}
		
		if (totalAmount <= 0) {
			request.setAttribute("text-red", "text-red");
		} else {
			request.setAttribute("text-green", "text-green");
		}
		RequestDispatcher rd = request.getRequestDispatcher("sales/index.jsp");
		request.setAttribute("salesList", salesList);
		request.setAttribute("totalAmount", totalAmount);
		rd.forward(request, response);
	}
}
