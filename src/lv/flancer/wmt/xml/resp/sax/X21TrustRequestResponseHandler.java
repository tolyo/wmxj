package lv.flancer.wmt.xml.resp.sax;

import lv.flancer.wmt.xml.dict.X21SmsType;
import lv.flancer.wmt.xml.resp.X21TrustRequestResponse;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

/**
 *
 * @author ainar
 */
public class X21TrustRequestResponseHandler extends AbstractResponseHandler {

    /**
     * Разобранный ответ от XML сервиса.
     */
    private X21TrustRequestResponse response;
    
    /**
     * Разобранный ответ от XML сервиса.
     *
     * @return Разобранный ответ от XML сервиса.
     */
    @Override
    public X21TrustRequestResponse getResponse() {
        return response;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
               
        // обработка общих элементов
        super.startElement(uri, localName, qName, attributes);
        // создаем новый экземпляр ответа
        if (qName.equals("merchant.response")) {
            this.response = new X21TrustRequestResponse();
        }
        
        if (qName.equals("trust")) {            
            this.response.setPurseID(attributes.getValue("purseid"));            
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
                
        if (qName.equals("realsmstype")) {
            this.response.setSmsType(X21SmsType.getByValue(this.parsedValue));
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
    }    
    
}
