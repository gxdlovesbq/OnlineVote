<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<script type="text/javascript">
	function fun(){
		location.href="DealServlet?meth=removeSession";
	}
</script>
  <head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
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
	<h2>�鿴ͶƱ</h2>
	<ul class="list">
		<li>
			<c:forEach var="li" items="${list}">
				<h4>${li.vs_titles}</h4>
				<p class="info">���� ${li.count} ��ѡ����� ${li.pcount} �����Ѳ�����ͶƱ��</p>
			</c:forEach>
				<ol>
					<c:forEach var="li" items="${list1}">
						<li>${li.vo_option}
							<div class="rate">
								<div class="ratebg"><div class="percent" style="width:<f:formatNumber value="${li.vcount==0?0.00:li.vcount*100/total}" pattern="#0.00"></f:formatNumber>%"></div></div>
								<p>${li.vcount}Ʊ<span>(<f:formatNumber value="${li.vcount==0?0.00:li.vcount*100/total}" pattern="#0.00"></f:formatNumber>%)</span></p>
							</div>
						</li>
					</c:forEach>
				</ol>
				<div class="goback"><a href="javascript:history.back();">����ͶƱ�б�</a></div>
		</li>
	</ul>
</div>
<div id="footer" class="wrap">
	����ͶƱϵͳ
</div>
</body>
</html>
