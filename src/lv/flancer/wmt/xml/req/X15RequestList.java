/**
 * 
 */
package lv.flancer.wmt.xml.req;

import lv.flancer.wmt.xml.dict.Wmid;

/**
 * Интерфейс X15: Просмотр и изменение текущих настроек управления "по доверию".
 * 
 * @author Alex Gusev <flancer64@gmail.com>
 * @version 1.0
 * 
 */
public class X15RequestList extends AbstractRequest {
	/**
	 * ВМ-идентификатор, по которому необходимо получить список доверия,
	 * возвращенный запросом список будет эквивалентен списку, который может
	 * быть получен при авторизации этим идентификатором вручную в сервисе
	 * security.webmoney.ru.
	 */
	private Wmid wmid;

	/*
	 * (non-Javadoc)
	 * 
	 * @see lv.flancer.wmt.xml.req.XmlRequest#getTextToSign()
	 */
	@Override
	public String getTextToSign() {
		String result = "";
		result += this.wmid;
		result += this.requestNum;
		return result;
	}

	/**
	 * ВМ-идентификатор, по которому необходимо получить список доверия,
	 * возвращенный запросом список будет эквивалентен списку, который может
	 * быть получен при авторизации этим идентификатором вручную в сервисе
	 * security.webmoney.ru.
	 * 
	 * @return ВМ-идентификатор.
	 */
	public Wmid getWmid() {
		return wmid;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see lv.flancer.wmt.xml.req.XmlRequest#getXmlRequest()
	 */
	@Override
	public String getXmlRequest() {
		String result = "<?xml version=\"1.0\"  encoding=\"windows-1251\"?>";
		result += "<w3s.request>";
		result += "<reqn>" + this.requestNum + "</reqn>";
		if (this.signerWmid != null)
			result += "<wmid>" + this.signerWmid + "</wmid>";
		if (this.sign != null)
			result += "<sign>" + this.sign + "</sign>";
		result += "<gettrustlist>";
		result += "<wmid>" + this.wmid + "</wmid>";
		result += "</gettrustlist>";
		result += "</w3s.request>";
		return result;
	}

	/**
	 * ВМ-идентификатор, по которому необходимо получить список доверия,
	 * возвращенный запросом список будет эквивалентен списку, который может
	 * быть получен при авторизации этим идентификатором вручную в сервисе
	 * security.webmoney.ru.
	 * 
	 * @param wmid
	 *            ВМ-идентификатор, по которому необходимо получить список
	 *            доверия.
	 */
	public void setWmid(String wmid) {
		this.wmid = new Wmid(wmid);
	}

	/**
	 * ВМ-идентификатор, по которому необходимо получить список доверия,
	 * возвращенный запросом список будет эквивалентен списку, который может
	 * быть получен при авторизации этим идентификатором вручную в сервисе
	 * security.webmoney.ru.
	 * 
	 * @param wmid
	 *            ВМ-идентификатор, по которому необходимо получить список
	 *            доверия.
	 */
	public void setWmid(Wmid wmid) {
		this.wmid = wmid;
	}

}
