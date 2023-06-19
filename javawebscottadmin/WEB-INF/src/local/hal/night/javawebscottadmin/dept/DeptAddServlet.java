package local.hal.night.javawebscottadmin.dept;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import local.hal.night.javawebscottadmin.ParentServlet;

/**
 * JavaWebScottAdmin Lesson Src08
 *
 * 部門情報登録。
 * @author yuyas
 *
 */
@WebServlet(name="DeptAddServlet",urlPatterns= {"/dept/add"})
public class DeptAddServlet extends ParentServlet{
	@Override
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws
	ServletException,IOException{
		String jspPath = "/dept/deptAdd.jsp";
		String redirectPath = "/javawebscottadmin/dept/showList";
		boolean isRedirect = false;

		request.setCharacterEncoding("UTF-8");
		String addDeptDeptno = request.getParameter("addDeptDeptno");
		String addDeptDname = request.getParameter("addDeptDname");
		String addDeptLoc = request.getParameter("addDeptLoc");
		addDeptDeptno = addDeptDeptno.trim();
		addDeptDname = addDeptDname.trim();
		addDeptLoc = addDeptLoc.trim();

		List<String> validationMsgs = new ArrayList<>();
		int addDeptDeptnoInt = 0;
		if(addDeptDeptno.equals("")) {
			validationMsgs.add("部門番号の入力は必須です。");
		}
		else {
			if(addDeptDeptno.length() != 2) {
				validationMsgs.add("部門番号は2桁の整数で入力してください。");
			}
			else {
				try {
					addDeptDeptnoInt = Integer.parseInt(addDeptDeptno);
				}
				catch(NumberFormatException ex) {
					validationMsgs.add("部門番号は2桁の整数で入力してください。");
				}
			}
		}
		if(addDeptDname.equals("")) {
			validationMsgs.add("部門名の入力は必須です。");
		}

		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sqlSelect = "SELECT COUNT(*) count FROM dept WHERE deptno=?";
		String sqlInsert = "INSERT INTO dept (deptno,dname,loc) VALUES (?,?,?)";

		try {
			con = getConnection();
			stmt = con.prepareStatement(sqlSelect);
			stmt.setInt(1, addDeptDeptnoInt);
			rs = stmt.executeQuery();
			int count = 0;
			if(rs.next()) {
				count = rs.getInt("count");
			}
			if(count > 0) {
				validationMsgs.add("その部門番号はすでに使われています。別のものを指定してください。");
			}
			if(validationMsgs.isEmpty()) {
				close(rs);
				close(stmt);

				stmt = con.prepareStatement(sqlInsert);
				stmt.setInt(1, addDeptDeptnoInt);
				stmt.setString(2, addDeptDname);
				stmt.setString(3, addDeptLoc);
				int result = stmt.executeUpdate();
				if(result < 1) {
					jspPath = ERROR_JSP;
					request.setAttribute("errorMsg", "情報登録に失敗しました。もう一度はじめからやり直してください。");
				}
				else {
					isRedirect = true;
				}
			}
			else {
				request.setAttribute("validationMsgs", validationMsgs);
				request.setAttribute("addDeptDeptno", addDeptDeptno);
				request.setAttribute("addDeptDname", addDeptDname);
				request.setAttribute("addDeptLoc", addDeptLoc);
			}
		}
		catch(NamingException ex) {
			jspPath = ERROR_JSP;
			ex.printStackTrace();
			request.setAttribute("errorMsg", "もう一度はじめから操作をお願いします。");
		}
		catch(SQLException ex) {
			jspPath = ERROR_JSP;
			ex.printStackTrace();
			request.setAttribute("errorMsg", "もう一度はじめから操作をお願いします。");
		}
		catch(Exception ex) {
			jspPath = ERROR_JSP;
			ex.printStackTrace();
			request.setAttribute("errorMsg", "もう一度はじめから操作をお願いします。");
		}
		finally {
			close(rs);
			close(stmt);
			close(con);
		}

		if(isRedirect) {
			response.sendRedirect(redirectPath);
		}
		else {
			RequestDispatcher rd = request.getRequestDispatcher(jspPath);
			rd.forward(request, response);
		}
	}

	@Override
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws
	IOException,ServletException{
		doPost(request,response);
	}

}
