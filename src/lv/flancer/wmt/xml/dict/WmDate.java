package lv.flancer.wmt.xml.dict;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Представление даты-времени в WMT XML.
 * 
 * @author Alex Gusev <flancer64@gmail.com>
 * @version 1.0
 * 
 */
public class WmDate {
	/**
	 * Значение даты/времени.
	 */
	private Date date;

	public WmDate(Date date) {
		this.date = date;
	}

	/**
	 * дата в формате WMT XML: "yyyyMMdd HH:mm:ss".
	 * 
	 * @param date
	 *            дата в формате WMT XML: "yyyyMMdd HH:mm:ss".
	 */
	public WmDate(String date) {
		SimpleDateFormat format = this.initDateFormat();
		try {
			this.date = format.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Значение даты/времени.
	 * 
	 * @return Значение даты/времени.
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * Возвращает дату в формате WMT XML: "yyyyMMdd HH:mm:ss".
	 * 
	 * @return Возвращает дату в формате WMT XML: "yyyyMMdd HH:mm:ss".
	 */
	public String getWmtDate() {
		SimpleDateFormat format = this.initDateFormat();
		return format.format(date);
	}

	/**
	 * WMT-формат даты/времени.
	 * 
	 * @return WMT-формат даты/времени.
	 */
	private SimpleDateFormat initDateFormat() {
		return new SimpleDateFormat("yyyyMMdd HH:mm:ss");
	}

	/**
	 * Значение даты/времени.
	 * 
	 * @param date
	 *            Значение даты/времени.
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return date.toString();
	}
}
