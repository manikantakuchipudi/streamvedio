<%@page import="java.io.File"%>
<%@page import="business.Book"%>
<%@page import="java.util.ArrayList"%>
<%@page import="data.BookRepository"%>
<%

ServletContext sc = getServletContext();
String path = sc.getRealPath("WEB-INF/books.txt");
ArrayList<Book> getBooks=BookRepository.getBooks(path);
%>




<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
 <h1>Book list</h1>
        <table cellpadding="5" border=1>
            <tr valign="bottom">
                <td align="left"><b>Title</b></td>
                <td align="left"><b>Price</b></td>
                <td align="left"></td>
            </tr> 
        <%
        for(int i=0;i<getBooks.size();i++)
        {
        	String title=getBooks.get(i).getTitle();
        	double price=getBooks.get(i).getPrice();
        	String isbn=getBooks.get(i).getISBN();
        		//11331A05B1
        %>
        <tr valign="top">
                <td><%=title%></td>
                <td><%=price%></td>
                <td><a href="<%=response.encodeURL("cart?bookISBN="+isbn)%>">Add To Cart</a></td>
            </tr>
		<%
        }
		%>



</body>
</html>