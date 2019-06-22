package com.jsonstring.to.object;

import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author li_hhui
 * @date:2019年6月16日
 * @version:
 */
public class ICBCBatchDKBody {

	private String  TOTALAMT;
	private String  TOTALNUM;
	private List<ICBCBatchDKDetail>  DETAIL;
	
	private String SUCAMT;//成功总金额
	private String SUCNUM;//成功总笔数
	
	@JSONField(name ="TOTALAMT")
	public String getTOTALAMT() {
		return TOTALAMT;
	}
	public void setTOTALAMT(String tOTALAMT) {
		TOTALAMT = tOTALAMT;
	}
	@JSONField(name ="TOTALNUM")
	public String getTOTALNUM() {
		return TOTALNUM;
	}
	public void setTOTALNUM(String tOTALNUM) {
		TOTALNUM = tOTALNUM;
	}
	@JSONField(name ="DETAIL")
	public List<ICBCBatchDKDetail> getDETAIL() {
		return DETAIL;
	}
	public void setDETAIL(List<ICBCBatchDKDetail> dETAIL) {
		DETAIL = dETAIL;
	}
	@JSONField(name ="SUCAMT")
	public String getSUCAMT() {
		return SUCAMT;
	}
	public void setSUCAMT(String sUCAMT) {
		SUCAMT = sUCAMT;
	}
	@JSONField(name ="SUCNUM")
	public String getSUCNUM() {
		return SUCNUM;
	}
	public void setSUCNUM(String sUCNUM) {
		SUCNUM = sUCNUM;
	}
	
	
	
}
