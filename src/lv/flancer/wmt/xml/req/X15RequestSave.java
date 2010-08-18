/**
 * 
 */
package lv.flancer.wmt.xml.req;

import lv.flancer.wmt.xml.dict.PurseNumber;
import lv.flancer.wmt.xml.dict.WmAmount;
import lv.flancer.wmt.xml.dict.Wmid;

/**
 * @author Alex Gusev <flancer64@gmail.com>
 * @version 1.0
 * 
 */
public class X15RequestSave extends AbstractRequest {
	/**
	 * Дневной лимит.
	 */
	private WmAmount dayLimit;
	/**
	 * Разрешена или нет ВМ-идентификатору 'masterwmid' выписка счетов на
	 * доверяемый кошелек 'purse'.
	 */
	private boolean hasInvRight;
	/**
	 * Разрешен или нет ВМ-идентификатору 'masterwmid' просмотр баланса на
	 * доверяемом кошельке 'purse'.
	 */
	private boolean hasPurseRight;
	/**
	 * Разрешен или нет ВМ-идентификатору 'masterwmid' просмотр истории операций
	 * кошелька 'purse'.
	 */
	private boolean hasTransHistRight;
	/**
	 * Разрешены или нет ВМ-идентификатору 'masterwmid' переводы средств по
	 * доверию с доверяемого кошелька 'purse'.
	 */
	private boolean hasTransRight;
	/**
	 * Суточный лимит.
	 */
	private WmAmount limit;
	/**
	 * ВМ-идентификатор, которому идентификатор 'slaveWmid' данным запросом
	 * разрешает или запрещает (в зависимости от атрибутов тега trust)
	 * управление своим кошельком 'purse'.
	 */
	private Wmid masterWmid;
	/**
	 * Месячный лимит.
	 */
	private WmAmount monthLimit;
	/**
	 * Кошелек, принадлежащий идентификатору 'slaveWmid', на который
	 * устанавливается доверие.
	 */
	private PurseNumber purse;
	/**
	 * ВМ-идентификатор, который доверяет идентификатору 'masterWmid' данным
	 * запросом (в зависимости от атрибутов тега trust) управление своим
	 * кошельком 'purse'.
	 */
	private Wmid slaveWmid;
	/**
	 * Недельный лимит.
	 */
	private WmAmount weekLimit;

	/**
	 * Дневной лимит.
	 * 
	 * @return Дневной лимит.
	 */
	public WmAmount getDayLimit() {
		return dayLimit;
	}

	/**
	 * Суточный лимит.
	 * 
	 * @return Суточный лимит.
	 */
	public WmAmount getLimit() {
		return limit;
	}

	/**
	 * ВМ-идентификатор, которому идентификатор 'slaveWmid' данным запросом
	 * разрешает или запрещает (в зависимости от атрибутов тега trust)
	 * управление своим кошельком 'purse'.
	 * 
	 * @return ВМ-идентификатор, которому идентификатор 'slaveWmid' данным
	 *         запросом разрешает или запрещает (в зависимости от атрибутов тега
	 *         trust) управление своим кошельком 'purse'.
	 */
	public Wmid getMasterWmid() {
		return masterWmid;
	}

	/**
	 * Месячный лимит.
	 * 
	 * @return Месячный лимит.
	 */
	public WmAmount getMonthLimit() {
		return monthLimit;
	}

	/**
	 * Кошелек, принадлежащий идентификатору 'slaveWmid', на который
	 * устанавливается доверие.
	 * 
	 * @return Кошелек, принадлежащий идентификатору 'slaveWmid', на который
	 *         устанавливается доверие.
	 */
	public PurseNumber getPurse() {
		return purse;
	}

	/**
	 * ВМ-идентификатор, который доверяет идентификатору 'masterWmid' данным
	 * запросом (в зависимости от атрибутов тега trust) управление своим
	 * кошельком 'purse'.
	 * 
	 * @return ВМ-идентификатор, который доверяет идентификатору 'masterWmid'
	 *         данным запросом (в зависимости от атрибутов тега trust)
	 *         управление своим кошельком 'purse'.
	 */
	public Wmid getSlaveWmid() {
		return slaveWmid;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see lv.flancer.wmt.xml.req.XmlRequest#getTextToSign()
	 */
	@Override
	public String getTextToSign() {
		String result = "";
		if (this.signerWmid != null)
			result += this.signerWmid;
		result += this.purse;
		result += this.masterWmid;
		result += this.requestNum;
		return result;
	}

	/**
	 * Недельный лимит.
	 * 
	 * @return Недельный лимит.
	 */
	public WmAmount getWeekLimit() {
		return weekLimit;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see lv.flancer.wmt.xml.req.XmlRequest#getXmlRequest()
	 */
	@Override
	public String getXmlRequest() {
		String result = "<?xml version=\"1.0\"  encoding=\"windows-1251\"?>";
		result += "<w3s.request>";
		result += "<reqn>" + this.requestNum + "</reqn>";
		if (this.signerWmid != null)
			result += "<wmid>" + this.signerWmid + "</wmid>";
		if (this.sign != null)
			result += "<sign>" + this.sign + "</sign>";
		// trust
		char inv = (this.hasInvRight) ? '1' : '0';
		char trans = (this.hasTransRight) ? '1' : '0';
		char purse = (this.hasPurseRight) ? '1' : '0';
		char transHist = (this.hasTransHistRight) ? '1' : '0';
		result += "<trust inv=\"" + inv + "\" trans=\"" + trans + "\" purse=\""
				+ purse + "\" transhist=\"" + transHist + "\">";
		result += "<masterwmid>" + this.masterWmid + "</masterwmid>";
		result += "<slavewmid>" + this.slaveWmid + "</slavewmid>";
		result += "<purse>" + this.purse + "</purse>";
		result += "<limit>" + this.limit + "</limit>";
		result += "<daylimit>" + this.dayLimit + "</daylimit>";
		result += "<weeklimit>" + this.weekLimit + "</weeklimit>";
		result += "<monthlimit>" + this.monthLimit + "</monthlimit>";
		result += "</trust>";
		result += "</w3s.request>";
		return result;
	}

	/**
	 * Разрешена или нет ВМ-идентификатору 'masterwmid' выписка счетов на
	 * доверяемый кошелек 'purse'.
	 * 
	 * @return Разрешена или нет ВМ-идентификатору 'masterwmid' выписка счетов
	 *         на доверяемый кошелек 'purse'.
	 */
	public boolean hasInvRight() {
		return hasInvRight;
	}

	/**
	 * Разрешен или нет ВМ-идентификатору 'masterwmid' просмотр баланса на
	 * доверяемом кошельке 'purse'.
	 * 
	 * @return Разрешен или нет ВМ-идентификатору 'masterwmid' просмотр баланса
	 *         на доверяемом кошельке 'purse'.
	 */
	public boolean hasPurseRight() {
		return hasPurseRight;
	}

	/**
	 * Разрешен или нет ВМ-идентификатору 'masterwmid' просмотр истории операций
	 * кошелька 'purse'.
	 * 
	 * @return Разрешен или нет ВМ-идентификатору 'masterwmid' просмотр истории
	 *         операций кошелька 'purse'.
	 */
	public boolean hasTransHistRight() {
		return hasTransHistRight;
	}

	/**
	 * Разрешены или нет ВМ-идентификатору 'masterwmid' переводы средств по
	 * доверию с доверяемого кошелька 'purse'.
	 * 
	 * @return Разрешены или нет ВМ-идентификатору 'masterwmid' переводы средств
	 *         по доверию с доверяемого кошелька 'purse'.
	 */
	public boolean hasTransRight() {
		return hasTransRight;
	}

	/**
	 * Дневной лимит.
	 * 
	 * @param dayLimit
	 *            Дневной лимит.
	 */
	public void setDayLimit(double dayLimit) {
		this.dayLimit = new WmAmount(dayLimit);
	}

	/**
	 * Дневной лимит.
	 * 
	 * @param dayLimit
	 *            Дневной лимит.
	 */
	public void setDayLimit(WmAmount dayLimit) {
		this.dayLimit = dayLimit;
	}

	/**
	 * Разрешена или нет ВМ-идентификатору 'masterwmid' выписка счетов на
	 * доверяемый кошелек 'purse'.
	 * 
	 * @param hasInvRight
	 *            Разрешена или нет ВМ-идентификатору 'masterwmid' выписка
	 *            счетов на доверяемый кошелек 'purse'.
	 */
	public void setHasInvRight(boolean hasInvRight) {
		this.hasInvRight = hasInvRight;
	}

	/**
	 * Разрешена (не '0') или нет ('0') ВМ-идентификатору 'masterwmid' выписка
	 * счетов на доверяемый кошелек 'purse'.
	 * 
	 * @param hasInvRight
	 *            Разрешена (не '0') или нет ('0') ВМ-идентификатору
	 *            'masterwmid' выписка счетов на доверяемый кошелек 'purse'.
	 */
	public void setHasInvRight(String hasInvRight) {
		this.hasInvRight = !hasInvRight.equals("0");
	}

	/**
	 * Разрешен или нет ВМ-идентификатору 'masterwmid' просмотр баланса на
	 * доверяемом кошельке 'purse'.
	 * 
	 * @param hasPurseRight
	 *            Разрешен или нет ВМ-идентификатору 'masterwmid' просмотр
	 *            баланса на доверяемом кошельке 'purse'.
	 */
	public void setHasPurseRight(boolean hasPurseRight) {
		this.hasPurseRight = hasPurseRight;
	}

	/**
	 * Разрешен (не '0') или нет ('0') ВМ-идентификатору 'masterwmid' просмотр
	 * баланса на доверяемом кошельке 'purse'.
	 * 
	 * @param hasPurseRight
	 *            Разрешен (не '0') или нет ('0') ВМ-идентификатору 'masterwmid'
	 *            просмотр баланса на доверяемом кошельке 'purse'.
	 */
	public void setHasPurseRight(String hasPurseRight) {
		this.hasPurseRight = !hasPurseRight.equals("0");
	}

	/**
	 * Разрешен или нет ВМ-идентификатору 'masterwmid' просмотр истории операций
	 * кошелька 'purse'.
	 * 
	 * @param hasTransHistRight
	 *            Разрешен или нет ВМ-идентификатору 'masterwmid' просмотр
	 *            истории операций кошелька 'purse'.
	 */
	public void setHasTransHistRight(boolean hasTransHistRight) {
		this.hasTransHistRight = hasTransHistRight;
	}

	/**
	 * Разрешен (не '0') или нет ('0') ВМ-идентификатору 'masterwmid' просмотр
	 * истории операций кошелька 'purse'.
	 * 
	 * @param hasTransHistRight
	 *            Разрешен (не '0') или нет ('0') ВМ-идентификатору 'masterwmid'
	 *            просмотр истории операций кошелька 'purse'.
	 */
	public void setHasTransHistRight(String hasTransHistRight) {
		this.hasTransHistRight = !hasTransHistRight.equals("0");
	}

	/**
	 * Разрешены или нет ВМ-идентификатору 'masterwmid' переводы средств по
	 * доверию с доверяемого кошелька 'purse'.
	 * 
	 * @param hasTransRight
	 *            Разрешены или нет ВМ-идентификатору 'masterwmid' переводы
	 *            средств по доверию с доверяемого кошелька 'purse'.
	 */
	public void setHasTransRight(boolean hasTransRight) {
		this.hasTransRight = hasTransRight;
	}

	/**
	 * Разрешены (не '0') или нет ('0') ВМ-идентификатору 'masterwmid' переводы
	 * средств по доверию с доверяемого кошелька 'purse'.
	 * 
	 * @param hasTransRight
	 *            Разрешены (не '0') или нет ('0') ВМ-идентификатору
	 *            'masterwmid' переводы средств по доверию с доверяемого
	 *            кошелька 'purse'.
	 */
	public void setHasTransRight(String hasTransRight) {
		this.hasTransRight = !hasTransRight.equals("0");
	}

	/**
	 * Суточный лимит.
	 * 
	 * @param limit
	 *            Суточный лимит.
	 */
	public void setLimit(double limit) {
		this.limit = new WmAmount(limit);
	}

	/**
	 * Суточный лимит.
	 * 
	 * @param limit
	 *            Суточный лимит.
	 */
	public void setLimit(WmAmount limit) {
		this.limit = limit;
	}

	/**
	 * ВМ-идентификатор, которому идентификатор 'slaveWmid' данным запросом
	 * разрешает или запрещает (в зависимости от атрибутов тега trust)
	 * управление своим кошельком 'purse'.
	 * 
	 * @param masterWmid
	 *            ВМ-идентификатор, которому идентификатор 'slaveWmid' данным
	 *            запросом разрешает или запрещает (в зависимости от атрибутов
	 *            тега trust) управление своим кошельком 'purse'.
	 */
	public void setMasterWmid(String masterWmid) {
		this.masterWmid = new Wmid(masterWmid);
	}

	/**
	 * ВМ-идентификатор, которому идентификатор 'slaveWmid' данным запросом
	 * разрешает или запрещает (в зависимости от атрибутов тега trust)
	 * управление своим кошельком 'purse'.
	 * 
	 * @param masterWmid
	 *            ВМ-идентификатор, которому идентификатор 'slaveWmid' данным
	 *            запросом разрешает или запрещает (в зависимости от атрибутов
	 *            тега trust) управление своим кошельком 'purse'.
	 */
	public void setMasterWmid(Wmid masterWmid) {
		this.masterWmid = masterWmid;
	}

	/**
	 * Месячный лимит.
	 * 
	 * @param monthLimit
	 *            Месячный лимит.
	 */
	public void setMonthLimit(double monthLimit) {
		this.monthLimit = new WmAmount(monthLimit);
	}

	/**
	 * Месячный лимит.
	 * 
	 * @param monthLimit
	 *            Месячный лимит.
	 */
	public void setMonthLimit(WmAmount monthLimit) {
		this.monthLimit = monthLimit;
	}

	/**
	 * Кошелек, принадлежащий идентификатору 'slaveWmid', на который
	 * устанавливается доверие.
	 * 
	 * @param purse
	 *            Кошелек, принадлежащий идентификатору 'slaveWmid', на который
	 *            устанавливается доверие.
	 */
	public void setPurse(PurseNumber purse) {
		this.purse = purse;
	}

	/**
	 * Кошелек, принадлежащий идентификатору 'slaveWmid', на который
	 * устанавливается доверие.
	 * 
	 * @param purse
	 *            Кошелек, принадлежащий идентификатору 'slaveWmid', на который
	 *            устанавливается доверие.
	 */
	public void setPurse(String purse) {
		this.purse = new PurseNumber(purse);
	}

	/**
	 * ВМ-идентификатор, который доверяет идентификатору 'masterWmid' данным
	 * запросом (в зависимости от атрибутов тега trust) управление своим
	 * кошельком 'purse'.
	 * 
	 * @param slaveWmid
	 *            ВМ-идентификатор, который доверяет идентификатору 'masterWmid'
	 *            данным запросом (в зависимости от атрибутов тега trust)
	 *            управление своим кошельком 'purse'.
	 */
	public void setSlaveWmid(String slaveWmid) {
		this.slaveWmid = new Wmid(slaveWmid);
	}

	/**
	 * ВМ-идентификатор, который доверяет идентификатору 'masterWmid' данным
	 * запросом (в зависимости от атрибутов тега trust) управление своим
	 * кошельком 'purse'.
	 * 
	 * @param slaveWmid
	 *            ВМ-идентификатор, который доверяет идентификатору 'masterWmid'
	 *            данным запросом (в зависимости от атрибутов тега trust)
	 *            управление своим кошельком 'purse'.
	 */
	public void setSlaveWmid(Wmid slaveWmid) {
		this.slaveWmid = slaveWmid;
	}

	/**
	 * Недельный лимит.
	 * 
	 * @param weekLimit
	 *            Недельный лимит.
	 */
	public void setWeekLimit(double weekLimit) {
		this.weekLimit = new WmAmount(weekLimit);
	}

	/**
	 * Недельный лимит.
	 * 
	 * @param weekLimit
	 *            Недельный лимит.
	 */
	public void setWeekLimit(WmAmount weekLimit) {
		this.weekLimit = weekLimit;
	}

}
