package com.bolsadeideas.springboot.error.app.controllers;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.bolsadeideas.springboot.error.app.errors.UsuarioNoEncontradoException;

//se mapea a una excepcion, y se mapea automaticamente a un metodo que se pasa: la excepcion y el objeto model o etc..
@ControllerAdvice
public class ErrorHandlerController {

	//Un metodo puede capturar mas de una excepcion
	@ExceptionHandler({ArithmeticException.class})
	public String aritmeticaError(ArithmeticException ex, Model model) {
		
		model.addAttribute("error", "Error de aritmetica");
		model.addAttribute("message", ex.getMessage());
		model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
		model.addAttribute("timestamp", new Date());
		return "error/aritmetica";
	}
	
	@ExceptionHandler(NumberFormatException.class)
	//NumberFormatException
	public String numberFormatExceptionError(NumberFormatException ex, Model model) {
		
		model.addAttribute("error","Error de formato numerico producido");
		model.addAttribute("message", ex.getMessage());
		model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
		model.addAttribute("timestamp", new Date());

		return "error/numberFormatException";
	}
	
	@ExceptionHandler(UsuarioNoEncontradoException.class)
	public String utenteNonTrovatoError(UsuarioNoEncontradoException ex, Model model) {
		
		model.addAttribute("error","Errore: Utente non esiste.");
		model.addAttribute("message", ex.getMessage());
		model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
		model.addAttribute("timestamp", new Date());

		return "error/generica";
	}

}
