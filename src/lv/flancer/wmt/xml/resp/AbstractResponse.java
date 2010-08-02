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
	/**
	 * Номер запроса.
	 */
	private long requestNum;

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

	/**
	 * Номер запроса.
	 * 
	 * @return Номер запроса.
	 */
	public long getRequestNum() {
		return requestNum;
	}

	/**
	 * Номер запроса.
	 * 
	 * @param requestNum
	 *            Номер запроса.
	 */
	public void setRequestNum(long requestNum) {
		this.requestNum = requestNum;
	}

	/**
	 * Номер запроса.
	 * 
	 * @param requestNum
	 *            Номер запроса.
	 */
	public void setRequestNum(String requestNum) {
		this.requestNum = Long.parseLong(requestNum);
	}

}