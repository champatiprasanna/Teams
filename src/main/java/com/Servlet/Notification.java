package com.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 * Servlet implementation class Notification
 */
@WebServlet("/Notification")
public class Notification extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		ServletContext context = getServletContext();
	    String email=(String)context.getAttribute("email");
	      
	    
		Configuration conref = new Configuration();
		conref.configure("hibernateN.cfg.xml");
		SessionFactory sfref = conref.buildSessionFactory();
		Session sref = sfref.openSession();
		Transaction tref = sref.beginTransaction();
		
		Query sqref = sref.createQuery("select id, iemail, display,email from NotificationPojo where email= :er");
		sqref.setParameter("er", email);
		List lsref = sqref.list();
		Iterator itsref = lsref.iterator();
		Object o[] = null;
		
		 out.print("<table border = '1' width = '50%'>");
		  out.print("<tr><th>Message</th></tr>");
		while(itsref.hasNext()) {
			
			 o = (Object[])itsref.next();
			
			String display = (String) o[2];
            out.println("<tr><td>"+display+"</td></tr>");

			//out.println(display);
			
			}
		out.println("</table>");
			 tref.commit();
				sref.close();
				sfref.close();
			
	      
		
	}

}
