package Controller;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import Model.Registration;

@WebServlet(name = "search", urlPatterns = {"/search"})
public class Search extends HttpServlet {

    //private static final String Long = null;

	protected void processRequest(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        //resp.setContentType("text/html;charset=UTF-8");
        //PrintWriter out = resp.getWriter();
        HttpSession session = req.getSession();
        Registration u = new Registration(session);
        try {
        	Long t=(Long)session.getAttribute("id");
            if(t != 0 && t==1){
            	int id =Integer.parseInt(req.getParameter("id"));
            	RequestDispatcher re=req.getRequestDispatcher("Search.jsp?id=" + id);
            	re.forward(req, resp);
            	//resp.sendRedirect("Search.jsp?id="+id);            p
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        processRequest(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        processRequest(req, resp);
    }
}

