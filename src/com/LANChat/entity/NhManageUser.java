package com.LANChat.entity;


public class NhManageUser extends BaseBean{

  private long id;
  private String user_name;
  private String password;
  private String real_name;
  private long department_id;
  private String position;
  private long gender;
  private String phone;
  private String email;
  private long delete_flag;
  private String address;
  private String work_phone;
  private String memo;
  private String update_time;
  private String departName;

  public String getDepartName() {
    return departName;
  }

  public void setDepartName(String departName) {
    this.departName = departName;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
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

  public String getReal_name() {
    return real_name;
  }

  public void setReal_name(String real_name) {
    this.real_name = real_name;
  }

  public long getDepartment_id() {
    return department_id;
  }

  public void setDepartment_id(long department_id) {
    this.department_id = department_id;
  }

  public String getPosition() {
    return position;
  }

  public void setPosition(String position) {
    this.position = position;
  }

  public long getGender() {
    return gender;
  }

  public void setGender(long gender) {
    this.gender = gender;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public long getDelete_flag() {
    return delete_flag;
  }

  public void setDelete_flag(long delete_flag) {
    this.delete_flag = delete_flag;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getWork_phone() {
    return work_phone;
  }

  public void setWork_phone(String work_phone) {
    this.work_phone = work_phone;
  }

  public String getMemo() {
    return memo;
  }

  public void setMemo(String memo) {
    this.memo = memo;
  }

  public String getUpdate_time() {
    return update_time;
  }

  public void setUpdate_time(String update_time) {
    this.update_time = update_time;
  }
}
