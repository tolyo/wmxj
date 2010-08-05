/**
 * 
 */
package lv.flancer.wmt.xml.resp;

import lv.flancer.wmt.xml.dict.PurseNumber;
import lv.flancer.wmt.xml.dict.WmAmount;
import lv.flancer.wmt.xml.dict.WmDate;
import lv.flancer.wmt.xml.dict.Wmid;

/**
 * X18: Получение деталей операции через WM Merchant.
 * 
 * @author Alex Gusev <flancer64@gmail.com>
 * @version 1.0
 * 
 */
public class X18Response extends AbstractResponse {
	/**
	 * Сумма WM переведенная в данной транзакции продавцу.
	 */
	private WmAmount amount;
	/**
	 * "true" – означает, что платеж был совершен плательщиком не с личного
	 * кошелька, а с кошелька, принадлежащего сервису Капиталлер, "false"
	 * -платеж был совершен с личного кошелька плательщика.
	 */
	private boolean capitallerFlag;
	/**
	 * Сумма платежа, если платеж был совершен плательщиком через терминал,
	 * банкомат или почту.
	 */
	private WmAmount cashierAmount;
	/**
	 * Дата платежа, если платеж был совершен плательщиком через терминал,
	 * банкомат или почту.
	 */
	private WmDate cashierDate;
	/**
	 * Номер платежа, если платеж был совершен плательщиком через терминал,
	 * банкомат или почту.
	 */
	private String cashierNumber;
	/**
	 * "true" – означает, что платеж был соверешен плательщиком авторизовавшимся
	 * посредством сервиса E-num.ru.
	 */
	private boolean enumFlag;
	/**
	 * Значение IP-адреса плательщика, с которого был совершен платеж.
	 */
	private String ipAddress;
	/**
	 * Серверная дата операции в системе WMT.
	 */
	private WmDate operDate;

	/**
	 * Email плательщика, указанный им при совершении платежа чеком Пеймер или
	 * ВМ-картой.
	 */
	private String paymerEmail;

	/**
	 * Номер чека или ВМ-карты плательщика в случае, если платеж был совершен
	 * чеком Пеймер или ВМ-картой.
	 */
	private String paymerNumber;

	/**
	 * Назначение платежа, переданное сервису merchant.webmoney в поле ввода
	 * lmi_payment_desc.
	 */
	private String purpose;

	/**
	 * Кошелек плательщика. Обратите внимание, что при платеже через терминалы
	 * или банкоматы, либо чеком Пеймер или WM-картой, в качестве кошелька
	 * плательщика будет передан кошелек соответствующего шлюза или сервиса
	 * Пеймер.
	 */
	private PurseNumber purseFrom;
	/**
	 * Телефон плательщика в случае если платеж был совершен через сервис Keeper
	 * Mobile.
	 */
	private String telepatPhone;

	/**
	 * WMID плательщика.
	 */
	private Wmid wmidFrom;

	/**
	 * Уникальный номер счета в системе WMT.
	 */
	private long wmInvoiceId;

	/**
	 * Уникальный номер транзакции в системе WMT.
	 */
	private long wmTransId;

	/**
	 * Сумма WM переведенная в данной транзакции продавцу.
	 * 
	 * @return Сумма WM переведенная в данной транзакции продавцу.
	 */
	public WmAmount getAmount() {
		return amount;
	}

	/**
	 * @return Сумма платежа, если платеж был совершен плательщиком через
	 *         терминал, банкомат или почту.
	 */
	public WmAmount getCashierAmount() {
		return cashierAmount;
	}

	/**
	 * Дата платежа, если платеж был совершен плательщиком через терминал,
	 * банкомат или почту.
	 * 
	 * @return Дата платежа, если платеж был совершен плательщиком через
	 *         терминал, банкомат или почту.
	 */
	public WmDate getCashierDate() {
		return cashierDate;
	}

	/**
	 * Номер платежа, если платеж был совершен плательщиком через терминал,
	 * банкомат или почту.
	 * 
	 * @return Номер платежа, если платеж был совершен плательщиком через
	 *         терминал, банкомат или почту.
	 */
	public String getCashierNumber() {
		return cashierNumber;
	}

	/**
	 * Значение IP-адреса плательщика, с которого был совершен платеж.
	 * 
	 * @return Значение IP-адреса плательщика, с которого был совершен платеж.
	 */
	public String getIpAddress() {
		return ipAddress;
	}

	/**
	 * Серверная дата операции в системе WMT.
	 * 
	 * @return Серверная дата операции в системе WMT.
	 */
	public WmDate getOperDate() {
		return operDate;
	}

	/**
	 * Email плательщика, указанный им при совершении платежа чеком Пеймер или
	 * ВМ-картой.
	 * 
	 * @return Email плательщика, указанный им при совершении платежа чеком
	 *         Пеймер или ВМ-картой.
	 */
	public String getPaymerEmail() {
		return paymerEmail;
	}

	/**
	 * Номер чека или ВМ-карты плательщика в случае, если платеж был совершен
	 * чеком Пеймер или ВМ-картой.
	 * 
	 * @return Номер чека или ВМ-карты плательщика в случае, если платеж был
	 *         совершен чеком Пеймер или ВМ-картой.
	 */
	public String getPaymerNumber() {
		return paymerNumber;
	}

	/**
	 * Назначение платежа, переданное сервису merchant.webmoney в поле ввода
	 * lmi_payment_desc.
	 * 
	 * @return Назначение платежа, переданное сервису merchant.webmoney в поле
	 *         ввода lmi_payment_desc.
	 */
	public String getPurpose() {
		return purpose;
	}

	/**
	 * Кошелек плательщика. Обратите внимание, что при платеже через терминалы
	 * или банкоматы, либо чеком Пеймер или WM-картой, в качестве кошелька
	 * плательщика будет передан кошелек соответствующего шлюза или сервиса
	 * Пеймер.
	 * 
	 * @return Кошелек плательщика.
	 */
	public PurseNumber getPurseFrom() {
		return purseFrom;
	}

	/**
	 * Телефон плательщика в случае если платеж был совершен через сервис Keeper
	 * Mobile.
	 * 
	 * @return Телефон плательщика в случае если платеж был совершен через
	 *         сервис Keeper Mobile.
	 */
	public String getTelepatPhone() {
		return telepatPhone;
	}

	/**
	 * WMID плательщика.
	 * 
	 * @return WMID плательщика.
	 */
	public Wmid getWmidFrom() {
		return wmidFrom;
	}

	/**
	 * Уникальный номер счета в системе WMT.
	 * 
	 * @return Уникальный номер счета в системе WMT.
	 */
	public long getWmInvoiceId() {
		return wmInvoiceId;
	}

	/**
	 * Уникальный номер транзакции в системе WMT.
	 * 
	 * @return Уникальный номер транзакции в системе WMT.
	 */
	public long getWmTransId() {
		return wmTransId;
	}

	/**
	 * "true" – означает, что платеж был совершен плательщиком не с личного
	 * кошелька, а с кошелька, принадлежащего сервису Капиталлер, "false"
	 * -платеж был совершен с личного кошелька плательщика.
	 * 
	 * @return "true" – означает, что платеж был совершен плательщиком не с
	 *         личного кошелька, а с кошелька, принадлежащего сервису
	 *         Капиталлер, "false" -платеж был совершен с личного кошелька
	 *         плательщика.
	 */
	public boolean isCapitallerFlag() {
		return capitallerFlag;
	}

	/**
	 * "true" – означает, что платеж был соверешен плательщиком авторизовавшимся
	 * посредством сервиса E-num.ru.
	 * 
	 * @return "true" – означает, что платеж был соверешен плательщиком
	 *         авторизовавшимся посредством сервиса E-num.ru.
	 */
	public boolean isEnumFlag() {
		return enumFlag;
	}

	/**
	 * Сумма WM переведенная в данной транзакции продавцу.
	 * 
	 * @param amount
	 *            Сумма WM переведенная в данной транзакции продавцу.
	 */
	public void setAmount(String amount) {
		this.amount = new WmAmount(amount);
	}

	/**
	 * Сумма WM переведенная в данной транзакции продавцу.
	 * 
	 * @param amount
	 *            Сумма WM переведенная в данной транзакции продавцу.
	 */
	public void setAmount(WmAmount amount) {
		this.amount = amount;
	}

	/**
	 * "true" – означает, что платеж был совершен плательщиком не с личного
	 * кошелька, а с кошелька, принадлежащего сервису Капиталлер, "false"
	 * -платеж был совершен с личного кошелька плательщика.
	 * 
	 * @param capitaller
	 *            "true" – означает, что платеж был совершен плательщиком не с
	 *            личного кошелька, а с кошелька, принадлежащего сервису
	 *            Капиталлер, "false" -платеж был совершен с личного кошелька
	 *            плательщика.
	 */
	public void setCapitallerFlag(boolean capitaller) {
		this.capitallerFlag = capitaller;
	}

	/**
	 * Сумма платежа, если платеж был совершен плательщиком через терминал,
	 * банкомат или почту.
	 * 
	 * @param cashierAmount
	 *            Сумма платежа, если платеж был совершен плательщиком через
	 *            терминал, банкомат или почту.
	 */
	public void setCashierAmount(String cashierAmount) {
		this.cashierAmount = new WmAmount(cashierAmount);
	}

	/**
	 * Сумма платежа, если платеж был совершен плательщиком через терминал,
	 * банкомат или почту.
	 * 
	 * @param cashierAmount
	 *            Сумма платежа, если платеж был совершен плательщиком через
	 *            терминал, банкомат или почту.
	 */
	public void setCashierAmount(WmAmount cashierAmount) {
		this.cashierAmount = cashierAmount;
	}

	/**
	 * Дата платежа, если платеж был совершен плательщиком через терминал,
	 * банкомат или почту.
	 * 
	 * @param cashierDate
	 *            Дата платежа, если платеж был совершен плательщиком через
	 *            терминал, банкомат или почту.
	 */
	public void setCashierDate(String cashierDate) {
		this.cashierDate = new WmDate(cashierDate);
	}

	/**
	 * Дата платежа, если платеж был совершен плательщиком через терминал,
	 * банкомат или почту.
	 * 
	 * @param cashierDate
	 *            Дата платежа, если платеж был совершен плательщиком через
	 *            терминал, банкомат или почту.
	 */
	public void setCashierDate(WmDate cashierDate) {
		this.cashierDate = cashierDate;
	}

	/**
	 * Номер платежа, если платеж был совершен плательщиком через терминал,
	 * банкомат или почту.
	 * 
	 * @param cashierNumber
	 *            Номер платежа, если платеж был совершен плательщиком через
	 *            терминал, банкомат или почту.
	 */
	public void setCashierNumber(String cashierNumber) {
		this.cashierNumber = cashierNumber;
	}

	/**
	 * "true" – означает, что платеж был соверешен плательщиком авторизовавшимся
	 * посредством сервиса E-num.ru.
	 * 
	 * @param enumFlag
	 *            "true" – означает, что платеж был соверешен плательщиком
	 *            авторизовавшимся посредством сервиса E-num.ru.
	 */
	public void setEnumFlag(boolean enumFlag) {
		this.enumFlag = enumFlag;
	}

	/**
	 * Значение IP-адреса плательщика, с которого был совершен платеж.
	 * 
	 * @param ipAddress
	 *            Значение IP-адреса плательщика, с которого был совершен
	 *            платеж.
	 */
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	/**
	 * Серверная дата операции в системе WMT.
	 * 
	 * @param operDate
	 *            Серверная дата операции в системе WMT.
	 */
	public void setOperDate(String operDate) {
		this.operDate = new WmDate(operDate);
	}

	/**
	 * Серверная дата операции в системе WMT.
	 * 
	 * @param operDate
	 *            Серверная дата операции в системе WMT.
	 */
	public void setOperDate(WmDate operDate) {
		this.operDate = operDate;
	}

	/**
	 * Email плательщика, указанный им при совершении платежа чеком Пеймер или
	 * ВМ-картой.
	 * 
	 * @param paymerEmail
	 *            Email плательщика, указанный им при совершении платежа чеком
	 *            Пеймер или ВМ-картой.
	 */
	public void setPaymerEmail(String paymerEmail) {
		this.paymerEmail = paymerEmail;
	}

	/**
	 * Номер чека или ВМ-карты плательщика в случае, если платеж был совершен
	 * чеком Пеймер или ВМ-картой.
	 * 
	 * @param paymerNumber
	 *            Номер чека или ВМ-карты плательщика в случае, если платеж был
	 *            совершен чеком Пеймер или ВМ-картой.
	 */
	public void setPaymerNumber(String paymerNumber) {
		this.paymerNumber = paymerNumber;
	}

	/**
	 * Назначение платежа, переданное сервису merchant.webmoney в поле ввода
	 * lmi_payment_desc.
	 * 
	 * @param purpose
	 *            Назначение платежа, переданное сервису merchant.webmoney в
	 *            поле ввода lmi_payment_desc.
	 */
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	/**
	 * Кошелек плательщика. Обратите внимание, что при платеже через терминалы
	 * или банкоматы, либо чеком Пеймер или WM-картой, в качестве кошелька
	 * плательщика будет передан кошелек соответствующего шлюза или сервиса
	 * Пеймер.
	 * 
	 * @param purseFrom
	 *            Кошелек плательщика.
	 */
	public void setPurseFrom(PurseNumber purseFrom) {
		this.purseFrom = purseFrom;
	}

	/**
	 * Кошелек плательщика. Обратите внимание, что при платеже через терминалы
	 * или банкоматы, либо чеком Пеймер или WM-картой, в качестве кошелька
	 * плательщика будет передан кошелек соответствующего шлюза или сервиса
	 * Пеймер.
	 * 
	 * @param purseFrom
	 *            Кошелек плательщика.
	 */
	public void setPurseFrom(String purseFrom) {
		this.purseFrom = new PurseNumber(purseFrom);
	}

	/**
	 * Телефон плательщика в случае если платеж был совершен через сервис Keeper
	 * Mobile.
	 * 
	 * @param telepatPhone
	 *            Телефон плательщика в случае если платеж был совершен через
	 *            сервис Keeper Mobile.
	 */
	public void setTelepatPhone(String telepatPhone) {
		this.telepatPhone = telepatPhone;
	}

	/**
	 * WMID плательщика.
	 * 
	 * @param wmidFrom
	 *            WMID плательщика.
	 */
	public void setWmidFrom(String wmidFrom) {
		this.wmidFrom = new Wmid(wmidFrom);
	}

	/**
	 * WMID плательщика.
	 * 
	 * @param wmidFrom
	 *            WMID плательщика.
	 */
	public void setWmidFrom(Wmid wmidFrom) {
		this.wmidFrom = wmidFrom;
	}

	/**
	 * Уникальный номер счета в системе WMT.
	 * 
	 * @param wmInvoiceId
	 *            Уникальный номер счета в системе WMT.
	 */
	public void setWmInvoiceId(long wmInvoiceId) {
		this.wmInvoiceId = wmInvoiceId;
	}

	/**
	 * Уникальный номер счета в системе WMT.
	 * 
	 * @param wmInvoiceId
	 *            Уникальный номер счета в системе WMT.
	 */
	public void setWmInvoiceId(String wmInvoiceId) {
		this.wmInvoiceId = Long.parseLong(wmInvoiceId);
	}

	/**
	 * Уникальный номер транзакции в системе WMT.
	 * 
	 * @param wmTransId
	 *            Уникальный номер транзакции в системе WMT.
	 */
	public void setWmTransId(long wmTransId) {
		this.wmTransId = wmTransId;
	}

	/**
	 * Уникальный номер транзакции в системе WMT.
	 * 
	 * @param wmTransId
	 *            Уникальный номер транзакции в системе WMT.
	 */
	public void setWmTransId(String wmTransId) {
		this.wmTransId = Long.parseLong(wmTransId);
	}
}
