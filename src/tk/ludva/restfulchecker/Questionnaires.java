package tk.ludva.restfulchecker;

public class Questionnaires
{
	//Question{Client-Server}
	private static final String Q1 = "Does your application providing service for other machines (clients)?";
	private static final boolean Q1_correctA = true;
	
	private static final String Q2 = "Is that service available to more then one client?";
	private static final boolean Q2_correctA = true;
	
	private static final String Q3 = "Does your application listening and responding for request on that service?";
	private static final boolean Q3_correctA = true;

	//Question{Stateless}
	private static final String Q11 = "Does your server part of application keeping any client's context while communicating with client?";
	private static final boolean Q11_correctA = false;
	
	private static final String Q12 = "Does the client keeping all the needed context while communicating with your application?";
	private static final boolean Q12_correctA = true;
	
	private static final String Q13 = "Is one client request enough for your application to create appropriate response?";
	private static final boolean Q13_correctA = true;
	
	private boolean Q1a;
	private boolean Q2a;
	private boolean Q3a;
	private boolean Q11a;
	private boolean Q12a;
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
	
	public String evaluate()
	{
		StringBuilder evaluationResult = new StringBuilder();
		evaluationResult.append("<ul>");
		if (Q1a != Q1_correctA)
		{
			evaluationResult.append("<li>You stated, that your application doesn't providing service for other machines (clients). This is violation of REST constraint: Client-Server.</li>\n");
		}
		if (Q2a != Q2_correctA)
		{
			evaluationResult.append("<li>You stated, that your service isn't available to more then one client. This is violation of REST constraint: Client-Server.</li>\n");
		}
		if (Q3a != Q3_correctA)
		{
			evaluationResult.append("<li>You stated, that your server part of application doesn't listening and responding for requests. This is violation of REST constraint: Client-Server.</li>\n");
		}
		
		if (Q11a != Q11_correctA)
		{
			evaluationResult.append("<li>You stated, that your server part of application is keeping client's context while communicating with client. This is violation of REST constraint: Stateless.</li>\n");
		}
		
		if (Q12a != Q12_correctA)
		{
			evaluationResult.append("<li>You stated, that the client doesn't keeps all the needed context while communicating with your application. This is violation of REST constraint: Stateless.</li>\n");
		}
		if (Q13a != Q13_correctA)
		{
			evaluationResult.append("<li>You stated, that one client request isn't enough for your application to create appropriate response. This is violation of REST constraint: Stateless.</li>\n");
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

	public boolean isQ1a()
	{
		return Q1a;
	}

	public void setQ1a(boolean q1a)
	{
		Q1a = q1a;
	}

	public boolean isQ2a()
	{
		return Q2a;
	}

	public void setQ2a(boolean q2a)
	{
		Q2a = q2a;
	}

	public boolean isQ3a()
	{
		return Q3a;
	}

	public void setQ3a(boolean q3a)
	{
		Q3a = q3a;
	}

	public boolean isQ11a()
	{
		return Q11a;
	}

	public void setQ11a(boolean q11a)
	{
		Q11a = q11a;
	}

	public boolean isQ12a()
	{
		return Q12a;
	}

	public void setQ12a(boolean q12a)
	{
		Q12a = q12a;
	}

	public boolean isQ13a()
	{
		return Q13a;
	}

	public void setQ13a(boolean q13a)
	{
		Q13a = q13a;
	}

	public String getQ1()
	{
		return Q1;
	}

	public String getQ2()
	{
		return Q2;
	}

	public String getQ3()
	{
		return Q3;
	}

	public String getQ11()
	{
		return Q11;
	}

	public String getQ12()
	{
		return Q12;
	}

	public String getQ13()
	{
		return Q13;
	}
	
	
}
