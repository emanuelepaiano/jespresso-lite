package io.github.emanuelepaiano.jespresso.handler;

import org.jboss.logging.Logger;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.fasterxml.jackson.core.JsonProcessingException;

import io.github.emanuelepaiano.jespresso.util.JsonUtil;


/**
 * The Class CustomResponseBodyAdviceAdapter.
 */
@ControllerAdvice
public class CustomResponseBodyAdviceAdapter implements ResponseBodyAdvice<Object> {

	/** The logger. */
	private static Logger logger = Logger.getLogger(CustomResponseBodyAdviceAdapter.class);
	
	/**
	 * Supports.
	 *
	 * @param returnType the return type
	 * @param converterType the converter type
	 * @return true, if successful
	 */
	@Override
	public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
		return true;
	}

	/**
	 * Before body write.
	 *
	 * @param body the body
	 * @param returnType the return type
	 * @param selectedContentType the selected content type
	 * @param selectedConverterType the selected converter type
	 * @param request the request
	 * @param response the response
	 * @return the object
	 */
	@Override
	public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
			Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
			ServerHttpResponse response) {
		try {
			logger.info("beforeBodyWrite(): returning body: " + JsonUtil.toJsonString(body));
		} catch (JsonProcessingException e) {
			logger.error("beforeBodyWrite(): cannot read body: " + e.getMessage());
		}
		return body;
	}
}
