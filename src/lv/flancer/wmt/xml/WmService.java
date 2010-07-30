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
import lv.flancer.wmt.xml.req.RequestNumberGenerator;
import lv.flancer.wmt.xml.req.X3Request;
import lv.flancer.wmt.xml.req.X6Request;
import lv.flancer.wmt.xml.req.X7Request;
import lv.flancer.wmt.xml.req.XmlRequest;
import lv.flancer.wmt.xml.resp.AbstractResponse;
import lv.flancer.wmt.xml.resp.X3Response;
import lv.flancer.wmt.xml.resp.X7Response;
import lv.flancer.wmt.xml.resp.sax.X3ResponseHandler;
import lv.flancer.wmt.xml.resp.sax.X7ResponseHandler;
import lv.flancer.wmt.xml.wmsigner.CannotLoadKeysException;
import lv.flancer.wmt.xml.wmsigner.KwmCorruptedException;
import lv.flancer.wmt.xml.wmsigner.WmSigner;

/**
 * <p>
 * Сервис, предоставляющий для доступа к WMT XML методы трех типов:
 * </p>
 * <ul>
 * <li><b>"библиотечные"</b>: в качестве параметра запроса выступает экземпляр
 * соответствующего запроса из данной библиотеки - <i>service.x3(X3Request
 * req)</i>;</li>
 * <li><b>"обычные"</b>: в качестве параметров запроса выступают данные
 * примитивных java-типов - <i>service.x3(String purse, Date dateStart, Date
 * dateFinish, long wmTranId, long tranId, long wmInvId, long orderId)</i>;</li>
 * <li><b>"минимальные"</b>: в качестве минимально-необходимых параметров
 * запроса выступают данные примитивных java-типов (если есть) -
 * <i>service.x3(String purse, Date dateStart, Date dateFinish)</i>;</li>
 * </ul>
 * <p>
 * Некоторые параметры запроса, например - w3s.request/reqn, доступны для
 * установки только в "библиотечном" типе вызова.
 * </p>
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
	/**
	 * Содержит текстовый вариант HTTP-запроса, отправленного на сервис WMT XML.
	 */
	private String httpRequest;
	/**
	 * Содержит текстовый вариант HTTP-ответа, полученного от сервиса WMT XML.
	 */
	private String httpResponse;
	/**
	 * Парсер для разбора XML-ответа от WMT XML.
	 */
	private SAXParser saxParser;
	/**
	 * WmSigner для генерации подписи при авторизации по схеме Classic.
	 */
	private WmSigner signer;
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
		HttpRequester httpReq = new HttpRequester(host, 443);
		httpReq.setRequestCharset(HTTP_CAHRSET);
		httpReq.setResponseCharset(HTTP_CAHRSET);
		httpReq.setSecuredResuest(true);
		this.httpResponse = httpReq.doPost(requestAddress, requestBody);
		this.httpRequest = httpReq.getRequest();
		// вырезаем текст xml из http-ответа.
		int start = this.httpResponse.indexOf("<?xml version=\"1.0\"");
		this.xmlResponse = this.httpResponse.substring(start);
		return this.httpResponse;
	}

	/**
	 * <p>
	 * X3. Получение истории операций по кошельку. Проверка выполнения операции
	 * по переводу средств.
	 * </p>
	 * <p>
	 * "Минимальная" форма запроса - только необходимые параметры.
	 * </p>
	 * 
	 * @param purse
	 * @param dateStart
	 * @param dateFinish
	 * @return
	 * @throws Exception
	 */
	public X3Response x3(String purse, Date dateStart, Date dateFinish)
			throws Exception {
		X3Request req = new X3Request();
		req.setRequestNum(RequestNumberGenerator.getRequestNumber());
		req.setPurseNumber(new PurseNumber(purse));
		req.setDateStart(new WmDate(dateStart));
		req.setDateFinish(new WmDate(dateFinish));
		return this.x3(req);
	}

	/**
	 * <p>
	 * X3. Получение истории операций по кошельку. Проверка выполнения операции
	 * по переводу средств.
	 * </p>
	 * <p>
	 * Простая форма запроса (java-типы).
	 * </p>
	 * 
	 * @param purse
	 * @param dateStart
	 * @param dateFinish
	 * @param wmTranId
	 * @param tranId
	 * @param wmInvId
	 * @param orderId
	 * @return
	 * @throws Exception
	 */
	public AbstractResponse x3(String purse, Date dateStart, Date dateFinish,
			long wmTranId, long tranId, long wmInvId, long orderId)
			throws Exception {
		X3Request req = new X3Request();
		req.setRequestNum(RequestNumberGenerator.getRequestNumber());
		req.setPurseNumber(new PurseNumber(purse));
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
	 * X3. Получение истории операций по кошельку. Проверка выполнения операции
	 * по переводу средств.
	 * </p>
	 * 
	 * @param req
	 * @return
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
	 * X6: Отправка сообщения произвольному WM-идентификатору по внутренней
	 * почте.
	 * </p>
	 * <p>
	 * Простая форма запроса (java-типы).
	 * </p>
	 * 
	 * @param receiverWmid
	 *            WM-идентификатор получателя сообщения.
	 * @param msgSubj
	 *            тема сообщения
	 * @param msgText
	 *            текст сообщения
	 * @return
	 * @throws Exception
	 */
	public String x6(String receiverWmid, String msgSubj, String msgText)
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
	 * X6: Отправка сообщения произвольному WM-идентификатору по внутренней
	 * почте.
	 * </p>
	 * <p>
	 * Вызов метода с соответствующим X??-запросом.
	 * </p>
	 * 
	 * @param req
	 * @return
	 * @throws Exception
	 */
	public String x6(X6Request req) throws Exception {
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
		return sendHttpRequest(host, requestAddress, req.getXmlRequest());
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
	 * X7. Проверка АСП клиента - владельца WM Keeper Classic.
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
}
