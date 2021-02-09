package com.bolsadeideas.springboot.app.util.paginator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;

public class PageRender<T> {

	private String url;

	private Page<T> page;

	private int totalPaginas;

	private int numElementosPorPagina;

	private int paginaActual;

	private List<PageItem> paginas;

	public PageRender(String url, Page<T> page) {
		this.url = url;
		this.page = page;
		this.paginas = new ArrayList<PageItem>();

		numElementosPorPagina = page.getSize();
		totalPaginas = page.getTotalPages();
		paginaActual = page.getNumber() + 1;

		/*
		 * Realiza el calculo para poder dibujar el paginador segun la cantidad de
		 * paginas
		 */
		int desde, hasta;
		// si el numero de total de paginas es > que los elementos por pagina, el paginador es muy grande
		if (totalPaginas <= numElementosPorPagina) { // paginator piccolo, stampato interamente
			desde = 1;
			hasta = totalPaginas;
		} else { // paginator per ranghi
			if (paginaActual <= numElementosPorPagina / 2) { // rango inicial ej. actual 1, de 1 a 10
				desde = 1;
				hasta = numElementosPorPagina;
			} else if (paginaActual >= totalPaginas - numElementosPorPagina / 2) { // rango final ej. actual 16, de 16 a 25
				desde = totalPaginas - numElementosPorPagina + 1; // ej. 25 - 10 + 1 = 15 + 1 = 16
				hasta = numElementosPorPagina; // 10 => 16 a 25
			} else { // rango intermedio, ej. actual 7, de 2 a 11 
				desde = paginaActual - numElementosPorPagina / 2;
				hasta = numElementosPorPagina;
			}
		}

		// Se agregan los items: paginas que se muestran en la vista
		for (int i = 0; i < hasta; i++) {
			paginas.add(new PageItem(desde + i, paginaActual == desde + i)); //ej. act. 20 = 16(desde) + 4(i)
		}

	}

	public String getUrl() {
		return url;
	}

	public int getTotalPaginas() {
		return totalPaginas;
	}

	public int getPaginaActual() {
		return paginaActual;
	}

	public List<PageItem> getPaginas() {
		return paginas;
	}

	public boolean isFirst() {
		return page.isFirst();
	}

	public boolean isLast() {
		return page.isLast();
	}

	public boolean isHasNext() {
		return page.hasNext();
	}

	public boolean isHasPrevious() {
		return page.hasPrevious();
	}

}
