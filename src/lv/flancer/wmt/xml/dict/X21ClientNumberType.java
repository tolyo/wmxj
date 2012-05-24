/*  
 * 
 */
package lv.flancer.wmt.xml.dict;

/**
 * Тип данных клиента, переданных через X21.
 * 
 * Тип переданных в lmi_clientnumber данных. 
 * Если передается мобильный телефон, то необходимо указать в этом поле цифру 0, 
 * если WMID – цифру 1, 
 * если E-mail – цифру 2, 
 * если WM-кошелек – цифру 4
 *
 * @author ainar
 */
public enum X21ClientNumberType {

    PHONE_NUMBER(0), WMID(1), EMAIL_ADDRESS(2), WM_PURSE(4);
    /**
     * Значение перечисления.
     */
    private int value;

    private X21ClientNumberType(int value) {
        this.value = value;
    }

    /**
     * Возвращает значение типа по его коду.
     *
     * @param code
     * @return значение типа
     */
    public static X21ClientNumberType getByValue(int code) {
        switch (code) {
            case 0:
                return PHONE_NUMBER;
            case 1:
                return WMID;
            case 2:
                return EMAIL_ADDRESS;
            case 4:
                return WM_PURSE;
        }
        return PHONE_NUMBER;
    }

    /**
     * Возвращает значение типа по его коду.
     *
     * @param code код значения
     * @return значение типа
     */
    public static X21ClientNumberType getByValue(String code) {
        return getByValue(Integer.parseInt(code));
    }

    /**
     * Возвращает код по значению типа.
     *
     * @param clientNumberType Тип номера клиента
     * @return код значение
     */
    public static int getValue(X21ClientNumberType clientNumberType) {
        return clientNumberType.value;
    }
}
