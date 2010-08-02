/**
 * 
 */
package lv.flancer.wmt.xml.resp;

import lv.flancer.wmt.xml.dict.WmDate;
import lv.flancer.wmt.xml.dict.Wmid;

/**
 * Интерфейс X6: Отправка сообщения произвольному WM-идентификатору по
 * внутренней почте.
 * 
 * @author Alex Gusev <flancer64@gmail.com>
 * @version 1.0
 * 
 */
public class X6Response extends AbstractResponse {

	/**
	 * Дата и время передачи сообщения.
	 */
	private WmDate dateCrt;
	/**
	 * Уникальный номер сообщения в системе учета WebMoney.
	 */
	private long id;
	/**
	 * Тема сообщения.
	 */
	private String msgSubj;
	/**
	 * Текст сообщения.
	 * 
	 */
	private String msgText;
	/**
	 * WM-идентификатор получателя сообщения.
	 */
	private Wmid receiverWmid;
	/**
	 * Дата и время передачи сообщения.
	 * 
	 * @return Дата и время передачи сообщения.
	 */
	public WmDate getDateCrt() {
		return dateCrt;
	}

	/**
	 * Уникальный номер сообщения в системе учета WebMoney.
	 * 
	 * @return Уникальный номер сообщения в системе учета WebMoney.
	 */
	public long getId() {
		return id;
	}

	/**
	 * Тема сообщения.
	 * 
	 * @return Тема сообщения.
	 */
	public String getMsgSubj() {
		return msgSubj;
	}

	/**
	 * Текст сообщения.
	 * 
	 * @return Текст сообщения.
	 */
	public String getMsgText() {
		return msgText;
	}

	/**
	 * WM-идентификатор получателя сообщения.
	 * 
	 * @return WM-идентификатор получателя сообщения.
	 */
	public Wmid getReceiverWmid() {
		return receiverWmid;
	}

	/**
	 * Дата и время передачи сообщения.
	 * 
	 * @param dateCrt
	 *            Дата и время передачи сообщения.
	 */
	public void setDateCrt(String dateCrt) {
		this.dateCrt = new WmDate(dateCrt);
	}

	/**
	 * Дата и время передачи сообщения.
	 * 
	 * @param dateCrt
	 *            Дата и время передачи сообщения.
	 */
	public void setDateCrt(WmDate dateCrt) {
		this.dateCrt = dateCrt;
	}

	/**
	 * Уникальный номер сообщения в системе учета WebMoney.
	 * 
	 * @param id
	 *            Уникальный номер сообщения в системе учета WebMoney.
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * Уникальный номер сообщения в системе учета WebMoney.
	 * 
	 * @param id
	 *            Уникальный номер сообщения в системе учета WebMoney.
	 */
	public void setId(String id) {
		this.id = Long.parseLong(id);
	}

	/**
	 * Тема сообщения.
	 * 
	 * @param msgSubj
	 *            Тема сообщения.
	 */
	public void setMsgSubj(String msgSubj) {
		this.msgSubj = msgSubj;
	}

	/**
	 * Текст сообщения.
	 * 
	 * @param msgText
	 *            Текст сообщения.
	 */
	public void setMsgText(String msgText) {
		this.msgText = msgText;
	}

	/**
	 * WM-идентификатор получателя сообщения.
	 * 
	 * @param receiverWmid
	 *            WM-идентификатор получателя сообщения.
	 */
	public void setReceiverWmid(String receiverWmid) {
		this.receiverWmid = new Wmid(receiverWmid);
	}

	/**
	 * WM-идентификатор получателя сообщения.
	 * 
	 * @param receiverWmid
	 *            WM-идентификатор получателя сообщения.
	 */
	public void setReceiverWmid(Wmid receiverWmid) {
		this.receiverWmid = receiverWmid;
	}

}
