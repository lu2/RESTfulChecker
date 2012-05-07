package tk.ludva.restfulchecker.model;

/**
 * Class for holding information about errors and warnings.
 * @author Lu2
 *
 */
public class ErrsAndWarns
{
	/**
	 * Errors count.
	 */
	private int errorsCount = 0;
	
	/**
	 * Warnings count.
	 */
	private int warnsCount = 0;

	public ErrsAndWarns()
	{

	}

	/**
	 * Adds 1 to errors count.
	 */
	public void errpp()
	{
		errorsCount++;
	}

	/**
	 * Adds 1 to warnings count.
	 */
	public void warnpp()
	{
		warnsCount++;
	}

	/**
	 * Adds specified amount to errors count.
	 * @param count amount to be added.
	 */
	public void addErr(int count)
	{
		errorsCount = errorsCount + count;
	}

	/**
	 * Adds specified amount to warnings count.
	 * @param count amount to be added.
	 */
	public void addWarn(int count)
	{
		warnsCount = warnsCount + count;
	}

	/**
	 * Gets number of errors.
	 * @return errors count.
	 */
	public int getErrorsCount()
	{
		return errorsCount;
	}

	/**
	 * Sets number of errors.
	 * @param errorsCount errors count to be set.
	 */
	public void setErrorsCount(int errorsCount)
	{
		this.errorsCount = errorsCount;
	}

	/**
	 * Gets number of warnings.
	 * @return warnings count.
	 */
	public int getWarnsCount()
	{
		return warnsCount;
	}

	/**
	 * Sets number of warnings.
	 * @param warnsCount warnings count to be set.
	 */
	public void setWarnsCount(int warnsCount)
	{
		this.warnsCount = warnsCount;
	}

}
