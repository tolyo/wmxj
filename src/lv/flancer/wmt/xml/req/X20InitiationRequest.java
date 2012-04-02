/**
 *
 */
package lv.flancer.wmt.xml.req;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import lv.flancer.wmt.xml.dict.PurseNumber;
import lv.flancer.wmt.xml.dict.WmAmount;
import lv.flancer.wmt.xml.dict.Wmid;
import lv.flancer.wmt.xml.dict.X20ClientNumberType;
import lv.flancer.wmt.xml.dict.X20SmsType;

/**
 * Интерфейс X20: Проведение транзакции в merchant.webmoney без ухода с сайта
 * (ресурса, сервиса, приложения) продавца. 
 * 1-й запрос, инициирование оплаты.
 *
 *
 * @author ainar
 */
public class X20InitiationRequest implements XmlRequest {

    
    /**
     * Язык ответа.
     * В данном параметре передается значение ru-RU или en-US соответсвенно для 
     * русского или английского языка интерфейса. Данное значение определяет и 
     * язык отправляемых пользователю SMS (USSD) запросов и язык ответов в 
     * теге userdesc. 
     * 
     */
    private String lang;

    
    /**
     * Тип СМС.
     *
     */
    private X20SmsType smsType;
    /**
     * Тип данных клиента. Тип переданных в lmi_clientnumber данных, если
     * передается мобильный телефон - 0, если WMID – 1, если E-mail – 2.
     *
     */
    private X20ClientNumberType clientNumberType;
    /**
     * Данные клиента. Мобильный телефон (с кодом страны и города только цифры
     * без плюсов, скобок и других символов например для России 79167777777 или
     * Украины 380527777777) или ВМ-идентификатор клиента (строго 12 цифр) или
     * E-mail клиента. При этом интерфейс автоматически найдет ВМ -идентификатор
     * или WebMoney чек, с которого может быть произведена оплата клиентом.
     *
     */
    private String clientNumber;
    /**
     * Описание оплачиваемого товара или услуги. (назначение платежа)
     * Максимальная длина – 255 символов.
     *
     */
    private String desc;
    /**
     * Сумма платежа.
     *
     */
    private WmAmount amount;
    /**
     * Номер платежа.
     */
    private long lmiPaymentNo;
    /**
     * ВМ-кошелек получателя платежа. Номер кошелька который зарегистрирован и
     * настроен в сервисе merchant.webmoney и на который продавец будет
     * принимать оплату.
     *
     */
    private PurseNumber payeePurse;
    /**
     * WMID подписавшего запрос. Используется только при авторизации с ключами
     * WM Keeper Classic.
     */
    private Wmid signerWmid;
    /**
     * Подпись запроса. Подпись производится ключами идентификатора wmid методом
     * WMSigner и формируется из параметров: wmid + lmi_payee_purse +
     * lmi_payment_no + lmi_clientnumber + lmi_clientnumber_type. В случае если
     * используется данный вариант аутентификации запроса, параметры md5 и
     * secret_key должны остаться пустыми или быть опущены.
     *
     */
    private String sign;
    /**
     * Подпись запроса. Подпись производится методом MD5 и формируется из
     * параметров: wmid + lmi_payee_purse + lmi_payment_no + lmi_clientnumber +
     * lmi_clientnumber_type + secret_key. В случае если используется данный
     * вариант аутентификации запроса, параметры sign и secret_key должны
     * остаться пустыми или быть опущены. Обратите внимание, что при
     * формировании строки к которой применяется алгоритм md5 в качестве
     * secret_key используется значение секретного слова из настроек кошелька в
     * сервисе merchant.webmoney.ru , при этом в самом запросе необходимо
     * передавать ТОЛЬКО результат работы алгоритма в параметре MD5 , параметр
     * secret_key должен остаться не указанным или пустым!!!
     *
     */
    private String md5;
    /**
     * В данном параметре передается значение секретного слова из настроек
     * кошелька lmi_payee_purse в сервисе merchant.webmoney.ru . Обратите
     * внимание, что при использование данного метода, проверка аутентичности
     * соединения по https (валидности и принадлежности корневого сертификата
     * сервера https://merchant.webmoney.ru/ и т.п.) во избежание подмены DNS и
     * т.п. остается на совести отправителя запроса. В случае если используется
     * данный вариант аутентификации запроса, параметры sign и md5 должны
     * остаться пустыми или быть опущены.
     *
     */
    private String secretKey;

    /*
     * (non-Javadoc)
     *
     * @see lv.flancer.wmt.xml.req.XmlRequest#getTextToSign()
     */
    @Override
    public String getTextToSign() {        
        //wmid + 
        //lmi_payee_purse + 
        //lmi_payment_no + 
        //lmi_clientnumber + 
        //lmi_clientnumber_type        
        String result = "";
        result += this.signerWmid;
        result += this.payeePurse;
        result += this.lmiPaymentNo;
        result += this.clientNumber;
        result += this.clientNumberType.ordinal();
        
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
        result += "<lmi_payment_no>" + this.lmiPaymentNo + "</lmi_payment_no>";        
        result += "<lmi_payment_amount>" + this.getAmount().getWmFormated() + "</lmi_payment_amount>";        
        result += "<lmi_payment_desc>" + this.desc + "</lmi_payment_desc>";        
        result += "<lmi_clientnumber>" + this.clientNumber + "</lmi_clientnumber>";        
        result += "<lmi_clientnumber_type>" + this.clientNumberType.ordinal() + "</lmi_clientnumber_type>";                
        result += "<lmi_sms_type>" + X20SmsType.getValue(this.smsType) + "</lmi_sms_type>";
        result += "<lang>" + this.lang + "</lang>";
                
        if (this.sign != null) {
                result += "<sign>" + this.sign + "</sign>";
                result += "<md5 />";
		result += "<secret_key />";
        } else {
                result += "<sign />";
                result += "<md5>" + (this.md5 != null ? this.md5 : this.getMd5Signature()) + "</md5>";
                result += "<secret_key />";
        }                 
        result += "</merchant.request>";
        
        return result;       
    }
    
    /**
    * md5-хэш для подписываемой строки.
    * 
    * @return md5-хэш для подписываемой строки.
    */
    private String getMd5Signature() {
        StringBuffer hexString = new StringBuffer();
        try {                
            String text = getTextToSign() + this.secretKey;               

            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(text.getBytes());
            byte[] hash = md.digest();
            for (int i = 0; i < hash.length; i++) {
                    if ((0xff & hash[i]) < 0x10) {
                            hexString.append("0"
                                            + Integer.toHexString((0xFF & hash[i])));
                    } else {
                            hexString.append(Integer.toHexString(0xFF & hash[i]));
                    }
            }
        } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
        }
        return hexString.toString();
    }
    
    /**
     * Сформировать MD5 - хэш и заполнить поле md5.      
     * 
     */
    public void initMd5() {
        this.setMd5(this.getMd5Signature());    
    }

    /**
     * Подпись запроса.
     *
     * @param signature Подпись запроса.
     */
    @Override
    public void setSign(String signature) {
        this.sign = signature;
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
     * Wmid получателя платежа или доверенный WMID.
     *
     * @param signerWmid Wmid получателя платежа или доверенный WMID.
     */
    @Override
    public void setSignerWmid(Wmid signerWmid) {
        this.signerWmid = signerWmid;
    }

    /**
     * Wmid получателя платежа или доверенный WMID.
     *
     * @return Wmid получателя платежа или доверенный WMID.
     */
    public Wmid getSignerWmid() {
        return this.signerWmid;
    }

    /**
     * @return the MD5 String
     */
    public String getMd5() {
        return md5;
    }

    /**
     * Подпись запроса. Подпись производится методом MD5.
     *
     * @param md5 Подпись запроса.
     */
    public void setMd5(String md5) {
        this.md5 = md5;
    }

    /**
     * @return the payee purse
     */
    public PurseNumber getPayeePurse() {
        return payeePurse;
    }

    /**
     * Номер кошелька, на который выполняется перевод (получатель).
     *
     * @param payeePurse Номер кошелька, на который выполняется перевод
     * (получатель).
     */
    public void setPayeePurse(PurseNumber payeePurse) {
        this.payeePurse = payeePurse;
    }
    
    /**
     * Номер кошелька, на который выполняется перевод (получатель).
     *
     * @param payeePurse Номер кошелька, на который выполняется перевод
     * (получатель).
     */
    public void setPayeePurse(String payeePurse) {        
        this.payeePurse = new PurseNumber(payeePurse);
    }

    /**
     * @return the secret key
     */
    public String getSecretKey() {
        return secretKey;
    }

    /**
     * Секретное слово из настроек кошелька lmi_payee_purse в сервисе
     * merchant.webmoney.ru.
     *
     * @param secretKey Секретное слово.
     */
    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    /**
     * Номер платежа.
     *
     * @return Номер платежа.
     */
    public long getLmiPaymentNo() {
        return lmiPaymentNo;
    }

    /**
     * Номер платежа.
     *
     * @param lmiPaymentNo Номер платежа.
     */
    public void setLmiPaymentNo(String lmiPaymentNo) {
        this.lmiPaymentNo = Long.parseLong(lmiPaymentNo);
    }
    
    /**
     * Номер платежа.
     *
     * @param lmiPaymentNo Номер платежа.
     */
    public void setLmiPaymentNo(long lmiPaymentNo) {
        this.lmiPaymentNo = lmiPaymentNo;
    }

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
    * @param amount
    *            Сумма платежа.
    */
    public void setAmount(double amount) {
            this.amount = new WmAmount(amount);
    }
    
    /**
     * Описание оплачиваемого товара или услуги.
     *
     * @return Описание оплачиваемого товара или услуги.
     */
    public String getDesc() {
        return desc;
    }

    /**
     * Описание оплачиваемого товара или услуги.
     *
     * @param desc Описание оплачиваемого товара или услуги.
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * Данные клиента. (мобильный телефон или WMID или E-mail).
     *
     * @return Данные клиента. (мобильный телефон или WMID или E-mail)
     */
    public String getClientNumber() {
        return clientNumber;
    }

    /**
     * Данные клиента. (мобильный телефон или WMID или E-mail).
     *
     * @param clientNumber Данные клиента. (мобильный телефон или WMID или
     * E-mail).
     */
    public void setClientNumber(String clientNumber) {
        this.clientNumber = clientNumber;
    }

    /**
     * Тип данных клиента.
     *
     * @return Тип данных клиента.
     */
    public X20ClientNumberType getClientNumberType() {
        return clientNumberType;
    }

    /**
     * Тип данных клиента.
     *
     * @param clientNumberType Тип данных клиента.
     */
    public void setClientNumberType(X20ClientNumberType clientNumberType) {
        this.clientNumberType = clientNumberType;
    }

    /**
     * Тип СМС.
     *
     * @return Тип СМС.
     */
    public X20SmsType getSmsType() {
        return smsType;
    }

    /**
     * Тип СМС.
     *
     * @param smsType Тип СМС.
     */
    public void setSmsType(X20SmsType smsType) {
        this.smsType = smsType;
    }
    
    
    /**
     * Язык ответа.
     *
     * @return Язык ответа.
     */
    public String getLang() {
        return lang;
    }
    
    /**
     * Язык ответа.
     *
     * @param lang Язык ответа.
     */
    public void setLang(String lang) {
        this.lang = lang;
    }
    
}
