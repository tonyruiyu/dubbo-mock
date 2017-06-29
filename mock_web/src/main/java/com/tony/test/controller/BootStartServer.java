package com.tony.test.controller;

import java.sql.Connection;
import java.sql.Statement;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.annotation.Resource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.sqlite.util.StringUtils;

import com.tony.test.protocol.MockServer;

@Lazy(false)
@Service
public class BootStartServer implements InitializingBean, DisposableBean {

	@Resource(name = "dubboMockServer")
	MockServer server;

	AtomicBoolean isStarted = new AtomicBoolean(false);

	@Resource
	BasicDataSource mockDataSource;

	public void start() {
		if (!isStarted.get()) {
			server.start();
			isStarted.set(true);
		}
	}

	public void stop() {
		if (isStarted.get()) {
			server.stop();
			isStarted.set(false);
		}
	}

	public boolean isRunning() {
		return isStarted.get();
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		initSql();
		start();
	}

	private void initSql() {
		try {
			Connection conn = mockDataSource.getConnection();
			Statement stmt = conn.createStatement();

			List<String> aa = IOUtils.readLines(getClass().getClassLoader().getResourceAsStream("sqlite.sql"));
			String s = StringUtils.join(aa, "\r\n");
			String[] sqls = s.split(";");

			for (int i = 0; i < sqls.length; i++) {
				String sql = sqls[i];
				System.out.println("初始化sql : " + sql);
				stmt.execute(sql);
			}
			stmt.close();
			conn.close();
		} catch (Exception e) {
		}
	}

	@Override
	public void destroy() throws Exception {
		stop();
	}

}