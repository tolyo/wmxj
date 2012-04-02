/*
 * 
 * 
 */
package lv.flancer.wmt.xml.req;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import lv.flancer.wmt.xml.dict.*;

/**
 * Интерфейс X20: Проведение транзакции в merchant.webmoney без ухода с сайта
 * (ресурса, сервиса, приложения) продавца. 
 * 2-й запрос, подтверждение оплаты.
 * 
 * @author ainar
 */
public class X20ConfirmationRequest implements XmlRequest {
    
    /**
     * Код клиента. 
     * В данном поле передается цифровой код, который клиент получил на 
     * мобильный телефон для подтверждения платежа. Если СМС не отправлялась 
     * клиенту (был отправлен USSD запрос или просто ожидается оплата клиентом 
     * ВМ-счета через мобильные программы управления кошельками), то здесь 
     * необходимо передать код со значением 0. В случае если все же СМС была 
     * отправлена клиенту, но клиент оплатил ВМ -счет через программу управления 
     * кошельками, то передача здесь кода 0 все равно даст успешный результат, 
     * так как проверка кода просто не будет производиться. Если в данном 
     * параметре передать -1 и на момент выполнения запроса оплата еще не 
     * произошла, то счет будет отменен и оплата в дальнейшем будет невозможна.
     *
     */
    private String clientNumberCode;
   
    /**
     * WMID подписавшего запрос. Используется только при авторизации с ключами
     * WM Keeper Classic.
     */
    private Wmid signerWmid;
    
    /**
     * Номер платежа в системе Webmoney.
     * wminvoiceid = requestNum, номер ВМ-счета wminvoiceid, полученный из 
     * ответа на предыдущий запрос (X20InitiationResponse).
     * 
     */
    private long requestNum;
    /**
     * ВМ-кошелек получателя платежа. Номер кошелька который зарегистрирован и
     * настроен в сервисе merchant.webmoney и на который продавец будет
     * принимать оплату.
     *
     */
    private PurseNumber payeePurse;  
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
        //lmi_wminvoiceid+
        //lmi_clientnumber_code        
        String result = "";
        result += this.signerWmid;
        result += this.payeePurse;
        result += this.requestNum;
        result += this.clientNumberCode;  
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
        result += "<lmi_clientnumber_code>" + this.clientNumberCode + "</lmi_clientnumber_code>";          
        result += "<lmi_wminvoiceid>" + this.requestNum + "</lmi_wminvoiceid>";      
                
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
     * Номер платежа в системе Webmoney.
     *
     * @return Номер платежа в системе Webmoney.
     */
    public long getRequestNum() {
        return this.requestNum;
    }

    /**
     * Номер платежа в системе Webmoney.
     *
     * @param requestNum Номер платежа в системе Webmoney.
     */
    public void setRequestNum(String requestNum) {
        this.requestNum = Long.parseLong(requestNum);
    }

    /**
     * Номер платежа в системе Webmoney.
     *
     * @param requestNum Номер платежа в системе Webmoney.
     */
    public void setRequestNum(long requestNum) {
        this.requestNum = requestNum;
    }
    
    /**
     * В данном поле передается цифровой код, который клиент получил на 
     * мобильный телефон для подтверждения платежа.
     *
     * @return В данном поле передается цифровой код, который клиент получил на 
     * мобильный телефон для подтверждения платежа.
     */
    public String getClientNumberCode() {
        return this.clientNumberCode;
    }

    /**
     * В данном поле передается цифровой код, который клиент получил на 
     * мобильный телефон для подтверждения платежа.
     *
     * @param clientNumberCode В данном поле передается цифровой код, который клиент получил на 
     *                          мобильный телефон для подтверждения платежа.
     */
    public void setClientNumberCode(String clientNumberCode) {
        this.clientNumberCode = clientNumberCode;
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
