/**
 * 
 */
package lv.flancer.wmt.xml.resp;

import java.util.List;

import lv.flancer.wmt.xml.dict.Invoice;

/**
 * Интерфейс X4: Получение истории выписанных счетов по кошельку. Проверка
 * оплаты счета.
 * 
 * @author Alex Gusev <flancer64@gmail.com>
 * @version 1.0
 * 
 */
public class X4Response extends AbstractResponse {

	/**
	 * Количество найденных счетов, удовлетворяющих запросу (атрибут
	 * w3s.response/outinvoices:cnt).
	 */
	private int count;
	/**
	 * Список найденных счетов, полученных по запросу.
	 */
	private List<Invoice> invoiceList;

	/**
	 * Количество найденных счетов, удовлетворяющих запросу (атрибут
	 * w3s.response/outinvoices:cnt).
	 * 
	 * @return Количество найденных счетов, удовлетворяющих запросу (атрибут
	 *         w3s.response/outinvoices:cnt).
	 */
	public int getCount() {
		return count;
	}

	/**
	 * Список найденных счетов, полученных по запросу.
	 * 
	 * @return Список найденных счетов, полученных по запросу.
	 */
	public List<Invoice> getInvoiceList() {
		return invoiceList;
	}

	/**
	 * Количество найденных счетов, удовлетворяющих запросу (атрибут
	 * w3s.response/outinvoices:cnt).
	 * 
	 * @param count
	 *            Количество найденных счетов, удовлетворяющих запросу (атрибут
	 *            w3s.response/outinvoices:cnt).
	 */
	public void setCount(int count) {
		this.count = count;
	}

	/**
	 * Количество найденных счетов, удовлетворяющих запросу (атрибут
	 * w3s.response/outinvoices:cnt).
	 * 
	 * @param count
	 *            Количество найденных счетов, удовлетворяющих запросу (атрибут
	 *            w3s.response/outinvoices:cnt).
	 */
	public void setCount(String count) {
		this.count = Integer.parseInt(count);
	}

	/**
	 * Список найденных счетов, полученных по запросу.
	 * 
	 * @param invoiceList
	 *            Список найденных счетов, полученных по запросу.
	 */
	public void setInvoiceList(List<Invoice> invoiceList) {
		this.invoiceList = invoiceList;
	}

}
