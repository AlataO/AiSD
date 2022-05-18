package ru.vsu.cs.kharlamov_i_s;

public class Calculator {
    private final double creditSum;
    private final double months;
    private final double interest;
    private final boolean isPaymentDifferentiated;

    public Calculator(double creditSum, double months, double interest, boolean isPaymentDifferentiated){
        this.creditSum=creditSum;
        this.months=months;
        this.interest=interest;
        this.isPaymentDifferentiated=isPaymentDifferentiated;
    }

    public double calcPayments (double n){
        double paymentSum = 0;
        if (isPaymentDifferentiated){
            double creditLeft=creditSum;
            for (int i=0;i<n;i++){
                paymentSum+=creditSum/months+creditLeft*interest/12;
                creditLeft-=creditSum/months+creditLeft*interest/12;
            }
        }
        else {
            paymentSum=n*(creditSum*(interest/12)*Math.pow((1+interest/12),months)/(Math.pow((1+interest/12),months)-1));
        }
        return paymentSum;
    }

    public double calcTotalSum (){
        return calcPayments(months);
    }
}
