<%--
javaWebScottAdmin Practice Src 07

従業員情報追加画面。

ファイル名=empAdd.jsp
ディレクトリ=/javawebscottadmin/emp/
 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%
	String addEmpEmpno = (String) request.getAttribute("addEmpEmpno");
	String addEmpEname = (String) request.getAttribute("addEmpEname");
	String addEmpJob = (String) request.getAttribute("addEmpJob");
	String addEmpMgr = (String) request.getAttribute("addEmpMgr");
	String addEmpHiredate = (String) request.getAttribute("addEmpHiredate");
	String addEmpSal = (String) request.getAttribute("addEmpSal");
	String addEmpComm = (String) request.getAttribute("addEmpComm");
	String addEmpDeptno = (String) request.getAttribute("addempDeptno");

	if (addEmpEmpno == null) {
		addEmpEmpno = "";
	}
	if (addEmpEname == null) {
		addEmpEname = "";
	}
	if (addEmpJob == null) {
		addEmpJob = "";
	}
	if (addEmpMgr == null) {
		addEmpMgr = "";
	}
	if (addEmpHiredate == null) {
		addEmpHiredate = "";
	}
	if (addEmpSal == null) {
		addEmpSal = "";
	}
	if (addEmpComm == null) {
		addEmpComm = "";
	}
	if (addEmpDeptno == null) {
		addEmpDeptno = "";
	}

	List<String> validationMsgs = (List<String>) request.getAttribute("validationMsgs");
%>

<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>従業員情報追加｜JavaWebScottAdmin Practice</title>
<link rel="stylesheet" href="/javawebscottadmin/css/main.css"
	type="text/css">
</head>
<body>
	<h1>従業員情報追加</h1>
	<nav>
		<ul>
			<li><a href="/javawebscottadmin/">TOP</a></li>
			<li><a href="/javawebscottadmin/emp/showList">従業員リスト</a></li>
			<li>従業員情報追加</li>
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
		<form action="/javawebscottadmin/emp/add" method="post">
			<table>
				<tr>
					<th>従業員番号&nbsp;<span class="required">必須</span></th>
					<td><input type="text" id="addEmpEmpno" name="addEmpEmpno"
						value="<%=addEmpEmpno%>"></td>
				</tr>
				<tr>
					<th>従業員名&nbsp;<span class="required">必須</span></th>
					<td><input type="text" id="addEmpEname" name="addEmpEname"
						value="<%=addEmpEname%>"></td>
				</tr>
				<tr>
					<th>役職&nbsp;<span class="required">必須</span></th>
					<td><input type="text" id="addEmpJob" name="addEmpJob"
						value="<%=addEmpJob%>"></td>
				</tr>
				<tr>
					<th>上司番号&nbsp;<span class="required">必須</span></th>
					<td><input type="text" id="addEmpMgr" name="addEmpMgr"
						value="<%=addEmpMgr%>"></td>
				</tr>
				<tr>
					<th>雇用日&nbsp;<span class="required">必須</span></th>
					<td><input type="text" id="addEmpHiredate"
						name="addEmpHiredate" value="<%=addEmpHiredate%>"></td>
				</tr>
				<tr>
					<th>給与&nbsp;<span class="required">必須</span></th>
					<td><input type="text" id="addEmpSal" name="addEmpSal"
						value="<%=addEmpSal%>"></td>
				</tr>
				<tr>
					<th>歩合&nbsp;<span class="required">必須</span></th>
					<td><input type="text" id="addEmpComm" name="addEmpComm"
						value="<%=addEmpComm%>"></td>
				</tr>
				<tr>
					<th>部門番号&nbsp;<span class="required">必須</span></th>
					<td><input type="text" id="addEmpDeptno" name="addEmpDeptno"
						value="<%=addEmpDeptno%>"></td>
				</tr>
				<tr>
					<td colspan="2" class="submit">
						<button type="submit">登録</button>
					</td>
				</tr>
			</table>
		</form>
	</section>
</body>
</html>