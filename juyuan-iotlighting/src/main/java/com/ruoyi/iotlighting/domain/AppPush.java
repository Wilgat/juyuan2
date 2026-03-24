package com.ruoyi.iotlighting.domain;

public class AppPush {
  private String cid;
  private String title;
  private String content;
  private String requestId;

  // All setters (preserved exactly as in the decompiled file)
  public void setCid(String cid) { this.cid = cid; }
  public void setTitle(String title) { this.title = title; }
  public void setContent(String content) { this.content = content; }
  public void setRequestId(String requestId) { this.requestId = requestId; }

  public boolean equals(Object o) { 
    if (o == this) return true;  
    if (!(o instanceof AppPush)) return false;  
    AppPush other = (AppPush)o; 
    if (!other.canEqual(this)) return false;  

    Object this$cid = getCid(), other$cid = other.getCid(); 
    if ((this$cid == null) ? (other$cid != null) : !this$cid.equals(other$cid)) return false;  

    Object this$title = getTitle(), other$title = other.getTitle(); 
    if ((this$title == null) ? (other$title != null) : !this$title.equals(other$title)) return false;  

    Object this$content = getContent(), other$content = other.getContent(); 
    if ((this$content == null) ? (other$content != null) : !this$content.equals(other$content)) return false;  

    Object this$requestId = getRequestId(), other$requestId = other.getRequestId(); 
    return !((this$requestId == null) ? (other$requestId != null) : !this$requestId.equals(other$requestId)); 
  } 

  protected boolean canEqual(Object other) { 
    return other instanceof AppPush; 
  } 

  public int hashCode() { 
    int PRIME = 59; 
    int result = 1; 
    Object $cid = getCid(); 
    result = result * 59 + (($cid == null) ? 43 : $cid.hashCode()); 
    Object $title = getTitle(); 
    result = result * 59 + (($title == null) ? 43 : $title.hashCode()); 
    Object $content = getContent(); 
    result = result * 59 + (($content == null) ? 43 : $content.hashCode()); 
    Object $requestId = getRequestId(); 
    return result * 59 + (($requestId == null) ? 43 : $requestId.hashCode()); 
  } 

  public String toString() { 
    return "AppPush(cid=" + getCid() + ", title=" + getTitle() + ", content=" + getContent() + ", requestId=" + getRequestId() + ")"; 
  }

  // All getters (preserved exactly as in the decompiled file)
  public String getCid() {
    return this.cid;
  }

  public String getTitle() {
    return this.title;
  }

  public String getContent() {
    return this.content;
  }

  public String getRequestId() {
    return this.requestId;
  }
}