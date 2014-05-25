/**
 * Dany Maillard, 603_3
 * 12.01.2014
 */

import Jama.Matrix;
import java.util.Scanner;

/**
 * La classe MatriceApp permet de résoudre une équiation grâce aux matrices
 */
public class MatriceApp
{
	/**
	 * Fonction principale
	 * @param args
	 */
	public static void main(String[] args)
	{		
		MatriceApp app = new MatriceApp();
		Scanner scanner = new Scanner(System.in);
		
		while (true)
		{
			// 7 70
			// 2 3 8, 3 4 11
			// 1 2 3 26, 4 5 6 62, 7 8 10 103
			System.out.println("Saisir une équation: ");
			String equation = scanner.nextLine();
			double[][] x = app.solve(equation);
			
			for (int i = 0; i < x.length; i++)
			{
				System.out.print((char)(97 + i) + ": " + x[i][0]);
				System.out.println();
			}	
		}
	}
	
	/**
	 * Résout une équation
	 * @param equation équation à résoudre. Exemple: equation == "2 3 8, 3 4 11"
	 * @return array de doubles contenant les réponses de l'équation
	 */
	public double[][] solve(String equation)
	{
		double[][] variables = parse(equation);
		double[][] A = getA(variables);
		double[][] b = getb(variables);
		double[][] x = solve(A, b);
		
		return x;
	}
	
	/**
	 * Résout une équation grâce aux matrices (librairie Jama)
	 * @param A array de doubles
	 * @param b array de doubles
	 * @return array de doubles contenant les réponses de l'équation
	 */
	private double[][] solve(double[][] A, double[][] b)
	{
		Matrix matrixA = new Matrix(A);
		Matrix matrixB = new Matrix(b);
		Matrix matrixX = matrixA.solve(matrixB);
		
		double[][] x = matrixX.getArray();
		
		return x;
	}
	
	/**
	 * Parse l'équation
	 * @param equation équation à résoudre
	 * @return array de toutes les variables de l'équation parsée
	 */
	private double[][] parse(String equation)
	{
		
		/*
		 	Exemple: equation == "2 3 8, 3 4 11"
		 	
		 	1)	lines[0] == "2 3 8"
				lines[1] == "3 4 11"
			
			2)	variables[0][0] == 2
				variables[0][1] == 3
				variables[0][2] == 8
				variables[1][0] == 3
				variables[1][1] == 4
				variables[1][2] == 11
		*/
		
		// 1)
		String[] lines = equation.split(",");
		
		// 2)
		double[][] variables = new double[lines.length][];
		for (int i = 0; i < lines.length; i++)
		{			
			String[] words = lines[i].trim().split(" ");
			variables[i] = new double[words.length];
			
			for (int j = 0; j < words.length; j++)
			{
				variables[i][j] = Double.parseDouble(words[j]);
			}
		}
		
		return variables;
	}
	
	/**
	 * Retourne un array A (matrice A) à partir de toutes les variables de l'équation
	 * @param variables array de toutes les variables de l'équation
	 * @return array A (matrice A) de l'équation
	 */
	private double[][] getA(double[][] variables)
	{
		double[][] A = new double[variables.length][variables[0].length - 1];
		
		for (int i = 0; i < variables.length; i++)
		{
			for (int j = 0; j < variables[i].length - 1; j++)
			{
				A[i][j] = variables[i][j];
			}
		}
		
		return A;
	}
	
	/**
	 * Retourne un array b (matrice b) à partir de toutes les variables de l'équation
	 * @param variables array de toutes les variables de l'équation
	 * @return array b (matrice b) de l'équation
	 */
	private double[][] getb(double[][] variables)
	{		
		double[][] b = new double[variables.length][1];
		
		for (int i = 0; i < variables.length; i++)
		{
			int j = variables[i].length - 1;
			b[i][0] = variables[i][j];
		}
		
		return b;
	}	
}