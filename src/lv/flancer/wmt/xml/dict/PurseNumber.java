/**
 * 
 */
package lv.flancer.wmt.xml.dict;

/**
 * Номер WebMoney-кошелька.
 * 
 * @author Alex Gusev <flancer64@gmail.com>
 * @version 1.0
 * 
 */
public class PurseNumber {
	/**
	 * Номер кошелька, включая префикс: Z123456789012.
	 */
	private String value;

	/**
	 * @param value
	 *            строковое представление номера кошелька.
	 */
	public PurseNumber(String value) {
		this.value = value.toUpperCase();
	}

	/**
	 * Номер кошелька, включая префикс: Z123456789012.
	 * 
	 * @return Номер кошелька, включая префикс: Z123456789012.
	 */
	public String getValue() {
		return value;
	}

	/**
	 * Валидатор правильности номера кошелька: префикс (Z, R, ...) + 12 цифр.
	 */
	public boolean isValid() {
		return this.value.matches("[ZRECDGUB]{1}[0-9]{12}");
	}

	/**
	 * Номер кошелька, включая префикс: Z123456789012.
	 * 
	 * @param value
	 *            Номер кошелька, включая префикс: Z123456789012.
	 */
	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return this.value;
	}
}
