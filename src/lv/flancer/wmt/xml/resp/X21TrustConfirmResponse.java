package lv.flancer.wmt.xml.resp;

import lv.flancer.wmt.xml.dict.PurseNumber;
import lv.flancer.wmt.xml.dict.Wmid;

/**
 * Интерфейс X21: Установка по СМС доверия на оплату в пользу продавца.
 * 2-й запрос - подтверждение доверия у владельца кошелька. Ответ на второй запрос.
 * 
 * @author ainar
 */
public class X21TrustConfirmResponse extends  AbstractResponse {
 
    /*
     * 
     * атрибут id – уникальный номер доверия в системе учета WebMoney
     */
    private long trustID;
    
    /*
     * Номер WM-Кошелька покупателя, для которого было успешно установлено 
     * доверие.
     */
    private PurseNumber slavePurse;
    
    /*
     * Номер WMID покупателя, для которого было успешно установлено доверие.
     */
    private Wmid slaveWmid;
    
    /*
     * В случае ошибки данный текст можно транслировать покупателю как 
     * инструкцию, которая поможет ему быстро и правильно понять, что 
     * необходимо сделать чтобы избежать ошибки в дальнейшем.
     */
    private String userDesc;

    
    private Wmid masterwmid;
    
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
        this.slaveWmid = new Wmid(slaveWmid);
    }

    public long getTrustID() {
        return trustID;
    }

    public void setTrustID(long trustID) {
        this.trustID = trustID;
    }
    
    public void setTrustID(String trustID) {
        this.trustID = Long.parseLong(trustID);
    }

    public String getUserDesc() {
        return userDesc;
    }

    public void setUserDesc(String userDesc) {
        this.userDesc = userDesc;
    }

    public Wmid getMasterwmid() {
        return masterwmid;
    }

    public void setMasterwmid(Wmid masterwmid) {
        this.masterwmid = masterwmid;
    }
     
    public void setMasterwmid(String masterwmid) {
        this.masterwmid = new Wmid(masterwmid);
    }
    
}
