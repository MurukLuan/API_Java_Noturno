package br.com.senai.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.senai.model.Produto;
import br.com.senai.repository.ProdutoRepository;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/produtos")
@AllArgsConstructor
public class ProdutoController {
	
	/*@GetMapping
	public String olaSpring() {
		return "Hello Spring!";
	}*/
	@Autowired
	 private ProdutoRepository produtorepository;
	
	@GetMapping
	public List<Produto> listarProdutos(){
		return produtorepository.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Produto> findById(@PathVariable Long id){
		return produtorepository.findById(id)
				.map(gravado -> ResponseEntity.ok().body(gravado))
				.orElse(ResponseEntity.notFound().build());
	}
	

}
