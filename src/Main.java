public class Main {
    public static void main(String[] arg)
    {
        Lab3 lab3 = new Lab3(10000,new double[]{0.9,1.3},new double[]{0.7,0.3},50,10);
        lab3.firstStage();
        lab3.secondAndThirdStage(false);
        lab3.secondAndThirdStage(true);
    }

}
