<%
System.out.println("user value set here"+request.getParameter("uscustomersvalue"));
session.setAttribute("noofusers",request.getParameter("uscustomersvalue"));
String r = "<p>You have successfully uploaded your video.</p>";
response.setContentType("text/html");
response.getWriter().write(r);
%>