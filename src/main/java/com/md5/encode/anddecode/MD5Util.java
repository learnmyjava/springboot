package com.md5.encode.anddecode;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MD5Util {  
  
    private final static String[] hexDigits = {"0", "1", "2", "3", "4", "5", "6", "7",
        "8", "9", "a", "b", "c", "d", "e", "f"};
    
    
    
    
    
   /*** 
     * MD5加码 生成32位md5码 
    */  
   public static String string2MD5(String inStr){  
        MessageDigest md5 = null;  
        try{  
           md5 = MessageDigest.getInstance("MD5");  
       }catch (Exception e){  
           System.out.println(e.toString());  
           e.printStackTrace();  
           return "";  
        }  
       char[] charArray = inStr.toCharArray();  
        byte[] byteArray = new byte[charArray.length];  
 
       for (int i = 0; i < charArray.length; i++)  
            byteArray[i] = (byte) charArray[i];  
        byte[] md5Bytes = md5.digest(byteArray);  
       StringBuffer hexValue = new StringBuffer();  
        for (int i = 0; i < md5Bytes.length; i++){  
            int val = ((int) md5Bytes[i]) & 0xff;  
           if (val < 16)  
                hexValue.append("0");  
            hexValue.append(Integer.toHexString(val));  
       }  
        return hexValue.toString();  
 
    }  
 
   public static String MD5Encode(String origin) {
       String resultString = null;
       try {
           resultString = origin;
           MessageDigest md = MessageDigest.getInstance("MD5");
           resultString = byteArrayToHexString(md.digest(resultString.getBytes("UTF-8")));
       } catch (Exception e) {
           e.printStackTrace();
       }
       return resultString;
   }
   
   public static String byteArrayToHexString(byte[] b) {
       StringBuilder resultSb = new StringBuilder();
       for (byte aB : b) {
           resultSb.append(byteToHexString(aB));
       }
       return resultSb.toString();
   }
   
   private static String byteToHexString(byte b) {
       int n = b;
       if (n < 0) {
           n = 256 + n;
       }
       int d1 = n / 16;
       int d2 = n % 16;
       return hexDigits[d1] + hexDigits[d2];
   }
    /** 
    * 加密解密算法 执行一次加密，两次解密 
     */   
    public static String convertMD5(String inStr){  
  
        char[] a = inStr.toCharArray();  
        for (int i = 0; i < a.length; i++){  
            a[i] = (char) (a[i] ^ 't');  
        }  
        String s = new String(a);  
        return s;  
  
    }  
  
 
    public static String getSign(Map<String,Object> map){
        ArrayList<String> list = new ArrayList<String>();
        for(Map.Entry<String,Object> entry:map.entrySet()){
            if(entry.getValue()!=""){
                list.add(entry.getKey() + "=" + entry.getValue() + "&");
            }
        }
        int size = list.size();
        String [] arrayToSort = list.toArray(new String[size]);
        Arrays.sort(arrayToSort, String.CASE_INSENSITIVE_ORDER);
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < size; i ++) {
            sb.append(arrayToSort[i]);
        }
        String result = sb.toString();
        
        result += "key=" +"VzxWnpwjxVJZ2Evz0iIS6hAgLyJ7552z";
        result = MD5.MD5Encode(result).toUpperCase();
        return result;
    }
    
    
    
    public static String cryptcpuMd5(byte[] source)
	  {
	    String s = null;
	    char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
	    try {
	      MessageDigest md = MessageDigest.getInstance("MD5");
	      md.update(source);
	      byte[] tmp = md.digest();

	      char[] str = new char[32];
	      int k = 0;
	      for (int i = 0; i < 16; i++)
	      {
	        byte byte0 = tmp[i];
	        str[(k++)] = hexDigits[(byte0 >>> 4 & 0xF)];

	        str[(k++)] = hexDigits[(byte0 & 0xF)];
	      }
	      s = new String(str);
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
	    return s;
	  }
}  

