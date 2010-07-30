/**
 * 
 */
package lv.flancer.wmt.xml.req;

import lv.flancer.wmt.xml.dict.Wmid;

/**
 * Интерфейс типового запроса к WMT XML.
 * 
 * @author Alex Gusev <flancer64@gmail.com>
 * @version 1.0
 * 
 */
public interface XmlRequest {
	/**
	 * Возвращает строку, которая должна быть подписана при идентификации по
	 * схеме Classic.
	 * 
	 * @return строка, которая должна быть подписана при идентификации по схеме
	 *         Classic.
	 */
	public String getTextToSign();

	/**
	 * Возвращает XML-документ, в виде которого запрос передается в WMT XML.
	 * 
	 * @return Возвращает XML-документ, в виде которого запрос передается в WMT
	 *         XML.
	 */
	public String getXmlRequest();

	/**
	 * Подпись запроса.
	 * 
	 * @param signature
	 *            Подпись запроса.
	 */
	public void setSign(String signature);

	/**
	 * Установка WMID подписавшего запрос.
	 * 
	 * @param signerWmid
	 *            WMID подписавшего запрос.
	 */
	public void setSignerWmid(Wmid signerWmid);
}
