package local.hal.night.javawebscottadmin.emp;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import local.hal.night.javawebscottadmin.ParentServlet;

/**
 * JavaWebScottAdmin Practice Src14
 *
 * 従業員情報削除。
 * @author yuyas
 *
 */
@WebServlet(name="EmpDeleteServlet",urlPatterns= {"/emp/delete"})
public class EmpDeleteServlet extends ParentServlet{
	@Override
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws
	ServletException,IOException{
		String redirectPath = "/javawebscottadmin/emp/showList";
		boolean isRedirect = false;

		request.setCharacterEncoding("UTF-8");
		String deleteEmpEmpno = request.getParameter("deleteEmpEmpno");
		int deleteEmpEmpnoInt = Integer.parseInt(deleteEmpEmpno);

		Connection con = null;
		PreparedStatement stmt = null;
		String sql = "DELETE FROM emp WHERE empno = ?";

		try {
			con = getConnection();
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, deleteEmpEmpnoInt);
			int result = stmt.executeUpdate();
			if(result < 1) {
				request.setAttribute("errorMsg", "情報更新に失敗しました。もう一度はじめからやり直してください。");
			}
			else {
				isRedirect = true;
			}
		}
		catch(NamingException ex) {
			ex.printStackTrace();
			request.setAttribute("errorMsg", "もう一度はじめから操作をお願いします。");
		}
		catch(SQLException ex) {
			ex.printStackTrace();
			request.setAttribute("errorMsg", "もう一度はじめから操作をお願いします。");
		}
		catch(Exception ex) {
			ex.printStackTrace();
			request.setAttribute("errorMsg", "もう一度はじめから操作をお願いします。");
		}
		finally {
			close(stmt);
			close(con);
		}
		if(isRedirect) {
			response.sendRedirect(redirectPath);
		}
		else {
			RequestDispatcher rd = request.getRequestDispatcher(ERROR_JSP);
			rd.forward(request, response);
		}
	}

	@Override
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws
	IOException,ServletException{
		doPost(request,response);
	}

}
