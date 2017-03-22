package com.sxt.dao;

import java.util.ArrayList;
import java.util.List;

import com.sxt.bean.OptionBean;
import com.sxt.bean.OptionBean1;
import com.sxt.bean.Vote_option;
import com.sxt.bean.Vote_option1;
import com.sxt.bean.Vote_option3;
import com.sxt.bean.Vote_subject;
import com.sxt.bean.Vote_subject1;
import com.sxt.bean.Vote_subject2;
import com.sxt.bean.Vote_subjectBean;
import com.sxt.bean.Vote_user;
import com.sxt.bean.Vote_user1;
import com.sxt.bean.Vote_user2;

public class Vote_userDao extends BaseDao {
	public int login(String name,String pwd){
		int flag=0;
		String sql="select * from vote_user where vu_name=\""+name+"\"";
		List<Vote_user> list=super.executeQuery(sql,Vote_user.class);
		if(list!=null){
			for (Vote_user v : list) {
				if(pwd.equals(v.getVu_passworld())){
					return flag=1;
				}else{
					return flag=-1;
				}
			}
		}else{
			return flag=-2;
		}
		return flag;
	}
	public int inster(Vote_user u){
		String sql="insert vote_user values(0,?,?,default) ";
		List list=new ArrayList();
		list.add(u.getVu_name());
		list.add(u.getVu_passworld());
		super.setParams(list);
		return super.executeUpdate(sql);
	}
	public List select(){
		String sql="SELECT s.vs_titles,s.vs_id,s.count,COUNT(DISTINCT vi.vu_id)AS pcount FROM (SELECT vs.vs_id AS vs_id,vs.vs_titles AS vs_titles,COUNT(vo.vo_option)AS count FROM vote_subject vs,vote_option vo WHERE vs.vs_id=vo.vs_id GROUP BY vs.vs_id) AS s LEFT JOIN vote_item vi ON s.vs_id=vi.vs_id GROUP BY s.vs_id";
		return super.executeQuery(sql,Vote_subject.class);
	}
	public List select1(String titles){
		String sql="select vs_id ,vs_type,nowtime,endtime from vote_subject where vs_titles=\""+titles+"\"";
		return super.executeQuery(sql,Vote_subject1.class);
	}
	public List select2(int id){
		String sql="select vo_option,vo_id from vote_option where vs_id=\""+id+"\"";
		return super.executeQuery(sql,Vote_option.class);
	}
	public int inster1(String title,int type,String nowtime,String endtime){
		String sql="insert vote_subject values(\"0\",\""+title+"\",\""+type+"\",\""+nowtime+"\",\""+endtime+"\") ";
		return super.executeUpdate(sql);
	}
	public List select3(String title){
		String sql="select vs_id from vote_subject where vs_titles=\""+title+"\"";
		return super.executeQuery(sql,Vote_subject2.class);
	}
	public int update(String[]options,int id){
		String sql="";
		int it=0;
		for (int i = 0; i < options.length; i++) {
			sql="insert vote_option values(\"0\",\""+options[i]+"\",\""+id+"\",\"0\") ";
			super.executeUpdate(sql);
		}
		return it;
	}
	public List select4(String name){
		String sql1="select vu_id from vote_user where vu_name=\""+name+"\"";
		return super.executeQuery(sql1,Vote_user2.class);
	}
	public int inster2(String[]options,String vs_id,int vu_id){
		String sql="";
		int it=0;
		for (int i = 0; i < options.length; i++) {
			sql="insert vote_item values(\"0\",\""+options[i]+"\",\""+vs_id+"\",\""+vu_id+"\")";
			it=super.executeUpdate(sql);
		}
		return it;
	}
	public List getselect(String vs_id){
		String sql="SELECT s.vs_titles,COUNT( vi.vu_id)AS vcount,s.count,COUNT(DISTINCT vi.vu_id)AS pcount FROM (SELECT vs.vs_id AS vs_id,vs.vs_titles AS vs_titles,COUNT(vo.vo_option)AS COUNT FROM vote_subject vs,vote_option vo WHERE vs.vs_id=vo.vs_id GROUP BY vs.vs_id) AS s LEFT JOIN vote_item vi ON s.vs_id=vi.vs_id WHERE vi.vs_id=\""+vs_id+"\" GROUP BY s.vs_id ";
		return super.executeQuery(sql, OptionBean1.class);
	}
	public List getview(String vs_id){
		String sql="SELECT vo.vo_option,vo.vo_id,COUNT(vi.vo_id)AS vcount FROM vote_item vi RIGHT JOIN vote_option vo ON vi.vo_id=vo.vo_id WHERE vo.vs_id=\""+vs_id+"\" GROUP BY vo.vo_id";
		return super.executeQuery(sql, OptionBean.class);
	}
	public int updatevote_subject(Vote_subjectBean vsb){
		String sql="UPDATE vote_subject SET vs_titles='"+vsb.getVs_titles()+"',vs_type="+vsb.getVs_type()+" WHERE vs_id="+vsb.getVs_id();
		return super.executeUpdate(sql);
	}
	public boolean selectvoit_item(int vs_id){
		long a=0;
		String sql="select count(vu_id) as countid from vote_item where vs_id="+vs_id;
		List<Vote_option3>list=super.executeQuery(sql, Vote_option3.class);
		for (Vote_option3 v : list) {
			a=v.getCountid();
		}
		if(a>0){
			return true;
		}else{
			return false;
		}
	}
	public int deletevote_option(int vs_id){
		String sql="delete  from vote_option where vs_id="+vs_id;
		return super.executeUpdate(sql);
	}
	public List search(String name){
		String sql="SELECT s.vs_titles,COUNT( vi.vu_id)AS vcount,s.count,COUNT(DISTINCT vi.vu_id)AS pcount FROM (SELECT vs.vs_id AS vs_id,vs.vs_titles AS vs_titles,COUNT(vo.vo_option)AS COUNT FROM vote_subject vs,vote_option vo WHERE vs.vs_id=vo.vs_id GROUP BY vs.vs_id) AS s LEFT JOIN vote_item vi ON s.vs_id=vi.vs_id WHERE vs_titles LIKE '"+name+"%' GROUP BY s.vs_id ";
		return super.executeQuery(sql, OptionBean1.class);
	}
	
	public int jjjj(String vs_id,int vu_id){
		int count=0;
		String sql="SELECT COUNT(*) as countid FROM vote_item WHERE vs_id='"+vs_id+"' AND vu_id="+vu_id;
		List<Vote_option3> list=super.executeQuery(sql, Vote_option3.class);
		for (Vote_option3 v : list) {
			count=(int)v.getCountid();
		}
		return count;
	}
	public List getselect1(String vs_id){
		String sql="SELECT s.vs_titles,COUNT( vi.vu_id)AS vcount,s.count,COUNT(DISTINCT vi.vu_id)AS pcount FROM (SELECT vs.vs_id AS vs_id,vs.vs_titles AS vs_titles,COUNT(vo.vo_option)AS COUNT FROM vote_subject vs,vote_option vo WHERE vs.vs_id=vo.vs_id and vs.vs_id=\""+vs_id+"\" GROUP BY vs.vs_id) AS s LEFT JOIN vote_item vi ON s.vs_id=vi.vs_id  GROUP BY s.vs_id ";
		return super.executeQuery(sql, OptionBean1.class);
	}
	
	public int selectFflag(String name){
		int flag=0;
		String sql="select flag from vote_user where vu_name='"+name+"'";
		List<Vote_user1> l=super.executeQuery(sql, Vote_user1.class);
		for (Vote_user1 v : l) {
			flag=v.getFlag();
		}
		return flag;
	}
	public int deletevote_subject(String vs_id){
		String sql="delete  from vote_subject where vs_id='"+vs_id+"'";
		return super.executeUpdate(sql);
	}
	public int deleteoptions(String vs_id){
		String sql="delete  from vote_option where vs_id='"+vs_id+"'";
		return super.executeUpdate(sql);
	}
}
