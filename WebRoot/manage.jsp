<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<script type="text/javascript">
	function fun(){
		location.href="DealServlet?meth=removeSession";
	}
	function ddelete(vs_id){
		if(confirm("确定删除吗？")){
			location.href="DealServlet?meth=delete&vs_id="+vs_id+"&name=${name}";
		}
	}
</script>
  <head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>管理投票</title>
<link type="text/css" rel="stylesheet" href="css/style.css" />
</head>
<body>

<div id="navbar" class="wrap">
	<div class="profile">
		您好，${name}
		<span class="return"><a href="index.jsp">返回列表</a></span>
		<span class="addnew"><a href="add.jsp">添加新投票</a></span>
		<span class="modify"><a href="DealServlet?meth=manages&name=${name}">维护</a></span>
		<span class="return"><a href="javascript:fun();">安全退出</a></span>
		
	</div>
	<div class="search">
		<form method="post" action="DealServlet">
			<input type="hidden" name="meth" value="search">
			<input type="text" name="keywords" class="input-text" value="${keywords}"/>
			<input type="submit" name="submit" class="input-button" value="" />
		</form>
	</div>
</div>
<div id="vote" class="wrap">
	<h2>投票列表</h2>
	<ul class="list">
		<c:forEach var="li" items="${list}">
			<li  class="odd">
				<h4>
					<em><a href="DealServlet?meth=edit&title=${li.vs_titles}&vs_id=${li.vs_id}">维护</a></em><br>
					<em><a href="javascript:ddelete(${li.vs_id});">删除</a></em>
					<a href="DealServlet?meth=edit&title=${li.vs_titles}&vs_id=${li.vs_id}">${li.vs_titles}</a>
				</h4>
				<p class="info">共有 ${li.count} 个选项，已有 ${li.pcount}个网友参与了投票。</p>
			</li>
		</c:forEach>
	</ul>
</div>
<div id="footer" class="wrap">
	网上投票系统
</div>
</body>
</html>
