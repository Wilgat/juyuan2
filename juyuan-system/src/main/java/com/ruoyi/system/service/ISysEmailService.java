package com.ruoyi.system.service;

public interface ISysEmailService {
  void sendVerificationCode(String paramString1, String paramString2);
  
  void sendEmail(String[] paramArrayOfString, String paramString1, String paramString2);
}


/* Location:              /home/adm01/Music/juyuan-system-3.8.6/!/com/ruoyi/system/service/ISysEmailService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */