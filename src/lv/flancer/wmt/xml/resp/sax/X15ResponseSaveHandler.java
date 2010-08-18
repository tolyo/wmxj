/**
 * 
 */
package lv.flancer.wmt.xml.resp.sax;

import lv.flancer.wmt.xml.resp.X15ResponseSave;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

/**
 * Интерфейс X15: Просмотр и изменение текущих настроек управления "по доверию".
 * 
 * @author Alex Gusev <flancer64@gmail.com>
 * @version 1.0
 * 
 */
public class X15ResponseSaveHandler extends AbstractResponseHandler {
	/**
	 * Разобранный ответ от XML сервиса.
	 */
	private X15ResponseSave response;

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		// разбор общих элементов
		super.endElement(uri, localName, qName);
		// разбор элементов конкретного ответа
				if (qName.equals("master")) {
					this.response.setMaster(this.parsedValue);
			return;
		}
		if (qName.equals("purse")) {
			this.response.setPurse(this.parsedValue);
			return;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see lv.flancer.wmt.xml.resp.sax.AbstractResponseHandler#getResponse()
	 */
	@Override
	public X15ResponseSave getResponse() {
		return this.response;
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		// обработка общих элементов
		super.startElement(uri, localName, qName, attributes);
		// создаем новый экземпляр ответа
		if (qName.equals("w3s.response")) {
			this.response = new X15ResponseSave();
		}
		if (qName.equals("trust")) {
			this.response.setId(attributes.getValue("id"));
			this.response.setHasInvRight(attributes.getValue("inv"));
			this.response.setHasTransRight(attributes.getValue("trans"));
			this.response.setHasPurseRight(attributes.getValue("purse"));
			this.response
					.setHasTransHistRight(attributes.getValue("transhist"));
		}
	}
}
