/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ainura.dbread;

import connections.OracleConn;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Ainura_Andr прописываем такой же указатель как в web.xml or Launcher
 */
@WebServlet("/AC")
public class AnChecked extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        String s = request.getParameter("a");
        String f = request.getParameter("f");
        String p = request.getParameter("p");

        response.getWriter().println("jjj1:" + s);
        System.out.println("jjj1:" + s);
        OracleConn nn = new OracleConn();
        Connection ss = nn.getConn();
        String qry = "";
        Statement st = null;
        ResultSet rs = null;
        if (p.equals("1")) {
            qry = "insert into ai.ANONSCHECKED(ANONSID,FUNUSID) values(" + s + "," + f + ")";
        } else {
            qry = "delete from ai.ANONSCHECKED where ANONSID=" + s;
        }
        try {
            st = ss.createStatement();
            st.addBatch(qry);
            int[] rez = st.executeBatch();
            ss.close();

        } catch (Exception ex) {
            System.out.println("Ins failed");
            ex.printStackTrace();
        }

    }
}
