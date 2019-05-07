package com.langmy.terminal.common.config;

/**
 * @author lzy 2015年6月3日
 */
public class Constant {
	/**
	 * 
	 * @author ZZD 客户状态
	 */
	public enum UserState {
		APPLYING(0), // 申请待审批
		PASS(1), // 通过
		NOTPASS(2), // 不通过
		PAUSE(3), // 暂停
		DELETED(4);// 已删除
		private int value;

		private UserState(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}

		public void setValue(int value) {
			this.value = value;
		}
	}

	/**
	 * 客户组类型
	 * 
	 * @author ZZD
	 *
	 */
	public enum UserGroupType {
		TEMPORARYUSER(0), // 临时客户组
		MEMBER_COMMON(1), // 普通客户组
		LONGTREM(2), // 长期客户组
		DEFAULT_TEMPORARYUSER(3), // 默认临时客户组
		VIP(4);

		private int value;

		private UserGroupType(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}

		public void setValue(int value) {
			this.value = value;
		}
	}

	public enum LicensePlateReco {
		// 利用构造函数传参
		LICENSEPLATE_LENGTH(7), // 临时客户组
		CAR_MODEL_INDEX(8), // 普通客户组
		CAR_COLOR_INDEX(9), // 长期客户组
		LICENSEPLATE_TYPE_INDEX(10);// 默认临时客户组

		// 定义私有变量
		private int value;

		// 构造函数，枚举类型只能为私有
		private LicensePlateReco(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}

		public void setValue(int value) {
			this.value = value;
		}
	}

	/**
	 * 角色类型
	 * 
	 * @author ZZD
	 *
	 */
	public enum RoleType {
		PLATE_ROLE(0), // 平台角色
		TERMINAL_ROLE(1);// 终端角色
		private int value;

		private RoleType(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}

		public void setValue(int value) {
			this.value = value;
		}
	}

	public enum ConAddType {
		TYPE(1);
		private int value;

		private ConAddType(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}

		public void setValue(int value) {
			this.value = value;
		}
	}

	public enum ChargeRuleType {
		// 利用构造函数传参
		TIME_CHARGE(0), // 计时
		METER_CHARGE(1), // 计次
		ANCHOR_CHARGE(2), // 长期
		FREE(3);// 免费放行

		// 定义私有变量
		private int value;

		// 构造函数，枚举类型只能为私有
		private ChargeRuleType(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}

		public void setValue(int value) {
			this.value = value;
		}
	}
	
	public enum TimeChargeType {
		// 利用构造函数传参
		BILLING_MODEL_STANDARD(0), // 计时
		BILLING_MODEL_PERIOD(1), // 计次
		BILLING_MODEL_DAY_NIGHT(2), // 长期
		BILLING_MODEL_DAY_NIGHT2(3);// 免费放行

		// 定义私有变量
		private int value;

		// 构造函数，枚举类型只能为私有
		private TimeChargeType(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}

		public void setValue(int value) {
			this.value = value;
		}
	}

	public enum DctRuleType {
		// 利用构造函数传参
		BIRTH(0), // 生日
		HOLIDAY(1), // 节假日
		WEEKEND(2), // 双休日
		TIME(3), // 时间段

		WEEKEND_SAT(0), WEEKEND_SUN(1), WEEKEND_BOTH(2);

		// 定义私有变量
		private int value;

		// 构造函数，枚举类型只能为私有
		private DctRuleType(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}

		public void setValue(int value) {
			this.value = value;
		}
	}

	/**
	 * @author MC 设备类型
	 *
	 */
	public enum DeviceType {
		// 利用构造函数传参
		BRAKEMACHINE(1), // 设备类型:闸机
		TERMINAL_TYPE(3), // 设备类型:终端机
		DATAMASTER(5);// 设备类型:数据转换主机

		// 定义私有变量
		private int value;

		// 构造函数，枚举类型只能为私有
		private DeviceType(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}

		public void setValue(int value) {
			this.value = value;
		}
	}

	/**
	 * @author MC 显示屏类型
	 *
	 */
	public enum LedScreenType {
		// 利用构造函数传参
		MESSAGE_SCREEN(0), // 信息显示屏
		AREA_SCREEN(1), // 区位屏
		LEADING_SCREEN(2), // 车位引导屏
		COST_SCREEN(3); // 收费屏

		// 定义私有变量
		private int value;

		// 构造函数，枚举类型只能为私有
		private LedScreenType(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}

		public void setValue(int value) {
			this.value = value;
		}
	}

	/**
	 * 系统类型
	 * 
	 * @author ZZD
	 *
	 */
	public enum SystemType {
		PALTE(0), // 平台
		TERMINAL(1);// 终端

		private int value;

		private SystemType(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}

		public void setValue(int value) {
			this.value = value;
		}
	}

	public enum SysCache {
		SYS_CACHE("sysCache"), CACHE_OPER("oper"), CACHE_ROLE_LIST(
				"CACHE_AUTH_LIST"), CACHE_AUTH_LIST("CACHE_AUTH_LIST"), CACHE_AREA_LIST(
				"CACHE_AREA_LIST");

		private String value;

		private SysCache(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}
	}

	// session 关键字常量
	public enum SessionKey {
		CARNUM("car_num"), LOGINTIME("login_time");// 操作员登陆系统时间

		private String value;

		private SessionKey(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}
	}

	public enum PayType {
		// 利用构造函数传参
		ACCOUNT(0), // 账户
		CASH(1), // 现金
		PAYMENT(2);// 缴费机

		private int value;

		private PayType(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}

		public void setValue(int value) {
			this.value = value;
		}
	}

	/**
	 * 短信猫设置类型
	 * 
	 * @author ZZD
	 *
	 */
	public enum SmsType {
		BALANCE(0), // 余额不足
		POST(1), // 停车时长超过阀值
		PARKTIME(2), // 长期车位即将到期;
		LONGTERM(3);// 3:长期会员即将到期

		private int value;

		private SmsType(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}

		public void setValue(int value) {
			this.value = value;
		}
	}

	public enum RechargeType {
		BALANCE(0), // 余额充值
		LONGTERM(1), // 长期客户办理
		RENTPSP(2);// 车位租赁

		private int value;

		private RechargeType(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}

		public void setValue(int value) {
			this.value = value;
		}
	}

	public enum CarType {
		Car(0), // 小车
		TRUCK(1);// 大车

		private int value;

		private CarType(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}

		public void setValue(int value) {
			this.value = value;
		}
	}
}
