/**
 *
 */
package lv.flancer.wmt.xml.resp.sax;

import lv.flancer.wmt.xml.dict.X20SmsType;
import lv.flancer.wmt.xml.resp.X20InitiationResponse;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

/**
 * Интерфейс X20: Проведение транзакции в merchant.webmoney без ухода с сайта
 * (ресурса, сервиса, приложения) продавца.
 * 1-й запрос, инициирование оплаты.
 *
 * @author ainar
 */
public class X20InitiationResponseHandler extends AbstractResponseHandler {

    /**
     * Разобранный ответ от XML сервиса.
     */
    private X20InitiationResponse response;

    /**
     * Разобранный ответ от XML сервиса.
     *
     * @return Разобранный ответ от XML сервиса.
     */
    @Override
    public X20InitiationResponse getResponse() {
        return response;
    }

    @Override
    public void startElement(String uri, String localName, String qName,
            Attributes attributes) throws SAXException {
        // обработка общих элементов
        super.startElement(uri, localName, qName, attributes);
        // создаем новый экземпляр ответа
        if (qName.equals("merchant.response")) {
            this.response = new X20InitiationResponse();
        }
        // задаем Id операции
        if (qName.equals("operation")) {
            this.response.setRequestNum(attributes.getValue("wminvoiceid"));
        }
        // начало разбора HTML encoded деталей платежа
        if (qName.equals("userdesc")) {
            this.isHtmlEncodedBeingParsed = true;
            this.parsedValue = "";
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName)
            throws SAXException {
        // разбор общих элементов в "w3s.response"
        super.endElement(uri, localName, qName);

        if (qName.equals("userdesc")) {
            this.response.setUserDesc(this.parsedValue);
            this.isHtmlEncodedBeingParsed = false;
            return;
        }
        // разбор подмножества элемента "merchant.response/operation"
        if (qName.equals("realsmstype")) {
            this.response.setSmsType(X20SmsType.getByValue(this.parsedValue));
            return;
        }
    }
}
