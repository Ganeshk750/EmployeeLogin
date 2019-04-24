package com.ganesh.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ganesh.dao.LoginDao;
import com.ganesh.model.LoginUser;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	 private LoginDao loginDao;
       
	  public void init() {
	        loginDao = new LoginDao();
	    }
  @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	 String username = req.getParameter("username");
	 String password = req.getParameter("password");
	 
	 LoginUser user = new LoginUser();
	 user.setUsername(username);
	 user.setPassword(password);
	 try {
	 if(loginDao.validate(user)) {
		  HttpSession session = req.getSession();
          session.setAttribute("username",username);
		 resp.sendRedirect("success.jsp"); 
	 }else {
          HttpSession session = req.getSession();
          session.setAttribute("user", username);
         resp.sendRedirect("login.jsp");
     }
  }catch (ClassNotFoundException e) {
      e.printStackTrace();
  }

  }

}
