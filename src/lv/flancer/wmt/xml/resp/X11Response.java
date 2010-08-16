/**
 * 
 */
package lv.flancer.wmt.xml.resp;

import lv.flancer.wmt.xml.dict.X11CertInfo;

/**
 * Интерфейс X11: Получение сведений об аттестате WM идентификатора и
 * персональных данных его владельца.
 * 
 * @author Alex Gusev <flancer64@gmail.com>
 * @version 1.0
 * 
 */
public class X11Response extends AbstractResponse {
	/**
	 * Информация об аттестате.
	 */
	private X11CertInfo certInfo;

	/**
	 * индикатор наличия доступа к закрытым полям аттестата: 'true' - есть
	 * доступ, 'false' - доступа нет.
	 */
	private boolean fullAccess;

	/**
	 * Информация об аттестате.
	 * 
	 * @return Информация об аттестате.
	 */
	public X11CertInfo getCertInfo() {
		return certInfo;
	}

	/**
	 * индикатор наличия доступа к закрытым полям аттестата: 'true' - есть
	 * доступ, 'false' - доступа нет.
	 * 
	 * @return индикатор наличия доступа к закрытым полям аттестата: 'true' -
	 *         есть доступ, 'false' - доступа нет.
	 */
	public boolean isFullAccess() {
		return fullAccess;
	}

	/**
	 * Информация об аттестате.
	 * 
	 * @param certInfo
	 *            Информация об аттестате.
	 */
	public void setCertInfo(X11CertInfo certInfo) {
		this.certInfo = certInfo;
	}

	/**
	 * Если fullaccess=1, значит владелец проверяемого WMID добавил WMID,
	 * подписывающий запрос в список доверенных и тем самым предоставил доступ к
	 * своим персональным данным. Если fullaccess=0, значит доступа к закрытым
	 * полям аттестата проверяемого WMID нет.
	 * 
	 * @param fullaccess
	 */
	public void setFullaccess(String fullaccess) {
		this.fullAccess = !fullaccess.equals("0");
	}

	/**
	 * индикатор наличия доступа к закрытым полям аттестата: 'true' - есть
	 * доступ, 'false' - доступа нет.
	 * 
	 * @param fullaccess
	 *            индикатор наличия доступа к закрытым полям аттестата: 'true' -
	 *            есть доступ, 'false' - доступа нет.
	 */
	public void setFullAccess(boolean fullaccess) {
		this.fullAccess = fullaccess;
	}
}
