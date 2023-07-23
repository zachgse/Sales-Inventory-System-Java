<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="../components/header.jsp" %>
<%@ include file="../components/navbar.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Boy Kahang | Add Item</title>
</head>
<body>

<section class="d-flex justify-content-center align-items-center">
	<div class="card card-form">
		<div class="card-header">
			<h4 class="text-center">Add item</h4>
		</div>
		<div class="card-body p-5">
			<form action="<%=request.getContextPath()%>/InventoryStore">
				<div class="mb-3">
					<label for="exampleInputEmail1" class="form-label">Name</label>
					<input type="text" class="form-control" name="name" placeholder="Name">
				    <c:if test="${not empty errorMessageName}">
				        <div class="alert alert-danger text-white">
				            ${errorMessageName}
				        </div>
				    </c:if>
				</div>
				<div class="mb-5">
					<label for="exampleInputEmail1" class="form-label">Initial Amount</label>
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