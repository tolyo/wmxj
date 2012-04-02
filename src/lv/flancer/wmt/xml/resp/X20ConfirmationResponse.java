/*
 * 
 * 
 */
package lv.flancer.wmt.xml.resp;

import lv.flancer.wmt.xml.dict.PurseNumber;
import lv.flancer.wmt.xml.dict.WmAmount;
import lv.flancer.wmt.xml.dict.WmDate;
import lv.flancer.wmt.xml.dict.Wmid;

/**
 * Интерфейс X20: Проведение транзакции в merchant.webmoney без ухода с сайта
 * (ресурса, сервиса, приложения) продавца. 
 * 2-й запрос, подтверждение оплаты.
 *
 * @author ainar
 */
public class X20ConfirmationResponse extends AbstractResponse {

    /**
     * Номер запроса в системе WebMoney wminvoiceid = requestNum
     */
    /**
     * Номер ВМ-транзакции. Уникальный номер транзакции. Если в данном параметре
     * присутствует положительное число больше 0, только тогда это означает, что
     * платеж был успешно совершен. Для обычных продавцов использующих
     * merchant.webmoney наличие данного уникального номера транзакции означает,
     * что средства от клиента поступили и находятся на кошельке. Для продавцов,
     * использующих processing.webmoney наличие wmtransid означает, что средства
     * поступили, войдут в текущий реестр и будут отправлены очередным
     * банковским переводом по этому реестру. Для продавцов с ВМИД Capitaller,
     * настроивших в нем прием средств через merchant.webmoney это означает ,
     * что средства поступили на доходный кошелек и в ближайшее время будут
     * распределены в соответствии с политикой бюджетного автомата. Если
     * приложение продавца не имеет номер wmtransid (не зафиксировало его
     * наличие или не сохранило его в своей системе учета), то ни предоставлять
     * товар, ни оказывать услугу, ни пополнять лицевой счет и т.п. нельзя. В
     * случае завершения запроса по таймауту или в случае обрыва связи его
     * следует повторить до получения wmtranid или четкого номера ошибки в
     * параметре retval, подтверждающего что платеж не прошел.
     *
     */
    private long wmTransId;
    /**
     * Информация для клиента. В случае ошибки данный текст можно транслировать
     * клиенту как инструкцию, которая поможет ему быстро и правильно понять,
     * что необходимо сделать чтобы избежать ошибки в дальнейшем.
     *
     */
    private String userDesc;
    /**
     * Сумма платежа.
     *
     */
    private WmAmount amount;
    /**
     * Серверная дата операции в системе WMT.
     */
    private WmDate operDate;
    /**
     * Кошелек клиента. Номер ВМ-Кошелька клиента, с которого была совершена
     * транзакция. Если платеж был совершен с WebMoney чека (в инициирующем
     * запросе был передан мобильный телефон, система определила наличие чека с
     * таким номером телефона в системе и была отправлена подтверждающая СМС),
     * то в качестве кошелька будет фигурировать кошелек системы Paymer
     * соответствующего типа Z000000000001 (или R, G, U, B, E).
     *
     *
     */
    private PurseNumber purseFrom;
    /**
     * WMID плательщика. ВМИД клиента, с которого была совершена транзакция.
     * Если платеж был совершен с WebMoney чека, то в качестве ВМИДа будет
     * фигурировать ВМИД чековой системы Paymer 000000000000
     *
     */
    private Wmid wmidFrom;
    /**
     * Cтатус отправки СМС.
     *
     * Если интерфейс завершен с ошибкой 553 (когда оплата осуществляется с
     * Вебмани чека и передан Lmi_clientnumber_code=0 для выяснения состояния
     * оплаты) или с ошибкой 556 (когда оплата осуществляется с WebMoney
     * кошелька и она еще не прошла), то данный тег присутствует в ответе (если
     * СМС или USSD отправлялись, то есть lmi_sms_type = 1,2,3 ) и содержит
     * состояние СМС или USSD : BUFFERED – ожидает отправки SENDING –
     * отправляется оператору SENDED- передано оператору DELIVERED- доставлено
     * NON_DELIVERED – не доставлено SUSPENDED – отложено для повтора
     *
     *
     */
    private String smsSentState;

    /**
     * Сумма платежа.
     *
     * @return Сумма платежа.
     */
    public WmAmount getAmount() {
        return amount;
    }

    /**
     * Сумма платежа.
     *
     * @param amount Сумма платежа.
     */
    public void setAmount(WmAmount amount) {
        this.amount = amount;
    }
    
    /**
     * Сумма платежа.
     *
     * @param amount Сумма платежа.
     */
    public void setAmount(String amount) {
	this.amount = new WmAmount(amount);
    }
    
    /**
     * Информация для клиента.
     *
     * @return Информация для клиента.
     */
    public String getUserDesc() {
        return userDesc;
    }

    /**
     * Информация для клиента.
     *
     * @param userDesc Информация для клиента.
     */
    public void setUserDesc(String userDesc) {
        this.userDesc = userDesc;
    }

    /**
     * Номер ВМ-транзакции.
     *
     * @return Номер ВМ-транзакции.
     */
    public long getWmTransId() {
        return wmTransId;
    }

    /**
     * Номер ВМ-транзакции.
     *
     * @param wmTransId Номер ВМ-транзакции.
     */
    public void setWmTransId(long wmTransId) {
        this.wmTransId = wmTransId;
    }

    /**
     * Номер ВМ-транзакции.
     *
     * @param wmTransId Номер ВМ-транзакции.
     */
    public void setWmTransId(String wmTransId) {
        this.wmTransId = Long.parseLong(wmTransId);
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
     *
     * Серверная дата операции в системе WMT.
     *
     * @param operDate Серверная дата операции в системе WMT.
     */
    public void setOperDate(String operDate) {
        this.operDate = new WmDate(operDate);
    }

    /**
     * Серверная дата операции в системе WMT.
     *
     * @param operDate Серверная дата операции в системе WMT.
     */
    public void setOperDate(WmDate operDate) {
        this.operDate = operDate;
    }

    /**
     * Номер кошелька с которого выполняется перевод (отправитель). Кошелек
     * клиента.
     *
     * @param pursefrom Номер кошелька с которого выполняется перевод
     * (отправитель). Кошелек клиента.
     */
    public void setPurseFrom(PurseNumber pursefrom) {
        this.purseFrom = pursefrom;
    }

    /**
     * Номер кошелька с которого выполняется перевод (отправитель). Кошелек
     * клиента.
     *
     * @param pursefrom Номер кошелька с которого выполняется перевод
     * (отправитель). Кошелек клиента.
     */
    public void setPurseFrom(String pursefrom) {
        this.purseFrom = new PurseNumber(pursefrom);
    }

    /**
     * Номер кошелька с которого выполняется перевод (отправитель). Кошелек
     * клиента.
     *
     * @return Номер кошелька с которого выполняется перевод (отправитель).
     * Кошелек клиента.
     */
    public PurseNumber getPurseFrom() {
        return this.purseFrom;
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
     * WMID плательщика.
     *
     * @param wmidFrom WMID плательщика.
     */
    public void setWmidFrom(String wmidFrom) {
        this.wmidFrom = new Wmid(wmidFrom);
    }

    /**
     * WMID плательщика.
     *
     * @param wmidFrom WMID плательщика.
     */
    public void setWmidFrom(Wmid wmidFrom) {
        this.wmidFrom = wmidFrom;
    }

    /**
     * Cтатус отправки СМС.
     *
     * @return Cтатус отправки СМС.
     */
    public String getSmsSentState() {
        return this.smsSentState;
    }

    /**
     * Cтатус отправки СМС.
     *
     * @param smsSentState Cтатус отправки СМС.
     */
    public void setSmsSentState(String smsSentState) {
        this.smsSentState = smsSentState;
    }
}
