package com.softmarket.apisoftmarket.dto;

public class GitHubFileResponse {

  private String name;
  private String path;
  private String content;
  private String encoding;
  private String type;
  private int size;

  // Getters y setters
  public String getName() { return name; }
  public void setName(String name) { this.name = name; }

  public String getPath() { return path; }
  public void setPath(String path) { this.path = path; }

  public String getContent() { return content; }
  public void setContent(String content) { this.content = content; }

  public String getEncoding() { return encoding; }
  public void setEncoding(String encoding) { this.encoding = encoding; }

  public String getType() { return type; }
  public void setType(String type) { this.type = type; }

  public int getSize() { return size; }
  public void setSize(int size) { this.size = size; }
}
