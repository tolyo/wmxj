/**
 * 
 */
package lv.flancer.wmt.xml.dict;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Параметры доверия (интерфейс X15).
 * 
 * @author Alex Gusev <flancer64@gmail.com>
 * @version 1.0
 * 
 */
public class Trust {
	/**
	 * Суточный лимит.
	 */
	private WmAmount dayLimit;
	/**
	 * Дневной лимит.
	 */
	private WmAmount dlimit;
	/**
	 * Дневная сумма.
	 */
	private WmAmount dsum;
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
	 * Дата проведения идентификатором 'master' последнего перевода по доверию с
	 * кошелька 'purse'.
	 */
	private Date lastSumDate;
	/**
	 * ВМ-идентификатор, которому доверено совершать какие-либо действия с
	 * кошельком purse.
	 */
	private Wmid master;
	/**
	 * Месячный лимит.
	 */
	private WmAmount mlimit;
	/**
	 * Месячная сумма.
	 */
	private WmAmount msum;
	/**
	 * Кошелек, над которым идентификатору 'master' разрешено совершать
	 * какие-либо действия.
	 */
	private PurseNumber purse;
	/**
	 * Недельный лимит.
	 */
	private WmAmount wlimit;
	/**
	 * Недельная сумма.
	 */
	private WmAmount wsum;

	/**
	 * Суточный лимит.
	 * 
	 * @return Суточный лимит.
	 */
	public WmAmount getDayLimit() {
		return dayLimit;
	}

	/**
	 * Дневной лимит.
	 * 
	 * @return Дневной лимит.
	 */
	public WmAmount getDlimit() {
		return dlimit;
	}

	/**
	 * Дневная сумма.
	 * 
	 * @return Дневная сумма.
	 */
	public WmAmount getDsum() {
		return dsum;
	}

	/**
	 * Уникальный номер доверия в системе учета WebMoney.
	 * 
	 * @return Уникальный номер доверия в системе учета WebMoney.
	 */
	public long getId() {
		return id;
	}

	/**
	 * Дата проведения идентификатором 'master' последнего перевода по доверию с
	 * кошелька 'purse'.
	 * 
	 * @return Дата проведения идентификатором 'master' последнего перевода по
	 *         доверию с кошелька 'purse'.
	 */
	public Date getLastSumDate() {
		return lastSumDate;
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
	 * Месячный лимит.
	 * 
	 * @return Месячный лимит.
	 */
	public WmAmount getMlimit() {
		return mlimit;
	}

	/**
	 * Месячная сумма.
	 * 
	 * @return Месячная сумма.
	 */
	public WmAmount getMsum() {
		return msum;
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
	 * Недельный лимит.
	 * 
	 * @return Недельный лимит.
	 */
	public WmAmount getWlimit() {
		return wlimit;
	}

	/**
	 * Недельная сумма.
	 * 
	 * @return Недельная сумма.
	 */
	public WmAmount getWsum() {
		return wsum;
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
	 * Суточный лимит.
	 * 
	 * @param daylimit
	 *            Суточный лимит.
	 */
	public void setDayLimit(String daylimit) {
		this.dayLimit = new WmAmount(daylimit);
	}

	/**
	 * Суточный лимит.
	 * 
	 * @param daylimit
	 *            Суточный лимит.
	 */
	public void setDayLimit(WmAmount daylimit) {
		this.dayLimit = daylimit;
	}

	/**
	 * Дневной лимит.
	 * 
	 * @param dlimit
	 *            Дневной лимит.
	 */
	public void setDlimit(String dlimit) {
		this.dlimit = new WmAmount(dlimit);
	}

	/**
	 * Дневной лимит.
	 * 
	 * @param dlimit
	 *            Дневной лимит.
	 */
	public void setDlimit(WmAmount dlimit) {
		this.dlimit = dlimit;
	}

	/**
	 * Дневная сумма.
	 * 
	 * @param dsum
	 *            Дневная сумма.
	 */
	public void setDsum(String dsum) {
		this.dsum = new WmAmount(dsum);
	}

	/**
	 * Дневная сумма.
	 * 
	 * @param dsum
	 *            Дневная сумма.
	 */
	public void setDsum(WmAmount dsum) {
		this.dsum = dsum;
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
	 * Дата проведения идентификатором 'master' последнего перевода по доверию с
	 * кошелька 'purse'.
	 * 
	 * @param lastsumdate
	 *            Дата проведения идентификатором 'master' последнего перевода
	 *            по доверию с кошелька 'purse'.
	 */
	public void setLastSumDate(String lastsumdate) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
		try {
			this.lastSumDate = sdf.parse(lastsumdate);
		} catch (ParseException e) {
			this.lastSumDate = null;
			e.printStackTrace();
		}
	}

	/**
	 * Дата проведения идентификатором 'master' последнего перевода по доверию с
	 * кошелька 'purse'.
	 * 
	 * @param lastsumdate
	 *            Дата проведения идентификатором 'master' последнего перевода
	 *            по доверию с кошелька 'purse'.
	 */
	public void setLastSumDate(Date lastsumdate) {
		this.lastSumDate = lastsumdate;
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
	 * Месячный лимит.
	 * 
	 * @param mlimit
	 *            Месячный лимит.
	 */
	public void setMlimit(String mlimit) {
		this.mlimit = new WmAmount(mlimit);
	}

	/**
	 * Месячный лимит.
	 * 
	 * @param mlimit
	 *            Месячный лимит.
	 */
	public void setMlimit(WmAmount mlimit) {
		this.mlimit = mlimit;
	}

	/**
	 * Месячная сумма.
	 * 
	 * @param msum
	 *            Месячная сумма.
	 */
	public void setMsum(String msum) {
		this.msum = new WmAmount(msum);
	}

	/**
	 * Месячная сумма.
	 * 
	 * @param msum
	 *            Месячная сумма.
	 */
	public void setMsum(WmAmount msum) {
		this.msum = msum;
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

	/**
	 * Недельный лимит.
	 * 
	 * @param wlimit
	 *            Недельный лимит.
	 */
	public void setWlimit(String wlimit) {
		this.wlimit = new WmAmount(wlimit);
	}

	/**
	 * Недельный лимит.
	 * 
	 * @param wlimit
	 *            Недельный лимит.
	 */
	public void setWlimit(WmAmount wlimit) {
		this.wlimit = wlimit;
	}

	/**
	 * Недельная сумма.
	 * 
	 * @param wsum
	 *            Недельная сумма.
	 */
	public void setWsum(String wsum) {
		this.wsum = new WmAmount(wsum);
	}

	/**
	 * Недельная сумма.
	 * 
	 * @param wsum
	 *            Недельная сумма.
	 */
	public void setWsum(WmAmount wsum) {
		this.wsum = wsum;
	}
}
