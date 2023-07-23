<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="../components/header.jsp" %>
<%@ include file="../components/navbar.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Boy Kahang | Inventory Management System History</title>
</head>
<body>

<section class="d-flex justify-content-center"> 
	<div>
		<h4 class="text-center my-5">INVENTORY MANAGEMENT SYSTEM HISTORY</h4>

		
		<table class="table table-striped text-center w-100">
			<thead>
				<tr>
					<th>#</th>
					<th>Name</th>
					<th>Amount</th>
					<th>Type</th>
					<th>Created At</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="item" items="${inventoryHistoryList}"  varStatus="loop">
					<tr>
					 	<td>${loop.index + 1}</td>
						<td><c:out value="${item.inventory_name}" /></td>
						<td><c:out value="${item.amount}" /></td>
						<td><c:out value="${item.type}" /></td>
						<td><c:out value="${item.created_at}" /></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

</section>


</body>
</html>