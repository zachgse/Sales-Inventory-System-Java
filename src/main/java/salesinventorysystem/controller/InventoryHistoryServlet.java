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

import salesinventorysystem.dao.InventoryDao;
import salesinventorysystem.dao.InventoryHistoryDao;
import salesinventorysystem.helpers.Helper;
import salesinventorysystem.model.InventoryHistory;

/**
 * Servlet implementation class InventoryHistoryServlet
 */
@WebServlet("/InventoryHistoryServlet")
public class InventoryHistoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private InventoryHistoryDao inventoryHistoryDao;
	
	public void init(ServletConfig config) throws ServletException {
		inventoryHistoryDao = new InventoryHistoryDao();
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();
		switch(action) {
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
		List<InventoryHistory> inventoryHistoryList = inventoryHistoryDao.index();
		RequestDispatcher rd = request.getRequestDispatcher("inventory/history.jsp");
		request.setAttribute("inventoryHistoryList", inventoryHistoryList);
		rd.forward(request, response);
	}



}
