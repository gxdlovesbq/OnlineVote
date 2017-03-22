package com.sxt.dao;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class BaseDao {
	private Connection conn=null;
	private PreparedStatement pstmt=null;
	private ResultSet rs=null;
	private List params=null; //���ڱ���SQL�еĲ���
	private String driver;
	private String url;
	private String username;
	private String password;
	
	
	
	public BaseDao() {
		InputStream is=this.getClass().getResourceAsStream("/db.properties");
		Properties prop=new Properties();
		try {
			prop.load(is);
			driver=prop.getProperty("driver");
			url=prop.getProperty("url");
			username=prop.getProperty("username");
			password=prop.getProperty("password");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void setParams(List params){
		this.params=params;
	}
	public void setPstmt(List params,PreparedStatement pstmt) throws SQLException{
		for(int i=0;i<params.size();i++){
			pstmt.setObject(i+1, params.get(i));
		}
	}
	//��ȡ��������
	public void getConn1() {
		try {
			Class.forName(driver);
			conn=DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	//��ȡ���ӳ�
	public void getConn2() {
		try {
			Context c=new InitialContext();
			DataSource d=(DataSource)c.lookup("java:comp/env/jdbc/management_db");
			conn=d.getConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	//ִ����ɾ��
	public int executeUpdate(String sql){
		getConn1();
		try {
			pstmt=conn.prepareStatement(sql);
			if(params!=null){
				//���ò���
				setPstmt(params,pstmt);
			}
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}finally{
			close();
		}
	}
	//ִ�в�ѯ
	public List	 executeQuery(String sql,Class cls){
		List list=new ArrayList();
		getConn1();
		try {
			pstmt=conn.prepareStatement(sql);
			if(params!=null){
				//���ò���
				setPstmt(params,pstmt);
			}
			rs=pstmt.executeQuery();
			while(rs.next()){
				//�����޲ι���
				Object obj=cls.newInstance();
				//��obj��װ����
				Field[]fs=cls.getDeclaredFields();
				for (Field field : fs) {
					String fname=field.getName();
					Method m=cls.getDeclaredMethod("set"+fname.substring(0,1).toUpperCase()+fname.substring(1), new Class[]{field.getType()});
					m.invoke(obj, rs.getObject(fname));
				}
				list.add(obj);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}finally{
			close();
		}
		return list;
	}
	//�ر�����
	public void close(){
		try {
			if(rs!=null){
				rs.close();
			}
			if(pstmt!=null){
				pstmt.close();
			}
			if(conn!=null){
				conn.close();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
