<%--
JavaWebScottAdmin Practice Src 05

従業員リスト画面。

ファイル名=empList.jsp
ディレクトリ=/javawebscottadmin/emp
 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="local.hal.night.javawebscottadmin.entity.*"%>
<%
	Map<Integer, Emp> resultList = (Map<Integer, Emp>) request.getAttribute("resultList");
%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>従業員リスト｜JavawebScottAdmin Practice</title>
<link rel="stylesheet" href="/javawebscottadmin/css/main.css"
	type="text/css">
</head>
<body>
	<h1>従業員リスト</h1>
	<nav>
		<ul>
			<li><a href="/javawebscottadmin/">TOP</a></li>
			<li>従業員リスト</li>
		</ul>
	</nav>
	<section>
		<p>
			新規登録は<a href="/javawebscottadmin/emp/goEmpAdd">こちら</a>から
		</p>
	</section>
	<section>
		<table>
			<thead>
				<tr>
					<th>従業員番号</th>
					<th>従業員名</th>
					<th>役職</th>
					<th>上司番号</th>
					<th>雇用日</th>
					<th>給与</th>
					<th>歩合</th>
					<th>部門番号</th>
					<th colspan="2">操作</th>
				</tr>
			</thead>
			<tbody>
			<%
			if(resultList.isEmpty()){
			%>
				<tr>
					<td colspan="5">該当従業員は存在しません。</td>
				</tr>
				<%
			}
				else{
					for(Map.Entry<Integer,Emp> entry:resultList.entrySet()){
						Integer empno = entry.getKey();
						Emp emp = entry.getValue();
				%>
				<tr>
					<td><%=empno %></td>
					<td><%=emp.getEname() %></td>
					<td><%=emp.getJob() %></td>
					<td><%=emp.getMgr() %></td>
					<td><%=emp.getHiredate() %></td>
					<td><%=emp.getSal() %></td>
					<td><%=emp.getComm() %></td>
					<td><%=emp.getDeptno() %></td>
					<td>
						<form action="/javawebscottadmin/emp/prepareEdit" method="post">
							<input type="hidden" id="editEmpEmpno" name="editEmpEmpno"
								value="<%=empno%>">
							<button type="submit">編集</button>
						</form>
					</td>
					<td>
						<form action="/javawebscottadmin/emp/confirmDelete" method="post">
							<input type="hidden" id="deleteEmpEmpno" name="deleteEmpEmpno"
								value="<%=empno%>">
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