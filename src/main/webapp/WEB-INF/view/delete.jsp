<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dto.UserDel" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		request.setCharacterEncoding("UTF-8");
		String errorCode = request.getParameter("error");
		if(errorCode != null && errorCode.equals("1")){
			UserDel ud = (UserDel)session.getAttribute("input_data");
	%>
	<p style="color:red">会員の削除に失敗しました。</p>
		<h3>削除する会員のメールアドレスを入力してください。</h3>
    	<form action="DeleteUserServlet" method="post">
      	メールアドレス：<input type="text" name="mail"><br>
      	<br>
        <input type="submit" value="削除">
        </form><br>
       	<%
		} else {
		%>
		<h3>削除する会員のメールアドレスを入力してください。</h3>
    	<form action="DeleteUserServlet" method="post">
      	メールアドレス：<input type="text" name="mail"><br>
      	<br>
        <input type="submit" value="削除">
        </form><br>
        <%
		}
		%>
</body>
</html>