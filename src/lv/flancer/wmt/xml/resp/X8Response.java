/**
 * 
 */
package lv.flancer.wmt.xml.resp;

import lv.flancer.wmt.xml.dict.PurseNumber;
import lv.flancer.wmt.xml.dict.Wmid;

/**
 * Интерфейс X8: Получение информации о принадлежности кошелька. Поиск участника
 * системы по его идентификатору или кошельку.
 * 
 * @author Alex Gusev <flancer64@gmail.com>
 * @version 1.0
 * 
 */
public class X8Response extends AbstractResponse {

	/**
	 * Номер проверяемого кошелька.
	 */
	private PurseNumber purse;
	/**
	 * Проверяемый WM-идентификатор.
	 */
	private Wmid wmid;

	/**
	 * Номер проверяемого кошелька.
	 * 
	 * @return Номер проверяемого кошелька.
	 */
	public PurseNumber getPurse() {
		return purse;
	}

	/**
	 * Проверяемый WM-идентификатор.
	 * 
	 * @return Проверяемый WM-идентификатор.
	 */
	public Wmid getWmid() {
		return wmid;
	}

	/**
	 * Номер проверяемого кошелька.
	 * 
	 * @param purse
	 *            Номер проверяемого кошелька.
	 */
	public void setPurse(PurseNumber purse) {
		this.purse = purse;
	}

	/**
	 * Номер проверяемого кошелька.
	 * 
	 * @param purse
	 *            Номер проверяемого кошелька.
	 */
	public void setPurse(String purse) {
		this.purse = new PurseNumber(purse);
	}

	/**
	 * Проверяемый WM-идентификатор.
	 * 
	 * @param wmid
	 *            Проверяемый WM-идентификатор.
	 */
	public void setWmid(String wmid) {
		this.wmid = new Wmid(wmid);
	}

	/**
	 * Проверяемый WM-идентификатор.
	 * 
	 * @param wmid
	 *            Проверяемый WM-идентификатор.
	 */
	public void setWmid(Wmid wmid) {
		this.wmid = wmid;
	}

}
