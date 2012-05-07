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
	private static final String Q1 = "Does your application providing service for other machines (clients)?";
	
	/**
	 * Question 1 correct answer.
	 */
	private static final boolean Q1_correctA = true;

	/**
	 * Question 2 definition.
	 */
	private static final String Q2 = "Is that service available to more then one client?";
	
	/**
	 * Question 2 correct answer.
	 */
	private static final boolean Q2_correctA = true;

	/**
	 * Question 3 definition.
	 */
	private static final String Q3 = "Does your application listening and responding for request on that service?";
	
	/**
	 * Question 3 correct answer.
	 */
	private static final boolean Q3_correctA = true;

	// Questions{Stateless}
	/**
	 * Question 11 definition.
	 */
	private static final String Q11 = "Does your server part of application keeping any client's context while communicating with client?";
	
	/**
	 * Question 11 correct answer.
	 */
	private static final boolean Q11_correctA = false;

	/**
	 * Question 12 definition.
	 */
	private static final String Q12 = "Does the client keeping all the needed context while communicating with your application?";
	
	/**
	 * Question 12 correct answer.
	 */
	private static final boolean Q12_correctA = true;

	/**
	 * Question 13 definition.
	 */
	private static final String Q13 = "Is one client request enough for your application to create appropriate response?";
	
	/**
	 * Question 13 correct answer.
	 */
	private static final boolean Q13_correctA = true;

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

	public Questionnaires()
	{
		Q1a = Q1_correctA;
		Q2a = Q2_correctA;
		Q3a = Q3_correctA;
		Q11a = Q11_correctA;
		Q12a = Q12_correctA;
		Q13a = Q13_correctA;
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
					.append("<li>You stated, that your application doesn't providing service for other machines (clients). This is violation of REST constraint: Client-Server.</li>\n");
		}
		if (Q2a != Q2_correctA)
		{
			evaluationResult
					.append("<li>You stated, that your service isn't available to more then one client. This is violation of REST constraint: Client-Server.</li>\n");
		}
		if (Q3a != Q3_correctA)
		{
			evaluationResult
					.append("<li>You stated, that your server part of application doesn't listening and responding for requests. This is violation of REST constraint: Client-Server.</li>\n");
		}

		if (Q11a != Q11_correctA)
		{
			evaluationResult
					.append("<li>You stated, that your server part of application is keeping client's context while communicating with client. This is violation of REST constraint: Stateless.</li>\n");
		}

		if (Q12a != Q12_correctA)
		{
			evaluationResult
					.append("<li>You stated, that the client doesn't keeps all the needed context while communicating with your application. This is violation of REST constraint: Stateless.</li>\n");
		}
		if (Q13a != Q13_correctA)
		{
			evaluationResult
					.append("<li>You stated, that one client request isn't enough for your application to create appropriate response. This is violation of REST constraint: Stateless.</li>\n");
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

}
