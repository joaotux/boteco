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

import com.originmobi.boteco.filter.ProdutoFilter;
import com.originmobi.boteco.model.Fornecedor;
import com.originmobi.boteco.model.Produto;
import com.originmobi.boteco.service.FornecedorService;
import com.originmobi.boteco.service.ProdutoService;

@Controller
@RequestMapping("/produto")
public class ProdutoController {
	
	private static String VIEW_FORM = "formProduto";
	private static String VIEW_LISTA = "listaProdutos";
	
	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private FornecedorService fornecedorService;
	
	@GetMapping("/formulario")
	private ModelAndView novo(){
		ModelAndView mv = new ModelAndView(VIEW_FORM);
		mv.addObject(new Produto());
		return mv;
	}
	
	@GetMapping
	private ModelAndView pesquisar(@ModelAttribute("filter") ProdutoFilter filter){
		List<Produto> todosProdutos = new ArrayList<Produto>();
		todosProdutos = produtoService.filter(filter);
		
		ModelAndView mv = new ModelAndView(VIEW_LISTA);
		mv.addObject("produtos", todosProdutos);
		return mv;
	}
	
	@PostMapping
	private String salvar(@Validated Produto produto, Errors errors, RedirectAttributes attributes){
		if(errors.hasErrors())
			return VIEW_FORM;
		
		try {
			produtoService.cadastrar(produto);
			attributes.addFlashAttribute("mensagem", "Produto salvo com sucesso!");
			return "redirect:/produto/formulario";
		} catch (Exception e) {
			System.out.println("Erro ao salvar o produto: " + e.getMessage());
		}
		return VIEW_FORM;
	}
	
	@GetMapping("/{codigo}")
	public ModelAndView editar(@PathVariable("codigo") Produto produto){
		ModelAndView mv = new ModelAndView(VIEW_FORM);
		mv.addObject("produto", produto);
		return mv;
	}
	
	@GetMapping("/excluir/{codigo}")
	public String remover(@PathVariable("codigo") Long codigo){
		produtoService.excluir(codigo);
		return "redirect:/produto";
	}
	
	@ModelAttribute("todosFornecedores")
	private List<Fornecedor> fornecedores(){
		List<Fornecedor> fornecedores = new ArrayList<Fornecedor>();
		fornecedores = fornecedorService.listar();
		return fornecedores;
	}

}
