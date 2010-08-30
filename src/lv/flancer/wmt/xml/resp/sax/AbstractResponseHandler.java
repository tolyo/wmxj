package lv.flancer.wmt.xml.resp.sax;

import java.io.UnsupportedEncodingException;

import lv.flancer.wmt.xml.resp.AbstractResponse;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Базовый класс для создания обработчиков ответов.
 * 
 * @author Alex Gusev <flancer64@gmail.com>
 * @version 1.0
 * 
 */
public abstract class AbstractResponseHandler extends DefaultHandler {
	/**
	 * Кодировка, используемая для ответов.
	 */
	private String httpCharset = "windows-1251";

	/**
	 * Флаг, указывающий, что происходит разбор html-encoded элемента.
	 */
	protected boolean isHtmlEncodedBeingParsed = false;

	/**
	 * Значение текущего разобранного элемента xml-документа.
	 */
	protected String parsedValue;

	public AbstractResponseHandler() {
		super();
	}
	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		if (this.isHtmlEncodedBeingParsed) {
			this.parsedValue += new String(ch, start, length);
		} else {
			this.parsedValue = (new String(ch, start, length)).trim();
		}
	}

	/**
	 * Преобразует строки из кодировки {@link #httpCharset} в UTF-8. По
	 * неведомым мне причинам описание и адрес в ответе X4 передаются как
	 * "![CDATA[...]]"
	 * 
	 * @param encoded
	 * @return
	 */
	protected String decodeCharset(String encoded) {
		String result = "";
		try {
			result = new String(encoded.getBytes(httpCharset));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			// do nothing
		}
		return result;
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		// разбор подмножества элемента "w3s.response"
		if (qName.equals("reqn")) {
			this.getResponse().setRequestNum(this.parsedValue);
			return;
		}
		if (qName.equals("retval")) {
			this.getResponse().setRetVal(this.parsedValue);
			return;
		}
		if (qName.equals("retdesc")) {
			this.getResponse().setRetDesc(this.parsedValue);
			this.isHtmlEncodedBeingParsed = false;
			return;
		}
	}

	/**
	 * Кодировка, используемая для ответов.
	 * 
	 * @return Кодировка, используемая для ответов.
	 */
	public String getHttpCharset() {
		return httpCharset;
	}

	/**
	 * Разобранный ответ от XML сервиса.
	 * 
	 * @return Разобранный ответ от XML сервиса.
	 */
	public abstract AbstractResponse getResponse();

	/**
	 * Кодировка, используемая для ответов.
	 * 
	 * @param httpCharset
	 *            Кодировка, используемая для ответов.
	 */
	public void setHttpCharset(String httpCharset) {
		this.httpCharset = httpCharset;
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		// начало разбора HTML encoded деталей платежа
		if (qName.equals("retdesc")) {
			this.isHtmlEncodedBeingParsed = true;
			this.parsedValue = "";
		}
	}

}