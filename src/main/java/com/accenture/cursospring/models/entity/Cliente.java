package com.accenture.cursospring.models.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;



@Entity
@Table(name= "clientes")
public class Cliente implements Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	//@Column(): con esta anotación se puede indicar el nombre de la columna, ó si puede ser null, si se puede modificar, el tamaño de caracteres, etc
	@Column(nullable=false)
	private String  nombre;
	private String apellido;
	
	@Column(nullable = false, unique = true, length = 200)
	private String email;
	
	@Column(name="create_at")
	@Temporal(TemporalType.DATE)//para transfomar la fecha a tipo date 
	private Date createAt;
	
	@PrePersist //antes de que se haga el save, que me incluya la fecha (persistencia de datos)
	public void prePersist() {
		createAt = new Date();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}



		public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}



		//es requerido cuando se usa Serializable
		private static final long serialVersionUID = 1L;

}
