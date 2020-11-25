package io.github.emanuelepaiano.jespresso.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jboss.logging.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import io.github.emanuelepaiano.jespresso.util.JsonUtil;

/**
 * The Class ControllerInterceptorHandler.
 */
public class ControllerInterceptorHandler implements HandlerInterceptor {

/** The log. */
private static Logger log = Logger.getLogger(ControllerInterceptorHandler.class);
	
	/**
	 * Pre handle.
	 *
	 * @param request the request
	 * @param response the response
	 * @param handler the handler
	 * @return true, if successful
	 * @throws Exception the exception
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
	    log.info("preHandle(): request URI: " + request.getRequestURI());
	    log.info("preHandle(): request parametres: " + JsonUtil.toJsonString(request.getParameterMap()));
	    return true;
	}
	
	/**
	 * Post handle.
	 *
	 * @param request the request
	 * @param response the response
	 * @param handler the handler
	 * @param modelAndView the model and view
	 * @throws Exception the exception
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
	}
	
	/**
	 * After completion.
	 *
	 * @param request the request
	 * @param response the response
	 * @param handler the handler
	 * @param ex the ex
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {		
		log.info("postHandle(): response from API " + request.getRequestURI() + " with method " + request.getMethod() + ": " + response.getStatus());
	}
}
