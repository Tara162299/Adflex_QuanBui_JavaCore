package week3.Project3.nonThread;

import java.io.File;

public class Runner3 {
    public static void main(String[] args) {
        File area = new File("Resource/Project3/input/area.txt");
        Process3 Area = new Process3(area.getAbsoluteFile());

        Area.process();
    }
}

