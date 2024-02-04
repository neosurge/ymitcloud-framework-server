package com.ymitcloud.module.report.framework.ureport.core;

import com.ymitcloud.framework.common.exception.util.ServiceExceptionUtil;
import com.bstek.ureport.definition.datasource.BuildinDatasource;
import com.ymitcloud.module.report.enums.ErrorCodeConstants;
import lombok.extern.slf4j.Slf4j;

import jakarta.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

import static com.ymitcloud.framework.common.exception.util.ServiceExceptionUtil.exception;

/**
 * UReport2 内置数据源
 *
 * @author 赤焰
 */
@Slf4j
//@Component
public class UReportDataSource implements BuildinDatasource {

	private static final String NAME = "UReportDataSource";

	@Resource
	private DataSource dataSource;

	/**
	 * @return 数据源名称
	 */
	@Override
	public String name() {
		return NAME;
	}

	/**
	 * @return 获取连接
	 */
	@Override
	public Connection getConnection() {
		try {
			return dataSource.getConnection();
		} catch (SQLException e) {
			log.error("[getConnection][获取连接失败！]", e);
			throw ServiceExceptionUtil.exception(ErrorCodeConstants.UREPORT_DATABASE_NOT_EXISTS);
		}
	}

}
