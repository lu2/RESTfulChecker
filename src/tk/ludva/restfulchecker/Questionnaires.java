package tk.ludva.restfulchecker;

/**
 * Class used for holding and evaluating questionnaires.
 * @author Lu2
 *
 */
public class Questionnaires
{
	// Questions{Client-Server}
	/**
	 * Question 1 definition.
	 */
	private static final String Q1 = "The application is divided to server and client.";
	
	/**
	 * Question 1 correct answer.
	 */
	private static final boolean Q1_correctA = true;

	/**
	 * Question 2 definition.
	 */
	private static final String Q2 = "Clients access the server.";
	
	/**
	 * Question 2 correct answer.
	 */
	private static final boolean Q2_correctA = true;

	/**
	 * Question 3 definition.
	 */
	private static final String Q3 = "The server can serve multiple clients.";
	
	/**
	 * Question 3 correct answer.
	 */
	private static final boolean Q3_correctA = true;
	
	/**
	 * Question 4 definition.
	 */
	private static final String Q4 = "The client initiates connection with server.";
	
	/**
	 * Question 4 correct answer.
	 */
	private static final boolean Q4_correctA = true;
	
	/**
	 * Question 5 definition.
	 */
	private static final String Q5 = "The server initiates connection with client.";
	
	/**
	 * Question 5 correct answer.
	 */
	private static final boolean Q5_correctA = false;
	
	/**
	 * Question 6 definition.
	 */
	private static final String Q6 = "The server provides data.";
	
	/**
	 * Question 6 correct answer.
	 */
	private static final boolean Q6_correctA = true;
	
	/**
	 * Question 7 definition.
	 */
	private static final String Q7 = "The client consumes data.";
	
	/**
	 * Question 7 correct answer.
	 */
	private static final boolean Q7_correctA = true;

	// Questions{Stateless}
	/**
	 * Question 11 definition.
	 */
	private static final String Q11 = "The server can hold client's context.";
	
	/**
	 * Question 11 correct answer.
	 */
	private static final boolean Q11_correctA = false;

	/**
	 * Question 12 definition.
	 */
	private static final String Q12 = "The client can hold server's context.";
	
	/**
	 * Question 12 correct answer.
	 */
	private static final boolean Q12_correctA = true;

	/**
	 * Question 13 definition.
	 */
	private static final String Q13 = "The client keeps entire context of application state.";
	
	/**
	 * Question 13 correct answer.
	 */
	private static final boolean Q13_correctA = true;
	
	/**
	 * Question 14 definition.
	 */
	private static final String Q14 = "The server keeps entire context of application state.";
	
	/**
	 * Question 14 correct answer.
	 */
	private static final boolean Q14_correctA = false;
	
	/**
	 * Question 15 definition.
	 */
	private static final String Q15 = "The server uses sessions and passes session id to client.";
	
	/**
	 * Question 15 correct answer.
	 */
	private static final boolean Q15_correctA = false;
	
	/**
	 * Question 16 definition.
	 */
	private static final String Q16 = "The client sends sessions id in cookies.";
	
	/**
	 * Question 16 correct answer.
	 */
	private static final boolean Q16_correctA = false;

	/**
	 * Question 1 answer.
	 */
	private boolean Q1a;
	
	/**
	 * Question 2 answer.
	 */
	private boolean Q2a;
	
	/**
	 * Question 3 answer.
	 */
	private boolean Q3a;
	
	/**
	 * Question 4 answer.
	 */
	private boolean Q4a;
	
	/**
	 * Question 5 answer.
	 */
	private boolean Q5a;
	
	/**
	 * Question 6 answer.
	 */
	private boolean Q6a;
	
	/**
	 * Question 7 answer.
	 */
	private boolean Q7a;
	
	/**
	 * Question 11 answer.
	 */
	private boolean Q11a;
	
	/**
	 * Question 12 answer.
	 */
	private boolean Q12a;
	
	/**
	 * Question 13 answer.
	 */
	private boolean Q13a;
	
	/**
	 * Question 14 answer.
	 */
	private boolean Q14a;
	
	/**
	 * Question 15 answer.
	 */
	private boolean Q15a;
	
	/**
	 * Question 16 answer.
	 */
	private boolean Q16a;

	public Questionnaires()
	{
		Q1a = Q1_correctA;
		Q2a = Q2_correctA;
		Q3a = Q3_correctA;
		Q4a = Q4_correctA;
		Q5a = Q5_correctA;
		Q6a = Q6_correctA;
		Q7a = Q7_correctA;
		Q11a = Q11_correctA;
		Q12a = Q12_correctA;
		Q13a = Q13_correctA;
		Q14a = Q14_correctA;
		Q15a = Q15_correctA;
		Q16a = Q16_correctA;
	}

	/**
	 * Evaluates answers against correct answers.
	 * @return Text with evaluation result.
	 */
	public String evaluate()
	{
		StringBuilder evaluationResult = new StringBuilder();
		evaluationResult.append("<ul>");
		if (Q1a != Q1_correctA)
		{
			evaluationResult
					.append("<li>You stated that the application is not divided to server and client. This is violation of REST constraint: Client-Server.</li>\n");
		}
		if (Q2a != Q2_correctA)
		{
			evaluationResult
					.append("<li>You stated that clients does not access the server. This is violation of REST constraint: Client-Server.</li>\n");
		}
		if (Q3a != Q3_correctA)
		{
			evaluationResult
					.append("<li>You stated that the server cannot serve multiple clients. This is violation of REST constraint: Client-Server.</li>\n");
		}
		if (Q4a != Q4_correctA)
		{
			evaluationResult
					.append("<li>You stated that the client does not initiate connection with server. This is violation of REST constraint: Client-Server.</li>\n");
		}
		if (Q5a != Q5_correctA)
		{
			evaluationResult
					.append("<li>You stated that the server initiates connection with client. This is violation of REST constraint: Client-Server.</li>\n");
		}
		if (Q6a != Q6_correctA)
		{
			evaluationResult
					.append("<li>You stated that the server does not provide data. This is violation of REST constraint: Client-Server.</li>\n");
		}
		if (Q7a != Q7_correctA)
		{
			evaluationResult
					.append("<li>You stated that the client does not consume data. This is violation of REST constraint: Client-Server.</li>\n");
		}
		
		if (Q11a != Q11_correctA)
		{
			evaluationResult
					.append("<li>You stated that the server can hold client's context. This is violation of REST constraint: Stateless.</li>\n");
		}
		if (Q12a != Q12_correctA)
		{
			evaluationResult
					.append("<li>You stated that the client cannot hold server's context. This is violation of REST constraint: Stateless.</li>\n");
		}
		if (Q13a != Q13_correctA)
		{
			evaluationResult
					.append("<li>You stated that the client does not keep entire context of application state. This is violation of REST constraint: Stateless.</li>\n");
		}
		if (Q14a != Q14_correctA)
		{
			evaluationResult
					.append("<li>You stated that the server keeps entire context of application state. This is violation of REST constraint: Stateless.</li>\n");
		}
		if (Q15a != Q15_correctA)
		{
			evaluationResult
					.append("<li>You stated that the server uses sessions and passes session id to client. This is violation of REST constraint: Stateless.</li>\n");
		}
		if (Q16a != Q16_correctA)
		{
			evaluationResult
					.append("<li>You stated that the client sends sessions id in cookies. This is violation of REST constraint: Stateless.</li>\n");
		}
		
		evaluationResult.append("</ul>");
		String toReturn = evaluationResult.toString();
		if (toReturn.equals("<ul></ul>"))
		{
			return "";
		}
		else
		{
			return toReturn;
		}
	}

	
	/**
	 * Gets answer to question 1.
	 * @return true if yes.
	 */
	public boolean isQ1a()
	{
		return Q1a;
	}

	/**
	 * Sets answer to question 1.
	 * @param q1a true if yes.
	 */
	public void setQ1a(boolean q1a)
	{
		Q1a = q1a;
	}

	
	/**
	 * Gets answer to question 2.
	 * @return true if yes.
	 */
	public boolean isQ2a()
	{
		return Q2a;
	}

	/**
	 * Sets answer to question 2.
	 * @param q2a true if yes.
	 */
	public void setQ2a(boolean q2a)
	{
		Q2a = q2a;
	}

	/**
	 * Gets answer to question 3.
	 * @return true if yes.
	 */
	public boolean isQ3a()
	{
		return Q3a;
	}

	/**
	 * Sets answer to question 3.
	 * @param q3a true if yes.
	 */
	public void setQ3a(boolean q3a)
	{
		Q3a = q3a;
	}

	/**
	 * Gets answer to question 11.
	 * @return true if yes.
	 */
	public boolean isQ11a()
	{
		return Q11a;
	}

	/**
	 * Sets answer to question 11.
	 * @param q11a true if yes.
	 */
	public void setQ11a(boolean q11a)
	{
		Q11a = q11a;
	}

	/**
	 * Gets answer to question 12.
	 * @return true if yes.
	 */
	public boolean isQ12a()
	{
		return Q12a;
	}

	/**
	 * Sets answer to question 12.
	 * @param q12a true if yes.
	 */
	public void setQ12a(boolean q12a)
	{
		Q12a = q12a;
	}

	/**
	 * Gets answer to question 13
	 * @return true if yes.
	 */
	public boolean isQ13a()
	{
		return Q13a;
	}

	/**
	 * Sets answer to question 13.
	 * @param q13a true if yes.
	 */
	public void setQ13a(boolean q13a)
	{
		Q13a = q13a;
	}

	/**
	 * Gets text of question 1.
	 * @return text of question.
	 */
	public String getQ1()
	{
		return Q1;
	}

	/**
	 * Gets text of question 2.
	 * @return text of question.
	 */
	public String getQ2()
	{
		return Q2;
	}

	/**
	 * Gets text of question 3.
	 * @return text of question.
	 */
	public String getQ3()
	{
		return Q3;
	}

	/**
	 * Gets text of question 12.
	 * @return text of question.
	 */
	public String getQ11()
	{
		return Q11;
	}

	/**
	 * Gets text of question 12.
	 * @return text of question.
	 */
	public String getQ12()
	{
		return Q12;
	}

	/**
	 * Gets text of question 13.
	 * @return text of question.
	 */
	public String getQ13()
	{
		return Q13;
	}

	/**
	 * Gets answer to question 4.
	 * @return true if yes.
	 */
	public boolean isQ4a()
	{
		return Q4a;
	}

	/**
	 * Sets answer to question 4.
	 * @param q4a true if yes.
	 */
	public void setQ4a(boolean q4a)
	{
		Q4a = q4a;
	}

	/**
	 * Gets answer to question 5.
	 * @return true if yes.
	 */
	public boolean isQ5a()
	{
		return Q5a;
	}

	/**
	 * Sets answer to question 5.
	 * @param q5a true if yes.
	 */
	public void setQ5a(boolean q5a)
	{
		Q5a = q5a;
	}

	/**
	 * Gets answer to question 6.
	 * @return true if yes.
	 */
	public boolean isQ6a()
	{
		return Q6a;
	}

	/**
	 * Sets answer to question 6.
	 * @param q6a true if yes.
	 */
	public void setQ6a(boolean q6a)
	{
		Q6a = q6a;
	}

	/**
	 * Gets answer to question 7.
	 * @return true if yes.
	 */
	public boolean isQ7a()
	{
		return Q7a;
	}

	/**
	 * Sets answer to question 7.
	 * @param q7a true if yes.
	 */
	public void setQ7a(boolean q7a)
	{
		Q7a = q7a;
	}

	/**
	 * Gets answer to question 14.
	 * @return true if yes.
	 */
	public boolean isQ14a()
	{
		return Q14a;
	}

	/**
	 * Sets answer to question 14.
	 * @param q14a true if yes.
	 */
	public void setQ14a(boolean q14a)
	{
		Q14a = q14a;
	}

	/**
	 * Gets answer to question 15.
	 * @return true if yes.
	 */
	public boolean isQ15a()
	{
		return Q15a;
	}

	/**
	 * Sets answer to question 15.
	 * @param q15a true if yes.
	 */
	public void setQ15a(boolean q15a)
	{
		Q15a = q15a;
	}

	/**
	 * Gets answer to question 16.
	 * @return true if yes.
	 */
	public boolean isQ16a()
	{
		return Q16a;
	}

	/**
	 * Sets answer to question 16.
	 * @param q16a true if yes.
	 */
	public void setQ16a(boolean q16a)
	{
		Q16a = q16a;
	}

	/**
	 * Gets text of question 4.
	 * @return text of question.
	 */
	public String getQ4()
	{
		return Q4;
	}

	/**
	 * Gets text of question 5.
	 * @return text of question.
	 */
	public String getQ5()
	{
		return Q5;
	}

	/**
	 * Gets text of question 6.
	 * @return text of question.
	 */
	public String getQ6()
	{
		return Q6;
	}

	/**
	 * Gets text of question 7.
	 * @return text of question.
	 */
	public String getQ7()
	{
		return Q7;
	}

	/**
	 * Gets text of question 14.
	 * @return text of question.
	 */
	public String getQ14()
	{
		return Q14;
	}

	/**
	 * Gets text of question 15.
	 * @return text of question.
	 */
	public String getQ15()
	{
		return Q15;
	}

	/**
	 * Gets text of question 16.
	 * @return text of question.
	 */
	public String getQ16()
	{
		return Q16;
	}
	
	

}
