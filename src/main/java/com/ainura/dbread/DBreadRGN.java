/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ainura.dbread;


import com.ainura.model.REGNS;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import connections.OracleConn;
import java.sql.Connection;
import javax.servlet.annotation.WebServlet;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

/**
 *
 * @author Ainura_Andr
 */
@WebServlet("/dbreadrgn")
public class DBreadRGN extends HttpServlet {
    
private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("start");
		int result = 0;
                 OracleConn nn=new OracleConn();
        Connection  ss=nn.getConn();
                try {
               
        
        Statement st = null;
ResultSet rs = null;
String qry = "select * from ai.REGNS";
st = ss.createStatement();
rs = st.executeQuery(qry);
JSONObject jObj = new JSONObject();
ArrayList<REGNS> list=new ArrayList<REGNS>();
Map<String, String> map=new HashMap<String, String>();
REGNS anons=null;
result=rs.getRow();
System.out.println(result);
while (rs.next()) {
anons=new REGNS();

anons.setREGNSID(rs.getString("REGNSID"));
 anons.setREGNS(rs.getString("REGNS"));
 anons.setREGNSID(rs.getString("REGNSID"));
 anons.setREGNS(rs.getString("REGNS"));

list.add(anons);

}
jObj.put("rgn",list);

System.out.println(jObj.toString());
ss.close();
        result=11;
                doSetResult( response, jObj  );
				return;
                }
         catch(Exception ex)
				
         {	
		
		 doSetError( response );
                 System.out.println("getdata failed");
        ex.printStackTrace();
	}
      
        }
	
	protected void doSetResult( HttpServletResponse response, JSONObject jObjt ) throws UnsupportedEncodingException, IOException {
		String reply = jObjt.toString();
		response.getOutputStream().write( reply.getBytes("UTF-8") );
		response.setContentType("application/json; charset=UTF-8");
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setStatus( HttpServletResponse.SC_OK );
	}		
	
	protected void doSetError( HttpServletResponse response ) throws UnsupportedEncodingException, IOException {
		String reply = "{\"error\":1}";
		response.getOutputStream().write( reply.getBytes("UTF-8") );
		response.setContentType("application/json; charset=UTF-8");
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setStatus( HttpServletResponse.SC_OK );
	}
}
	
