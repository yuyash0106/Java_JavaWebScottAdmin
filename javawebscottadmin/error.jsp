<%--
JavaWebScottAdmin Lesson Src02

エラー画面

ファイル名=error.jsp
ディレクトリ=/javawebscottadmin/
 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html lang="ja">
<html>
<head>
<meta charset="UTF-8">
<title>Error|JavaWebScottAdmin Lesson</title>
</head>
<body>
	<h1>Error</h1>
	<section>
		<h2>申し訳ございません。障害が発生しました。</h2>
		<p>
			以下のメッセージをご確認ください。<br>
			<%=request.getAttribute("errorMsg")%>
		</p>
	</section>
	<p>
		<a href="/javawebscottadmin/">TOPへ戻る</a>
	</p>
</body>
</html>