package ru.vsu.cs.kharlamov_i_s;

import java.sql.SQLOutput;
import java.util.Locale;

public class Main {

    public static class InputArgs {
        private double creditSum;
        private double months;
        private double interest;
        private double n;
        private boolean isPaymentDifferentiated;
        private boolean calcMode;
        private boolean help;
    }

    public static InputArgs parseCmdArgs(String[] args) {
        InputArgs parameters = new InputArgs();
        if (args.length > 0) {
            if (args[0].equals("--help")) {
                parameters.help = true;
                return parameters;
            }
            parameters.creditSum = Double.parseDouble(args[1]);
            parameters.months = Double.parseDouble(args[2]);
            parameters.interest = Double.parseDouble(args[3]);
            parameters.isPaymentDifferentiated = Boolean.parseBoolean(args[4]);
            if (args[0].equals("-calcPayments")) {
                parameters.calcMode = true;
                parameters.n = Double.parseDouble(args[5]);
            }
        } else {
            parameters.help = true;
        }
        return parameters;
    }

    public static void main(String[] args){
        Locale.setDefault(Locale.ROOT);
        InputArgs arg = parseCmdArgs(args);
        if (arg.help){
            System.out.println("Использование:");
            System.out.println("--help    // помощь");
            System.out.println("-calcPayments <сумма_кредита> <всего_месяцев> <годовой_процент> <вид_платежа> <месяц_n> // сумма платежей до месяца n");
            System.out.println("-calcTotalSum <сумма_кредита> <всего_месяцев> <годовой_процент> <вид_платежа>           // общая сумма платежей");
            System.out.println("Годовой процент в абсолютном виде");
            System.out.println("Виды платежа: true - дифференцированный; false - аннуитетный");
            System.exit(1);
        }
        Calculator calculator = new Calculator(arg.creditSum,arg.months,arg.interest,arg.isPaymentDifferentiated);
        if (arg.calcMode){
            System.out.println(calculator.calcPayments(arg.n));
        } else {
            System.out.println(calculator.calcTotalSum());
        }
        }
    }