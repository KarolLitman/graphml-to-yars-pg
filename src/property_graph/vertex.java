package property_graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class vertex extends element{



    public vertex() {
        labels=new HashSet<String>();
        properties=new HashMap<>();

    }






    @Override
    public String toString() {
        return "<\"" + "" + id + "\">" +
                "" + getLabelsYARSPGFormat() +
                 getpropertiesYARSPGFormat();
    }
}
