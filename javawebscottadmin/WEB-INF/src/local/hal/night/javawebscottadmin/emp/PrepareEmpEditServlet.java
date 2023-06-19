package local.hal.night.javawebscottadmin.emp;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import local.hal.night.javawebscottadmin.ParentServlet;
import local.hal.night.javawebscottadmin.entity.Emp;

/**
 * javaWebScottAdmin Practice Src09
 *
 * 従業員情報編集画面。
 * @author yuyas
 *
 */
@WebServlet(name = "PrepareEmpEditSevlet", urlPatterns = { "/emp/prepareEdit" })
public class PrepareEmpEditServlet extends ParentServlet {
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String jspPath = "/emp/empEdit.jsp";
		request.setCharacterEncoding("UTF-8");
		String editEmpEmpno = request.getParameter("editEmpEmpno");

		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM emp WHERE empno = ?";

		try {
			int editEmpEmpnoInt = Integer.parseInt(editEmpEmpno);
			con = getConnection();
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, editEmpEmpnoInt);
			rs = stmt.executeQuery();

			if (rs.next()) {
				Integer empno = rs.getInt("empno");
				String ename = rs.getString("ename");
				String job = rs.getString("job");
				int mgr = rs.getInt("mgr");
				Date hiredate = rs.getDate("hiredate");
				double sal = rs.getDouble("sal");
				double comm = rs.getDouble("comm");
				Integer deptno = rs.getInt("deptno");

				Emp emp = new Emp();
				emp.setEmpno(empno);
				emp.setEname(ename);
				emp.setJob(job);
				emp.setMgr(mgr);
				emp.setHiredate((java.sql.Date) hiredate);
				emp.setSal(sal);
				emp.setComm(comm);
				emp.setDeptno(deptno);
				request.setAttribute("emp", emp);
			} else {
				jspPath = ERROR_JSP;
				request.setAttribute("errorMsg", "更新対象従業員情報の取得に失敗しました。もう一度はじめからやり直してください。");
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
