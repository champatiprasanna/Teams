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

import com.Pojo.SignUpPojo;

/**
 * Servlet implementation class CreateTeam
 */
@WebServlet("/CreateTeam")
public class CreateTeam extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String Specsubject = null;
		 PrintWriter out = response.getWriter();
			response.setContentType("text/html");
			String subject = request.getParameter("subject");
			 ServletContext context = getServletContext();
		      String email=(String)context.getAttribute("email");
		      String specsubject=(String)context.getAttribute("subject");
		      context.setAttribute("subject", subject);
			
		    
	        try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/teams", "root","1234");
				
				String sql = "select id, name, email, subject from signup where subject = '"+subject+"' and subject != '"+specsubject+"' and email != '"+email+"'" ;
				
				PreparedStatement ps = con.prepareStatement(sql);
				
				  ResultSet rs = ps.executeQuery();
				  out.print("<table border = '1' width = '50%'>");
				  out.print("<tr><th>id</th><th>name</th><th>email</th><th>subject</th><th>Inivitaion</th></tr>");
			        while (rs.next()) {
			          
				           // out.println("<tr><td>"+rs.getString(1)+"</td><td><a href = 'Invite.html'/>Invite</td><td><a href = 'TRemove'/>Deactivate</td></tr>");
				            out.println("<tr><td>"+rs.getString(1)+"</td><td>"+rs.getString(2)+"</td><td>"+rs.getString(3)+"</td><td>"+rs.getString(4)+"</td><td><a href = 'Invite?emailI="+rs.getString(3)+"'>Invite</a></td></tr>");

				          
			        }
			        
			        out.println("</table>");
			        
			     
			    } catch(Exception e) {
					 e.printStackTrace();
						System.out.println(e.getMessage());
					}
					
			
	      
		
		/*
		
					
					
					  Configuration conref = new Configuration();
					  conref.configure("hibernateS.cfg.xml");
					  SessionFactory sfref = conref.buildSessionFactory();
					  Session sref = sfref.openSession();
					  Transaction tref = sref.beginTransaction();
					
					  Query sqref = sref.createQuery("from SignUpPojo where subject = :sub");
					  	sqref.setParameter("sub", subject);
						//sqref.setParameter("er", email);
						List lsref = sqref.list();
						Iterator itsref = lsref.iterator();
						
						out.print("<table border = '1' width = '50%'>");
						out.print("<tr><th>id</th><th>name</th><th>email</th><th>mobile</th><th>subject</th><th>Delete my application</tr>");
						if(itsref.hasNext()) {
							
							SignUpPojo sp = new SignUpPojo();
							Query qu = sref.createQuery("from SignUpPojo");
							
							List lsref1 = qu.list();
							Iterator itsref1 = lsref1.iterator();
							
							while(itsref1.hasNext()) {
								SignUpPojo spo = (SignUpPojo)itsref1.next();
								
								
					            out.println("<tr><td>"+spo.getId()+"</td><td>"+spo.getName()+"</td><td>"+spo.getEmail()+"</td><td>"+spo.getMobile()+"</td><td>"+spo.getSubject()+"</td><td><a href = 'Invite?email = "+spo.getEmail()+"'>Invite</a></td></tr>");

							}
							
							
							}
						  out.println("</table>");
					        
						tref.commit();
						sref.close();
						sfref.close();
						
						
	}
	
	*/
						
						/* 
						while(itsref.hasNext()) {
							 ServletContext servletContext = getServletContext();
					          servletContext.setAttribute("email",email);
					            
							 o = (Object[])itsref.next();
							
				          out.println("<tr><td>"+o[0]+"</td><td>"+o[1]+"</td><td>"+o[2]+"</td><td>"+o[3]+"</td><td>"+o[4]+"</td><td><a href = 'Invite.html'/>Delet my application</td></tr>");

						}
						 out.println("</table>");	
						 
						tref.commit();
						sref.close();
						sfref.close();
						
					  
				 }							 
				
					
				 	
			   */
					
	      
	}

}
