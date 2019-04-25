import java.util.ArrayList;

public class Lab3 {
    private int N;
    private double lymda[];
    private double prob[];
    private double step;
    private double delta;

    public Lab3(int n,double[] l, double []pr, double s, double d) {
        N = n;
        lymda = l;
        prob = pr;
        step = s;
        delta = d;
    }
    public void firstStage()
    {
        ArrayList<Double>R = new ArrayList<>();
        ArrayList<Double>curs = new ArrayList<>();
        ArrayList<Double>R_teor = new ArrayList<>();
        ArrayList<Double>lym = new ArrayList<>();
        ArrayList<Double>lym_teor = new ArrayList<>();
        double T_average = 0;
        double T_max = 0;
        double T[] = new double[N];
        int index = 0;
        for(int j = 0; j < N; j++)
        {
            if(j ==(int)(N * prob[index]))
            {
                index++;
            }
            T[j] = getLymda(index);
            T_average += T[j];
            if(T[j] > T_max)
                T_max = T[j];
        }
        T_average = T_average / N;
        double Tmidle = (T_average + T_max) / 2;
        double st = Tmidle/step;
        double delta_S = st / delta;
        double cur = 0;
        for(int i = 0; i < step; i++)
        {
            int counter = 0;
            int counter_delta = 0 ;
            for(int j = 0; j < N; j++)
            {
                if(T[j] > cur)
                    counter++;
                if(T[j] > cur + delta_S)
                    counter_delta++;
            }
            R.add((double)counter/N);
            lym.add((counter-counter_delta)/(counter * delta_S));
            R_teor.add((Math.pow(Math.exp(1), -lymda[0]*cur) * prob[0] + Math.pow(Math.exp(1), -lymda[1]*cur) * prob[1]));
            lym_teor.add(-(-lymda[0] * Math.pow(Math.exp(1), -lymda[0] * cur) * prob[0] - lymda[1] * Math.pow(Math.exp(1), -lymda[1] * cur) * prob[1]) / (Math.pow(Math.exp(1), -lymda[0]*cur) * prob[0] + Math.pow(Math.exp(1), -lymda[1]*cur) * prob[1]));
            curs.add(cur);
            cur += st;
        }
        Schedule.print(R_teor,R,curs,"R(t)");
        Schedule.print(lym_teor,lym,curs,"lym(t)");

    }
    public void secondAndThirdStage(boolean stage)
    {
        ArrayList<Double>R = new ArrayList<>();
        ArrayList<Double>R_teor = new ArrayList<>();
        ArrayList<Double>curs = new ArrayList<>();
        ArrayList<Double>lym = new ArrayList<>();
        ArrayList<Double>lym_teor = new ArrayList<>();
        double T_average = 0;
        double T_max = 0;
        double T[] = new double[N];
        double T1 = 0;
        double T2 = 0;
        int index = 0;
        for(int j = 0; j < N; j++)
        {
            T1 = getLymda(0);
            T2 = getLymda(1);
            if(!stage) {
                if (T1 < T2)
                    T[j] = T1;
                else
                    T[j] = T2;
            }
            else {
                if (T1 > T2)
                    T[j] = T1;
                else
                    T[j] = T2;
            }
            T_average += T[j];
            if(T[j] > T_max)
                T_max = T[j];
        }
        T_average = T_average / N;
        double Tmidle = (T_average + T_max) / 2;
        double st = Tmidle/step;
        double delta_S = st / delta;
        double cur = 0;
        for(int i = 0; i < step; i++)
        {
            int counter = 0;
            int counter_delta = 0 ;
            for(int j = 0; j < N; j++)
            {
                if(T[j] > cur)
                    counter++;
                if(T[j] > cur + delta_S)
                    counter_delta++;
            }
            R.add((double)counter/N);
            lym.add((counter-counter_delta)/(counter * delta_S));
            if(!stage) {
                R_teor.add((Math.pow(Math.exp(1), (-1) * lymda[0] * cur)) * (Math.pow(Math.exp(1), (-1) * lymda[1] * cur)));
                lym_teor.add(lymda[1] + lymda[0]);
            }
            else {
                R_teor.add(Math.pow(Math.exp(1), (-1) * lymda[0] * cur) + Math.pow(Math.exp(1), (-1) * lymda[1] * cur) -  Math.pow(Math.exp(1), (-1) * (lymda[1] + lymda[0]) * cur));
                lym_teor.add(((lymda[0] * Math.pow(Math.exp(1),(-1)*lymda[0]*cur)) + (lymda[1] * Math.pow(Math.exp(1),(-1)*lymda[1]*cur))  - (lymda[1] + lymda[0])* Math.pow(Math.exp(1),(-1)*(lymda[1]+ lymda[0])*cur))/((Math.pow(Math.exp(1),(-1)*lymda[0]*cur)) + (Math.pow(Math.exp(1),(-1)*lymda[1]*cur))  - Math.pow(Math.exp(1),(-1)*(lymda[1]+ lymda[0])*cur)));
            }
            curs.add(cur);
            cur += st;
        }
        Schedule.print(R_teor,R,curs,"R(t)");
        Schedule.print(lym_teor,lym,curs,"lym(t)");

    }
    private double getLymda(int index)
    {
        return Math.log(Math.random())/lymda[index]*(-1);
    }
}