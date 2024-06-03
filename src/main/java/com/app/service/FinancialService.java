package com.app.service;

import java.util.ArrayList;

import com.app.dao.FinancialDao;
import com.app.model.FinancialAccount;

public class FinancialService {
	private FinancialDao financialDao = new FinancialDao();

    public void saveFinancialAccount(FinancialAccount account) {
    	if(account.getId() == null) {
    		financialDao.add(account);
    	} else {
    		financialDao.update(account);
    	}
    }
    
    public ArrayList<FinancialAccount> getAllRecords() {
        return financialDao.getAll();
    }
    
    public void getAccount(FinancialAccount account) {
    	financialDao.getAccountById(account);
    }

    public void delete(int id) {
    	financialDao.delete(id);
    }
}
