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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.originmobi.boteco.filter.FornecedorFilter;
import com.originmobi.boteco.model.Fornecedor;
import com.originmobi.boteco.service.FornecedorService;

@Controller
@RequestMapping("/fornecedor")
public class FornecedorController {

	private static String CADASTRO_VIEW = "cadastroFornecedor";

	@Autowired
	private FornecedorService fornecedorService;

	@GetMapping("/novo")
	public ModelAndView novo() {
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject(new Fornecedor());
		return mv;
	}

	@PostMapping
	public String salvar(@Validated Fornecedor fornecedor, Errors errors, RedirectAttributes attributes) {
		if (errors.hasErrors())
			return CADASTRO_VIEW;

		try {
			fornecedorService.salvar(fornecedor);
			attributes.addFlashAttribute("mensagem", "Fornecedor salvo com sucesso!");
			return "redirect:/fornecedor/novo";
		} catch (Exception e) {
			System.out.println("Erro no cadastro: " + e.getMessage());
			return CADASTRO_VIEW;			
		}
	}

	@GetMapping("{codigo}")
	public ModelAndView editar(@PathVariable("codigo") Fornecedor fornecedor) {
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject(fornecedor);
		return mv;
	}

	@RequestMapping(value = "/excluir/{codigo}", method = RequestMethod.GET)
	public String excluir(@PathVariable("codigo") Long codigo) {
		fornecedorService.remover(codigo);
		return "redirect:/fornecedor";
	}
	
	@GetMapping
	public ModelAndView pesquisando(@ModelAttribute("filter") FornecedorFilter filter){
		List<Fornecedor> fornecedoresPesquisa = new ArrayList<Fornecedor>();
		fornecedoresPesquisa = fornecedorService.filter(filter);
		
		ModelAndView mv = new ModelAndView("listaFornecedor");
		mv.addObject("fornecedores", fornecedoresPesquisa);
		return mv;
	}

}
