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
@WebServlet(name = "deposit", urlPatterns = {"/deposit"})
public class Deposit extends HttpServlet{
	protected void processRequest(HttpServletRequest req, HttpServletResponse resp){
		try {
			HttpSession session=req.getSession();
			Registration reg=new Registration(session);
			if(req.getParameter("deposit")!=null) {
				int amt=Integer .parseInt(req.getParameter("amt"));
				String ret=reg.depositamt(amt);
				if(ret.equals("success")) {
					req.setAttribute("status", "Amount Deposited Successfully");
					RequestDispatcher re=req.getRequestDispatcher("Index.jsp");
					re.forward(req, resp);
				}
				else {
					req.setAttribute("status", "Error");
					RequestDispatcher re=req.getRequestDispatcher("Index.jsp");
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
