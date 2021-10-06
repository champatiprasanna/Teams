package com.Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;


import com.Pojo.SignUpPojo;

/**
 * Servlet implementation class SignUP
 */
@WebServlet("/SignUP")
public class SignUP extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		
		PrintWriter out = response.getWriter();
		 String name = request.getParameter("name");
			String password = request.getParameter("password");
			String email = request.getParameter("email");
			int mobile = Integer.parseInt(request.getParameter("mobile"));
			String subject = request.getParameter("subject");
			
				Configuration conref = new Configuration();
				conref.configure("hibernateS.cfg.xml");
				SessionFactory sfref = conref.buildSessionFactory();
				Session sref = sfref.openSession();
				Transaction tref = sref.beginTransaction();
				SignUpPojo sp = new SignUpPojo();
				sp.setName(name);
				sp.setEmail(email);
				sp.setPassword(password);
				sp.setMobile(mobile);
				sp.setSubject(subject);
				
				sref.save(sp);
				
				out.print("<table >");
				  out.print("<tr><th>Successfully SignedUp!</th></tr>");
				  out.println("<tr><td><a href = 'LogIn.html'/>LogIn</td></tr>");
				  out.println("</table>");
				  
				tref.commit();
				sref.close();
				sfref.close();
	}

}
