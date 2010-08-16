/**
 * 
 */
package lv.flancer.wmt.xml.dict;

import java.util.List;

/**
 * Информация об аттестате в ответе X11.
 * 
 * @author Alex Gusev <flancer64@gmail.com>
 * @version 1.0
 * 
 */
public class X11CertInfo {
	/**
	 * Выданные аттестаты.
	 */
	private List<X11AttestatRow> attestats;
	/**
	 * Опорный словарь.
	 */
	private List<X11DirectoryRow> directory;
	/**
	 * Признаки проверки персональных данных аттестатором и блокировки
	 * публичного отображения персональных данных.
	 */
	private List<X11UserInfoRow> userInfoChecks;
	/**
	 * Персональные данные владельца аттестата.
	 */
	private List<X11UserInfoRow> userInfoValues;
	/**
	 * WM идентификатор аттестата.
	 */
	private Wmid wmid;
	/**
	 * Информация о всех WMID, прикрепленных к данному аттестату.
	 */
	private List<X11WmidsRow> wmids;

	/**
	 * Выданные аттестаты.
	 * 
	 * @return Выданные аттестаты.
	 */
	public List<X11AttestatRow> getAttestats() {
		return attestats;
	}

	/**
	 * Опорный словарь.
	 * 
	 * @return Опорный словарь.
	 */
	public List<X11DirectoryRow> getDirectory() {
		return directory;
	}

	/**
	 * Признаки проверки персональных данных аттестатором и блокировки
	 * публичного отображения персональных данных.
	 * 
	 * @return Признаки проверки персональных данных аттестатором и блокировки
	 *         публичного отображения персональных данных.
	 */
	public List<X11UserInfoRow> getUserInfoChecks() {
		return userInfoChecks;
	}

	/**
	 * Персональные данные владельца аттестата.
	 * 
	 * @return Персональные данные владельца аттестата.
	 */
	public List<X11UserInfoRow> getUserInfoValues() {
		return userInfoValues;
	}

	/**
	 * WM идентификатор аттестата.
	 * 
	 * @return WM идентификатор аттестата.
	 */
	public Wmid getWmid() {
		return wmid;
	}

	/**
	 * Информация о всех WMID, прикрепленных к данному аттестату.
	 * 
	 * @return Информация о всех WMID, прикрепленных к данному аттестату.
	 */
	public List<X11WmidsRow> getWmids() {
		return wmids;
	}

	/**
	 * Выданные аттестаты.
	 * 
	 * @param attestats
	 *            Выданные аттестаты.
	 */
	public void setAttestats(List<X11AttestatRow> attestats) {
		this.attestats = attestats;
	}

	/**
	 * Опорный словарь.
	 * 
	 * @param directory
	 *            Опорный словарь.
	 */
	public void setDirectory(List<X11DirectoryRow> directory) {
		this.directory = directory;
	}

	/**
	 * Признаки проверки персональных данных аттестатором и блокировки
	 * публичного отображения персональных данных.
	 * 
	 * @param userInfoChecks
	 *            Признаки проверки персональных данных аттестатором и
	 *            блокировки публичного отображения персональных данных.
	 */
	public void setUserInfoChecks(List<X11UserInfoRow> userInfoChecks) {
		this.userInfoChecks = userInfoChecks;
	}

	/**
	 * Персональные данные владельца аттестата.
	 * 
	 * @param userInfoValues
	 *            Персональные данные владельца аттестата.
	 */
	public void setUserInfoValues(List<X11UserInfoRow> userInfoValues) {
		this.userInfoValues = userInfoValues;
	}

	/**
	 * WM идентификатор аттестата.
	 * 
	 * @param wmid
	 *            WM идентификатор аттестата.
	 */
	public void setWmid(String wmid) {
		this.wmid = new Wmid(wmid);
	}

	/**
	 * WM идентификатор аттестата.
	 * 
	 * @param wmid
	 *            WM идентификатор аттестата.
	 */
	public void setWmid(Wmid wmid) {
		this.wmid = wmid;
	}

	/**
	 * Информация о всех WMID, прикрепленных к данному аттестату.
	 * 
	 * @param wmids
	 *            Информация о всех WMID, прикрепленных к данному аттестату.
	 */
	public void setWmids(List<X11WmidsRow> wmids) {
		this.wmids = wmids;
	}
}
