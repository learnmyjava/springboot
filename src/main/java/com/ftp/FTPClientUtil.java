package com.ftp;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.SocketException;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

public class FTPClientUtil
{
  private String ftpHost;
  private int ftpPort;
  private String userName;
  private String password;
  private boolean binaryTransfer = true;
  private boolean passiveMode = true;
  private int clientTimeout = 30000;

  public FTPClientUtil(String ftpHost, String ftpPort, String userName, String password) {
    this.ftpHost = ftpHost;
    this.ftpPort = Integer.valueOf(ftpPort).intValue();
    this.userName = userName;
    this.password = password;
  }

  public FTPClient getFTPClient()
  {
    FTPClient ftpClient = new FTPClient();
    ftpClient.setControlEncoding("UTF-8");

    connect(ftpClient);

    if (this.passiveMode) {
      ftpClient.enterLocalPassiveMode();
    }
    setFileType(ftpClient);
    try
    {
      ftpClient.setSoTimeout(this.clientTimeout);
    } catch (SocketException e) {
    	e.printStackTrace();
    }

    return ftpClient;
  }

  public void setFileType(FTPClient ftpClient)
  {
    try
    {
      if (this.binaryTransfer)
        ftpClient.setFileType(2);
      else
        ftpClient.setFileType(0);
    }
    catch (IOException e) {
    	e.printStackTrace();
    }
  }

  public boolean connect(FTPClient ftpClient)
  {
    try
    {
      ftpClient.connect(this.ftpHost, this.ftpPort);

      int reply = ftpClient.getReplyCode();

      if (FTPReply.isPositiveCompletion(reply))
      {
        if (ftpClient.login(this.userName, this.password)) {
          setFileType(ftpClient);
          return true;
        }
      } else {
        ftpClient.disconnect();
        System.out.println("FTP server refused connection.");
      }
    } catch (IOException e) {
      if (ftpClient.isConnected()) {
        try {
          ftpClient.disconnect();
        } catch (IOException e1) {
        	e.printStackTrace();
        }
      }

      e.printStackTrace();
    }
    return false;
  }

  public void disconnect()
  {
    try
    {
      FTPClient ftpClient = getFTPClient();
      ftpClient.logout();
      if (ftpClient.isConnected()) {
        ftpClient.disconnect();
        ftpClient = null;
      }
    } catch (IOException e) {
    	e.printStackTrace();
    }
  }

  public boolean putFile(String remoteAbsoluteFile, String localAbsoluteFile)
  {
    return put(remoteAbsoluteFile, localAbsoluteFile, true);
  }

  public boolean put(String remoteAbsoluteFile, String localAbsoluteFile, boolean autoClose)
  {
    InputStream input = null;
    try
    {
      input = new FileInputStream(localAbsoluteFile);
      boolean result = getFTPClient()
        .storeFile(remoteAbsoluteFile, input);
      boolean bool1 = result;
      return bool1;
    }
    catch (FileNotFoundException e) {
    	e.printStackTrace();
    } catch (IOException e) {
    	e.printStackTrace();
    } finally {
      try {
        if (input != null)
          input.close();
      }
      catch (Exception e) {
    	  e.printStackTrace();
      }
      if (autoClose) {
        disconnect();
      }
    }

    return false;
  }

  public boolean getFile(String remoteAbsoluteFile, String localAbsoluteFile)
  {
    return get(remoteAbsoluteFile, localAbsoluteFile, true);
  }

  public boolean get(String remoteAbsoluteFile, String localAbsoluteFile, boolean autoClose)
  {
    OutputStream output = null;
    try {
      output = new FileOutputStream(localAbsoluteFile);
      boolean bool = get(remoteAbsoluteFile, output, autoClose);
      return bool;
    } catch (FileNotFoundException e) {
    	e.printStackTrace();
    } finally {
      try {
        if (output != null)
          output.close();
      }
      catch (IOException e) {
    	 e.printStackTrace();
      }
    }

    return false;
  }

  public boolean get(String remoteAbsoluteFile, OutputStream output, boolean autoClose)
  {
    try
    {
      FTPClient ftpClient = getFTPClient();

      boolean bool = ftpClient.retrieveFile(remoteAbsoluteFile, output);
      return bool;
    } catch (IOException e) {
         e.printStackTrace();
    } finally {
      if (autoClose) {
        disconnect();
      }
    }

    return false;
  }
}
