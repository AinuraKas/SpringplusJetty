package com.ainura.launcher;

import com.ainura.calc.Calcul;
import com.ainura.dbread.DBinsUser;
import com.ainura.dbread.DBread;
import com.ainura.dbread.DBreadUser;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

import java.net.URL;
import java.security.ProtectionDomain;
import javax.servlet.*;
import org.eclipse.jetty.servlet.ServletHolder;

/**
 * Starts jetty-server on the specified port
 */
public class Launcher {

    public static void main(String[] args) throws Exception {
        int port = 8800;
        try {
            if (args.length > 0) {
                port = Integer.parseInt(args[0]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        Server server = new Server(port);

        ProtectionDomain domain = Launcher.class.getProtectionDomain();
        URL location = domain.getCodeSource().getLocation();

        WebAppContext webapp = new WebAppContext();
        //webapp.setContextPath("/");
        webapp.addServlet(new ServletHolder(new Calcul()), "/func");
        webapp.addServlet(new ServletHolder((Servlet) new DBread()), "/read");
        webapp.addServlet(new ServletHolder((Servlet) new DBreadUser()), "/readurs");
        webapp.addServlet(new ServletHolder((Servlet) new DBinsUser()), "/insusr");
        //можно тут можно в web.xml
        //webapp.addServlet(new ServletHolder( (Servlet) new NewAnons ( )),"/newanons");
        webapp.setWar(location.toExternalForm());

        server.setHandler(webapp);
        server.start();
        server.join();

    }
}
