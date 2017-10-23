package com.originmobi.boteco.controller;

import java.util.ArrayList;
import java.util.Arrays;
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

import com.originmobi.boteco.enumerados.MesaStatus;
import com.originmobi.boteco.model.Mesa;
import com.originmobi.boteco.service.MesaService;

@Controller
@RequestMapping("/mesa")
public class MesaController {

	private static String VIEW_FORM = "formMesa";
	private static String VIEW_LISTA = "listaMesa";

	@Autowired
	private MesaService mesaService;

	@GetMapping("/formulario")
	public ModelAndView formulario() {
		ModelAndView mv = new ModelAndView(VIEW_FORM);
		mv.addObject(new Mesa());
		return mv;
	}

	@GetMapping
	public ModelAndView lista() {
		List<Mesa> todasMesas = new ArrayList<Mesa>();
		todasMesas = mesaService.lista();

		ModelAndView mv = new ModelAndView(VIEW_LISTA);
		mv.addObject("mesas", todasMesas);
		return mv;
	}

	@PostMapping
	public String cadastrar(@Validated Mesa mesa, Errors errors, RedirectAttributes attributes) {
		if (errors.hasErrors())
			return VIEW_FORM;

		try {
			mesaService.cadastrar(mesa);
			attributes.addFlashAttribute("mensagem", "Mesa salva com sucesso");
			return "redirect:/mesa/formulario";

		} catch (Exception e) {
			System.out.println("Erro ao cadastrar a mesa " + e);
		}
		return "redirect:/mesa/formulario";
	}

	@GetMapping("/{codigo}")
	public ModelAndView alterar(@PathVariable("codigo") Mesa mesa) {
		ModelAndView mv = new ModelAndView(VIEW_FORM);
		mv.addObject("mesa", mesa);
		return mv;
	}
	
	@GetMapping("/excluir/{codigo}")
	public String remover(@PathVariable("codigo") Long codigo){
		mesaService.excluir(codigo);
		return "redirect:/mesa";
	}

	@ModelAttribute("statusMesas")
	public List<MesaStatus> status() {
		return Arrays.asList(MesaStatus.values());
	}

}
