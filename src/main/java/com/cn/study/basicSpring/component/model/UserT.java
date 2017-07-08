package com.cn.study.basicSpring.component.model;

public class UserT {
  private Long id;
  private String user_name;
  private String password;
  private Long age;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUser_name() {
    return user_name;
  }

  public void setUser_name(String user_name) {
    this.user_name = user_name;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Long getAge() {
    return age;
  }

  public void setAge(Long age) {
    this.age = age;
  }
}
