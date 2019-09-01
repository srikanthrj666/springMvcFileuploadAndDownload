<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Manage Documents</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<link rel="stylesheet" 
	href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css">


</head>
<body>
	<div class="container">
		<div id="wrapper">
			<div id="header">
				<br>
				<h1 class="text-primary" align="center">Manage Documents</h1>
			</div>
		</div>

		<div class="card bg-light" style="padding: 20px;">

			<table id="example" class="display" style="width:100%">
				<thead class="thead-dark">
					<tr>
						<th>File No</th>
						<th>File Name</th>
						<th></th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<!-- loop over and print our customers -->
					<c:forEach var="tempFile" items="${files}">

						<tr>
							<td>${tempFile.id}</td>
							<td>${tempFile.fName}</td>
							<td><a href="downloadById/${tempFile.id}">Download</a></td>
							<td><a  class="text-danger" href="deleteById/${tempFile.id}">Delete</a></td>
						</tr>
					</c:forEach>
				</tbody>

			</table>

		</div>
		<br> <div class="card bg-light" style="color: red;"  align="center" id="hideDiv">${status}</div> <br>
		<div class="card bg-light" style="padding: 20px;">
			<h3 class="text-primary" align="center">Upload File</h3>
			<br>
			
			<form action="uploadFileProcess" method="post"
				enctype="multipart/form-data">
				<div class="form-inline ">


					File:&nbsp; <input type="file" name="file" required="required"
						class="form-control"> &nbsp;&nbsp; <input type="submit"
						value="Submit" class="btn btn-primary">
				</div>
			</form>
		</div>

	</div>

	<!-- jQuery library -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>


	<!-- Latest compiled JavaScript -->
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
	<script
		src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>

	<script type="text/javascript">
	
	$(document).ready(function() {
	    $('#example').DataTable({
	    	
	    	"scrollY": "180px",
	    	  "scrollCollapse": true,
	    	  "paging": false
	    });
	    	
	    
	    $('#hideDiv').delay(2000).fadeOut('slow');
	    
	} );
	
		</script>
</body>
</html>