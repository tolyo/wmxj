package lv.flancer.wmt.xml;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.Date;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import lv.flancer.wmt.xml.dict.PurseNumber;
import lv.flancer.wmt.xml.dict.WmDate;
import lv.flancer.wmt.xml.dict.Wmid;
import lv.flancer.wmt.xml.dict.X18AuthType;
import lv.flancer.wmt.xml.dict.X19Operation;
import lv.flancer.wmt.xml.dict.X19UserInfo;
import lv.flancer.wmt.xml.dict.X20ClientNumberType;
import lv.flancer.wmt.xml.dict.X20SmsType;
import lv.flancer.wmt.xml.req.RequestNumberGenerator;
import lv.flancer.wmt.xml.req.X10Request;
import lv.flancer.wmt.xml.req.X11Request;
import lv.flancer.wmt.xml.req.X13Request;
import lv.flancer.wmt.xml.req.X14Request;
import lv.flancer.wmt.xml.req.X15RequestList;
import lv.flancer.wmt.xml.req.X15RequestSave;
import lv.flancer.wmt.xml.req.X16Request;
import lv.flancer.wmt.xml.req.X17Request;
import lv.flancer.wmt.xml.req.X18Request;
import lv.flancer.wmt.xml.req.X19Request;
import lv.flancer.wmt.xml.req.X1Request;
import lv.flancer.wmt.xml.req.X20InitiationRequest;
import lv.flancer.wmt.xml.req.X20ConfirmationRequest;
import lv.flancer.wmt.xml.req.X2Request;
import lv.flancer.wmt.xml.req.X3Request;
import lv.flancer.wmt.xml.req.X4Request;
import lv.flancer.wmt.xml.req.X5Request;
import lv.flancer.wmt.xml.req.X6Request;
import lv.flancer.wmt.xml.req.X7Request;
import lv.flancer.wmt.xml.req.X8Request;
import lv.flancer.wmt.xml.req.X9Request;
import lv.flancer.wmt.xml.req.XmlRequest;
import lv.flancer.wmt.xml.resp.X10Response;
import lv.flancer.wmt.xml.resp.X11Response;
import lv.flancer.wmt.xml.resp.X13Response;
import lv.flancer.wmt.xml.resp.X14Response;
import lv.flancer.wmt.xml.resp.X15ResponseList;
import lv.flancer.wmt.xml.resp.X15ResponseSave;
import lv.flancer.wmt.xml.resp.X16Response;
import lv.flancer.wmt.xml.resp.X17Response;
import lv.flancer.wmt.xml.resp.X18Response;
import lv.flancer.wmt.xml.resp.X19Response;
import lv.flancer.wmt.xml.resp.X1Response;
import lv.flancer.wmt.xml.resp.X20InitiationResponse;
import lv.flancer.wmt.xml.resp.X20ConfirmationResponse;
import lv.flancer.wmt.xml.resp.X2Response;
import lv.flancer.wmt.xml.resp.X3Response;
import lv.flancer.wmt.xml.resp.X4Response;
import lv.flancer.wmt.xml.resp.X5Response;
import lv.flancer.wmt.xml.resp.X6Response;
import lv.flancer.wmt.xml.resp.X7Response;
import lv.flancer.wmt.xml.resp.X8Response;
import lv.flancer.wmt.xml.resp.X9Response;
import lv.flancer.wmt.xml.resp.sax.X10ResponseHandler;
import lv.flancer.wmt.xml.resp.sax.X11ResponseHandler;
import lv.flancer.wmt.xml.resp.sax.X13ResponseHandler;
import lv.flancer.wmt.xml.resp.sax.X14ResponseHandler;
import lv.flancer.wmt.xml.resp.sax.X15ResponseListHandler;
import lv.flancer.wmt.xml.resp.sax.X15ResponseSaveHandler;
import lv.flancer.wmt.xml.resp.sax.X16ResponseHandler;
import lv.flancer.wmt.xml.resp.sax.X17ResponseHandler;
import lv.flancer.wmt.xml.resp.sax.X18ResponseHandler;
import lv.flancer.wmt.xml.resp.sax.X19ResponseHandler;
import lv.flancer.wmt.xml.resp.sax.X1ResponseHandler;
import lv.flancer.wmt.xml.resp.sax.X20InitiationResponseHandler;
import lv.flancer.wmt.xml.resp.sax.X20ConfirmationResponseHandler;
import lv.flancer.wmt.xml.resp.sax.X2ResponseHandler;
import lv.flancer.wmt.xml.resp.sax.X3ResponseHandler;
import lv.flancer.wmt.xml.resp.sax.X4ResponseHandler;
import lv.flancer.wmt.xml.resp.sax.X5ResponseHandler;
import lv.flancer.wmt.xml.resp.sax.X6ResponseHandler;
import lv.flancer.wmt.xml.resp.sax.X7ResponseHandler;
import lv.flancer.wmt.xml.resp.sax.X8ResponseHandler;
import lv.flancer.wmt.xml.resp.sax.X9ResponseHandler;
import lv.flancer.wmt.xml.wmsigner.CannotLoadKeysException;
import lv.flancer.wmt.xml.wmsigner.KwmCorruptedException;
import lv.flancer.wmt.xml.wmsigner.WmSigner;

/**
 * <p>
 * Сервис, предоставляющий методы для доступа к WMT XML. Методы разделяются на
 * следующие типы:
 * </p>
 * <ul>
 * <li><b>"библиотечный"</b>: в качестве параметра запроса выступает экземпляр
 * соответствующего запроса из данной библиотеки - <i>service.x3(X3Request
 * req)</i>;</li>
 * <li><b>"простой"</b>: в качестве параметров запроса выступают данные
 * примитивных java-типов - <i>service.x3(String purse, Date dateStart, Date
 * dateFinish, long wmTranId, long tranId, long wmInvId, long orderId)</i>;</li>
 * <li><b>"минимальный"</b>: в качестве минимально-необходимых параметров
 * запроса выступают данные примитивных java-типов (если есть) -
 * <i>service.x3(String purse, Date dateStart, Date dateFinish)</i>;</li>
 * </ul>
 * <p>
 * Некоторые параметры запроса, например - w3s.request/reqn, доступны для
 * установки только в "библиотечном" типе вызова.
 * </p>
 * <p>
 * Пример вызова, авторизация по схеме Classiс:
 * </p>
 * 
 * <pre>
 * try {
 *   WmService service = new WmService();
 *   service.initWmSignerKwm(&quot;123456789012&quot;, &quot;/path/to/kwm/file&quot;, &quot;KeyPassword&quot;);
 *   X6Response resp = service.x6(&quot;123456789012&quot;, &quot;Message Subject&quot;, &quot;Message Body...&quot;);
 * catch(Exception e) {
 * }
 * </pre>
 * <p>
 * Пример вызова, авторизация по схеме Light:
 * </p>
 * 
 * <pre>
 * try {
 *   WmService service = new WmService();
 *   service.initWmLightKeyStore("/path/to/javaKeyStore/468526977914.jks", "jksPassword");
 *   service.setAllowUnsafeRenegotiation(true);
 *   X6Response resp = service.x6(&quot;123456789012&quot;, &quot;Message Subject&quot;, &quot;Message Body...&quot;);
 * catch(Exception e) {
 * }
 * </pre>
 * 
 * @author Alex Gusev <flancer64@gmail.com>
 * @version 1.0
 * 
 */
public class WmService {
	/**
	 * Кодировка, используемая для запросов и ответов по HTTP.
	 */
	private final static String HTTP_CAHRSET = "windows-1251";

	private final static String WMT_HOST_ARBITRAGE = "arbitrage.webmoney.ru";
	private final static String WMT_HOST_CLASSIC = "w3s.webmoney.ru";
	private final static String WMT_HOST_LIGHT = "w3s.wmtransfer.com";
	private final static String WMT_HOST_MERCHANT = "merchant.webmoney.ru";
	private final static String WMT_HOST_PASSPORT = "passport.webmoney.ru";
	/**
	 * Содержит текстовый вариант HTTP-запроса, отправленного на сервис WMT XML.
	 */
	private String httpRequest;
	/**
	 * Содержит текстовый вариант HTTP-ответа, полученного от сервиса WMT XML.
	 */
	private String httpResponse;
	/**
	 * Количество попыток повторения запросов к XML-сервисам, в случае
	 * возникновения проблем в сети (IOException). По умолчанию - 1.
	 */
	private int httpRetries = 1;
	/**
	 * Время (мсек.) между попытками соединения с сервером WM XML, если по
	 * каким-то причинам соединение не установилось с первого раза. По умолчанию
	 * - 5000 мсек.;
	 */
	private int httpRetriesSleepTime = 5000;
	/**
	 * Пароль доступа к хранилищу ключей, содержащему персональный сертификат
	 * для аутентификации по схеме Light.
	 */
	private String lightKeyStorePassword;
	/**
	 * Путь к хранилищу ключей, содержащему персональный сертификат для
	 * аутентификации по схеме Light.
	 */
	private String lightKeyStorePath;
	/**
	 * Парсер для разбора XML-ответа от WMT XML.
	 */
	private SAXParser saxParser;
	/**
	 * WmSigner для генерации подписи при авторизации по схеме Classic.
	 */
	private WmSigner signer;
	/**
	 * Содержит XML-запрос, отправленный на сервис WMT XML.
	 */
	private String xmlRequest;

	/**
	 * Содержит XML-ответ, полученный от сервиса WMT XML.
	 */
	private String xmlResponse;

	public WmService() throws Exception {
		SAXParserFactory spf = SAXParserFactory.newInstance();
		this.saxParser = spf.newSAXParser();
	}

	/**
	 * Содержит текстовый вариант HTTP-запроса, отправленного на сервис WMT XML.
	 * 
	 * @return Содержит текстовый вариант HTTP-запроса, отправленного на сервис
	 *         WMT XML.
	 */
	public String getHttpRequest() {
		return httpRequest;
	}

	/**
	 * Содержит текстовый вариант HTTP-ответа, полученного от сервиса WMT XML.
	 * 
	 * @return the httpResponse Содержит текстовый вариант HTTP-ответа,
	 *         полученного от сервиса WMT XML.
	 */
	public String getHttpResponse() {
		return httpResponse;
	}

	/**
	 * Количество попыток повторения запросов к XML-сервисам, в случае
	 * возникновения проблем в сети (IOException). По умолчанию - 1.
	 * 
	 * @return Количество попыток повторения запросов к XML-сервисам, в случае
	 *         возникновения проблем в сети (IOException).
	 */
	public int getHttpRetries() {
		return httpRetries;
	}

	/**
	 * Время (мсек.) между попытками соединения с сервером WM XML, если по
	 * каким-то причинам соединение не установилось с первого раза. По умолчанию
	 * - 5000 мсек.;
	 * 
	 * @return Время (мсек.) между попытками соединения с сервером WM XML, если
	 *         по каким-то причинам соединение не установилось с первого раза.
	 */
	public int getHttpRetriesSleepTime() {
		return httpRetriesSleepTime;
	}

	/**
	 * Содержит XML-запрос, отправленный на сервис WMT XML.
	 * 
	 * @return Содержит XML-запрос, отправленный на сервис WMT XML.
	 */
	public String getXmlRequest() {
		return xmlRequest;
	}

	/**
	 * Содержит XML-ответ, полученный от сервиса WMT XML.
	 * 
	 * @return Содержит XML-ответ, полученный от сервиса WMT XML.
	 */
	public String getXmlResponse() {
		return xmlResponse;
	}

	/**
	 * Добавляет подпись к запросу.
	 * 
	 * @param request
	 * @return
	 * @throws CannotLoadKeysException
	 * @throws IOException
	 * @throws KwmCorruptedException
	 */
	private XmlRequest initSignature(XmlRequest request)
			throws CannotLoadKeysException, IOException, KwmCorruptedException {
		request.setSignerWmid(this.signer.getWmid());
		String signature = this.signer.sign(request.getTextToSign());
		request.setSign(signature);
		return request;
	}

	/**
	 * Инициирует сервис для использования аутентификации по схеме WebMoney
	 * Light (с персональным сертификатом).
	 * 
	 * @param keyStorePath
	 *            путь к хранилищу, содержащему ключ для аутентификации по схеме
	 *            Light.
	 * @param keyStorePassword
	 *            пароль доступа к хранилищу, содержащему ключ для
	 *            аутентификации по схеме Light.
	 */
	public void initWmLightKeyStore(String keyStorePath, String keyStorePassword) {
		this.lightKeyStorePath = keyStorePath;
		this.lightKeyStorePassword = keyStorePassword;
	}

	/**
	 * Инициирует WmSigner с использованием ключа из Base64-кодированной строки.
	 * 
	 * @param wmid
	 *            WMID от имени которого подписывается запрос.
	 * @param base64Key
	 *            ключ подписи, кодированный в base64.
	 * @param password
	 *            пароль доступа к ключу.
	 */
	public void initWmSignerBase64(String wmid, String base64Key,
			String password) {
		this.initWmSignerBase64(new Wmid(wmid), base64Key, password);
	}

	/**
	 * Инициирует WmSigner с использованием ключа из Base64-кодированной строки.
	 * 
	 * @param wmid
	 *            WMID от имени которого подписывается запрос.
	 * @param base64Key
	 *            ключ подписи, кодированный в base64.
	 * @param password
	 *            пароль доступа к ключу.
	 */
	public void initWmSignerBase64(Wmid wmid, String base64Key, String password) {
		this.signer = new WmSigner();
		this.signer.setWmid(wmid);
		this.signer.setKeyPassword(password);
		this.signer.setBase64Key(base64Key);
	}

	/**
	 * Инициирует WmSigner с использованием пути к kwm-файлу.
	 * 
	 * @param wmid
	 *            WMID от имени которого подписывается запрос.
	 * @param kwmFileName
	 *            полный путь к kwm-файлу с ключом.
	 * @param password
	 *            пароль доступа к ключу.
	 */
	public void initWmSignerKwm(String wmid, String kwmFileName, String password) {
		this.initWmSignerKwm(new Wmid(wmid), kwmFileName, password);
	}

	/**
	 * Инициирует WmSigner с использованием пути к kwm-файлу.
	 * 
	 * @param wmid
	 *            WMID от имени которого подписывается запрос.
	 * @param kwmFileName
	 *            полный путь к kwm-файлу с ключом.
	 * @param password
	 *            пароль доступа к ключу.
	 */
	public void initWmSignerKwm(Wmid wmid, String kwmFileName, String password) {
		this.signer = new WmSigner();
		this.signer.setWmid(wmid);
		this.signer.setKeyPassword(password);
		this.signer.setKwmFileName(kwmFileName);
	}

	/**
	 * Выполняет непосредственную отправку HTTP-запроса на сервис WMT XML.
	 * 
	 * @param host
	 *            имя хоста для запроса.
	 * @param requestAddress
	 *            адрес запроса.
	 * @param requestBody
	 *            тело запроса.
	 * @return
	 * @throws KeyManagementException
	 * @throws NoSuchAlgorithmException
	 * @throws IOException
	 * @throws CertificateException
	 * @throws KeyStoreException
	 * @throws UnrecoverableKeyException
	 */
	private String sendHttpRequest(String host, String requestAddress,
			String requestBody) throws KeyManagementException,
			NoSuchAlgorithmException, IOException, UnrecoverableKeyException,
			KeyStoreException, CertificateException {
		this.httpResponse = null;
		this.httpRequest = null;
		this.xmlResponse = null;
		this.xmlRequest = requestBody;
		HttpRequester httpReq = new HttpRequester(host, 443);
		// инициируем JKS, если для аутентификации используется схема Light
		if ((this.lightKeyStorePath != null)
				&& (this.lightKeyStorePassword != null)) {
			httpReq.loadKeyStore(this.lightKeyStorePath,
					this.lightKeyStorePassword, false);
		}
		httpReq.setRequestCharset(HTTP_CAHRSET);
		httpReq.setResponseCharset(HTTP_CAHRSET);
		httpReq.setSecuredResuest(true);
		// цикл повторений запросов, на случай сбоев в сети
		int i = 0;
		do {
			i++;
			try {
				this.httpResponse = httpReq.doPost(requestAddress, requestBody);
			} catch (IOException e) {
				e.printStackTrace();
				// сбрасываем соединение и "засыпаем" на некоторое время.
				System.out.println("Connection retry #" + i + " is failed.");
				if (i < httpRetries) {
					try {
						System.out.println("Sleeping "
								+ this.httpRetriesSleepTime + " msec.");
						Thread.sleep(this.httpRetriesSleepTime);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
				}
			}
		} while ((this.httpResponse == null) && (i < httpRetries));
		this.httpRequest = httpReq.getRequest();
		// System.out.println(this.httpRequest);
		// System.out.println(this.httpResponse);
		// вырезаем текст xml из http-ответа.
		int start = this.httpResponse.indexOf("<?xml version=");
		this.xmlResponse = this.httpResponse.substring(start);
		return this.httpResponse;
	}

	/**
	 * Данная настройка нужна для аутентификации по схеме Light. Более подробно
	 * - http://blogs.sun.com/security/entry/
	 * vulnerability_in_tls_protocol_during и
	 * http://java.sun.com/javase/javaseforbusiness/docs/TLSReadme.html
	 * 
	 * @param allow
	 *            'true' - разрешить Unsafe Renegotiation, 'false' - наоборот.
	 */
	public void setAllowUnsafeRenegotiation(boolean allow) {
		java.lang.System.setProperty(
				"sun.security.ssl.allowUnsafeRenegotiation",
				String.valueOf(allow));
	}

	/**
	 * Количество попыток повторения запросов к XML-сервисам, в случае
	 * возникновения проблем в сети (IOException). По умолчанию - 1.
	 * 
	 * @param httpRetries
	 *            Количество попыток повторения запросов к XML-сервисам, в
	 *            случае возникновения проблем в сети (IOException).
	 */
	public void setHttpRetries(int httpRetries) {
		this.httpRetries = httpRetries;
	}

	/**
	 * Время (мсек.) между попытками соединения с сервером WM XML, если по
	 * каким-то причинам соединение не установилось с первого раза. По умолчанию
	 * - 5000 мсек.;
	 * 
	 * @param httpRetriesSleepTime
	 *            Время (мсек.) между попытками соединения с сервером WM XML,
	 *            если по каким-то причинам соединение не установилось с первого
	 *            раза.
	 */
	public void setHttpRetriesSleepTime(int httpRetriesSleepTime) {
		this.httpRetriesSleepTime = httpRetriesSleepTime;
	}

	/**
	 * Содержит XML-запрос, отправленный на сервис WMT XML.
	 * 
	 * @param xmlRequest
	 *            Содержит XML-запрос, отправленный на сервис WMT XML.
	 */
	public void setXmlRequest(String xmlRequest) {
		this.xmlRequest = xmlRequest;
	}

	/**
	 * <p>
	 * Простая форма для X1: Выписывание счета от одного участника (магазина,
	 * ресурса) другому участнику (покупателю).
	 * </p>
	 * 
	 * @param orderId
	 *            Номер счета в системе учета магазина.
	 * @param customerWmid
	 *            Wmid покупателя.
	 * @param storePurse
	 *            Номер кошелька, но который необходимо оплатить счет.
	 * @param amount
	 *            Сумма счета, выставленная для оплаты покупателю.
	 * @param desc
	 *            Описание товара или услуги, на который выписывается счет.
	 * @param address
	 *            Адрес доставки товара.
	 * @param period
	 *            Максимально допустимый срок протекции сделки в днях при оплате
	 *            счета.
	 * @param expiration
	 *            Максимально допустимый срок оплаты счета в днях.
	 * @return X1Response
	 * @throws Exception
	 */
	public X1Response x1(long orderId, String customerWmid, String storePurse,
			double amount, String desc, String address, int period,
			int expiration) throws Exception {
		X1Request req = new X1Request();
		req.setRequestNum(RequestNumberGenerator.getRequestNumber());
		req.setOrderId(orderId);
		req.setCustomerWmid(customerWmid);
		req.setStorePurse(storePurse);
		req.setAmount(amount);
		req.setDesc(desc);
		req.setAddress(address);
		req.setPeriod(period);
		req.setExpiration(expiration);
		return this.x1(req);
	}

	/**
	 * <p>
	 * Библиотечная форма для X1: Выписывание счета от одного участника
	 * (магазина, ресурса) другому участнику (покупателю).
	 * </p>
	 * 
	 * @param req
	 *            X1Request
	 * @return X1Response
	 * @throws Exception
	 */
	public X1Response x1(X1Request req) throws Exception {
		// авторизация по схеме Light
		String host = WMT_HOST_LIGHT;
		String requestAddress = "/asp/XMLInvoiceCert.asp";
		// подписываем запрос, если авторизация по схеме Classic.
		if (this.signer != null) {
			req = (X1Request) this.initSignature(req);
			host = WMT_HOST_CLASSIC;
			requestAddress = "/asp/XMLInvoice.asp";
		}
		// отправляем запрос в WMT
		sendHttpRequest(host, requestAddress, req.getXmlRequest());
		// производим разбор запроса
		X1ResponseHandler hdl = new X1ResponseHandler();
		ByteArrayInputStream is = new ByteArrayInputStream(
				this.xmlResponse.getBytes());
		this.saxParser.parse(is, hdl);
		return hdl.getResponse();
	}

	/**
	 * <p>
	 * Минимальная форма для X10: Получение списка счетов на оплату.
	 * </p>
	 * 
	 * @param wmid
	 *            WM-идентификатор, которому был выписан счет(-а) на оплату.
	 * @param dateStart
	 *            Минимальное время и дата создания счета.
	 * @param dateFinish
	 *            Максимальное время и дата создания счета.
	 * @return X10Response
	 * @throws Exception
	 */
	public X10Response x10(String wmid, Date dateStart, Date dateFinish)
			throws Exception {
		return this.x10(wmid, 0, dateStart, dateFinish);
	}

	/**
	 * <p>
	 * Простая форма для X10: Получение списка счетов на оплату.
	 * </p>
	 * 
	 * @param wmid
	 *            WM-идентификатор, которому был выписан счет(-а) на оплату.
	 * @param wmInvId
	 *            Номер счета в системе WebMoney.
	 * @param dateStart
	 *            Минимальное время и дата создания счета.
	 * @param dateFinish
	 *            Максимальное время и дата создания счета.
	 * @return X10Response
	 * @throws Exception
	 */
	public X10Response x10(String wmid, long wmInvId, Date dateStart,
			Date dateFinish) throws Exception {
		X10Request req = new X10Request();
		req.setRequestNum(RequestNumberGenerator.getRequestNumber());
		req.setWmid(wmid);
		req.setWmInvId(wmInvId);
		req.setDateStart(dateStart);
		req.setDateFinish(dateFinish);
		return this.x10(req);
	}

	/**
	 * <p>
	 * Библиотечная форма для X10: Получение списка счетов на оплату.
	 * </p>
	 * 
	 * @param req
	 *            X10Request
	 * @return X10Response
	 * @throws Exception
	 */
	public X10Response x10(X10Request req) throws Exception {
		// авторизация по схеме Light
		String host = WMT_HOST_LIGHT;
		String requestAddress = "/asp/XMLInInvoicesCert.asp";
		// подписываем запрос, если авторизация по схеме Classic.
		if (this.signer != null) {
			req = (X10Request) this.initSignature(req);
			host = WMT_HOST_CLASSIC;
			requestAddress = "/asp/XMLInInvoices.asp";
		}
		// отправляем запрос в WMT
		sendHttpRequest(host, requestAddress, req.getXmlRequest());
		// производим разбор запроса
		X10ResponseHandler hdl = new X10ResponseHandler();
		ByteArrayInputStream is = new ByteArrayInputStream(
				this.xmlResponse.getBytes());
		this.saxParser.parse(is, hdl);
		return hdl.getResponse();
	}

	/**
	 * <p>
	 * Простая форма для X11: Получение сведений об аттестате WM идентификатора
	 * и персональных данных его владельца.
	 * </p>
	 * 
	 * @param passportWmid
	 *            WMID аттестата.
	 * @param dict
	 *            Отображение "опорного словаря" (1 - да, 0 - нет).
	 * @param info
	 *            Отображение персональных данных владельца аттестата (1 - да, 0
	 *            - нет).
	 * @param mode
	 *            Проверка принадлежности WM идентификатора, подписавшего
	 *            запрос, списку доверенных идентификаторов для проверяемого
	 *            аттестата (1 - да, 0 - нет).
	 * @return X11Response
	 * @throws Exception
	 */
	public X11Response x11(String passportWmid, int dict, int info, int mode)
			throws Exception {
		X11Request req = new X11Request();
		req.setPassportWmid(passportWmid);
		req.setDict(dict);
		req.setInfo(info);
		req.setMode(mode);
		return this.x11(req);
	}

	/**
	 * <p>
	 * Библиотечная форма для X11: Получение сведений об аттестате WM
	 * идентификатора и персональных данных его владельца.
	 * </p>
	 * 
	 * @param req
	 *            X11Request
	 * @return X11Response
	 * @throws Exception
	 */
	public X11Response x11(X11Request req) throws Exception {
		// авторизация по обоим схемам
		String host = WMT_HOST_PASSPORT;
		String requestAddress = "/asp/XMLGetWMPassport.asp";
		// подписываем запрос, если авторизация по схеме Classic.
		if (this.signer != null) {
			req = (X11Request) this.initSignature(req);
		}
		// отправляем запрос в WMT
		sendHttpRequest(host, requestAddress, req.getXmlRequest());
		// производим разбор запроса
		X11ResponseHandler hdl = new X11ResponseHandler();
		ByteArrayInputStream is = new ByteArrayInputStream(
				this.xmlResponse.getBytes());
		this.saxParser.parse(is, hdl);
		return hdl.getResponse();
	}

	/**
	 * <p>
	 * Простая форма для X13: Возврат незавершенного платежа с протекцией.
	 * </p>
	 * 
	 * @param wmtranid
	 *            Номер транзакции (целое положительное число) по внутреннему
	 *            учету WebMoney Transfer (wmtranid), при этом тип этой
	 *            транзакции должен быть с протекцией (по коду или по времени),
	 *            а состояние транзакции с протекцией – не завершена.
	 * @return X13Response
	 * @throws Exception
	 */
	public X13Response x13(long wmtranid) throws Exception {
		X13Request req = new X13Request();
		req.setRequestNum(RequestNumberGenerator.getRequestNumber());
		req.setWmTranId(wmtranid);
		return this.x13(req);
	}

	/**
	 * <p>
	 * Библиотечная форма для X13: Возврат незавершенного платежа с протекцией.
	 * </p>
	 * 
	 * @param req
	 *            X13Request
	 * @return X13Response
	 * @throws Exception
	 */
	public X13Response x13(X13Request req) throws Exception {
		// авторизация по схеме Light
		String host = WMT_HOST_LIGHT;
		String requestAddress = "/asp/XMLRejectProtectCert.asp";
		// подписываем запрос, если авторизация по схеме Classic.
		if (this.signer != null) {
			req = (X13Request) this.initSignature(req);
			host = WMT_HOST_CLASSIC;
			requestAddress = "/asp/XMLRejectProtect.asp";
		}
		// отправляем запрос в WMT
		sendHttpRequest(host, requestAddress, req.getXmlRequest());
		// производим разбор запроса
		X13ResponseHandler hdl = new X13ResponseHandler();
		ByteArrayInputStream is = new ByteArrayInputStream(
				this.xmlResponse.getBytes());
		this.saxParser.parse(is, hdl);
		return hdl.getResponse();
	}

	/**
	 * <p>
	 * Простая форма для X14: Бескомиссионный возврат средств отправителю
	 * (покупателю).
	 * </p>
	 * 
	 * @param inWmTranId
	 *            номер транзакции.
	 * @param amount
	 *            сумма транзакции
	 * @return X14Response
	 * @throws Exception
	 */
	public X14Response x14(long inWmTranId, double amount) throws Exception {
		X14Request req = new X14Request();
		req.setRequestNum(RequestNumberGenerator.getRequestNumber());
		req.setInWmTranId(inWmTranId);
		req.setAmount(amount);
		return this.x14(req);
	}

	/**
	 * <p>
	 * Библиотечная форма для X14: Бескомиссионный возврат средств отправителю
	 * (покупателю).
	 * </p>
	 * 
	 * @return X14Response
	 * @throws Exception
	 */
	public X14Response x14(X14Request req) throws Exception {
		// авторизация по схеме Light
		String host = WMT_HOST_LIGHT;
		String requestAddress = "/asp/XMLTransMoneybackCert.asp";
		// подписываем запрос, если авторизация по схеме Classic.
		if (this.signer != null) {
			req = (X14Request) this.initSignature(req);
			host = WMT_HOST_CLASSIC;
			requestAddress = "/asp/XMLTransMoneyback.asp";
		}
		// отправляем запрос в WMT
		sendHttpRequest(host, requestAddress, req.getXmlRequest());
		// производим разбор запроса
		X14ResponseHandler hdl = new X14ResponseHandler();
		ByteArrayInputStream is = new ByteArrayInputStream(
				this.xmlResponse.getBytes());
		this.saxParser.parse(is, hdl);
		return hdl.getResponse();
	}

	/**
	 * <p>
	 * Простая форма для X15: <b>Просмотр</b> и изменение текущих настроек
	 * управления "по доверию".
	 * </p>
	 * 
	 * @param wmid
	 *            проверяемый WMID.
	 * @param iTrust
	 *            'true' - запрос "кому я доверяю", 'false' - запрос
	 *            "кто мне доверяет".
	 * @return X15Response
	 * @throws Exception
	 */
	public X15ResponseList x15List(String wmid, boolean iTrust)
			throws Exception {
		X15RequestList req = new X15RequestList();
		req.setRequestNum(RequestNumberGenerator.getRequestNumber());
		req.setWmid(wmid);
		return this.x15List(req, iTrust);
	}

	/**
	 * <p>
	 * Библиотечная форма для X15: <b>Просмотр</b> и изменение текущих настроек
	 * управления "по доверию".
	 * </p>
	 * 
	 * @param req
	 *            X15RequestList
	 * @param iTrust
	 *            'true' - запрос "кому я доверяю", 'false' - запрос
	 *            "кто мне доверяет".
	 * @return X15Response
	 * @throws Exception
	 */
	public X15ResponseList x15List(X15RequestList req, boolean iTrust)
			throws Exception {
		// общая авторизация
		String host = WMT_HOST_CLASSIC;
		String requestAddress;
		if (iTrust) {
			requestAddress = "/asp/XMLTrustListCert.asp";
		} else {
			requestAddress = "/asp/XMLTrustList2Cert.asp";
		}
		// подписываем запрос, если авторизация по схеме Classic.
		if (this.signer != null) {
			req = (X15RequestList) this.initSignature(req);
			if (iTrust) {
				requestAddress = "/asp/XMLTrustList.asp";
			} else {
				requestAddress = "/asp/XMLTrustList2.asp";
			}
		}
		// отправляем запрос в WMT
		sendHttpRequest(host, requestAddress, req.getXmlRequest());
		// производим разбор запроса
		X15ResponseListHandler hdl = new X15ResponseListHandler();
		ByteArrayInputStream is = new ByteArrayInputStream(
				this.xmlResponse.getBytes());
		this.saxParser.parse(is, hdl);
		return hdl.getResponse();
	}

	/**
	 * <p>
	 * Библиотечная форма для X15: Просмотр и <b>изменение</b> текущих настроек
	 * управления "по доверию".
	 * </p>
	 * 
	 * @param req
	 *            X15RequestList
	 * @return X15Response
	 * @throws Exception
	 */
	public X15ResponseSave x15Save(X15RequestSave req) throws Exception {
		// общая авторизация
		String host = WMT_HOST_CLASSIC;
		String requestAddress = "/asp/XMLTrustSave2Cert.asp";
		// подписываем запрос, если авторизация по схеме Classic.
		if (this.signer != null) {
			req = (X15RequestSave) this.initSignature(req);
			requestAddress = "/asp/XMLTrustSave2.asp";
		}
		// отправляем запрос в WMT
		sendHttpRequest(host, requestAddress, req.getXmlRequest());
		// производим разбор запроса
		X15ResponseSaveHandler hdl = new X15ResponseSaveHandler();
		ByteArrayInputStream is = new ByteArrayInputStream(
				this.xmlResponse.getBytes());
		this.saxParser.parse(is, hdl);
		return hdl.getResponse();
	}

	/**
	 * <p>
	 * Простая форма для X16: Создание кошелька.
	 * </p>
	 * 
	 * @param wmid
	 *            ВМ-идентификатор, которому будет принадлежать вновь созданный
	 *            кошелек.
	 * @param purseType
	 *            Тип создаваемого кошелька в виде одного латинского символа в
	 *            верхнем регистре B ,C ,D ,E ,G ,R ,U ,Y ,Z.
	 * @param desc
	 *            Текстовое название кошелька, которое будет отображаться в
	 *            интерфейсе Webmoney Keeper Classic или Light.
	 * @return X16Response
	 * @throws Exception
	 */
	public X16Response x16(String wmid, char purseType, String desc)
			throws Exception {
		X16Request req = new X16Request();
		req.setRequestNum(RequestNumberGenerator.getRequestNumber());
		req.setWmid(wmid);
		req.setPurseType(purseType);
		req.setDesc(desc);
		return this.x16(req);
	}

	/**
	 * <p>
	 * Библиотечная форма для X16: Создание кошелька.
	 * </p>
	 * 
	 * @param req
	 *            X16Request
	 * @return X16Response
	 * @throws Exception
	 */
	public X16Response x16(X16Request req) throws Exception {
		// авторизация по схеме Light
		String host = WMT_HOST_LIGHT;
		String requestAddress = "/asp/XMLCreatePurseCert.asp";
		// подписываем запрос, если авторизация по схеме Classic.
		if (this.signer != null) {
			req = (X16Request) this.initSignature(req);
			host = WMT_HOST_CLASSIC;
			requestAddress = "/asp/XMLCreatePurse.asp";
		}
		// отправляем запрос в WMT
		sendHttpRequest(host, requestAddress, req.getXmlRequest());
		// производим разбор запроса
		X16ResponseHandler hdl = new X16ResponseHandler();
		ByteArrayInputStream is = new ByteArrayInputStream(
				this.xmlResponse.getBytes());
		this.saxParser.parse(is, hdl);
		return hdl.getResponse();
	}

	/**
	 * <p>
	 * Минимальная форма для X17: Операции с арбитражными контрактами.
	 * </p>
	 * 
	 * @param name
	 *            Название контракта (не более 255 символов).
	 * @param text
	 *            Текст контракта. Для разделения строк в тексте контракта
	 *            используйте: "\r\n".
	 * 
	 * @return X17Response
	 * @throws Exception
	 */
	public X17Response x17(String name, String text) throws Exception {
		X17Request req = new X17Request();
		req.setName(name);
		req.setText(text);
		req.setCtype(1);
		req.setAccessList(null);
		return this.x17(req);
	}

	/**
	 * <p>
	 * Простая форма для X17: Операции с арбитражными контрактами.
	 * </p>
	 * 
	 * @param name
	 *            Название контракта (не более 255 символов).
	 * @param text
	 *            Текст контракта. Для разделения строк в тексте контракта
	 *            используйте: "\r\n".
	 * @param ctype
	 *            Тип контракта. ctype=1 – контракт с открытым доступом, ctype=2
	 *            – контракт с ограниченным доступом
	 * @param accessList
	 *            Список WMID пользователей, которым разрешается акцептовывать
	 *            данный контракт.
	 * @return X17Response
	 * @throws Exception
	 */
	public X17Response x17(String name, String text, int ctype,
			List<Wmid> accessList) throws Exception {
		X17Request req = new X17Request();
		req.setName(name);
		req.setText(text);
		req.setCtype(ctype);
		req.setAccessList(accessList);
		return this.x17(req);
	}

	/**
	 * <p>
	 * Библиотечная форма для X17: Операции с арбитражными контрактами.
	 * </p>
	 * 
	 * @param req
	 *            X17Request
	 * @return X17Response
	 * @throws Exception
	 */
	public X17Response x17(X17Request req) throws Exception {
		// авторизация по схеме Light
		String host = WMT_HOST_ARBITRAGE;
		String requestAddress = "/xml/X17_CreateContract.aspx";
		// подписываем запрос
		req = (X17Request) this.initSignature(req);
		// отправляем запрос в WMT
		sendHttpRequest(host, requestAddress, req.getXmlRequest());
		// производим разбор запроса
		X17ResponseHandler hdl = new X17ResponseHandler();
		ByteArrayInputStream is = new ByteArrayInputStream(
				this.xmlResponse.getBytes());
		this.saxParser.parse(is, hdl);
		return hdl.getResponse();
	}

	/**
	 * <p>
	 * Простая форма для X18: Получение деталей операции через WM Merchant.
	 * </p>
	 * 
	 * @param wmid
	 *            Wmid получателя платежа или доверенный WMID.
	 * @param lmiPayeePurse
	 *            Кошелек получателя платежа.
	 * @param lmiPaymentNo
	 *            Номер платежа.
	 * @param secretKey
	 *            Секретное слово из настроек кошелька lmi_payee_purse в сервисе
	 *            merchant.webmoney.ru.
	 * @param authType
	 *            Тип аутентификации при запросе.
	 * @return X18Response
	 * @throws Exception
	 */
	public X18Response x18(String wmid, String lmiPayeePurse,
			long lmiPaymentNo, String secretKey, X18AuthType authType)
			throws Exception {
		X18Request req = new X18Request();
		req.setWmid(wmid);
		req.setLmiPayeePurse(lmiPayeePurse);
		req.setLmiPaymentNo(lmiPaymentNo);
		req.setSecretKey(secretKey);
		req.setAuthType(authType);
		return this.x18(req);
	}

	/**
	 * <p>
	 * Библиотечная форма для X18: Получение деталей операции через WM Merchant.
	 * </p>
	 * 
	 * @param req
	 *            X18Request
	 * @return X18Response
	 * @throws Exception
	 */
	public X18Response x18(X18Request req) throws Exception {
		// авторизация по схеме Light
		String host = WMT_HOST_MERCHANT;
		String requestAddress = "/conf/xml/XMLTransGet.asp";
		// подписываем запрос, если авторизация по схеме Classic.
		if ((this.signer != null)
				&& req.getAuthType() == X18AuthType.WM_SIGNER_AUTH) {
			req = (X18Request) this.initSignature(req);
		}
		// отправляем запрос в WMT
		sendHttpRequest(host, requestAddress, req.getXmlRequest());
		// производим разбор запроса
		X18ResponseHandler hdl = new X18ResponseHandler();
		ByteArrayInputStream is = new ByteArrayInputStream(
				this.xmlResponse.getBytes());
		this.saxParser.parse(is, hdl);
		return hdl.getResponse();
	}

	/**
	 * <p>
	 * Простая форма для X19: Проверка соответствия персональных данных
	 * владельца WM-идентификатора.
	 * </p>
	 * 
	 */
	public X19Response x19(X19Operation operation, X19UserInfo userInfo,
			String lang) throws Exception {
		X19Request req = new X19Request();
		req.setRequestNum(RequestNumberGenerator.getRequestNumber());
		req.setOperation(operation);
		req.setUserInfo(userInfo);
		return this.x19(req);
	}

	/**
	 * <p>
	 * Библиотечная форма для X19: Проверка соответствия персональных данных
	 * владельца WM-идентификатора.
	 * </p>
	 * 
	 * @param req
	 *            X19Request
	 * @return X19Response
	 * @throws Exception
	 */
	public X19Response x19(X19Request req) throws Exception {
		// авторизация по схеме Light
		String host = WMT_HOST_PASSPORT;
		String requestAddress = "/XML/XMLCheckUserCert.aspx";
		// подписываем запрос, если авторизация по схеме Classic.
		if (this.signer != null) {
			req = (X19Request) this.initSignature(req);
			requestAddress = "/XML/XMLCheckUser.aspx";
		}
		// отправляем запрос в WMT
		sendHttpRequest(host, requestAddress, req.getXmlRequest());
		// производим разбор запроса
		X19ResponseHandler hdl = new X19ResponseHandler();
		ByteArrayInputStream is = new ByteArrayInputStream(
				this.xmlResponse.getBytes());
		this.saxParser.parse(is, hdl);
		return hdl.getResponse();
	}

	/**
	 * <p>
	 * Минимальная форма для X2: Перевод средств с одного кошелька на другой.
	 * </p>
	 * 
	 * @param tranId
	 *            Номер перевода в системе учета отправителя.
	 * @param purseSrc
	 *            Номер кошелька с которого выполняется перевод (отправитель).
	 * @param purseDest
	 *            Номер кошелька, на который выполняется перевод (получатель).
	 * @param amount
	 *            Сумма платежа.
	 * @param desc
	 *            Описание оплачиваемого товара или услуги.
	 * @return X2Response
	 * @throws Exception
	 */
	public X2Response x2(long tranId, String purseSrc, String purseDest,
			double amount, String desc) throws Exception {
		X2Request req = new X2Request();
		req.setRequestNum(RequestNumberGenerator.getRequestNumber());
		req.setTranId(tranId);
		req.setPurseSrc(purseSrc);
		req.setPurseDest(purseDest);
		req.setAmount(amount);
		req.setPeriod(0);
		req.setPcode("");
		req.setDesc(desc);
		req.setWmInvId(0);
		return this.x2(req);
	}

	/**
	 * <p>
	 * Простая форма для X2: Перевод средств с одного кошелька на другой.
	 * </p>
	 * 
	 * @param tranId
	 *            Номер перевода в системе учета отправителя.
	 * @param purseSrc
	 *            Номер кошелька с которого выполняется перевод (отправитель).
	 * @param purseDest
	 *            Номер кошелька, на который выполняется перевод (получатель).
	 * @param amount
	 *            Сумма платежа.
	 * @param desc
	 *            Описание оплачиваемого товара или услуги.
	 * @param period
	 *            Срок протекции сделки в днях.
	 * @param pcode
	 *            Код протекции сделки.
	 * @param wmInvId
	 *            Номер счета в системе WebMoney, по которому выполняется
	 *            перевод.
	 * @return X2Response
	 * @throws Exception
	 */
	public X2Response x2(long tranId, String purseSrc, String purseDest,
			double amount, String desc, int period, String pcode, long wmInvId)
			throws Exception {
		X2Request req = new X2Request();
		req.setRequestNum(RequestNumberGenerator.getRequestNumber());
		req.setTranId(tranId);
		req.setPurseSrc(purseSrc);
		req.setPurseDest(purseDest);
		req.setAmount(amount);
		req.setPeriod(period);
		req.setPcode(pcode);
		req.setDesc(desc);
		req.setWmInvId(wmInvId);
		return this.x2(req);
	}

	/**
	 * <p>
	 * Библиотечная форма для X2: Перевод средств с одного кошелька на другой.
	 * </p>
	 * 
	 * @param req
	 *            X2Request
	 * @return X2Response
	 * @throws Exception
	 */
	public X2Response x2(X2Request req) throws Exception {
		// авторизация по схеме Light
		String host = WMT_HOST_LIGHT;
		String requestAddress = "/asp/XMLTransCert.asp";
		// подписываем запрос, если авторизация по схеме Classic.
		if (this.signer != null) {
			req = (X2Request) this.initSignature(req);
			host = WMT_HOST_CLASSIC;
			requestAddress = "/asp/XMLTrans.asp";
		}
		// отправляем запрос в WMT
		sendHttpRequest(host, requestAddress, req.getXmlRequest());
		// производим разбор запроса
		X2ResponseHandler hdl = new X2ResponseHandler();
		ByteArrayInputStream is = new ByteArrayInputStream(
				this.xmlResponse.getBytes());
		this.saxParser.parse(is, hdl);
		return hdl.getResponse();
	}
        
        
        /**
	 * <p>
	 * Минимальная форма для X20Initiation: Проведение транзакции в 
         * merchant.webmoney без ухода с сайта (ресурса, сервиса, приложения) продавца.
         * 1-й запрос, инициирование оплаты.
	 * </p>
	 * 
         * Дефолтные значения для полей:
         *      ClientNumberType = PHONE_NUMBER   
         *      SmsType = DETERMINE_AUTOMATICALLY (SMS by default)
         *      Lang = "en-US"
         * 
         * 
	 * @param payeePurse
	 *            Номер кошелька получателя платежа.
	 * @param paymentNo
	 *            Номер платежа в системе учета продавца, который должен 
         *            быть сформирован приложением продавца.
	 * @param amount
	 *            Сумма платежа, которую продавец желает получить от покупателя.
         * * @param desc
	 *            Назначение платежа. Максимальная длина – 255 символов.
         * * @param clientNumber
	 *            Мобильный телефон (с кодом страны и города только цифры 
         *            без плюсов, скобок и других символов например для России 
         *            79167777777 или Украины 380527777777) или Wmid 
         *            клиента (строго 12 цифр) или E-mail клиента. 
	 * @return X20InitiationResponse
	 * @throws Exception
	 */
	public X20InitiationResponse x20Initiation(String payeePurse, long paymentNo, 
                double amount, String desc, String clientNumber) throws Exception {            
		X20InitiationRequest req = new X20InitiationRequest();                
                req.setPayeePurse(payeePurse);
                req.setLmiPaymentNo(paymentNo);
                req.setAmount(amount);
                req.setDesc(desc);
                req.setClientNumber(clientNumber);                
                req.setClientNumberType(X20ClientNumberType.PHONE_NUMBER);
                req.setSmsType(X20SmsType.DETERMINE_AUTOMATICALLY);
                req.setLang("en-US");
		return this.x20Initiation(req);
	}
        
        
         /**
	 * <p>
	 * Простая форма для X20Initiation: Проведение транзакции в 
         * merchant.webmoney без ухода с сайта (ресурса, сервиса, приложения) продавца.
         * 1-й запрос, инициирование оплаты.
	 * </p>	           
         * 
         * 
	 * @param payeePurse
	 *            Номер кошелька получателя платежа.
	 * @param paymentNo
	 *            Номер платежа в системе учета продавца, который должен 
         *            быть сформирован приложением продавца.
	 * @param amount
	 *            Сумма платежа, которую продавец желает получить от покупателя.
         * @param desc
	 *            Назначение платежа. Максимальная длина – 255 символов.
         * @param clientNumber
	 *            Мобильный телефон (с кодом страны и города только цифры 
         *            без плюсов, скобок и других символов например для России 
         *            79167777777 или Украины 380527777777) или Wmid 
         *            клиента (строго 12 цифр) или E-mail клиента. 
         * @param clientNumberType
	 *            Тип переданных в clientNumber данных.
         * @param smsType
	 *            Тип подтверждения клиентом транзакции (SMS, USSD и тд и тп).
         * @param lang
	 *            В данном параметре передается значение ru-RU или en-US 
         *            соответсвенно для русского или английского языка интерфейса. 
         *            Данное значение определяет и язык отправляемых пользователю 
         *            SMS (USSD) запросов и язык ответов в теге userdesc.
         * 
	 * @return X20InitiationResponse
	 * @throws Exception
	 */
	public X20InitiationResponse x20Initiation(String payeePurse, long paymentNo, 
                        double amount, String desc, String clientNumber, 
                        X20ClientNumberType clientNumberType, X20SmsType smsType,
                        String lang) throws Exception {
		X20InitiationRequest req = new X20InitiationRequest();                
                req.setPayeePurse(payeePurse);
                req.setLmiPaymentNo(paymentNo);
                req.setAmount(amount);
                req.setDesc(desc);
                req.setClientNumber(clientNumber);                
                req.setClientNumberType(clientNumberType);
                req.setSmsType(smsType);
                req.setLang(lang);
		return this.x20Initiation(req);		
	}
        
        /**
	 * <p>
	 * Библиотечная форма для X20Initiation: Проведение транзакции в 
         * merchant.webmoney без ухода с сайта (ресурса, сервиса, приложения) продавца.
         * 1-й запрос, инициирование оплаты.          
	 * </p>
	 * 
	 * @param req
	 *            X20InitiationRequest
	 * @return X20InitiationResponse
	 * @throws Exception
	 */
        public X20InitiationResponse x20Initiation(X20InitiationRequest req) 
                    throws Exception {
                        
                String host = WMT_HOST_MERCHANT;
                String requestAddress = "/conf/xml/XMLTransRequest.asp";
                // подписываем запрос.
                //возможны 3 варианта подписи:
                //  1) sign
                //  2) md5
                //  3) secretKey - не реализовано т.к. не секьюрно.
                if (this.signer != null) {                
                    req = (X20InitiationRequest) this.initSignature(req);
                } else {
                    //secretKey должен быть заполнен!
                    //TODO проверить заполнение обязательных полей.
                    req.initMd5();            
                }

                // отправляем запрос в WMT
                sendHttpRequest(host, requestAddress, req.getXmlRequest());
                // производим разбор запроса
                X20InitiationResponseHandler hdl = new X20InitiationResponseHandler();
                ByteArrayInputStream is = new ByteArrayInputStream(
                                this.xmlResponse.getBytes());
                this.saxParser.parse(is, hdl);
                return hdl.getResponse();
        }
                
         /**
	 * <p>
	 * Простая форма для x20Confirmation: Проведение транзакции в 
         * merchant.webmoney без ухода с сайта (ресурса, сервиса, приложения) продавца. 
         * 2-й запрос, подтверждение оплаты.
	 * </p>	           
         * 
         * 
	 * @param payeePurse
	 *            Номер кошелька получателя платежа.
	 * @param clientNumberCode
	 *            В данном поле передается цифровой код, который клиент получил 
         *            на мобильный телефон для подтверждения платежа. Если СМС 
         *            не отправлялась клиенту (был отправлен USSD запрос или 
         *            просто ожидается оплата клиентом ВМ-счета через мобильные 
         *            программы управления кошельками), то здесь необходимо 
         *            передать код со значением 0.
	 * @param wminvoiceId
	 *            Номер ВМ-счета wminvoiceid, полученный из ответа на предыдущий запрос.
         * @param lang
	 *            В данном параметре передается значение ru-RU или en-US 
         *            соответсвенно для русского или английского языка интерфейса. 
         *            Данное значение определяет и язык отправляемых пользователю 
         *            SMS (USSD) запросов и язык ответов в теге userdesc.
         *          
	 * @return X20ConfirmationResponse
	 * @throws Exception
	 */
	public X20ConfirmationResponse x20Confirmation(String payeePurse, String clientNumberCode, 
                        long wminvoiceId, String lang) throws Exception {
		X20ConfirmationRequest req = new X20ConfirmationRequest();                
                req.setPayeePurse(payeePurse);
                req.setClientNumberCode(clientNumberCode);                
                req.setRequestNum(wminvoiceId);
                req.setLang(lang);                                		
		return this.x20Confirmation(req);		
	}
        
        /**
	 * <p>
	 * Библиотечная форма для x20Confirmation: Проведение транзакции в 
         * merchant.webmoney без ухода с сайта (ресурса, сервиса, приложения) продавца. 
         * 2-й запрос, подтверждение оплаты.
	 * </p>
	 * 
	 * @param req
	 *            X20ConfirmationRequest
	 * @return X20ConfirmationResponse
	 * @throws Exception
	 */
        public X20ConfirmationResponse x20Confirmation(X20ConfirmationRequest req) 
                    throws Exception {
                        
                String host = WMT_HOST_MERCHANT;
                String requestAddress = "/conf/xml/XMLTransConfirm.asp";
                // подписываем запрос.
                //возможны 3 варианта подписи:
                //  1) sign
                //  2) md5
                //  3) secretKey - не реализовано т.к. не секьюрно.
                if (this.signer != null) {                
                    req = (X20ConfirmationRequest) this.initSignature(req);
                } else {
                    //secretKey должен быть заполнен!
                    //TODO проверить заполнение обязательных полей.
                    req.initMd5();            
                }

                // отправляем запрос в WMT
                sendHttpRequest(host, requestAddress, req.getXmlRequest());
                // производим разбор запроса
                X20ConfirmationResponseHandler hdl = new X20ConfirmationResponseHandler();
                ByteArrayInputStream is = new ByteArrayInputStream(
                                this.xmlResponse.getBytes());
                this.saxParser.parse(is, hdl);
                return hdl.getResponse();
        }
                
	/**
	 * <p>
	 * Минимальная форма для X3: Получение истории операций по кошельку,
	 * проверка выполнения операции по переводу средств.
	 * </p>
	 * 
	 * @param purse
	 *            Номер кошелька для которого запрашивается операция.
	 * @param dateStart
	 *            Минимальное время и дата выполнения операции.
	 * @param dateFinish
	 *            Максимальное время и дата выполнения операции.
	 * @return X3Response
	 * @throws Exception
	 */
	public X3Response x3(String purse, Date dateStart, Date dateFinish)
			throws Exception {
		X3Request req = new X3Request();
		req.setRequestNum(RequestNumberGenerator.getRequestNumber());
		req.setPurse(new PurseNumber(purse));
		req.setDateStart(new WmDate(dateStart));
		req.setDateFinish(new WmDate(dateFinish));
		return this.x3(req);
	}

	/**
	 * <p>
	 * Простая форма для X3: Получение истории операций по кошельку, проверка
	 * выполнения операции по переводу средств.
	 * </p>
	 * 
	 * @param purse
	 *            Номер кошелька для которого запрашивается операция.
	 * @param dateStart
	 *            Минимальное время и дата выполнения операции.
	 * @param dateFinish
	 *            Максимальное время и дата выполнения операции.
	 * @param wmTranId
	 *            Номер операции в системе WebMoney.
	 * @param tranId
	 *            Номер перевода в системе отправителя.
	 * @param wmInvId
	 *            Номер счета в системе WebMoney по которому выполнялась
	 *            операция.
	 * @param orderId
	 *            Номер счета в системе учета магазина.
	 * @return X3Response
	 * @throws Exception
	 */
	public X3Response x3(String purse, Date dateStart, Date dateFinish,
			long wmTranId, long tranId, long wmInvId, long orderId)
			throws Exception {
		X3Request req = new X3Request();
		req.setRequestNum(RequestNumberGenerator.getRequestNumber());
		req.setPurse(new PurseNumber(purse));
		req.setDateStart(new WmDate(dateStart));
		req.setDateFinish(new WmDate(dateFinish));
		req.setWmTranId(wmTranId);
		req.setTranId(tranId);
		req.setWmInvId(wmInvId);
		req.setOrderId(orderId);
		return this.x3(req);
	}

	/**
	 * <p>
	 * Библиотечная форма для X3: Получение истории операций по кошельку,
	 * проверка выполнения операции по переводу средств.
	 * </p>
	 * 
	 * @param req
	 *            X3Request
	 * @return X3Response
	 * @throws Exception
	 */
	public X3Response x3(X3Request req) throws Exception {
		// авторизация по схеме Light
		String host = WMT_HOST_LIGHT;
		String requestAddress = "/asp/XMLOperationsCert.asp";
		// подписываем запрос, если авторизация по схеме Classic.
		if (this.signer != null) {
			req = (X3Request) this.initSignature(req);
			host = WMT_HOST_CLASSIC;
			requestAddress = "/asp/XMLOperations.asp";
		}
		// отправляем запрос в WMT
		sendHttpRequest(host, requestAddress, req.getXmlRequest());
		// производим разбор запроса
		X3ResponseHandler hdl = new X3ResponseHandler();
		ByteArrayInputStream is = new ByteArrayInputStream(
				this.xmlResponse.getBytes());
		this.saxParser.parse(is, hdl);
		return hdl.getResponse();
	}

	/**
	 * <p>
	 * Минимальная форма для X4: Получение истории выписанных счетов по
	 * кошельку. Проверка оплаты счета.
	 * </p>
	 * 
	 * @param purse
	 *            Номер кошелька для оплаты на который выписывался счет.
	 * @param dateStart
	 *            Максимальное время и дата создания счета.
	 * @param dateFinish
	 *            Минимальное время и дата создания счета.
	 * @return X4Response
	 * @throws Exception
	 */
	public X4Response x4(String purse, Date dateStart, Date dateFinish)
			throws Exception {
		X4Request req = new X4Request();
		req.setRequestNum(RequestNumberGenerator.getRequestNumber());
		req.setPurse(purse);
		req.setWmInvId(0);
		req.setOrderId(0);
		req.setDateStart(dateStart);
		req.setDateFinish(dateFinish);
		return this.x4(req);
	}

	/**
	 * <p>
	 * Простая форма для X4: Получение истории выписанных счетов по кошельку.
	 * Проверка оплаты счета.
	 * </p>
	 * 
	 * @param purse
	 *            Номер кошелька для оплаты на который выписывался счет.
	 * @param wmInvId
	 *            Номер счета в системе WebMoney.
	 * @param orderId
	 *            Номер счета в системе учета магазина.
	 * @param dateStart
	 *            Максимальное время и дата создания счета.
	 * @param dateFinish
	 *            Минимальное время и дата создания счета.
	 * @return X4Response
	 * @throws Exception
	 */
	public X4Response x4(String purse, long wmInvId, long orderId,
			Date dateStart, Date dateFinish) throws Exception {
		X4Request req = new X4Request();
		req.setRequestNum(RequestNumberGenerator.getRequestNumber());
		req.setPurse(purse);
		req.setWmInvId(wmInvId);
		req.setOrderId(orderId);
		req.setDateStart(dateStart);
		req.setDateFinish(dateFinish);
		return this.x4(req);
	}

	/**
	 * <p>
	 * Библиотечная форма для X4: Получение истории выписанных счетов по
	 * кошельку. Проверка оплаты счета.
	 * </p>
	 * 
	 * @param req
	 *            X4Request
	 * @return X4Response
	 * @throws Exception
	 */
	public X4Response x4(X4Request req) throws Exception {
		// авторизация по схеме Light
		String host = WMT_HOST_LIGHT;
		String requestAddress = "/asp/XMLOutInvoicesCert.asp";
		// подписываем запрос, если авторизация по схеме Classic.
		if (this.signer != null) {
			req = (X4Request) this.initSignature(req);
			host = WMT_HOST_CLASSIC;
			requestAddress = "/asp/XMLOutInvoices.asp";
		}
		// отправляем запрос в WMT
		sendHttpRequest(host, requestAddress, req.getXmlRequest());
		// производим разбор запроса
		X4ResponseHandler hdl = new X4ResponseHandler();
		// для Light и Classic версий кодировки различаются
		String charset = (this.signer != null) ? "windows-1251" : "UTF-8";
		hdl.setHttpCharset(charset);
		ByteArrayInputStream is = new ByteArrayInputStream(
				this.xmlResponse.getBytes());
		this.saxParser.parse(is, hdl);
		return hdl.getResponse();
	}

	/**
	 * <p>
	 * Простая форма для X5: Завершение операции с протекцией сделки. Ввод кода
	 * протекции.
	 * </p>
	 * 
	 * @param wmTranId
	 *            Уникальный номер платежа в системе учета WebMoney.
	 * @param pCode
	 *            Код протекции сделки.
	 * @return X5Response
	 * @throws Exception
	 */
	public X5Response x5(long wmTranId, String pCode) throws Exception {
		X5Request req = new X5Request();
		req.setRequestNum(RequestNumberGenerator.getRequestNumber());
		req.setWmTranId(wmTranId);
		req.setPcode(pCode);
		return this.x5(req);
	}

	/**
	 * <p>
	 * Библиотечная форма для X5: Завершение операции с протекцией сделки. Ввод
	 * кода протекции.
	 * </p>
	 * 
	 * @param req
	 *            X5Request
	 * @return X5Response
	 * @throws Exception
	 */
	public X5Response x5(X5Request req) throws Exception {
		// авторизация по схеме Light
		String host = WMT_HOST_LIGHT;
		String requestAddress = "/asp/XMLFinishProtectCert.asp";
		// подписываем запрос, если авторизация по схеме Classic.
		if (this.signer != null) {
			req = (X5Request) this.initSignature(req);
			host = WMT_HOST_CLASSIC;
			requestAddress = "/asp/XMLFinishProtect.asp";
		}
		// отправляем запрос в WMT
		sendHttpRequest(host, requestAddress, req.getXmlRequest());
		// производим разбор запроса
		X5ResponseHandler hdl = new X5ResponseHandler();
		ByteArrayInputStream is = new ByteArrayInputStream(
				this.xmlResponse.getBytes());
		this.saxParser.parse(is, hdl);
		return hdl.getResponse();
	}

	/**
	 * <p>
	 * Простая форма для X6: Отправка сообщения произвольному WM-идентификатору
	 * по внутренней почте.
	 * </p>
	 * 
	 * @param receiverWmid
	 *            WM-идентификатор получателя сообщения.
	 * @param msgSubj
	 *            Тема сообщения.
	 * @param msgText
	 *            Текст сообщения.
	 * @return X6Response
	 * @throws Exception
	 */
	public X6Response x6(String receiverWmid, String msgSubj, String msgText)
			throws Exception {
		X6Request req = new X6Request();
		req.setRequestNum(RequestNumberGenerator.getRequestNumber());
		req.setReceiverWmid(new Wmid(receiverWmid));
		req.setMsgSubj(msgSubj);
		req.setMsgText(msgText);
		return this.x6(req);
	}

	/**
	 * <p>
	 * Библиотечная форма для X6: Отправка сообщения произвольному
	 * WM-идентификатору по внутренней почте.
	 * </p>
	 * 
	 * @param req
	 *            X6Request
	 * @return X6Response
	 * @throws Exception
	 */
	public X6Response x6(X6Request req) throws Exception {
		// авторизация по схеме Light
		String host = WMT_HOST_LIGHT;
		String requestAddress = "/asp/XMLSendMsgCert.asp";
		// подписываем запрос, если авторизация по схеме Classic.
		if (this.signer != null) {
			req = (X6Request) this.initSignature(req);
			host = WMT_HOST_CLASSIC;
			requestAddress = "/asp/XMLSendMsg.asp";
		}
		// отправляем запрос в WMT
		sendHttpRequest(host, requestAddress, req.getXmlRequest());
		// производим разбор запроса
		X6ResponseHandler hdl = new X6ResponseHandler();
		ByteArrayInputStream is = new ByteArrayInputStream(
				this.xmlResponse.getBytes());
		this.saxParser.parse(is, hdl);
		return hdl.getResponse();
	}

	/**
	 * <p>
	 * X7. Проверка АСП клиента - владельца WM Keeper Classic.
	 * </p>
	 * <p>
	 * Простая форма запроса (java-типы).
	 * </p>
	 * 
	 * @param wmid
	 *            WM-идентификатор клиента, которого необходимо
	 *            аутентифицировать.
	 * @param plan
	 *            строка, которую должен был подписать клиент.
	 * @param sign
	 *            подпись строки, передаваемой в параметре testsign\plan,
	 *            сформированная клиентом, которого необходимо
	 *            аутентифицировать.
	 * @return X7Response
	 * @throws Exception
	 */
	public X7Response x7(String wmid, String plan, String sign)
			throws Exception {
		X7Request req = new X7Request();
		req.setTestSignWmid(new Wmid(wmid));
		req.setTestSignPlan(plan);
		req.setTestSignSign(sign);
		return this.x7(req);
	}

	/**
	 * <p>
	 * Библиотечная форма для X7: Проверка АСП клиента - владельца WM Keeper
	 * Classic.
	 * </p>
	 * 
	 * @param req
	 *            X7Request
	 * @return X7Response
	 * @throws Exception
	 */
	public X7Response x7(X7Request req) throws Exception {
		// авторизация по схеме Light
		String host = WMT_HOST_LIGHT;
		String requestAddress = "/asp/XMLClassicAuthCert.asp";
		// подписываем запрос, если авторизация по схеме Classic.
		if (this.signer != null) {
			req = (X7Request) this.initSignature(req);
			host = WMT_HOST_CLASSIC;
			requestAddress = "/asp/XMLClassicAuth.asp";
		}
		// отправляем запрос в WMT
		sendHttpRequest(host, requestAddress, req.getXmlRequest());
		// производим разбор запроса
		X7ResponseHandler hdl = new X7ResponseHandler();
		ByteArrayInputStream is = new ByteArrayInputStream(
				this.xmlResponse.getBytes());
		this.saxParser.parse(is, hdl);
		return hdl.getResponse();
	}

	/**
	 * <p>
	 * Простая форма для X8: Получение информации о принадлежности кошелька.
	 * Поиск участника системы по его идентификатору или кошельку.
	 * </p>
	 * 
	 * @param wmid
	 *            Проверяемый WM-идентификатор.
	 * @param purse
	 *            Номер проверяемого кошелька.
	 * @return X8Response
	 * @throws Exception
	 */
	public X8Response x8(String wmid, String purse) throws Exception {
		X8Request req = new X8Request();
		req.setRequestNum(RequestNumberGenerator.getRequestNumber());
		req.setWmid(wmid);
		req.setPurse(purse);
		return this.x8(req);
	}

	/**
	 * <p>
	 * Библиотечная форма для X8: Получение информации о принадлежности
	 * кошелька. Поиск участника системы по его идентификатору или кошельку.
	 * </p>
	 * 
	 * @param req
	 *            X8Request
	 * @return X8Response
	 * @throws Exception
	 */
	public X8Response x8(X8Request req) throws Exception {
		// авторизация по схеме Light
		String host = WMT_HOST_LIGHT;
		String requestAddress = "/asp/XMLFindWMPurseCert.asp";
		// подписываем запрос, если авторизация по схеме Classic.
		if (this.signer != null) {
			req = (X8Request) this.initSignature(req);
			host = WMT_HOST_CLASSIC;
			requestAddress = "/asp/XMLFindWMPurse.asp";
		}
		// отправляем запрос в WMT
		sendHttpRequest(host, requestAddress, req.getXmlRequest());
		// производим разбор запроса
		X8ResponseHandler hdl = new X8ResponseHandler();
		ByteArrayInputStream is = new ByteArrayInputStream(
				this.xmlResponse.getBytes());
		this.saxParser.parse(is, hdl);
		return hdl.getResponse();
	}

	/**
	 * <p>
	 * Простая форма для X9: Получение информации о балансе на кошельках.
	 * </p>
	 * 
	 * @param wmid
	 *            Проверяемый WM-идентификатор.
	 * @return X9Response
	 * @throws Exception
	 */
	public X9Response x9(String wmid) throws Exception {
		X9Request req = new X9Request();
		req.setRequestNum(RequestNumberGenerator.getRequestNumber());
		req.setWmid(wmid);
		return this.x9(req);
	}

	/**
	 * <p>
	 * Библиотечная форма для X9: Получение информации о балансе на кошельках.
	 * </p>
	 * 
	 * @param req
	 *            X9Request
	 * @return X9Response
	 * @throws Exception
	 */
	public X9Response x9(X9Request req) throws Exception {
		// авторизация по схеме Light
		String host = WMT_HOST_LIGHT;
		String requestAddress = "/asp/XMLPursesCert.asp";
		// подписываем запрос, если авторизация по схеме Classic.
		if (this.signer != null) {
			req = (X9Request) this.initSignature(req);
			host = WMT_HOST_CLASSIC;
			requestAddress = "/asp/XMLPurses.asp";
		}
		// отправляем запрос в WMT
		sendHttpRequest(host, requestAddress, req.getXmlRequest());
		// производим разбор запроса
		X9ResponseHandler hdl = new X9ResponseHandler();
		ByteArrayInputStream is = new ByteArrayInputStream(
				this.xmlResponse.getBytes());
		this.saxParser.parse(is, hdl);
		return hdl.getResponse();
	}
}
