/**
 * 
 */
package lv.flancer.wmt.xml.req;

import java.util.List;

import lv.flancer.wmt.xml.dict.Wmid;

/**
 * Интерфейс X17: Операции с арбитражными контрактами.
 * 
 * @author Alex Gusev <flancer64@gmail.com>
 * @version 1.0
 * 
 */
public class X17Request extends AbstractRequest {
	/**
	 * Список WMID пользователей, которым разрешается акцептовывать данный
	 * контракт. Для контрактов с ограниченным доступом contract.request\ctype=2
	 * указывать список допущенных к нему пользователей обязательно, после
	 * публикации контракта изменить этот список нельзя.
	 */
	private List<Wmid> accessList;
	/**
	 * Тип контракта. ctype=1 – контракт с открытым доступом, ctype=2 – контракт
	 * с ограниченным доступом.
	 */
	private int ctype;
	/**
	 * Название контракта (не более 255 символов).
	 */
	private String name;
	/**
	 * Текст контракта. Для разделения строк в тексте контракта используйте:
	 * "\r\n".
	 */
	private String text;

	/**
	 * Список WMID пользователей, которым разрешается акцептовывать данный
	 * контракт. Для контрактов с ограниченным доступом contract.request\ctype=2
	 * указывать список допущенных к нему пользователей обязательно, после
	 * публикации контракта изменить этот список нельзя.
	 * 
	 * @return Список WMID пользователей, которым разрешается акцептовывать
	 *         данный контракт.
	 */
	public List<Wmid> getAccessList() {
		return accessList;
	}

	/**
	 * Тип контракта. ctype=1 – контракт с открытым доступом, ctype=2 – контракт
	 * с ограниченным доступом.
	 * 
	 * @return Тип контракта. ctype=1 – контракт с открытым доступом, ctype=2 –
	 *         контракт с ограниченным доступом.
	 */
	public int getCtype() {
		return ctype;
	}

	/**
	 * Название контракта (не более 255 символов).
	 * 
	 * @return Название контракта (не более 255 символов).
	 */
	public String getName() {
		return name;
	}

	/**
	 * Текст контракта. Для разделения строк в тексте контракта используйте:
	 * "\r\n".
	 * 
	 * @return Текст контракта.
	 */
	public String getText() {
		return text;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see lv.flancer.wmt.xml.req.XmlRequest#getTextToSign()
	 */
	@Override
	public String getTextToSign() {
		String result = "";
		result += this.signerWmid;
		result += this.name.length();
		result += this.ctype;
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see lv.flancer.wmt.xml.req.XmlRequest#getXmlRequest()
	 */
	@Override
	public String getXmlRequest() {
		String result = "<?xml version=\"1.0\"  encoding=\"windows-1251\"?>";
		result += "<contract.request>";
		result += "<sign_wmid>" + this.signerWmid + "</sign_wmid>";
		result += "<name>" + this.name + "</name>";
		result += "<ctype>" + this.ctype + "</ctype>";
		result += "<text>" + this.text + "</text>";
		result += "<sign>" + this.sign + "</sign>";
		// accesslist
		if (this.accessList != null) {
			result += "<accesslist>";
			for (Wmid one : this.accessList) {
				result += "<wmid>" + one + "</wmid>";
			}
			result += "</accesslist>";
		}
		result += "</contract.request>";
		return result;
	}

	/**
	 * Список WMID пользователей, которым разрешается акцептовывать данный
	 * контракт. Для контрактов с ограниченным доступом contract.request\ctype=2
	 * указывать список допущенных к нему пользователей обязательно, после
	 * публикации контракта изменить этот список нельзя.
	 * 
	 * @param accessList
	 *            Список WMID пользователей, которым разрешается акцептовывать
	 *            данный контракт.
	 */
	public void setAccessList(List<Wmid> accessList) {
		this.accessList = accessList;
	}

	/**
	 * Тип контракта. ctype=1 – контракт с открытым доступом, ctype=2 – контракт
	 * с ограниченным доступом.
	 * 
	 * @param ctype
	 *            Тип контракта. ctype=1 – контракт с открытым доступом, ctype=2
	 *            – контракт с ограниченным доступом.
	 */
	public void setCtype(int ctype) {
		this.ctype = ctype;
	}

	/**
	 * Название контракта (не более 255 символов).
	 * 
	 * @param name
	 *            Название контракта (не более 255 символов).
	 */
	public void setName(String name) {
		this.name = prepareString(name, 255, true);
	}

	/**
	 * Текст контракта. Для разделения строк в тексте контракта используйте:
	 * "\r\n".
	 * 
	 * @param text
	 *            Текст контракта.
	 */
	public void setText(String text) {
		this.text = text;
	}
}
