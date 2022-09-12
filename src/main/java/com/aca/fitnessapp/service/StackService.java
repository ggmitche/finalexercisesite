package com.aca.fitnessapp.service;

import java.util.List;

import com.aca.fitnessapp.dao.StackDao;
import com.aca.fitnessapp.dao.StackDaoImpl;
import com.aca.fitnessapp.model.ClientStack;

public class StackService {

	// private StackDao stackDao = new StackDaoMock();
	private StackDao stackDao = new StackDaoImpl();

	public List<ClientStack> getClientStacks() {
		return stackDao.getClientStacks();
	}
}