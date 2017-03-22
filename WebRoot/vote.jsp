<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<script type="text/javascript">
	function fun(){
		location.href="DealServlet?meth=removeSession";
	}
	function myTime(){
		var today=new Date();
		document.getElementById("d1").innerText=today.getYear()+"/"+today.getMonth()+"/"+today.getDate()+"-"+today.getHours()+":"+today.getMinutes()+":"+today.getSeconds();
		setTimeout("myTime()","1000");
	}
</script>
 <head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>����ͶƱ</title> 
<link type="text/css" rel="stylesheet" href="css/style.css" />
</head>
<body onload="myTime()">

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
	<h2>����ͶƱ</h2><div id="d1" style="display:inline"></div>
	<ul class="list">
		<li>
			<h4>${titles}</h4>
			<p class="info">���� ${count} ��ѡ����� ${pcount} �����Ѳ�����ͶƱ��</p>
			<form method="post" action="DealServlet?name=${name}&id=${id}">
				   <input type="hidden" name="meth" value="additem">
				    <c:forEach var="li" items="${list1}"> 
					<ol>
						<c:if test="${type==1}">
							<li><input type="radio" name="options"  value="${li.vo_id}"/>${li.vo_option}</li>
						</c:if>
						<c:if test="${type==2}">
						<li><input type="checkbox" name="options"  value="${li.vo_id}"/>${li.vo_option}</li>
						</c:if>
					</ol>
					</c:forEach>
				<p class="voteView"><input type="image" src="images/button_vote.gif"/><a href="DealServlet?meth=selectview&id=${id}&entityId=<s:property value='subject.id'/>"><img src="images/button_view.gif" /></a></p>
			</form>
		</li>
	</ul>
</div>
<div id="footer" class="wrap">
	����ͶƱϵͳ
</div>
</body>
</html>
