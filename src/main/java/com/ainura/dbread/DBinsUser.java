/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ainura.dbread;

import com.ainura.model.Anons;
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
@WebServlet("/ReadData")
public class DBinsUser extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Statement st = null;
        ResultSet rs = null;
        OracleConn nn = new OracleConn();
        Connection ss = nn.getConn();
        String qry = "";
        if (request.getParameter("p") == "1") {//это информация о пользователе
            String str_0 = request.getParameter("p0");
            String str_a = request.getParameter("p1");
            String str_b = request.getParameter("p2");

            qry = "insert into ai.funuss(USERSTYPEID,DNAME,PHNUM) values(" + str_0 + ",'" + str_a + "','" + str_b + "')";
        }

        /*  if (request.getParameter("p")=="3")	{//это анонс - net  ne poidet
	String str_l=request.getParameter("pl");	
          String str_0=request.getParameter("p0");	
            String str_a=request.getParameter("p1");
                String str_b=request.getParameter("p2");  
        
        
 qry= "insert into ai.ANONS(FUNUSID,SUBTHEMEID,TEXT,IMGFILE,ANONSDST,ANONSDEND,REGNSID) values("+str_l+","+str_0+","+str_a+","+str_b+")";}
         */
        if (request.getParameter("p") == "2") {//это выбранные подписки
            String str_l = request.getParameter("pl");
            String str_0 = request.getParameter("p0");
            String str_a = request.getParameter("p1");
            String str_b = request.getParameter("p2");

            qry = "insert into ai.SUBSCRIPTIONS(FUNUSID,SUBTHEMEID,PRIORITYID,CNUM) values(" + str_l + "," + str_0 + "," + str_a + "," + str_b + ")";
        }
        try {
            st = ss.createStatement();
            st.addBatch(qry);
            int[] rez = st.executeBatch();
            ss.close();

            doSetResult(response, rez.length);
            return;

        } catch (Exception ex) {
            System.out.println("Ins failed");

            doSetError(response);

            ex.printStackTrace();
        }

    }

    protected void doSetResult(HttpServletResponse response, int jObjt) throws UnsupportedEncodingException, IOException {
        String reply = "{\"error\":0,\"result\":" + Double.toString(jObjt) + "}";
        response.getOutputStream().write(reply.getBytes("UTF-8"));
        response.setContentType("application/json; charset=UTF-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setStatus(HttpServletResponse.SC_OK);
    }

    protected void doSetError(HttpServletResponse response) throws UnsupportedEncodingException, IOException {
        String reply = "{\"error\":1}";
        response.getOutputStream().write(reply.getBytes("UTF-8"));
        response.setContentType("application/json; charset=UTF-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
