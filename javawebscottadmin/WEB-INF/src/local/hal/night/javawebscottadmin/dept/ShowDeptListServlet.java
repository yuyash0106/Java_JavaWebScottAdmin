package local.hal.night.javawebscottadmin.dept;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import local.hal.night.javawebscottadmin.ParentServlet;
import local.hal.night.javawebscottadmin.entity.Dept;

/**
 * JavaWebScottAdmin Lesson Src04
 *
 * 部門情報リスト表示。
 *
 * @author yuyas
 */
@WebServlet(name = "ShowDeptListServlet", urlPatterns = { "/dept/showList" })
public class ShowDeptListServlet extends ParentServlet {
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String jspPath = "/dept/deptList.jsp";

		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM dept ORDER BY deptno";
		Map<Integer, Dept> resultList = new LinkedHashMap<>();

		try {
			con = getConnection();
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();

			while (rs.next()) {
				Integer deptno = rs.getInt("deptno");
				String dname = rs.getString("dname");
				String loc = rs.getString("loc");

				Dept dept = new Dept();
				dept.setDeptno(deptno);
				dept.setDname(dname);
				dept.setLoc(loc);

				resultList.put(deptno, dept);
			}
			request.setAttribute("resultList", resultList);
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
