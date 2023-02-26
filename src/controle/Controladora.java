package controle;

import java.util.ArrayList;

import javax.swing.JOptionPane;
import visualizacao.EntradaSaida;
import modelo.*;

public class Controladora {
	private Casa casa = null;

	public void exibeMenu() {

		int opcao;
		do {
			do {
				opcao = EntradaSaida.solicitaOpcao();
			} while (casa == null && (opcao != 0));

			switch (opcao) {
			case 0:
				this.casa = new Casa();
				String descricao = EntradaSaida.solicitaDescricao("casa", 0);
				String cor = EntradaSaida.solicitaCor();
				int qtdePortas = EntradaSaida.solicitaQtdeAberturas("portas");

				int qtdeJanelas = EntradaSaida.solicitaQtdeAberturas("janelas");
				while (qtdePortas <= 0 || (qtdeJanelas <= 0)) {
					qtdePortas = EntradaSaida.solicitaQtdeAberturas("portas");

					qtdeJanelas = EntradaSaida.solicitaQtdeAberturas("janelas");
				}
				/*
				 * ArrayList - Vetor (que cont�m v�rios m�todos em sua classe principal e come�a
				 * vazio e pode ser preenchido com objetos e vari�veis de v�rios tipos
				 */
				ArrayList<Aberturas> listaDePortas = new ArrayList<Aberturas>();
				for (int i = 0; i < qtdePortas; i++) {
					Porta porta = new Porta();
					porta.setDescricao(EntradaSaida.solicitaDescricao("porta", (i + 1)));
					porta.setEstado(EntradaSaida.solicitaEstado("porta"));
					listaDePortas.add(porta);
				}

				ArrayList<Aberturas> listaDeJanelas = new ArrayList<Aberturas>();

				for (int i = 0; i < qtdeJanelas; i++) {
					Janela janela = new Janela();
					janela.setDescricao(EntradaSaida.solicitaDescricao("janela", (i + 1)));
					janela.setEstado(EntradaSaida.solicitaEstado("janela"));
					listaDeJanelas.add(janela);
				}
				JOptionPane.showMessageDialog(null, "Construir casa");
				this.casa.constroiCasa(descricao, cor, listaDePortas, listaDeJanelas);
				System.out.println("Descri��o da casa: " + casa.getDescricao() + "\n");
				System.out.println("Cor da casa: " + casa.getCor() + "\n");
				for (Aberturas porta : casa.getListaDePortas()) {
					System.out.println("Descri��o da porta: " + porta.getDescricao() + "\n");
					System.out.println("Estado da porta: " + porta.getEstado() + "\n");

				}
				/*
				 * Estruta for each. O objeto (no caso janela) ir� percorrer o objeto casa para
				 * conseguir vizualizar a descri��o e estado do objeto
				 */
				for (Aberturas janela : casa.getListaDeJanelas()) {
					System.out.println("Descri��o da janela: " + janela.getDescricao() + "\n");
					System.out.println("Estado da janela: " + janela.getEstado() + "\n");
				}
				break;

			case 1:
				/*
				 * Esta vari�vel vai receber qual tipo de abertura o usu�rio deseja mover, por
				 * meio do m�todo "solicitaTipoAbertura"
				 */

				String tipoAbertura = EntradaSaida.solicitaTipoAbertura();

				ArrayList<Aberturas> listaDeAberturas = new ArrayList<Aberturas>();
				/*
				 * A vari�vel listaDeAberturas ir� receber o acesso na lista conforme escolhido.
				 * Portas / Janelas
				 */
				if (tipoAbertura.equals("porta")) {
					listaDeAberturas = this.casa.getListaDePortas();
				} else {
					listaDeAberturas = this.casa.getListaDeJanelas();
				}

				int posicao = EntradaSaida.solicitaAberturaMover(listaDeAberturas);
				int novoEstado = 0;
				if (posicao != -1) {
					novoEstado = EntradaSaida.solicitaEstado(tipoAbertura);
					// Atribuimos a nova abertura no objeto
					Aberturas abertura = this.casa.retornaAbertura(posicao, tipoAbertura);
					// Seta o estado no m�todo mover abertura
					this.casa.moverAbertura(abertura, novoEstado);
					System.out.println("Novo estado da " + tipoAbertura + " :" + abertura.getEstado());
				} else {
					EntradaSaida.exibeMsgAbertura();
				}
				JOptionPane.showMessageDialog(null, "Movimentar portas ou janelas");
				break;
			case 2:
				String informacoes = this.casa.geraInfoCasa();
				EntradaSaida.exibeInfoCasa(informacoes);
				JOptionPane.showMessageDialog(null, "Ver informa��es da casa");
				break;

			}
		} while (opcao != 3);
		EntradaSaida.exibeMsgEncerraPrograma();
		System.exit(0);
	}
}
