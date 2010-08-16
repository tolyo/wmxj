/**
 * 
 */
package lv.flancer.wmt.xml.dict;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Информация об отдельном WMID, прикрепленном к аттестату. Владельцы
 * аттестатов, имеющих более одного WMID, могут задать уникальные для каждого
 * характеристики (дополнительная информация и название (nick)).
 * 
 * @author Alex Gusev <flancer64@gmail.com>
 * @version 1.0
 * 
 */
public class X11WmidsRow {
	/**
	 * Дата и время регистрации WMID в системе.
	 */
	private Date dateReg;
	/**
	 * Дополнительная информация о WMID.
	 */
	private String info;
	/**
	 * Уникальное для данного WMID имя (nick).
	 */
	private String nickname;
	/**
	 * WMID.
	 */
	private Wmid wmid;

	/**
	 * Дата и время регистрации WMID в системе.
	 * 
	 * @return Дата и время регистрации WMID в системе.
	 */
	public Date getDateReg() {
		return dateReg;
	}

	/**
	 * Дополнительная информация о WMID.
	 * 
	 * @return Дополнительная информация о WMID.
	 */
	public String getInfo() {
		return info;
	}

	/**
	 * Уникальное для данного WMID имя (nick).
	 * 
	 * @return Уникальное для данного WMID имя (nick).
	 */
	public String getNickname() {
		return nickname;
	}

	/**
	 * WMID.
	 * 
	 * @return WMID.
	 */
	public Wmid getWmid() {
		return wmid;
	}

	/**
	 * Дата и время регистрации WMID в системе.
	 * 
	 * @param dateReg
	 *            Дата и время регистрации WMID в системе.
	 */
	public void setDateReg(Date dateReg) {
		this.dateReg = dateReg;
	}

	/**
	 * Дата и время регистрации WMID в системе.
	 * 
	 * @param dateReg
	 *            Дата и время регистрации WMID в системе.
	 */
	public void setDateReg(String dateReg) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
		try {
			this.dateReg = sdf.parse(dateReg);
		} catch (ParseException e) {
			this.dateReg = null;
			e.printStackTrace();
		}
	}

	/**
	 * Дополнительная информация о WMID.
	 * 
	 * @param info
	 *            Дополнительная информация о WMID.
	 */
	public void setInfo(String info) {
		this.info = info;
	}

	/**
	 * Уникальное для данного WMID имя (nick).
	 * 
	 * @param nickname
	 *            Уникальное для данного WMID имя (nick).
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	/**
	 * WMID.
	 * 
	 * @param wmid
	 *            WMID.
	 */
	public void setWmid(String wmid) {
		this.wmid = new Wmid(wmid);
	}

	/**
	 * WMID.
	 * 
	 * @param wmid
	 *            WMID.
	 */
	public void setWmid(Wmid wmid) {
		this.wmid = wmid;
	}
}
