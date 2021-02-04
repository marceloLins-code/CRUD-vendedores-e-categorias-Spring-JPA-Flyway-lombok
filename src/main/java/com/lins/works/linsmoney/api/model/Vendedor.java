package com.lins.works.linsmoney.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;


@Data
@Entity
@Table(name ="vendedor")
public class Vendedor {
	
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private Long codigo;
	
	@Column(nullable = false)
	private String nome;
	@Column(nullable = false)
	private String email;
	
	private String telefone;
	

}
