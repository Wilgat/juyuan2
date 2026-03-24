/*     */ package com.ruoyi.common.utils.uuid;
/*     */ 
/*     */ import com.ruoyi.common.exception.UtilException;
/*     */ import java.io.Serializable;
/*     */ import java.security.MessageDigest;
/*     */ import java.security.NoSuchAlgorithmException;
/*     */ import java.security.SecureRandom;
/*     */ import java.util.Random;
/*     */ import java.util.concurrent.ThreadLocalRandom;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class UUID
/*     */   implements Serializable, Comparable<UUID>
/*     */ {
/*     */   private static final long serialVersionUID = -1185015143654744140L;
/*     */   private final long mostSigBits;
/*     */   private final long leastSigBits;
/*     */   
/*     */   private static class Holder
/*     */   {
/*  25 */     static final SecureRandom numberGenerator = UUID.getSecureRandom();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private UUID(byte[] data) {
/*  41 */     long msb = 0L;
/*  42 */     long lsb = 0L;
/*  43 */     assert data.length == 16 : "data must be 16 bytes in length"; int i;
/*  44 */     for (i = 0; i < 8; i++)
/*     */     {
/*  46 */       msb = msb << 8L | (data[i] & 0xFF);
/*     */     }
/*  48 */     for (i = 8; i < 16; i++)
/*     */     {
/*  50 */       lsb = lsb << 8L | (data[i] & 0xFF);
/*     */     }
/*  52 */     this.mostSigBits = msb;
/*  53 */     this.leastSigBits = lsb;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public UUID(long mostSigBits, long leastSigBits) {
/*  64 */     this.mostSigBits = mostSigBits;
/*  65 */     this.leastSigBits = leastSigBits;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static UUID fastUUID() {
/*  75 */     return randomUUID(false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static UUID randomUUID() {
/*  85 */     return randomUUID(true);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static UUID randomUUID(boolean isSecure) {
/*  96 */     Random ng = isSecure ? Holder.numberGenerator : getRandom();
/*     */     
/*  98 */     byte[] randomBytes = new byte[16];
/*  99 */     ng.nextBytes(randomBytes);
/* 100 */     randomBytes[6] = (byte)(randomBytes[6] & 0xF);
/* 101 */     randomBytes[6] = (byte)(randomBytes[6] | 0x40);
/* 102 */     randomBytes[8] = (byte)(randomBytes[8] & 0x3F);
/* 103 */     randomBytes[8] = (byte)(randomBytes[8] | 0x80);
/* 104 */     return new UUID(randomBytes);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static UUID nameUUIDFromBytes(byte[] name) {
/*     */     MessageDigest md;
/*     */     try {
/* 119 */       md = MessageDigest.getInstance("MD5");
/*     */     }
/* 121 */     catch (NoSuchAlgorithmException nsae) {
/*     */       
/* 123 */       throw new InternalError("MD5 not supported");
/*     */     } 
/* 125 */     byte[] md5Bytes = md.digest(name);
/* 126 */     md5Bytes[6] = (byte)(md5Bytes[6] & 0xF);
/* 127 */     md5Bytes[6] = (byte)(md5Bytes[6] | 0x30);
/* 128 */     md5Bytes[8] = (byte)(md5Bytes[8] & 0x3F);
/* 129 */     md5Bytes[8] = (byte)(md5Bytes[8] | 0x80);
/* 130 */     return new UUID(md5Bytes);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static UUID fromString(String name) {
/* 143 */     String[] components = name.split("-");
/* 144 */     if (components.length != 5)
/*     */     {
/* 146 */       throw new IllegalArgumentException("Invalid UUID string: " + name);
/*     */     }
/* 148 */     for (int i = 0; i < 5; i++)
/*     */     {
/* 150 */       components[i] = "0x" + components[i];
/*     */     }
/*     */     
/* 153 */     long mostSigBits = Long.decode(components[0]).longValue();
/* 154 */     mostSigBits <<= 16L;
/* 155 */     mostSigBits |= Long.decode(components[1]).longValue();
/* 156 */     mostSigBits <<= 16L;
/* 157 */     mostSigBits |= Long.decode(components[2]).longValue();
/*     */     
/* 159 */     long leastSigBits = Long.decode(components[3]).longValue();
/* 160 */     leastSigBits <<= 48L;
/* 161 */     leastSigBits |= Long.decode(components[4]).longValue();
/*     */     
/* 163 */     return new UUID(mostSigBits, leastSigBits);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long getLeastSignificantBits() {
/* 173 */     return this.leastSigBits;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long getMostSignificantBits() {
/* 183 */     return this.mostSigBits;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int version() {
/* 202 */     return (int)(this.mostSigBits >> 12L & 0xFL);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int variant() {
/* 225 */     return (int)(this.leastSigBits >>> (int)(64L - (this.leastSigBits >>> 62L)) & this.leastSigBits >> 63L);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long timestamp() throws UnsupportedOperationException {
/* 243 */     checkTimeBase();
/* 244 */     return (this.mostSigBits & 0xFFFL) << 48L | (this.mostSigBits >> 16L & 0xFFFFL) << 32L | this.mostSigBits >>> 32L;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int clockSequence() throws UnsupportedOperationException {
/* 264 */     checkTimeBase();
/* 265 */     return (int)((this.leastSigBits & 0x3FFF000000000000L) >>> 48L);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long node() throws UnsupportedOperationException {
/* 283 */     checkTimeBase();
/* 284 */     return this.leastSigBits & 0xFFFFFFFFFFFFL;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 314 */     return toString(false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString(boolean isSimple) {
/* 343 */     StringBuilder builder = new StringBuilder(isSimple ? 32 : 36);
/*     */     
/* 345 */     builder.append(digits(this.mostSigBits >> 32L, 8));
/* 346 */     if (!isSimple)
/*     */     {
/* 348 */       builder.append('-');
/*     */     }
/*     */     
/* 351 */     builder.append(digits(this.mostSigBits >> 16L, 4));
/* 352 */     if (!isSimple)
/*     */     {
/* 354 */       builder.append('-');
/*     */     }
/*     */     
/* 357 */     builder.append(digits(this.mostSigBits, 4));
/* 358 */     if (!isSimple)
/*     */     {
/* 360 */       builder.append('-');
/*     */     }
/*     */     
/* 363 */     builder.append(digits(this.leastSigBits >> 48L, 4));
/* 364 */     if (!isSimple)
/*     */     {
/* 366 */       builder.append('-');
/*     */     }
/*     */     
/* 369 */     builder.append(digits(this.leastSigBits, 12));
/*     */     
/* 371 */     return builder.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 382 */     long hilo = this.mostSigBits ^ this.leastSigBits;
/* 383 */     return (int)(hilo >> 32L) ^ (int)hilo;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object obj) {
/* 398 */     if (null == obj || obj.getClass() != UUID.class)
/*     */     {
/* 400 */       return false;
/*     */     }
/* 402 */     UUID id = (UUID)obj;
/* 403 */     return (this.mostSigBits == id.mostSigBits && this.leastSigBits == id.leastSigBits);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int compareTo(UUID val) {
/* 424 */     return (this.mostSigBits < val.mostSigBits) ? -1 : ((this.mostSigBits > val.mostSigBits) ? 1 : ((this.leastSigBits < val.leastSigBits) ? -1 : ((this.leastSigBits > val.leastSigBits) ? 1 : 0)));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static String digits(long val, int digits) {
/* 442 */     long hi = 1L << digits * 4;
/* 443 */     return Long.toHexString(hi | val & hi - 1L).substring(1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void checkTimeBase() {
/* 451 */     if (version() != 1)
/*     */     {
/* 453 */       throw new UnsupportedOperationException("Not a time-based UUID");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static SecureRandom getSecureRandom() {
/*     */     try {
/* 466 */       return SecureRandom.getInstance("SHA1PRNG");
/*     */     }
/* 468 */     catch (NoSuchAlgorithmException e) {
/*     */       
/* 470 */       throw new UtilException(e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static ThreadLocalRandom getRandom() {
/* 482 */     return ThreadLocalRandom.current();
/*     */   }
/*     */ }


/* Location:              /home/adm01/Music/juyuan-common-3.8.6/!/com/ruoyi/common/utils/uuid/UUID.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */