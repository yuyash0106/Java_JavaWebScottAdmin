package local.hal.night.javawebscottadmin.emp;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
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
import local.hal.night.javawebscottadmin.entity.Emp;

/**
 * JavaWebScottAdmin Practice Src12
 *
 * 従業員情報削除確認画面。
 * @author yuyas
 *
 */
@WebServlet(name="ConfirmEmpDeleteServlet",urlPatterns= {"/emp/confirmDelete"})
public class ConfirmEmpDeleteServlet extends ParentServlet{
	@Override
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws
	ServletException,IOException{
		String jspPath = "/emp/confirmEmpDelete.jsp";

		request.setCharacterEncoding("UTF-8");
		String deleteEmpEmpno = request.getParameter("deleteEmpEmpno");

		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM emp WHERE empno = ?";

		try {
			int deleteEmpEmpnoInt = Integer.parseInt(deleteEmpEmpno);
			con = getConnection();
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, deleteEmpEmpnoInt);
			rs = stmt.executeQuery();

			if(rs.next()) {
				Integer empno = rs.getInt("empno");
				String ename = rs.getString("ename");
				String job = rs.getString("job");
				Integer mgr = rs.getInt("mgr");
				String hiredate = rs.getString("hiredate");
				double sal = rs.getDouble("sal");
				double comm = rs.getDouble("comm");
				Integer deptno = rs.getInt("deptno");

				Date hiredateDate= Date.valueOf(hiredate);
				Emp emp = new Emp();
				emp.setEmpno(empno);
				emp.setEname(ename);
				emp.setJob(job);
				emp.setMgr(mgr);
				emp.setHiredate(hiredateDate);
				emp.setSal(sal);
				emp.setComm(comm);
				emp.setDeptno(deptno);
				request.setAttribute("emp", emp);
			}
			else {
				jspPath = ERROR_JSP;
				request.setAttribute("errorMsg", "削除対象従業員情報の取得に失敗しました。もう一度はじめからやり直してください。");
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
		RequestDispatcher rd = request.getRequestDispatcher(jspPath);
		rd.forward(request, response);
	}

	@Override
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws
	IOException,ServletException{
		doPost(request,response);
	}

}
