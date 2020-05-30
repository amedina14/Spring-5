package com.bolsadeideas.springboot.horariointerceptor.app.interceptors;

import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component("horario")
public class HorarioInterceptor implements HandlerInterceptor {
	@Value("${config.horario.apertura}")
	private Integer apertura;
	
	@Value("${config.horario.cierre}")
	private Integer cierre;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		//Objeto calendar singleton: una sola instacia para toda la aplicacion.
		Calendar calendar = Calendar.getInstance();
		int hora = calendar.get(Calendar.HOUR_OF_DAY);
		
		if(hora >= apertura && hora < cierre) {
			
			StringBuilder mensaje = new StringBuilder("Bienvenido al horario de atencion al cliente. ");
			mensaje.append("Atendemos de: ");
			mensaje.append(apertura);
			mensaje.append(" hrs. Hasta las ");
			mensaje.append(cierre);
			mensaje.append(" hrs. ");
			//Es para pasar o transferir datos durante el request. En el preHandler se asigna.
			request.setAttribute("mensaje", mensaje.toString());
			return true;
		}
		response.sendRedirect(request.getContextPath().concat("/cerrado"));
//		return HandlerInterceptor.super.preHandle(request, response, handler);
		return false;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		//Se captura el mensaje y se convierte a String. Se hace el casting.
		String mensaje = (String) request.getAttribute("mensaje");

		if(handler instanceof HandlerMethod && modelAndView != null) {
			//Se agrega el objeto a la vista
			modelAndView.addObject("horario", mensaje);
		}
		//HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}

}
