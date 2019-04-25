import java.util.ArrayList;
import java.util.List;
import org.knowm.xchart.*;
import org.knowm.xchart.style.Styler;

public class Schedule {
    public static void print (ArrayList<Double> array, ArrayList<Double> array1,ArrayList<Double> list,String name) {

        ArrayList<Integer> list1 = new ArrayList<>();
        for (int i = 0; i < 50;i++)
        {
            list1.add(i);
        }

        XYChart chart = new XYChartBuilder().width(800).height(600).title(name).xAxisTitle("x").yAxisTitle("y").build();

        chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideNW);
        chart.getStyler().setHasAnnotations(false);
        chart.getStyler().setPlotGridLinesVisible(false);

        chart.addSeries("теоретическая", list1, array);
        chart.addSeries("Практическая", list1,array1);
        List<XYChart> charts = new ArrayList<XYChart>();
        charts.add(chart);
        new SwingWrapper<XYChart>(charts).displayChartMatrix();

    }
}
