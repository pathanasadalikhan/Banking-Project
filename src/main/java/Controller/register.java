package Controller;

import java.io.IOException;
import java.io.PrintWriter;

import Model.Registration;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
@WebServlet(name = "register", urlPatterns = {"/register"})
public class register extends HttpServlet {
	protected void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session=req.getSession();
		Registration reg=new Registration(session);
		try {
			if(req.getParameter("register")!=null) {
                String name = req.getParameter("name");
                String phone = req.getParameter("phone");
                String email = req.getParameter("email");
                String pw = req.getParameter("pw");
                String cp = req.getParameter("cp");
                if (pw.equals(cp)) {
                    String status = reg.Registration(name, phone, email, pw);
                    if(status.equals("existed")) {
                    	req.setAttribute("status","Existed record");
                    	RequestDispatcher rd=req.getRequestDispatcher("Registration.jsp");
                    	rd.forward(req, resp);
                    }else if(status.equals("success")) {
                    	req.setAttribute("status", "successfully Registered");
                    	RequestDispatcher rd=req.getRequestDispatcher("Login.jsp");
                    	rd.forward(req, resp);
                    }else if(status.equals("failure")) {
                    	req.setAttribute("status", "Registeration failed");
                    	RequestDispatcher rd=req.getRequestDispatcher("Registration.jsp");
                    	rd.forward(req, resp);
                    }
                }
			}else if(req.getParameter("login")!=null) {
				String email=req.getParameter("email");
				String pass=req.getParameter("pw");
				String status=reg.login(email,pass);
				if(status.equals("success")) {
					RequestDispatcher rd=req.getRequestDispatcher("Index.jsp");
					rd.forward(req, resp);
				}else if(status.equals("failure")) {
					req.setAttribute("status", "Login Failed");
					RequestDispatcher rd=req.getRequestDispatcher("Login.jsp");
					rd.forward(req, resp);
				}
			}
			else if (session.getAttribute("uname") != null && req.getParameter("submit") != null) {
                String name = req.getParameter("name");
                String pno = req.getParameter("pno");
                String email = req.getParameter("email");
                Registration u = new Registration(session);
                String status = u.update(name, pno, email);
                if (status.equals("success")) {
                    req.setAttribute("status", "Profile successfully Updated");
                    RequestDispatcher rd1 = req.getRequestDispatcher("Index.jsp");
                    rd1.forward(req, resp);
                } else {
                    req.setAttribute("status", "Updation failure");
                    RequestDispatcher rd1 = req.getRequestDispatcher("Index.jsp");
                    rd1.forward(req, resp);
                }
            }else if(req.getParameter("logouts")!=null) {
				session.invalidate();
				RequestDispatcher rd=req.getRequestDispatcher("Index.jsp");
				rd.forward(req, resp);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		processRequest(req,resp);
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		processRequest(req,resp);
	}
	
}
