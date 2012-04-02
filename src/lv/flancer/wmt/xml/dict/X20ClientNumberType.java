/*  
 * 
 */
package lv.flancer.wmt.xml.dict;

/**
 * Тип данных клиента, переданных через X20.
 *
 * Тип переданных в lmi_clientnumber данных, если передается мобильный телефон
 * то необходимо указать в этом поле цифру 0, если ВМИД – цифру 1, если E-mail –
 * цифру 2.
 *
 * @author ainar
 */
public enum X20ClientNumberType {

    PHONE_NUMBER, WMID, EMAIL_ADDRESS
}
