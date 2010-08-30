/**
 * 
 */
package lv.flancer.wmt.xml;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;

/**
 * <p>
 * Отправляет один запрос (GET или POST) к HTTP-серверу и получает ответ от
 * него. Производит кодирование/декодирование запроса/ответа в соответствующую
 * кодировку (см. {@link #setRequestCharset(Charset)},
 * {@link #setResponseCharset(Charset)}).
 * </p>
 * <p>
 * POST-запрос типа "application/x-www-form-urlencoded", за формирование тела
 * запроса в соответствующем виде отвечает вызывающая сторона (
 * {@link #doPost(String, String)}).
 * </p>
 * <p>
 * Пример "короткого" вызова (настройки по-умолчанию: кодировка запросов -
 * UTF-8; SSL не используется):
 * </p>
 * 
 * <pre>
 * HttpRequester req = new HttpRequester(&quot;secured.host.tld&quot;, 80);
 * String response = req.doGet(&quot;/path/to/get/agent?id=5&quot;);
 * System.out.println(&quot;Response:\n&quot; + response);
 * </pre>
 * 
 * <p>
 * Пример "длинного" вызова (задаются все настройки)
 * </p>
 * 
 * <pre>
 * HttpRequester req = new HttpRequester(&quot;secured.host.tld&quot;, 443);
 * req.setRequestCharset(&quot;windows-1251&quot;);
 * req.setResponseCharset(&quot;windows-1251&quot;);
 * req.setSecuredResuest(true);
 * req.loadTrustStore(&quot;/path/to/truststore.jks&quot;, &quot;TrustStorePassword&quot;);
 * String requestBody = &quot;param1=val1&amp;param2=val2&quot;;
 * String response = req.doPost(&quot;/path/to/post/agent&quot;, requestBody);
 * System.out.println(&quot;Request:\n&quot; + req.getRequest());
 * System.out.println(&quot;Response:\n&quot; + response);
 * </pre>
 * 
 * @author Alex Gusev <flancer64@gmail.com>
 * @version 1.0
 * 
 */
public class HttpRequester {
	/**
	 * Внутренний типизатор типа HTTP-запроса.
	 * 
	 * @author Alex Gusev <flancer64@gmail.com>
	 * @version 1.0
	 * 
	 */
	private enum HttpRequestType {
		GET, POST
	}

	private final static String DEFAULT_CAHRSET = "utf-8";
	private final static String DEFAULT_SECURE_RANDOM_ALGORITHM = "SHA1PRNG";
	private final static String DEFAULT_SSL_PROTOCOL = "SSLv3";

	/**
	 * Адрес сервера для установления соединения.
	 */
	private InetAddress host;
	/**
	 * Порт сервера для установления соединения.
	 */
	private int port;
	/**
	 * Запрос, отправленный на сервер, сохраняется для диагностических целей.
	 */
	private String request;
	/**
	 * Кодировка, в которую перекодируются из UTF-8 символы запроса при отправке
	 * на сервер.
	 */
	private Charset requestCharset;
	/**
	 * Кодировка, из которой перекодируются в UTF-8 символы ответа при получении
	 * их с сервера.
	 */
	private Charset responseCharset;
	/**
	 * Менеджеры ключей для установления защищенного соединения (SSL).
	 */
	private KeyManager[] keyManagers = null;
	/**
	 * Менеджеры доверенных сертификатов для установления защищенного соединения
	 * (SSL).
	 */
	private TrustManager[] trustManagers = null;
	/**
	 * Флаг, указывающий на необходимость использования шифрования (SSL) при
	 * выполнении запроса.
	 */
	private boolean securedResuest;

	/**
	 * Создает экземпляр класса с кодировкой UTF-8 для запроса/ответа.
	 * 
	 * @param host
	 *            Адрес сервера для установления соединения.
	 * @param port
	 *            Порт сервера для установления соединения.
	 * @throws UnknownHostException
	 */
	public HttpRequester(String host, int port) throws UnknownHostException {
		this.host = InetAddress.getByName(host);
		this.port = port;
		this.requestCharset = Charset.forName(DEFAULT_CAHRSET);
		this.responseCharset = Charset.forName(DEFAULT_CAHRSET);
		this.securedResuest = false;
	}

	/**
	 * Формирует запрос в виде строки в зависимости от его типа (GET или POST).
	 * 
	 * @param requestAddress
	 *            адрес на сервере (URL).
	 * @param requestBody
	 *            тело запроса для POST-запроса.
	 * @param requestType
	 *            тип запроса (GET или POST0.
	 * @return
	 */
	private String composeCommonRequest(String requestAddress,
			String requestBody, HttpRequestType requestType) {
		String result = null;
		// формируем строку запроса
		this.request = "";
		this.request += requestType + " " + requestAddress + " HTTP/1.0\r\n";
		this.request += "Proxy-Connection: close\r\n";
		this.request += "User-Agent: " + this.getClass().getCanonicalName()
				+ "\r\n";
		this.request += "From: info@flancer.lv\r\n";
		this.request += "Host: " + this.host.getHostName() + "\r\n";
		// поля для POST-запроса
		if (requestType == HttpRequestType.POST) {
			this.request += "Content-Type: application/x-www-form-urlencoded\r\n";
			this.request += "Content-Length: " + requestBody.length() + "\r\n";
			// конец HTTP-заголовков
			this.request += "\r\n";
			this.request += requestBody;
		} else {
			// GET
			this.request += "\r\n";
		}
		result = this.request;
		return result;
	}

	/**
	 * Формирует GET-запрос в соответствующей кодировке и возвращает его в виде
	 * буфера.
	 * 
	 * @param requestAddress
	 * @param charset
	 * @return
	 */
	private ByteBuffer composeGetRequestBuffer(String requestAddress,
			Charset requestCharset) {
		ByteBuffer result = null;
		// формируем строку запроса
		String request = this.composeCommonRequest(requestAddress, null,
				HttpRequestType.GET);
		result = requestCharset.encode(request);
		return result;
	}

	/**
	 * Формирует POST-запрос в соответствующей кодировке и возвращает его в виде
	 * буфера.
	 * 
	 * @param requestAddress
	 * @param requestBody
	 * @param requestCharset
	 * @return
	 */
	private ByteBuffer composePostRequestBuffer(String requestAddress,
			String requestBody, Charset requestCharset) {
		ByteBuffer result = null;
		// формируем строку запроса
		String request = this.composeCommonRequest(requestAddress, requestBody,
				HttpRequestType.POST);
		result = requestCharset.encode(request);
		return result;
	}

	/**
	 * Посылает серверу GET-запрос по адресу requestAddress.
	 * 
	 * @param requestAddress
	 * @return http-ответ, полученный от сервера
	 * @throws IOException
	 * @throws NoSuchAlgorithmException
	 * @throws KeyManagementException
	 */
	public String doGet(String requestAddress) throws IOException,
			KeyManagementException, NoSuchAlgorithmException {
		return this.doRequest(requestAddress, null, HttpRequestType.GET);
	}

	/**
	 * Посылает серверу POST-запрос по адресу requestAddress, переменные запроса
	 * содержатся в requestBody, за соответствующий вид которого отвечает
	 * вызывающая сторона
	 * 
	 * @param requestAddress
	 * @return http-ответ, полученный от сервера
	 * @throws IOException
	 * @throws NoSuchAlgorithmException
	 * @throws KeyManagementException
	 */
	public String doPost(String requestAddress, String requestBody)
			throws IOException, KeyManagementException,
			NoSuchAlgorithmException {
		return this
				.doRequest(requestAddress, requestBody, HttpRequestType.POST);
	}

	/**
	 * Выполняет запрос в зависимости от его типа (GET или POST).
	 * 
	 * @param requestAddress
	 * @param requestBody
	 * @param requestType
	 * @return
	 * @throws KeyManagementException
	 * @throws NoSuchAlgorithmException
	 * @throws IOException
	 */
	private String doRequest(String requestAddress, String requestBody,
			HttpRequestType requestType) throws KeyManagementException,
			NoSuchAlgorithmException, IOException {
		String result = null;
		// устанавливаем соединение
		Socket socket = this.initSocket();
		if (socket.isConnected()) {
			// готовим запрос
			ByteBuffer buff;
			if (requestType == HttpRequestType.POST) {
				buff = this.composePostRequestBuffer(requestAddress,
						requestBody, this.requestCharset);
			} else {
				buff = this.composeGetRequestBuffer(requestAddress,
						this.requestCharset);
			}
			// создаем канал для записи запроса
			WritableByteChannel outChannel = Channels.newChannel(socket
					.getOutputStream());
			outChannel.write(buff);
			// создаем канал для чтения ответа
			ReadableByteChannel inChannel = Channels.newChannel(socket
					.getInputStream());
			String response = this.parseResponse(inChannel,
					this.responseCharset);
			socket.close();
			result = response;
		}
		return result;
	}

	/**
	 * Адрес сервера для установления соединения.
	 * 
	 * @return Адрес сервера для установления соединения.
	 */
	public InetAddress getHost() {
		return host;
	}

	/**
	 * Порт сервера для установления соединения.
	 * 
	 * @return Порт сервера для установления соединения.
	 */
	public int getPort() {
		return port;
	}

	/**
	 * Запрос, отправленный на сервер, сохраняется для диагностических целей.
	 * 
	 * @return the request Запрос, отправленный на сервер, сохраняется для
	 *         диагностических целей.
	 */
	public String getRequest() {
		return request;
	}

	/**
	 * Кодировка, в которую перекодируются из UTF-8 символы запроса при отправке
	 * на сервер.
	 * 
	 * @return Кодировка, в которую перекодируются из UTF-8 символы запроса при
	 *         отправке на сервер.
	 */
	public Charset getRequestCharset() {
		return requestCharset;
	}

	/**
	 * Кодировка, из которой перекодируются в UTF-8 символы ответа при получении
	 * их с сервера.
	 * 
	 * @return Кодировка, из которой перекодируются в UTF-8 символы ответа при
	 *         получении их с сервера.
	 */
	public Charset getResponseCharset() {
		return responseCharset;
	}

	/**
	 * Инициализирует и возвращает сокет, соединенный с удаленным сервером. В
	 * зависимости от настроек, сокет может быть защищенный (SSLSocket) или
	 * простой.
	 * 
	 * @return Инициализирует и возвращает сокет, соединенный с удаленным
	 *         сервером.
	 * @throws NoSuchAlgorithmException
	 * @throws KeyManagementException
	 * @throws IOException
	 */
	private Socket initSocket() throws NoSuchAlgorithmException,
			KeyManagementException, IOException {
		Socket result = null;
		if (this.securedResuest) {
			// инициируем контекст для работы SSL
			SSLContext sslContext = SSLContext
					.getInstance(DEFAULT_SSL_PROTOCOL);
			SecureRandom random = SecureRandom
					.getInstance(DEFAULT_SECURE_RANDOM_ALGORITHM);
			sslContext.init(keyManagers, trustManagers, random);
			// создаем SSL-сокет
			SSLSocketFactory fac = sslContext.getSocketFactory();
			result = fac.createSocket(this.host, this.port);
		} else {
			// не защищенное соединение
			result = new Socket(this.host, this.port);
		}
		return result;
	}

	/**
	 * Загрузка стороннего хранилища ключей и/или доверенных сертификатов (не
	 * системного). Собственное хранилище ключей используется при авторизации по
	 * персональным сертификатам. Собственное хранилище доверенных сертификатов
	 * используется в случае работы с серверами, имеющими собственную систему
	 * Certificate Authority.
	 * 
	 * @param pathToStore
	 * @param password
	 * @param trustStoreOnly
	 *            загружаются только доверенные сертификаты
	 * @throws KeyStoreException
	 * @throws IOException
	 * @throws FileNotFoundException
	 * @throws CertificateException
	 * @throws NoSuchAlgorithmException
	 * @throws UnrecoverableKeyException
	 */
	public void loadKeyStore(String pathToStore, String password,
			boolean trustStoreOnly) throws KeyStoreException,
			NoSuchAlgorithmException, CertificateException,
			FileNotFoundException, IOException, UnrecoverableKeyException {
		if ((pathToStore != null) && (password != null)) {
			// подгружаем файл ключей
			KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
			keyStore.load(new FileInputStream(pathToStore.trim()),
					password.toCharArray());
			// инициируем менеджеры ключей, если надо
			if (!trustStoreOnly) {
				KeyManagerFactory kmf = KeyManagerFactory
						.getInstance(KeyManagerFactory.getDefaultAlgorithm());
				kmf.init(keyStore, password.toCharArray());
				this.keyManagers = kmf.getKeyManagers();
			}
			// инициируем менеджеры сертификатов
			TrustManagerFactory tmf = TrustManagerFactory
					.getInstance(TrustManagerFactory.getDefaultAlgorithm());
			tmf.init(keyStore);
			this.trustManagers = tmf.getTrustManagers();
		}
	}

	/**
	 * Флаг, указывающий на необходимость использования шифрования (SSL) при
	 * выполнении запроса.
	 * 
	 * @return Флаг, указывающий на необходимость использования шифрования (SSL)
	 *         при выполнении запроса.
	 */
	public boolean isSecuredResuest() {
		return securedResuest;
	}

	/**
	 * Считывает из канала ответ от сервера и преобразует его в строку в
	 * соответствующей кодировке.
	 * 
	 * @param schannel
	 * @param charset
	 * @return
	 * @throws IOException
	 */
	private String parseResponse(ReadableByteChannel schannel, Charset charset)
			throws IOException {
		String result = null;
		// буфер для получения бинарных данных из канала
		ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
		CharBuffer charBuffer = CharBuffer.allocate(1024);
		// преобразуем кодировку
		CharsetDecoder decoder = charset.newDecoder();
		String response = "";
		while ((schannel.read(buffer)) != -1) {
			buffer.flip();
			decoder.decode(buffer, charBuffer, false);
			charBuffer.flip();
			response += charBuffer.toString();
			buffer.clear();
			charBuffer.clear();
		}
		result = response;
		return result;
	}

	/**
	 * Адрес сервера для установления соединения.
	 * 
	 * @param host
	 *            Адрес сервера для установления соединения.
	 */
	public void setHost(InetAddress host) {
		this.host = host;
	}

	/**
	 * Порт сервера для установления соединения.
	 * 
	 * @param port
	 *            Порт сервера для установления соединения.
	 */
	public void setPort(int port) {
		this.port = port;
	}

	/**
	 * Кодировка, в которую перекодируются из UTF-8 символы запроса при отправке
	 * на сервер.
	 * 
	 * @param charsetName
	 *            Кодировка, в которую перекодируются из UTF-8 символы запроса
	 *            при отправке на сервер.
	 */
	public void setRequestCharset(Charset charsetName) {
		this.requestCharset = charsetName;
	}

	/**
	 * Кодировка, в которую перекодируются из UTF-8 символы запроса при отправке
	 * на сервер.
	 * 
	 * @param charsetName
	 *            Кодировка, в которую перекодируются из UTF-8 символы запроса
	 *            при отправке на сервер.
	 */
	public void setRequestCharset(String charsetName) {
		this.requestCharset = Charset.forName(charsetName);
	}

	/**
	 * Кодировка, из которой перекодируются в UTF-8 символы ответа при получении
	 * их с сервера.
	 * 
	 * @param charsetName
	 *            Кодировка, из которой перекодируются в UTF-8 символы ответа
	 *            при получении их с сервера.
	 */
	public void setResponseCharset(Charset charsetName) {
		this.responseCharset = charsetName;
	}

	/**
	 * Кодировка, из которой перекодируются в UTF-8 символы ответа при получении
	 * их с сервера.
	 * 
	 * @param charsetName
	 *            Кодировка, из которой перекодируются в UTF-8 символы ответа
	 *            при получении их с сервера.
	 */
	public void setResponseCharset(String charsetName) {
		this.responseCharset = Charset.forName(charsetName);
	}

	/**
	 * Флаг, указывающий на необходимость использования шифрования (SSL) при
	 * выполнении запроса.
	 * 
	 * @param securedResuest
	 *            Флаг, указывающий на необходимость использования шифрования
	 *            (SSL) при выполнении запроса.
	 */
	public void setSecuredResuest(boolean securedResuest) {
		this.securedResuest = securedResuest;
	}

}
