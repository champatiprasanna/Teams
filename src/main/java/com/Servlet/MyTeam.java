package com.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

/**
 * Servlet implementation class MyTeam
 */
@WebServlet("/MyTeam")
public class MyTeam extends HttpServlet {
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
	    String subject=(String)context.getAttribute("subject");
	      
	 
	    try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/teams", "root","1234");
			
			
			String sql = "select id, email, subject from invites where iemail = '"+email+"'and subject != '"+subject+"' and active = true" ;
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
// For printing you are the admin of this team
			String sqs = "select id, email, subject from signup where email = '"+email+"'" ;

			PreparedStatement pss = con.prepareStatement(sqs);
			
			 ResultSet rss = pss.executeQuery();
			  
			  out.print("<table border = '1' width = '50%'>");
			  out.print("<tr><th>id</th><th>email</th><th>subject</th><th>Remove</th></tr>");
			 
			  // if for printing the tea admin
			  if(rss.next()) {
			      
				  out.println("<tr><td>"+rss.getString(1)+"</td><td>"+rss.getString(2)+"</td><td>"+rss.getString(3)+"</td><td>You are the admin of this team</td></tr>");

			  
		        while (rs.next()) {
		          
			         
			        out.println("<tr><td>"+rs.getString(1)+"</td><td>"+rs.getString(2)+"</td><td>"+rs.getString(3)+"</td><td><a href = 'RemoveTM?idr="+rs.getInt(1)+"'>Remove</a></td></tr>");

			          
		        }
			  }
		        out.println("</table>");
		        
		       
		    } catch(Exception e) {
				 e.printStackTrace();
					System.out.println(e.getMessage());
				}
				
	}

}









/*
Configuration conref = new Configuration();
conref.configure("hibernateI.cfg.xml");
SessionFactory sfref = conref.buildSessionFactory();
Session sref = sfref.openSession();
Transaction tref = sref.beginTransaction();
Query qref = sref.createQuery("from InvitePojo where email = :eref and active = :ac");
qref.setParameter("eref", email);
qref.setParameter("ac", active);
List ls = qref.list();
Iterator itref = ls.iterator();
  out.print("<table border = '1' width = '50%'>");
  out.print("<tr><th>id</th><th>email</th><th>subject</th><th>Delete</th></tr>");
if(itref.hasNext()) {
	
	
	InvitePojo ip1 = new InvitePojo();
	Query qs = sref.createQuery("from InvitePojo");
	List ls1 = qs.list();
	Iterator itr1= ls1.iterator();
	while(itr1.hasNext()) {
	InvitePojo ip = (InvitePojo)itref.next();
    out.println("<tr><td>"+ip.getId()+"</td><td>"+ip.getEmail()+"</td><td>"+ip.getSubject()+"</td><td><a href = 'Accept?id="+ip.getId()+"'>Accept</a></td></tr>");
	}
	
}
out.println("</table>");
System.out.println("hello");
tref.commit();
sref.close();
sfref.close();

*/
