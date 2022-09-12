package com.aca.fitnessapp.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.aca.fitnessapp.model.ClientStack;
import com.aca.fitnessapp.model.Type;
import com.aca.fitnessapp.model.BodyPart;

public class StackDaoImpl implements StackDao {

	private final static String getAllClientStacks = "SELECT id, stack, description, updateDateTime, createDateTime "
			+ "FROM client_stacks ";

	@Override
	public List<ClientStack> getClientStacks() {
		List<ClientStack> myClientStacks = new ArrayList<ClientStack>();
		ResultSet result = null;
		Statement statement = null;

		Connection conn = MariaDbUtil.getConnection();
		try {
			statement = conn.createStatement();
			result = statement.executeQuery(getAllClientStacks);
			myClientStacks = makeClientStacks(result);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		/*
		 * the purpose of the close statements is to close or "clean up" the objects
		 * after you're done using them. The finally clause gives you more control over
		 * when that happens and makes the closing happen no matter what. So in order:
		 * the result closes first, then the statement, and then the connection to the
		 * database. If anything else happens, a stack trace is printed.
		 */
		finally {
			try {
				result.close();
				statement.close();
				conn.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return myClientStacks;
	}

//	private List<ClientStack> makeClientStacks(ResultSet result) throws SQLException {
//		List<ClientStack> myClientStacks = new ArrayList<ClientStack>();
//		while (result.next()) {
//			ClientStack clientStack= new ClientStack();
//			
//			clientStack.setId(result.getInt("id"));
//			clientStack.setDescription(result.getString("description"));
//			clientStack.setStack(result.getString("stack"));
//			clientStack.setUpdateDateTime(result.getObject("updateDateTime", LocalDateTime.class));
//			clientStack.setCreateDateTime(result.getObject("createDateTime", LocalDateTime.class));
//
//	
//		}
//		return myClientStacks;
//
//}

	@Override
	public List<ClientStack> makeClientStacks(ResultSet result) throws SQLException {
		List<ClientStack> myClientStacks = new ArrayList<ClientStack>();
		while (result.next()) {

			ClientStack clientStack = new ClientStack();

			clientStack.setId(result.getInt("id"));
			clientStack.setStack(result.getString("stack"));
			clientStack.setDescription(result.getString("description"));
			clientStack.setUpdateDateTime(result.getObject("updateDateTime", LocalDateTime.class));
			clientStack.setCreateDateTime(result.getObject("createDateTime", LocalDateTime.class));
			myClientStacks.add(clientStack);

		}
		return myClientStacks;

	}
}