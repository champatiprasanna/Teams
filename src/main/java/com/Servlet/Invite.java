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

import com.Pojo.InvitePojo;
import com.Pojo.SignUpPojo;

/**
 * Servlet implementation class Invite
 */
@WebServlet("/Invite")
public class Invite extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		 PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		String emailI = request.getParameter("emailI");
		
		 ServletContext context = getServletContext();
	      String iemail=(String)context.getAttribute("email");
	      String subject =(String)context.getAttribute("subject");
		//ServletContext sc = getServletContext();
		context.setAttribute("email", emailI);
		
		 //out.println(email);
		// out.println(iemail);
		 out.println("Invitation sent!");
		 Configuration conref = new Configuration();
			conref.configure("hibernateI.cfg.xml");
			SessionFactory sfref = conref.buildSessionFactory();
			Session sref = sfref.openSession();
			Transaction tref = sref.beginTransaction();
			
			InvitePojo ip = new InvitePojo();
			ip.setIemail(iemail);
			ip.setEmail(emailI);
			ip.setSubject(subject);
			ip.setActive(false);
			
			
			sref.save(ip);
			
			tref.commit();
			sref.close();
			sfref.close();
			
			
			
	}

}
