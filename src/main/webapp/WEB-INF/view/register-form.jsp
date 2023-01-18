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
	<%
		request.setCharacterEncoding("UTF-8");
		String errorCode = request.getParameter("error");
		if(errorCode != null && errorCode.equals("1")){
			User us = (User)session.getAttribute("input_data");
	%>
	<p style="color:red">登録に失敗しました。</p>
	<h3>新規会員登録</h3>
	<form action="UserRegisterConfirmServlet" method="post">
		名前：<input type="text" name="name" value="<%=us.getName()%>"><br>
		年齢：<input type="text" name="age" value="<%=us.getAge()%>"><br>
		性別：
		男：<input type="radio" name="gender" value="0">
		女：<input type="radio" name="gender" value="1"><br>
		電話番号：<input type="text" name="phone" value="<%=us.getPhone()%>"><br>
		メールアドレス：<input type="email" name="email" value="<%=us.getMail()%>"><br>
		パスワード：<input type="password" name="pw"><br>
		<br>
		<input type="submit" value="登録">
	</form>
	<%
		} else {
	%>
	<h3>新規会員登録</h3>
	<form action="UserRegisterConfirmServlet" method="post">
		名前：<input type="text" name="name"><br>
		年齢：<input type="text" name="age"><br>
		性別：
		男：<input type="radio" name="gender" value="0">
		女：<input type="radio" name="gender" value="1"><br>
		電話番号：<input type="text" name="phone"><br>
		メールアドレス：<input type="email" name="email"><br>
		パスワード：<input type="password" name="pw"><br>
		<br>
		<input type="submit" value="登録">
	</form>
	<%
		}
	%>
</body>
</html>