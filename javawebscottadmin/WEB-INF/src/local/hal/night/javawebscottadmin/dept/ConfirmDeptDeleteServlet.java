package local.hal.night.javawebscottadmin.dept;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import local.hal.night.javawebscottadmin.ParentServlet;
import local.hal.night.javawebscottadmin.entity.Dept;

/**
 * JavaWebScottAdmin Lesson Src12
 *
 * 部門情報削除確認画面。
 * @author yuyas
 *
 */
@WebServlet(name = "ConfirmDeptDeleteServlet", urlPatterns = { "/dept/confirmDelete" })
public class ConfirmDeptDeleteServlet extends ParentServlet {
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String jspPath = "/dept/confirmDeptDelete.jsp";

		request.setCharacterEncoding("UTF-8");
		String deleteDeptDeptno = request.getParameter("deleteDeptDeptno");

		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM dept WHERE deptno = ?";

		try {
			int deleteDeptDeptnoInt = Integer.parseInt(deleteDeptDeptno);
			con = getConnection();
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, deleteDeptDeptnoInt);
			rs = stmt.executeQuery();

			if (rs.next()) {
				Integer deptno = rs.getInt("deptno");
				String dname = rs.getString("dname");
				String loc = rs.getString("loc");

				Dept dept = new Dept();
				dept.setDeptno(deptno);
				dept.setDname(dname);
				dept.setLoc(loc);
				request.setAttribute("dept", dept);
			} else {
				jspPath = ERROR_JSP;
				request.setAttribute("errorMsg", "削除対象部門情報の取得に失敗しました。もう一度はじめからやり直してください。");
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
			close(rs);
			close(stmt);
			close(con);
		}

		RequestDispatcher rd = request.getRequestDispatcher(jspPath);
		rd.forward(request, response);
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		doPost(request, response);
	}

}
