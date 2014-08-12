<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
session.setAttribute("noofusers",10);
%>
<jsp:include page="/header"></jsp:include>
<jsp:include page="/application"></jsp:include>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Selection Data</title>
<link href="http://fonts.googleapis.com/css?family=PT+Sans:400,700"
	rel='stylesheet' />
<link href="./css/style.css" rel="stylesheet" />
</head>
<script type="text/javascript">
 function customersset() {
	 uscustomersvalue=document.getElementById('persons').value;
	 
	 var senddata="uscustomersvalue="+uscustomersvalue;
	 var retrunvalue=false;
	 jQuery.ajax({
		   
		    url: "usersset",
		    data:senddata,
	        async:   false,
			success:function(val)
		    {
		  		retrunvalue=true;
		  		
		    },
	   parse:function(response)
	   {
		   retrunvalue=true;
	   }
   });
	 return retrunvalue;
	
	}
 
 

</script>
<body>


	<form id="main" method="post" onsubmit="return customersset();"
		action="checkout">
		<h1>BookList Services</h1>

		<ul id="services">

		</ul>

		<p id="total">
			total: <span>$0</span>
		</p>

		<input type="submit" id="order" value="Order" />

	</form>
</body>
<script src="./js/app/model/userselection.js"></script>
<script src="./js/app/controller/userselection.js"></script>
<script src="./js/app/view/userselection.js"></script>
<script src="./js/app/view/userselectionApp.js"></script>

</html>
<footer> <jsp:include page="/footer"></jsp:include> </footer>

