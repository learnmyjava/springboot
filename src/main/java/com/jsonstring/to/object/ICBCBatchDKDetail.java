package com.jsonstring.to.object;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author li_hhui
 * @date:2019年6月16日
 * @version:
 */
public class ICBCBatchDKDetail {
	
	private String  ORDERNO;
	private String  CARNO;
	private String  AMOUNT;
	private String  SIGNNO;
	private String ACTAMT;
	private String RETCODE;
	private String RETMSG;
	private String NOTE;
	
	
	@JSONField(name ="ORDERNO")
	public String getORDERNO() {
		return ORDERNO;
	}
	public void setORDERNO(String oRDERNO) {
		ORDERNO = oRDERNO;
	}
	@JSONField(name ="CARNO")
	public String getCARNO() {
		return CARNO;
	}
	public void setCARNO(String cARNO) {
		CARNO = cARNO;
	}
	@JSONField(name ="AMOUNT")
	public String getAMOUNT() {
		return AMOUNT;
	}
	public void setAMOUNT(String aMOUNT) {
		AMOUNT = aMOUNT;
	}
	@JSONField(name ="SIGNNO")
	public String getSIGNNO() {
		return SIGNNO;
	}
	public void setSIGNNO(String sIGNNO) {
		SIGNNO = sIGNNO;
	}
	@JSONField(name ="ACTAMT")
	public String getACTAMT() {
		return ACTAMT;
	}
	public void setACTAMT(String aCTAMT) {
		ACTAMT = aCTAMT;
	}
	@JSONField(name ="RETCODE")
	public String getRETCODE() {
		return RETCODE;
	}
	public void setRETCODE(String rETCODE) {
		RETCODE = rETCODE;
	}
	@JSONField(name ="RETMSG")
	public String getRETMSG() {
		return RETMSG;
	}
	public void setRETMSG(String rETMSG) {
		RETMSG = rETMSG;
	}
	@JSONField(name ="NOTE")
	public String getNOTE() {
		return NOTE;
	}
	public void setNOTE(String nOTE) {
		NOTE = nOTE;
	}
	
	
}
