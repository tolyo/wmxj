package lv.flancer.wmt.xml.wmsigner;

import java.security.MessageDigest;

import sun.security.provider.MD4;

/**
 * Утилиты, используемые в пакете.
 * 
 * @author Alex Gusev <flancer64@gmail.com>
 * @version 1.0
 */
public class Utils {

	/**
	 * Для входного массива байтов рассчитывает MD4-хэш и возвращает его в виде
	 * массива байтов.
	 * 
	 * @param input
	 * @return последовательность байтов, являющаяся MD4-хэшем для входной
	 *         последовательности байтов.
	 */
	public static byte[] calcMd4(byte[] input) {
		MessageDigest md4 = MD4.getInstance();
		md4.reset();
		md4.update(input);
		byte[] result = md4.digest();
		return result;
	}

	/**
	 * Из 4-хбайтового массива образует int и возвращает его обратно,
	 * предполагается, что байты в массиве стоят в порядке little-endian (от
	 * младшего к старшему).
	 * 
	 * @param bytes
	 * @return соответствующее целочисленное значение.
	 */
	public static int convertBytesToUnsignedInt(byte[] bytes) {
		int b1 = (0x000000FF & ((int) bytes[0]));
		int b2 = (0x000000FF & ((int) bytes[1]));
		int b3 = (0x000000FF & ((int) bytes[2]));
		int b4 = (0x000000FF & ((int) bytes[3]));
		long anUnsignedInt = (long) (b4 << 24 | b3 << 16 | b2 << 8 | b1);
		return (int) anUnsignedInt;
	}

	/**
	 * Из 2-хбайтового массива образует unsigned short и возвращает его в виде
	 * int ("родной" для java формат). Предполагается, что байты в массиве стоят
	 * в порядке little-endian (от младшего к старшему).
	 * 
	 * @param bytes
	 * @return соответствующее целочисленное значение.
	 */
	public static int convertBytesToUnsignedShort(byte[] bytes) {
		int b1 = (0x000000FF & ((int) bytes[0]));
		int b2 = (0x000000FF & ((int) bytes[1]));
		int anUnsignedShort = (char) (b2 << 8 | b1);
		return anUnsignedShort;
	}

}