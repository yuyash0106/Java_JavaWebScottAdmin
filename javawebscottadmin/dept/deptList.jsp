<%--
JavaWebScottAdmin Lesson Src05

部門情報リスト画面。

ファイル名=deptList.jsp
ディレクトリ=/javawebscottadmin/dept/
 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="local.hal.night.javawebscottadmin.entity.*"%>
<%
	Map<Integer, Dept> resultList = (Map<Integer, Dept>) request.getAttribute("resultList");
%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>部門情報リスト|JavaWebScottAdmin Lesson</title>
<link rel="stylesheet" href="/javawebscottadmin/css/main.css"
	type="text/css">
</head>
<body>
	<h1>部門情報リスト</h1>
	<nav>
		<ul>
			<li><a href="/javawebscottadmin/">TOP</a></li>
			<li>部門情報リスト</li>
		</ul>
	</nav>
	<section>
		<p>
			新規登録は<a href="/javawebscottadmin/dept/goDeptAdd">こちら</a>から
		</p>
	</section>
	<section>
		<table>
			<thead>
				<tr>
					<th>部門番号</th>
					<th>部門名</th>
					<th>所在地</th>
					<th colspan="2">操作</th>
				</tr>
			</thead>
			<tbody>
				<%
					if (resultList.isEmpty()) {
				%>
				<tr>
					<td colspan="5">該当部門が存在しません。</td>
				</tr>
				<%
					} else {
						for (Map.Entry<Integer, Dept> entry : resultList.entrySet()) {
							Integer deptno = entry.getKey();
							Dept dept = entry.getValue();
				%>
				<tr>
					<td><%=deptno%></td>
					<td><%=dept.getDname()%></td>
					<td><%=dept.getLoc()%></td>
					<td>
						<form action="/javawebscottadmin/dept/prepareEdit" method="post">
							<input type="hidden" id="editDeptDeptno" name="editDeptDeptno"
								value="<%=deptno%>">
							<button type="submit">編集</button>
						</form>
					</td>
					<td>
						<form action="/javawebscottadmin/dept/confirmDelete" method="post">
							<input type="hidden" id="deleteDeptDeptno"
								name="deleteDeptDeptno" value="<%=deptno%>">
							<button type="submit">削除</button>
						</form>
					</td>
				</tr>
				<%
					}
					}
				%>
			</tbody>
		</table>
	</section>
</body>
</html>