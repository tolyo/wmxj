/**
 * 
 */
package org.wmxj.dict;

/**
 * Персональные данные владельца аттестата. Может содержать как сами данные
 * (/response/certinfo/userinfo/value/row/), так и признаки проверки
 * персональных данных аттестатором и блокировки публичного отображения
 * персональных данных (response/certinfo/userinfo/check-lock/row).
 * 
 * @author Alex Gusev <flancer64@gmail.com>
 * @version 1.0
 * 
 */
public class X11UserInfoRow {
	/**
	 * Фактическое местонахождение (адрес) организации или владельца
	 * аттестованного идентификатора.
	 */
	private String adres;
	/**
	 * Для юридического лица. Банковские реквизиты. Название банка.
	 */
	private String bankname;
	/**
	 * Дата рождения (день).
	 */
	private String bday;
	/**
	 * Для юридического лица. Банковские реквизиты. БИК.
	 */
	private String bik;
	/**
	 * Дата рождения (месяц).
	 */
	private String bmonth;
	/**
	 * Место рождения (населенный пункт, страна).
	 */
	private String bplace;
	/**
	 * Для юридического лица. Главный бухгалтер (ФИО).
	 */
	private String buhfio;
	/**
	 * Дата рождения (год).
	 */
	private String byear;
	/**
	 * Только для аттестатов Capitaller. WMID учредителя.
	 */
	private String cap_owner;
	/**
	 * Фактическое местонахождение (город) организации или владельца
	 * аттестованного идентификатора.
	 */
	private String city;
	/**
	 * Фактическое местонахождение (страна) организации или владельца
	 * аттестованного идентификатора.
	 */
	private String country;
	/**
	 * Статус владельца.
	 */
	private String ctype;
	/**
	 * Для юридического лица. Директор (ФИО).
	 */
	private String dirfio;
	/**
	 * Контактная информация владельца аттестованного идентификатора. Адрес
	 * электронной почты.
	 */
	private String email;
	/**
	 * Контактная информация владельца аттестованного идентификатора. Номер
	 * факса.
	 */
	private String fax;
	/**
	 * Фамилия владельца аттестованного идентификатора.
	 */
	private String fname;
	/**
	 * Контактная информация владельца аттестованного идентификатора. UIN ICQ.
	 */
	private String icq;
	/**
	 * Имя владельца аттестованного идентификатора.
	 */
	private String iname;
	/**
	 * Дополнительная информация (направление деятельности, комментарии,
	 * информация рекламного характера.
	 */
	private String infoopen;
	/**
	 * Для юридического лица. ИНН.
	 */
	private String inn;
	/**
	 * Признак проверки электронной копии свидетельства о постановке на
	 * налоговый учет администратором центра аттестации.
	 */
	private String inndoc;
	/**
	 * Для юридического лица. Юридический адрес.
	 */
	private String jadres;
	/**
	 * Для юридического лица. Юридический адрес (город).
	 */
	private String jcity;
	/**
	 * Для юридического лица. Юридический адрес (страна).
	 */
	private String jcountry;
	/**
	 * Для юридического лица. Статус (директор, представитель и др.).
	 */
	private String jstatus;
	/**
	 * Для юридического лица. Юридический адрес (почтовый индекс).
	 */
	private String jzipcode;
	/**
	 * Для юридического лица. Банковские реквизиты. Номер корреспондентского
	 * счета.
	 */
	private String ks;
	/**
	 * Информация об отзыве аттестата.
	 */
	private String locked;
	/**
	 * Для юридического лица. Название организации.
	 */
	private String name;
	/**
	 * Название проекта, имя (nick).
	 */
	private String nickname;
	/**
	 * Для юридического лица. ОКВЭД.
	 */
	private String okonx;
	/**
	 * Для юридического лица. ОКПО.
	 */
	private String okpo;
	/**
	 * Отчество владельца аттестованного идентификатора.
	 */
	private String oname;
	/**
	 * Для юридического лица. На основании чего действует(устав, доверенность и
	 * др.).
	 */
	private String osnovainfo;
	/**
	 * Признак проверки электронной копии паспорта администратором центра
	 * аттестации. Если pasdoc = 1, то электронная копия проверена
	 * администратором центра аттестации. Если pasdoc = 0, то электронная копия
	 * не проверена администратором центра аттестации.
	 */
	private String pasdoc;
	/**
	 * Место (кем выдан) выдачи паспорта.
	 */
	private String pbywhom;
	/**
	 * Место (город) выдачи паспорта.
	 */
	private String pcity;
	/**
	 * Место (страна) выдачи паспорта.
	 */
	private String pcountry;
	/**
	 * Дата выдачи паспорта ММ/ДД/ГГГГ.
	 */
	private String pdate;
	/**
	 * Контактная информация владельца аттестованного идентификатора. Номер
	 * контактного телефона.
	 */
	private String phone;
	/**
	 * Контактная информация владельца аттестованного идентификатора. Номер
	 * домашнего телефона.
	 */
	private String phonehome;
	/**
	 * Контактная информация владельца аттестованного идентификатора. Номер
	 * мобильного телефона.
	 */
	private String phonemobile;
	/**
	 * Номер паспорта владельца аттестованного идентификатора.
	 */
	private String pnomer;
	/**
	 * Место (полный адрес) постоянной регистрации.
	 */
	private String radres;
	/**
	 * Место (город) постоянной регистрации.
	 */
	private String rcity;
	/**
	 * Место (страна) постоянной регистрации.
	 */
	private String rcountry;
	/**
	 * Для юридического лица. Банковские реквизиты. Номер расчетного счета.
	 */
	private String rs;
	/**
	 * Контактная информация владельца аттестованного идентификатора. Адрес
	 * интернет сайта.
	 */
	private String web;
	/**
	 * Фактическое местонахождение (почтовый индекс) организации или владельца
	 * аттестованного идентификатора.
	 */
	private String zipcode;

	/**
	 * Фактическое местонахождение (адрес) организации или владельца
	 * аттестованного идентификатора.
	 * 
	 * @return Фактическое местонахождение (адрес) организации или владельца
	 *         аттестованного идентификатора.
	 */
	public String getAdres() {
		return adres;
	}

	/**
	 * Для юридического лица. Банковские реквизиты. Название банка.
	 * 
	 * @return Для юридического лица. Банковские реквизиты. Название банка.
	 */
	public String getBankname() {
		return bankname;
	}

	/**
	 * Дата рождения (день).
	 * 
	 * @return Дата рождения (день).
	 */
	public String getBday() {
		return bday;
	}

	/**
	 * Для юридического лица. Банковские реквизиты. БИК.
	 * 
	 * @return Для юридического лица. Банковские реквизиты. БИК.
	 */
	public String getBik() {
		return bik;
	}

	/**
	 * Дата рождения (месяц).
	 * 
	 * @return Дата рождения (месяц).
	 */
	public String getBmonth() {
		return bmonth;
	}

	/**
	 * Место рождения (населенный пункт, страна).
	 * 
	 * @return Место рождения (населенный пункт, страна).
	 */
	public String getBplace() {
		return bplace;
	}

	/**
	 * Для юридического лица. Главный бухгалтер (ФИО).
	 * 
	 * @return Для юридического лица. Главный бухгалтер (ФИО).
	 */
	public String getBuhfio() {
		return buhfio;
	}

	/**
	 * Дата рождения (год).
	 * 
	 * @return Дата рождения (год).
	 */
	public String getByear() {
		return byear;
	}

	/**
	 * Только для аттестатов Capitaller. WMID учредителя.
	 * 
	 * @return Только для аттестатов Capitaller. WMID учредителя.
	 */
	public String getCap_owner() {
		return cap_owner;
	}

	/**
	 * Фактическое местонахождение (город) организации или владельца
	 * аттестованного идентификатора
	 * 
	 * @return Фактическое местонахождение (город) организации или владельца
	 *         аттестованного идентификатора
	 */
	public String getCity() {
		return city;
	}

	/**
	 * Фактическое местонахождение (страна) организации или владельца
	 * аттестованного идентификатора.
	 * 
	 * @return Фактическое местонахождение (страна) организации или владельца
	 *         аттестованного идентификатора.
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * Статус владельца.
	 * 
	 * @return Статус владельца.
	 */
	public String getCtype() {
		return ctype;
	}

	/**
	 * Для юридического лица. Директор (ФИО).
	 * 
	 * @return Для юридического лица. Директор (ФИО).
	 */
	public String getDirfio() {
		return dirfio;
	}

	/**
	 * Контактная информация владельца аттестованного идентификатора. Адрес
	 * электронной почты.
	 * 
	 * @return Контактная информация владельца аттестованного идентификатора.
	 *         Адрес электронной почты.
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Контактная информация владельца аттестованного идентификатора. Номер
	 * факса.
	 * 
	 * @return Контактная информация владельца аттестованного идентификатора.
	 *         Номер факса.
	 */
	public String getFax() {
		return fax;
	}

	/**
	 * Фамилия владельца аттестованного идентификатора.
	 * 
	 * @return Фамилия владельца аттестованного идентификатора.
	 */
	public String getFname() {
		return fname;
	}

	/**
	 * Контактная информация владельца аттестованного идентификатора. UIN ICQ.
	 * 
	 * @return Контактная информация владельца аттестованного идентификатора.
	 *         UIN ICQ.
	 */
	public String getIcq() {
		return icq;
	}

	/**
	 * Имя владельца аттестованного идентификатора.
	 * 
	 * @return Имя владельца аттестованного идентификатора.
	 */
	public String getIname() {
		return iname;
	}

	/**
	 * Дополнительная информация (направление деятельности, комментарии,
	 * информация рекламного характера.
	 * 
	 * @return Дополнительная информация (направление деятельности, комментарии,
	 *         информация рекламного характера.
	 */
	public String getInfoopen() {
		return infoopen;
	}

	/**
	 * Для юридического лица. ИНН.
	 * 
	 * @return Для юридического лица. ИНН.
	 */
	public String getInn() {
		return inn;
	}

	/**
	 * Признак проверки электронной копии свидетельства о постановке на
	 * налоговый учет администратором центра аттестации.
	 * 
	 * @return Признак проверки электронной копии свидетельства о постановке на
	 *         налоговый учет администратором центра аттестации.
	 */
	public String getInndoc() {
		return inndoc;
	}

	/**
	 * Для юридического лица. Юридический адрес.
	 * 
	 * @return Для юридического лица. Юридический адрес.
	 */
	public String getJadres() {
		return jadres;
	}

	/**
	 * Для юридического лица. Юридический адрес (город).
	 * 
	 * @return Для юридического лица. Юридический адрес (город).
	 */
	public String getJcity() {
		return jcity;
	}

	/**
	 * Для юридического лица. Юридический адрес (страна).
	 * 
	 * @return Для юридического лица. Юридический адрес (страна).
	 */
	public String getJcountry() {
		return jcountry;
	}

	/**
	 * Для юридического лица. Статус (директор, представитель и др.).
	 * 
	 * @return Для юридического лица. Статус (директор, представитель и др.).
	 */
	public String getJstatus() {
		return jstatus;
	}

	/**
	 * Для юридического лица. Юридический адрес (почтовый индекс).
	 * 
	 * @return Для юридического лица. Юридический адрес (почтовый индекс).
	 */
	public String getJzipcode() {
		return jzipcode;
	}

	/**
	 * Для юридического лица. Банковские реквизиты. Номер корреспондентского
	 * счета.
	 * 
	 * @return Для юридического лица. Банковские реквизиты. Номер
	 *         корреспондентского счета.
	 */
	public String getKs() {
		return ks;
	}

	/**
	 * Информация об отзыве аттестата.
	 * 
	 * @return Информация об отзыве аттестата.
	 */
	public String getLocked() {
		return locked;
	}

	/**
	 * Для юридического лица. Название организации.
	 * 
	 * @return Для юридического лица. Название организации.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Название проекта, имя (nick).
	 * 
	 * @return Название проекта, имя (nick).
	 */
	public String getNickname() {
		return nickname;
	}

	/**
	 * Для юридического лица. ОКВЭД.
	 * 
	 * @return Для юридического лица. ОКВЭД.
	 */
	public String getOkonx() {
		return okonx;
	}

	/**
	 * Для юридического лица. ОКПО.
	 * 
	 * @return Для юридического лица. ОКПО.
	 */
	public String getOkpo() {
		return okpo;
	}

	/**
	 * Отчество владельца аттестованного идентификатора.
	 * 
	 * @return Отчество владельца аттестованного идентификатора.
	 */
	public String getOname() {
		return oname;
	}

	/**
	 * Для юридического лица. На основании чего действует(устав, доверенность и
	 * др.).
	 * 
	 * @return Для юридического лица. На основании чего действует(устав,
	 *         доверенность и др.).
	 */
	public String getOsnovainfo() {
		return osnovainfo;
	}

	/**
	 * Признак проверки электронной копии паспорта администратором центра
	 * аттестации. Если pasdoc = 1, то электронная копия проверена
	 * администратором центра аттестации. Если pasdoc = 0, то электронная копия
	 * не проверена администратором центра аттестации.
	 * 
	 * @return Признак проверки электронной копии паспорта администратором
	 *         центра аттестации.
	 */
	public String getPasdoc() {
		return pasdoc;
	}

	/**
	 * Место (кем выдан) выдачи паспорта.
	 * 
	 * @return Место (кем выдан) выдачи паспорта.
	 */
	public String getPbywhom() {
		return pbywhom;
	}

	/**
	 * Место (город) выдачи паспорта.
	 * 
	 * @return Место (город) выдачи паспорта.
	 */
	public String getPcity() {
		return pcity;
	}

	/**
	 * Место (страна) выдачи паспорта.
	 * 
	 * @return Место (страна) выдачи паспорта.
	 */
	public String getPcountry() {
		return pcountry;
	}

	/**
	 * Дата выдачи паспорта ММ/ДД/ГГГГ.
	 * 
	 * @return Дата выдачи паспорта ММ/ДД/ГГГГ.
	 */
	public String getPdate() {
		return pdate;
	}

	/**
	 * Контактная информация владельца аттестованного идентификатора. Номер
	 * контактного телефона.
	 * 
	 * @return Контактная информация владельца аттестованного идентификатора.
	 *         Номер контактного телефона.
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * Контактная информация владельца аттестованного идентификатора. Номер
	 * домашнего телефона.
	 * 
	 * @return Контактная информация владельца аттестованного идентификатора.
	 *         Номер домашнего телефона.
	 */
	public String getPhonehome() {
		return phonehome;
	}

	/**
	 * Контактная информация владельца аттестованного идентификатора. Номер
	 * мобильного телефона.
	 * 
	 * @return Контактная информация владельца аттестованного идентификатора.
	 *         Номер мобильного телефона.
	 */
	public String getPhonemobile() {
		return phonemobile;
	}

	/**
	 * Номер паспорта владельца аттестованного идентификатора.
	 * 
	 * @return Номер паспорта владельца аттестованного идентификатора.
	 */
	public String getPnomer() {
		return pnomer;
	}

	/**
	 * Место (полный адрес) постоянной регистрации.
	 * 
	 * @return Место (полный адрес) постоянной регистрации.
	 */
	public String getRadres() {
		return radres;
	}

	/**
	 * Место (город) постоянной регистрации.
	 * 
	 * @return Место (город) постоянной регистрации.
	 */
	public String getRcity() {
		return rcity;
	}

	/**
	 * Место (страна) постоянной регистрации.
	 * 
	 * @return Место (страна) постоянной регистрации.
	 */
	public String getRcountry() {
		return rcountry;
	}

	/**
	 * Для юридического лица. Банковские реквизиты. Номер расчетного счета.
	 * 
	 * @return Для юридического лица. Банковские реквизиты. Номер расчетного
	 *         счета.
	 */
	public String getRs() {
		return rs;
	}

	/**
	 * Контактная информация владельца аттестованного идентификатора. Адрес
	 * интернет сайта.
	 * 
	 * @return Контактная информация владельца аттестованного идентификатора.
	 *         Адрес интернет сайта.
	 */
	public String getWeb() {
		return web;
	}

	/**
	 * Фактическое местонахождение (почтовый индекс) организации или владельца
	 * аттестованного идентификатора.
	 * 
	 * @return Фактическое местонахождение (почтовый индекс) организации или
	 *         владельца аттестованного идентификатора.
	 */
	public String getZipcode() {
		return zipcode;
	}

	/**
	 * Фактическое местонахождение (адрес) организации или владельца
	 * аттестованного идентификатора.
	 * 
	 * @param adres
	 *            Фактическое местонахождение (адрес) организации или владельца
	 *            аттестованного идентификатора.
	 */
	public void setAdres(String adres) {
		this.adres = adres;
	}

	/**
	 * Для юридического лица. Банковские реквизиты. Название банка.
	 * 
	 * @param bankname
	 *            Для юридического лица. Банковские реквизиты. Название банка.
	 */
	public void setBankname(String bankname) {
		this.bankname = bankname;
	}

	/**
	 * Дата рождения (день).
	 * 
	 * @param bday
	 *            Дата рождения (день).
	 */
	public void setBday(String bday) {
		this.bday = bday;
	}

	/**
	 * Для юридического лица. Банковские реквизиты. БИК.
	 * 
	 * @param bik
	 *            Для юридического лица. Банковские реквизиты. БИК.
	 */
	public void setBik(String bik) {
		this.bik = bik;
	}

	/**
	 * Дата рождения (месяц).
	 * 
	 * @param bmonth
	 *            Дата рождения (месяц).
	 */
	public void setBmonth(String bmonth) {
		this.bmonth = bmonth;
	}

	/**
	 * Место рождения (населенный пункт, страна).
	 * 
	 * @param bplace
	 *            Место рождения (населенный пункт, страна).
	 */
	public void setBplace(String bplace) {
		this.bplace = bplace;
	}

	/**
	 * Для юридического лица. Главный бухгалтер (ФИО).
	 * 
	 * @param buhfio
	 *            Для юридического лица. Главный бухгалтер (ФИО).
	 */
	public void setBuhfio(String buhfio) {
		this.buhfio = buhfio;
	}

	/**
	 * Дата рождения (год).
	 * 
	 * @param byear
	 *            Дата рождения (год).
	 */
	public void setByear(String byear) {
		this.byear = byear;
	}

	/**
	 * Только для аттестатов Capitaller. WMID учредителя.
	 * 
	 * @param cap_owner
	 *            Только для аттестатов Capitaller. WMID учредителя.
	 */
	public void setCap_owner(String cap_owner) {
		this.cap_owner = cap_owner;
	}

	/**
	 * Фактическое местонахождение (город) организации или владельца
	 * аттестованного идентификатора
	 * 
	 * @param city
	 *            Фактическое местонахождение (город) организации или владельца
	 *            аттестованного идентификатора
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * Фактическое местонахождение (страна) организации или владельца
	 * аттестованного идентификатора.
	 * 
	 * @param country
	 *            Фактическое местонахождение (страна) организации или владельца
	 *            аттестованного идентификатора.
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * Статус владельца.
	 * 
	 * @param ctype
	 *            Статус владельца.
	 */
	public void setCtype(String ctype) {
		this.ctype = ctype;
	}

	/**
	 * Для юридического лица. Директор (ФИО).
	 * 
	 * @param dirfio
	 *            Для юридического лица. Директор (ФИО).
	 */
	public void setDirfio(String dirfio) {
		this.dirfio = dirfio;
	}

	/**
	 * Контактная информация владельца аттестованного идентификатора. Адрес
	 * электронной почты.
	 * 
	 * @param email
	 *            Контактная информация владельца аттестованного идентификатора.
	 *            Адрес электронной почты.
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Контактная информация владельца аттестованного идентификатора. Номер
	 * факса.
	 * 
	 * @param fax
	 *            Контактная информация владельца аттестованного идентификатора.
	 *            Номер факса.
	 */
	public void setFax(String fax) {
		this.fax = fax;
	}

	/**
	 * Фамилия владельца аттестованного идентификатора.
	 * 
	 * @param fname
	 *            Фамилия владельца аттестованного идентификатора.
	 */
	public void setFname(String fname) {
		this.fname = fname;
	}

	/**
	 * Контактная информация владельца аттестованного идентификатора. UIN ICQ.
	 * 
	 * @param icq
	 *            Контактная информация владельца аттестованного идентификатора.
	 *            UIN ICQ.
	 */
	public void setIcq(String icq) {
		this.icq = icq;
	}

	/**
	 * Имя владельца аттестованного идентификатора.
	 * 
	 * @param iname
	 *            Имя владельца аттестованного идентификатора.
	 */
	public void setIname(String iname) {
		this.iname = iname;
	}

	/**
	 * Дополнительная информация (направление деятельности, комментарии,
	 * информация рекламного характера.
	 * 
	 * @param infoopen
	 *            Дополнительная информация (направление деятельности,
	 *            комментарии, информация рекламного характера.
	 */
	public void setInfoopen(String infoopen) {
		this.infoopen = infoopen;
	}

	/**
	 * Для юридического лица. ИНН.
	 * 
	 * @param inn
	 *            Для юридического лица. ИНН.
	 */
	public void setInn(String inn) {
		this.inn = inn;
	}

	/**
	 * Признак проверки электронной копии свидетельства о постановке на
	 * налоговый учет администратором центра аттестации.
	 * 
	 * @param inndoc
	 *            Признак проверки электронной копии свидетельства о постановке
	 *            на налоговый учет администратором центра аттестации.
	 */
	public void setInndoc(String inndoc) {
		this.inndoc = inndoc;
	}

	/**
	 * Для юридического лица. Юридический адрес.
	 * 
	 * @param jadres
	 *            Для юридического лица. Юридический адрес.
	 */
	public void setJadres(String jadres) {
		this.jadres = jadres;
	}

	/**
	 * Для юридического лица. Юридический адрес (город).
	 * 
	 * @param jcity
	 *            Для юридического лица. Юридический адрес (город).
	 */
	public void setJcity(String jcity) {
		this.jcity = jcity;
	}

	/**
	 * Для юридического лица. Юридический адрес (страна).
	 * 
	 * @param jcountry
	 *            Для юридического лица. Юридический адрес (страна).
	 */
	public void setJcountry(String jcountry) {
		this.jcountry = jcountry;
	}

	/**
	 * Для юридического лица. Статус (директор, представитель и др.).
	 * 
	 * @param jstatus
	 *            Для юридического лица. Статус (директор, представитель и др.).
	 */
	public void setJstatus(String jstatus) {
		this.jstatus = jstatus;
	}

	/**
	 * Для юридического лица. Юридический адрес (почтовый индекс).
	 * 
	 * @param jzipcode
	 *            Для юридического лица. Юридический адрес (почтовый индекс).
	 */
	public void setJzipcode(String jzipcode) {
		this.jzipcode = jzipcode;
	}

	/**
	 * Для юридического лица. Банковские реквизиты. Номер корреспондентского
	 * счета.
	 * 
	 * @param ks
	 *            Для юридического лица. Банковские реквизиты. Номер
	 *            корреспондентского счета.
	 */
	public void setKs(String ks) {
		this.ks = ks;
	}

	/**
	 * Информация об отзыве аттестата.
	 * 
	 * @param locked
	 *            Информация об отзыве аттестата.
	 */
	public void setLocked(String locked) {
		this.locked = locked;
	}

	/**
	 * Для юридического лица. Название организации.
	 * 
	 * @param name
	 *            Для юридического лица. Название организации.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Название проекта, имя (nick).
	 * 
	 * @param nickname
	 *            Название проекта, имя (nick).
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	/**
	 * Для юридического лица. ОКВЭД.
	 * 
	 * @param okonx
	 *            Для юридического лица. ОКВЭД.
	 */
	public void setOkonx(String okonx) {
		this.okonx = okonx;
	}

	/**
	 * Для юридического лица. ОКПО.
	 * 
	 * @param okpo
	 *            Для юридического лица. ОКПО.
	 */
	public void setOkpo(String okpo) {
		this.okpo = okpo;
	}

	/**
	 * Отчество владельца аттестованного идентификатора.
	 * 
	 * @param oname
	 *            Отчество владельца аттестованного идентификатора.
	 */
	public void setOname(String oname) {
		this.oname = oname;
	}

	/**
	 * Для юридического лица. На основании чего действует(устав, доверенность и
	 * др.).
	 * 
	 * @param osnovainfo
	 *            Для юридического лица. На основании чего действует(устав,
	 *            доверенность и др.).
	 */
	public void setOsnovainfo(String osnovainfo) {
		this.osnovainfo = osnovainfo;
	}

	/**
	 * Признак проверки электронной копии паспорта администратором центра
	 * аттестации. Если pasdoc = 1, то электронная копия проверена
	 * администратором центра аттестации. Если pasdoc = 0, то электронная копия
	 * не проверена администратором центра аттестации.
	 * 
	 * @param pasdoc
	 *            Признак проверки электронной копии паспорта администратором
	 *            центра аттестации.
	 */
	public void setPasdoc(String pasdoc) {
		this.pasdoc = pasdoc;
	}

	/**
	 * Место (кем выдан) выдачи паспорта.
	 * 
	 * @param pbywhom
	 *            Место (кем выдан) выдачи паспорта.
	 */
	public void setPbywhom(String pbywhom) {
		this.pbywhom = pbywhom;
	}

	/**
	 * Место (город) выдачи паспорта.
	 * 
	 * @param pcity
	 *            Место (город) выдачи паспорта.
	 */
	public void setPcity(String pcity) {
		this.pcity = pcity;
	}

	/**
	 * Место (страна) выдачи паспорта.
	 * 
	 * @param pcountry
	 *            Место (страна) выдачи паспорта.
	 */
	public void setPcountry(String pcountry) {
		this.pcountry = pcountry;
	}

	/**
	 * Дата выдачи паспорта ММ/ДД/ГГГГ.
	 * 
	 * @param pdate
	 *            Дата выдачи паспорта ММ/ДД/ГГГГ.
	 */
	public void setPdate(String pdate) {
		this.pdate = pdate;
	}

	/**
	 * Контактная информация владельца аттестованного идентификатора. Номер
	 * контактного телефона.
	 * 
	 * @param phone
	 *            Контактная информация владельца аттестованного идентификатора.
	 *            Номер контактного телефона.
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * Контактная информация владельца аттестованного идентификатора. Номер
	 * домашнего телефона.
	 * 
	 * @param phonehome
	 *            Контактная информация владельца аттестованного идентификатора.
	 *            Номер домашнего телефона.
	 */
	public void setPhonehome(String phonehome) {
		this.phonehome = phonehome;
	}

	/**
	 * Контактная информация владельца аттестованного идентификатора. Номер
	 * мобильного телефона.
	 * 
	 * @param phonemobile
	 *            Контактная информация владельца аттестованного идентификатора.
	 *            Номер мобильного телефона.
	 */
	public void setPhonemobile(String phonemobile) {
		this.phonemobile = phonemobile;
	}

	/**
	 * Номер паспорта владельца аттестованного идентификатора.
	 * 
	 * @param pnomer
	 *            Номер паспорта владельца аттестованного идентификатора.
	 */
	public void setPnomer(String pnomer) {
		this.pnomer = pnomer;
	}

	/**
	 * Место (полный адрес) постоянной регистрации.
	 * 
	 * @param radres
	 *            Место (полный адрес) постоянной регистрации.
	 */
	public void setRadres(String radres) {
		this.radres = radres;
	}

	/**
	 * Место (город) постоянной регистрации.
	 * 
	 * @param rcity
	 *            Место (город) постоянной регистрации.
	 */
	public void setRcity(String rcity) {
		this.rcity = rcity;
	}

	/**
	 * Место (страна) постоянной регистрации.
	 * 
	 * @param rcountry
	 *            Место (страна) постоянной регистрации.
	 */
	public void setRcountry(String rcountry) {
		this.rcountry = rcountry;
	}

	/**
	 * Для юридического лица. Банковские реквизиты. Номер расчетного счета.
	 * 
	 * @param rs
	 *            Для юридического лица. Банковские реквизиты. Номер расчетного
	 *            счета.
	 */
	public void setRs(String rs) {
		this.rs = rs;
	}

	/**
	 * Контактная информация владельца аттестованного идентификатора. Адрес
	 * интернет сайта.
	 * 
	 * @param web
	 *            Контактная информация владельца аттестованного идентификатора.
	 *            Адрес интернет сайта.
	 */
	public void setWeb(String web) {
		this.web = web;
	}

	/**
	 * Фактическое местонахождение (почтовый индекс) организации или владельца
	 * аттестованного идентификатора.
	 * 
	 * @param zipcode
	 *            Фактическое местонахождение (почтовый индекс) организации или
	 *            владельца аттестованного идентификатора.
	 */
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

}
