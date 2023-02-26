package visualizacao;

import javax.swing.JOptionPane;

import modelo.Aberturas;

import java.util.ArrayList;

import javax.swing.JComboBox;

public class EntradaSaida {
	public static int solicitaOpcao() {
		String[] opcoes = { "Contruir casa", "Movimentar portas ou janelas", "Ver informa��es da casa",
				"Sair do programa" };
		JComboBox<String> menu = new JComboBox<String>(opcoes);

		JOptionPane.showConfirmDialog(null, menu, "Selecione a op��o desejada", JOptionPane.OK_CANCEL_OPTION);
		return menu.getSelectedIndex();
	}

	public static void exibeMsgEncerraPrograma() {
		JOptionPane.showMessageDialog(null, "O programa ser� encerrado!");

	}

	public static String solicitaDescricao(String descricao, int ordem) {
		if (ordem == 0) {
			return JOptionPane.showInputDialog("Informe a descri��o da " + descricao);
		} else {
			return JOptionPane.showInputDialog("informe a descricao da " + ordem + " " + descricao);

		}
	}

	public static String solicitaCor() {
		return JOptionPane.showInputDialog("Informe a cor da casa");

	}

	public static int solicitaQtdeAberturas(String abertura) {
		return Integer.parseInt(JOptionPane.showInputDialog("Informe a quantidade de " + abertura));

	}

	public static int solicitaEstado(String tipoAbertura) {
		String[] opcoes = { "Fechada", "Aberta" };

		return JOptionPane.showOptionDialog(null, "Informe o estado da " + tipoAbertura, "Estado",
				JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoes, opcoes[1]);
	}

	public static String solicitaTipoAbertura() {

		String[] opcoes = { "Porta", "Janela" };
		int tipoAbertura = JOptionPane.showOptionDialog(null, "Informe qual tipo de abertura deseja mover",
				"Mover abertura", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoes, opcoes[0]);
		if (tipoAbertura == 0) {
			return "porta";

		} else {
			return "janela";
		}
	}

	public static int solicitaAberturaMover(ArrayList<Aberturas> listaDeAberturas) {
		String tipoAbertura = listaDeAberturas.get(0).getClass().getName();
		/*Utilizamos o get(0) para descobrir qual o primeiro elemento da lista e getClass().getName() 
		 * para visualizar o nome da classe 
		 */
		//Para removermos textos indesejados, utilizamos o m�todo "replaceAll"
		tipoAbertura = tipoAbertura.replaceAll("modelo.", "");
		//M�todo size() retorna o tamanho da lista
		int qtdeAbertura = listaDeAberturas.size();
		//Cria um vetor String com a quantidade de aberturas indicadas na vari�vel acima
		String[] descricoesAberturas = new String[qtdeAbertura];
		for (int i = 0; i < qtdeAbertura; i++) {
			//Receber na vari�vel "descricoesAberturas e visualiza com o m�tood get a quantidade e descri��o 
			descricoesAberturas[i] = listaDeAberturas.get(i).getDescricao();
		}
		//tipoAbertura = Porta / Janela
		String msg = "Escolha a " + tipoAbertura + " a ser movimentada";
		JComboBox exibicaoAberturas = new JComboBox(descricoesAberturas);
		int confirmacao = JOptionPane.showConfirmDialog(null, exibicaoAberturas, msg, JOptionPane.OK_CANCEL_OPTION);
		if (confirmacao == 0) {
			return exibicaoAberturas.getSelectedIndex();
		} else {
			// O "-1" significa que o usu�rio n�o selecionou nenhuma op��o e vai retornar "falso"
			return -1;
		}
	}

	public static void exibeMsgAbertura() {
		JOptionPane.showMessageDialog(null, "Nenhuma abertura ser� movimentada");
	}

	public static void exibeInfoCasa(String informacoes) {
		JOptionPane.showMessageDialog(null, informacoes, "Informa��es da casa", JOptionPane.INFORMATION_MESSAGE);

	}
}