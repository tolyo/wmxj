/*  
 * 
 */
package lv.flancer.wmt.xml.resp;

import lv.flancer.wmt.xml.dict.X20SmsType;

/**
 * Интерфейс X20: Проведение транзакции в merchant.webmoney без ухода с сайта
 * (ресурса, сервиса, приложения) продавца.
 * 1-й запрос, инициирование оплаты.
 * 
 * @author ainar
 */
public class X20InitiationResponse extends AbstractResponse {
    /**
     * Номер запроса в системе WebMoney
     * wminvoiceid = requestNum     
     * wminvoiceid - необходимо сохранить, т.к. понадобится для второго запроса.
     * 
     */
    
    /**
     * Тип СМС.
     * Если в данном поле передана цифра 1 – это значит что клиенту отправлена 
     * SMS, 2 – отправлен USSD запрос, 4 – ничего не отправлялось
     *
     */
    private X20SmsType smsType;
    
    /**
     * Информация для клиента.
     * В случае ошибки данный текст можно транслировать клиенту как инструкцию, 
     * которая поможет ему быстро и правильно понять, что необходимо сделать 
     * чтобы избежать ошибки в дальнейшем.      
     * 
     */
    private String userDesc;

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
    
}
