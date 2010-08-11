package lv.flancer.wmt.xml;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import lv.flancer.wmt.xml.dict.PurseNumber;
import lv.flancer.wmt.xml.dict.WmDate;
import lv.flancer.wmt.xml.dict.Wmid;
import lv.flancer.wmt.xml.dict.X18AuthType;
import lv.flancer.wmt.xml.req.RequestNumberGenerator;
import lv.flancer.wmt.xml.req.X10Request;
import lv.flancer.wmt.xml.req.X18Request;
import lv.flancer.wmt.xml.req.X1Request;
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
import lv.flancer.wmt.xml.resp.X18Response;
import lv.flancer.wmt.xml.resp.X1Response;
import lv.flancer.wmt.xml.resp.X2Response;
import lv.flancer.wmt.xml.resp.X3Response;
import lv.flancer.wmt.xml.resp.X4Response;
import lv.flancer.wmt.xml.resp.X5Response;
import lv.flancer.wmt.xml.resp.X6Response;
import lv.flancer.wmt.xml.resp.X7Response;
import lv.flancer.wmt.xml.resp.X8Response;
import lv.flancer.wmt.xml.resp.X9Response;
import lv.flancer.wmt.xml.resp.sax.X10ResponseHandler;
import lv.flancer.wmt.xml.resp.sax.X18ResponseHandler;
import lv.flancer.wmt.xml.resp.sax.X1ResponseHandler;
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
 * Пример вызова (авторизация по схеме Classic):
 * </p>
 * 
 * <pre>
 * try {
 *   WmService service = new WmService();
 *   service.initWmSignerKwm(&quot;123456789012&quot;, &quot;/path/to/kwm/file&quot;, &quot;KeyPassword&quot;);
 *   X6Response resp = service.x6(&quot;123456789012&quot;, &quot;Message Subject&quot;,
 * 		&quot;Message Body...&quot;);
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

	private final static String WMT_HOST_CLASSIC = "w3s.webmoney.ru";
	private final static String WMT_HOST_LIGHT = "w3s.wmtransfer.com";
	private final static String WMT_HOST_MERCHANT = "merchant.webmoney.ru";
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
	 */
	private String sendHttpRequest(String host, String requestAddress,
			String requestBody) throws KeyManagementException,
			NoSuchAlgorithmException, IOException {
		this.httpResponse = null;
		this.httpRequest = null;
		this.xmlResponse = null;
		this.xmlRequest = requestBody;
		HttpRequester httpReq = new HttpRequester(host, 443);
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
				System.out.println("Connection retry #" + i
						+ " is failed. Sleeping " + this.httpRetriesSleepTime
						+ " msec.");
				try {
					Thread.sleep(this.httpRetriesSleepTime);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
		} while ((this.httpResponse == null) && (i < httpRetries));
		this.httpRequest = httpReq.getRequest();
		// вырезаем текст xml из http-ответа.
		int start = this.httpResponse.indexOf("<?xml version=\"1.0\"");
		this.xmlResponse = this.httpResponse.substring(start);
		return this.httpResponse;
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
	 * Минимальная форма для для X10: Получение списка счетов на оплату.
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
	 * Простая форма для для X10: Получение списка счетов на оплату.
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
	 * Простая форма для для X18: Получение деталей операции через WM Merchant.
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
	 * Простая форма для для X9: Получение информации о балансе на кошельках.
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
