package lv.flancer.wmt.xml.req;

import lv.flancer.wmt.xml.dict.Wmid;

/**
 * Интерфейс X21: Установка по СМС доверия на оплату в пользу продавца.
 * 2-й запрос - подтверждение доверия у владельца кошелька.
 * 
 * @author ainar
 */
public class X21TrustConfirmRequest implements XmlRequest {
    
    /**
     * WM-идентификатора, подписывающего запрос с использовании метода 
     * аутентификации WMSigner или стандартных сертификатов x.509 
     * (сертификаты WM Keeper Light). Именно этому идентификатору будет 
     * устанавливаться доверие по управлению кошельком покупателя и именно 
     * он будет вызывать потом Интерфейс X2 для соврешения регулярных платежей.
     */
    private Wmid signerWmid;
    
    /*
     * Номер запроса из ответа в предыдущем вызове.
     */
    private long purseID;
    
    /*
     * В данном поле передается цифровой код, который покупатель получил на 
     * мобильный телефон для подтверждения платежа. Если СМС не отправлялась 
     * покупателю (был отправлен USSD запрос), то здесь необходимо передать 
     * код со значением 0.
     */
    private String clientNumberCode;
    
    /*
     * Подпись производится ключами идентификатора wmid методом 
     * WMSigner и формируется из параметров: 
     * wmid + lmi_purseid + lmi_clientnumber_code.
     */
    private String sign;
    
    /*
     * В данном параметре передается значение ru-RU или en-US соответственно 
     * для русского или английского языка интерфейса. Данное значение 
     * определяет и язык отправляемых пользователю СМС (USSD) запросов и 
     * язык ответов в теге userdesc
     */
    private String lang;
    
    /*
     * (non-Javadoc)
     *
     * @see lv.flancer.wmt.xml.req.XmlRequest#getTextToSign()
     */
    @Override
    public String getTextToSign() {
        /* wmid + lmi_purseid + lmi_clientnumber_code. */        
        String result = "";
        result += this.signerWmid;
        result += Long.toString(this.purseID);
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
        result += "<lmi_purseid>" + Long.toString(this.purseID) + "</lmi_purseid>";
        result += "<lmi_clientnumber_code>" + this.clientNumberCode + "</lmi_clientnumber_code>"; 
        result += "<sign>" + this.sign + "</sign>"; 
        result += "<lang>" + this.lang + "</lang>";         
        result += "</merchant.request>";
        
        return result;          
    }

    @Override
    public void setSign(String signature) {
        this.sign = signature;
    }

    public String getSign() {
        return sign;
    }
    
    @Override
    public void setSignerWmid(Wmid signerWmid) {
        this.signerWmid = signerWmid;
    }

    public Wmid getSignerWmid() {
        return signerWmid;
    }
    
    public String getClientNumberCode() {
        return clientNumberCode;
    }

    public void setClientNumberCode(String clientNumberCode) {
        this.clientNumberCode = clientNumberCode;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public long getPurseID() {
        return purseID;
    }

    public void setPurseID(long purseID) {
        this.purseID = purseID;
    }
    
}
