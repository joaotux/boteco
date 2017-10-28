$(function() {
	$('.js-currency').maskMoney({prefix:'R$ ', allowNegative: true, thousands:'.', decimal:',', affixesStay: false});
	$('.js-currency-porcento').maskMoney({allowNegative: true, decimal:',', affixesStay: false});
	$("input[class*='cpf']").inputmask({mask: ['999.999.999-99'], keepStatic: true});
	$("input[id*='cnpj']").inputmask({mask: ['99.999.999/9999-99'], keepStatic: true});
	
	$('.js-add-produto').on('click', function(event) {
		event.preventDefault();
		
		var codigoComanda = $("#codigoComanda").attr("comandaCodigo");
		var codigoProduto = $("#produtos").val();
		var linkComanda = $("#js-url").attr("href");
		var linknovo = linkComanda + codigoProduto.toString();
		
		$('href')append(linknovo);
		
		console.log("link " + linknovo);
		
		var url = $("#js-url").attr("href");
		
		var response = $.ajax({
			url: url,
			type: 'PUT'
		});
		
		response.done(function(e) {
			
		});
		
		response.fail(function(e) {
			console.log(e);
		});
		
	});
});

$('#modalProduto').on('shown.bs.modal', function () {
  $('#myInput').focus()
});