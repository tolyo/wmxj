package lv.flancer.wmt.xml.dict;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Представление сумм в WebMoney.
 * 
 * @author Alex Gusev <flancer64@gmail.com>
 * @version 1.0
 * 
 */
public class WmAmount {
	private static final int SCALE = 2;
	/**
	 * Значение суммы.
	 */
	private BigDecimal amount;

	public WmAmount(String value) {
		BigDecimal tmp = new BigDecimal(value);
		this.amount = tmp.setScale(SCALE, RoundingMode.HALF_UP);
	}

	/**
	 * Значение суммы.
	 * 
	 * @return Значение суммы.
	 */
	public BigDecimal getAmount() {
		return amount;
	}

	/**
	 * Значение суммы.
	 * 
	 * @param amount
	 *            Значение суммы.
	 */
	public void setAmount(BigDecimal amount) {
		this.amount = amount.setScale(SCALE, RoundingMode.HALF_UP);
	}

	/**
	 * Значение суммы.
	 * 
	 * @param amount
	 *            Значение суммы.
	 */
	public void setAmount(float amount) {
		BigDecimal tmp = new BigDecimal(amount);
		this.amount = tmp.setScale(SCALE, RoundingMode.HALF_UP);
	}

	/**
	 * Значение суммы.
	 * 
	 * @param amount
	 *            Значение суммы.
	 */
	public void setAmount(String amount) {
		BigDecimal tmp = new BigDecimal(amount);
		this.amount = tmp.setScale(SCALE, RoundingMode.HALF_UP);
	}

	@Override
	public String toString() {
		return amount.toPlainString();
	}
}
