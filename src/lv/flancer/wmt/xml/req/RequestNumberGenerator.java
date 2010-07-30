/**
 * 
 */
package lv.flancer.wmt.xml.req;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Генератор счетчика запросов к WMT XML. Т.к. счетчик для каждого последующего
 * запроса должен быть больше счетчика предыдущего, то счетчик формируется на
 * основании текущей даты/времени по GMT (во избежание проблем, связанных с
 * отправкой запросов с разных компьютеров в разных часовых поясах).
 * 
 * @author Alex Gusev <flancer64@gmail.com>
 * @version 1.0
 * 
 */
public class RequestNumberGenerator {
	/**
	 * Возвращает номер запроса.
	 * 
	 * @return Возвращает номер запроса.
	 */
	public static long getRequestNumber() {
		// формирование номера запроса в виде "yyMMddHHmmssSSS"
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmssSSS");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		String timestamp = sdf.format(date);
		Long id = Long.valueOf(timestamp);
		return id;
	}
}
