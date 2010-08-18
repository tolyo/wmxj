/**
 * 
 */
package lv.flancer.wmt.xml.resp;

/**
 * Интерфейс X17: Операции с арбитражными контрактами.
 * 
 * @author Alex Gusev <flancer64@gmail.com>
 * @version 1.0
 * 
 */
public class X17Response extends AbstractResponse {
	/**
	 * Номер созданного контракта.
	 */
	private long contractId;

	/**
	 * Номер созданного контракта.
	 * 
	 * @return Номер созданного контракта.
	 */
	public long getContractId() {
		return contractId;
	}

	/**
	 * Номер созданного контракта.
	 * 
	 * @param contractId
	 *            Номер созданного контракта.
	 */
	public void setContractId(long contractId) {
		this.contractId = contractId;
	}

	/**
	 * Номер созданного контракта.
	 * 
	 * @param contractId
	 *            Номер созданного контракта.
	 */
	public void setContractId(String contractId) {
		this.contractId = Long.parseLong(contractId);
	}
}
