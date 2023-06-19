package local.hal.night.javawebscottadmin.emp;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
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
import local.hal.night.javawebscottadmin.entity.Emp;

/**
 * JavaWebScottAdmin Practice Src11
 *
 * 従業員情報更新。
 * @author yuyas
 *
 */
@WebServlet(name="EmpEditServlet",urlPatterns= {"/emp/edit"})
public class EmpEditServlet extends ParentServlet{

	boolean isNumericSal;
	boolean isNumericComm;

	@Override
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
		//JSP PATH
		String jspPath = "/emp/empEdit.jsp";
		//Redirect Psth
		String redirectPath = "/javawebscottadmin/emp/showList";
		boolean isRedirect = false;

		//Char Set
		request.setCharacterEncoding("UTF-8");

		//JSP getParameter
		String editEmpEmpno = request.getParameter("editEmpEmpno");
		String editEmpEname = request.getParameter("editEmpEname");
		String editEmpJob = request.getParameter("editEmpJob");
		String editEmpMgr = request.getParameter("editEmpMgr");
		String editEmpHiredate = request.getParameter("editEmpHiredate");
		String editEmpSal = request.getParameter("editEmpSal");
		String editEmpComm = request.getParameter("editEmpComm");
		String editEmpDeptno = request.getParameter("editEmpDeptno");


		//trim()
		editEmpEname = editEmpEname.trim();
		editEmpJob = editEmpJob.trim();

		//List...validationMsgs
		List<String> validationMsgs = new ArrayList<String>();

		//Emp emp
		Emp emp = new Emp();

		//int ...set.double...set
		int editEmpEmpnoInt = Integer.parseInt(editEmpEmpno);
		int editEmpMgrInt = Integer.parseInt(editEmpMgr);
		double editEmpSalDouble = Double.parseDouble(editEmpSal);
		double editEmpCommDouble = Double.parseDouble(editEmpComm);
		int editEmpDeptnoInt = Integer.parseInt(editEmpDeptno);

		Date editEmpHiredateDate= Date.valueOf(editEmpHiredate);

		//emp.set...()
			emp.setEmpno(editEmpEmpnoInt);
			emp.setEname(editEmpEname);
			emp.setJob(editEmpJob);
			emp.setMgr(editEmpMgrInt);
			emp.setSal(editEmpSalDouble);
			emp.setComm(editEmpCommDouble);
			emp.setDeptno(editEmpDeptnoInt);
			emp.setHiredate(editEmpHiredateDate);


		//validation
		if(editEmpEmpno.equals("")) {
			validationMsgs.add("従業員番号の入力は必須です。");
		}else if(editEmpEmpno.length() != 4) {
			validationMsgs.add("4桁の整数で入力してください。");
		}

		if(editEmpEname.equals("")) {
			validationMsgs.add("従業員名の入力は必須です。");
		}

		if(editEmpMgr.equals("")) {
			validationMsgs.add("上司番号の入力は必須です。");
		}

		if(editEmpJob.equals("")) {
			validationMsgs.add("役職の入力は必須です。");
		}

		if(editEmpHiredate.equals("")) {
			validationMsgs.add("雇用日の入力は必須です。");
		}

		isNumericSal =  editEmpSal.matches("[+-]?\\d*(\\.\\d+)?");
		if(editEmpSal.equals("")) {
			validationMsgs.add("給与の入力は必須です。");
		}else if(isNumericSal = false) {
			validationMsgs.add("給与は数値で入力してください。");
		}

		isNumericComm = editEmpComm.matches("[+-]?\\d*(\\.\\d+)?");
		if(editEmpComm.equals("")) {
			validationMsgs.add("歩合の入力は必須です。");
		}else if(isNumericComm = false) {
			validationMsgs.add("歩合は数値で入力してください。");
		}

		if(editEmpDeptno.equals("")) {
			validationMsgs.add("部門番号の入力は必須です。");
		}

		Connection con = null;
		PreparedStatement stmt =null;
		String sql = "UPDATE emp SET ename=?,job=?,mgr=?,hiredate=?,sal=?,comm=?,deptno=? WHERE empno = ?";

		try {
			con = getConnection();
			if(validationMsgs.isEmpty()) {
				stmt = con.prepareStatement(sql);
				stmt.setString(1, emp.getEname());
				stmt.setString(2, emp.getJob());
				stmt.setInt(3, emp.getMgr());
				stmt.setDate(4, emp.getHiredate());
				stmt.setDouble(5, emp.getSal());
				stmt.setDouble(6, emp.getComm());
				stmt.setInt(7, emp.getDeptno());
				stmt.setInt(8, emp.getEmpno());

				int result = stmt.executeUpdate();
				if(result < 1) {
					jspPath = ERROR_JSP;
					request.setAttribute("errorMsg", "情報更新に失敗しました。もう一度はじめからやり直してください。");
				}
				else {
					isRedirect = true;
				}
			}
			else {
				request.setAttribute("validationMsgs", validationMsgs);
				request.setAttribute("emp", emp);
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
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException{
		doPost(request,response);
	}

}
