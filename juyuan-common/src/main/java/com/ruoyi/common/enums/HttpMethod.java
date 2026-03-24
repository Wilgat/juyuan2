/*    */ package com.ruoyi.common.enums;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import org.springframework.lang.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
public enum HttpMethod {
    GET, HEAD, POST, PUT, PATCH, DELETE, OPTIONS, TRACE;

    // Move declaration BEFORE the static block
    private static final Map<String, HttpMethod> mappings = new HashMap<>(16);

    static {
        for (HttpMethod httpMethod : values()) {
            mappings.put(httpMethod.name(), httpMethod);
        }
    }

    @Nullable
    public static HttpMethod resolve(@Nullable String method) {
        return (method != null) ? mappings.get(method) : null;
    }

    public boolean matches(String method) {
        return (this == resolve(method));
    }
}


/* Location:              /home/adm01/Music/juyuan-common-3.8.6/!/com/ruoyi/common/enums/HttpMethod.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */