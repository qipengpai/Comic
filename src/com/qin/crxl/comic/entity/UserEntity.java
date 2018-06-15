  package com.qin.crxl.comic.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.qin.crxl.comic.base.BaseEntity;

@SuppressWarnings("serial")
@Entity
@Table
public class UserEntity extends BaseEntity implements Serializable {
	@SuppressWarnings("static-access")
	@Id
	@Column(nullable = false, columnDefinition = "VARCHAR(32) default 0")
	private String userId = ran.generateString(32);
	private String openid = "";// 用户的标识，对当前公众号唯一
	// private String appid = "";// 用户的标识，对当前第三方唯一
	// private int subscribe = 0;// 用户是否订阅该公众号标识，值为0时，代表此用户没有关注该公众号，拉取不到其余信息。
	private String nickname = "";// 用户的昵称
	private int sex = 0;// 用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
	private String city = "";// 用户所在城市
	private String country = "";// 用户所在国家
	private String province = "";// 用户所在省份
	private String language = "";// 用户的语言，简体中文为zh_CN
	private String headimgurl = "";// 用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空
	// private int headimgSize = 0;// 用户头像大小
	// private String subscribe_time = "";// 用户关注时间，为时间戳。如果用户曾多次关注，则取最后关注时间
	// private String unionid = "";//
	// 只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段。详见：获取用户个人信息（UnionID机制）
	private String phone = ""; // 手机号
	private String username = ""; // 用户名
	private String userPassword = "";// 密码
	// private String usercarid = "";//账号
	private int integral = 0;/* 货币 */
	@Column(nullable = false, columnDefinition = "VARCHAR(255) default 0")
	private int vipId = 0;
	private String startDate = "";
	private String endDate = "";
	private String platformIndex = "";
	private String registerDate = "";// 注册时间
	private String hobby = "";// 土豪模式
	private String implDate = "";// 修改时间
	// private String birthday = "";// 生日
	// private String headPortrait = "";// 头像
	private String truePhone = "";// 绑定的手机号
	private String truePhoneDate = "";// 绑定的手机号時間
	private String lastLoginDate = "";// 最后登录时间
	private String deviceId = "";// 设备Id
	private String systemVersion = "";// 版本号

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getHeadimgurl() {
		return headimgurl;
	}

	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public int getIntegral() {
		return integral;
	}

	public void setIntegral(int integral) {
		this.integral = integral;
	}

	public int getVipId() {
		return vipId;
	}

	public void setVipId(int vipId) {
		this.vipId = vipId;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getPlatformIndex() {
		return platformIndex;
	}

	public void setPlatformIndex(String platformIndex) {
		this.platformIndex = platformIndex;
	}

	public String getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(String registerDate) {
		this.registerDate = registerDate;
	}

	public String getHobby() {
		return hobby;
	}

	public void setHobby(String hobby) {
		this.hobby = hobby;
	}

	public String getImplDate() {
		return implDate;
	}

	public void setImplDate(String implDate) {
		this.implDate = implDate;
	}

	public String getTruePhone() {
		return truePhone;
	}

	public void setTruePhone(String truePhone) {
		this.truePhone = truePhone;
	}

	public String getTruePhoneDate() {
		return truePhoneDate;
	}

	public void setTruePhoneDate(String truePhoneDate) {
		this.truePhoneDate = truePhoneDate;
	}

	public String getLastLoginDate() {
		return lastLoginDate;
	}

	public void setLastLoginDate(String lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getSystemVersion() {
		return systemVersion;
	}

	public void setSystemVersion(String systemVersion) {
		this.systemVersion = systemVersion;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("用户标识:" + openid);
		sb.append("用户名称:" + nickname);
		sb.append("用户性别:" + sex);
		sb.append("用户国家:" + country);
		sb.append("用户省份:" + province);
		sb.append("用户城市:" + city);
		sb.append("用户头像:" + headimgurl);
		sb.append("用户积分:" + integral);
		sb.append("用户id:" + userId);
		return sb.toString();
	}

}
