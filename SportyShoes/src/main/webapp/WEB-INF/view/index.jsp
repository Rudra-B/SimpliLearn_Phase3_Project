<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>     
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sporty Shoes</title>
</head>
<body>
<jsp:include page="/WEB-INF/view/components/header.jsp" ></jsp:include>
<jsp:include page="/WEB-INF/view/components/topbar.jsp" ></jsp:include>

<table border=1 cellspacing=2 cellpadding=4>
 	<tr>
 		<td><b>Product</b></td>
 		<td><b>Price</b></td>
 		<td><b>Category</b></td> 
 		<td></td>
 	</tr>
 	 <c:forEach items="${list}" var="item">
 	  	<tr>
	 		<td>${item.name }</td>
 			<td>${item.price }</td>
 			 <td>${catmap.get(item.id)}</td>
 	  		<td>
 	  			<a href="cartadditem?id=${item.id}">Add To Cart</a>
 	  		</td>
 	  	</tr>
 	  </c:forEach>
</table>

<jsp:include page="/WEB-INF/view/components/footer.jsp"></jsp:include>
</body>
</html>