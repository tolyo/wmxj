package lv.flancer.wmt.xml.req;

import lv.flancer.wmt.xml.dict.Wmid;

/**
 * Базовый класс для создания запросов.
 * 
 * @author Alex Gusev <flancer64@gmail.com>
 * @version 1.0
 * 
 */
public abstract class AbstractRequest implements XmlRequest {

	/**
	 * Номер запроса. Целое без знака, максимальное количество цифр - 15. Всегда
	 * должен быть больше номера предыдущего запроса на перевод средств!!!
	 */
	protected long requestNum;
	/**
	 * Подпись запроса. Используется только при авторизации с ключами WM Keeper
	 * Classic.
	 */
	protected String sign;
	/**
	 * WMID подписавшего запрос. Используется только при авторизации с ключами
	 * WM Keeper Classic.
	 */
	protected Wmid signerWmid;

	/**
	 * Номер запроса.
	 * 
	 * @return Номер запроса.
	 */
	public long getRequestNum() {
		return requestNum;
	}

	/**
	 * Подпись запроса.
	 * 
	 * @return Подпись запроса.
	 */
	public String getSign() {
		return sign;
	}

	/**
	 * <p>
	 * Подготавливает строку для WMT XML: если "isSubject", то строка не
	 * допускает символов перевода строки, если "!isSubject", то переводы строки
	 * допускаются только в виде "\n".
	 * </p>
	 * <p>
	 * Ограничивает длину строки 'in' количеством символов 'maxLength'. В
	 * случае, если строка превышает 'maxLength', она обрезается, а последние
	 * три символа урезанной строки заменяются на '...'.
	 * </p
	 * 
	 * @param in
	 * @param maxLength
	 * @param isSubject
	 * @return
	 */
	protected String prepareString(String in, int maxLength, boolean isSubject) {
		String result = in;
		if (result != null) {
			result = result.trim();
			// убираем возврат каретки и амперсанд
			result = result.replace("\r", "");
			result = result.replace("&", " and ");
			if (isSubject) {
				// убираем табуляцию, перевод строки
				result = result.replace("\t", " ");
				result = result.replace("\n", " ");
				// убираем сдвоенные пробелы
				while (result.contains("  ")) {
					result = result.replace("  ", " ");
				}
			}
			// ограничиваем длину строки
			int length = result.length();
			if (length > maxLength) {
				if (length >= 3) {
					result = result.substring(0, maxLength - 3) + "...";
				} else {
					result = result.substring(0, maxLength);
				}
			}
		}
		return result;
	}

	/**
	 * @return the signerWmid
	 */
	public Wmid getSignerWmid() {
		return signerWmid;
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
	 * Подпись запроса.
	 * 
	 * @param sign
	 *            Подпись запроса.
	 */
	public void setSign(String sign) {
		this.sign = sign;
	}

	/**
	 * WMID подписавшего запрос.
	 * 
	 * @param signerWmid
	 *            WMID подписавшего запрос.
	 */
	public void setSignerWmid(Wmid signerWmid) {
		this.signerWmid = signerWmid;
	}

}