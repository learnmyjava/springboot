package com.alipaydaikou.until;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

import com.alipaydaikou.until.ReadXmlUtil;

public class ConvertTools {
	/** Mask for bit 0 of a byte. */
	private static final int BIT_0 = 1;

	/** Mask for bit 1 of a byte. */
	private static final int BIT_1 = 0x02;

	/** Mask for bit 2 of a byte. */
	private static final int BIT_2 = 0x04;

	/** Mask for bit 3 of a byte. */
	private static final int BIT_3 = 0x08;

	/** Mask for bit 4 of a byte. */
	private static final int BIT_4 = 0x10;

	/** Mask for bit 5 of a byte. */
	private static final int BIT_5 = 0x20;

	/** Mask for bit 6 of a byte. */
	private static final int BIT_6 = 0x40;

	/** Mask for bit 7 of a byte. */
	private static final int BIT_7 = 0x80;

	private static final int[] BITS = { BIT_0, BIT_1, BIT_2, BIT_3, BIT_4,
			BIT_5, BIT_6, BIT_7 };

	/*
	 * 把16进制字符串转换成字节数组 @param hex @return
	 */
	public static byte[] hexStringToByte(String hex) {

		int len = (hex.length() / 2);
		byte[] result = new byte[len];
		char[] achar = hex.toUpperCase().toCharArray();
		for (int i = 0; i < len; i++) {
			int pos = i * 2;
			result[i] = (byte) (toByte(achar[pos]) << 4 | (toByte(achar[pos + 1])&0xff));
		}
		return result;
	}

	private static byte toByte(char c) {
		return (byte) "0123456789ABCDEF".indexOf(c);
	}

	/**
	 * 把字节数组转换成16进制字符串
	 * 
	 * @param bArray
	 * @return
	 */
	public static final String bytesToHexString(byte[] bArray) {
		StringBuffer sb = new StringBuffer(bArray.length);
		String sTemp;
		for (int i = 0; i < bArray.length; i++) {
			sTemp = Integer.toHexString(0xFF & bArray[i]);
			if (sTemp.length() < 2) {
				sb.append(0);
			}
			Locale locale = new Locale("US-ASCII");
			sb.append(sTemp.toUpperCase(locale));
		}
		return sb.toString();
	}

	/**
	 * 把字节数组转换为对象
	 * 
	 * @param bytes
	 * @return
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static final Object bytesToObject(byte[] bytes) throws IOException,
			ClassNotFoundException {
		ByteArrayInputStream in = new ByteArrayInputStream(bytes);
		ObjectInputStream oi = new ObjectInputStream(in);
		Object o = oi.readObject();
		oi.close();
		return o;
	}

	/**
	 * 把可序列化对象转换成字节数组
	 * 
	 * @param s
	 * @return
	 * @throws IOException
	 */
	public static final byte[] objectToBytes(Serializable s) throws IOException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ObjectOutputStream ot = new ObjectOutputStream(out);
		ot.writeObject(s);
		ot.flush();
		ot.close();
		return out.toByteArray();
	}

	public static final String objectToHexString(Serializable s)
			throws IOException {
		return bytesToHexString(objectToBytes(s));
	}

	public static final Object hexStringToObject(String hex)
			throws IOException, ClassNotFoundException {
		return bytesToObject(hexStringToByte(hex));
	}

	/**
	 * @函数功能: 10进制串转为BCD码
	 * @输入参数: 10进制串
	 * @输出结果: BCD码
	 */
	public static byte[] str2Bcd(String ascii) {
		String asc = ascii;
		int len = asc.length();
		int mod = len % 2;

		if (mod != 0) {
			asc = "0" + asc;
			len = asc.length();
		}

		byte abt[] = null;
		if (len >= 2) {
			len = len / 2;
		}

		byte bbt[] = new byte[len];
		try {
			abt = asc.getBytes("US-ASCII");
		} catch (UnsupportedEncodingException e) {

		}
		int j, k;

		for (int p = 0; p < asc.length() / 2; p++) {
			if ((abt[2 * p] >= '0') && (abt[2 * p] <= '9')) {
				j = abt[2 * p] - '0';
			} else if ((abt[2 * p] >= 'a') && (abt[2 * p] <= 'z')) {
				j = abt[2 * p] - 'a' + 0x0a;
			} else {
				j = abt[2 * p] - 'A' + 0x0a;
			}

			if ((abt[2 * p + 1] >= '0') && (abt[2 * p + 1] <= '9')) {
				k = abt[2 * p + 1] - '0';
			} else if ((abt[2 * p + 1] >= 'a') && (abt[2 * p + 1] <= 'z')) {
				k = abt[2 * p + 1] - 'a' + 0x0a;
			} else {
				k = abt[2 * p + 1] - 'A' + 0x0a;
			}

			int a = (j << 4) + k;
			byte b = (byte) a;
			bbt[p] = b;
		}
		return bbt;
	}

	public static long bytes2int(byte[] resource) {
		if (resource == null) {
			return 0;
		}
		if (resource.length >= 4) {
			return 0;
		}
		int mask = 0xff;
		int temp = 0;
		int res = 0;
		byte[] b = new byte[] { 0, 0, 0, 0 };
		System.arraycopy(resource, 0, b, 4 - resource.length, resource.length);
		for (int i = 0; i < 4; i++) {
			res <<= 8;
			temp = b[i] & mask;
			res |= temp;
		}
		return res;
	}

	public static byte[] int2bytes(int num) {
		byte[] b = new byte[4];
		long value = num;
		for (int i = 0; i < 4; i++) {
			b[i] = (byte) ((value >>> (24 - i * 8)) & 0xFF);
		}
		return b;
	}

	/**
	 * 把字节数组转换成ASCII '0''1'表示字符串,不使用apache的codec的原因是它的bincodec是反过来的
	 * 
	 * @param raw
	 * @return
	 */
	public static final char[] bytesToascii(byte[] raw) {
		if (raw == null || raw.length == 0) {
			return new char[0];
		}
		// get 8 times the bytes with 3 bit shifts to the left of the length
		char[] lascii = new char[raw.length << 3];
		/*
		 * We decr index jj by 8 as we go along to not recompute indices using
		 * multiplication every time inside the loop.
		 */
		for (int ii = raw.length - 1, jj = lascii.length - 1; ii >= 0; ii--, jj -= 8) {
			for (int bits = 0; bits < BITS.length; ++bits) {
				if ((raw[ii] & BITS[bits]) == 0) {
					lascii[jj - bits] = '0';
				} else {
					lascii[jj - bits] = '1';
				}
			}
		}
		return lascii;
	}

	public static final byte[] acsiiTobytes(char[] ascii) {
		if (ascii == null || ascii.length == 0) {
			return new byte[0];
		}
		// get length/8 times bytes with 3 bit shifts to the right of the length
		byte[] lraw = new byte[ascii.length >> 3];
		/*
		 * We decr index jj by 8 as we go along to not recompute indices using
		 * multiplication every time inside the loop.
		 */
		for (int ii = lraw.length - 1, jj = ascii.length - 1; ii >= 0; ii--, jj -= 8) {
			for (int bits = 0; bits < BITS.length; ++bits) {
				if (ascii[jj - bits] == '1') {
					lraw[ii] |= BITS[bits];
				}
			}
		}
		return lraw;

	}

	/**
	 * 组合字节数组.
	 * 
	 * @param bytes
	 *            the bytes
	 * @return the byte[]
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static byte[] combinByteArray(byte[]... bytes) throws IOException {
		ByteArrayOutputStream bos = null;
		try {
			bos = new ByteArrayOutputStream();
			for (byte[] b : bytes) {
				bos.write(b);
			}
			return bos.toByteArray();
		} finally {
			if (bos != null) {
				bos.close();
				bos = null;
			}
		}
	}
	/**
	 * 判断代扣是否成功处理
	 * @param protocolXML
	 * @return
	 */
	public static Map<String, String>  getReturnMap(String protocolXML){
		Map<String, String> map = ReadXmlUtil.ReaderXmlForSAX(protocolXML);
		return map;
	}
	/**
	 * 获取排序的map
	 * @param protocolXML
	 * @return
	 */
	
/*	<?xml version="1.0" encoding="GBK"?>
	<alipay><is_success>T</is_success><request><param name="_input_charset">UTF-8</param>
	<param name="subject">shangdongAliDK</param><param name="sign">qBs8GcWxCmvc3gztdTzDHx+tf0QLs5SIPci7
	CcufEjcE58trLnzq8zmb7yBMu2Jgae1YfdN6VFBrkB38Pv85APcFmc7l5CHhMLzWRQUSqCIh5mAJnU2XoNzJtnc+OInaJ28gwtjMAIOf/D0X
	Keerg5miIyWbomPodA32g3G1sJg=</param><param name="product_code">FUND_TRADE_FAST_PAY</param>
	<param name="buyer_id">2088*****798053</param><param name="body">shangdongAliDK</param>
	<param name="notify_url">http://60.208.85.141:8880/bkg/OBKGDDK1/4311001.dow</param><param name="out_trade_no">
		ALDK20190226105652</param><param name="partner">2088021757584232</param>
		<param name="agreement_info">{"agreement_no":"XXXXXXXXXXXX"}</param>
		<param name="service">alipay.acquire.createandpay</param><param name="total_fee">0.01</param>
		<param name="sign_type">RSA</param></request><response><alipay>
		<detail_error_code>ACCESS_FORBIDDEN</detail_error_code><detail_error_des>
		ACCESS_FORBIDDEN</detail_error_des><display_message>交易失败</display_message>
		<out_trade_no>ALDK20190226105652</out_trade_no><result_code>ORDER_FAIL</result_code>
		</alipay></response><sign>jMLZaswcbPhbQcUpNRwlZxYbnuXYLrII03C/n97IEJ0+wO6xWRxHZnF98seEZ7NiUOQ
		NGlXyal5dOjUB6znuGdZxypn7V3pJaQVERvgD48AstC8faW+COGttlyJ7vk6W/RwYqNvmApE//E0z6McsKSnRR5zjiWHhgq
		mScnnFjR0=</sign><sign_type>RSA</sign_type></alipay>*/
	
	//getSortMap 获取上述xml 中 所有response <alipay> 下的  key =value
	public static Map<String, String> getSortMap(String protocolXML) {
		Map<String, String> map = ReadXmlUtil.ReaderXmlForSAX(protocolXML);
		Map<String, String> mapsign =ReadXmlUtil.getSignMap(map);
		return  sortMapByKey(mapsign);
		
	}
	
	 /**
     * 把数组所有元素排序，并按照“参数=参数值”的模式用“&”字符拼接成字符串
     * 
     * @param params 需要排序并参与字符拼接的参数组
     * @return 拼接后字符串
     */
    public static String createLinkString(Map<String, String> params)
    {
        
        List<String> keys = new ArrayList<String>(params.keySet());
        Collections.sort(keys);
        
        String prestr = "";
        
        for (int i = 0; i < keys.size(); i++)
        {
            String key = keys.get(i);
            String value = params.get(key);
            
            if (i == keys.size() - 1)
            {// 拼接时，不包括最后一个&字符
                prestr = prestr + key + "=" + value;
            }
            else
            {
                prestr = prestr + key + "=" + value + "&";
            }
        }
        
//        log.info("待签名参数:" + prestr);
        return prestr;
    }
    
    
	
	
	/**
	 * map 排序
	 * @param map
	 * @return
	 */
	public static Map<String, String> sortMapByKey(Map<String, String> map) {
		if (map == null || map.isEmpty()) {
			return null;
		}
		Map<String, String> sortMap = new TreeMap<String, String>( new Comparator<String>() {
            public int compare(String obj1, String obj2) {
                
                return obj1.compareTo(obj2);
            }
        });
		sortMap.putAll(map);
		return sortMap;
	}
	
	/**
	 * 获取签名数据
	 * @param map
	 * @return
	 */
	public static String getSignContent(Map<String, String> map){
		
		StringBuffer s =new StringBuffer();
		for (Map.Entry<String, String> entry : map.entrySet()) {
			s.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
		}
		
		return s.substring(0,s.length()-1);
	}
	
	
	public static  String getPayTime(String s) throws ParseException{

		SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d =date.parse(s);
		
		SimpleDateFormat date1 = new SimpleDateFormat("yyyyMMddHHmmss");

		return date1.format(d);
	}
	
	public static Long getLongtxtAmt(String s){
		int f = (int) (Double.parseDouble(s)*100);
		Long f2 = Long.valueOf(String.valueOf(f));
		return f2;
	}
}
