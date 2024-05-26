
import java.util.Random;
import java.util.Scanner;

/**
	<strong>Objetivo</strong>
	  <p>
	    Implementar, usando a linguagem Java, o jogo da senha.
	  </p>
	<strong>Jogo da senha</strong>
  <p>
    Segundo a Wikipedia, o jogo da senha (Mastermind) foi criado por Mordechai Meirowitz. Consistia
    num jogo de tabuleiro em que uma senha de quatro dígitos representados por pinos coloridos (sendo
    6 possibilidades de cores) era estabelecida pelo jogador desafiador e deveria ser descoberta em 10
    tentativas pelo jogador desafiado.
  </p>
  <p>
    Para tanto, a cada tentativa, o desafiador informava ao desafiado quantos pinos estavam na posição
    correta e quantos faziam parte da senha mas estavam em posição equivocada.
  </p>
	<strong>Implementação</strong>
  <p>
    Como será utilizada interface de texto, ao invés de cores serão usados números (de 1 a 6) para
    compor a senha de quatro dígitos.
  </p>
  <p>
    A senha deve ser gerada aleatoriamente pelo programa.
  </p>
  <p>
    A senha deve ser armazenada num vetor de quatro posições e um outro vetor similar deve ser usado
    para receber as tentativas do jogador desafiado.
  </p>
  <p>
    A cada tentativa, o programa deve informar a quantidade de números corretos em posição correta e
    a quantidade de números corretos (ou seja que fazem parte da senha) em posição equivocada.
  </p>
*/
class App {
	public static void main(String[] args) {
	  int[] senha = {3, 4, 1, 1};
	  int[] tentativa = new int[4];
	  Scanner scan = new Scanner(System.in);
	  
	  Random random = new Random();
	  for(int i = 0; i < 4; i++) {
		  senha[i] = random.nextInt(6) + 1;
	  }
	  
	  int chances = 10;
	  boolean userLosed = false;
	  int numeroDigitado = 0;
	  do {
		  System.out.println("\nTentativas restantes: " + chances);
		  System.out.println("Digite numeros para o seu chute:");
		  
		  int i = 0;
		  while(i < 4) {
			  numeroDigitado = scan.nextInt();
			  if(numeroDigitado < 1 || numeroDigitado > 6) {
				  System.out.println("Apenas numeros de 1 e 6");
			  } else {
				  tentativa[i] = numeroDigitado;
				  i++;
			  }
		  }
		  
		  showArray(tentativa, "\nSeu chute: ");
		  
		  System.out.println("Numeros corretos: " + getNumInPosCorrect(senha, tentativa));
		  System.out.println("Numeros deslocados: " + getNumberOfDisplacedNumbers(senha, tentativa));
		  
		  if(getNumInPosCorrect(senha, tentativa) == 4) {
			  userLosed = true;
		  }
		  chances--;
	  } while(!userLosed && chances > 0);
	  
	  if(userLosed) {
		  System.out.println("\nVoce ganhou!!!");
	  } else {
		  System.out.println("\nBurro pra disgrama viu!");
		  showArray(senha, "A senha era: ");
	  }
	  
	}
	
	/**
	  * @param senha - Array de inteiros que representa a senha
	  * @param tentativa - Array de inteiros que representa a tentativa do usuario
	  *
	  * @return Int[] - Array dos numeros que estao na mesma posicao
	*/
	public static int[] getArrayOfCorrectNumbers(int[] senha, int[] tentativa) {
	  int[] numerosCorretos = new int[4];
	  for(int i = 0; i < 4; i++) {
	    if(senha[i] == tentativa[i]) {
	      numerosCorretos[i] = senha[i];
	    }
	  }
	  return numerosCorretos;
	}
	
	/**
	  * @param number	- Numero que será verificado
	  * @param array - Array onde será feita da verificação
	  *
	  * @return Bollean - Se um numero está contido no array
	*/
	public static boolean findNumberInArray(int number, int j, int[] array) {
	  boolean finded = false;
	
	  for(int i = 0; i < 4; i++) {
	    if(array[i] == number && i == j) {
	      finded = true;
	      break;
	    }
	  }
	
	  return finded;
	
	}
	
	/**
	  * @param senha Int[] - Array da senha
	  * @param tentativa Int[] - Array da tentativa do usuario
	  *
	  * @return Int - Quantidade de numeros corretos, mas na posição errada
	  *
	  * @see <strong>if(senha[i] == tentativa[j]):</strong>
	  * <ul>
	  *   <li>
	  *     <i>Se o numero atual em senha, array senha na posição i, é igual ao numero atual em tentativa, array tentativa na posição j</i>
	  *   </li>
	  * </ul>
	  * @see <strong>if(i != j):</strong>
	  * <ul>
	  *   <li>
	  *     <i>Se os indices i e j são diferentes</i>
	  *   </li>
	  * </ul>
	  * @see <strong>if(numDeslocado != tentativa[j]):</strong>
	  * <ul>
	  *   <li>
	  *     <i>Se o numero atual em tentativa, array tentativa na posição j, é diferente de um numero deslocado encontrado</i>
	  *   </li>
	  * </ul>
	*/
	public static int getNumberOfDisplacedNumbers(int[] senha, int[] tentativa) {
	  int deslocados = 0;
	  int[] numerosCorretos = getArrayOfCorrectNumbers(senha, tentativa);

	  int[] copiaSenha = new int[4];
	  int[] copiaTentativa = new int[4];
	  for(int i = 0; i < 4; i++) {
	  	copiaTentativa[i] = tentativa[i];
	  	copiaSenha[i] = senha[i];
	  }
	  
	  
	  for(int i = 0; i < 4; i++) {
	    for(int j = 0; j < 4; j++) {
	      if(!findNumberInArray(copiaTentativa[j], j, numerosCorretos)) {
	        if(copiaSenha[i] == copiaTentativa[j]) { 
	          if(i != j) {
	          	deslocados++;
	            copiaTentativa[j] = 0;
	            copiaSenha[i] = 0;
	            i = 0;
	            j = 0;
	          }
	        } 
	      }
	    }
	  }
	  
	  return deslocados;
	}
	
	public static void showArray(int[] list, String txt) {
		System.out.print(txt);
	  for(int n : list) {
	    System.out.print( n + " ");
	  }
	  System.out.println();
	}
	
	/**
		* @param senha Int[] - Array da senha
		* @param tentativa Int[] - Array da tentativa do usuario
		*
		* @return Int - Quantidade de números corretos
	*/
	public static int getNumInPosCorrect(int[] senha, int[] tentativa) {
	  int corretos = 0;
	  for(int i = 0; i < 4; i++) {
	    if(senha[i] == tentativa[i]) {
	      corretos++;
	    }
	  }
	
	  return corretos;
	}
}



