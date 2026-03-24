/*     */ package com.ruoyi.framework.web.domain;
/*     */ 
/*     */ import com.ruoyi.common.utils.Arith;
/*     */ import com.ruoyi.common.utils.ip.IpUtils;
/*     */ import com.ruoyi.framework.web.domain.server.Cpu;
/*     */ import com.ruoyi.framework.web.domain.server.Jvm;
/*     */ import com.ruoyi.framework.web.domain.server.Mem;
/*     */ import com.ruoyi.framework.web.domain.server.Sys;
/*     */ import com.ruoyi.framework.web.domain.server.SysFile;
/*     */ import java.net.UnknownHostException;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import java.util.Properties;
/*     */ import oshi.SystemInfo;
/*     */ import oshi.hardware.CentralProcessor;
/*     */ import oshi.hardware.GlobalMemory;
/*     */ import oshi.hardware.HardwareAbstractionLayer;
/*     */ import oshi.software.os.FileSystem;
/*     */ import oshi.software.os.OSFileStore;
/*     */ import oshi.software.os.OperatingSystem;
/*     */ import oshi.util.Util;
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
/*     */ public class Server
/*     */ {
/*     */   private static final int OSHI_WAIT_SECOND = 1000;
/*  36 */   private Cpu cpu = new Cpu();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  41 */   private Mem mem = new Mem();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  46 */   private Jvm jvm = new Jvm();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  51 */   private Sys sys = new Sys();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  56 */   private List<SysFile> sysFiles = new LinkedList<>();
/*     */ 
/*     */   
/*     */   public Cpu getCpu() {
/*  60 */     return this.cpu;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCpu(Cpu cpu) {
/*  65 */     this.cpu = cpu;
/*     */   }
/*     */ 
/*     */   
/*     */   public Mem getMem() {
/*  70 */     return this.mem;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMem(Mem mem) {
/*  75 */     this.mem = mem;
/*     */   }
/*     */ 
/*     */   
/*     */   public Jvm getJvm() {
/*  80 */     return this.jvm;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setJvm(Jvm jvm) {
/*  85 */     this.jvm = jvm;
/*     */   }
/*     */ 
/*     */   
/*     */   public Sys getSys() {
/*  90 */     return this.sys;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSys(Sys sys) {
/*  95 */     this.sys = sys;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<SysFile> getSysFiles() {
/* 100 */     return this.sysFiles;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSysFiles(List<SysFile> sysFiles) {
/* 105 */     this.sysFiles = sysFiles;
/*     */   }
/*     */ 
/*     */   
/*     */   public void copyTo() throws Exception {
/* 110 */     SystemInfo si = new SystemInfo();
/* 111 */     HardwareAbstractionLayer hal = si.getHardware();
/*     */     
/* 113 */     setCpuInfo(hal.getProcessor());
/*     */     
/* 115 */     setMemInfo(hal.getMemory());
/*     */     
/* 117 */     setSysInfo();
/*     */     
/* 119 */     setJvmInfo();
/*     */     
/* 121 */     setSysFiles(si.getOperatingSystem());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void setCpuInfo(CentralProcessor processor) {
/* 130 */     long[] prevTicks = processor.getSystemCpuLoadTicks();
/* 131 */     Util.sleep(1000L);
/* 132 */     long[] ticks = processor.getSystemCpuLoadTicks();
/* 133 */     long nice = ticks[CentralProcessor.TickType.NICE.getIndex()] - prevTicks[CentralProcessor.TickType.NICE.getIndex()];
/* 134 */     long irq = ticks[CentralProcessor.TickType.IRQ.getIndex()] - prevTicks[CentralProcessor.TickType.IRQ.getIndex()];
/* 135 */     long softirq = ticks[CentralProcessor.TickType.SOFTIRQ.getIndex()] - prevTicks[CentralProcessor.TickType.SOFTIRQ.getIndex()];
/* 136 */     long steal = ticks[CentralProcessor.TickType.STEAL.getIndex()] - prevTicks[CentralProcessor.TickType.STEAL.getIndex()];
/* 137 */     long cSys = ticks[CentralProcessor.TickType.SYSTEM.getIndex()] - prevTicks[CentralProcessor.TickType.SYSTEM.getIndex()];
/* 138 */     long user = ticks[CentralProcessor.TickType.USER.getIndex()] - prevTicks[CentralProcessor.TickType.USER.getIndex()];
/* 139 */     long iowait = ticks[CentralProcessor.TickType.IOWAIT.getIndex()] - prevTicks[CentralProcessor.TickType.IOWAIT.getIndex()];
/* 140 */     long idle = ticks[CentralProcessor.TickType.IDLE.getIndex()] - prevTicks[CentralProcessor.TickType.IDLE.getIndex()];
/* 141 */     long totalCpu = user + nice + cSys + idle + iowait + irq + softirq + steal;
/* 142 */     this.cpu.setCpuNum(processor.getLogicalProcessorCount());
/* 143 */     this.cpu.setTotal(totalCpu);
/* 144 */     this.cpu.setSys(cSys);
/* 145 */     this.cpu.setUsed(user);
/* 146 */     this.cpu.setWait(iowait);
/* 147 */     this.cpu.setFree(idle);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void setMemInfo(GlobalMemory memory) {
/* 155 */     this.mem.setTotal(memory.getTotal());
/* 156 */     this.mem.setUsed(memory.getTotal() - memory.getAvailable());
/* 157 */     this.mem.setFree(memory.getAvailable());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void setSysInfo() {
/* 165 */     Properties props = System.getProperties();
/* 166 */     this.sys.setComputerName(IpUtils.getHostName());
/* 167 */     this.sys.setComputerIp(IpUtils.getHostIp());
/* 168 */     this.sys.setOsName(props.getProperty("os.name"));
/* 169 */     this.sys.setOsArch(props.getProperty("os.arch"));
/* 170 */     this.sys.setUserDir(props.getProperty("user.dir"));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void setJvmInfo() throws UnknownHostException {
/* 178 */     Properties props = System.getProperties();
/* 179 */     this.jvm.setTotal(Runtime.getRuntime().totalMemory());
/* 180 */     this.jvm.setMax(Runtime.getRuntime().maxMemory());
/* 181 */     this.jvm.setFree(Runtime.getRuntime().freeMemory());
/* 182 */     this.jvm.setVersion(props.getProperty("java.version"));
/* 183 */     this.jvm.setHome(props.getProperty("java.home"));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void setSysFiles(OperatingSystem os) {
/* 191 */     FileSystem fileSystem = os.getFileSystem();
/* 192 */     List<OSFileStore> fsArray = fileSystem.getFileStores();
/* 193 */     for (OSFileStore fs : fsArray) {
/*     */       
/* 195 */       long free = fs.getUsableSpace();
/* 196 */       long total = fs.getTotalSpace();
/* 197 */       long used = total - free;
/* 198 */       SysFile sysFile = new SysFile();
/* 199 */       sysFile.setDirName(fs.getMount());
/* 200 */       sysFile.setSysTypeName(fs.getType());
/* 201 */       sysFile.setTypeName(fs.getName());
/* 202 */       sysFile.setTotal(convertFileSize(total));
/* 203 */       sysFile.setFree(convertFileSize(free));
/* 204 */       sysFile.setUsed(convertFileSize(used));
/* 205 */       sysFile.setUsage(Arith.mul(Arith.div(used, total, 4), 100.0D));
/* 206 */       this.sysFiles.add(sysFile);
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
/*     */   public String convertFileSize(long size) {
/* 218 */     long kb = 1024L;
/* 219 */     long mb = kb * 1024L;
/* 220 */     long gb = mb * 1024L;
/* 221 */     if (size >= gb)
/*     */     {
/* 223 */       return String.format("%.1f GB", new Object[] { Float.valueOf((float)size / (float)gb) });
/*     */     }
/* 225 */     if (size >= mb) {
/*     */       
/* 227 */       float f = (float)size / (float)mb;
/* 228 */       return String.format((f > 100.0F) ? "%.0f MB" : "%.1f MB", new Object[] { Float.valueOf(f) });
/*     */     } 
/* 230 */     if (size >= kb) {
/*     */       
/* 232 */       float f = (float)size / (float)kb;
/* 233 */       return String.format((f > 100.0F) ? "%.0f KB" : "%.1f KB", new Object[] { Float.valueOf(f) });
/*     */     } 
/*     */ 
/*     */     
/* 237 */     return String.format("%d B", new Object[] { Long.valueOf(size) });
/*     */   }
/*     */ }


/* Location:              /home/adm01/Music/juyuan-framework-3.8.6/!/com/ruoyi/framework/web/domain/Server.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */