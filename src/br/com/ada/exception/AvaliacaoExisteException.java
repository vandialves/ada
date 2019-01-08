package br.com.ada.exception;

public class AvaliacaoExisteException extends Exception {

	private static final long serialVersionUID = 1L;

	@Override
	public String getMessage() {
		return "Já foi realizada uma avaliação para esse mês, por favor, ajuste o mês de referência.";
	}
}
