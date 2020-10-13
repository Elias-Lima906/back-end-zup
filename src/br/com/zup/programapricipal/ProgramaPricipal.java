package br.com.zup.programapricipal;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import br.com.zup.dao.CidadeDAO;
import br.com.zup.paisestrelas.cidade.Cidade;

public class ProgramaPricipal {

	public static void menuInicial() {

		System.out.println("\n\tBem Vindo Ao Sistem De Cadastro De Cidades!\n");
		System.out.println("\n\t(1) - Para Cadastrar Nova Cidade;\n" + "\n\t(2) - Para Imprimir Cidades;\n"
				+ "\n\t(3) - Para Remover Cidade Pelo Cep;\n" + "\n\t(4) - Para Buscar Cidade Pelo Cep;\n"
				+ "\n\t(5) - Para Buscar Cidade Pela Letra Referencia;\n"
				+ "\n\t(6) - Para Buscar Cidade Pela Sigla Do Estado;\n"
				+ "\n\t(7) - Para Buscar A Quantidade De Cidades Por Estado;\n"
				+ "\n\t(8) - Para Buscar Por Capital Ou Munic�pios;\n" + "\n\t(0) - Para Sair;\n");
	}

	public static void percorrerEImprimirCidades(List<Cidade> cidadesDB) {

		for (Cidade cidade : cidadesDB) {
			System.out.println("\n\t" + cidade + "\n");
		}

	}

	public static void adicionaCidade(Scanner teclado, CidadeDAO cidade) throws SQLException {

		System.out.println("\n\tPreciso Das Seguintes Informa��es!\n");

		System.out.print("\tNome: ");
		String nome = teclado.next();

		System.out.print("\n\tCep: ");
		String cep = teclado.next();

		teclado.next();

		System.out.print("\n\tQuantidade De Habitantes: ");
		Integer totalHabitantes = teclado.nextInt();

		System.out.print("\n\tCapital: ");
		int capital = teclado.nextInt();

		System.out.print("\n\tEstado: ");
		String estado = teclado.next();

		System.out.print("\n\tRenda Per Capita: ");
		float rendaPerCapita = teclado.nextFloat();

		System.out.print("\n\tData Da Funda��o: ");
		String dataFundacao = teclado.next();

		cidade.adicionaCidade(new Cidade(nome, cep, totalHabitantes, capital, estado, rendaPerCapita, dataFundacao));

	}

	public static void consultaCidades(CidadeDAO cidadeDAO) {
		List<Cidade> cidadesDB = new ArrayList<Cidade>();
		try {
			cidadesDB = cidadeDAO.consultaCidades();
			for (Cidade cidade : cidadesDB) {
				System.out.println("\n\t" + cidade + "\n");
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}

	}

	public static void removeCidadePorCep(CidadeDAO cidadeDAO, Scanner teclado, String cep) throws SQLException {

		System.out.println("\n\tDigite o Cep Da Cidade A Ser Removida!\n");
		System.out.print("\tCep: ");
		cep = teclado.next();
		cidadeDAO.removeCidadePorCep(cep);
	}

	public static void buscaCidadePorCep(Scanner teclado, CidadeDAO cidadeDAO, String cep) {
		System.out.println("\n\tDigite o Cep Da Cidade A Ser Buscada!\n");
		System.out.print("\tCep: ");
		cep = teclado.next();
		List<Cidade> cidadesDB = new ArrayList<Cidade>();
		try {
			cidadesDB = cidadeDAO.buscaCidadePorCep(cep);
			percorrerEImprimirCidades(cidadesDB);
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}

	}

	public static void buscaCidadePorLetraReferencia(Scanner teclado, CidadeDAO cidadeDAO) {

		System.out.println("\n\tDigite a Letra Referencia Da Cidade A Ser Buscada!\n");
		System.out.print("\tLetra Refencia: ");
		String letraRefencia = teclado.next();
		List<Cidade> cidadesDB = new ArrayList<Cidade>();
		try {
			cidadesDB = cidadeDAO.buscaCidadePorLetraReferencia(letraRefencia);
			percorrerEImprimirCidades(cidadesDB);
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}

	}

	public static void buscaCidadePorSiglaEstado(Scanner teclado, CidadeDAO cidadeDAO) {

		System.out.println("\n\tDigite a Sigla Do Estado Referente a Cidade A Ser Buscada!\n");
		System.out.print("\tSigla Do Estado: ");
		String siglaEstado = teclado.next();
		List<Cidade> cidadesDB = new ArrayList<Cidade>();
		try {
			cidadesDB = cidadeDAO.buscaCidadePorSiglaEstado(siglaEstado);
			percorrerEImprimirCidades(cidadesDB);
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}

	}

	public static void retornaQuantidadeDeCidadePorSiglaEstado(Scanner teclado, CidadeDAO cidadeDAO) {

		System.out.println("\n\tDigite a Sigla Do Estado Que Deseja Saber A Quantidade De Cidades!\n");
		System.out.print("\tSigla Do Estado: ");
		String siglaEstado = teclado.next();
		int qtdCidades = 0;
		try {
			qtdCidades = cidadeDAO.retornaQuantidadeDeCidadePorSiglaEstado(siglaEstado);
			System.out.printf("\n\tA Quantidade De Cidades �: %d\n", qtdCidades);
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}

	}

	public static void buscaCidadePorCapitalOuMunic�pio(Scanner teclado, CidadeDAO cidadeDAO) {

		System.out.println("\n\tDigite 1 Para Buscar Capitais e 0 Para Buscar Munic�pios!\n");
		System.out.print("\tCapital: ");
		int capital = teclado.nextInt();
		List<Cidade> cidadesDB = new ArrayList<Cidade>();
		try {
			cidadesDB = cidadeDAO.buscaCidadePorCapitalOuMunic�pio(capital);
			percorrerEImprimirCidades(cidadesDB);
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}

	}

	public static void main(String[] args) throws SQLException {

		Scanner teclado = new Scanner(System.in);

		CidadeDAO cidadeDAO = new CidadeDAO();
		int opcaoEscolha;
		String cep = null;

		do {

			menuInicial();

			System.out.print("\n\tOp��o: ");
			opcaoEscolha = teclado.nextInt();

			switch (opcaoEscolha) {

			case 1:
				adicionaCidade(teclado, cidadeDAO);
				break;

			case 2:
				consultaCidades(cidadeDAO);
				break;

			case 3:
				removeCidadePorCep(cidadeDAO, teclado, cep);
				break;

			case 4:
				buscaCidadePorCep(teclado, cidadeDAO, cep);
				break;

			case 5:
				buscaCidadePorLetraReferencia(teclado, cidadeDAO);
				break;

			case 6:
				buscaCidadePorSiglaEstado(teclado, cidadeDAO);
				break;

			case 7:
				retornaQuantidadeDeCidadePorSiglaEstado(teclado, cidadeDAO);
				break;

			case 8:
				buscaCidadePorCapitalOuMunic�pio(teclado, cidadeDAO);
				break;

			case 0:
				System.out.println("\n\tMuito Obrigado!\n");
				break;

			default:
				System.out.println("\n\tOp��o Inv�lida!\n");
			}
		} while (opcaoEscolha != 0);
	}

}
