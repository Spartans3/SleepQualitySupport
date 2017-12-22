package sleep.quality.support;

import extensions.averagedata;
import extensions.sensor;
import java.awt.Toolkit;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Result {


    public void results() throws SQLException{
        DatabaseConnection db=new DatabaseConnection();
        List<sensor> ls = new ArrayList<>();
        ls = db.veri_listele();
        float temp = 0 , hum = 0 , co2 = 0;
        float gecici;
        float avgtemp , avghum, avgco2;
        float c= 0;
        for(int i=0;i<ls.size();i++){
            c++;
            gecici = Float.parseFloat(ls.get(i).getHeat());
            
            temp += gecici;
            gecici = Float.parseFloat(ls.get(i).getHumidity());
            hum += gecici;
            gecici = Float.parseFloat(ls.get(i).getAirQuality());
            co2 += gecici;     
            System.out.println("haha\t" +c );
        }
        avgtemp = temp/c;
        System.out.println("avgtemp \n"+avgtemp);
        avghum = hum/c;
        avgco2 = co2/c;
        System.out.println("avgco2 \n"+avgco2);
        System.out.println("avghum \n"+avghum);
        averagedata var = new averagedata();
        var.setavgco2(avgco2);
        var.setavghum(avghum);
        var.setavgtemp(avgtemp);
        
        
    }
    public Result() throws SQLException {
        results();
    }
}