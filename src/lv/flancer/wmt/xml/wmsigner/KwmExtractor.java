/**
 * 
 */
package lv.flancer.wmt.xml.wmsigner;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;

import javax.xml.bind.DatatypeConverter;

/**
 * Класс, отвечающий за распаковку необходимых данных из kwm-файла или из
 * base64-кодированной строки с ключом WM Classic.
 * 
 * @author Alex Gusev <flancer64@gmail.com>
 * @version 1.0
 */
public class KwmExtractor {
	/**
	 * Ключ подписи в виде base64-строки.
	 */
	private String base64Kwm;
	/**
	 * Пароль доступа к ключу.
	 */
	private String keyPassword;
	/**
	 * Данные, извлеченные из kwm-файла.
	 */
	private KwmData kwmData;
	/**
	 * Имя kwm-файла, включающее в себя путь к нему.
	 */
	private String kwmFileName;
	/**
	 * WMID ключа, используемого для подписи.
	 */
	private String signerWmid;

	/**
	 * Формирует проверочную подпись (CRC) для ключа и проверят целостность
	 * ключа.
	 * 
	 * 
	 * @param kwmData
	 * @throws KwmCorruptedException
	 *             ключ не прошел проверку на целостность.
	 */
	private void checkCrc(KwmData kwmData) throws KwmCorruptedException {
		// формируем CRC (2+2+16+4 + длина буфера)
		int crcLength = (int) (24 + kwmData.getLengthAsInt());
		byte[] crc = new byte[crcLength];
		System.arraycopy(kwmData.getReserved(), 0, crc, 0, 2);
		// новый массив байтов должен быть "забит" нулями, если это не так, то
		// нужно забить ими следующие 18 позиций.
		// добавляем длину ключа в буфере
		System.arraycopy(kwmData.getLength(), 0, crc, 20, 4);
		// добавляем буфер (после обработки wmid & key password)
		System.arraycopy(kwmData.getSecuredBuffer(), 0, crc, 24,
				kwmData.getLengthAsInt());
		// получаем MD4-хэш для CRC
		byte[] md4digest = Utils.calcMd4(crc);
		if (!Arrays.equals(md4digest, kwmData.getCrc())) {
			throw new KwmCorruptedException();
		}
	}

	/**
	 * Распаковывает части ключа и инициализирует соответствующие атрибуты.
	 * 
	 * @return распакованные ключи для подписи.
	 * @throws IOException
	 *             если в процессе считывания kwm-файла произошли ошибки.
	 * @throws KwmCorruptedException
	 *             ключ не прошел проверку на целостность.
	 */
	public KwmData extract() throws IOException, KwmCorruptedException {
		byte[] buff;
		if (this.base64Kwm != null) {
			// распаковываем ключ из base64-строки
			buff = this.readBase64();
		} else {
			// считываем содержимое kwm-файла
			buff = this.readKwmFile();
		}
		this.kwmData = new KwmData(buff);
		// SecureKeyByIDPW
		byte[] secured = this.secureKey(this.kwmData.getBuffer());
		this.kwmData.setSecuredBuffer(secured);
		// проверяем CRC
		this.checkCrc(this.kwmData);
		this.kwmData.extractKeys();
		return this.kwmData;
	}

	/**
	 * Ключ подписи в виде base64-строки.
	 * 
	 * @return Ключ подписи в виде base64-строки.
	 */
	public String getBase64Kwm() {
		return base64Kwm;
	}

	/**
	 * Пароль доступа к ключу.
	 * 
	 * @return Пароль доступа к ключу.
	 */
	public String getKeyPassword() {
		return keyPassword;
	}

	/**
	 * Данные, извлеченные из kwm-файла.
	 * 
	 * @return Данные, извлеченные из kwm-файла.
	 */
	public KwmData getKwmData() {
		return kwmData;
	}

	/**
	 * Имя kwm-файла, включающее в себя путь к нему.
	 * 
	 * @return Имя kwm-файла, включающее в себя путь к нему.
	 */
	public String getKwmFileName() {
		return kwmFileName;
	}

	/**
	 * WMID ключа, используемого для подписи.
	 * 
	 * @return WMID ключа, используемого для подписи.
	 */
	public String getSignerWmid() {
		return signerWmid;
	}

	/**
	 * Декодирует base64-строку и возвращает байт-массив со структурой
	 * kwm-файла.
	 * 
	 * @return
	 */
	private byte[] readBase64() {
		byte[] result = DatatypeConverter.parseBase64Binary(this.base64Kwm);
		return result;
	}

	/**
	 * Считывает содержимое kwm-файла в виде байт-массива.
	 * 
	 * @return
	 * @throws IOException
	 */
	private byte[] readKwmFile() throws IOException {
		byte[] result = null;
		BufferedInputStream in = null;
		try {
			in = new BufferedInputStream(new FileInputStream(
					this.getKwmFileName()));
			// считываем содержимое файла в буфер, размер kwm-файла не должен
			// превышать 164 байтов (предположительно).
			byte[] buff = new byte[256];
			int length = in.read(buff);
			// переносим данные в результирующий буфер
			result = new byte[length];
			System.arraycopy(buff, 0, result, 0, length);
		} finally {
			if (in != null) {
				in.close();
			}
		}
		return result;
	}

	/**
	 * "Шифруем" kwm-ключ при помощи wmid и пароля доступа к ключу.
	 * 
	 * @param keyBytes
	 * @return
	 */
	private byte[] secureKey(byte[] keyBytes) {
		// получаем MD4-хэш "wmid+password"
		String text2Hash = this.getSignerWmid() + this.getKeyPassword();
		byte[] md4digest = Utils.calcMd4(text2Hash.getBytes());
		// "Шифруем" входную цепочку байтов через XOR на md4-хэш
		// строки "wmid+password".
		byte[] result = new byte[keyBytes.length];
		// сдвиг на 6 байтов вперед по входной цепочке
		int shift = 6;
		System.arraycopy(keyBytes, 0, result, 0, shift);
		int x = 0;
		for (int i = shift; i < keyBytes.length; i++) {
			byte b1 = keyBytes[i];
			byte b2 = md4digest[x];
			result[i] = (byte) (b1 ^ b2);
			x++;
			if (x >= md4digest.length) {
				x = 0;
			}
		}
		return result;
	}

	/**
	 * Ключ подписи в виде base64-строки.
	 * 
	 * @param base64Kwm
	 *            Ключ подписи в виде base64-строки.
	 */
	public void setBase64Kwm(String base64Kwm) {
		this.base64Kwm = base64Kwm;
	}

	/**
	 * Пароль доступа к ключу.
	 * 
	 * @param keyPassword
	 *            Пароль доступа к ключу.
	 */
	public void setKeyPassword(String keyPassword) {
		this.keyPassword = keyPassword;
	}

	/**
	 * Данные, извлеченные из kwm-файла.
	 * 
	 * @param kwmData
	 *            Данные, извлеченные из kwm-файла.
	 */
	public void setKwmData(KwmData kwmData) {
		this.kwmData = kwmData;
	}

	/**
	 * Имя kwm-файла, включающее в себя путь к нему.
	 * 
	 * @param kwmFileName
	 *            Имя kwm-файла, включающее в себя путь к нему.
	 */
	public void setKwmFileName(String kwmFileName) {
		this.kwmFileName = kwmFileName;
	}

	/**
	 * WMID ключа, используемого для подписи.
	 * 
	 * @param wmid
	 *            WMID ключа, используемого для подписи.
	 */
	public void setSignerWmid(String wmid) {
		this.signerWmid = wmid;
	}
}
