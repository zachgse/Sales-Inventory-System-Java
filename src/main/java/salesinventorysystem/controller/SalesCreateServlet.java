package salesinventorysystem.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
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
 * Servlet implementation class SalesCreateServlet
 */
@WebServlet("/SalesCreateServlet")
public class SalesCreateServlet extends HttpServlet {
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
					create(request,response);
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
	
	public void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		RequestDispatcher rd = request.getRequestDispatcher("sales/create.jsp");
		rd.forward(request, response);
	}

}
