package local.hal.night.javawebscottadmin.dept;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
import local.hal.night.javawebscottadmin.entity.Dept;

/**
 * JavaWebScottAdmin Lesson Src11
 *
 * 部門情報更新。
 * @author yuyas
 *
 */
@WebServlet(name = "DeptEditServlet", urlPatterns = { "/dept/edit" })
public class DeptEditServlet extends ParentServlet {
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String jspPath = "/dept/deptEdit.jsp";
		String redirectPath = "/javawebscottadmin/dept/showList";
		boolean isRedirect = false;

		request.setCharacterEncoding("UTF-8");
		String editDeptDeptno = request.getParameter("editDeptDeptno");
		String editDeptDname = request.getParameter("editDeptDname");
		String editDeptLoc = request.getParameter("editDeptLoc");
		editDeptDname = editDeptDname.trim();
		editDeptLoc = editDeptLoc.trim();

		List<String> validationMsgs = new ArrayList<>();
		Dept dept = new Dept();
		int editDeptDeptnoInt = Integer.parseInt(editDeptDeptno);
		dept.setDeptno(editDeptDeptnoInt);
		if (editDeptDname.equals("")) {
			validationMsgs.add("部門名の入力は必須です。");
		}
		dept.setDname(editDeptDname);
		dept.setLoc(editDeptLoc);

		Connection con = null;
		PreparedStatement stmt = null;
		String sql = "UPDATE dept SET dname = ?,loc = ? WHERE deptno = ?";

		try {
			con = getConnection();
			if (validationMsgs.isEmpty()) {
				stmt = con.prepareStatement(sql);
				stmt.setString(1, dept.getDname());
				stmt.setString(2, dept.getLoc());
				stmt.setInt(3, dept.getDeptno());
				int result = stmt.executeUpdate();
				if (result < 1) {
					jspPath = ERROR_JSP;
					request.setAttribute("errorMsg", "情報更新に失敗しました。もう一度はじめからやり直してください。");
				} else {
					isRedirect = true;
				}
			} else {
				request.setAttribute("validationMsgs", validationMsgs);
				request.setAttribute("dept", dept);
			}
		} catch (NamingException ex) {
			jspPath = ERROR_JSP;
			ex.printStackTrace();
			request.setAttribute("errorMsg", "もう一度はじめから操作をお願いします。");
		} catch (SQLException ex) {
			jspPath = ERROR_JSP;
			ex.printStackTrace();
			request.setAttribute("errorMsg", "もう一度はじめから操作をお願いします。");
		} catch (Exception ex) {
			jspPath = ERROR_JSP;
			ex.printStackTrace();
			request.setAttribute("errorMsg", "もう一度はじめから操作をお願いします。");
		} finally {
			close(stmt);
			close(con);
		}

		if (isRedirect) {
			response.sendRedirect(redirectPath);
		} else {
			RequestDispatcher rd = request.getRequestDispatcher(jspPath);
			rd.forward(request, response);
		}
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

}
