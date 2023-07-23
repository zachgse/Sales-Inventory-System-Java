<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="../components/header.jsp" %>
<%@ include file="../components/navbar.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Boy Kahang | Update Info</title>
</head>
<body>

<section class="d-flex justify-content-center align-items-center">
	<div class="card card-form">
		<div class="card-header">
			<h4 class="text-center">Edit Info</h4>
		</div>
		<div class="card-body p-5">
			<form action="<%=request.getContextPath()%>/InventoryUpdateInfoServlet">
				<input type="hidden" name="id" value="${inventory.id}"/>
				<div class="mb-3">
					<label for="exampleInputEmail1" class="form-label">Name</label>
					<input type="text" class="form-control" name="name" value="${inventory.name}">
				    <c:if test="${not empty errorMessageName}">
						<div class="alert alert-danger text-white">
				            ${errorMessageName}
				        </div>
				    </c:if>						
				</div>
				<div class="mb-3">
					<p>Current Amount: <b><c:out value="${inventory.amount}" /></b></p>
				</div>
				<div class="mb-3">
					<p>Status: <b><c:out value="${inventory.status ? 'Available' : 'Not Available'}" /></b></p>
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