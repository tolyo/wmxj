/**
 * 
 */
package lv.flancer.wmt.xml.resp;

import java.util.List;

import lv.flancer.wmt.xml.dict.Trust;

/**
 * Интерфейс X15: Просмотр и изменение текущих настроек управления "по доверию".
 * 
 * @author Alex Gusev <flancer64@gmail.com>
 * @version 1.0
 * 
 */
public class X15ResponseList extends AbstractResponse {
	/**
	 * Количество найденных записей о доверии.
	 */
	private int count;
	/**
	 * Список доверия.
	 */
	private List<Trust> trustList;

	/**
	 * Количество найденных записей о доверии.
	 * 
	 * @return Количество найденных записей о доверии.
	 */
	public int getCount() {
		return count;
	}

	/**
	 * Список доверия.
	 * 
	 * @return Список доверия.
	 */
	public List<Trust> getTrustList() {
		return trustList;
	}

	/**
	 * Количество найденных записей о доверии.
	 * 
	 * @param count
	 *            Количество найденных записей о доверии.
	 */
	public void setCount(int count) {
		this.count = count;
	}

	/**
	 * Количество найденных записей о доверии.
	 * 
	 * @param count
	 *            Количество найденных записей о доверии.
	 */
	public void setCount(String count) {
		this.count = Integer.parseInt(count);
	}

	/**
	 * Список доверия.
	 * 
	 * @param trustList
	 *            Список доверия.
	 */
	public void setTrustList(List<Trust> trustList) {
		this.trustList = trustList;
	}

}
