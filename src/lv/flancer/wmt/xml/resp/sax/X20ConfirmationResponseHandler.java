/**  
 * 
 */
package lv.flancer.wmt.xml.resp.sax;

import lv.flancer.wmt.xml.resp.X20ConfirmationResponse;
import lv.flancer.wmt.xml.resp.X20InitiationResponse;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;


/**
 * Интерфейс X20: Проведение транзакции в merchant.webmoney без ухода с сайта
 * (ресурса, сервиса, приложения) продавца. 
 * 2-й запрос, подтверждение оплаты.
 * 
 * 
 * @author ainar
 */
public class X20ConfirmationResponseHandler extends AbstractResponseHandler {

     /**
     * Разобранный ответ от XML сервиса.
     */
    private X20ConfirmationResponse response;
    
    /**
     * Разобранный ответ от XML сервиса.
     *
     * @return Разобранный ответ от XML сервиса.
     */
    @Override
    public X20ConfirmationResponse getResponse() {
        return this.response;
    }
    
     @Override
    public void startElement(String uri, String localName, String qName,
            Attributes attributes) throws SAXException {
        // обработка общих элементов
        super.startElement(uri, localName, qName, attributes);
        // создаем новый экземпляр ответа
        if (qName.equals("merchant.response")) {
            this.response = new X20ConfirmationResponse();
        }
        // задаем Id операции
        if (qName.equals("operation")) {
            this.response.setWmTransId(attributes.getValue("wmtransid"));
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
        if (qName.equals("amount")) {
            this.response.setAmount(this.parsedValue);
            return;
        }
        if (qName.equals("operdate")) {
            this.response.setOperDate(this.parsedValue);
            return;
        }        
//        if (qName.equals("purpose")) {
//            this.response.setPurpose(this.parsedValue);
//            return;
//        }
        if (qName.equals("pursefrom")) {
            this.response.setPurseFrom(this.parsedValue);
            return;
        }         
        if (qName.equals("wmidfrom")) {
            this.response.setWmidFrom(this.parsedValue);
            return;
        }         
    }        
}
