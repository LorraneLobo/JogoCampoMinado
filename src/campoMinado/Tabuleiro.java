package campoMinado;

import java.util.Random;
import java.util.Scanner;

public class Tabuleiro {

	private int[][] minas;
	private char[][] tabuleiro;
	private int linha, coluna;
	Random random = new Random();
	Scanner entrada = new Scanner(System.in);
	
	//inicia um tabuleiro
	public Tabuleiro() {
		minas = new int [10][10];
		tabuleiro = new char[10][10];
		iniciaMinas(); //Coloca 0 em todas as posições do tabuleiro
		sorteiaMinas(); //Coloca aleatoriamente minas pelo tabuleiro
		preencheDicas(); //Preenche o tabuleiro com o n° de minas vizinhas
		iniciaTabuleiro(); //Inicia o tabuleiro com _
	}
	
	//ve se o jogador ganhou o jogo
	public boolean ganhou() {
		int count = 0;
		for(int linha = 1; linha < 9 ; linha++){
			for(int coluna = 1; coluna < 9; coluna++) {
				if(tabuleiro[linha][coluna] == '_') {
					count++;
				}
			}
		}
		if(count == 10) {
			return true;
		}else {
			return false;
		}
	}
	
	//abre as posições vizinhas se não houver minas
	public void abrirVizinhas() {
		for(int i = -1; i < 2; i++) {
			for(int j = -1; j <2; j++) {
				if( (minas[linha+i][coluna+j] != -1) && linha != 0 && linha != 9 && coluna != 0 && coluna != 9 ) {
					//Character.forDigit pega o numero e transforma em caractere. Os numeros estao na base decimal, por isso 10
					tabuleiro[linha+i][coluna+j]=Character.forDigit(minas[linha+i][coluna+j], 10);
				}
			}
		}
	}
	
	public int getPosicao(int linha, int coluna) {
		return minas[linha][coluna];
	}
	
	public boolean setPosicao() {
		do {
			System.out.println("\nLinha: ");
			linha = entrada.nextInt();
			System.out.println("\nColuna: ");
			coluna = entrada.nextInt();
			
			//Checa se o campo já está sendo exibido ou não
			if((tabuleiro[linha][coluna] != '_') && ((linha > 0 && linha < 9) && (coluna > 0 && coluna < 9))) {
				System.out.println("Este campo já está sendo exibido");
			}
			//Checa se o n° digitado está entre 1 e 8
			if( linha < 1 || linha > 8 || coluna < 1 || coluna > 8) {
				System.out.println("Escolha um numero entre 1 e 8!");
			}
			
		}while( (linha < 8 && linha > 1) && (coluna < 8 && coluna > 0) || tabuleiro[linha][coluna] != '_');
		
		if(getPosicao(linha, coluna) == -1) {
			return true;
		}else {
			return false;
		}
	}
	
	//exibe o tabuleiro
	public void exibe() {
		System.out.println("\n     Linhas: ");
		for(int linha = 8; linha > 0; linha++) {
			System.out.println("        " + linha + "  ");
		
			for(int coluna = 1; coluna < 9; coluna++) {
				System.out.println("  " + tabuleiro[linha][coluna]);
			}
			System.out.println();	
		}
		
		System.out.println("\n            1   2   3   4   5   6   7   8");
		System.out.println("                       Colunas");
	}
	
	//preenche as dicas de acordo com a quantidade de minas ao redor
	public void preencheDicas() {
		for(int linha = 1; linha < 9; linha++) {
			for(int coluna = 1; coluna < 9; coluna++) {
				
				for(int i = -1; i <= 1; i++) {
					for(int j = -1; j<= 1; j++) {
						if(minas[linha][coluna] != 1) {
							if(minas[linha+i][coluna+j] == -1) {
								minas[linha][coluna]++;
							}
						}
					}
				}
			}
		}
	}
	
	//exibe as minas
	public void exibeMinas() {
		for(int i=1 ; i < 9; i++)
            for(int j=1 ; j < 9 ; j++)
                if(minas[i][j] == -1)
                    tabuleiro[i][j]='*';
        
        exibe();
	}
	
	//inicia o tabuleiro com _
	public void iniciaTabuleiro() {
		 for(int i=1 ; i<minas.length ; i++)
	            for(int j=1 ; j<minas.length ; j++)
	                tabuleiro[i][j]= '_';
	}
	
	//cria as minas
	public void iniciaMinas(){
		for(int i=0 ; i<minas.length ; i++)
            for(int j=0 ; j<minas.length ; j++)
                minas[i][j]=0;
	}
	
	//sorteia as minas aleatoriamente
	public void sorteiaMinas(){
		boolean sorteado;
		int linha, coluna;
		
		for(int i = 0; i<10; i++) {
			do {
				linha = random.nextInt(8) + 1;
                coluna = random.nextInt(8) + 1;
                
                if(minas[linha][coluna] == -1)
                    sorteado=true;
                else
                    sorteado = false;
			}while(sorteado);
			
			minas[linha][coluna] = -1;
		}
	}
	
}
