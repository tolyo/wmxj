/**
 *
 */
package lv.flancer.wmt.xml.dict;

/**
 * Тип СМС в запросе X21. Данное поле определяет способы подтверждения 
 * покупателем устанавливаемого доверия. 
 * Если в данном поле передать цифру 1, то покупателю будет выслана СМС. 
 * Если цифра 2, то покупателю будет передан USSD-запрос.
 *
 * @author ainar
 */
public enum X21SmsType {

    SMS(1), USSD(2);
    /**
     * Значение перечисления.
     *
     */
    private int value;

    private X21SmsType(int value) {
        this.value = value;
    }

    /**
     * Возвращает значение типа по его коду.
     *
     * @param code
     * @return значение типа
     */
    public static X21SmsType getByValue(int code) {
        switch (code) {
            case 1:
                return SMS;
            case 2:
                return USSD;            
        }
        return SMS;
    }
    
    /**
     * Возвращает значение типа по его коду.
     *
     * @param code код значения
     * @return значение типа
     */
    public static X21SmsType getByValue(String code) {
		return getByValue(Integer.parseInt(code));
    }
    
    /**
     * Возвращает код по значению типа.
     *
     * @param smsType Тип СМС
     * @return код значение 
     */
    public static int getValue(X21SmsType smsType) {    
        return smsType.value;
    }
    
}
