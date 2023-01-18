<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="dto.AllUser" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1 align="center">会員一覧</h1>
	<table align="center" border="1" width="80%">
		<tr>
			<th>名前</th>
			<th>年齢</th>
			<th>性別</th>
			<th>電話番号</th>
			<th>メールアドレス</th>
		</tr>
	<%
	List<AllUser> list = (ArrayList<AllUser>)request.getAttribute("list");
		for(AllUser au : list) {
	%>
		<tr>
			<td><%=au.getName() %></td>
			<td><%=au.getAge() %></td>
			<td><%=au.getGen() %></td>
			<td><%=au.getPhone() %></td>
			<td><%=au.getMail() %></td>
		</tr>
	<%} %>
	</table><br>
	<a href="./">戻る</a>
</body>
</html>