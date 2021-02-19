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
public class DBread extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String s = request.getParameter("p");
        double result = 0;
        OracleConn nn = new OracleConn();
        Connection ss = nn.getConn();
        try {

            Statement st = null;
            ResultSet rs = null;
            String qry = "select f.anonsid, f.text, f.imgfile, f.anonsdst, f.anonsdend, f.regns, f.theme_name, f.tm, f.funusid, chkd from anons_v f where f.funusid=" + s + " order by priorityid, dcreate";
            st = ss.createStatement();
            rs = st.executeQuery(qry);
            JSONObject jObj = new JSONObject();
            ArrayList<Anons> list = new ArrayList<Anons>();
            Map<String, String> map = new HashMap<String, String>();
            Anons anons = null;

            while (rs.next()) {
                anons = new Anons();

                anons.setTEXT(rs.getString("TEXT"));
                anons.setIMGFILE(rs.getString("IMGFILE"));
                anons.setANONSDST(rs.getString("ANONSDST"));
                anons.setANONSDEND(rs.getString("ANONSDEND"));
                anons.setREGNS(rs.getString("REGNS"));
                anons.setTHEME_NAME(rs.getString("THEME_NAME"));
                anons.setTM(rs.getString("TM"));
                anons.setFUNUSID(rs.getString("FUNUSID"));
                anons.setANONSID(rs.getString("ANONSID"));
                anons.setCHKD(rs.getString("CHKD"));

                list.add(anons);

            }
            jObj.put("det", list);

            System.out.println(jObj.toString());
            ss.close();
            result = 11;
            doSetResult(response, jObj);
            return;
        } catch (Exception ex) {

            doSetError(response);
            System.out.println("getdata failed");
            ex.printStackTrace();
        }

    }

    protected void doSetResult(HttpServletResponse response, JSONObject jObjt) throws UnsupportedEncodingException, IOException {
        String reply = jObjt.toString();
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
