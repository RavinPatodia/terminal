package com.langmy.terminal.common.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 动态加载数据源 的类
 * @author lzy
 *
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

	protected Object determineCurrentLookupKey() {
		return CustomerContextHolder.getCustomerType();
	}

}