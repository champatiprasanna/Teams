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

import com.Pojo.NotificationPojo;


/**
 * Servlet implementation class RemoveTM
 */
@WebServlet("/RemoveTM")
public class RemoveTM extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		//ServletContext context = getServletContext();
		int id = Integer.parseInt(request.getParameter("idr"));
	    Configuration conref = new Configuration();
		conref.configure("hibernateI.cfg.xml");
		SessionFactory sfref = conref.buildSessionFactory();
		Session sref = sfref.openSession();
		Transaction tref = sref.beginTransaction();
		Query qref = sref.createQuery("select iemail,email from InvitePojo where id = :id");
		qref.setParameter("id", id);
		Object o[] = null;
		List ls = qref.list();
		Iterator itref = ls.iterator();
		if(itref.hasNext()) {
			
			 o = (Object[])itref.next();
				
				String iemail = (String) o[0];
				String email = (String) o[1];
				
			
		            Query query1 = sref.createQuery("delete from InvitePojo where id =:id ");
			        query1.setParameter("id",id);
			      
			        query1.executeUpdate();
			        
			        conref.configure("hibernateN.cfg.xml");
					SessionFactory sfrefn = conref.buildSessionFactory();
					Session srefn = sfrefn.openSession();
					Transaction trefn = srefn.beginTransaction();
			        
					 NotificationPojo np = new NotificationPojo();
				        np.setIemail(iemail);
				        
				        String display = "You are removed from the team created by "+iemail+"";
				        np.setDisplay(display);
				        np.setEmail(email);
				        srefn.save(np);
				        trefn.commit();
						srefn.close();
						sfrefn.close();
				   out.println("Successfully removed from your team!");
				   
			out.println(email);
			
		}
		else {
			out.println("The details you entered are incorrect");
		}
		
		tref.commit();
		sref.close();
		sfref.close();
		
	}

}
