package salesinventorysystem.controller;

import java.io.IOException;
import java.sql.SQLException;
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

import salesinventorysystem.dao.InventoryDao;
import salesinventorysystem.dao.InventoryHistoryDao;
import salesinventorysystem.helpers.Helper;
import salesinventorysystem.model.Inventory;
import salesinventorysystem.model.InventoryHistory;

/**
 * Servlet implementation class InventoryCreate
 */
@WebServlet("/InventoryCreate")
public class InventoryCreate extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private InventoryDao inventoryDao;
    private InventoryHistoryDao inventoryHistoryDao;
    private Helper helper;

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
		RequestDispatcher rd = request.getRequestDispatcher("inventory/create.jsp");
		rd.forward(request, response);
	}
}
