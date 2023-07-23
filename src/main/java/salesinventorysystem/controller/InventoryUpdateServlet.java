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
 * Servlet implementation class InventoryUpdateServlet
 */
@WebServlet("/InventoryUpdateServlet")
public class InventoryUpdateServlet extends HttpServlet {
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
					update_amount(request,response);
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
	
	public void update_amount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		
		int id = Integer.parseInt(request.getParameter("id"));
		int amount = Integer.parseInt(request.getParameter("amount"));
		String type = request.getParameter("type");
		int inventory_id = id;
		int totalAmount;
		boolean status = true;
		LocalDateTime currentDateTime = LocalDateTime.now();
		Date created_at = Date.from(currentDateTime.atZone(ZoneId.systemDefault()).toInstant());
		
		Inventory inventory = inventoryDao.view(id); //problem
		if (inventory != null) {
			int currentAmount = inventory.getAmount();
			System.out.println("Invoked");
			if (type.equals("Increment")) {
				amount = Math.abs(amount);
				totalAmount = currentAmount + amount;
			} else {
				if (currentAmount < amount) {
	                // If the current amount is less than the requested amount, forward to edit-amount.jsp with an error message
	                request.setAttribute("errorMessageAmount", "Insufficient amount in inventory for decrement operation.");
	                RequestDispatcher rd = request.getRequestDispatcher("/InventoryEditAmountServlet?id="+id);
	                rd.forward(request, response);
	                return; // Stop further execution to prevent updating the inventory with an invalid amount
				}
				totalAmount = currentAmount - amount;
				if (totalAmount <= 0) {
					status = false;
				}
			}
			
			inventory.setAmount(totalAmount);
			inventory.setStatus(status);
			inventoryDao.update_amount(inventory);
			
			InventoryHistory inventoryHistory = new InventoryHistory(inventory_id, amount, type, created_at);
			inventoryHistoryDao.store(inventoryHistory);
		}
		RequestDispatcher rd = request.getRequestDispatcher("/inventory");
		request.setAttribute("successMessage", "Item amount has been updated.");
		rd.forward(request, response);
	}



}
