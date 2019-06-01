<%@ page pageEncoding="utf-8"%>
<% 
	String view = request.getParameter("view");
	if (view.startsWith("user/")) {
		pageContext.include("user_layout.jsp");
	} else if (view.startsWith("home/")) {
		pageContext.include("home_layout.jsp");
	};
%>
