<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="../components/header.jsp" %>
<%@ include file="../components/navbar.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Boy Kahang | Add Sale</title>
</head>
<body>

<section class="d-flex justify-content-center align-items-center">
	<div class="card card-form">
		<div class="card-header">
			<h4 class="text-center">Add sales</h4>
		</div>
		<div class="card-body p-5">
			<form action="<%=request.getContextPath()%>/SalesStoreServlet">
				<div class="mb-3">
					<label for="exampleInputEmail1" class="form-label">Type</label>
					<select class="form-control" name="type">
						<option value="none" selected disabled hidden>-- SELECT TYPE OF SALES -- </option>
						<option value="Profit">Profit</option>
						<option value="Loss">Loss</option>
					</select> 
				</div>
				<div class="mb-3">
					<label for="exampleInputEmail1" class="form-label">Amount</label>
					<input type="number" class="form-control" name="amount" placeholder="Amount">
				    <c:if test="${not empty errorMessageAmount}">
						<div class="alert alert-danger text-white">
				            ${errorMessageAmount}
				        </div>
				    </c:if>						
				</div>
				<div class="mb-5">
					<label for="exampleInputEmail1" class="form-label">Date</label>
					<input type="date" class="form-control" name="date">				
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