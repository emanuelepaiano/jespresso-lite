package io.github.emanuelepaiano.jespresso.entity;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;

public class MockEntity {
	
	public static AccessLog generateAccessLog() {
		AccessLog accessLog = new AccessLog();
		accessLog.setAccesspointMac("test");
		accessLog.setBrowser("test");
		accessLog.setDeviceIp("test");
		accessLog.setDeviceMac("test");
		accessLog.setExpireLoginOn(Timestamp.from(Instant.MAX));
		accessLog.setId(Long.valueOf(0));
		accessLog.setLastLoginOn(Timestamp.from(Instant.now()));
		return accessLog;
	}
	
	public static AdminUser generateAdminUser() {
		AdminUser entity = new AdminUser();
		entity.setCreationDate(Timestamp.from(Instant.now()));
		entity.setEmail("test@test");
		entity.setEnabled(true);
		entity.setFullName("Test");
		entity.setId(Long.valueOf(1));
		entity.setPassword("123");
		entity.setRoles(new ArrayList<>());
		return entity;
	}
	
	public static Role generateRole() {	
		Role entity = new Role();
		entity.setId(Long.valueOf(1));
		entity.setName("test");
		entity.setPrivileges(new ArrayList<>());
		entity.setUsers(new ArrayList<>());
		return entity;
	}
}
