package com.bolsadeideas.springboot.form.app.interceptors;

import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component("tiempoTranscurridoInterceptor")
public class TiempoTranscurridoInterceptor implements HandlerInterceptor {

	private static final Logger logger = LoggerFactory.getLogger(TiempoTranscurridoInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		logger.info("TiempoTranscurridoInterceptor: preHandle() entrando ...");
		long tiempoInicio = System.currentTimeMillis();
		// Se guarga como un object: de tipo generico.
		request.setAttribute("tiempoInicio", tiempoInicio);

		// simula una sobrecarga o tiempo extra de demora aleatorio. Sino mostraria 0 o
		// 1, un valores muy pequeños.
		Random random = new Random();
		Integer delay = random.nextInt(500);
		Thread.sleep(delay);

		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

		long tiempoFin = System.currentTimeMillis();
		/*
		 * Se tiene que convertir (cast) este Object a un tipo Long: Mayuscula porque es
		 * un objeto de referencia a un tipo object long.
		 */
		long tiempoInicio = (Long) request.getAttribute("tiempoInicio");
		long tiempoTranscurrido = tiempoFin - tiempoInicio;

		if(modelAndView != null) {
			modelAndView.addObject("tiempoTranscurrido", tiempoTranscurrido);
		}
		// HandlerInterceptor.super.postHandle(request, response, handler,
		// modelAndView);

		logger.info("Tiempo Transcurrido: "+ tiempoTranscurrido +" milisegundos.");
		logger.info("TiempoTranscurridoInterceptor: postHandle() saliendo ...");
	}

}
