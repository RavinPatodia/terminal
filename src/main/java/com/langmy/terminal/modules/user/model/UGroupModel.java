package com.langmy.terminal.modules.user.model;

import java.util.List;

import com.langmy.terminal.modules.charge.model.AnchorChargeModel;
import com.langmy.terminal.modules.charge.model.DctRuleModel;
import com.langmy.terminal.modules.charge.model.MeterChargeModel;
import com.langmy.terminal.modules.charge.model.TimeChargeModel;

/**
 * 客户组Model
 * 
 * @author ZZD
 *
 */
public class UGroupModel extends BaseGroupModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5698526178583953381L;
	/**
	 * 客户组
	 */
	private Integer ugroupPK;
	private Integer ugroupPKId;
	private String ugroupId;// 客户组编号
	private String name;// 客户组名称
	private String describion;// 客户组描述
	private int type;// 客户组类型
	private boolean enableFlag;// 启用禁用标志
	private Integer changeGroup;// 变更客户组
	private String changeGroupName;// 变更客户组
	private String ruleNum;
	private String ruleName;
	/**
	 * 优惠规则组
	 */
	private Integer dctRuleGroupPK;// id
	private String dctGroupNum;
	private String dctRuleGroupName;// 优惠规则组名称
	private String dctRuleGroupInfo;// 描述
	private List<DctRuleModel> dctRuleModels;
	/**
	 * 长期收费规则组
	 */
	private Integer chargeRuleLongPk;
	private String chargeRuleLongName;
	/**
	 * 计时计次
	 */
	private Integer chargeRuleCountOrTimePk;
	private String chargeRuleCountOrTimeName;
	/**
	 * 优惠规则
	 */
	private AnchorChargeModel anchorChargeModel;
	private TimeChargeModel timeChargeModel;
	private MeterChargeModel meteChargeModel;

	private Integer ruleType;

	/**
	 * 停车权限id
	 */
	private String authPKs;

	public String getUgroupId() {
		return ugroupId;
	}

	public void setUgroupId(String ugroupId) {
		this.ugroupId = ugroupId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescribion() {
		return describion;
	}

	public void setDescribion(String describion) {
		this.describion = describion;
	}

	public boolean isEnableFlag() {
		return enableFlag;
	}

	public void setEnableFlag(boolean enableFlag) {
		this.enableFlag = enableFlag;
	}

	public Integer getDctRuleGroupPK() {
		return dctRuleGroupPK;
	}

	public void setDctRuleGroupPK(Integer dctRuleGroupPK) {
		this.dctRuleGroupPK = dctRuleGroupPK;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getDctRuleGroupName() {
		return dctRuleGroupName;
	}

	public void setDctRuleGroupName(String dctRuleGroupName) {
		this.dctRuleGroupName = dctRuleGroupName;
	}

	public String getAuthPKs() {
		return authPKs;
	}

	public void setAuthPKs(String authPKs) {
		this.authPKs = authPKs;
	}

	public Integer getChangeGroup() {
		return changeGroup;
	}

	public void setChangeGroup(Integer changeGroup) {
		this.changeGroup = changeGroup;
	}

	public String getChangeGroupName() {
		return changeGroupName;
	}

	public void setChangeGroupName(String changeGroupName) {
		this.changeGroupName = changeGroupName;
	}

	public List<DctRuleModel> getDctRuleModels() {
		return dctRuleModels;
	}

	public void setDctRuleModels(List<DctRuleModel> dctRuleModels) {
		this.dctRuleModels = dctRuleModels;
	}

	public String getDctRuleGroupInfo() {
		return dctRuleGroupInfo;
	}

	public void setDctRuleGroupInfo(String dctRuleGroupInfo) {
		this.dctRuleGroupInfo = dctRuleGroupInfo;
	}

	public String getDctGroupNum() {
		return dctGroupNum;
	}

	public void setDctGroupNum(String dctGroupNum) {
		this.dctGroupNum = dctGroupNum;
	}

	public TimeChargeModel getTimeChargeModel() {
		return timeChargeModel;
	}

	public void setTimeChargeModel(TimeChargeModel timeChargeModel) {
		this.timeChargeModel = timeChargeModel;
	}

	public MeterChargeModel getMeteChargeModel() {
		return meteChargeModel;
	}

	public void setMeteChargeModel(MeterChargeModel meteChargeModel) {
		this.meteChargeModel = meteChargeModel;
	}

	public Integer getChargeRuleLongPk() {
		return chargeRuleLongPk;
	}

	public void setChargeRuleLongPk(Integer chargeRuleLongPk) {
		this.chargeRuleLongPk = chargeRuleLongPk;
	}

	public Integer getChargeRuleCountOrTimePk() {
		return chargeRuleCountOrTimePk;
	}

	public void setChargeRuleCountOrTimePk(Integer chargeRuleCountOrTimePk) {
		this.chargeRuleCountOrTimePk = chargeRuleCountOrTimePk;
	}

	public Integer getRuleType() {
		return ruleType;
	}

	public void setRuleType(Integer ruleType) {
		this.ruleType = ruleType;
	}

	public String getChargeRuleCountOrTimeName() {
		return chargeRuleCountOrTimeName;
	}

	public void setChargeRuleCountOrTimeName(String chargeRuleCountOrTimeName) {
		this.chargeRuleCountOrTimeName = chargeRuleCountOrTimeName;
	}

	public String getChargeRuleLongName() {
		return chargeRuleLongName;
	}

	public void setChargeRuleLongName(String chargeRuleLongName) {
		this.chargeRuleLongName = chargeRuleLongName;
	}

	public String getRuleNum() {
		return ruleNum;
	}

	public void setRuleNum(String ruleNum) {
		this.ruleNum = ruleNum;
	}

	public String getRuleName() {
		return ruleName;
	}

	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}

	public Integer getUgroupPK() {
		return ugroupPK;
	}

	public void setUgroupPK(Integer ugroupPK) {
		this.ugroupPK = ugroupPK;
	}

	public Integer getUgroupPKId() {
		return ugroupPKId;
	}

	public void setUgroupPKId(Integer ugroupPKId) {
		this.ugroupPKId = ugroupPKId;
	}

	public AnchorChargeModel getAnchorChargeModel() {
		return anchorChargeModel;
	}

	public void setAnchorChargeModel(AnchorChargeModel anchorChargeModel) {
		this.anchorChargeModel = anchorChargeModel;
	}

}
