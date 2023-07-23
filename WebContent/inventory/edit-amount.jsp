<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="../components/header.jsp" %>
<%@ include file="../components/navbar.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Boy Kahang | Update Amount</title>
</head>
<body>

<section class="d-flex justify-content-center align-items-center">
	<div class="card card-form">
		<div class="card-header">
			<h4 class="text-center">Edit Amount</h4>
		</div>
		<div class="card-body p-5">
			<form action="<%=request.getContextPath()%>/InventoryUpdateServlet">
				<input type="hidden" name="id" value="${inventory.id}"/>
				<div class="mb-3">
					<p>Name: <b><c:out value="${inventory.name}" /></b></p>
				</div>
				<div class="mb-3">
					<p>Current Amount: <b><c:out value="${inventory.amount}" /></b></p>
				</div>
				<div class="mb-3">
					<label for="exampleInputEmail1" class="form-label">Type</label>
					<select class="form-control" name="type">
						<option value="none" selected disabled hidden>-- SELECT TYPE OF UPDATE AMOUNT -- </option>
						<option value="Increment">Add Stocks</option>
						<option value="Decrement">Decrease Stocks</option>
					</select> 
				</div>
				<div class="mb-5">
					<label for="exampleInputEmail1" class="form-label">Amount</label>
					<input type="number" class="form-control" name="amount" placeholder="Amount">
				    <c:if test="${not empty errorMessageAmount}">
						<div class="alert alert-danger text-white">
				            ${errorMessageAmount}
				        </div>
				    </c:if>						
				</div>
				<br/><br/><br/>
				<button type="submit" class="btn btn-success pull-right mb-5">Submit</button>
				<br/><br/><br/>
			</form>
		</div>
	</div>
</section>
		
</body>
</html>