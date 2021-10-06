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
 * Servlet implementation class MyInvites
 */
@WebServlet("/MyInvites")
public class MyInvites extends HttpServlet {
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
					
			
			
			
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/teams", "root","1234");
				String sql = "select id, iemail, subject from invites where  email = '"+email+"' and active = 0"  ;
				
				
				PreparedStatement ps = con.prepareStatement(sql);
				
				  ResultSet rs = ps.executeQuery();
				  out.print("<table border = '1' width = '50%'>");
				  out.print("<tr><th>id</th><th>Sent By</th><th>subject</th><th>Inivitaion</th><th>Delete</th></tr>");
			        while (rs.next()) {
			          
				           // out.println("<tr><td>"+rs.getString(1)+"</td><td><a href = 'Invite.html'/>Invite</td><td><a href = 'TRemove'/>Deactivate</td></tr>");
				            out.println("<tr><td>"+rs.getString(1)+"</td><td>"+rs.getString(2)+"</td><td>"+rs.getString(3)+"</td><td><a href = 'Accept?id="+rs.getInt(1)+"'>Accept Invitation</a></td><td><a href = 'DeleteInvitation?id="+rs.getInt(1)+"'>Delete Invitation</a></td></tr>");

				          
			        }
			        
			        out.println("</table>");
			        
			       
			    } catch(Exception e) {
					 e.printStackTrace();
						System.out.println(e.getMessage());
					}
					
			
			
			
			
	}

}
