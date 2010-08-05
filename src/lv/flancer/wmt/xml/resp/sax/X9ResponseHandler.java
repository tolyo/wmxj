/**
 * 
 */
package lv.flancer.wmt.xml.resp.sax;

import java.util.ArrayList;
import java.util.List;

import lv.flancer.wmt.xml.dict.WmPurse;
import lv.flancer.wmt.xml.resp.X9Response;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

/**
 * Интерфейс X9: Получение информации о балансе на кошельках.
 * 
 * @author Alex Gusev <flancer64@gmail.com>
 * @version 1.0
 * 
 */
public class X9ResponseHandler extends AbstractResponseHandler {

	/**
	 * Флаг, показывающий, что происходит разбор элементов, относящихся к
	 * "w3s.response/purses/purse".
	 */
	private boolean isPurseElementBeingParsed = false;
	/**
	 * Отдельный кошелек.
	 */
	private WmPurse purse;
	/**
	 * Список кошельков с остатками.
	 */
	private List<WmPurse> pursesList;
	/**
	 * Разобранный ответ от XML сервиса.
	 */
	private X9Response response;

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		if (this.isPurseElementBeingParsed) {
			// разбор подмножества элемента "w3s.response/purses/purse"
			this.endElementPurse(qName);
			return;
		} else {
			// разбор общих для большинства запросов элементов
			super.endElement(uri, localName, qName);
			// разбор подмножества элемента "w3s.response/purses"
			if (qName.equals("purses")) {
				this.response.setPurseList(this.pursesList);
				return;
			}
		}
	}

	/**
	 * Разбор элементов, входящих в подмножество "w3s.response/purses/purse"
	 * 
	 * @param name
	 */
	private void endElementPurse(String name) {
		// закончили разбор подмножества элемента
		// "w3s.response/purses/purse"
		if (name.equals("purse")) {
			this.isPurseElementBeingParsed = false;
			this.pursesList.add(this.purse);
			return;
		}
		// разбор подмножества элемента
		// "w3s.response/purses/purse"
		if (name.equals("pursename")) {
			this.purse.setNumber(this.parsedValue);
			return;
		}
		if (name.equals("amount")) {
			this.purse.setAmount(this.parsedValue);
			return;
		}
		if (name.equals("desc")) {
			this.purse.setDesc(this.parsedValue);
			return;
		}
		if (name.equals("outsideopen")) {
			this.purse.setOutsideOpen(this.parsedValue);
			return;
		}
		if (name.equals("lastintr")) {
			this.purse.setLastInTr(this.parsedValue);
			return;
		}
		if (name.equals("lastouttr")) {
			this.purse.setLastOutTr(this.parsedValue);
			return;
		}
	}

	/**
	 * Разобранный ответ от XML сервиса.
	 * 
	 * @return Разобранный ответ от XML сервиса.
	 */
	public X9Response getResponse() {
		return response;
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		// обработка общих элементов
		super.startElement(uri, localName, qName, attributes);
		// создаем новый экземпляр ответа
		if (qName.equals("w3s.response")) {
			this.response = new X9Response();
		}
		// создаем список кошельков
		if (qName.equals("purses")) {
			this.response.setCount(attributes.getValue("cnt"));
			this.pursesList = new ArrayList<WmPurse>();
		}
		// начало разбора отдельного кошелька
		if (qName.equals("purse")) {
			this.isPurseElementBeingParsed = true;
			this.purse = new WmPurse();
			this.purse.setId(attributes.getValue("id"));
		}
	}

}
