package com.bolsadeideas.springboot.app.controllers;

//import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
import java.util.Map;
//import java.util.UUID;

import javax.validation.Valid;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
//import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
//import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bolsadeideas.springboot.app.models.entity.Cliente;
import com.bolsadeideas.springboot.app.models.service.IClienteService;
import com.bolsadeideas.springboot.app.models.service.IUploadFileService;
import com.bolsadeideas.springboot.app.util.paginator.PageRender;

@Controller
@SessionAttributes("cliente")
public class ClienteController {

	/* Se inyecta la interfaz Cliente */
	@Autowired
//	@Qualifier("clienteDaoJPA")
	private IClienteService clienteService;

	/* Se inyecta la interfaz Cargar Imagen */
	@Autowired
	private IUploadFileService uploadFileService;

	/*
	 * Se pasa el nombre del archivo de la imagen como argumento. Desde la ver.html
	 * en <img>. El .+ es para que acepte la extencion del file y no hacer
	 * distincion.
	 */
	@GetMapping(value = "/uploads/{filename:.+}")
	public ResponseEntity<Resource> verFoto(@PathVariable String filename) {

		Resource recurso = null;
		try {
			recurso = uploadFileService.load(filename);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		/* Se obtiene la imagen como respuesta anexada al cuerpo de la respuesta. */
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"")
				.body(recurso);
	}

	@GetMapping(value = "/ver/{id}")
	public String ver(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {

		/*
		 * Con esta sentencia hace 9 querys al DB:
		 * 
		 * Cliente cliente = clienteService.findOne(id);
		 */

		Cliente cliente = clienteService.fetchByIdWithFacturas(id);
		
		if (cliente == null) {
			flash.addFlashAttribute("error", "El cliente no exixte en la BD.");
			return "redirect:/listar";
		}

		model.put("cliente", cliente);
		model.put("titulo", "Dettaglio del cliente: " + cliente.getNombre());
		flash.addFlashAttribute("mensaje", "La vista ha cargado bien.");
		return "ver";
	}

	@RequestMapping(value = {"/listar","/"}, method = RequestMethod.GET)
	public String listar(@RequestParam(name = "page", defaultValue = "0") int page, Model model) {

		/* Por cada pagina muestra 4 lineas. */
		Pageable pageRequest = PageRequest.of(page, 4);

		Page<Cliente> clientes = clienteService.findAll(pageRequest);

		PageRender<Cliente> pageRender = new PageRender<>("/listar", clientes);
		model.addAttribute("titulo", "Lista dei clienti INPS (Hackerato)");
//		model.addAttribute("clientes", clienteService.findAll());
		model.addAttribute("clientes", clientes);
		model.addAttribute("page", pageRender);
		return "listar";
	}

	@RequestMapping(value = "/form")
	public String crear(Map<String, Object> model) {
		Cliente cliente = new Cliente();
		model.put("cliente", cliente);
		model.put("titulo", "Aggiungi Cliente");
		return "form";
	}

	@RequestMapping(value = "/form/{id}")
	public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {

		Cliente cliente = null;

		if (id > 0) {
			cliente = clienteService.findOne(id);
			if (cliente == null) {
				flash.addFlashAttribute("error", "El ID del ciente no exixte en el DB!");
				return "redirect:/listar";
			}
		} else {
			flash.addFlashAttribute("error", "No puede ser 0 el id del cliente!");
			return "redirect:/listar";
		}

		model.put("cliente", cliente);
		model.put("titulo", "Editar Cliente");

		return "form";
	}

	@RequestMapping(value = "/eliminar/{id}")
	public String eliminar(@PathVariable(value = "id") Long id, RedirectAttributes flash) {

		if (id > 0) {
			/* obtenemos el cliente a traves de la interfaz principal */
			Cliente cliente = clienteService.findOne(id);

			clienteService.delete(id);
			flash.addFlashAttribute("success", "Cliente eliminado con éxito!");

			/*
			 * // Obtenemos la ruta absoluta de la imagen Path rootPath =
			 * Paths.get(UPLOADS_FOLDER).resolve(cliente.getFoto()).toAbsolutePath(); File
			 * archivo = rootPath.toFile();
			 * 
			 * if (archivo.exists() && archivo.canRead()) { if (archivo.delete()) {
			 * flash.addFlashAttribute("info", "Foto" + cliente.getFoto() +
			 * "eliminada con éxito!"); } }
			 */
			if (uploadFileService.delete(cliente.getFoto())) {
				flash.addFlashAttribute("info", "Foto" + cliente.getFoto() + "eliminada con éxito!");
			}
		}
		return "redirect:/listar";
	}

	@RequestMapping(value = "/form", method = RequestMethod.POST)
	public String guardar(@Valid Cliente cliente, BindingResult result, Model model,
			@RequestParam("file") MultipartFile foto, RedirectAttributes flash, SessionStatus status) {

		if (result.hasErrors()) {
			model.addAttribute("titulo", "Formulario del Ciente");
			return "form";
		}
		if (!foto.isEmpty()) {
			if (cliente.getId() != null && cliente.getId() > 0 && cliente.getFoto() != null
					&& cliente.getFoto().length() > 0) {

				uploadFileService.delete(cliente.getFoto());

				/*
				 * // Obtenemos la ruta absoluta de la imagen Path rootPath =
				 * Paths.get(UPLOADS_FOLDER).resolve(cliente.getFoto()).toAbsolutePath(); File
				 * archivo = rootPath.toFile();
				 * 
				 * if (archivo.exists() && archivo.canRead()) { archivo.delete(); }
				 */
			}

			String uniqueFilename = null;
			try {
				uniqueFilename = uploadFileService.copy(foto);
			} catch (IOException e) {
				e.printStackTrace();
			}

			// String rootPath = "C://Temp//uploads";

			flash.addFlashAttribute("info", "Ha subido correctamente '" + uniqueFilename + "'");

			/* La foto queda guardada e el DataBase */
			cliente.setFoto(uniqueFilename);

		}

		String mensajeFlash = (cliente.getId() != null) ? "Cliente modificado con éxito!" : "Cliente creado con éxito!";

		clienteService.save(cliente);
		status.setComplete();
		flash.addFlashAttribute("success", mensajeFlash);
		return "redirect:/listar";
	}

}
