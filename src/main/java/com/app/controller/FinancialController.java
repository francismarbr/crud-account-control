package com.app.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.app.model.FinancialAccount;
import com.app.service.FinancialService;

/**
 * Servlet implementation class FinancialController
 */
@WebServlet(urlPatterns = {"/financial", "/add", "/edit", "/update", "/delete"})
public class FinancialController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private FinancialService financialService = new FinancialService();

    public FinancialController() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		String action = request.getServletPath();
		
		if (action.equals("/financial")) {
			list(request, response);
		} else if (action.equals("/add")) {
			add(request, response);
		} else if (action.equals("/edit")) {
			select(request, response);
		} else if (action.equals("/update")) {
			update(request, response);
		} else if (action.equals("/delete")) {
			delete(request, response);
		} else {
			
		}
	}
	
	protected void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<FinancialAccount> list = financialService.getAllRecords();
		request.setAttribute("accounts", list);
		
		Double totalCredit = 0.0;
		Double totalDebit = 0.0;
		
		for(FinancialAccount account : list) {
			if(account.getType().equals("credito")) {
				totalCredit += account.getValue();
			} else if (account.getType().equals("debito")) {
				totalDebit += account.getValue();
			}
		}
		
		request.setAttribute("totalCredit", totalCredit);
		request.setAttribute("totalDebit", totalDebit);
		
		RequestDispatcher rd = request.getRequestDispatcher("account-list.jsp");
		rd.forward(request, response);
	}
	
	protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		FinancialAccount account = new FinancialAccount();
		
		String valueForm = request.getParameter("value");
		double value = Double.parseDouble(valueForm);
		
		String dateForm = request.getParameter("date");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = dateFormat.parse(dateForm);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		account.setDate(date);
		account.setType(request.getParameter("type"));
		account.setDescription(request.getParameter("description"));
		account.setPerson(request.getParameter("person"));
		account.setValue(value);
		
		financialService.saveFinancialAccount(account);
				
		response.sendRedirect("financial");
	}
	
	protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		FinancialAccount account = new FinancialAccount();
		
		String valueForm = request.getParameter("value");
		double value = Double.parseDouble(valueForm);
		
		String dateForm = request.getParameter("date");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = dateFormat.parse(dateForm);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		String idAccount = request.getParameter("id");	 

        try {
            int id = Integer.parseInt(idAccount);
            account.setId(id);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
		
		account.setDate(date);
		account.setType(request.getParameter("type"));
		account.setDescription(request.getParameter("description"));
		account.setPerson(request.getParameter("person"));
		account.setValue(value);
		
		financialService.saveFinancialAccount(account);
		
		response.sendRedirect("financial");
	}
	
	protected void select(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		FinancialAccount account = new FinancialAccount();
		
		String idAccount = request.getParameter("id");	 

        try {
            int id = Integer.parseInt(idAccount);
            account.setId(id);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        
        financialService.getAccount(account);
        
        request.setAttribute("id", account.getId());
        request.setAttribute("date", account.getDate());
        request.setAttribute("type", account.getType());
        request.setAttribute("description", account.getDescription());
        request.setAttribute("person", account.getPerson());
        request.setAttribute("value", account.getValue());
        
        RequestDispatcher rd = request.getRequestDispatcher("account-registration.jsp");
        rd.forward(request, response);

	}
	
	protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		String idAccount = request.getParameter("id");
		
		if(idAccount != null && !idAccount.isEmpty()) {
			try {
				int id = Integer.parseInt(idAccount);
				financialService.delete(id);
				response.sendRedirect("financial");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
