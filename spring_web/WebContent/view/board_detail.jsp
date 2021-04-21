<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table border="1">
		<tr>
			<td>번호</td>
			<td>${board.seq}</td>
			<td>글 제목</td>
			<td>${board.title}</td>
			<td>작성자</td>
			<td>${board.writer}</td>
		</tr>
		<tr><td colspan="6">${board.contents}</td></tr>
	</table>
	<a href="board_list">목록</a>
</body>
</html>