<%--
JavaWebScottAdmin Lesson Src07

部門情報追加画面。

ファイル名=deptAdd.jsp
ディレクトリ=/javawebscottadmin/dept/
 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%
	String addDeptDeptno = (String) request.getAttribute("addDeptDeptno");
	String addDeptDname = (String) request.getAttribute("addDeptDname");
	String addDeptLoc = (String) request.getAttribute("addDeptLoc");

	if (addDeptDeptno == null) {
		addDeptDeptno = "";
	}
	if (addDeptDname == null) {
		addDeptDname = "";
	}
	if (addDeptLoc == null) {
		addDeptLoc = "";
	}
	List<String> validationMsgs = (List<String>) request.getAttribute("validationMsgs");
%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>部門情報追加画面|JavaWebScottAdmin Lesson</title>
<link rel="stylesheet" href="/javawebscottadmin/css/main.css"
	type="text/css">
</head>
<body>
	<h1>部門情報追加</h1>
	<nav>
		<ul>
			<li><a href="/javawebscottadmin/">TOP</a></li>
			<li><a href="/javawebscottadmin/dept/showList">部門リスト</a></li>
			<li>部門情報追加</li>
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
		<p>情報を入力し、登録ボタンをクリックしてください。</p>
		<form action="/javawebscottadmin/dept/add" method="post">
			<table>
				<tbody>
					<tr>
						<th>部門番号&nbsp;<span class="required">必須</span></th>
						<td><input type="text" id="addDeptDeptno"
							name="addDeptDeptno" value="<%=addDeptDeptno%>"></td>
					</tr>
					<tr>
						<th>部門名&nbsp;<span class="required">必須</span></th>
						<td><input type="text" id="addDeptDname" name="addDeptDname"
							value="<%=addDeptDname%>"></td>
					</tr>
					<tr>
						<th>所在地</th>
						<td><input type="text" id="addDeptLoc" name="addDeptLoc"
							value="<%=addDeptLoc%>"></td>
					</tr>
					<tr>
						<td colspan="2" class="submit">
							<button type="submit">登録</button>
						</td>
					</tr>
				</tbody>
			</table>
		</form>
	</section>
</body>
</html>