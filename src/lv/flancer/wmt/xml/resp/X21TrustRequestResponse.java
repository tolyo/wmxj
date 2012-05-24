package lv.flancer.wmt.xml.resp;

import lv.flancer.wmt.xml.dict.PurseNumber;
import lv.flancer.wmt.xml.dict.Wmid;
import lv.flancer.wmt.xml.dict.X21SmsType;

/**
 * Интерфейс X21: Установка по СМС доверия на оплату в пользу продавца.
 * 1-й запрос - запрос на доверие у владельца кошелька. Ответ на первый запрос.
 *
 * @author ainar
 */
public class X21TrustRequestResponse extends  AbstractResponse{
    
    /*
     * Номер запроса в системе WMT для последующей передачи его во второй 
     * запрос для проверки или подтверждения установки доверия.
     */
    private long purseID;
    
    /*
     * Если в данном поле передана цифра 
     * 1 – это значит, что покупателю отправлена СМС. 
     * Если цифра 2 – отправлен USSD-запрос.
     */
    private X21SmsType smsType;
    
    
    /*
     * В случае ошибки данный текст можно транслировать покупателю как 
     * инструкцию, которая поможет ему быстро и правильно понять что 
     * необходимо сделать, чтобы избежать ошибки в дальнейшем.
     */
    private String userDesc;
    
    /*
     * Кошелек покупателя
     * Независимо от того, завершился ли запрос с ошибкой или без, если данный 
     * тег присутствует, то в нем указан номер WM-Кошелька покупателя, 
     * для которого ранее уже было успешно установлено доверие.
     */
    private PurseNumber slavePurse;
    
    
    /*
     * WMID покупателя
     * Независимо от того, завершился ли запрос с ошибкой или без, если данный 
     * тег присутствует, то в нем указан номер WMID покупателя, для которого 
     * ранее уже было успешно установлено доверие.
     */
    private Wmid slaveWmid;

    public long getPurseID() {
        return purseID;
    }

    public void setPurseID(long purseID) {
        this.purseID = purseID;
    }

    public void setPurseID(String purseID) {
        this.purseID = Long.parseLong(purseID);
    }
    
    public PurseNumber getSlavePurse() {
        return slavePurse;
    }

    public void setSlavePurse(PurseNumber slavePurse) {
        this.slavePurse = slavePurse;
    }
    
    public void setSlavePurse(String slavePurse) {
        this.slavePurse = new PurseNumber(slavePurse);
    }

    public Wmid getSlaveWmid() {
        return slaveWmid;
    }

    public void setSlaveWmid(Wmid slaveWmid) {
        this.slaveWmid = slaveWmid;
    }

    public void setSlaveWmid(String slaveWmid) {
        this.slaveWmid = new Wmid(userDesc);
    }
    
    public X21SmsType getSmsType() {
        return smsType;
    }

    public void setSmsType(X21SmsType smsType) {
        this.smsType = smsType;
    }

    public String getUserDesc() {
        return userDesc;
    }

    public void setUserDesc(String userDesc) {
        this.userDesc = userDesc;
    }
    
}
