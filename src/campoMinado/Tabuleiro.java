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
		int count=0;
        for(int line = 1 ; line < 9 ; line++)
            for(int column = 1 ; column < 9 ; column++)
                if(tabuleiro[line][column]=='_')
                    count++;
        if(count == 10)
            return true;
        else
            return false;
	}
	
	//abre as posições vizinhas se não houver minas
	public void abrirVizinhas() {
		for(int i=-1 ; i<2 ; i++)
            for(int j=-1 ; j<2 ; j++)
                if( (minas[linha+i][coluna+j] != -1) && (linha != 0 && linha != 9 && coluna != 0 && coluna != 9) ){
                    tabuleiro[linha+i][coluna+j]=Character.forDigit(minas[linha+i][coluna+j], 10);
                }
	}
	
	public int getPosicao(int linha, int coluna) {
		return minas[linha][coluna];
	}
	
	public boolean setPosicao() {
		do{
            System.out.print("\nLinha: "); 
            linha = entrada.nextInt();
            System.out.print("Coluna: "); 
            coluna = entrada.nextInt();
            
            if( (tabuleiro[linha][coluna] != '_') && ((linha < 9 && linha > 0) && (coluna < 9 && coluna > 0)))
                System.out.println("Esse campo já está sendo exibido");
            
            if( linha < 1 || linha > 8 || coluna < 1 || coluna > 8)
                System.out.println("Escolha números de 1 até 8");
            
        }while((linha < 1 && linha > 8) && (coluna < 1 && coluna > 8) || (tabuleiro[linha][coluna] != '_') );
        
        if(getPosicao(linha, coluna)== -1)
            return true;
        else
            return false;
	}
	
	//exibe o tabuleiro
	public void exibe() {
		System.out.println("\n     Linhas");
        for(int linha = 8 ; linha > 0 ; linha--){
            System.out.print("       "+linha + " ");
            
            for(int coluna = 1 ; coluna < 9 ; coluna++){
                    System.out.print("   "+ tabuleiro[linha][coluna]);
            }
                
            System.out.println();
        }
            
        System.out.println("\n            1   2   3   4   5   6   7   8");
        System.out.println("                      Colunas");
	}
	
	//preenche as dicas de acordo com a quantidade de minas ao redor
	public void preencheDicas() {
		for(int line=1 ; line < 9 ; line++)
            for(int column=1 ; column < 9 ; column++){
                
                    for(int i=-1 ; i<=1 ; i++)
                        for(int j=-1 ; j<=1 ; j++)
                            if(minas[line][column] != -1)
                                if(minas[line+i][column+j] == -1)
                                    minas[line][column]++;
                
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
