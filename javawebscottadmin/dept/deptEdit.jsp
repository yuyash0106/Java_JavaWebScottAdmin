<%--
JavaWebScottAdmin Lesson Src10

部門情報編集画面。

ファイル名=deptEdit.jsp
ディレクトリ=/javawebscottadmin/dept/
 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="local.hal.night.javawebscottadmin.entity.*"%>
<%
	Dept dept = (Dept) request.getAttribute("dept");

	List<String> validationMsgs = (List<String>) request.getAttribute("validationMsgs");
%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>部門情報編集｜JavaWebScottAdmin Lesson</title>
<link rel="stylesheet" href="/javawebscottadmin/css/main.css"
	type="text/css">
</head>
<body>
	<h1>部門情報編集</h1>
	<nav>
		<ul>
			<li><a href="javawebscottadmin/">TOP</a></li>
			<li><a href="javawebscottadmin/dept/showList">部門リスト</a></li>
			<li>部門情報編集</li>
		</ul>
	</nav>
	<%
		if (validationMsgs != null) {
	%>
	<section id="errorMsg">
		<p>以下のメッセージをご確認ください。</p>
		<ul>
			<%
				for (String msg : validationMsgs) {
			%>
			<li><%=msg%></li>
			<%
				}
			%>
		</ul>
	</section>
	<%
		}
	%>
	<section>
		<p>情報を入力し、更新ボタンをクリックしてください。</p>
		<form action="/javawebscottadmin/dept/edit" method="post">
			<table>
				<tbody>
					<tr>
						<th>部門番号</th>
						<td><%=dept.getDeptno()%> <input type="hidden"
							id="editDeptDeptno" name="editDeptDeptno"
							value="<%=dept.getDeptno()%>"></td>
					</tr>
					<tr>
						<th>部門名&nbsp;<span class="required">必須</span></th>
						<td><input type="text" id="editDeptDname"
							name="editDeptDname" value="<%=dept.getDname()%>"></td>
					</tr>
					<tr>
						<th>所在地</th>
						<td><input type="text" id="editDeptLoc" name="editDeptLoc"
							value="<%=dept.getLoc()%>"></td>
					</tr>
					<tr>
						<td colspan="2" class="submit">
							<button type="submit">更新</button>
						</td>
					</tr>
				</tbody>
			</table>
		</form>
	</section>
</body>
</html>