<%@page import="model.product"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%
	
	if (request.getParameter("productId") != null)
	{
		product pObj = new product();
		String stsMsg = "";
	//for Insert--------------------------
	if (request.getParameter("btnSaveProduct") == "")
	{
		stsMsg = pObj.insertProductDetails(request.getParameter("txtName"),
		request.getParameter("txtCode"),
		request.getParameter("txtQty"),
		request.getParameter("txtPrice"),
		request.getParameter("txtDesc"));
	}
	else//for Update----------------------
	{
		stsMsg = pObj.updateProductDetails(request.getParameter("btnSaveProduct"),
				request.getParameter("txtName"),
				request.getParameter("txtCode"),
				request.getParameter("txtQty"),
				request.getParameter("txtPrice"),
				request.getParameter("txtDesc"));
	}
	session.setAttribute("statusMsg", stsMsg);
	}
	//for Delete-----------------------------
	if (request.getParameter("hidItemIDDelete") != null)
	{
		product pObj = new product();
		String stsMsg =	pObj.deleteProductDetails(request.getParameter("hidItemIDDelete"));
		session.setAttribute("statusMsg", stsMsg);
	}
%>
<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" >
	<script src="components/jquery-3.6.0.js"></script>
	<script src="components/product.js"></script>
	<link rel="stylesheet" href="style/stylesheet.css">
	
<meta charset="ISO-8859-1">

</head>
<body>
	<div class="container">
<h1>Product Management</h1>

<form id="formProduct" name="formProduct" method="post" action="product.jsp">
 Product Name:
<input id="txtName" name="txtName" type="text"
 class="form-control form-control-sm">
<br> Product Code:
<input id="txtCode" name="txtCode" type="text"
 class="form-control form-control-sm">
<br> Product Qty:
<input id="txtQty" name="txtQty" type="text"
 class="form-control form-control-sm">
 <br> Product Price:
<input id="txtPrice" name="txtPrice" type="text"
 class="form-control form-control-sm">
 <br> Product Desc:
<input id="txtDesc" name="txtDesc" type="text"
 class="form-control form-control-sm">

<br>
<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary">
<input type="hidden" id="btnSaveProduct" name="btnSaveProduct" value="">
</form>
<div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div>

<%
 out.print(session.getAttribute("statusMsg"));
%>
<br>
<div id="divProductGrid">
<%
	product pObj = new product();
	out.print(pObj.readProductDetails());
%>
</div>
</body>
</html>