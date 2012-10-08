/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.text.DecimalFormat;

/**
 *
 * @author Gihan
 */
public class Salarycalculate {

    double basicsal;
    double gvtallowance;
    double budallowance;
    double nopayrate;
    double totepf;
    double empepf;
    double companyepf;
    double etf;
    double otrate;
    double attendbonus;
    double totsalary;
    double totallowances;
    double totdeductions;
    double grosssalary;
    public static int EPFEMPLOYEE = 10;
    public static int EPFCOMPNAY = 12;
    DecimalFormat format = new DecimalFormat("#.##");

    public double getNoPayRate(double basicamt, int emptype) {

        if (emptype == 1) {
            nopayrate = basicamt / 26.0;
        } else if (emptype == 2) {
            nopayrate = basicamt / 30.0;
        }
        nopayrate = Double.parseDouble(format.format(nopayrate));
        return nopayrate;
    }

    public double getOTRate(double basicamt, int emptype) {
        if (emptype == 1) {
            otrate = (basicamt / 208) * 1.5;
        } else if (emptype == 2) {
            otrate = (basicamt / 240) * 1.5;
        }
        otrate = Double.parseDouble(format.format(otrate));
        return otrate;
    }

    public double getEpF(double salaryPF, int type) {
        double efp = 0.0;
        if (type == EPFEMPLOYEE) {

            efp = salaryPF * 0.08;

        } else if (type == EPFCOMPNAY) {
            efp = salaryPF * 0.12;
        }
        efp = Double.parseDouble(format.format(efp));
        return efp;
    }

    public double getETF(double salaryPF) {
        double etf = 0.0;
        etf = salaryPF * 0.03;
        etf = Double.parseDouble(format.format(etf));
        return etf;
    }

    public double getTotalBasic(double basicsalary, double gvtallowance, double budallowance) {
        double sal =basicsalary + gvtallowance + budallowance;
        return Double.parseDouble(format.format(sal));
    }

    public double getSalaryPF(double totalbasic, double nopayrate) {

        return totalbasic - nopayrate;
    }
}
