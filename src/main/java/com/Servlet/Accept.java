package com.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
 * Servlet implementation class Accept
 */
@WebServlet("/Accept")
public class Accept extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		
		int id = Integer.parseInt(request.getParameter("id"));
		
		boolean active = true;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conj = DriverManager.getConnection("jdbc:mysql://localhost:3306/teams", "root","1234");
			String sql = "select  iemail, email, subject from invites where  id = '"+id+"'"  ;
			PreparedStatement ps = conj.prepareStatement(sql);
			
			 ResultSet rs = ps.executeQuery();
			 if(rs.next()) {
				 
				 String iemail = rs.getString(1);
				String emails = rs.getString(2);
				 String subject = rs.getString(3);
				  
				 String sqli = "select  active from invites where  iemail = '"+iemail+"' and subject = '"+subject+"'"  ;
				 
				 PreparedStatement psi = conj.prepareStatement(sqli);
					
				 ResultSet rsi = psi.executeQuery();
				 if(rsi.next()) {
					 
					 Boolean active1 = rsi.getBoolean(1);
					
					 if(active1 == true) {
						 out.println("Someone already accepted!");
						 
					 	}
					 else {
						 Configuration con = new Configuration();
					        con.configure("hibernateI.cfg.xml");
					        SessionFactory sref = con.buildSessionFactory();
					        Session sre = sref.openSession();
					        Transaction tr1 = sre.beginTransaction();
					        Query query1 = sre.createQuery("update InvitePojo set active=:ac where id=:id");
					        query1.setParameter("ac", active);
					        query1.setParameter("id",id);
					        query1.executeUpdate();
					      out.println("successfully accepted the invitation");
					      //
					      Query query2 = sre.createQuery("delete from InvitePojo where iemail =:iemail  and subject =:subject and active = :active ");
					        query2.setParameter("iemail",iemail);
					        query2.setParameter("subject",subject);
					        query2.setParameter("active",false);
					      
					        query2.executeUpdate();
					       //
					        tr1.commit();
					        sre.close();
					        sref.close();
					       
					       
					 }
							 }
					 
				 }
				
			 
			 }catch(Exception e) {
				 e.printStackTrace();
					System.out.println(e.getMessage());
				}
			 
			 
	}

}
