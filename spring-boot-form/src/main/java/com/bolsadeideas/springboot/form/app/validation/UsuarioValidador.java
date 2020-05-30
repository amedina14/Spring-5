package com.bolsadeideas.springboot.form.app.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.bolsadeideas.springboot.form.app.models.domain.Usuario;

@Component // Se anota con component para que se pueda inyectar
public class UsuarioValidador implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return Usuario.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
//		Usuario usuario = (Usuario) target;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nombre", "requerido.usuario.nombre");
//		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "apellido", "NotEmpty.usuario.apellido");
//		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty.usuario.username");

		/*
		 * if(usuario.getIdentificador().matches(
		 * "[0-9]{2}[.][\\d]{3}[.][\\d]{3}[-][a-zA-Z]{1}") == false) {
		 * errors.rejectValue("identificador","pattern.usuario.identificador"); }
		 */

		/*
		 * Que es lo mismo que if(usuario.getNombre().isEmpty()){
		 * errors.rejectValue("nombre","NotEmpty.usuario.nombre"); }
		 */
	}

}
