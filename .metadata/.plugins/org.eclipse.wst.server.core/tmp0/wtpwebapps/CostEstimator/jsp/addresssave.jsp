<%@page import="com.teamz.util.SelectiondataObject"%>
<%@page import="org.json.JSONArray"%>
<%@page import="org.json.JSONObject"%>
<%

JSONObject USDN=new JSONObject((String)request.getParameter("usdn"));
JSONObject USPN=new JSONObject((String)request.getParameter("uspn"));
JSONObject USFOOD=new JSONObject(request.getParameter("usfood"));
JSONObject USDRINK=new JSONObject(request.getParameter("usdrink"));
JSONObject USHEALTHY=new JSONObject(request.getParameter("ushealthy"));
JSONObject entrieOnjects=new JSONObject();
entrieOnjects.put("USDN", USDN);
entrieOnjects.put("USPN", USPN);
entrieOnjects.put("USFOOD", USFOOD);
entrieOnjects.put("USDRINK", USDRINK);
entrieOnjects.put("USHEALTHY", USHEALTHY);
String noofusers=(String)session.getAttribute("noofusers");
int eventid=new SelectiondataObject(entrieOnjects,Integer.parseInt(noofusers)).eventid();
session.setAttribute("conformationid",""+eventid);

%>
<html>

</html>