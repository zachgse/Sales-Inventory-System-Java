<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="../components/header.jsp" %>
<%@ include file="../components/navbar.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Boy Kahang | Sales List</title>
</head>
<body>

<section class="d-flex justify-content-center"> 
	<div>
		<h4 class="text-center my-5">SALES LIST</h4>
		
	   	<c:if test="${not empty profitMessage}">
	      	<div class="alert alert-success text-black">
	            ${profitMessage}
	        </div>
	    </c:if> 
	    
  	   	<c:if test="${not empty lossMessage}">
	      	<div class="alert alert-dangertext-black">
	            ${lossMessage}
	        </div>
	    </c:if> 
	    
	    <h5 class="mb-5">TOTAL SALES: 
			<c:choose>
			  <c:when test="${totalAmount >= 0}">
			    <span class="text-green">+ <c:out value="${totalAmount}"/></span>
			  </c:when>
			  <c:otherwise>
			    <span class="text-red">- <c:out value="${totalAmount}"/></span>
			  </c:otherwise>
			</c:choose>
	    </h5>
		
		<table class="table table-striped text-center w-100">
			<thead>
				<tr>
					<th>#</th>
					<th>Date</th>
					<th>Type</th>
					<th>Amount</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="item" items="${salesList}"  varStatus="loop">
					<tr>
					 	<td>${loop.index + 1}</td>
						<td><c:out value="${item.created_at}" /></td>
						<td><c:out value="${item.type}" /></td>
						<td><c:out value="${item.amount}" /></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</section>


</body>
</html>