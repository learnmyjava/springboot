package com.jsonstring.to.object;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author li_hhui
 * @date:2019年6月16日
 * @version:
 */
public class ICBCBatchDKHeader {

	private String REQFLAG;
	private String TRCODE;
	private String TRDATE;
	private String REQNO;
	private String CORPNO;
	
	@JSONField(name ="REQFLAG")
	public String getREQFLAG() {
		return REQFLAG;
	}
	public void setREQFLAG(String rEQFLAG) {
		REQFLAG = rEQFLAG;
	}
	@JSONField(name ="TRCODE")
	public String getTRCODE() {
		return TRCODE;
	}
	public void setTRCODE(String tRCODE) {
		TRCODE = tRCODE;
	}
	@JSONField(name ="TRDATE")
	public String getTRDATE() {
		return TRDATE;
	}
	public void setTRDATE(String tRDATE) {
		TRDATE = tRDATE;
	}
	@JSONField(name ="REQNO")
	public String getREQNO() {
		return REQNO;
	}
	public void setREQNO(String rEQNO) {
		REQNO = rEQNO;
	}
	@JSONField(name ="CORPNO")
	public String getCORPNO() {
		return CORPNO;
	}
	public void setCORPNO(String cORPNO) {
		CORPNO = cORPNO;
	}
	
	
	
}
