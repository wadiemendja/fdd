package fdd;

import java.util.Scanner;

/**
 *
 * @author Wadie Mendja
 */
public class Fdd {

    static Scanner read = new Scanner(System.in);

    static double avg(double[] arr) {
        double total = 0;
        int arrLength = arr.length;
        for (int i = 0; i < arrLength; i++) {
            total += arr[i];
        }
        return (double) total / arrLength;
    }

    static double variance(double table[], double avg) {
        double s = 0;
        for (int i = 0; i < table.length; i++) {
            s += Math.pow(table[i], 2);
        }
        return (double) (s / (table.length) - Math.pow(avg, 2));
    }

    static double[][] readingT2() {
        int colNumbber = 2;
        System.out.print("Entrer le nomber de individues : ");
        int lineNumber = read.nextInt();
        double t2[][] = new double[lineNumber][colNumbber];
        for (int i = 0; i < t2.length; i++) {
            for (int j = 0; j < 2; j++) {
                System.out.print("T2[" + (i + 1) + "][" + (j + 1) + "]:");
                t2[i][j] = read.nextDouble();
            }
        }
        return t2;
    }
    static void cc(double t2[][]) {
        // splitting the table
        double T2Col1[] = new double[t2.length];
        double T2Col2[] = new double[t2.length];
        for (int i = 0; i < t2.length; i++) {
            T2Col1[i] = t2[i][0];
        }
        for (int i = 0; i < t2.length; i++) {
            T2Col2[i] = t2[i][1];
        }
        // calculating the avg of each column
        double avgCol1 = avg(T2Col1);
        double avgCol2 = avg(T2Col2);
        // outpetting averages of each column        
        System.out.println("X_BAR =  " + avgCol1);
        System.out.println("Y_BAR = " + avgCol2);
        // calculating the variance of the first column
        double varX = variance(T2Col1, avgCol1);
        double catrieDeX = Math.sqrt(varX);
        // calculating the variance of the second column
        double varY = variance(T2Col2, avgCol2);
        double cartieDeY = Math.sqrt(varY);
        // outputting variance of each
        System.out.println("VarX = " + varX);
        System.out.println("VarY = " + varY);
        // calculating the covariance
        double bast = 0;
        for (int i = 0; i < t2.length; i++) {
            bast += T2Col1[i] * T2Col2[i];
        }
        double coVar = (bast / t2.length) - (avgCol1 * avgCol2);
        System.out.println("Covariance = " + coVar);
        // calculating r
        double r = (double) coVar / (catrieDeX * cartieDeY);
        System.out.println("r = " + r);
        // calculating a and b
        double a = calculateA(coVar, catrieDeX);
        double b = calculateB(avgCol1, avgCol2, a);
        System.out.println("a = " + a);
        System.out.println("b = " + b);
        // calculating alpha and beta
        double alpha = calculateAlpha(coVar, cartieDeY);
        System.out.println("alpha = " + alpha);
        double beta = calculateBeta(avgCol1, avgCol2, alpha);
        System.out.println("Beta = " + beta);
        // calculating delta and trace 
        double delta = calcualteDelta(varX, varY, coVar);
        double trace = calculateTrace(varX, varY);
        System.out.println("l'equation est: Lambda^2-(" + trace + ")Lamda+(" + delta + ")");
        calculateSolutions(trace, delta, varX , varY, coVar);
        // calculating a' and b'
        double aPrim = 1 / alpha;
        double bPrim = -beta / alpha;
        System.out.println("a' = " + aPrim);
        System.out.println("b' = " + bPrim);
        System.out.println("Les droites : \ny=" + a + "x+" + b + "\ny=" + aPrim + "x+" + bPrim);
    }

    static double calculateA(double covar, double cartieDeX) {
        return (double) covar / Math.pow(cartieDeX, 2);
    }

    static double calculateB(double xBar, double yBar, double a) {
        return (double) yBar - xBar * a;
    }

    static double calculateAlpha(double covar, double catrieDeY) {
        return (double) covar / Math.pow(catrieDeY, 2);
    }

    static double calculateBeta(double xBar, double yBar, double alpha) {
        return (double) xBar - alpha * yBar;
    }

    static double calcualteDelta(double varX, double varY, double covar) {
        return (double) ((varX * varY) - Math.pow(covar, 2));
    }

    static double calculateTrace(double varX, double varY) {
        return (double) (varX + varY);
    }

    static void calculateSolutions(double trace, double det, double varX, double varY,double covar) {
        double delta = Math.pow(trace, 2) - (4 * det);
        if (delta == 0) {
            double lamda = (trace + Math.sqrt(delta)) / 2;
            System.out.println("Lamda = " + lamda);
        } else if (delta > 0) {
            double lamda1 = (trace + Math.sqrt(delta)) / 2;
            double lamda2 = (trace - Math.sqrt(delta)) / 2;
            System.out.println("Lamda 1 = " + lamda1);
            System.out.println("Lamda 2 = " + lamda2);
            double a = Math.sqrt((varX - lamda1) / (varY - lamda1));
            double aPrim = Math.sqrt((varX - lamda2) / (varY - lamda2));                                  
            double alpha1 =  (-covar * Math.sqrt((varX - lamda1) / (varY - lamda1))) / (varX - lamda1);
            System.out.println("la formule de alpha1 en fonction de alpha2 est :");
            System.out.println("alpha2 = alpha2 * "+ a);
            System.out.println("alpha1 = " + alpha1);
            double alpha2  = a * alpha1;
            System.out.println("alpha2 = " + alpha2);
            
        } else {
            System.out.println("n a pas des solutions !!!");
        }
    }

    public static void main(String[] args) throws Exception {
        double T2[][] = readingT2();
        /*double T2[][] = {
            {0.5, 0},
            {-0.1, 1.2},
            {-0.5, 0.5},
            {-0.3, 0.1},
            {0, 2.5},
            {1.6, -0.7},
            {2, 2},
            {2.4, 1.2},
            {0.5, 3.5},
            {2.7, -0.9}
        };*/
        cc(T2);
    }

}
