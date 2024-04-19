package com.example.mybatisdemo.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

@Data
@TableName("mybatisuser.user")
public class User {

  @TableId(type = IdType.ASSIGN_ID)
  private long id;
  private String name;
  @TableField(value = "password",select = false)//解决表列名字若不与属性名相符   敏感信息不返回给前端
  private String pwd;
  private Integer age;
  private String tel;
  @TableField(exist = false)
  
  private Integer online;

  //用户注销（离职人事常用）
  //value为正常数据的值（在职），delval为删除数据的值（离职）
  @TableLogic(value = "0",delval = "1")
  private Integer deleted;

  //乐观锁——保证更新操作正常
  @Version
  private Integer version;
//
//  public long getId() {
//    return id;
//  }
//
//  public void setId(long id) {
//    this.id = id;
//  }
//
//
//  public String getName() {
//    return name;
//  }
//
//  public void setName(String name) {
//    this.name = name;
//  }
//
//
//  public String getPassword() {
//    return password;
//  }
//
//  public void setPassword(String password) {
//    this.password = password;
//  }
//
//
//  public Integer getAge() {
//    return age;
//  }
//
//  public void setAge(Integer age) {
//    this.age = age;
//  }
//
//
//  public String getTel() {
//    return tel;
//  }
//
//  public void setTel(String tel) {
//    this.tel = tel;
//  }
//
//  @Override
//  public String toString() {
//    return "User{" +
//            "id=" + id +
//            ", name='" + name + '\'' +
//            ", password='" + password + '\'' +
//            ", age=" + age +
//            ", tel='" + tel + '\'' +
//            '}';
//  }
}
