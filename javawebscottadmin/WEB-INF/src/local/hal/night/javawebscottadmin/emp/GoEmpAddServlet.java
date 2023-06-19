package local.hal.night.javawebscottadmin.emp;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import local.hal.night.javawebscottadmin.ParentServlet;

/**
 * JavaWebScottAdmin Practice Src06
 *
 * 従業員情報登録画面。
 * @author yuyas
 *
 */
@WebServlet(name="GoEmpAddServlet",urlPatterns= {"/emp/goEmpAdd"})
public class GoEmpAddServlet extends ParentServlet{
	@Override
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws
	ServletException,IOException{
		String jspPath = "/emp/empAdd.jsp";

		RequestDispatcher rd = request.getRequestDispatcher(jspPath);
		rd.forward(request, response);
	}

	@Override
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws
	IOException,ServletException{
		doPost(request,response);
	}

}
