package com.rsa.encode.anddecode;  


public class MainTest {  
  
    public static void main(String[] args) throws Exception {  
        String filepath="F:/RSAkey-produce/";  
  
      RSAEncrypt.genKeyPair(filepath);  
          
          
       /* System.out.println("--------------公钥加密私钥解密过程-------------------");  
        String plainText="这是测试原文_公钥加密私钥解密";  
        //公钥加密过程  
        byte[] cipherData=RSAEncrypt.encrypt(RSAEncrypt.loadPublicKeyByStr(RSAEncrypt.loadPublicKeyByFile(filepath)),plainText.getBytes());  
        String cipher=Base64.encode(cipherData);  
        //私钥解密过程  
        byte[] res=RSAEncrypt.decrypt(RSAEncrypt.loadPrivateKeyByStr(RSAEncrypt.loadPrivateKeyByFile(filepath)), Base64.decode(cipher));  
        String restr=new String(res);  
        System.out.println("原文："+plainText);  
        System.out.println("密文："+cipher);  
        System.out.println("解密："+restr);  */
         
        System.out.println("--------------私钥加密公钥解密过程-------------------");  
        String plainText="这是测试原_私钥加密公钥解密";  
        //私钥加密过程  
        byte[] cipherData=RSAEncrypt.encrypt(RSAEncrypt.loadPrivateKeyByStr(RSAEncrypt.loadPrivateKeyByFile(filepath)),plainText.getBytes());  
        String cipher=Base64.encode(cipherData);  
        //公钥解密过程  
        byte[] res=RSAEncrypt.decrypt(RSAEncrypt.loadPublicKeyByStr(RSAEncrypt.loadPublicKeyByFile(filepath)), Base64.decode(cipher));  
        String restr=new String(res);  
        System.out.println("原文："+plainText);  
        System.out.println("密文："+cipher);  
        System.out.println("解密："+restr);  
        System.out.println();  
          
        
        Student s = new Student();
		s.setAge(11);
		s.setName("lihonghui");
		s.setId("no1233");
		String content=s.toString();
		
		
        System.out.println("---------------私钥计算签名------------------");  
//        String content="ihep_这是用于签名的原始数据";  
        String signstr=RSASignature.sign(content,RSAEncrypt.loadPrivateKeyByFile(filepath));  
        System.out.println("签名原串："+content);  
        System.out.println("签名串："+signstr);  
          
        System.out.println("---------------公钥校验签名------------------");  
        System.out.println("签名原串："+content); //接收端按照升降规则组成待验签串 
        System.out.println("签名串："+signstr);  
        System.out.println("验签结果："+RSASignature.verify(content, signstr, RSAEncrypt.loadPublicKeyByFile(filepath)));  
        
        
       
          
       
           
        
       
        
        
    }  
}  
