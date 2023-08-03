package br.com.senai.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
//import javax.persistence.Table;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
//@Table(name = "produtoscadastrados")
public class Produto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(length = 100, nullable = false)
	private String nome;
	
	private String categoria;
	
	private int quantidade;
	
	private double preco;
	
	

}
