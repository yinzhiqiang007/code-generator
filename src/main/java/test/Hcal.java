package test;

public class Hcal {

//    拿地价7000/㎡+建筑成本2500+设计监理费250+装修成本1000=11750元/㎡ 这是理论成本
//      11750 x 118%（ 100+融资成本10+营销5+销售3）=13865元/㎡ 这是实际成本
//  13865x120%（税费）=16638元/㎡
    public static void main(String[] args) {
        double dPrice = 20000;
        double price1= dPrice+2500+250+1000;
        double price2 = price1*1.18;
        double price3 = price2*1.2;
        System.out.println(price3);
    }
}
