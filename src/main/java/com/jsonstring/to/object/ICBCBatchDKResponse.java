package com.jsonstring.to.object;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author li_hhui
 * @date:2019年6月16日
 * @version:
 */
public class ICBCBatchDKResponse {

	private  ICBCBatchDKHeader HEADER;
	private ICBCBatchDKReponseBody BODY;
	@JSONField(name ="HEADER")
	public ICBCBatchDKHeader getHEADER() {
		return HEADER;
	}
	public void setHEADER(ICBCBatchDKHeader hEADER) {
		HEADER = hEADER;
	}
	@JSONField(name ="BODY")
	public ICBCBatchDKReponseBody getBODY() {
		return BODY;
	}
	public void setBODY(ICBCBatchDKReponseBody bODY) {
		BODY = bODY;
	}
	
	
}
