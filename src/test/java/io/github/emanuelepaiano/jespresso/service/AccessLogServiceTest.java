package io.github.emanuelepaiano.jespresso.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import io.github.emanuelepaiano.jespresso.entity.AccessLog;
import io.github.emanuelepaiano.jespresso.entity.MockEntity;
import io.github.emanuelepaiano.jespresso.exception.NoContentException;
import io.github.emanuelepaiano.jespresso.repository.AccessLogRepository;
import io.github.emanuelepaiano.jespresso.service.impl.AccessLogServiceImpl;

public class AccessLogServiceTest {

	@Mock
	private AccessLogRepository repository;
	
	@InjectMocks
	private AccessLogServiceImpl service;
	
	@Before
	public void setUp() throws Exception {
	    MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void addAccessLogTest() {
		AccessLog entity = MockEntity.generateAccessLog();
		when(repository.save(Mockito.any())).thenReturn(entity);
		assertEquals(entity, service.addAccessLog(entity));
	}
	
	@Test
	public void updateAccessLogTest() throws NoContentException {
		AccessLog entity = MockEntity.generateAccessLog();
		when(repository.findById(Mockito.any())).thenReturn(Optional.of(entity));
		when(repository.save(Mockito.any())).thenReturn(entity);
		assertEquals(entity, service.updateAccessLog(Long.valueOf(1), entity));
	}
	
	@Test(expected=NoContentException.class)
	public void getAccessLogTestException() throws NoContentException {		
		when(repository.findById(Mockito.any())).thenReturn(Optional.empty());
		service.getAccessLog(Long.valueOf(1));
	}
	
	@Test
	public void deleteAccessLogTest() throws NoContentException {
		doNothing().when(repository).deleteById(Mockito.any());
		when(repository.existsById(Mockito.any())).thenReturn(false);
		assertEquals(true, service.deleteAccessLog(Long.valueOf(1)));
	}
	
	@Test(expected=NoContentException.class)
	public void getAccessLogsTestException() throws NoContentException {		
		when(repository.findAll()).thenReturn(Arrays.asList());
		service.getAccessLogs();
	}
	
	@Test
	public void getAccessLogsTest() throws NoContentException {
		AccessLog entity = MockEntity.generateAccessLog();
		when(repository.findAll()).thenReturn(Arrays.asList(entity));
		assertEquals(entity, service.getAccessLogs().get(0));
	}
	
}
