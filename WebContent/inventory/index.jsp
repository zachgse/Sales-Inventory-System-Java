<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="../components/header.jsp" %>
<%@ include file="../components/navbar.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Boy Kahang | Inventory Management System</title>
</head>
<body>

<section class="d-flex justify-content-center"> 
	<div>
		<h4 class="text-center my-5">INVENTORY MANAGEMENT SYSTEM</h4>
		
	   	<c:if test="${not empty successMessage}">
	      	<div class="alert alert-success text-black">
	            ${successMessage}
	        </div>
	    </c:if>
		
		<table class="table table-striped text-center w-100">
			<thead>
				<tr>
					<th>#</th>
					<th>Name</th>
					<th>Amount</th>
					<th>Status</th>
					<th>Created At</th>
					<th>Actions</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="item" items="${inventoryList}"  varStatus="loop">
					<tr>
					 	<td>${loop.index + 1}</td>
						<td><c:out value="${item.name}" /></td>
						<td><c:out value="${item.amount}" /></td>
						<td><c:out value="${item.status ? 'Available' : 'Not Available'}"/></td>
						<td><c:out value="${item.created_at}" /></td>
						<td>
							<div>
								<button class="btn btn-primary mx-2">
									<a class="button-link" href="<%=request.getContextPath()%>/InventoryEditInfoServlet?id=<c:out value='${item.id}' />">Edit Info</a>
								</button>
							
								<button class="btn btn-secondary mx-2">
									<a class="button-link" href="<%=request.getContextPath()%>/InventoryEditAmountServlet?id=<c:out value='${item.id}' />">Edit Amount</a>
								</button>
							</div>			
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</section>


</body>
</html>