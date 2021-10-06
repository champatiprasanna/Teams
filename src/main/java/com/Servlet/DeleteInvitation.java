package com.Servlet;

import java.io.IOException;
import java.io.PrintWriter;

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
 * Servlet implementation class DeleteInvitation
 */
@WebServlet("/DeleteInvitation")
public class DeleteInvitation extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		//int id = request.getParameter("id");
		int id = Integer.parseInt(request.getParameter("id"));
		
		 Configuration con = new Configuration();
	        con.configure("hibernateI.cfg.xml");
	        SessionFactory sessionFactory = con.buildSessionFactory();
	        Session session = sessionFactory.openSession();
	        Transaction tr = session.beginTransaction();
	       
	        Query query1 = session.createQuery("delete from InvitePojo where id =:id ");
	        query1.setParameter("id",id);
	      
	        query1.executeUpdate();
	        tr.commit();
	        session.close();
	        sessionFactory.close();
	        out.println("Successfully deleted the invitation!");
	}

}
