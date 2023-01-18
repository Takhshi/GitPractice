<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3 style="color:red">下記のデータを削除しました。</h3>
	<%
	request.setCharacterEncoding("UTF-8");
	String mail = request.getParameter("mail");
	%>
	<p>メールアドレス：<%=mail %></p><br>
	<a href="./">戻る</a>
</body>
</html>