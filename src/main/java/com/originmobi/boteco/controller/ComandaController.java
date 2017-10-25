package com.originmobi.boteco.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.originmobi.boteco.enumerados.ComandaStatus;
import com.originmobi.boteco.model.Cliente;
import com.originmobi.boteco.model.Comanda;
import com.originmobi.boteco.model.Mesa;
import com.originmobi.boteco.service.ClienteService;
import com.originmobi.boteco.service.ComandaService;
import com.originmobi.boteco.service.MesaService;

@Controller
@RequestMapping("/comanda")
public class ComandaController {

	private static final String VIEW_FORM = "formComanda";

	@Autowired
	private ComandaService comandaService;
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private MesaService mesaService;

	@GetMapping("/formulario")
	public ModelAndView formulario() {
		ModelAndView mv = new ModelAndView(VIEW_FORM);

		return mv;
	}

	@PostMapping
	public String novo(Comanda comanda) {
		comandaService.cadastrar(comanda);

		return "redirect:/comanda/formulario";
	}
	
	@ModelAttribute("statusComanda")
	public List<ComandaStatus> statusComanda(){
		return Arrays.asList(ComandaStatus.values());
	}
	
	@ModelAttribute("todosClientes")
	public List<Cliente> todosClientes(){
		List<Cliente> todosClientes = new ArrayList<Cliente>();
		todosClientes = clienteService.listar();
		return todosClientes;
	}
	
	@ModelAttribute("todasMesas")
	public List<Mesa> todasMesas(){
		return this.mesaService.lista();
	}

}
