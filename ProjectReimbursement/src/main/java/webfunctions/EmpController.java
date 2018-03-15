package webfunctions;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import objects.User;
import pov.ReimFunc;

public class EmpController {

	public void login(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, java.io.IOException {
/**		// call services to grab data, put the data into a scope
		User user = ReinFunc.login(request);
		request.setAttribute("user", user);
		request.getRequestDispatcher("/login.jsp").forward(request, response);
**/	}

	public void doAll(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}
	

}