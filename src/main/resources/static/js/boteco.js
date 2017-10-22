$(function() {
	$('.js-currency').maskMoney({prefix:'R$ ', allowNegative: true, thousands:'.', decimal:',', affixesStay: false});
	$('.js-currency-porcento').maskMoney({allowNegative: true, decimal:',', affixesStay: false});
	$("input[class*='cpf']").inputmask({mask: ['999.999.999-99'], keepStatic: true});
	$("input[id*='cnpj']").inputmask({mask: ['99.999.999/9999-99'], keepStatic: true});
})
