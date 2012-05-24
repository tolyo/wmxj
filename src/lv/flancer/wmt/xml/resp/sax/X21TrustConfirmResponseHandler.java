package lv.flancer.wmt.xml.resp.sax;

import lv.flancer.wmt.xml.resp.X21TrustConfirmResponse;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

/**
 *
 * @author ainar
 */
public class X21TrustConfirmResponseHandler extends AbstractResponseHandler {

    private X21TrustConfirmResponse response;
    
    /**
     * Разобранный ответ от XML сервиса.
     *
     * @return Разобранный ответ от XML сервиса.
     */
    @Override
    public X21TrustConfirmResponse getResponse() {
        return response;
    }
    

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
                
        // обработка общих элементов
        super.startElement(uri, localName, qName, attributes);
        // создаем новый экземпляр ответа
        if (qName.equals("merchant.response")) {
            this.response = new X21TrustConfirmResponse();
        }
        // задаем Id операции
        if (qName.equals("trust")) {
            this.response.setTrustID(attributes.getValue("id"));            
        }
        
        // начало разбора HTML encoded деталей платежа
        if (qName.equals("userdesc")) {
            this.isHtmlEncodedBeingParsed = true;
            this.parsedValue = "";
        }
    }
    
    
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
               
        // разбор общих элементов в "w3s.response"
        super.endElement(uri, localName, qName);
        if (qName.equals("userdesc")) {
            this.response.setUserDesc(this.parsedValue);
            this.isHtmlEncodedBeingParsed = false;
            return;
        }     
                
        if (qName.equals("slavepurse")) {
            this.response.setSlavePurse(this.parsedValue);
            return;
        }        
                    
        if (qName.equals("slavewmid")) {
            this.response.setSlaveWmid(this.parsedValue);
            return;
        }
        
        if (qName.equals("masterwmid")) {
            this.response.setMasterwmid(this.parsedValue);
            return;
        }
    }
        
}
