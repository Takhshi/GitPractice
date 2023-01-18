<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dto.User" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<p>下記の内容で登録します。よろしいですか？</p>
	<%
		User user = (User)session.getAttribute("input_data");
	%>
	名前：<%=user.getName() %><br>
	年齢：<%=user.getAge() %><br>
	性別：<%=user.getGen() %><br>
	電話番号：<%=user.getPhone() %><br>
	メールアドレス：<%=user.getMail() %><br>
	パスワード：********<br>
	<a href="UserRegisterExecuteServlet">OK</a><br>
	<a href="UserRegisterFormServlet">戻る</a>
</body>
</html>