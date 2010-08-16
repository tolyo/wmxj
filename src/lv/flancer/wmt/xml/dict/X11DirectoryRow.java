/**
 * 
 */
package lv.flancer.wmt.xml.dict;

/**
 * Отдельная запись в опорном словаре в ответе X11.
 * 
 * @author Alex Gusev <flancer64@gmail.com>
 * @version 1.0
 * 
 */
public class X11DirectoryRow {
	/**
	 * Идентификатор записи.
	 */
	private int id;
	/**
	 * Тип записи: 'ctype', 'jstatus', 'tid'.
	 */
	private String type;
	/**
	 * Символьное значение записи.
	 */
	private String value;

	/**
	 * Идентификатор записи.
	 * 
	 * @return Идентификатор записи.
	 */
	public int getId() {
		return id;
	}

	/**
	 * Тип записи: 'ctype', 'jstatus', 'tid'.
	 * 
	 * @return Тип записи: 'ctype', 'jstatus', 'tid'.
	 */
	public String getType() {
		return type;
	}

	/**
	 * Символьное значение записи.
	 * 
	 * @return Символьное значение записи.
	 */
	public String getValue() {
		return value;
	}

	/**
	 * Идентификатор записи.
	 * 
	 * @param id
	 *            Идентификатор записи.
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Идентификатор записи.
	 * 
	 * @param id
	 *            Идентификатор записи.
	 */
	public void setId(String id) {
		this.id = Integer.parseInt(id);
	}

	/**
	 * Тип записи: 'ctype', 'jstatus', 'tid'.
	 * 
	 * @param type
	 *            Тип записи: 'ctype', 'jstatus', 'tid'.
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * Символьное значение записи.
	 * 
	 * @param value
	 *            Символьное значение записи.
	 */
	public void setValue(String value) {
		this.value = value;
	}
}
