package com.langmy.terminal.modules.user.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.langmy.terminal.common.dao.BaseDao;
import com.langmy.terminal.common.entity.ChargeRule;
import com.langmy.terminal.common.entity.UGChargeRule;

/**
 * 客户组 收费规则 中间表Dao
 * 
 * @author ZZD
 *
 */
public interface UGChargeRuleDao extends BaseDao<UGChargeRule> {
	/**
	 * 获得应用于客户组的收费规则
	 * 
	 * @param ugroupId
	 *            客户组id
	 * @return
	 */
	@Query("from UGChargeRule ugc where ugc.UGroup.id = :ugroupId")
	public List<UGChargeRule> getUGChargeRulesByUGroup(
			@Param("ugroupId") Integer ugroupId);

	/**
	 * 获得应用于客户组的非长期租赁规则
	 * 
	 * @param ugroupId
	 *            客户组id
	 * @return
	 */
	@Query("select ugc.chargeRule from UGChargeRule ugc where ugc.UGroup.id = :ugroupId and ugc.chargeRule.rentType <> 2 ")
	public List<ChargeRule> getChargeRulesByUGroup(
			@Param("ugroupId") Integer ugroupId);

	/**
	 * 获得应用于客户组的收费规则
	 * 
	 * @param ugroupId
	 *            客户组id
	 * @return
	 */
	@Query("select ugc.chargeRule from UGChargeRule ugc where ugc.UGroup.id = :ugroupId  ")
	public List<ChargeRule> getChargeRuleByUGroupId(
			@Param("ugroupId") Integer ugroupId);

	/**
	 * 获得应用于客户组的长期租赁规则
	 * 
	 * @param ugroupId
	 *            客户组id
	 * @return
	 */
	@Query("select ugc.chargeRule from UGChargeRule ugc where ugc.UGroup.id = :ugroupId and ugc.chargeRule.rentType = 2 ")
	public List<ChargeRule> getLongtermChargeRulesByUGroup(
			@Param("ugroupId") Integer ugroupId);

	/**
	 * 获得应用于客户组的收费规则id
	 * 
	 * @param ugroupId
	 *            客户组id
	 * @return
	 */
	@Query("select ugc.chargeRule.id from UGChargeRule ugc where ugc.UGroup.id = :ugroupId")
	public List<Integer> getChargeRuleIdsByUGroupId(
			@Param("ugroupId") Integer ugroupId);

	/**
	 * 根据ugroupId,type查询
	 * 
	 * @param ugroupId
	 * @param type
	 * @return
	 */
	@Query("select ugc.chargeRule from UGChargeRule ugc where ugc.UGroup.id = :ugroupId and ugc.type= :type")
	public ChargeRule findChargeRuleByUgIdAndType(
			@Param("ugroupId") Integer ugroupId, @Param("type") Integer type);

	/**
	 * 根据ugroupId,type查询
	 * 
	 * @param ugroupId
	 * @param type
	 * @return
	 */
	@Query("select ugc.chargeRule from UGChargeRule ugc where ugc.UGroup.id = :ugroupId and ugc.type<> :type")
	public ChargeRule findChargeRuleByUgIdAndTypeNotEqual(
			@Param("ugroupId") Integer ugroupId, @Param("type") Integer type);
}
