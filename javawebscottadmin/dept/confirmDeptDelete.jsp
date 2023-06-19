<%--
JavaWebScottAdmin Lesson Src13

部門情報削除確認画面。

ファイル名=confirmDeptDelete.jsp
ディレクトリ=/javawebscottadmin/dept/
 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="local.hal.night.javawebscottadmin.entity.*"%>
<%
	Dept dept = (Dept) request.getAttribute("dept");
%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>部門情報削除｜JavaWebscottAdmin Lesson</title>
<link rel="stylesheet" href="/javawebscottadmin/css/main.css"
	type="text/css">
</head>
<body>
	<h1>部門情報削除</h1>
	<nav>
		<ul>
			<li><a href="/javawebscottadmin/">TOP</a></li>
			<li><a href="/javawebscottadmin/dept/showList">部門リスト</a></li>
			<li>部門情報削除</li>
		</ul>
	</nav>
	<section>
		<p>
			以下の部門情報を削除します。<br> よろしければ、削除ボタンをクリックしてください。
		</p>
		<form action="/javawebscottadmin/dept/delete" method="post">
			<table>
				<tbody>
					<tr>
						<th>部門番号</th>
						<td><%=dept.getDeptno()%> <input type="hidden"
							id="deleteDeptDeptno" name="deleteDeptDeptno"
							value="<%=dept.getDeptno()%>"></td>
					</tr>
					<tr>
						<th>部門名</th>
						<td><%=dept.getDname()%></td>
					</tr>
					<tr>
						<th>所在地</th>
						<td><%=dept.getLoc()%></td>
					</tr>
					<tr>
						<td colspan="2" class="submit">
							<button type="submit">削除</button>
						</td>
					</tr>
				</tbody>
			</table>
		</form>
	</section>
</body>
</html>