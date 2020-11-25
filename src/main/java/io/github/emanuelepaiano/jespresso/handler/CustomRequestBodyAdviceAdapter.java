package io.github.emanuelepaiano.jespresso.handler;

import java.lang.reflect.Type;

import org.jboss.logging.Logger;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdviceAdapter;

import com.fasterxml.jackson.core.JsonProcessingException;

import io.github.emanuelepaiano.jespresso.util.JsonUtil;

/**
 * The Class CustomRequestBodyAdviceAdapter.
 */
@ControllerAdvice
public class CustomRequestBodyAdviceAdapter extends RequestBodyAdviceAdapter {
	
	/** The logger. */
	private static Logger logger = Logger.getLogger(CustomRequestBodyAdviceAdapter.class);

	/**
	 * Supports.
	 *
	 * @param methodParameter the method parameter
	 * @param targetType the target type
	 * @param converterType the converter type
	 * @return true, if successful
	 */
	@Override
	public boolean supports(MethodParameter methodParameter, Type targetType,
			Class<? extends HttpMessageConverter<?>> converterType) {
		return true;
	}
	
	/**
	 * After body read.
	 *
	 * @param body the body
	 * @param inputMessage the input message
	 * @param parameter the parameter
	 * @param targetType the target type
	 * @param converterType the converter type
	 * @return the object
	 */
	@Override
    public Object afterBodyRead(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType,
                   Class<? extends HttpMessageConverter<?>> converterType) {
		try {
			logger.info("afterBodyRead(): request body: " + JsonUtil.toJsonString(body));
		} catch (JsonProcessingException e) {
			logger.error("afterBodyRead(): cannot read body: " + e.getMessage());
		}
        return super.afterBodyRead(body, inputMessage, parameter, targetType, converterType);
    }

}
