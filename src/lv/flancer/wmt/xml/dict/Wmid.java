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

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Wmid)) {
			return false;
		}
		Wmid other = (Wmid) obj;
		return (value.equals(other.getValue()));

	}

	/**
	 * Значение WM-идентификатора.
	 * 
	 * @return Значение WM-идентификатора.
	 */
	public String getValue() {
		return value;
	}

	@Override
	public int hashCode() {
		return this.toString().hashCode();
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
