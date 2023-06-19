package local.hal.night.javawebscottadmin.emp;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import local.hal.night.javawebscottadmin.ParentServlet;
import local.hal.night.javawebscottadmin.entity.Emp;

/**
 * JavaWebScottAdmin Practice Src04
 *
 * 従業員リスト表示。
 *
 * @author yuyas
 */
@WebServlet(name = "ShowEmpListServlet", urlPatterns = { "/emp/showList" })
public class ShowEmpListServlet extends ParentServlet{
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String jspPath = "/emp/empList.jsp";

		Connection con =null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM emp ORDER BY empno";
		Map<Integer,Emp> resultList = new LinkedHashMap<Integer, Emp>();

		try {
			con = getConnection();
			stmt  = con.prepareStatement(sql);
			rs = stmt.executeQuery();

			while(rs.next()) {
				Integer empno = rs.getInt("empno");
				String ename = rs.getString("ename");
				String job = rs.getString("job");
				Integer mgr = rs.getInt("mgr");
				Date hiredate = rs.getDate("hiredate");
				Double sal = rs.getDouble("sal");
				Double comm = rs.getDouble("comm");
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

				resultList.put(empno, emp);
			}
			request.setAttribute("resultList", resultList);
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
