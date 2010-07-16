/**
 * 
 */
package lv.flancer.wmt.xml.wmsigner;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Random;

import javax.xml.bind.DatatypeConverter;

/**
 * <p>
 * Имплементация модуля аутентификации <a
 * href="http://wiki.webmoney.ru/wiki/show/WMSigner"
 * target="_blank">WMSigner</a>.
 * <p>
 * 
 * <p>
 * Использование WmSigner'а с ключом в kwm-файле:
 * </p>
 * 
 * <pre>
 * String wmid = &quot;123456789012&quot;;
 * String kwmFileName = &quot;/path/to/kwm/file&quot;;
 * String keyPassword = &quot;KwmFileAccessPassword&quot;;
 * 
 * WmSigner signer = new WmSigner();
 * signer.setWmid(wmid);
 * signer.setKwmFileName(kwmFileName);
 * signer.setKeyPassword(keyPassword);
 * 
 * try {
 * 	String signature = signer.sign(&quot;Text to sign.&quot;);
 * } catch (CannotLoadKeysException e) {
 * } catch (IOException e) {
 * } catch (KwmCorruptedException e) {
 * }
 * </pre>
 * <p>
 * Использование WmSigner'а с base64-кодированным ключом:
 * </p>
 * 
 * <pre>
 * ...
 * String base64Key = &quot;gQA...bg=&quot;;
 * ...
 * WmSigner signer = new WmSigner();
 * 
 * signer.setWmid(wmid);
 * signer.setBase64Key(base64Key);
 * signer.setKeyPassword(keyPassword);
 * ...
 * </pre>
 * <p>
 * В случае, если указаны и путь к kwm-файлу, и строка с base64-кодированным
 * ключом, в качестве источника kwm-ключа берется строка с base64-кодированным
 * ключом.
 * </p>
 * 
 * <p>
 * При создании подписи перевод строки 'textToSign' в байты WmSigner
 * осуществляет без указания какой-либо кодировки, а т.к. сервис WM XML
 * распознает только Windows-1251, могут быть сбои, если во входной строке есть
 * символы, которые не перекодируются в Windows-1251. Ответственность за
 * перекодирование строки должна лежать на вызывающем классе.
 * </p>
 * 
 * @author Alex Gusev <alex@flancer.lv>
 * @version 1.0
 */
public class WmSigner {
	/**
	 * Длина буфера для массива байтов, служащего основанием при возведении в
	 * степень.
	 */
	private static final int BASE_BUFFER_LENGTH = 58;
	/**
	 * Длина буфера для массива случайных байтов, дополняющего основание при
	 * возведении в степень.
	 */
	private static final int RANDOM_BUFFER_LENGTH = 40;
	/**
	 * Base64-кодированный kwm-ключ.
	 */
	private String base64Key;
	/**
	 * Пароль доступа к kwm-ключу.
	 */
	private String keyPassword;
	/**
	 * Данные, распакованные из kwm-файла.
	 */
	private KwmData kwmData;
	/**
	 * Путь к kwm-файлу, содержащему ключ подписи.
	 */
	private String kwmFileName;
	/**
	 * WMID ключа, которым подписывается запрос.
	 */
	private String wmid;

	/**
	 * Непосредственная генерация подписи для массива байтов.
	 * 
	 * @param textToSign
	 * @return
	 */
	private byte[] generateSignature(byte[] textToSign) {
		byte[] result = null;
		// буфер для размещения в нем основания степени
		byte[] baseBuff = new byte[BASE_BUFFER_LENGTH];
		// создаем MD4-хэш для входного массива байтов.
		byte[] md4 = Utils.calcMd4(textToSign);
		// 40 случайных байтов
		byte[] rnd = new byte[RANDOM_BUFFER_LENGTH];
		Random random = new Random();
		random.nextBytes(rnd);
		System.arraycopy(rnd, 0, baseBuff, 18, RANDOM_BUFFER_LENGTH);
		// заполняем основание степени данными: добавляем 2 байта с длиной (она
		// всегда равна 16 + RANDOM_BUFFER_LENGTH = 56) и добавляем md4-хэш и
		// случайные байты.
		baseBuff[0] = 0x38;
		baseBuff[1] = 0x00;
		System.arraycopy(md4, 0, baseBuff, 2, 16);
		// инициализируем целочисленные переменные для вычисления возведения
		// 'base' в степень 'exponent' по модулю 'modulus'
		byte[] baseRev = new byte[baseBuff.length];
		for (int i = 0; i < baseBuff.length; i++) {
			baseRev[i] = baseBuff[baseBuff.length - 1 - i];
		}
		// т.к. последний случайный байт, ставший теперь первым может содержать
		// знаковый бит, прямо указываем, что число положительное
		BigInteger base = new BigInteger(1, baseRev);
		BigInteger exponent = this.kwmData.getExponentAsBigInt();
		BigInteger modulus = this.kwmData.getModulusAsBigInt();
		// непосредственное возведение в степень
		BigInteger signature = base.modPow(exponent, modulus);
		result = signature.toByteArray();
		return result;
	}

	/**
	 * Base64-кодированный kwm-ключ.
	 * 
	 * @return Base64-кодированный kwm-ключ.
	 */
	public String getBase64Key() {
		return base64Key;
	}

	/**
	 * Пароль доступа к kwm-ключу.
	 * 
	 * @return Пароль доступа к kwm-ключу.
	 */
	public String getKeyPassword() {
		return keyPassword;
	}

	/**
	 * Путь к kwm-файлу, содержащему ключ подписи.
	 * 
	 * @return Путь к kwm-файлу, содержащему ключ подписи.
	 */
	public String getKwmFileName() {
		return kwmFileName;
	}

	/**
	 * WMID ключа, которым подписывается запрос.
	 * 
	 * @return WMID ключа, которым подписывается запрос.
	 */
	public String getWmid() {
		return wmid;
	}

	/**
	 * Анализируем атрибуты Signer'а и подгружаем ключи {@link #keys}, если они
	 * до этого не были подгружены.
	 * 
	 * @throws CannotLoadKeysException
	 *             В процессе загрузки и распаковки ключей из внешнего источника
	 *             (строки или файла) произошла ошибка.
	 * @throws KwmCorruptedException
	 *             ключ не прошел проверку на целостность.
	 * @throws IOException
	 */
	private void loadKeys() throws CannotLoadKeysException, IOException,
			KwmCorruptedException {
		// если ключи не подгружены
		if (this.kwmData == null) {
			KwmExtractor extractor = new KwmExtractor();
			extractor.setSignerWmid(this.wmid);
			extractor.setKeyPassword(this.keyPassword);
			// анализируем проинициализированные атрибуты и выбираем метод
			// извлечения ключа - из base64-строки или из kwm-файла
			if (this.base64Key != null) {
				// Ключ в виде base64-кодированной строки имеет преимущество
				// перед kwm-файлом
				extractor.setBase64Kwm(this.base64Key);
			} else {
				// загружаем ключ из файла
				if (this.kwmFileName != null) {
					extractor.setKwmFileName(this.kwmFileName);
				} else {
					// ключи подгружать неоткуда
					throw new CannotLoadKeysException();
				}
			}
			// выполняем загрузку ключей
			this.kwmData = extractor.extract();
		}
	}

	/**
	 * Base64-кодированный kwm-ключ.
	 * 
	 * @param base64Key
	 *            Base64-кодированный kwm-ключ.
	 */
	public void setBase64Key(String base64Key) {
		this.base64Key = base64Key;
	}

	/**
	 * Пароль доступа к kwm-ключу.
	 * 
	 * @param keyPassword
	 *            Пароль доступа к kwm-ключу.
	 */
	public void setKeyPassword(String keyPassword) {
		this.keyPassword = keyPassword;
	}

	/**
	 * Путь к kwm-файлу, содержащему ключ подписи.
	 * 
	 * @param kwmFileName
	 *            Путь к kwm-файлу, содержащему ключ подписи.
	 */
	public void setKwmFileName(String kwmFileName) {
		this.kwmFileName = kwmFileName;
	}

	/**
	 * WMID ключа, которым подписывается запрос.
	 * 
	 * @param wmid
	 *            WMID ключа, которым подписывается запрос.
	 */
	public void setWmid(String wmid) {
		this.wmid = wmid;
	}

	/**
	 * Нужно вот так вот все попереставлять. Причина неизвестна. Взято из
	 * PHP-реализации DKameleon (http://dkameleon.com) за что ему отдельное
	 * спасибо.
	 * 
	 * @param swapped
	 * @return
	 */
	private String shortUnswap(String swapped) {
		String result = "";
		while (swapped.length() < 132) {
			swapped = "00" + swapped;
		}
		for (int i = 0; i < (swapped.length() / 4); i++) {
			result = swapped.substring(i * 4, i * 4 + 4) + result;
		}
		return result;
	}

	/**
	 * Подписывает входную строку textToSign и возвращает подпись в виде строки
	 * 16-ричных символов.
	 * 
	 * @param textToSign
	 *            подписываемая строка.
	 * @return
	 * @throws CannotLoadKeysException
	 *             в процессе загрузки и распаковки ключей из внешнего источника
	 *             (строки или файла) произошла ошибка.
	 * @throws KwmCorruptedException
	 *             ключ не прошел проверку на целостность.
	 * @throws IOException
	 */
	public String sign(String textToSign) throws CannotLoadKeysException,
			IOException, KwmCorruptedException {
		String result = null;
		// подгружаем ключи для подписи
		this.loadKeys();
		// генерируем подпись
		byte[] signature = this.generateSignature(textToSign.getBytes());
		String swapped = DatatypeConverter.printHexBinary(signature)
				.toLowerCase();
		// рекомбинируем байты чтобы получилось что-то другое
		result = this.shortUnswap(swapped);
		return result;
	}
}
