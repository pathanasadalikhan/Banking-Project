package Controller;

import java.io.IOException;

import Model.Registration;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
@WebServlet(name = "forget", urlPatterns = {"/forget"})
public class Forget extends HttpServlet{
	protected void processRequest(HttpServletRequest req, HttpServletResponse resp) {
		try {
			HttpSession session=req.getSession();
			Registration reg=new Registration(session);
			String email=email=req.getParameter("email");
			String phone=phone=req.getParameter("phone");
			if(req.getParameter("forget")!=null) {
				String status=reg.forget(email,phone);
				if(status.equals("sucess")) {
					req.setAttribute("status", "Email found");
					RequestDispatcher re=req.getRequestDispatcher("Forget.jsp");
					re.forward(req, resp);
				}
				else {
					req.setAttribute("status", "email not found");
					RequestDispatcher re=req.getRequestDispatcher("Forget.jsp");
					re.forward(req, resp);
				}
			}else if(req.getParameter("update")!=null) {
				String pw=req.getParameter("pw");
				String cp=req.getParameter("cp");
				if(pw.equals(cp)){
					String status=reg.updatePass(pw);
					if(status.equals("sucess")) {
						req.setAttribute("status", "Password Updated Sucessfully");
						RequestDispatcher re=req.getRequestDispatcher("Login.jsp");
						re.forward(req, resp);
					}
					else {
						req.setAttribute("status", "failure occured");
						RequestDispatcher re=req.getRequestDispatcher("Forget.jsp");
						re.forward(req, resp);
					}
				}
				else {
					req.setAttribute("status", "password Mismatch");
					RequestDispatcher re=req.getRequestDispatcher("Forget.jsp");
					re.forward(req, resp);
				}
				
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		processRequest(req,resp);
	}

}
