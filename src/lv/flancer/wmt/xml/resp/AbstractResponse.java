package lv.flancer.wmt.xml.resp;

/**
 * Абстрактный ответ, содержащий общие для всех ответов поля.
 * 
 * @author Alex Gusev <flancer64@gmail.com>
 * @version 1.0
 * 
 */
public abstract class AbstractResponse {

	/**
	 * Расшифровка кода выполнения запроса.
	 */
	private String retDesc;
	/**
	 * Код выполнения запроса.
	 */
	private int retVal;

	public AbstractResponse() {
		super();
	}

	/**
	 * Расшифровка кода выполнения запроса.
	 * 
	 * @return Расшифровка кода выполнения запроса.
	 */
	public String getRetDesc() {
		return retDesc;
	}

	/**
	 * Код выполнения запроса.
	 * 
	 * @return Код выполнения запроса.
	 */
	public int getRetVal() {
		return retVal;
	}

	/**
	 * Расшифровка кода выполнения запроса.
	 * 
	 * @param retDesc
	 *            Расшифровка кода выполнения запроса.
	 */
	public void setRetDesc(String retDesc) {
		this.retDesc = retDesc;
	}

	/**
	 * Код выполнения запроса.
	 * 
	 * @param retVal
	 *            Код выполнения запроса.
	 */
	public void setRetVal(int retVal) {
		this.retVal = retVal;
	}

	/**
	 * Код выполнения запроса.
	 * 
	 * @param retVal
	 *            Код выполнения запроса.
	 */
	public void setRetVal(String retVal) {
		this.retVal = Integer.parseInt(retVal);

	}

}