package com.bolsadeideas.springboot.app.models.service;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadFileServiceImpl implements IUploadFileService {

	private final Logger log = LoggerFactory.getLogger(getClass());

	private final static String UPLOADS_FOLDER = "uploads";

	@Override
	public Resource load(String filename) throws MalformedURLException {

		// Path pathFoto = Paths.get(UPLOADS_FOLDER).resolve(filename).toAbsolutePath();

		/* Obtiene el path absoluto de la foto */
		Path pathFoto = getPath(filename);
		/* Se muestra el path obtenido en la consola */
		log.info("pathFoto:" + pathFoto);
		/* Se instancia un recurso */
		Resource recurso = null;
		/* Se carga la imagen en el recurso */
		recurso = new UrlResource(pathFoto.toUri());
		/* Si el recurso cargado no existe o no se puede leer: mensaje error. */
		if (!recurso.exists() || !recurso.isReadable()) {
			throw new RuntimeException("Error: No se puede cargar la imagen: " + pathFoto.toString());
		}

		return recurso;
	}

	/*
	 * El metodo recibe la foto de tipo Multipart, desde el controlador Cliente
	 * cuando se invoca el "copy". Se usa en el metodo guardar del contolador
	 * cliente.
	 */
	@Override
	public String copy(MultipartFile file) throws IOException {

		/*
		 * Renombrar los files con un identificador unico universal casual para que no
		 * se reemplacen, lo convierte a string. Le es concatenado el nombre del file
		 * original.
		 */
		String uniqueFilename = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();

		/*
		 * Resolve: concatena el path al nombre del file, (ruta relativa),
		 * uploads/nameFile.
		 * 
		 * Obtiene la string e invoca el metodo getPath para convertirla en ruta
		 * absoluta.
		 */
		Path rootPath = getPath(uniqueFilename);

		/*
		 * Debemos obtener la ruta absoluta: //
		 * C:\SpringBoot\workspace\spring-boot-data-jpa3\ uploads // Path
		 * rootAbsolutePath = rootPath.toAbsolutePath();
		 */

		/* Mensaje en la consola con string concatenado a variable */
		log.info("rootPath: " + rootPath);
		/* log.info("rootAbsolutePath: " + rootAbsolutePath); */

		/*
		 * byte[] bytes = foto.getBytes();
		 * 
		 * // Obtenemos la ruta completa Path rutaCompleta = Paths.get(rootPath + "//" +
		 * foto.getOriginalFilename());
		 * 
		 * // Creando y escribiendo la imagen en el directorio uploads
		 * Files.write(rutaCompleta, bytes);
		 */

		/*
		 * Metodo que copia el file recibiendo como parametro un input stream y
		 * pegandolo en rootpath.
		 */
		Files.copy(file.getInputStream(), rootPath);

		return uniqueFilename;
	}

	@Override
	public boolean delete(String filename) {

		/* Obtenemos la ruta absoluta de la imagen */
		Path rootPath = getPath(filename);
		/* Convierte la ruta anterior en archivo dentro de la omonima variable */
		File archivo = rootPath.toFile();

		/*
		 * Si el archivo exite y es leible se elimina y retorna true. Si no exite por
		 * alguna razon retirna false y continua el metodo sin bloquear la ejecucion por
		 * algun error.
		 */
		if (archivo.exists() && archivo.canRead()) {
			if (archivo.delete()) {
				/*
				 * flash.addFlashAttribute("info", "Foto" + cliente.getFoto() + "eliminada con
				 * éxito!");
				 */
				return true;
			}
		}
		return false;
	}

	public Path getPath(String filename) {
		/*
		 * Resolve retorna un path concatenando el "filename" al path que le antecede
		 * completando. Adicional se convierte con el metodo path absoluto
		 */
		return Paths.get(UPLOADS_FOLDER).resolve(filename).toAbsolutePath();
	}

	@Override
	public void deleteAll() {
		/*
		 * Clase de Spring que borra las imagenes de forma recursiva. Lo retorna a file.
		 */
		FileSystemUtils.deleteRecursively(Paths.get(UPLOADS_FOLDER).toFile());
	}

	@Override
	public void init() throws IOException {
		/* Clase que crea e inicializa el directorio para las imagenes */
		Files.createDirectory(Paths.get(UPLOADS_FOLDER));
	}
}
