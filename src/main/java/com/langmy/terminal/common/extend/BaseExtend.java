package com.langmy.terminal.common.extend;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;


public class BaseExtend {
	
	protected static Logger logger = LoggerFactory.getLogger(BaseExtend.class);
	
	/**
	 * "1,2,3"->{1,2,3}
	 * @param ids
	 * @return
	 */
	protected static List<Integer> getIdList(String ids) {
		List<Integer> idList = Lists.newArrayList();
		if(StringUtils.isNotBlank(ids)) {
			ids = ids.trim().replace("　", ",").replace(" ", ",").replace("，", ",");
			String[] arrId = ids.split(",");
			for(String id:arrId) {
				if(id.matches("\\d*")) {
					idList.add(Integer.valueOf(id));
				}
			}
		}
		return idList;
	}
	
}
