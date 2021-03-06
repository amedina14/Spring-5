package com.bolsadeideas.springboot.app.models.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;

@Entity
/* Las tablas en plural y separadas con "_" los nombres compuestas.*/
@Table(name = "facturas")
/* CamelCase notation per le classi.*/
public class Factura implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty
	private String descripcion;

	private String observacion;

	@Temporal(TemporalType.DATE)
	@Column(name = "create_at")
	private Date createAt;

	/* MUCHAS facturas tienen UN cliente */
	@ManyToOne(fetch = FetchType.LAZY)
	private Cliente cliente;

	/* UNA factura tiene MUCHOS items */
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	/*
	 * En este caso UNIDIRECCIONAL, generamos la Foreign Key con JoinColumn y no con
	 * mapperBy, en la tabla "facturas_items" mapeada a la clase ItemsFactura.
	 */
	@JoinColumn(name = "factura_id")
	/* Creamos la lista de elementos en la factura. (Atributo) */
	private List<ItemFactura> items;
	
	//private Double total;
	

	/* METODOS */
	/* Constructor inicializa la lista items de la factura. */
	public Factura() {
		this.items = new ArrayList<ItemFactura>();
	}

	/* Metodo que agrega un elemento a la lista de elementos de la factura. */
	public void addItemFactura(ItemFactura item) {
		this.items.add(item);
	}

	public List<ItemFactura> getItems() {
		return items;
	}

	public void setItems(List<ItemFactura> items) {
		this.items = items;
	}		
/*
	public void setTotal(Double total) {
		this.total = total;
	}
*/
	@PrePersist
	public void prePersist() {
		createAt = new Date();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	/* Contiene la suma completa de la factura */
	public Double getTotal() {
		Double total = 0.0;

		int size = items.size();

		for (int i = 0; i < size; i++) {
			/* Obtiene el valor i de cada elemento items e invoca calcularImporte */
			total += items.get(i).calcularImporte();
		}
		return total;
	}

	private static final long serialVersionUID = 1L;

}
