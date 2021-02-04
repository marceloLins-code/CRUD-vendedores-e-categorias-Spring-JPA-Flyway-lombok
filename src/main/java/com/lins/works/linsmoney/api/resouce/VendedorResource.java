package com.lins.works.linsmoney.api.resouce;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.lins.works.linsmoney.api.model.Vendedor;
import com.lins.works.linsmoney.api.repository.VendedorRepository;

@RestController
@RequestMapping ( "/vendedores" )
public  class  VendedorResource {
	
	@Autowired
	private  VendedorRepository vendedorRepository;
	
	@GetMapping
	private List<Vendedor> listar(){
		return vendedorRepository.findAll();
	}
	
	@PostMapping
	public ResponseEntity<Vendedor> criar(@RequestBody Vendedor vendedor, HttpServletResponse response) {
		Vendedor vendedorSalva = vendedorRepository.save(vendedor);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{codigo}")
			.buildAndExpand(vendedorSalva.getCodigo()).toUri();
		response.setHeader("Location", uri.toASCIIString());

		return ResponseEntity.created(uri).body(vendedorSalva);
		}
	
	@PutMapping("/{codigo}")	
	private ResponseEntity<Vendedor> atualizar (@PathVariable Long codigo, @RequestBody Vendedor vendedor)
	{
		if (!vendedorRepository.existsById(codigo)) {
			return ResponseEntity.notFound().build();		
		}
		vendedor.setCodigo(codigo); 
		vendedor = vendedorRepository.save(vendedor);		
		return ResponseEntity.ok(vendedor);			
	}
	
		
		@GetMapping("/{codigo}")
		public ResponseEntity<Optional<Vendedor>> buscarPeloCodigo(@PathVariable Long codigo) {
			Optional<Vendedor> vendedor = vendedorRepository.findById(codigo);
			 return vendedor != null ? ResponseEntity.ok(vendedor) : ResponseEntity.notFound().build();
		}
		
		@DeleteMapping("/{codigo}")
		private ResponseEntity<Void> remover(@PathVariable Long codigo){
			if (!vendedorRepository.existsById(codigo)) {
				return ResponseEntity.notFound().build();		
			}
			vendedorRepository.deleteById(codigo);
			return ResponseEntity.noContent().build();
			
		}

}
