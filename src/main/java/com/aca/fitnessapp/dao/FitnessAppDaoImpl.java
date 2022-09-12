package com.aca.fitnessapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.aca.fitnessapp.model.BodyPart;
import com.aca.fitnessapp.model.BodyPartTotal;
import com.aca.fitnessapp.model.Exercise;
import com.aca.fitnessapp.model.Type;

/*The purpose of this is to use Java code to link the SQL database to the Tomcat server and test through Postman.
 * The code used in Java can be used to update and view the database in its current iteration
 */
public class FitnessAppDaoImpl implements FitnessAppDao {

	// line 21-22 is a variable to cut down on repetition for other SQL statements
	private static String columnNames = " exerciseId, title, bodyPartId, exerciseTypeId, updateDateTime, addedDateTime ";

	// This section is for the SQL statements that will be implemented in the core
	// java methods below
	private static String selectAllExercises = "SELECT exerciseId, title, bodyPartId, exerciseTypeId, updateDateTime, addedDateTime "
			+ " FROM exercises";

	private static String selectExercisesByBodyPart = "SELECT exerciseId, title, bodyPartId, exerciseTypeId, updateDateTime, addedDateTime "
			+ " FROM exercises" + " WHERE bodyPartId LIKE ? ";

	private static String selectExercisesByTitle = "SELECT exerciseId, title, bodyPartId, exerciseTypeId, updateDateTime, addedDateTime "
			+ " FROM exercises" + " WHERE title LIKE ? ";

	private static String selectExercisesById =

			"SELECT exerciseId, title, bodyPartId, exerciseTypeId, updateDateTime, addedDateTime " + " FROM exercises"
					+ " WHERE exerciseId = ? ";

	private static String selectExercisesByType =

			"SELECT exerciseId, title, bodyPartId, exerciseTypeId, updateDateTime, addedDateTime " + " FROM exercises"
					+ " WHERE exerciseTypeId = ? ";

	private static String deleteExerciseById = "DELETE FROM exercises " + " WHERE exerciseId = ? ";

	private static String insertExercise = "INSERT INTO exercises (title, bodyPartId, exerciseTypeId) " + "VALUES "
			+ "(?, ?, ?)";

	private static String updateExerciseById = "UPDATE exercises " + " SET title = ?, " + " bodyPartId = ?, "
			+ " exerciseTypeId = ? " + " WHERE exerciseId = ? ";

	private static String selectNewExerciseId = "SELECT LAST_INSERT_ID() AS 'exerciseId' "; // aliasing the last
																							// exercise's id as
																							// exerciseId so that you
																							// can return it back to the
																							// client

	@Override
	public List<Exercise> getExercises() {
		List<Exercise> myExercises = new ArrayList<Exercise>();
		ResultSet result = null;
		Statement statement = null;

		Connection conn = MariaDbUtil.getConnection();
		try {
			statement = conn.createStatement();
			result = statement.executeQuery(selectAllExercises);
			myExercises = makeExercises(result);
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

		return myExercises;
	}

	private List<Exercise> makeExercises(ResultSet result) throws SQLException {
		List<Exercise> myExercises = new ArrayList<Exercise>();
		while (result.next()) {
			Exercise exercise = new Exercise();
			exercise.setTitle(result.getString("title"));
			exercise.setExerciseId(result.getInt("exerciseId"));

			/*
			 * NOTE:Because SQL does not deal with Enum's (which Type and BodyPart are
			 * here), you have to make special code to convert the SQL String data into the
			 * enums of Type and BodyPart
			 */
			String bodyPartString = result.getString("bodyPartId");
			BodyPart bodyPart = BodyPart.convertStringToBodyPart(bodyPartString);

			String exerciseTypeString = result.getString("exerciseTypeId");
			Type exerciseType = Type.convertStringToType(exerciseTypeString);

			// using jdbc class to instantiate objects of LocalDateTime and map them to the
			// date time elements in the SQL database

			exercise.setUpdateDate(result.getObject("updateDateTime", LocalDateTime.class));
			exercise.setAddedDateTime(result.getObject("addedDateTime", LocalDateTime.class));

			exercise.setBodyPart(bodyPart);
			exercise.setExerciseType(exerciseType);
			myExercises.add(exercise);
		}
		return myExercises;

	}

	@Override
	public List<Exercise> getExercisesByBodyPart(BodyPart bodyPart) {
		List<Exercise> myExercises = new ArrayList<Exercise>();
		ResultSet result = null;
		PreparedStatement ps = null;

		Connection conn = MariaDbUtil.getConnection(); // Connection variable lets you hold onto it

		try {
			ps = conn.prepareStatement(selectExercisesByBodyPart);
			ps.setString(1, bodyPart.toString()); // Substitutes the questionmark in the query with genre starting at
													// row
													// one and iterates through them all
													// selecting the ones that match the genre in the URL request
													// test with
													// http://localhost:8080/fitnessapp/webapi/exercises/bodypart/*whatever
													// bodypart*
			result = ps.executeQuery(); // instantiates the result set and makes it the result of the query
			myExercises = makeExercises(result);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				result.close();
				ps.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return myExercises;
	}

	@Override
	public List<Exercise> getExercisesByType(Type exerciseType) {
		List<Exercise> myExercises = new ArrayList<Exercise>();
		ResultSet result = null;
		PreparedStatement ps = null;

		Connection conn = MariaDbUtil.getConnection(); // Connection variable lets you hold onto it

		try {
			ps = conn.prepareStatement(selectExercisesByType);
			ps.setString(1, exerciseType.toString()); // Substitutes the questionmark in the query with genre starting
														// at row
			// one and iterates through them all
			// selecting the ones that match the genre in the URL request
			// test with
			// http://localhost:8080/fitnessapp/webapi/exercises/exercisetype/*ExerciseTypeValue*
			result = ps.executeQuery(); // instantiates the result set and makes it the result of the query
			myExercises = makeExercises(result);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				result.close();
				ps.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return myExercises;
	}

	@Override
	public List<Exercise> getExercisesById(Integer exerciseId) {
		List<Exercise> myExercises = new ArrayList<Exercise>();
		ResultSet result = null;
		PreparedStatement ps = null;

		Connection conn = MariaDbUtil.getConnection(); // Connection variable lets you hold onto it

		try {
			ps = conn.prepareStatement(selectExercisesById);
			ps.setInt(1, exerciseId); // replaces the question mark with %'s surrounding the title for the select
										// statement
										// note: choosing to pass a variable as a query param through the controller
										// will affect how the logic has to be coded elsewhere

			result = ps.executeQuery();
			myExercises = makeExercises(result);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				result.close();
				ps.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return myExercises;
	}

	@Override
	public List<Exercise> getExercisesByTitle(String title) {
		List<Exercise> myExercises = new ArrayList<Exercise>();
		ResultSet result = null;
		PreparedStatement ps = null;

		Connection conn = MariaDbUtil.getConnection(); // Connection variable lets you hold onto it

		try {
			ps = conn.prepareStatement(selectExercisesByTitle);
			ps.setString(1, "%" + title + "%"); // replaces the question marks with %'s surrounding the title for the
												// select statement
			// note: choosing to pass a variable as a query param through the controller
			// will affect how the logic has to be coded elsewhere

			result = ps.executeQuery();
			myExercises = makeExercises(result);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				result.close();
				ps.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return myExercises;
	}

	
// getReport not necessary at this time.  Implement once many exercises have been created across multiple months.
//	@Override
//	public List<Exercise> getReport(LocalDateTime addedDate, LocalDateTime updateDate) { // test with GET. DOES NOT WORK
//																							// IN POSTMAN ON URL PATH.
//																							// FIX IT
//		List<Exercise> myExercises = new ArrayList<Exercise>();
//		ResultSet result = null;
//		PreparedStatement ps = null;
//
//		Connection conn = MariaDbUtil.getConnection(); // Connection variable lets you hold onto it
//
//		try {
//			ps = conn.prepareStatement(selectExercisesByRangeOfAddedDate);
//			ps.setInt(1, startAddedDate);
//			ps.setInt(2, endAddedDate);
//
//			result = ps.executeQuery();
//			myExercises = makeExercises(result);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				result.close();
//				ps.close();
//				conn.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//		return myExercises;
//	}

	@Override
	public Exercise createExercise(Exercise exercise) {
		// Note that with this insertion method, the new exercises lack an id, update date,
		// and added date

		// test in postman with POST request with body attributes matching parameters
		// below. Switch to a GET to make sure it was added to the list
		int updateRowCount = 0;
		PreparedStatement ps = null;

		Connection conn = MariaDbUtil.getConnection();
		try {
			ps = conn.prepareStatement(insertExercise);
			ps.setString(1, exercise.getTitle());
			ps.setString(2, exercise.getBodyPart().toString());
			ps.setString(3, exercise.getExerciseType().toString());
			updateRowCount = ps.executeUpdate();
			System.out.println("rows inserted: " + updateRowCount);

			// gets new exerciseId and adds it to the exercise object before returning it
			// back to the client
			int exerciseId = this.getNewExerciseId(conn);
			exercise.setExerciseId(exerciseId);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				ps.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return exercise;
	}

	private int getNewExerciseId(Connection conn) { // uses the same connection object the client uses to start the java
													// code to retrieve the created id
													// done like this because other connection objects could return
													// different results

		ResultSet rs = null;
		Statement statement = null;
		int newExerciseId = 0;

		try {
			statement = conn.createStatement();
			rs = statement.executeQuery(selectNewExerciseId); // remember that you want to hold onto this result, hence
																// the assignment operator
			while (rs.next()) {
				newExerciseId = rs.getInt("exerciseId"); // passing in the exerciseId column alias so that you can return
															// it
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				statement.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return newExerciseId; // returning it
	}

	@Override
	public Exercise updateExercise(Exercise updateExercise) {
		List<Exercise> exercises = this.getExercisesById(updateExercise.getExerciseId());

		if (exercises.size() > 0) {
			int updateRowCount = 0; // instantiating update row count before updating

			PreparedStatement ps = null;

			Connection conn = MariaDbUtil.getConnection();
			try {
				ps = conn.prepareStatement(updateExerciseById);
				ps.setString(1, updateExercise.getTitle());
				ps.setString(2, updateExercise.getBodyPart().toString());
				ps.setString(3, updateExercise.getExerciseType().toString());
				ps.setInt(4, updateExercise.getExerciseId());
				updateRowCount = ps.executeUpdate();
				System.out.println("rows updated: " + updateRowCount);
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					ps.close();
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}
		return updateExercise;
	}

	@Override
	public Exercise deleteExercise(Integer exerciseId) {
	List<Exercise> exercises = this.getExercisesById(exerciseId); //finds the exercise to be deleted and returns it first
	Exercise exerciseToDelete = null;  //always code this part first and test before adding actual deletion logic
	
	if (exercises.size() > 0) {
		exerciseToDelete = exercises.get(0);

		int updateRowCount = 0; // instantiating update row count before deletion
		PreparedStatement ps = null;

		Connection conn = MariaDbUtil.getConnection();
		try {
			ps = conn.prepareStatement(deleteExerciseById);
			ps.setInt(1, exerciseId);
			updateRowCount = ps.executeUpdate();
			System.out.println("rows delete: " + updateRowCount);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				ps.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}
		return exerciseToDelete;
	}

	@Override
	public List<BodyPartTotal> getBodyPartTotals() {
		List<BodyPartTotal> myTotals = new ArrayList<BodyPartTotal>();
		
		BodyPartTotal abs = new BodyPartTotal();
		abs.setBodyPart(BodyPart.Abs.toString());
		
		return myTotals;
	}

}