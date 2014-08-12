<%@page import="com.teamz.util.AddressStore"%>
<%@page import="org.json.JSONObject"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%
 
 
 String name=request.getParameter("name");
 String phonenumber=request.getParameter("phonenumber");
 String date=request.getParameter("bookdate");
 String address=request.getParameter("address");
 
 
 
 name="name";
 phonenumber="11111111";
 date="111111";
 address="ddddd";
 
 
String conformationid="000";
 if(session.getAttribute("conformationid")!=null)
 {
 conformationid=(String)session.getAttribute("conformationid");
 JSONObject addresObject=new JSONObject();
 addresObject.put("name",name);
 addresObject.put("phonenumber",phonenumber);
 addresObject.put("bookdate",date);
 addresObject.put("address",address);
 addresObject.put("eventid",conformationid);
 new AddressStore(addresObject);
 }
 %>   
    
<jsp:include page="/header"></jsp:include>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Thankyou page</title>
<link href="http://fonts.googleapis.com/css?family=PT+Sans:400,700" rel='stylesheet' />
<link href="./css/style.css" rel="stylesheet" />
</head>
<body>
<center>
<p>Thank you booking this event your conformation id is <%=conformationid%></p>
</center>
</body>
</html>
 <jsp:include page="/footer"></jsp:include>    