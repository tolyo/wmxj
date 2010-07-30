/**
 * 
 */
package lv.flancer.wmt.xml.dict;

/**
 * WMID - числовой идентификатор пользователя WebMoney, состоящий из 12-ти цифр.
 * 
 * @author Alex Gusev <flancer64@gmail.com>
 * @version 1.0
 * 
 */
public class Wmid {
	/**
	 * Значение WM-идентификатора.
	 */
	private String value;

	public Wmid(String value) {
		this.value = value;
	}

	/**
	 * Значение WM-идентификатора.
	 * 
	 * @return Значение WM-идентификатора.
	 */
	public String getValue() {
		return value;
	}

	/**
	 * Значение WM-идентификатора.
	 * 
	 * @param value
	 *            Значение WM-идентификатора.
	 */
	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return this.value;
	}
}
