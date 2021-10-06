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
 * Servlet implementation class InvitationsSent
 */
@WebServlet("/InvitationsSent")
public class InvitationsSent extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		ServletContext context = getServletContext();
	    String email=(String)context.getAttribute("email");
	    
	    Configuration conref = new Configuration();
		conref.configure("hibernateI.cfg.xml");
		SessionFactory sfref = conref.buildSessionFactory();
		Session sref = sfref.openSession();
		Transaction tref = sref.beginTransaction();
		
		Query sqref = sref.createQuery("select id, iemail, email, subject, active from InvitePojo where iemail= :er");
		sqref.setParameter("er", email);
		List lsref = sqref.list();
		Iterator itsref = lsref.iterator();
		Object o[] = null;
		 out.print("<table border = '1' width = '50%'>");
		  out.print("<tr><th>id</th><th>iemail</th><th>subject</th><th>Remove</th></tr>");
		 
		while(itsref.hasNext()) {
			
			 o = (Object[])itsref.next();
			  out.println("<tr><td>"+o[0]+"</td><td>"+o[2]+"</td><td>"+o[3]+"</td><td><a href = 'RemoveReq?idI="+o[0]+"'>Remove the Invitation</a></td></tr>");

			}
			 tref.commit();
				sref.close();
				sfref.close();
			
	      
		
	}

}
