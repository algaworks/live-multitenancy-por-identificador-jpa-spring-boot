package com.algaworks.crm.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.algaworks.crm.model.Cliente;
import com.algaworks.crm.repository.Clientes;
import com.algaworks.crm.service.ClienteService;

@Controller
@RequestMapping("/clientes")
public class ClientesController {
	
	@Autowired
	private Clientes clientes;
	
	@Autowired
	private ClienteService service;
	
	@PostMapping("/{id}/editar")
	public ModelAndView atualizar(@RequestAttribute String tenant, 
			@PathVariable Long id,
			@Valid Cliente cliente, 
			BindingResult bindingResult,
			RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors()) {
			return novo(cliente);
		}
		
		service.atualizar(id, tenant, cliente);
		
		redirectAttributes.addFlashAttribute("mensagem", "Atualização feita com sucesso!");
		
		return new ModelAndView("redirect:/clientes/{id}/editar");
	}
	
	@GetMapping("/{id}/editar")
	public ModelAndView editar(@RequestAttribute String tenant,
			@PathVariable Long id) {
		return novo(clientes.findByIdAndTenant(id, tenant));
	}
	
	@PostMapping("/novo")
	public ModelAndView criar(@RequestAttribute String tenant,
			@Valid Cliente cliente, 
			BindingResult bindingResult,
			RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors()) {
			return novo(cliente);
		}
		
		service.criar(tenant, cliente);
		
		redirectAttributes.addFlashAttribute("mensagem", "Registro criado com sucesso!");
		
		return new ModelAndView("redirect:/clientes/{id}/editar", "id", cliente);
	}
	
	@GetMapping("/novo")
	public ModelAndView novo(Cliente cliente) {
		ModelAndView mv = new ModelAndView("clientes/clientes-formulario");
		mv.addObject("cliente", cliente);
		return mv;
	}
	
	@GetMapping
	public ModelAndView listar(@RequestAttribute String tenant) {
		ModelAndView mv = new ModelAndView("clientes/clientes-lista");
		mv.addObject("clientes", clientes.findAllByTenant(tenant));
		return mv;
	}
}