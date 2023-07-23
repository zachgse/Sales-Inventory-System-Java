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

import salesinventorysystem.dao.InventoryDao;
import salesinventorysystem.dao.InventoryHistoryDao;
import salesinventorysystem.helpers.Helper;
import salesinventorysystem.model.Inventory;
import salesinventorysystem.model.InventoryHistory;

/**
 * Servlet implementation class InventoryServlet
 */
@WebServlet("/inventory")
public class InventoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private InventoryDao inventoryDao;
    private InventoryHistoryDao inventoryHistoryDao;
    private Helper helper;

	public void init(ServletConfig config) throws ServletException {
		inventoryDao = new InventoryDao();
		inventoryHistoryDao = new InventoryHistoryDao();
		helper = new Helper();
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();
		switch(action) {
			case "/form":
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
			case "/edit-info":
				try {
					edit_info(request,response);
				} catch (ServletException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				break;
			case "/edit":
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
				
			case "/store":
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
			case "/update-info":
				try {
					update_info(request,response);
				} catch (ServletException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				break;
			case "/update-amount":
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
	
	public void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		RequestDispatcher rd = request.getRequestDispatcher("inventory/create.jsp");
		rd.forward(request, response);
	}
	
	public void edit_info(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		int id = Integer.parseInt(request.getParameter("id"));
		Inventory inventory;
		try {
			inventory = inventoryDao.view(id);
			RequestDispatcher rd = request.getRequestDispatcher("inventory/edit-info.jsp");
			request.setAttribute("inventory", inventory);
			rd.forward(request, response);	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void edit_amount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		System.out.println("Invoked");
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
	
	public void store(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		String name = Helper.ucFirst(request.getParameter("name"));
		if (Helper.existingName(name) == true) {
			response.sendRedirect("inventory/create.jsp");
		}
		int amount = Integer.parseInt(request.getParameter("amount"));
		if (Helper.positiveInput(amount) == false) {
			response.sendRedirect("inventory/create.jsp");
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
		response.sendRedirect("inventory/index.jsp");
	}
	
	public void update_info(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		int id = Integer.parseInt(request.getParameter("id"));
		String name = Helper.ucFirst(request.getParameter("name"));
		if (Helper.existingName(name) == true) {
			response.sendRedirect("inventory/edit.jsp");
		}
		
		Inventory inventory = inventoryDao.view(id);
		if (inventory != null) {
			inventory.setName(name);
			inventoryDao.update_info(inventory);
		}
		response.sendRedirect("inventory/index.jsp");	
	}
	
	public void update_amount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		int id = Integer.parseInt(request.getParameter("id"));
		int amount = Integer.parseInt(request.getParameter("amount"));
		String type = request.getParameter("type");
		int inventory_id = id;
		int totalAmount = 0;
		boolean status = true;
		LocalDateTime currentDateTime = LocalDateTime.now();
		Date created_at = Date.from(currentDateTime.atZone(ZoneId.systemDefault()).toInstant());
		
		Inventory inventory = inventoryDao.view(id);
		if (inventory != null) {
			int currentAmount = inventory.getAmount();
			
			if (type == "Increment") {
				amount = Math.abs(amount);
				totalAmount = currentAmount + amount;
			} else {
				amount = -amount;
				if (currentAmount < amount) {
					response.sendRedirect("inventory/edit-amount.jsp");
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
		response.sendRedirect("inventory/index.jsp");
	}
	
	public void index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		try {
			List<Inventory> inventoryList = inventoryDao.index();
			request.setAttribute("inventoryList", inventoryList);
			RequestDispatcher rd = request.getRequestDispatcher("inventory/index.jsp");
			rd.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
