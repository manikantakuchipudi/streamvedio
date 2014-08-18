<%-- 
    Document   : Cart
    Created on : Jul 14, 2014, 11:46:54 PM
    Author     : ken
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Bookstore Page</title>
    </head>
    <body>
        <h1>Your cart</h1>
        <table border="1" cellpadding="5">
            <tr>
                <th>Quantity</th>
                <th>Title</th>
                <th>Price</th>
                <th>Amount</th>
            </tr>
            <%@ page import="business.*, java.util.ArrayList" %>
            <%
                Cart cart = (Cart) session.getAttribute("cart");
                ArrayList<OrderItem> items = cart.getItems();
                for (OrderItem item : items) {
                    Book book = item.getBook();
            %>
            <tr valign="top">
                <td>
                    <form action="<%=response.encodeURL("cart")%>" method="post">
                        <input type="hidden" name="bookISBN" 
                               value="<%=book.getISBN()%>">
                        <input type=text size=2 name="quantity" 
                               value="<%=item.getQuantity()%>">
                        <input type="submit" value="Update">
                    </form>
                </td>
                <td>
                    <%=book.getTitle()%>
                </td>
                <td>
                    <%=book.getPriceCurrencyFormat()%>
                </td>
                <td>
                    <%=item.getTotalCurrencyFormat()%>
                </td>
                <td>
                    <form action="<%= response.encodeURL("cart")%>" method="post">
                        <input type="hidden" name="bookISBN" 
                               value="<%=book.getISBN()%>">
                        <input type="hidden" name="quantity" 
                               value="0">
                        <input type="submit" value="Remove Item">
                    </form>
                </td>
            </tr>
            <% }%>
            <tr>
                <td colspan="3">
                    <p><b>To change the quantity</b>, enter the new quantity 
                        and click on the Update button.</p>
                </td>
            </tr>
        </table>
        <br>
        <form action="<%= response.encodeURL("booklist.jsp")%>" method="post">
            <input type="submit" name="continue" value="Continue Shopping">
        </form>
        <form action="<%= response.encodeURL("checkout.jsp")%>" method="post">
            <input type="submit" name="checkout" value="Checkout">
        </form>
    </body>
</html>
