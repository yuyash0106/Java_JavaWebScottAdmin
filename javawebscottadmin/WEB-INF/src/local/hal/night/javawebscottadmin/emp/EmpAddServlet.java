package local.hal.night.javawebscottadmin.emp;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
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
 * JavaWebScottAdmin Practice Src08
 *
 * 従業員情報登録。
 * @author yuyas
 *
 */
@WebServlet(name = "EmpAddServlet", urlPatterns = { "/emp/add" })
public class EmpAddServlet extends ParentServlet {

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//JSP PATH SET
		String jspPath = "/emp/empAdd.jsp";

		//REDIRECT PATH SET
		String redirectPath = "/javawebscottadmin/emp/showList";

		//REDIRECT 不可
		boolean isRedirect = false;

		//文字コードセット
		request.setCharacterEncoding("UTF-8");

		//JSPから値を受け取る
		String addEmpEmpno = request.getParameter("addEmpEmpno");
		String addEmpEname = request.getParameter("addEmpEname");
		String addEmpJob = request.getParameter("addEmpJob");
		String addEmpMgr = request.getParameter("addEmpMgr");
		String addEmpHiredate = request.getParameter("addEmpHiredate");
		String addEmpSal = request.getParameter("addEmpSal");
		String addEmpComm = request.getParameter("addEmpComm");
		String addEmpDeptno = request.getParameter("addEmpDeptno");

		//値の成形
		addEmpEname = addEmpEname.trim();
		addEmpJob = addEmpJob.trim();

		//ヴァリデーションメッセージ変数を用意。
		List<String> validationMsgs = new ArrayList<String>();

		boolean isNumericEmpno;
		boolean isNumericMgr;
		boolean isNumericComm;
		boolean isNumericSal;
		boolean isNumericDeptno;

		//従業員番号のヴァリデーション
		int addEmpEmpnoInt = 0;
		isNumericEmpno = addEmpEmpno.matches("[+-]?\\d*(\\.\\d+)?");
		if (addEmpEmpno.equals("")) {
			validationMsgs.add("従業員番号の入力は必須です。");
		} else if (addEmpEmpno.length() != 4) {
			validationMsgs.add("従業員番号は4桁の整数で入力してください。");
		} else if (isNumericEmpno = false) {
			validationMsgs.add("従業員番号は数値で入力してください。");
		} else {
			try {
				addEmpEmpnoInt = Integer.parseInt(addEmpEmpno);
			} catch (NumberFormatException ex) {
				validationMsgs.add("従業員番号は4桁の整数で入力してください。");
			} catch (Exception ex) {
				validationMsgs.add("入力された従業員番号はすでに存在します。別の従業員番号を入力してください。");
			}
		}

		//従業員名のヴァリデーション
		if (addEmpEname.equals("")) {
			validationMsgs.add("従業員名の入力は必須です。");
		}

		//役職JOBのヴァリデーション
		if (addEmpJob.equals("")) {
			validationMsgs.add("役職の入力は必須です。");
		}
		//上司番号Mgrのヴァリデーション
		int addEmpMgrInt = 0;
		isNumericMgr = addEmpMgr.matches("[+-]?\\d*(\\.\\d+)?");
		if (addEmpMgr.equals("")) {
			validationMsgs.add("上司番号の入力は必須です。");
		} else if (isNumericMgr = false) {
			validationMsgs.add("上司番号は数値で入力してください。");
		} else {
			try {
				addEmpMgrInt = Integer.parseInt(addEmpMgr);
			} catch (NumberFormatException ex) {
				validationMsgs.add("上司番号は4桁で入力してください。");
			}
		}
		//雇用日Hiredateのヴァリデーション

		if (addEmpHiredate.equals("")) {
			validationMsgs.add("雇用日の入力は必須です。");
		}

		//給与Salのヴァリデーション
		double addEmpSalDouble = 0;
		isNumericSal = addEmpSal.matches("[+-]?\\d*(\\.\\d+)?");
		if (addEmpSal.equals("")) {
			validationMsgs.add("給与の入力は必須です。");
		} else if (isNumericSal = false) {
			validationMsgs.add("給与は数値で入力してください。");
		} else {
			try {
				addEmpSalDouble = Integer.parseInt(addEmpSal);
			} catch (NumberFormatException ex) {
				validationMsgs.add("給与は数値で入力してください。");
			}
		}
		//歩合Commのヴァリデーション
		double addEmpCommDouble = 0;
		isNumericComm = addEmpComm.matches("[+-]?\\d*(\\.\\d+)?");
		if (addEmpComm.equals("")) {
			validationMsgs.add("歩合の入力は必須です。");
		} else if (isNumericComm = false) {
			validationMsgs.add("歩合は数値で入力してください。");
		} else {
			try {
				addEmpCommDouble = Integer.parseInt(addEmpComm);
			} catch (NumberFormatException ex) {
				validationMsgs.add("歩合は数値で入力してください。");
			}
		}
		//部門番号Deptnoのヴァリデーション
		int addEmpDeptnoInt = 0;
		isNumericDeptno = addEmpDeptno.matches("[+-]?\\d*(\\.\\d+)?");
		if (addEmpDeptno.equals("")) {
			validationMsgs.add("部門番号の入力は必須です。");
		} else if (addEmpDeptno.length() != 2) {
			validationMsgs.add("部門番号は2桁の整数で入力してください。");
		} else if (isNumericDeptno = false) {
			validationMsgs.add("部門番号は数値で入力してください。");
		} else {
			try {
				addEmpDeptnoInt = Integer.parseInt(addEmpDeptno);
			} catch (NumberFormatException ex) {
				validationMsgs.add("部門番号は2桁の整数で入力してください。");
			}
		}

		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		String aqlSelect = "SELECT COUNT(*) count from emp WHERE empno = ?";
		String sqlInsert = "INSERT INTO emp (empno,ename,job,mgr,hiredate,sal,comm,deptno) VALUES "
				+ "(?,?,?,?,?,?,?,?)";

		try {
			con = getConnection();
			stmt = con.prepareStatement(aqlSelect);
			stmt.setInt(1, addEmpEmpnoInt);
			rs = stmt.executeQuery();
			int count = 0;
			if (rs.next()) {
				count = rs.getInt("count");
			}
			if (count > 0) {
				validationMsgs.add("その従業員番号はすでに使われています。別のものを指定してください。");
			}

			if (validationMsgs.isEmpty()) {
				close(rs);
				close(stmt);

				Date addEmpHiredateDate = Date.valueOf(addEmpHiredate);

				stmt = con.prepareStatement(sqlInsert);
				stmt.setInt(1, addEmpEmpnoInt);
				stmt.setString(2, addEmpEname);
				stmt.setString(3, addEmpJob);
				stmt.setInt(4, addEmpMgrInt);
				stmt.setString(5, addEmpHiredate);
				stmt.setDouble(6, addEmpSalDouble);
				stmt.setDouble(7, addEmpCommDouble);
				stmt.setInt(8, addEmpDeptnoInt);

				int result = stmt.executeUpdate();
				if (result < 1) {
					jspPath = ERROR_JSP;
					request.setAttribute("errorMsg", "情報登録に失敗しました。もう一度はじめからやり直してください。");
				} else {
					isRedirect = true;
				}
			} else {
				request.setAttribute("validationMsgs", validationMsgs);
				request.setAttribute("addEmpEmpno", addEmpEmpno);
				request.setAttribute("addEmpEname", addEmpEname);
				request.setAttribute("addEmpJob", addEmpJob);
				request.setAttribute("addEmpMgr", addEmpMgr);
				request.setAttribute("addEmpHiredate", addEmpHiredate);
				request.setAttribute("addEmpSal", addEmpSal);
				request.setAttribute("addEmpComm", addEmpComm);
				request.setAttribute("addEmpDepyno", addEmpDeptno);
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

		if (isRedirect) {
			response.sendRedirect(redirectPath);
		} else {
			RequestDispatcher rd = request.getRequestDispatcher(jspPath);
			rd.forward(request, response);
		}
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		doPost(request, response);
	}

}
