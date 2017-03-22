<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>

<html>
 <head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>发布新投票</title>
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
	_a.appendChild(document.createTextNode("删除"));
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
			document.getElementById("d1").innerHTML="<span style='color:red'>内容不能为空<span>";
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
					document.getElementById("d1").innerHTML="<span style='color:red'>内容不能为空！<span>";
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
					document.getElementById("d1").innerHTML="<span style='color:red'>内容重复！<span>";
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
<div id="voteManage" class="box">
	<h2>添加新投票</h2>
	<div class="content">
	<form method="post" action="DealServlet" name="mm" onsubmit="return check();">
		<input type="hidden" name="meth" value="add">
			<dl>
				<div id="d1" style="display:inline"></div>
				<dt>投票内容：</dt>
				<dd>
				   <input type="hidden" name="entityId"/>
				   <input type="text" class="input-text" name="title" onkeyup="checkName();"/>
				</dd>
				<dt>投票类型：</dt>
				<dd>
		  		   <input type="radio" name="subject.type" value="单选" checked="checked"/>单选
				   <input type="radio" name="subject.type" value="多选"/>多选
				</dd>
				<dt>投票时间：</dt>
				<dd>
		  		   <select name="time">
		  		   		<option value="一小时" selected="selected">一小时</option>
		  		   		<option value="一天">一天</option>
		  		   		<option value="一周">一周</option>
		  		   </select>
				</dd>
				<dt>投票选项：</dt>
				<dd id="voteoptions">
					<p><input type="text" class="input-text" name="options"  onkeyup="checkOption()" onblur="checkreput()"/></p>
					<p><input type="text" class="input-text" name="options" onkeyup="checkOption()" onblur="checkreput()"/></p>
				</dd>
				<dd class="button">
					<input type="image" src="images/button_submit.gif"/>
					<a href="javascript:;" onclick="AddOption()">增加选项</a>
					<a href="index.jsp">取消操作</a>
				</dd>
			</dl>
		</form>
	</div>
</div>
<div id="footer" class="wrap">
	网上投票系统
</div>
</body>
</html>
