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
 * Servlet implementation class InventoryStore
 */
@WebServlet("/InventoryStore")
public class InventoryStore extends HttpServlet {
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
		String name = Helper.ucFirst(request.getParameter("name"));
		if (Helper.existingName(name) == true) {
            request.setAttribute("errorMessageName", "Name already exists.");
            RequestDispatcher rd = request.getRequestDispatcher("/InventoryCreate");
            rd.forward(request, response);
            return;
		}
		int amount = Integer.parseInt(request.getParameter("amount"));
		if (Helper.positiveInput(amount) == false) {
            request.setAttribute("errorMessageAmount", "Negative values are not allowed.");
            RequestDispatcher rd = request.getRequestDispatcher("/InventoryCreate");
            rd.forward(request, response);
            return;
		}
		boolean status = true;
		LocalDateTime currentDateTime = LocalDateTime.now();
		Date created_at = Date.from(currentDateTime.atZone(ZoneId.systemDefault()).toInstant());
		Inventory inventory = new Inventory(name,amount,status,created_at);
		inventoryDao.store(inventory);
		
		int inventory_id = inventory.getId();
		String type = request.getParameter("type");
		InventoryHistory inventoryHistory = new InventoryHistory(inventory_id, amount, type,created_at);
		inventoryHistoryDao.store(inventoryHistory);
		RequestDispatcher rd = request.getRequestDispatcher("/inventory");
		request.setAttribute("successMessage", "Item has been added.");
		rd.forward(request, response);
	}



}
