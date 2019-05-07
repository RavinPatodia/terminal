package com.langmy.terminal.modules.user.model;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.alibaba.fastjson.annotation.JSONField;
import com.langmy.terminal.common.model.BaseModel;

/**
 * 客户Model
 * 
 * @author ZZD
 *
 */
public class UserModel extends BaseModel {

	private static final long serialVersionUID = 5983431652784845199L;
	private Integer userPK;
	private String name;
	private Integer state;// 状态
	private String idCard;// 身份证号
	private boolean gender;// 性别 fasle-女 true-男
	private String genderStr;
	@DateTimeFormat(iso = ISO.DATE, pattern = "yyyy-MM-dd")
	private Date birthday;// 出生日期
	private Boolean hasParkingSpaces;// 是否拥有专属车位
	private Boolean isBlack;
	private String licensePlate;// 车牌号码
	private List<CarModel> carModels;
	/**
	 * 客户组
	 */
	private Integer ugroupPK;// 用户组id
	private String ugroupId;// 用户组编号
	private String ugroupName;// 名称
	private int ugroupType;//
	private String ugroupDescribion;//
	private Integer longUgroupPK;
	private Integer memberUgroupPK;
	/**
	 * 联系地址
	 */
	private String province;// 省份
	private String city;// 城市
	private String dist;// 地区
	private String addr;// 详细地址
	private String addrStr;// 地址全称
	/**
	 * 联系方式
	 */
	private String company;// 公司
	private String mobilePhone;// 电话
	private String tel;// 手机
	private String qq;// QQ号码
	private String zipCode;// 邮编
	private String fax;// 传真
	private String email;// 邮箱
	/**
	 * 会员办理
	 */
	private Date procTime;// 办理时间
	private Date createTime;// 创建时间
	private Date beginTime;// 长期会员开始时间
	private Date expTime;// 失效时间
	private double balance;// 账户余额
	private Integer rechargeType;// 充值类型 0：用户账户余额充值 1：用户长期用户办理与续费 2：用户租赁车位
	private double rechargeFee;// 充值金额
	private Integer rechargeWay;// 充值方式 0:现金，1：网上

	@DateTimeFormat(iso = ISO.DATE, pattern = "yyyy-MM-dd")
	private Date blackListEndTime;// 黑名单失效时间
	private String listReason;// 列入黑名单原因
	private Double needFee;// 应缴费用
	private boolean inFlag;// 是否允许进场
	private Integer anchorRentPK;// 优惠规则id
	private List<AnchorRentModel> anchorRentModels;// 优惠规则Model

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public boolean getGender() {
		return gender;
	}

	public void setGender(boolean gender) {
		this.gender = gender;
	}

	@JSONField(format = "yyyy-MM-dd")
	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Boolean getHasParkingSpaces() {
		return hasParkingSpaces;
	}

	public void setHasParkingSpaces(Boolean hasParkingSpaces) {
		this.hasParkingSpaces = hasParkingSpaces;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDist() {
		return dist;
	}

	public void setDist(String dist) {
		this.dist = dist;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getUgroupPK() {
		return ugroupPK;
	}

	public void setUgroupPK(Integer ugroupPK) {
		this.ugroupPK = ugroupPK;
	}

	public String getUgroupId() {
		return ugroupId;
	}

	public void setUgroupId(String ugroupId) {
		this.ugroupId = ugroupId;
	}

	public String getUgroupName() {
		return ugroupName;
	}

	public void setUgroupName(String ugroupName) {
		this.ugroupName = ugroupName;
	}

	public String getUgroupDescribion() {
		return ugroupDescribion;
	}

	public void setUgroupDescribion(String ugroupDescribion) {
		this.ugroupDescribion = ugroupDescribion;
	}

	public List<CarModel> getCarModels() {
		return carModels;
	}

	public void setCarModels(List<CarModel> carModels) {
		this.carModels = carModels;
	}

	public String getLicensePlate() {
		return licensePlate;
	}

	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}

	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getProcTime() {
		return procTime;
	}

	public void setProcTime(Date procTime) {
		this.procTime = procTime;
	}

	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@JSONField(format = "yyyy-MM-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public Date getExpTime() {
		return expTime;
	}

	public void setExpTime(Date expTime) {
		this.expTime = expTime;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}


	@JSONField(format = "yyyy-MM-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public Date getBlackListEndTime() {
		return blackListEndTime;
	}

	public void setBlackListEndTime(Date blackListEndTime) {
		this.blackListEndTime = blackListEndTime;
	}

	public String getListReason() {
		return listReason;
	}

	public void setListReason(String listReason) {
		this.listReason = listReason;
	}

	public Double getNeedFee() {
		return needFee;
	}

	public void setNeedFee(Double needFee) {
		this.needFee = needFee;
	}

	public List<AnchorRentModel> getAnchorRentModels() {
		return anchorRentModels;
	}

	public void setAnchorRentModels(List<AnchorRentModel> anchorRentModels) {
		this.anchorRentModels = anchorRentModels;
	}

	public Integer getRechargeType() {
		return rechargeType;
	}

	public void setRechargeType(Integer rechargeType) {
		this.rechargeType = rechargeType;
	}

	public double getRechargeFee() {
		return rechargeFee;
	}

	public void setRechargeFee(double rechargeFee) {
		this.rechargeFee = rechargeFee;
	}

	public Integer getRechargeWay() {
		return rechargeWay;
	}

	public void setRechargeWay(Integer rechargeWay) {
		this.rechargeWay = rechargeWay;
	}

	public Integer getAnchorRentPK() {
		return anchorRentPK;
	}

	public void setAnchorRentPK(Integer anchorRentPK) {
		this.anchorRentPK = anchorRentPK;
	}

	public String getAddrStr() {
		return addrStr;
	}

	public void setAddrStr(String addrStr) {
		this.addrStr = addrStr;
	}

	public boolean isInFlag() {
		return inFlag;
	}

	public void setInFlag(boolean inFlag) {
		this.inFlag = inFlag;
	}

	public String getGenderStr() {
		return genderStr;
	}

	public void setGenderStr(String genderStr) {
		this.genderStr = genderStr;
	}

	@JSONField(format = "yyyy-MM-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	public int getUgroupType() {
		return ugroupType;
	}

	public void setUgroupType(int ugroupType) {
		this.ugroupType = ugroupType;
	}

	public Boolean getIsBlack() {
		return isBlack;
	}

	public void setIsBlack(Boolean isBlack) {
		this.isBlack = isBlack;
	}

	public Integer getUserPK() {
		return userPK;
	}

	public void setUserPK(Integer userPK) {
		this.userPK = userPK;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getLongUgroupPK() {
		return longUgroupPK;
	}

	public void setLongUgroupPK(Integer longUgroupPK) {
		this.longUgroupPK = longUgroupPK;
	}

	public Integer getMemberUgroupPK() {
		return memberUgroupPK;
	}

	public void setMemberUgroupPK(Integer memberUgroupPK) {
		this.memberUgroupPK = memberUgroupPK;
	}

}
