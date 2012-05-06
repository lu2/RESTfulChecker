package tk.ludva.restfulchecker.model;

public class ErrsAndWarns
{
	private int errorsCount=0;
	private int warnsCount=0;
	
	public ErrsAndWarns()
	{
		
	}
	
	public void errpp()
	{
		errorsCount++;
	}
	
	public void warnpp()
	{
		warnsCount++;
	}
	
	public void addErr(int count)
	{
		errorsCount = errorsCount + count;
	}
	
	public void addWarn(int count)
	{
		warnsCount = warnsCount + count;
	}

	public int getErrorsCount()
	{
		return errorsCount;
	}

	public void setErrorsCount(int errorsCount)
	{
		this.errorsCount = errorsCount;
	}

	public int getWarnsCount()
	{
		return warnsCount;
	}

	public void setWarnsCount(int warnsCount)
	{
		this.warnsCount = warnsCount;
	}
	
	
}
