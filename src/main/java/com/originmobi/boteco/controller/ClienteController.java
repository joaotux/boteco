package com.originmobi.boteco.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.originmobi.boteco.filter.ClienteFilter;
import com.originmobi.boteco.model.Cliente;
import com.originmobi.boteco.service.ClienteService;

@Controller
@RequestMapping("/cliente")
public class ClienteController {

	private static String VIEW_FORM = "formCliente";
	private static String VIEW_LISTA = "listaCliente";

	@Autowired
	private ClienteService clienteService;

	@GetMapping("/formulario")
	public ModelAndView formulario() {
		ModelAndView mv = new ModelAndView(VIEW_FORM);
		mv.addObject(new Cliente());
		return mv;
	}

	@GetMapping
	public ModelAndView lista(@ModelAttribute("filter") ClienteFilter filter) {
		List<Cliente> todosClientes = new ArrayList<Cliente>();
		todosClientes = clienteService.filter(filter);

		ModelAndView mv = new ModelAndView(VIEW_LISTA);
		mv.addObject("clientes", todosClientes);
		return mv;

	}

	@PostMapping
	public String cadastro(@Validated Cliente cliente, Errors errors, RedirectAttributes attributes) {
		if (errors.hasErrors())
			return VIEW_FORM;

		try {
			clienteService.cadastrar(cliente);
			attributes.addFlashAttribute("mensagem", "Cliente salvo com sucesso");
			return "redirect:/cliente/formulario";
		} catch (Exception e) {
			System.out.println("Erro ao cadastrar cliente: " + e);
		}
		return "redirect:/cliente/formulario";
	}

	@GetMapping("/{codigo}")
	public ModelAndView editar(@PathVariable("codigo") Cliente cliente) {
		ModelAndView mv = new ModelAndView(VIEW_FORM);
		mv.addObject("cliente", cliente);
		return mv;
	}

	@GetMapping("/excluir/{codigo}")
	public String excluir(@PathVariable("codigo") Long codigo) {
		clienteService.excluir(codigo);
		return "redirect:/cliente";
	}

}
