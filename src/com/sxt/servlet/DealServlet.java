package com.sxt.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sxt.bean.OptionBean;
import com.sxt.bean.OptionBean1;
import com.sxt.bean.Vote_option;
import com.sxt.bean.Vote_option1;
import com.sxt.bean.Vote_subject;
import com.sxt.bean.Vote_subject1;
import com.sxt.bean.Vote_subject2;
import com.sxt.bean.Vote_subjectBean;
import com.sxt.bean.Vote_user;
import com.sxt.bean.Vote_user2;
import com.sxt.dao.BaseDao;
import com.sxt.dao.Vote_userDao;

public class DealServlet extends ConstructorServlet {

	/**
	 * Constructor of the object.
	 */
	public DealServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String msg="";
		String name=request.getParameter("name");
		String pwd=request.getParameter("password");
		Vote_userDao vd=new Vote_userDao();
		int flag=vd.login(name, pwd);
		if(flag==1){
			HttpSession session=request.getSession();
			session.setAttribute("name", name);
			response.sendRedirect("index.jsp");
		}else if(flag==-1){
			msg="密码错误！";
			request.setAttribute("msg", msg);
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}else{
			msg="用户名错误！";
			request.setAttribute("msg", msg);
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
	}
	
	public void register(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name=request.getParameter("userName");
		String password1=request.getParameter("password");
		String password2=request.getParameter("confirmPassword");
		Vote_user vu=new Vote_user(name,password1);
		Vote_userDao vd=new Vote_userDao();
			if(vd.inster(vu)>=0){
				String msg="恭喜：注册成功！请重新登录";
				request.setAttribute("msg",msg);
				request.getRequestDispatcher("reg_success.jsp").forward(request, response);
			}else{
				response.sendRedirect("register.jsp");
			}
	}
	
	public void select1(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id=0;
		int type=0;
		String nowtime="";
		String endtime="";
		String titles=request.getParameter("titles");
		Vote_userDao vd=new Vote_userDao();
		List<Vote_subject1> list=vd.select1(titles);
		for (Vote_subject1 v : list) {
			id=v.getVs_id();
			type=v.getVs_type();
			nowtime=v.getNowtime();
			endtime=v.getEndtime();
		}
		System.out.println(endtime);
		List<Vote_subject1> list1=vd.select2(id);
		String count=request.getParameter("count");
		String pcount=request.getParameter("pcount");
		String name=request.getParameter("name");
		request.setAttribute("nowtime", nowtime);
		request.setAttribute("endtime", endtime);
		request.setAttribute("id", id);
		request.setAttribute("count", count);
		request.setAttribute("pcount", pcount);
		request.setAttribute("name", name);
		request.setAttribute("type", type);
		request.setAttribute("list1", list1);
		request.setAttribute("titles", titles);
		request.getRequestDispatcher("vote.jsp").forward(request, response);
		}
	public void add(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int type=0;
		int id=0;
		String title=request.getParameter("title");
		String tp=request.getParameter("subject.type");
		String time=request.getParameter("time");
		Date date=new Date();
		DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//得到系统的时间
		String nowtime=format.format(date);
		//结束时间
		String endtime="";

		if("一小时".equals(time)){
			endtime=format.format(date.getTime()+(60*60*1000));
		}else if("一天".equals(time)){
			endtime=format.format(date.getTime()+(24*60*60*1000));
		}else{
			endtime=format.format(date.getTime()+(7*24*60*60*1000));
		}
		
		if("单选".equals(tp)){
			type=1;
		}else{
			type=2;
		}
		String[]options=request.getParameterValues("options");
		BaseDao bd=new BaseDao();
		//根据用户输入信息需要分表添加，先给vote_subject表添加数据
		Vote_userDao vd=new Vote_userDao();
		int t=vd.inster1(title, type,nowtime,endtime);
		//根据用户输入的title查出对应的id，添加到vote_option表
		List<Vote_subject2> list=vd.select3(title);
		for (Vote_subject2 v : list) {
			id=v.getVs_id();
		}
		int in=vd.update(options, id);
		if(t>=0&&in>=0){
			String msg="恭喜：操作成功！";
			request.setAttribute("msg",msg);
			request.getRequestDispatcher("reg_success.jsp").forward(request, response);
		}else{
			response.sendRedirect("add.jsp");
		}
	}
	public void selectview(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		long total=0;
		String vs_id=request.getParameter("id");
		Vote_userDao vd=new Vote_userDao();
		List<OptionBean1>list=vd.getselect1(vs_id);
		for (OptionBean1 o : list) {
			total=o.getVcount();
		}
		List<OptionBean>list1=vd.getview(vs_id);
		request.setAttribute("list", list);
		request.setAttribute("list1", list1);
		request.setAttribute("total", total);
		request.getRequestDispatcher("view.jsp").forward(request, response);
	}
	public void manages(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		String name=request.getParameter("name");
		Vote_userDao vd=new Vote_userDao();
		int flag=0;
		if(name==""){
			response.sendRedirect("login.jsp");
		}else{ 
			flag=vd.selectFflag(name);
				if(flag==1) {
					List<Vote_subject> list=vd.select();
					request.setAttribute("list", list);
					request.getRequestDispatcher("manage.jsp").forward(request, response);
				}else{
					request.setAttribute("msg", "您不是管理员没有操作权限");
					request.getRequestDispatcher("filter.jsp").forward(request, response);
				}
		}
	}
	public void edit(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		String title=request.getParameter("title");
		String vs_id=request.getParameter("vs_id");
		Vote_userDao vd=new Vote_userDao();
		List<OptionBean>list1=vd.getview(vs_id);
		
		int type=0;
		List<Vote_subject1> list=vd.select1(title);
		for (Vote_subject1 v : list) {
			type=v.getVs_type();
		}
		request.setAttribute("vs_id", vs_id);
		request.setAttribute("type", type);
		request.setAttribute("list1", list1);
		request.setAttribute("title", title);
		request.getRequestDispatcher("editor.jsp").forward(request, response);
	}
	public void editortable(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		int vs_id=Integer.parseInt(request.getParameter("vs_id"));
		String title=request.getParameter("title");
		int type=Integer.parseInt(request.getParameter("type"));
		String[]options=request.getParameterValues("options");
		Vote_userDao vd=new Vote_userDao();
		Vote_subjectBean vsb=new Vote_subjectBean(vs_id,title,type);
		if(vd.selectvoit_item(vs_id)==true){
			request.setAttribute("msg","有用户参与投票不能编辑！" );
			request.getRequestDispatcher("filter.jsp").forward(request, response);
		}else{
			vd.deletevote_option(vs_id);
			vd.updatevote_subject(vsb);
			vd.update(options, vs_id);
			request.setAttribute("msg","编辑成功！" );
			request.getRequestDispatcher("reg_success.jsp").forward(request, response);
		}
	}
	public void search(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		String keywords=request.getParameter("keywords");
		HttpSession session=request.getSession();
		session.setAttribute("keywords", keywords);
		Vote_userDao vd=new Vote_userDao();
		List<Vote_subject> list=vd.search(keywords);
		request.setAttribute("list", list);
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}
	public void additem(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		String[]options=request.getParameterValues("options");
		String name=request.getParameter("name");
		if(name==""){
			request.setAttribute("msg","对不起，您没有登录，无法参与该投票！！");
			request.getRequestDispatcher("filter.jsp").forward(request, response);
		}else if(options==null){
			request.setAttribute("msg","请选择您要投票的选项！！");
			request.getRequestDispatcher("filter.jsp").forward(request, response);
		}else{
			String vs_id=request.getParameter("id");
			String sql="";
			int vu_id=0;
			Vote_userDao vd=new Vote_userDao();
			List<Vote_user2> l=vd.select4(name);
			for (Vote_user2 u : l) {
				vu_id=u.getVu_id();
			}
			if(vd.jjjj(vs_id, vu_id)>0){
				request.setAttribute("msg","对不起，您已经参与过该投票了！！");
				request.getRequestDispatcher("filter.jsp").forward(request, response);
			}else{
				int in=vd.inster2(options, vs_id, vu_id);
				if(in>=0){
					String msg="恭喜：投票成功！";
					request.setAttribute("msg",msg);
					request.getRequestDispatcher("reg_success.jsp").forward(request, response);
				}else{
					response.sendRedirect("vote.jsp");
				}
			}
		}
	}
	public void delete(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		String vs_id=request.getParameter("vs_id");
		String name=request.getParameter("name");
		int vu_id=0;
		Vote_userDao vd=new Vote_userDao();
		List<Vote_user2> l=vd.select4(name);
		for (Vote_user2 u : l) {
			vu_id=u.getVu_id();
		}
		int flag=vd.selectFflag(name);
		if(flag==1) {
			if(vd.jjjj(vs_id, vu_id)>0){
				request.setAttribute("msg","对不起，该选项有用户参与投票，不能删除！！");
				request.getRequestDispatcher("filter.jsp").forward(request, response);
			}else{
				int vvo=vd.deleteoptions(vs_id);
				int vvs=vd.deletevote_subject(vs_id);
				if(vvo>0&&vvs>0){
					List<Vote_subject> list=vd.select();
					request.setAttribute("list", list);
					request.getRequestDispatcher("manage.jsp").forward(request, response);
				}
			}
		}else{
			request.setAttribute("msg", "您不是管理员没有操作权限");
			request.getRequestDispatcher("filter.jsp").forward(request, response);
		}
	}
	public void removeSession(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		HttpSession session=request.getSession();
		session.getAttribute("name");
		session.removeAttribute("name");
		response.sendRedirect("login.jsp");
	}
	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
