<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>

<html>
 <head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>������ͶƱ</title>
<link type="text/css" rel="stylesheet" href="css/style.css" />
<script type="text/javascript">
var isIE = !!document.all;
function AddOption()
{
	var voteoptions = document.getElementById("voteoptions");
	var _p = document.createElement("p");
	var _input = document.createElement("input");
	_input.type = "text";
	_input.className = "input-text";
	_input.setAttribute("name", "options");
	_p.appendChild(_input);
	var _a = document.createElement("a");
	_a.className = "del";
	_a.setAttribute("href", "javascript:;");
	if(isIE) {
		_a.attachEvent("onclick", DelOption);
	} else {
		_a.addEventListener("click", DelOption, false);
	}
	_a.appendChild(document.createTextNode("ɾ��"));
	_p.appendChild(_a);
	voteoptions.appendChild(_p);
}
function DelOption(e)
{
	if(!e) e = window.event;
	var a = e.srcElement || e.target;
	var obj = a.parentNode;
	obj.parentNode.removeChild(obj);
}
function fun(){
		location.href="DealServlet?meth=removeSession";
	}
	
	
	function checkName(){
		var flag=true;
		var title=mm.title.value;
		if(title==""){
			document.getElementById("d1").innerHTML="<span style='color:red'>���ݲ���Ϊ��<span>";
			flag=false;
		}
		if(flag){
			document.getElementById("d1").innerHTML="";
		}
		return flag;
	}
	function checkOption(){
		var flag=true;
		var options=document.getElementsByName("options");
		for ( var i = 0; i < options.length; i++) {
				if(options[i].value==""){
					document.getElementById("d1").innerHTML="<span style='color:red'>���ݲ���Ϊ�գ�<span>";
					flag=false;
					break;
				}else{
					document.getElementById("d1").innerHTML="";
					flag=true;
				}
		}
		
		return flag;
	}
	function checkreput(){
		var flag=true;
		var options=document.getElementsByName("options");
		for ( var i = 0; i < options.length; i++) {
			for ( var j =0; j <options.length;j++) {
				if(options[i].value==options[j].value && i!=j){
					document.getElementById("d1").innerHTML="<span style='color:red'>�����ظ���<span>";
					flag=false;
				}
			}
		}
		return flag;
	}
	function check(){
		if(checkName()&&checkOption()&&checkreput()){
			return true;
		}else{
			return false;
		}
	}
</script>
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
<div id="voteManage" class="box">
	<h2>�����ͶƱ</h2>
	<div class="content">
	<form method="post" action="DealServlet" name="mm" onsubmit="return check();">
		<input type="hidden" name="meth" value="add">
			<dl>
				<div id="d1" style="display:inline"></div>
				<dt>ͶƱ���ݣ�</dt>
				<dd>
				   <input type="hidden" name="entityId"/>
				   <input type="text" class="input-text" name="title" onkeyup="checkName();"/>
				</dd>
				<dt>ͶƱ���ͣ�</dt>
				<dd>
		  		   <input type="radio" name="subject.type" value="��ѡ" checked="checked"/>��ѡ
				   <input type="radio" name="subject.type" value="��ѡ"/>��ѡ
				</dd>
				<dt>ͶƱʱ�䣺</dt>
				<dd>
		  		   <select name="time">
		  		   		<option value="һСʱ" selected="selected">һСʱ</option>
		  		   		<option value="һ��">һ��</option>
		  		   		<option value="һ��">һ��</option>
		  		   </select>
				</dd>
				<dt>ͶƱѡ�</dt>
				<dd id="voteoptions">
					<p><input type="text" class="input-text" name="options"  onkeyup="checkOption()" onblur="checkreput()"/></p>
					<p><input type="text" class="input-text" name="options" onkeyup="checkOption()" onblur="checkreput()"/></p>
				</dd>
				<dd class="button">
					<input type="image" src="images/button_submit.gif"/>
					<a href="javascript:;" onclick="AddOption()">����ѡ��</a>
					<a href="index.jsp">ȡ������</a>
				</dd>
			</dl>
		</form>
	</div>
</div>
<div id="footer" class="wrap">
	����ͶƱϵͳ
</div>
</body>
</html>
