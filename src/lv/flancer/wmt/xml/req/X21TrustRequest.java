package lv.flancer.wmt.xml.req;

import lv.flancer.wmt.xml.dict.*;

/**
 * Интерфейс X21: Установка по СМС доверия на оплату в пользу продавца.
 * 1-й запрос - запрос на доверие у владельца кошелька.
 *
 * @author ainar
 */
public class X21TrustRequest implements XmlRequest {

    /**
     * WM-идентификатора, подписывающего запрос с использовании метода 
     * аутентификации WMSigner или стандартных сертификатов x.509 
     * (сертификаты WM Keeper Light). Именно этому идентификатору будет 
     * устанавливаться доверие по управлению кошельком покупателя и именно 
     * он будет вызывать потом Интерфейс X2 для соврешения регулярных платежей.
     */
    private Wmid signerWmid;
    
    
    /*
     * Номер кошелька, на который продавец будет принимать оплату путем 
     * перевода средств с кошелька покупателя при помощи интерфейса Х2.
     */
    private PurseNumber payeePurse;
    
    /*
     * Максимальная сумма платежей, которую продавец сможет перевести от 
     * покупателя в течении одного календарного дня в том же типе WM-валюты, 
     * что и кошелек продавца lmi_payee_purse. Сумма должна быть больше или 
     * равна нулю, дробная часть отделяется точкой. Если сумма равна нулю, 
     * то лимита на оплату в день не существует. При этом, как минимум один 
     * из трех лимитов (дневной, недельный, месячный) должен быть обязательно 
     * указан, т.е. значения всех трех лимитов не должны равняться нулю 
     * (недопустимо полное отсутствие лимитов). При этом, максимальная сумма 
     * лимита, которую можно установить с помощью данного интерфейса, такая же,
     * как и в статье Финансовые ограничения WebMoney Keeper Mini для Кипера 
     * Мини с непроверенным телефоном. Если по каким-либо причинам магазину или 
     * сервису понадобится большая сумма, то об этом надо сообщить покупателю, 
     * чтобы он поднял лимит самостоятельно в настройках доверия 
     * на security.webmoney.ru
     */
    private WmAmount dayLimit;
    
    /*
     * См. описание lmi_day_limit с заменой календарного дня на календарную 
     * неделю.
     */
    private WmAmount weekLimit;
    
    /*
     * См. описание lmi_day_limit с заменой календарного дня на календарный 
     * месяц.
     */
    private WmAmount monthLimit;
    
    /*
     * В этом параметре могут быть указаны на выбор: 
     *  - Мобильный телефон с кодом страны и города (допустимы только цифры, 
     *  без плюсов, скобок и других символов). Например, для России – 
     *  79167777777, а для Украины – 380527777777) 
     *  - WM-идентификатор покупателя (строго 12 цифр)
     *  - WM-кошелек покупателя (строго большая буква типа WM-кошелька и 
     *  12 цифр)
     *  - E-mail покупателя. При этом, интерфейс автоматически найдет 
     *  WM-идентификатор, с которого может быть произведена оплата покупателем.
     * 
     */
    private String clientNumber;
    
    /*
     * Тип переданных в lmi_clientnumber данных. Если передается мобильный 
     * телефон, то необходимо указать в этом поле цифру 0, если WMID – цифру 1, 
     * если E-mail – цифру 2, если WM-кошелек – цифру 4
     */
    private X21ClientNumberType clientNumberType;
    
    /*
     * Данное поле определяет способы подтверждения покупателем устанавливаемого 
     * доверия. Если в данном поле передать цифру 1, то покупателю будет выслана СМС. 
     * Если цифра 2, то покупателю будет передан USSD-запрос.
     */
    private X21SmsType smsType;
     
    /*
     * Подпись производится ключами идентификатора wmid методом WMSigner и 
     * формируется из параметров: 
     * wmid + lmi_payee_purse + lmi_clientnumber + lmi_clientnumber_type +lmi_sms_type. 
     * Обратите внимание, что если кошелек lmi_payee_purse не принадлежит 
     * идентификатору wmid, то необходимо чтобы на security.webmoney.ru этому 
     * идентификатору было установлено доверие на выписку счетов для 
     * кошелька lmi_payee_purse.
     */
    private String sign;
    
    /*
     * В данном параметре передается значение ru-RU или en-US, соответственно, 
     * для русского или английского языка интерфейса. Данное значение 
     * определяет и язык отправляемых пользователю СМС- или USSD-запросов, 
     * и язык ответов в теге userdesc
     */
    private String lang;
              
    /*
     * (non-Javadoc)
     *
     * @see lv.flancer.wmt.xml.req.XmlRequest#getTextToSign()
     */
    @Override
    public String getTextToSign() {
        /* wmid + lmi_payee_purse + lmi_clientnumber + lmi_clientnumber_type +lmi_sms_type */        
        String result = "";
        result += this.signerWmid;
        result += this.payeePurse;
        result += this.clientNumber;
        result += X21ClientNumberType.getValue(this.clientNumberType);
        result += X21SmsType.getValue(this.smsType);
        
        return result;
    }

    /*
    * (non-Javadoc)
    * 
    * @see lv.flancer.wmt.xml.req.XmlRequest#getXmlRequest()
    */
    @Override
    public String getXmlRequest() {
                       
        String result = "<?xml version=\"1.0\"  encoding=\"windows-1251\"?>";
        result += "<merchant.request>";        
        result += "<wmid>" + this.signerWmid + "</wmid>";
        result += "<lmi_payee_purse>" + this.payeePurse + "</lmi_payee_purse>";        
        result += "<lmi_day_limit>" + this.dayLimit + "</lmi_day_limit>";        
        result += "<lmi_week_limit>" + this.weekLimit + "</lmi_week_limit>";        
        result += "<lmi_month_limit>" + this.monthLimit + "</lmi_month_limit>";        
        result += "<lmi_clientnumber>" + this.clientNumber + "</lmi_clientnumber>";        
        result += "<lmi_clientnumber_type>" + X21ClientNumberType.getValue(this.clientNumberType) + "</lmi_clientnumber_type>";                
        result += "<lmi_sms_type>" + X21SmsType.getValue(this.smsType) + "</lmi_sms_type>";
        result += "<lang>" + this.lang + "</lang>";
        result += "<sign>" + this.sign + "</sign>";                
        result += "</merchant.request>";
        
        return result;         
    }

    @Override
    public void setSign(String signature) {
        this.sign = signature;
    }

    @Override
    public void setSignerWmid(Wmid signerWmid) {
        this.signerWmid = signerWmid;
    }

    public String getClientNumber() {
        return clientNumber;
    }

    public void setClientNumber(String clientNumber) {
        this.clientNumber = clientNumber;
    }

    public X21ClientNumberType getClientNumberType() {
        return clientNumberType;
    }

    public void setClientNumberType(X21ClientNumberType clientNumberType) {
        this.clientNumberType = clientNumberType;
    }

    public WmAmount getDayLimit() {
        return dayLimit;
    }

    public void setDayLimit(WmAmount dayLimit) {
        this.dayLimit = dayLimit;
    }

    public void setDayLimit(double dayLimit) {
        this.dayLimit = new WmAmount(dayLimit);
    }
    
    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public WmAmount getMonthLimit() {
        return monthLimit;
    }

    public void setMonthLimit(WmAmount monthLimit) {
        this.monthLimit = monthLimit;
    }
    
    public void setMonthLimit(double monthLimit) {
        this.monthLimit = new WmAmount(monthLimit);
    }

    public PurseNumber getPayeePurse() {
        return payeePurse;
    }

    public void setPayeePurse(PurseNumber payeePurse) {
        this.payeePurse = payeePurse;
    }

    public void setPayeePurse(String payeePurse) {
        this.payeePurse = new PurseNumber(payeePurse);
    }
    
    public Wmid getSignerWmid() {
        return signerWmid;
    }
    
    public X21SmsType getSmsType() {
        return smsType;
    }

    public void setSmsType(X21SmsType smsType) {
        this.smsType = smsType;
    }

    public WmAmount getWeekLimit() {
        return weekLimit;
    }

    public void setWeekLimit(WmAmount weekLimit) {
        this.weekLimit = weekLimit;
    }  
    
    public void setWeekLimit(double weekLimit) {
        this.weekLimit = new WmAmount(weekLimit);
    }
    
}
