package com.aca.fitnessapp.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.aca.fitnessapp.model.ClientStack;

public interface StackDao {

	public List<ClientStack> getClientStacks();
	

	public List<ClientStack> makeClientStacks(ResultSet result) throws SQLException;
	

}
