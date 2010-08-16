/**
 * 
 */
package lv.flancer.wmt.xml.dict;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Информация об отдельном аттестате.
 * 
 * @author Alex Gusev <flancer64@gmail.com>
 * @version 1.0
 * 
 */
public class X11AttestatRow {
	/**
	 * Право выдачи аттестатов. Для владельцев персональных аттестатов
	 * admlocked='false' означает наличие права выдачи начальных аттестатов, для
	 * владельцев аттестата регистратора admlocked='false' означает наличие
	 * права выдачи персональных аттестатов. admlocked='true' означает, что
	 * данный аттестатор лишен права выдачи аттестатов.
	 */
	private boolean admLocked;
	/**
	 * Внутренний номер владельца аттестата в системе центра аттестации
	 * (уникальный).
	 */
	private int cid;
	/**
	 * Дата и время (московское) выдачи аттестата.
	 */
	private Date dateCrt;
	/**
	 * Разница в днях между текущей датой и датой выдачи аттестата.
	 */
	private int dateDiff;
	/**
	 * Право выдачи аттестатов. Для владельцев персональных аттестатов
	 * locked='false' означает наличие права выдачи начальных аттестатов, для
	 * владельцев аттестата регистратора locked='false' означает наличие права
	 * выдачи персональных аттестатов. locked='true' означает временную
	 * блокировку выдачи аттестатов или отсутствие такого права.
	 */
	private boolean locked;
	/**
	 * Информация об отзыве аттестата. Если значение атрибута равно 'true', то
	 * этот аттестат отозван и его статус эквивалентен аттестату псевдонима.
	 * Параметр аналогичен response/certinfo/userinfo/value/row/@locked.
	 */
	private boolean recalled;
	/**
	 * Внутренний номер аттестатора в системе центра аттестации (уникальный).
	 */
	private int regCid;
	/**
	 * Название проекта, имя (nick) аттестатора, выдавшего данный аттестат.
	 */
	private String regNickname;
	/**
	 * WMID аттестатора, выдавшего данный аттестат.
	 */
	private Wmid regWmid;
	/**
	 * Тип аттестата (смотрите опорный словарь). При анализе данного параметра
	 * необходимо обратить внимание на атрибуты
	 * response/certinfo/attestat/row/@recalled или
	 * response/certinfo/userinfo/value/row/@locked Если их значение равно 1, то
	 * этот аттестат отозван и его статус эквивалентен аттестату псевдонима.
	 * Проверять можно любой из атрибутов.
	 */
	private int tid;

	/**
	 * Внутренний номер владельца аттестата в системе центра аттестации
	 * (уникальный).
	 * 
	 * @return Внутренний номер владельца аттестата в системе центра аттестации
	 *         (уникальный).
	 */
	public int getCid() {
		return cid;
	}

	/**
	 * Дата и время (московское) выдачи аттестата.
	 * 
	 * @return Дата и время (московское) выдачи аттестата.
	 */
	public Date getDateCrt() {
		return dateCrt;
	}

	/**
	 * Разница в днях между текущей датой и датой выдачи аттестата.
	 * 
	 * @return Разница в днях между текущей датой и датой выдачи аттестата.
	 */
	public int getDateDiff() {
		return dateDiff;
	}

	/**
	 * Внутренний номер аттестатора в системе центра аттестации (уникальный).
	 * 
	 * @return Внутренний номер аттестатора в системе центра аттестации
	 *         (уникальный).
	 */
	public int getRegCid() {
		return regCid;
	}

	/**
	 * Название проекта, имя (nick) аттестатора, выдавшего данный аттестат.
	 * 
	 * @return Название проекта, имя (nick) аттестатора, выдавшего данный
	 *         аттестат.
	 */
	public String getRegNickname() {
		return regNickname;
	}

	/**
	 * WMID аттестатора, выдавшего данный аттестат.
	 * 
	 * @return WMID аттестатора, выдавшего данный аттестат.
	 */
	public Wmid getRegWmid() {
		return regWmid;
	}

	/**
	 * Тип аттестата (смотрите опорный словарь). При анализе данного параметра
	 * необходимо обратить внимание на атрибуты
	 * response/certinfo/attestat/row/@recalled или
	 * response/certinfo/userinfo/value/row/@locked Если их значение равно 1, то
	 * этот аттестат отозван и его статус эквивалентен аттестату псевдонима.
	 * Проверять можно любой из атрибутов.
	 * 
	 * @return Тип аттестата (смотрите опорный словарь).
	 */
	public int getTid() {
		return tid;
	}

	/**
	 * Право выдачи аттестатов. Для владельцев персональных аттестатов
	 * admlocked='false' означает наличие права выдачи начальных аттестатов, для
	 * владельцев аттестата регистратора admlocked='false' означает наличие
	 * права выдачи персональных аттестатов. admlocked='true' означает, что
	 * данный аттестатор лишен права выдачи аттестатов.
	 * 
	 * @return Право выдачи аттестатов.
	 */
	public boolean isAdmLocked() {
		return admLocked;
	}

	/**
	 * Право выдачи аттестатов. Для владельцев персональных аттестатов
	 * locked='false' означает наличие права выдачи начальных аттестатов, для
	 * владельцев аттестата регистратора locked='false' означает наличие права
	 * выдачи персональных аттестатов. locked='true' означает временную
	 * блокировку выдачи аттестатов или отсутствие такого права.
	 * 
	 * @return Право выдачи аттестатов.
	 */
	public boolean isLocked() {
		return locked;
	}

	/**
	 * Информация об отзыве аттестата. Если значение атрибута равно 'true', то
	 * этот аттестат отозван и его статус эквивалентен аттестату псевдонима.
	 * Параметр аналогичен response/certinfo/userinfo/value/row/@locked.
	 * 
	 * @return Информация об отзыве аттестата.
	 */
	public boolean isRecalled() {
		return recalled;
	}

	/**
	 * Право выдачи аттестатов. Для владельцев персональных аттестатов
	 * admlocked='false' означает наличие права выдачи начальных аттестатов, для
	 * владельцев аттестата регистратора admlocked='false' означает наличие
	 * права выдачи персональных аттестатов. admlocked='true' означает, что
	 * данный аттестатор лишен права выдачи аттестатов.
	 * 
	 * @param admlocked
	 *            Право выдачи аттестатов.
	 */
	public void setAdmlocked(String admlocked) {
		this.admLocked = !admlocked.equals("0");
	}

	/**
	 * Право выдачи аттестатов. Для владельцев персональных аттестатов
	 * admlocked='false' означает наличие права выдачи начальных аттестатов, для
	 * владельцев аттестата регистратора admlocked='false' означает наличие
	 * права выдачи персональных аттестатов. admlocked='true' означает, что
	 * данный аттестатор лишен права выдачи аттестатов.
	 * 
	 * @param admlocked
	 *            Право выдачи аттестатов.
	 */
	public void setAdmLocked(boolean admlocked) {
		this.admLocked = admlocked;
	}

	/**
	 * Внутренний номер владельца аттестата в системе центра аттестации
	 * (уникальный).
	 * 
	 * @param cid
	 *            Внутренний номер владельца аттестата в системе центра
	 *            аттестации (уникальный).
	 */
	public void setCid(int cid) {
		this.cid = cid;
	}

	/**
	 * Внутренний номер владельца аттестата в системе центра аттестации
	 * (уникальный).
	 * 
	 * @param cid
	 *            Внутренний номер владельца аттестата в системе центра
	 *            аттестации (уникальный).
	 */
	public void setCid(String cid) {
		this.cid = Integer.parseInt(cid);
	}

	/**
	 * Дата и время (московское) выдачи аттестата.
	 * 
	 * @param dateCrt
	 *            Дата и время (московское) выдачи аттестата.
	 */
	public void setDateCrt(Date dateCrt) {
		this.dateCrt = dateCrt;
	}

	/**
	 * Дата и время (московское) выдачи аттестата.
	 * 
	 * @param dateCrt
	 *            Дата и время (московское) выдачи аттестата.
	 */
	public void setDateCrt(String dateCrt) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
		try {
			this.dateCrt = sdf.parse(dateCrt);
		} catch (ParseException e) {
			this.dateCrt = null;
			e.printStackTrace();
		}
	}

	/**
	 * Разница в днях между текущей датой и датой выдачи аттестата.
	 * 
	 * @param datediff
	 *            Разница в днях между текущей датой и датой выдачи аттестата.
	 */
	public void setDatediff(String datediff) {
		this.dateDiff = Integer.parseInt(datediff);
	}

	/**
	 * Разница в днях между текущей датой и датой выдачи аттестата.
	 * 
	 * @param datediff
	 *            Разница в днях между текущей датой и датой выдачи аттестата.
	 */
	public void setDateDiff(int datediff) {
		this.dateDiff = datediff;
	}

	/**
	 * Право выдачи аттестатов. Для владельцев персональных аттестатов
	 * locked='false' означает наличие права выдачи начальных аттестатов, для
	 * владельцев аттестата регистратора locked='false' означает наличие права
	 * выдачи персональных аттестатов. locked='true' означает временную
	 * блокировку выдачи аттестатов или отсутствие такого права.
	 * 
	 * @param locked
	 *            Право выдачи аттестатов.
	 */
	public void setLocked(boolean locked) {
		this.locked = locked;
	}

	/**
	 * Право выдачи аттестатов. '0' - false, остальное - true.
	 * 
	 * @param locked
	 *            Право выдачи аттестатов.
	 */
	public void setLocked(String locked) {
		this.locked = !locked.equals("0");
	}

	/**
	 * Информация об отзыве аттестата. Если значение атрибута равно 'true', то
	 * этот аттестат отозван и его статус эквивалентен аттестату псевдонима.
	 * Параметр аналогичен response/certinfo/userinfo/value/row/@locked.
	 * 
	 * @param recalled
	 *            Информация об отзыве аттестата.
	 */
	public void setRecalled(boolean recalled) {
		this.recalled = recalled;
	}

	/**
	 * Информация об отзыве аттестата. Если значение атрибута равно 'true', то
	 * этот аттестат отозван и его статус эквивалентен аттестату псевдонима.
	 * Параметр аналогичен response/certinfo/userinfo/value/row/@locked.
	 * 
	 * @param recalled
	 *            Информация об отзыве аттестата.
	 */
	public void setRecalled(String recalled) {
		this.recalled = !recalled.equals("0");
	}

	/**
	 * Внутренний номер аттестатора в системе центра аттестации (уникальный).
	 * 
	 * @param regcid
	 *            Внутренний номер аттестатора в системе центра аттестации
	 *            (уникальный).
	 */
	public void setRegcid(String regcid) {
		this.regCid = Integer.parseInt(regcid);
	}

	/**
	 * Внутренний номер аттестатора в системе центра аттестации (уникальный).
	 * 
	 * @param regcid
	 *            Внутренний номер аттестатора в системе центра аттестации
	 *            (уникальный).
	 */
	public void setRegCid(int regcid) {
		this.regCid = regcid;
	}

	/**
	 * Название проекта, имя (nick) аттестатора, выдавшего данный аттестат.
	 * 
	 * @param regnickname
	 *            Название проекта, имя (nick) аттестатора, выдавшего данный
	 *            аттестат.
	 */
	public void setRegNickname(String regnickname) {
		this.regNickname = regnickname;
	}

	/**
	 * WMID аттестатора, выдавшего данный аттестат.
	 * 
	 * @param regwmid
	 *            WMID аттестатора, выдавшего данный аттестат.
	 */
	public void setRegwmid(String regwmid) {
		this.regWmid = new Wmid(regwmid);
	}

	/**
	 * WMID аттестатора, выдавшего данный аттестат.
	 * 
	 * @param regwmid
	 *            WMID аттестатора, выдавшего данный аттестат.
	 */
	public void setRegWmid(Wmid regwmid) {
		this.regWmid = regwmid;
	}

	/**
	 * Тип аттестата (смотрите опорный словарь). При анализе данного параметра
	 * необходимо обратить внимание на атрибуты
	 * response/certinfo/attestat/row/@recalled или
	 * response/certinfo/userinfo/value/row/@locked Если их значение равно 1, то
	 * этот аттестат отозван и его статус эквивалентен аттестату псевдонима.
	 * Проверять можно любой из атрибутов.
	 * 
	 * @param tid
	 *            Тип аттестата (смотрите опорный словарь).
	 */
	public void setTid(int tid) {
		this.tid = tid;
	}

	/**
	 * Тип аттестата (смотрите опорный словарь). При анализе данного параметра
	 * необходимо обратить внимание на атрибуты
	 * response/certinfo/attestat/row/@recalled или
	 * response/certinfo/userinfo/value/row/@locked Если их значение равно 1, то
	 * этот аттестат отозван и его статус эквивалентен аттестату псевдонима.
	 * Проверять можно любой из атрибутов.
	 * 
	 * @param tid
	 *            Тип аттестата (смотрите опорный словарь).
	 */
	public void setTid(String tid) {
		this.tid = Integer.parseInt(tid);
	}
}
