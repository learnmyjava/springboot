package com.jsonstring.to.object;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author li_hhui
 * @date:2019年6月16日
 * @version:
 */
public class ICBCBatchDKReponseBody {

	private String RETCODE;
	private String RETMSG;
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
	
	
}
