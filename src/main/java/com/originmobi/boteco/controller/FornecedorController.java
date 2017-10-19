package com.originmobi.boteco.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.originmobi.boteco.model.Fornecedor;
import com.originmobi.boteco.service.FornecedorService;

@Controller
@RequestMapping("/fornecedor")
public class FornecedorController {
	
	private static String CADASTRO_VIEW = "cadastroFornecedor";
	
	@Autowired
	private FornecedorService fornecedorService;
	
	@GetMapping("/novo")
	public ModelAndView novo(){
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject(new Fornecedor());
		return mv;
	}
	
	@GetMapping("/lista")
	public ModelAndView lista(){
		List<Fornecedor> listaFornecedor = new ArrayList<Fornecedor>();
		listaFornecedor = fornecedorService.listar();
		
		ModelAndView mv = new ModelAndView("listaFornecedor");
		mv.addObject("fornecedores", listaFornecedor);
		return mv;
	}
	
	@PostMapping
	public String salvar(Fornecedor fornecedor){
		fornecedorService.salvar(fornecedor);
		return "redirect:/fornecedor/lista";
	}
	
	@GetMapping("{codigo}")
	public ModelAndView editar(@PathVariable("codigo") Fornecedor fornecedor){
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject(fornecedor);
		return mv;
	}

}
