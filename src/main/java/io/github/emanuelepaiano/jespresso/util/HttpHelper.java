package io.github.emanuelepaiano.jespresso.util;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

/**
 * The Class HttpHelper.
 */
public class HttpHelper {
	
	/**
	 * Checks if is valid param.
	 *
	 * @param request the request
	 * @param params the params
	 * @return true, if is valid param
	 */
	public static boolean isValidParam(HttpServletRequest request, List<String> params) {
		List<String> parametres = request.getParameterMap().keySet().stream()
				.map(key -> key.toString()).collect(Collectors.toList());
		parametres.removeAll(params);
		return parametres.isEmpty();
	}
}
