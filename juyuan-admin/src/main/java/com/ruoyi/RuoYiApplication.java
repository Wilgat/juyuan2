/*    */ package com.ruoyi;
/*    */ 
/*    */ import org.springframework.boot.SpringApplication;
/*    */ import org.springframework.boot.autoconfigure.SpringBootApplication;
/*    */ import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
/*    */ public class RuoYiApplication
/*    */ {
/*    */   public static void main(String[] args) {
/* 16 */     SpringApplication.run(RuoYiApplication.class, args);
/* 17 */     System.out.println("(♥◠‿◠)ﾉﾞ  若依启动成功   ლ(´ڡ`ლ)ﾞ  \n .-------.       ____     __        \n |  _ _   \\      \\   \\   /  /    \n | ( ' )  |       \\  _. /  '       \n |(_ o _) /        _( )_ .'         \n | (_,_).' __  ___(_ o _)'          \n |  |\\ \\  |  ||   |(_,_)'         \n |  | \\ `'   /|   `-'  /           \n |  |  \\    /  \\      /           \n ''-'   `'-'    `-..-'              ");
/*    */   }
/*    */ }


/* Location:              /home/adm01/Music/BOOT-INF/classes/!/com/ruoyi/RuoYiApplication.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */