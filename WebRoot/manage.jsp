<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<script type="text/javascript">
	function fun(){
		location.href="DealServlet?meth=removeSession";
	}
	function ddelete(vs_id){
		if(confirm("ȷ��ɾ����")){
			location.href="DealServlet?meth=delete&vs_id="+vs_id+"&name=${name}";
		}
	}
</script>
  <head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>����ͶƱ</title>
<link type="text/css" rel="stylesheet" href="css/style.css" />
</head>
<body>

<div id="navbar" class="wrap">
	<div class="profile">
		���ã�${name}
		<span class="return"><a href="index.jsp">�����б�</a></span>
		<span class="addnew"><a href="add.jsp">�����ͶƱ</a></span>
		<span class="modify"><a href="DealServlet?meth=manages&name=${name}">ά��</a></span>
		<span class="return"><a href="javascript:fun();">��ȫ�˳�</a></span>
		
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
	<h2>ͶƱ�б�</h2>
	<ul class="list">
		<c:forEach var="li" items="${list}">
			<li  class="odd">
				<h4>
					<em><a href="DealServlet?meth=edit&title=${li.vs_titles}&vs_id=${li.vs_id}">ά��</a></em><br>
					<em><a href="javascript:ddelete(${li.vs_id});">ɾ��</a></em>
					<a href="DealServlet?meth=edit&title=${li.vs_titles}&vs_id=${li.vs_id}">${li.vs_titles}</a>
				</h4>
				<p class="info">���� ${li.count} ��ѡ����� ${li.pcount}�����Ѳ�����ͶƱ��</p>
			</li>
		</c:forEach>
	</ul>
</div>
<div id="footer" class="wrap">
	����ͶƱϵͳ
</div>
</body>
</html>
