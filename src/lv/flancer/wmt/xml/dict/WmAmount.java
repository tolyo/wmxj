package lv.flancer.wmt.xml.dict;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

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

	public WmAmount(double value) {
		BigDecimal tmp = new BigDecimal(value);
		this.amount = tmp.setScale(SCALE, RoundingMode.HALF_UP);
	}

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

	/**
	 * Число с плавающей точкой (разделитель - . /точка/) и для десяти с
	 * половиной может выглядеть так: 10.5; незначащие нули в конце и точка,
	 * если число целое, должны отсутствовать, например, 10.50 - не верно, 10.5
	 * - верно, 9. - не верно, 9 - верно)
	 * 
	 * @return Значение суммы, форматированное в соответствии с правилами
	 *         WebMoney.
	 */
	public String getWmFormated() {
		DecimalFormat df = new DecimalFormat();
		df.setGroupingUsed(false);
		DecimalFormatSymbols dec = DecimalFormatSymbols.getInstance();
		dec.setDecimalSeparator('.');
		df.setDecimalFormatSymbols(dec);
		return df.format(this.getAmount());
	}
}
