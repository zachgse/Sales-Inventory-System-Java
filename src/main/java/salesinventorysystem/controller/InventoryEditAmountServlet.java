package salesinventorysystem.controller;

import java.io.IOException;
import java.sql.SQLException;

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
import salesinventorysystem.model.Inventory;

/**
 * Servlet implementation class InventoryEditAmountServlet
 */
@WebServlet("/InventoryEditAmountServlet")
public class InventoryEditAmountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private InventoryDao inventoryDao;
    private InventoryHistoryDao inventoryHistoryDao;
    private Helper helper;
    
    
	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		inventoryDao = new InventoryDao();
		inventoryHistoryDao = new InventoryHistoryDao();
		helper = new Helper();	
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();
		switch(action) {
//			case "/form":
//				try {
//					create(request,response);
//				} catch (ServletException e) {
//					e.printStackTrace();
//				} catch (IOException e) {
//					e.printStackTrace();
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//				break;
			default:
				try {
					edit_amount(request,response);
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
	
	public void edit_amount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		
		int id = Integer.parseInt(request.getParameter("id"));
		Inventory inventory;
		try {
			inventory = inventoryDao.view(id);
			RequestDispatcher rd = request.getRequestDispatcher("inventory/edit-amount.jsp");
			request.setAttribute("inventory", inventory);
			rd.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}



}
