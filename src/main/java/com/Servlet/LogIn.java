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

import com.Pojo.SignUpPojo;

/**
 * Servlet implementation class LogIn
 */
@WebServlet("/LogIn")
public class LogIn extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		PrintWriter out = response.getWriter();
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		Configuration conref = new Configuration();
		conref.configure("hibernateS.cfg.xml");
		SessionFactory sfref = conref.buildSessionFactory();
		Session sref = sfref.openSession();
		Transaction tref = sref.beginTransaction();
		Query qref = sref.createQuery("select subject from SignUpPojo where email = :eref and password = :pref");
		qref.setParameter("eref", email);
		qref.setParameter("pref", password);
		List ls = qref.list();
		Iterator itref = ls.iterator();
		if(itref.hasNext()) {
			
				//int  id = (Integer) ls.get(0);
				String subject = (String) ls.get(0);
				 ServletContext servletContext = getServletContext();
		            servletContext.setAttribute("email",email);
		           // servletContext.setAttribute("id", id);
		            servletContext.setAttribute("subject", subject);
		           
				out.print("<table >");
				  out.print("<tr><th>Successfully SignedIn!</th></tr>");
				  
				  out.println("<tr><td><a href = 'createTeam.html'/>Create a team</td></tr>");
				  out.println("<tr><td><a href = 'MyInvite.html'/>My Invites</td></tr>");
				  out.println("<tr><td><a href = 'MyTeam.html'/>My Team</td></tr>");
				  out.println("<tr><td><a href = 'InvitationS.html'/>InvitationsSent</td></tr>");
				  out.println("<tr><td><a href = 'NotificationH.html'/>Notifications</td></tr>");
				 
				  out.println("</table>");
				
			
			
		}
		else {
			out.println("The details you entered are incorrect");
		}
		
		tref.commit();
		sref.close();
		sfref.close();
		
		
		
	}

}
