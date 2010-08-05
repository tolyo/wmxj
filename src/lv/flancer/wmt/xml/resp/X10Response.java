/**
 * 
 */
package lv.flancer.wmt.xml.resp;

import java.util.List;

import lv.flancer.wmt.xml.dict.Invoice;

/**
 * Интерфейс X10: Получение списка счетов на оплату.
 * 
 * @author Alex Gusev <flancer64@gmail.com>
 * @version 1.0
 * 
 */
public class X10Response extends AbstractResponse {

	/**
	 * Количество найденных счетов, удовлетворяющих запросу (атрибут
	 * w3s.response/ininvoices:cnt).
	 */
	private int count;
	/**
	 * Список найденных счетов, полученных по запросу.
	 */
	private List<Invoice> invoiceList;

	/**
	 * Количество найденных счетов, удовлетворяющих запросу (атрибут
	 * w3s.response/ininvoices:cnt).
	 * 
	 * @return Количество найденных счетов, удовлетворяющих запросу (атрибут
	 *         w3s.response/ininvoices:cnt).
	 */
	public int getCount() {
		return count;
	}

	/**
	 * Количество найденных счетов, удовлетворяющих запросу (атрибут
	 * w3s.response/ininvoices:cnt).
	 * 
	 * @param count
	 *            Количество найденных счетов, удовлетворяющих запросу (атрибут
	 *            w3s.response/ininvoices:cnt).
	 */
	public void setCount(int count) {
		this.count = count;
	}

	/**
	 * Количество найденных счетов, удовлетворяющих запросу (атрибут
	 * w3s.response/ininvoices:cnt).
	 * 
	 * @param count
	 *            Количество найденных счетов, удовлетворяющих запросу (атрибут
	 *            w3s.response/ininvoices:cnt).
	 */
	public void setCount(String count) {
		this.count = Integer.parseInt(count);
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
	 * Список найденных счетов, полученных по запросу.
	 * 
	 * @param invoiceList
	 *            Список найденных счетов, полученных по запросу.
	 */
	public void setInvoiceList(List<Invoice> invoiceList) {
		this.invoiceList = invoiceList;
	}

}
