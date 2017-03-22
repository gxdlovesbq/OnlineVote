package com.sxt.dao;

import java.util.List;

import com.sxt.bean.Vote_user3;

public class SerchDao extends BaseDao {
		public String serchName(String name){
			String n="";
			String sql="select vu_name from vote_user where vu_name='"+name+"'";
			List<Vote_user3> list=super.executeQuery(sql, Vote_user3.class);
			for (Vote_user3 v : list) {
				n=v.getVu_name();
			}
			if(n!=""){
				return "用户名"+name+"存在";
			}else{
				return "";
			}
		}
}
