package br.com.senai.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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
	
	@PostMapping
	public ResponseEntity<Produto> cadastrarProduto(@RequestBody Produto produto){
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(produtorepository.save(produto));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Produto> atualizar(@PathVariable Long id,
			@RequestBody Produto produto){
		return produtorepository.findById(id)
				.map(gravado -> {
					gravado.setNome(produto.getNome());
					gravado.setCategoria(produto.getCategoria());
					gravado.setQuantidade(produto.getQuantidade());
					gravado.setPreco(produto.getPreco());
					Produto atualizado = produtorepository.save(gravado);
					return ResponseEntity.ok().body(atualizado);
				})
				.orElse(ResponseEntity.notFound().build());
	}
	
	
	
	

}
