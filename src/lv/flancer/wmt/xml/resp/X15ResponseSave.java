/**
 * 
 */
package lv.flancer.wmt.xml.resp;

import lv.flancer.wmt.xml.dict.PurseNumber;
import lv.flancer.wmt.xml.dict.Wmid;

/**
 * Интерфейс X15: Просмотр и изменение текущих настроек управления "по доверию".
 * 
 * @author Alex Gusev <flancer64@gmail.com>
 * @version 1.0
 * 
 */
public class X15ResponseSave extends AbstractResponse {

	/**
	 * Разрешена или нет ВМ-идентификатору 'master' выписка счетов на доверяемый
	 * кошелек 'purse'.
	 */
	private boolean hasInvRight;
	/**
	 * Разрешен или нет ВМ-идентификатору 'master' просмотр баланса на
	 * доверяемом кошельке 'purse'.
	 */
	private boolean hasPurseRight;
	/**
	 * Разрешен или нет ВМ-идентификатору 'master' просмотр истории операций
	 * кошелька 'purse'.
	 */
	private boolean hasTransHistRight;
	/**
	 * Разрешены или нет ВМ-идентификатору 'master' переводы средств по доверию
	 * с доверяемого кошелька 'purse'.
	 */
	private boolean hasTransRight;
	/**
	 * Уникальный номер доверия в системе учета WebMoney.
	 */
	private long id;
	/**
	 * ВМ-идентификатор, которому доверено совершать какие-либо действия с
	 * кошельком purse.
	 */
	private Wmid master;
	/**
	 * Кошелек, над которым идентификатору 'master' разрешено совершать
	 * какие-либо действия.
	 */
	private PurseNumber purse;

	/**
	 * Уникальный номер доверия в системе учета WebMoney.
	 * 
	 * @return Уникальный номер доверия в системе учета WebMoney.
	 */
	public long getId() {
		return id;
	}

	/**
	 * ВМ-идентификатор, которому доверено совершать какие-либо действия с
	 * кошельком purse.
	 * 
	 * @return ВМ-идентификатор, которому доверено совершать какие-либо действия
	 *         с кошельком purse.
	 */
	public Wmid getMaster() {
		return master;
	}

	/**
	 * Кошелек, над которым идентификатору 'master' разрешено совершать
	 * какие-либо действия.
	 * 
	 * @return Кошелек, над которым идентификатору 'master' разрешено совершать
	 *         какие-либо действия.
	 */
	public PurseNumber getPurse() {
		return purse;
	}

	/**
	 * Разрешена или нет ВМ-идентификатору 'master' выписка счетов на доверяемый
	 * кошелек 'purse'.
	 * 
	 * @return Разрешена или нет ВМ-идентификатору 'master' выписка счетов на
	 *         доверяемый кошелек 'purse'.
	 */
	public boolean hasInvRight() {
		return hasInvRight;
	}

	/**
	 * Разрешен или нет ВМ-идентификатору 'master' просмотр баланса на
	 * доверяемом кошельке 'purse'.
	 * 
	 * @return Разрешен или нет ВМ-идентификатору 'master' просмотр баланса на
	 *         доверяемом кошельке 'purse'.
	 */
	public boolean hasPurseRight() {
		return hasPurseRight;
	}

	/**
	 * Разрешен или нет ВМ-идентификатору 'master' просмотр истории операций
	 * кошелька 'purse'.
	 * 
	 * @return Разрешен или нет ВМ-идентификатору 'master' просмотр истории
	 *         операций кошелька 'purse'.
	 */
	public boolean hasTransHistRight() {
		return hasTransHistRight;
	}

	/**
	 * Разрешены или нет ВМ-идентификатору 'master' переводы средств по доверию
	 * с доверяемого кошелька 'purse'.
	 * 
	 * @return Разрешены или нет ВМ-идентификатору 'master' переводы средств по
	 *         доверию с доверяемого кошелька 'purse'.
	 */
	public boolean hasTransRight() {
		return hasTransRight;
	}

	/**
	 * Разрешена или нет ВМ-идентификатору 'master' выписка счетов на доверяемый
	 * кошелек 'purse'.
	 * 
	 * @param hasInvRight
	 *            Разрешена или нет ВМ-идентификатору 'master' выписка счетов на
	 *            доверяемый кошелек 'purse'.
	 */
	public void setHasInvRight(boolean hasInvRight) {
		this.hasInvRight = hasInvRight;
	}

	/**
	 * Разрешена (не '0') или нет ('0') ВМ-идентификатору 'master' выписка
	 * счетов на доверяемый кошелек 'purse'.
	 * 
	 * @param hasInvRight
	 *            Разрешена (не '0') или нет ('0') ВМ-идентификатору 'master'
	 *            выписка счетов на доверяемый кошелек 'purse'.
	 */
	public void setHasInvRight(String hasInvRight) {
		this.hasInvRight = !hasInvRight.equals("0");
	}

	/**
	 * Разрешен или нет ВМ-идентификатору 'master' просмотр баланса на
	 * доверяемом кошельке 'purse'.
	 * 
	 * @param hasPurseRight
	 *            Разрешен или нет ВМ-идентификатору 'master' просмотр баланса
	 *            на доверяемом кошельке 'purse'.
	 */
	public void setHasPurseRight(boolean hasPurseRight) {
		this.hasPurseRight = hasPurseRight;
	}

	/**
	 * Разрешен (не '0') или нет ('0') ВМ-идентификатору 'master' просмотр
	 * баланса на доверяемом кошельке 'purse'.
	 * 
	 * @param hasPurseRight
	 *            Разрешен (не '0') или нет ('0') ВМ-идентификатору 'master'
	 *            просмотр баланса на доверяемом кошельке 'purse'.
	 */
	public void setHasPurseRight(String hasPurseRight) {
		this.hasPurseRight = !hasPurseRight.equals("0");
	}

	/**
	 * Разрешен или нет ВМ-идентификатору 'master' просмотр истории операций
	 * кошелька 'purse'.
	 * 
	 * @param hasTransHistRight
	 *            Разрешен или нет ВМ-идентификатору 'master' просмотр истории
	 *            операций кошелька 'purse'.
	 */
	public void setHasTransHistRight(boolean hasTransHistRight) {
		this.hasTransHistRight = hasTransHistRight;
	}

	/**
	 * Разрешен (не '0') или нет ('0') ВМ-идентификатору 'master' просмотр
	 * истории операций кошелька 'purse'.
	 * 
	 * @param hasTransHistRight
	 *            Разрешен (не '0') или нет ('0') ВМ-идентификатору 'master'
	 *            просмотр истории операций кошелька 'purse'.
	 */
	public void setHasTransHistRight(String hasTransHistRight) {
		this.hasTransHistRight = !hasTransHistRight.equals("0");
	}

	/**
	 * Разрешены или нет ВМ-идентификатору 'master' переводы средств по доверию
	 * с доверяемого кошелька 'purse'.
	 * 
	 * @param hasTransRight
	 *            Разрешены или нет ВМ-идентификатору 'master' переводы средств
	 *            по доверию с доверяемого кошелька 'purse'.
	 */
	public void setHasTransRight(boolean hasTransRight) {
		this.hasTransRight = hasTransRight;
	}

	/**
	 * Разрешены (не '0') или нет ('0') ВМ-идентификатору 'master' переводы
	 * средств по доверию с доверяемого кошелька 'purse'.
	 * 
	 * @param hasTransRight
	 *            Разрешены (не '0') или нет ('0') ВМ-идентификатору 'master'
	 *            переводы средств по доверию с доверяемого кошелька 'purse'.
	 */
	public void setHasTransRight(String hasTransRight) {
		this.hasTransRight = !hasTransRight.equals("0");
	}

	/**
	 * Уникальный номер доверия в системе учета WebMoney.
	 * 
	 * @param id
	 *            Уникальный номер доверия в системе учета WebMoney.
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * Уникальный номер доверия в системе учета WebMoney.
	 * 
	 * @param id
	 *            Уникальный номер доверия в системе учета WebMoney.
	 */
	public void setId(String id) {
		this.id = Long.parseLong(id);
	}

	/**
	 * ВМ-идентификатор, которому доверено совершать какие-либо действия с
	 * кошельком purse.
	 * 
	 * @param master
	 *            ВМ-идентификатор, которому доверено совершать какие-либо
	 *            действия с кошельком purse.
	 */
	public void setMaster(String master) {
		this.master = new Wmid(master);
	}

	/**
	 * ВМ-идентификатор, которому доверено совершать какие-либо действия с
	 * кошельком purse.
	 * 
	 * @param master
	 *            ВМ-идентификатор, которому доверено совершать какие-либо
	 *            действия с кошельком purse.
	 */
	public void setMaster(Wmid master) {
		this.master = master;
	}

	/**
	 * Кошелек, над которым идентификатору 'master' разрешено совершать
	 * какие-либо действия.
	 * 
	 * @param purse
	 *            Кошелек, над которым идентификатору 'master' разрешено
	 *            совершать какие-либо действия.
	 */
	public void setPurse(PurseNumber purse) {
		this.purse = purse;
	}

	/**
	 * Кошелек, над которым идентификатору 'master' разрешено совершать
	 * какие-либо действия.
	 * 
	 * @param purse
	 *            Кошелек, над которым идентификатору 'master' разрешено
	 *            совершать какие-либо действия.
	 */
	public void setPurse(String purse) {
		this.purse = new PurseNumber(purse);
	}

}
