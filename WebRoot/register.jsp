<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<script type='text/javascript' src='/OnlineVote/dwr/interface/serch.js'></script>
  <script type='text/javascript' src='/OnlineVote/dwr/engine.js'></script>
  <script type='text/javascript' src='/OnlineVote/dwr/util.js'></script>
  

<html>
<script type="text/javascript">
	function checkName(){
		var flag=true;
		var name=mm.userName.value;
		var pwd=mm.password.value;
		if(name==""){
			document.getElementById("d1").innerHTML="<span style='color:red'>�û�������Ϊ��<span>";
			flag=false;
		}else{
			for(var i=0;i<name.length;i++){
				var c=name.charAt(i);
				if(c>=0){
					document.getElementById("d1").innerHTML="<span style='color:red'>�û����в��ܺ�������<span>";
					flag=false;
					break;
				}
			}
		}
		if(flag){
			document.getElementById("d1").innerHTML="";
			flag=true;
		}
		return flag;
	}
	function checkPwd(){
		var pwd=mm.password.value;
		if(pwd.length<6){
			document.getElementById("d2").innerHTML="<span style='color:red'>���벻��С��6λ��<span>";
			return false;
		}else{
			document.getElementById("d2").innerHTML="";
			return true;
		}
	}
	function checkPwd2(){
		var flag=true;
		var pwd=mm.confirmPassword.value;
		if(pwd!=mm.password.value){
			document.getElementById("d3").innerHTML="<span style='color:red'>���벻��ͬ�����������룡<span>";
			flag=false;
		}
		if(pwd==mm.password.value){
			document.getElementById("d3").innerHTML="";
			flag=true;
		}
		return flag;
	}
	function check(){
		if(checkName()&&checkPwd()&&checkPwd2()){
			return true;
		}else{
			return false;
		}
	}
	function fun(name){
		serch.serchName(name,haolejiaowo);
	}
	function haolejiaowo(data){
		var flag=true;
	 	if(data==""){
	 		flag=true;
	 	}else{
			document.getElementById("d1").innerHTML=data;
			flag=false;
		}
		return flag;
	}
</script>
 <head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>ע   ��</title>
<link type="text/css" rel="stylesheet" href="css/style.css" />
</head>
<body>

<div id="navbar" class="wrap">
	<div class="search">
	<!--  
		<form method="get" action="index.html">
			<input type="text" name="keywords" class="input-text" /><input type="submit" name="submit" class="input-button" value="" />
		</form>
	-->	
	</div>
</div>
<div id="register" class="box">
	<h2>���û�ע��</h2>
	<div class="content">
	   <form method="post" action="DealServlet" name="mm" onsubmit="return check();">
	   		<input type="hidden" name="meth" value="register">
			<dl>
				<dt>�û�����</dt>
				<dd><input type="text" class="input-text" name="userName" onkeyup="checkName(),fun(this.value);" onkeydown=""/><div id="d1" style="display:inline"></div></dd>
				<dt>���룺</dt>
				<dd><input type="password" class="input-text" name="password" onkeyup="checkPwd();"/><div id="d2" style="display:inline"></div></dd>
				<dt>ȷ�����룺</dt>
				<dd><input type="password" class="input-text" name="confirmPassword" onkeyup="checkPwd2();"/><div id="d3" style="display:inline"></div></dd>
				<dt></dt>
				<dd><input type="submit" class="input-button" name="submit" value="" /></dd>
			</dl>
		</form>
		<div class="error"></div>
	</div>
</div>
<div id="footer" class="wrap">
	����ͶƱϵͳ
</div>
</body>
</html>
