/**
 * 
 */
package lv.flancer.wmt.xml.wmsigner;

import java.math.BigInteger;

/**
 * Структура данных, содержащаяся в kwm-файле.
 * 
 * @author Alex Gusev <alex@flancer.lv>
 * @version 1.0
 */
public class KwmData {
	/**
	 * Буфер, содержащий ключи для генерации подписи.
	 */
	private byte[] buffer;
	/**
	 * Код для проверки целостности ключей.
	 */
	private byte[] crc;
	/**
	 * Показатель степени, в которую возводится основание при вычислении подписи
	 * (возведение 'base' в степень 'exponent' по модулю 'modulus').
	 */
	private byte[] exponent;
	/**
	 * Показатель степени, в которую возводится основание при вычислении подписи
	 * (возведение 'base' в степень 'exponent' по модулю 'modulus').
	 */
	private BigInteger exponentAsBigInt;
	/**
	 * Длина буфера, содержащего ключи для генерации подписи.
	 */
	private byte[] length;
	/**
	 * Длина буфера, содержащего ключи для генерации подписи.
	 */
	private int lengthAsInt;
	/**
	 * Значение модуля, которое используется при вычислении подписи (возведение
	 * 'base' в степень 'exponent' по модулю 'modulus').
	 */
	private byte[] modulus;
	/**
	 * Значение модуля, которое используется при вычислении подписи (возведение
	 * 'base' в степень 'exponent' по модулю 'modulus').
	 */
	private BigInteger modulusAsBigInt;
	private byte[] reserved;
	private int reservedAsInt;
	/**
	 * Буфер, содержащий ключи для генерации подписи, после их "обработки"
	 * значениями wmid и паролем доступа к ключам.
	 */
	private byte[] securedBuffer;
	private byte[] signflag;
	private int signflagAsInt;

	/**
	 * Извлекает из последовательности байтов составные части kwm-файла.
	 * 
	 * @param kwmData
	 */
	public KwmData(byte[] kwmData) {
		// reserved
		this.reserved = new byte[2];
		System.arraycopy(kwmData, 0, this.reserved, 0, 2);
		this.setReservedAsInt(Utils.convertBytesToUnsignedShort(this.reserved));
		// signflag
		this.signflag = new byte[2];
		System.arraycopy(kwmData, 2, this.signflag, 0, 2);
		this.setSignflagAsInt(Utils.convertBytesToUnsignedShort(this.signflag));
		// crc
		this.crc = new byte[16];
		System.arraycopy(kwmData, 4, this.crc, 0, 16);
		// len
		this.length = new byte[4];
		System.arraycopy(kwmData, 20, this.length, 0, 4);
		this.setLengthAsInt(Utils.convertBytesToUnsignedInt(this.length));
		// buff
		int buffLength = kwmData.length;
		this.buffer = new byte[buffLength - 24]; // 2+2+16+4
		// если вдруг остаток буфера не совпадает с указанной длиной - мы сможем
		// об этом узнать (если длина больше остатка)
		System.arraycopy(kwmData, 24, this.buffer, 0, this.getLengthAsInt());
	}

	/**
	 * Извлекает из структуры данных kwm-файла составные части ключей,
	 * используемые в качестве exponent & modulus при генерации подписи (функция
	 * возведение в степень по модулю).
	 */
	public void extractKeys() {
		// вычисляем длину e-ключа (exponent)
		byte[] eKeyLengthBuff = new byte[2];
		System.arraycopy(this.securedBuffer, 4, eKeyLengthBuff, 0, 2);
		int eKeyLength = Utils.convertBytesToUnsignedShort(eKeyLengthBuff);
		// копируем e-key (exponent)
		this.exponent = new byte[eKeyLength];
		System.arraycopy(this.securedBuffer, 6, this.exponent, 0, eKeyLength);
		// переворачиваем буфер и создаем BigInteger для exponent
		byte[] tmp = new byte[eKeyLength];
		for (int i = 0; i < eKeyLength; i++) {
			tmp[i] = this.exponent[eKeyLength - 1 - i];
		}
		this.exponentAsBigInt = new BigInteger(1, tmp);
		// вычисляем длину m-ключа
		byte[] mKeyLengthBuff = new byte[2];
		System.arraycopy(this.securedBuffer, 6 + eKeyLength, mKeyLengthBuff, 0,
				2);
		int mKeyLength = Utils.convertBytesToUnsignedShort(mKeyLengthBuff);
		// копируем m-key
		this.modulus = new byte[mKeyLength];
		System.arraycopy(this.securedBuffer, 6 + eKeyLength + 2, this.modulus,
				0, mKeyLength);
		// переворачиваем буфер и создаем BigInteger для modulus
		tmp = new byte[mKeyLength];
		for (int i = 0; i < mKeyLength; i++) {
			tmp[i] = this.modulus[mKeyLength - 1 - i];
		}
		this.modulusAsBigInt = new BigInteger(1, tmp);
	}

	/**
	 * Буфер, содержащий ключи для генерации подписи.
	 * 
	 * @return Буфер, содержащий ключи для генерации подписи.
	 */
	public byte[] getBuffer() {
		return buffer;
	}

	/**
	 * Код для проверки целостности ключей.
	 * 
	 * @return Код для проверки целостности ключей.
	 */
	public byte[] getCrc() {
		return crc;
	}

	/**
	 * Показатель степени, в которую возводится основание при вычислении подписи
	 * (возведение 'base' в степень 'exponent' по модулю 'modulus').
	 * 
	 * @return Показатель степени, в которую возводится основание при вычислении
	 *         подписи (возведение 'base' в степень 'exponent' по модулю
	 *         'modulus').
	 */
	public byte[] getExponent() {
		return exponent;
	}

	/**
	 * Показатель степени, в которую возводится основание при вычислении подписи
	 * (возведение 'base' в степень 'exponent' по модулю 'modulus').
	 * 
	 * @return Показатель степени, в которую возводится основание при вычислении
	 *         подписи (возведение 'base' в степень 'exponent' по модулю
	 *         'modulus').
	 */
	public BigInteger getExponentAsBigInt() {
		return this.exponentAsBigInt;
	}

	/**
	 * Длина буфера, содержащего ключи для генерации подписи.
	 * 
	 * @return Длина буфера, содержащего ключи для генерации подписи.
	 */
	public byte[] getLength() {
		return length;
	}

	/**
	 * Длина буфера, содержащего ключи для генерации подписи.
	 * 
	 * @return Длина буфера, содержащего ключи для генерации подписи.
	 */
	public int getLengthAsInt() {
		return lengthAsInt;
	}

	/**
	 * Значение модуля, которое используется при вычислении подписи (возведение
	 * 'base' в степень 'exponent' по модулю 'modulus').
	 * 
	 * @return Значение модуля, которое используется при вычислении подписи
	 *         (возведение 'base' в степень 'exponent' по модулю 'modulus').
	 */
	public byte[] getModulus() {
		return modulus;
	}

	/**
	 * Значение модуля, которое используется при вычислении подписи (возведение
	 * 'base' в степень 'exponent' по модулю 'modulus').
	 * 
	 * @return Значение модуля, которое используется при вычислении подписи
	 *         (возведение 'base' в степень 'exponent' по модулю 'modulus').
	 */
	public BigInteger getModulusAsBigInt() {
		return this.modulusAsBigInt;
	}

	public byte[] getReserved() {
		return reserved;
	}

	public int getReservedAsInt() {
		return reservedAsInt;
	}

	/**
	 * Буфер, содержащий ключи для генерации подписи, после их "обработки"
	 * значениями wmid и паролем доступа к ключам.
	 * 
	 * @return Буфер, содержащий ключи для генерации подписи, после их
	 *         "обработки" значениями wmid и паролем доступа к ключам.
	 */
	public byte[] getSecuredBuffer() {
		return securedBuffer;
	}

	public byte[] getSignflag() {
		return signflag;
	}

	public int getSignflagAsInt() {
		return signflagAsInt;
	}

	/**
	 * Буфер, содержащий ключи для генерации подписи.
	 * 
	 * @param buffer
	 *            Буфер, содержащий ключи для генерации подписи.
	 */
	public void setBuffer(byte[] buffer) {
		this.buffer = buffer;
	}

	/**
	 * Код для проверки целостности ключей.
	 * 
	 * @param crc
	 *            Код для проверки целостности ключей.
	 */
	public void setCrc(byte[] crc) {
		this.crc = crc;
	}

	/**
	 * Показатель степени, в которую возводится основание при вычислении подписи
	 * (возведение 'base' в степень 'exponent' по модулю 'modulus').
	 * 
	 * @param exponent
	 *            Показатель степени, в которую возводится основание при
	 *            вычислении подписи (возведение 'base' в степень 'exponent' по
	 *            модулю 'modulus').
	 */
	public void setExponent(byte[] exponent) {
		this.exponent = exponent;
	}

	/**
	 * Длина буфера, содержащего ключи для генерации подписи.
	 * 
	 * @param length
	 *            Длина буфера, содержащего ключи для генерации подписи.
	 */
	public void setLength(byte[] length) {
		this.length = length;
	}

	/**
	 * Длина буфера, содержащего ключи для генерации подписи.
	 * 
	 * @param lengthAsInt
	 *            Длина буфера, содержащего ключи для генерации подписи.
	 */
	public void setLengthAsInt(int lengthAsInt) {
		this.lengthAsInt = lengthAsInt;
	}

	/**
	 * Значение модуля, которое используется при вычислении подписи (возведение
	 * 'base' в степень 'exponent' по модулю 'modulus').
	 * 
	 * @param modulus
	 *            Значение модуля, которое используется при вычислении подписи
	 *            (возведение 'base' в степень 'exponent' по модулю 'modulus').
	 */
	public void setModulus(byte[] modulus) {
		this.modulus = modulus;
	}

	public void setReserved(byte[] reserved) {
		this.reserved = reserved;
	}

	public void setReservedAsInt(int reserved) {
		this.reservedAsInt = reserved;
	}

	/**
	 * Буфер, содержащий ключи для генерации подписи, после их "обработки"
	 * значениями wmid и паролем доступа к ключам.
	 * 
	 * @param securedBuffer
	 *            Буфер, содержащий ключи для генерации подписи, после их
	 *            "обработки" значениями wmid и паролем доступа к ключам.
	 */
	public void setSecuredBuffer(byte[] securedBuffer) {
		this.securedBuffer = securedBuffer;
	}

	public void setSignflag(byte[] signflag) {
		this.signflag = signflag;
	}

	public void setSignflagAsInt(int signflag) {
		this.signflagAsInt = signflag;
	}

}
