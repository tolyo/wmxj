/**
 * 
 */
package lv.flancer.wmt.xml.resp.sax;

import java.util.ArrayList;
import java.util.List;

import lv.flancer.wmt.xml.dict.X11AttestatRow;
import lv.flancer.wmt.xml.dict.X11CertInfo;
import lv.flancer.wmt.xml.dict.X11DirectoryRow;
import lv.flancer.wmt.xml.dict.X11UserInfoRow;
import lv.flancer.wmt.xml.dict.X11WmidsRow;
import lv.flancer.wmt.xml.resp.X11Response;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

/**
 * Интерфейс X11: Получение сведений об аттестате WM идентификатора и
 * персональных данных его владельца.
 * 
 * @author Alex Gusev <flancer64@gmail.com>
 * @version 1.0
 * 
 */
public class X11ResponseHandler extends AbstractResponseHandler {
	/**
	 * Отдельный аттестат.
	 */
	private X11AttestatRow attestatRow;
	/**
	 * Выданные аттестаты.
	 */
	private List<X11AttestatRow> attestats;
	/**
	 * Информация об аттестате.
	 */
	private X11CertInfo certInfo;
	/**
	 * Опорный словарь.
	 */
	private List<X11DirectoryRow> directory;
	/**
	 * Отдельная запись в опорном словаре.
	 */
	private X11DirectoryRow directoryRow;
	private boolean isAttestatElementBeingParsed = false;
	private boolean isCheckLockElementBeingParsed = false;
	private boolean isValueElementBeingParsed = false;
	private boolean isWmidsElementBeingParsed = false;
	/**
	 * Разобранный ответ от XML сервиса.
	 */
	private X11Response response;
	/**
	 * Признаки проверки персональных данных аттестатором и блокировки
	 * публичного отображения персональных данных.
	 */
	private List<X11UserInfoRow> userInfoChecks;
	/**
	 * Структура UserInfo, в которую можно закладывать как данные, так и
	 * признаки проверки персональных данных аттестатором и блокировки
	 * публичного отображения
	 */
	private X11UserInfoRow userInfoRow;
	/**
	 * Персональные данные владельца аттестата.
	 */
	private List<X11UserInfoRow> userInfoValues;
	/**
	 * Информация о всех WMID, прикрепленных к данному аттестату.
	 */
	private ArrayList<X11WmidsRow> wmids;
	/**
	 * Отдельная запись о прикрепленном WMID.
	 */
	private X11WmidsRow wmidsRow;

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		// разбор общих для большинства запросов элементов
		super.endElement(uri, localName, qName);
		// /response/fullaccess
		if (qName.equals("fullaccess")) {
			this.response.setFullaccess(this.parsedValue);
			return;
		}
		// /response/certinfo/directory/[ctype | jstatus | tid]
		if (qName.equals("ctype") || qName.equals("jstatus")
				|| qName.equals("tid")) {
			this.directoryRow.setValue(this.decodeCharset(this.parsedValue));
			this.directory.add(this.directoryRow);
			return;
		}
		// /response/certinfo/directory
		if (qName.equals("directory")) {
			this.certInfo.setDirectory(this.directory);
			return;
		}
		// /response/certinfo/attestat
		if (qName.equals("attestat")) {
			this.isAttestatElementBeingParsed = false;
			this.certInfo.setAttestats(this.attestats);
			return;
		}
		// /response/certinfo/wmids
		if (qName.equals("wmids")) {
			this.isWmidsElementBeingParsed = false;
			this.certInfo.setWmids(this.wmids);
			return;
		}
		// /response/certinfo/userinfo/value
		if (qName.equals("value")) {
			this.isValueElementBeingParsed = false;
			this.certInfo.setUserInfoValues(this.userInfoValues);
			return;
		}
		// /response/certinfo/userinfo/check-lock
		if (qName.equals("check-lock")) {
			this.isCheckLockElementBeingParsed = false;
			this.certInfo.setUserInfoChecks(this.userInfoChecks);
			return;
		}
		// /response/certinfo
		if (qName.equals("certinfo")) {
			this.response.setCertInfo(this.certInfo);
			return;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see lv.flancer.wmt.xml.resp.sax.AbstractResponseHandler#getResponse()
	 */
	@Override
	public X11Response getResponse() {
		return this.response;
	}

	/**
	 * Производит разбор структуры типа X11UserInfoRow (данные или признаки
	 * блокировки/публичности).
	 * 
	 * @param qName
	 * @param attributes
	 */
	private void parseUserInfo(String qName, Attributes attributes) {
		this.userInfoRow = new X11UserInfoRow();

		this.userInfoRow.setCtype(this.decodeCharset(attributes
				.getValue("ctype")));
		this.userInfoRow.setJstatus(this.decodeCharset(attributes
				.getValue("jstatus")));
		this.userInfoRow.setOsnovainfo(this.decodeCharset(attributes
				.getValue("osnovainfo")));
		this.userInfoRow.setLocked(this.decodeCharset(attributes
				.getValue("locked")));
		this.userInfoRow.setNickname(this.decodeCharset(attributes
				.getValue("nickname")));
		this.userInfoRow.setInfoopen(this.decodeCharset(attributes
				.getValue("infoopen")));
		this.userInfoRow
				.setCity(this.decodeCharset(attributes.getValue("city")));
		this.userInfoRow.setCountry(this.decodeCharset(attributes
				.getValue("country")));
		this.userInfoRow.setZipcode(this.decodeCharset(attributes
				.getValue("zipcode")));
		this.userInfoRow.setAdres(this.decodeCharset(attributes
				.getValue("adres")));
		this.userInfoRow.setFname(this.decodeCharset(attributes
				.getValue("fname")));
		this.userInfoRow.setIname(this.decodeCharset(attributes
				.getValue("iname")));
		this.userInfoRow.setOname(this.decodeCharset(attributes
				.getValue("oname")));
		this.userInfoRow.setPnomer(this.decodeCharset(attributes
				.getValue("pnomer")));
		this.userInfoRow.setPdate(this.decodeCharset(attributes
				.getValue("pdate")));
		this.userInfoRow.setPcountry(this.decodeCharset(attributes
				.getValue("pcountry")));
		this.userInfoRow.setPcity(this.decodeCharset(attributes
				.getValue("pcity")));
		this.userInfoRow.setPbywhom(this.decodeCharset(attributes
				.getValue("pbywhom")));
		this.userInfoRow.setRcountry(this.decodeCharset(attributes
				.getValue("rcountry")));
		this.userInfoRow.setRcity(this.decodeCharset(attributes
				.getValue("rcity")));
		this.userInfoRow.setRadres(this.decodeCharset(attributes
				.getValue("radres")));
		this.userInfoRow.setBplace(this.decodeCharset(attributes
				.getValue("bplace")));
		this.userInfoRow
				.setBday(this.decodeCharset(attributes.getValue("bday")));
		this.userInfoRow.setBmonth(this.decodeCharset(attributes
				.getValue("bmonth")));
		this.userInfoRow.setByear(this.decodeCharset(attributes
				.getValue("byear")));
		this.userInfoRow
				.setName(this.decodeCharset(attributes.getValue("name")));
		this.userInfoRow.setDirfio(this.decodeCharset(attributes
				.getValue("dirfio")));
		this.userInfoRow.setBuhfio(this.decodeCharset(attributes
				.getValue("buhfio")));
		this.userInfoRow.setInn(this.decodeCharset(attributes.getValue("inn")));
		this.userInfoRow.setOkpo(this.decodeCharset(attributes
				.getValue("okpo")));
		this.userInfoRow.setOkonx(this.decodeCharset(attributes
				.getValue("okonx")));
		this.userInfoRow.setJadres(this.decodeCharset(attributes
				.getValue("jadres")));
		this.userInfoRow.setJcountry(this.decodeCharset(attributes
				.getValue("jcountry")));
		this.userInfoRow.setJcity(this.decodeCharset(attributes
				.getValue("jcity")));
		this.userInfoRow.setJzipcode(this.decodeCharset(attributes
				.getValue("jzipcode")));
		this.userInfoRow.setBankname(this.decodeCharset(attributes
				.getValue("bankname")));
		this.userInfoRow
				.setBik(this.decodeCharset(attributes.getValue("bik")));
		this.userInfoRow
				.setKs(this.decodeCharset(attributes.getValue("ks")));
		this.userInfoRow
				.setRs(this.decodeCharset(attributes.getValue("rs")));
		this.userInfoRow.setPhonehome(this.decodeCharset(attributes
				.getValue("phonehome")));
		this.userInfoRow.setPhonemobile(this.decodeCharset(attributes
				.getValue("phonemobile")));
		this.userInfoRow
				.setIcq(this.decodeCharset(attributes.getValue("icq")));
		this.userInfoRow
				.setFax(this.decodeCharset(attributes.getValue("fax")));
		this.userInfoRow.setEmail(this.decodeCharset(attributes
				.getValue("email")));
		this.userInfoRow
				.setWeb(this.decodeCharset(attributes.getValue("web")));
		this.userInfoRow.setPhone(this.decodeCharset(attributes
				.getValue("phone")));
		this.userInfoRow.setCap_owner(this.decodeCharset(attributes
				.getValue("cap_owner")));
		this.userInfoRow.setPasdoc(this.decodeCharset(attributes
				.getValue("pasdoc")));
		this.userInfoRow.setInndoc(this.decodeCharset(attributes
				.getValue("inndoc")));
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		// обработка общих элементов
		super.startElement(uri, localName, qName, attributes);
		// /response
		if (qName.equals("response")) {
			this.response = new X11Response();
			this.response.setRetVal(attributes.getValue("retval"));
		}
		// /response/certinfo
		if (qName.equals("certinfo")) {
			this.certInfo = new X11CertInfo();
			this.certInfo.setWmid(attributes.getValue("wmid"));

		}
		// /response/certinfo/directory
		if (qName.equals("directory")) {
			this.directory = new ArrayList<X11DirectoryRow>();
		}
		// /response/certinfo/directory/[ctype | jstatus | tid]
		if (qName.equals("ctype")) {
			this.directoryRow = new X11DirectoryRow();
			this.directoryRow.setType("ctype");
			this.directoryRow.setId(attributes.getValue("id"));
		}
		if (qName.equals("jstatus")) {
			this.directoryRow = new X11DirectoryRow();
			this.directoryRow.setType("jstatus");
			this.directoryRow.setId(attributes.getValue("id"));
		}
		if (qName.equals("tid")) {
			this.directoryRow = new X11DirectoryRow();
			this.directoryRow.setType("tid");
			this.directoryRow.setId(attributes.getValue("id"));
		}
		// /response/certinfo/attestat
		if (qName.equals("attestat")) {
			this.isAttestatElementBeingParsed = true;
			this.attestats = new ArrayList<X11AttestatRow>();
		}
		// /response/certinfo/wmids
		if (qName.equals("wmids")) {
			this.isWmidsElementBeingParsed = true;
			this.wmids = new ArrayList<X11WmidsRow>();
		}
		// /response/certinfo/userinfo/value
		if (qName.equals("value")) {
			this.isValueElementBeingParsed = true;
			this.userInfoValues = new ArrayList<X11UserInfoRow>();
		}
		// /response/certinfo/userinfo/check-lock
		if (qName.equals("check-lock")) {
			this.isCheckLockElementBeingParsed = true;
			this.userInfoChecks = new ArrayList<X11UserInfoRow>();
		}
		// разбор элемента 'row', который может принадлежать [attestat | wmids |
		// value | check-lock]
		if (qName.equals("row")) {
			if (this.isAttestatElementBeingParsed) {
				this.attestatRow = new X11AttestatRow();
				this.attestatRow.setCid(attributes.getValue("cid"));
				this.attestatRow.setRegcid(attributes.getValue("regcid"));
				this.attestatRow.setTid(attributes.getValue("tid"));
				this.attestatRow.setLocked(attributes.getValue("locked"));
				this.attestatRow.setAdmlocked(attributes.getValue("admlocked"));
				this.attestatRow.setRecalled(attributes.getValue("recalled"));
				this.attestatRow.setDateCrt(attributes.getValue("datecrt"));
				this.attestatRow.setDatediff(attributes.getValue("datediff"));
				this.attestatRow.setRegNickname(this.decodeCharset(attributes
						.getValue("regnickname")));
				this.attestatRow.setRegwmid(attributes.getValue("regwmid"));
				this.attestats.add(this.attestatRow);
				return;
			}
			if (this.isWmidsElementBeingParsed) {
				this.wmidsRow = new X11WmidsRow();
				this.wmidsRow.setWmid(attributes.getValue("wmid"));
				this.wmidsRow.setInfo(this.decodeCharset(attributes
						.getValue("info")));
				this.wmidsRow.setNickname(this.decodeCharset(attributes
						.getValue("nickname")));
				this.wmidsRow.setDateReg(attributes.getValue("datereg"));
				this.wmids.add(this.wmidsRow);
				return;
			}
			// /response/certinfo/userinfo/value/row
			if (this.isValueElementBeingParsed) {
				this.parseUserInfo(qName, attributes);
				this.userInfoValues.add(this.userInfoRow);
				return;
			}
			// /response/certinfo/userinfo/check-lock/row
			if (this.isCheckLockElementBeingParsed) {
				this.parseUserInfo(qName, attributes);
				this.userInfoChecks.add(this.userInfoRow);
				return;
			}
		}
	}
}
