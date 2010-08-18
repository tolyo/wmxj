/**
 * 
 */
package lv.flancer.wmt.xml.resp.sax;

import java.util.ArrayList;
import java.util.List;

import lv.flancer.wmt.xml.dict.Trust;
import lv.flancer.wmt.xml.resp.X15ResponseList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

/**
 * Интерфейс X15: Просмотр и изменение текущих настроек управления "по доверию".
 * 
 * @author Alex Gusev <flancer64@gmail.com>
 * @version 1.0
 * 
 */
public class X15ResponseListHandler extends AbstractResponseHandler {
	/**
	 * Разобранный ответ от XML сервиса.
	 */
	private X15ResponseList response;

	private Trust trust;

	private List<Trust> trustList;

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		// разбор общих элементов
		super.endElement(uri, localName, qName);
		// разбор элементов конкретного ответа
		if (qName.equals("trustlist")) {
			this.response.setTrustList(this.trustList);
			return;
		}
		if (qName.equals("trust")) {
			this.trustList.add(this.trust);
			return;
		}
		// разбор элементов 'trust'
		if (qName.equals("master")) {
			this.trust.setMaster(this.parsedValue);
			return;
		}
		if (qName.equals("purse")) {
			this.trust.setPurse(this.parsedValue);
			return;
		}
		if (qName.equals("daylimit")) {
			this.trust.setDayLimit(this.parsedValue);
			return;
		}
		if (qName.equals("dlimit")) {
			this.trust.setDlimit(this.parsedValue);
			return;
		}
		if (qName.equals("wlimit")) {
			this.trust.setWlimit(this.parsedValue);
			return;
		}
		if (qName.equals("mlimit")) {
			this.trust.setMlimit(this.parsedValue);
			return;
		}
		if (qName.equals("dsum")) {
			this.trust.setDsum(this.parsedValue);
			return;
		}
		if (qName.equals("wsum")) {
			this.trust.setWsum(this.parsedValue);
			return;
		}
		if (qName.equals("msum")) {
			this.trust.setMsum(this.parsedValue);
			return;
		}
		if (qName.equals("lastsumdate")) {
			this.trust.setLastSumDate(this.parsedValue);
			return;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see lv.flancer.wmt.xml.resp.sax.AbstractResponseHandler#getResponse()
	 */
	@Override
	public X15ResponseList getResponse() {
		return this.response;
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		// обработка общих элементов
		super.startElement(uri, localName, qName, attributes);
		// создаем новый экземпляр ответа
		if (qName.equals("w3s.response")) {
			this.response = new X15ResponseList();
		}
		if (qName.equals("trustlist")) {
			this.trustList = new ArrayList<Trust>();
			this.response.setCount(attributes.getValue("cnt"));
		}
		if (qName.equals("trust")) {
			this.trust = new Trust();
			this.trust.setId(attributes.getValue("id"));
			this.trust.setHasInvRight(attributes.getValue("inv"));
			this.trust.setHasTransRight(attributes.getValue("trans"));
			this.trust.setHasPurseRight(attributes.getValue("purse"));
			this.trust.setHasTransHistRight(attributes.getValue("transhist"));
		}
	}

}
