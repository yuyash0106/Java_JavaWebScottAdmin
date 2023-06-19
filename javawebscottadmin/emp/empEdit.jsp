<%--
JavaWebScottAdmin Practice Src10

従業員情報編集画面。

ファイル名=empEdit.jsp
ディレクトリ=/javawebscottadmin/emp
 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="java.sql.Date" %>
<%@ page import="local.hal.night.javawebscottadmin.entity.*"%>
<%
	Emp emp = (Emp) request.getAttribute("emp");

	List<String> validationMsgs = (List<String>) request.getAttribute("validationMsgs");
%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>従業員情報編集｜JavaWebScottAdmin Practice</title>
<link rel="stylesheet" href="/javawebscottadmin/css/main.css"
	type="text/css">
</head>
<body>
	<h1>従業員情報編集</h1>
	<nav>
		<ul>
			<li><a href="/javawebscottadmin/">TOP</a></li>
			<li><a href="/javawebscottadmin/emp/showList">従業員リスト</a></li>
			<li>従業員情報編集</li>
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
		<form action="/javawebscottadmin/emp/edit" method="post">
			<table>
				<tbody>
					<tr>
						<th>従業員番号&nbsp;<span class="required">必須</span></th>
						<td><%=emp.getEmpno() %><input type="hidden"
							id="editEmpEmpno" name="editEmpEmpno" value="<%=emp.getEmpno()%>">
						</td>
					</tr>
					<tr>
						<th>従業員名&nbsp;<span class="required">必須</span></th>
						<td> <input type="text" id="editEmpEname"
							name="editEmpEname" value="<%=emp.getEname()%>"></td>
					</tr>
					<tr>
						<th>役職&nbsp;<span class="required">必須</span></th>
						<td><input type="text" id="editEmpJob"
							name="editEmpJob" value="<%=emp.getJob()%>"></td>
					</tr>
					<tr>
						<th>上司番号&nbsp;<span class="required">必須</span></th>
						<td> <input type="text" id="editEmpMgr"
							name="editEmpMgr" value="<%=emp.getMgr()%>"></td>
					</tr>
					<tr>
						<th>雇用日&nbsp;<span class="required">必須</span></th>
						<td> <input type="text"
							id="editEmpHiredate" name="editEmpHiredate"
							value="<%=emp.getHiredate()%>"></td>
					</tr>
					<tr>
						<th>給与&nbsp;<span class="required">必須</span></th>
						<td> <input type="text" id="editEmpSal"
							name="editEmpSal" value="<%=emp.getSal()%>"></td>
					</tr>
					<tr>
						<th>歩合&nbsp;<span class="required">必須</span></th>
						<td><input type="text" id="editEmpComm"
							name="editEmpComm" value="<%=emp.getComm()%>"></td>
					</tr>
					<tr>
						<th>部門員番号&nbsp;<span class="required">必須</span></th>
						<td> <input type="text"
							id="editEmpDeptno" name="editEmpDeptno"
							value="<%=emp.getDeptno()%>"></td>
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