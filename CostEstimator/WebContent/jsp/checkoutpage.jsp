<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	session.setAttribute("conformationid", null);
%>
<jsp:include page="/header"></jsp:include>
<jsp:include page="/application"></jsp:include>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>CheckoutDetails</title>
<link href="http://fonts.googleapis.com/css?family=PT+Sans:400,700"
	rel='stylesheet' />
<link href="./css/style.css" rel="stylesheet" />

<script type="text/javascript">
	function checkout() {
		var senddata = "usdn=" +JSON.stringify(usdn) + "&uspn=" + JSON.stringify(uspn)+"&usfood="+JSON.stringify(usfood)+"&usdrink="+JSON.stringify(usdrink)+"&ushealthy="+JSON.stringify(ushealthy);
		var retrunvalue = false;
		jQuery.ajax({

			url : "addresssave",
			data : senddata,
			async : false,
			success : function(val) {
				retrunvalue = true;

			},
			parse : function(response) {
				retrunvalue = true;
			}
		});
		return retrunvalue;
		}
</script>




</head>
<body>

	<center>
		<form id="checkout" method="post" onsubmit="return checkout();"
			action="thankyou">
			<h1>ADDRESS</h1>
			<ul id="services">

				<div>
					<span style="text-align: left;">name :</span> <input type="text" id="name" name="name" />

				</div>
				<div>
					<span style="text-align: left;">bookdate :</span> <input type="text" id="bookdate" name="bookdate"/>
				</div>
				
				<div>
					<span style="text-align: left;">PhoneNumber :</span> <input type="text" id="phonenumber" name="phonenumber" />
				</div>
				
				<div>
					<span style="text-align: left;">address :</span> <input type="textArea" id="address" name="address"/>
				</div>
			</ul>
			<input type="submit" id="order" value="checkout" />

		</form>
	</center>
</body>


<script src="./js/app/model/userselection.js"></script>
<script type="text/javascript" src="js/app/model/addressModel.js"></script>
<script type="text/javascript"
	src="js/app/controller/addressController.js"></script>
</html>
<jsp:include page="/footer"></jsp:include>
