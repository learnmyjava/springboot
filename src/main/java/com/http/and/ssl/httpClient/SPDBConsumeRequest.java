package com.http.and.ssl.httpClient;

/**
 * @author li_hhui
 * @date:2019年5月13日
 * @version:
 * 浦发代收请求报文
 */
public class SPDBConsumeRequest {


	private String bank_abbr;//银行简称
	private String partner_id;//合作商户编号
	private String out_partner_id;//银行商户号
	private String tid_no;//终端号
	private String order_id;//订单号
	private String refund_order_id;//退款订单号
	private String txnAmt;//交易金额
	private String refund_txnAmt;//退款金额
	private String dk_agreement_no;//用户协议号
	private String tid_seq;//交易流水号
	
	private String sign_type;
	private String sign;
	private String charset;
	private String bg_notify_url;//后台通知地址

	private String certif_type;//证件类型
	private String certifId;//证件号码
	private String customer_name;//姓名
	private String phoneNo;//手机号
	private String bankNo;

	public String getBankNo() {
		return bankNo;
	}

	public void setBankNo(String bankNo) {
		this.bankNo = bankNo;
	}

	public String getCertif_type() {
		return certif_type;
	}

	public void setCertif_type(String certif_type) {
		this.certif_type = certif_type;
	}

	public String getCertifId() {
		return certifId;
	}

	public void setCertifId(String certifId) {
		this.certifId = certifId;
	}

	public String getCustomer_name() {
		return customer_name;
	}

	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getBg_notify_url() {
		return bg_notify_url;
	}

	public void setBg_notify_url(String bg_notify_url) {
		this.bg_notify_url = bg_notify_url;
	}

	public String getBank_abbr() {
		return bank_abbr;
	}

	public void setBank_abbr(String bank_abbr) {
		this.bank_abbr = bank_abbr;
	}

	public String getPartner_id() {
		return partner_id;
	}

	public void setPartner_id(String partner_id) {
		this.partner_id = partner_id;
	}

	public String getOut_partner_id() {
		return out_partner_id;
	}

	public void setOut_partner_id(String out_partner_id) {
		this.out_partner_id = out_partner_id;
	}

	public String getTid_no() {
		return tid_no;
	}

	public void setTid_no(String tid_no) {
		this.tid_no = tid_no;
	}

	public String getOrder_id() {
		return order_id;
	}

	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}

	public String getRefund_order_id() {
		return refund_order_id;
	}

	public void setRefund_order_id(String refund_order_id) {
		this.refund_order_id = refund_order_id;
	}

	public String getTxnAmt() {
		return txnAmt;
	}

	public void setTxnAmt(String txnAmt) {
		this.txnAmt = txnAmt;
	}

	public String getRefund_txnAmt() {
		return refund_txnAmt;
	}

	public void setRefund_txnAmt(String refund_txnAmt) {
		this.refund_txnAmt = refund_txnAmt;
	}

	public String getDk_agreement_no() {
		return dk_agreement_no;
	}

	public void setDk_agreement_no(String dk_agreement_no) {
		this.dk_agreement_no = dk_agreement_no;
	}

	public String getTid_seq() {
		return tid_seq;
	}

	public void setTid_seq(String tid_seq) {
		this.tid_seq = tid_seq;
	}

	public String getSign_type() {
		return sign_type;
	}

	public void setSign_type(String sign_type) {
		this.sign_type = sign_type;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getCharset() {
		return charset;
	}

	public void setCharset(String charset) {
		this.charset = charset;
	}



}
