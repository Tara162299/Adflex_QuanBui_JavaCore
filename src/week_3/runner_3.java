package week_3;

import java.io.File;
import java.util.List;

public class runner_3 {
    public static void main(String[] args) {
        File area = new File("Resource/Project3/input/area.txt");
        File postion = new File("Resource/Project3/input/positions.txt");

        helperMethods_3 Area = new helperMethods_3(area.getAbsoluteFile());
        helperMethods_3 Position = new helperMethods_3(postion.getAbsoluteFile());

        List<String> areaList = Area.changeToString();
        List<String> positionList = Position.changeToString();
        System.out.println(areaList);
        System.out.println(positionList);
        System.out.println();

        for (int i = 0; i < positionList.size(); i++) {
            String longAndLat = Position.getLongandLat(positionList.get(i));
            String getLong = Position.getLong(longAndLat);
            String getLat = Position.getLat(longAndLat);
            System.out.println(longAndLat);
            System.out.println(getLong);
            System.out.println(getLat);
        }

    }
}
