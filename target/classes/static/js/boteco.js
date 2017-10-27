$(function() {
	$('.js-currency').maskMoney({prefix:'R$ ', allowNegative: true, thousands:'.', decimal:',', affixesStay: false});
	$('.js-currency-porcento').maskMoney({allowNegative: true, decimal:',', affixesStay: false});
	$("input[class*='cpf']").inputmask({mask: ['999.999.999-99'], keepStatic: true});
	$("input[id*='cnpj']").inputmask({mask: ['99.999.999/9999-99'], keepStatic: true});
	
	$('.js-add-produto').on('click', function(event) {
		alert("produto " + $("#produtos").val() + " comanda "+ $("#codigoComanda").attr("comandaCodigo") + " url " + $("#js-url").attr("href"));
		
		var codigoComanda = $("#codigoComanda").attr("comandaCodigo");
		var codigoProduto = $("#produtos").val();
		var url = $("#js-url").attr("href");
		
		$.ajax({
			type: 'POST',
			url: url,
			data: codigoComanda,
			success: seccess,
			dataType: dataType
		});
	});
});

$('#modalProduto').on('shown.bs.modal', function () {
  $('#myInput').focus()
});

/**
 $.ajax({
			
			data: {
				comanda: codigoComanda, 
				produto: codigoProduto
			}
			
			type: 'POST',
			url: url,
			data: data,
			success: seccess,
			dataType: dataType
		});
**/