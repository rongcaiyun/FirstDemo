package cn.myschool.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BaseDao {
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	public void getConn() throws ClassNotFoundException, SQLException{
		Class.forName("com.mysql.jdbc.Driver");
		conn=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/myschool?","root","mysql");
	}
	public int executeSql(String sql,Object... objs){
		try {
			this.getConn();
			pstmt=conn.prepareStatement(sql);
			//?��֪��sql���ж����ʺţ��������Ҳ��ȷ��
			for(int i=0;i<objs.length;i++){
				pstmt.setObject(i+1,objs[i]);//setObject���������Ͷ����Ը�ֵ
			}
			return pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			this.closeAll();
		}
		return 0;
	}
	//
	public void closeAll(){
		try {
			if(rs!=null)
				rs.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			if(pstmt!=null)
				pstmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			if(!conn.isClosed())
				conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
