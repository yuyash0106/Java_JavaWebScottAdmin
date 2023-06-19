<%--
JavaWebScottAdmin Practice Src13

従業員情報削除確認画面。

ファイル名=confirmEmpDelete.jsp
ディレクトリ=/javawebscottadmin/emp/
 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="local.hal.night.javawebscottadmin.entity.*"%>
<%
	Emp emp = (Emp) request.getAttribute("emp");
%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>従業員情報削除｜JavaWebScottAdmin Practice</title>
<link rel="stylesheet" href="/javawebscottadmin/css/main.css"
	type="text/css">
</head>
<body>
	<h1>従業員情報削除</h1>
	<nav>
		<ul>
			<li><a href="/javawebscottadmin/">TOP</a></li>
			<li><a href="/javawebscottadmin/emp/showList">従業員リスト</a></li>
			<li>従業員情報削除従業員情報削除</li>
		</ul>
	</nav>
	<section>
		<p>
			以下の従業員情報を削除します。<br> よろしければ、削除ボタンをクリックしてください。
		</p>
		<form action="/javawebscottadmin/emp/delete" method="post">
			<table>
				<tbody>
					<tr>
						<th>従業員番号</th>
						<td><%=emp.getEmpno()%>
						<input type="hidden" id="deleteEmpEmpno" name="deleteEmpEmpno" value="<%=emp.getEmpno()%>">
						</td>
					</tr>
					<tr>
						<th>従業員名</th>
						<td><%=emp.getEname()%></td>
					</tr>
					<tr>
						<th>役職</th>
						<td><%=emp.getJob()%></td>
					</tr>
					<tr>
						<th>上司番号</th>
						<td><%=emp.getMgr()%></td>
					</tr>
					<tr>
						<th>雇用日</th>
						<td><%=emp.getHiredate()%></td>
					</tr>
					<tr>
						<th>給与</th>
						<td><%=emp.getSal()%></td>
					</tr>
					<tr>
						<th>歩合</th>
						<td><%=emp.getComm()%></td>
					</tr>
					<tr>
						<th>部門番号</th>
						<td><%=emp.getDeptno()%></td>
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