/**
 * 
 */
package lv.flancer.wmt.xml.resp;

/**
 * Интерфейс X19: Проверка соответствия персональных данных владельца
 * WM-идентификатора.
 * 
 * @author Alex Gusev <flancer64@gmail.com>
 * @version 1.0
 * 
 */
public class X19Response extends AbstractResponse {
	/**
	 * Имя пользователя.
	 */
	private String iName;
	/**
	 * Отчество пользователя.
	 */
	private String oName;
	/**
	 * Уникальный идентификатор ответа, создается при каждом обращении к
	 * интерфейсу.
	 */
	private String retId;

	/**
	 * Имя пользователя.
	 * 
	 * @return Имя пользователя.
	 */
	public String getiName() {
		return iName;
	}

	/**
	 * Отчество пользователя.
	 * 
	 * @return Отчество пользователя.
	 */
	public String getoName() {
		return oName;
	}

	/**
	 * Уникальный идентификатор ответа, создается при каждом обращении к
	 * интерфейсу.
	 * 
	 * @return Уникальный идентификатор ответа, создается при каждом обращении к
	 *         интерфейсу.
	 */
	public String getRetId() {
		return retId;
	}

	/**
	 * Имя пользователя.
	 * 
	 * @param iName
	 *            Имя пользователя.
	 */
	public void setiName(String iName) {
		this.iName = iName;
	}

	/**
	 * Отчество пользователя.
	 * 
	 * @param oName
	 *            Отчество пользователя.
	 */
	public void setoName(String oName) {
		this.oName = oName;
	}

	/**
	 * Уникальный идентификатор ответа, создается при каждом обращении к
	 * интерфейсу.
	 * 
	 * @param retId
	 *            Уникальный идентификатор ответа, создается при каждом
	 *            обращении к интерфейсу.
	 */
	public void setRetId(String retId) {
		this.retId = retId;
	}
}
