package property_graph;

import property_graph.vertex;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class edge extends element{

    boolean isDirected;
    String vertex_start;
    String vertex_end;



    public String getVertex_start() {
        return vertex_start;
    }

    public void setVertex_start(String vertex_start) {
        this.vertex_start = vertex_start;
    }

    public String getVertex_end() {
        return vertex_end;
    }

    public void setVertex_end(String vertex_end) {
        this.vertex_end = vertex_end;
    }



    public void setDirected(boolean directed) {
        isDirected = directed;
    }



    public boolean isDirected() {
        return isDirected;
    }



    public edge() {
        labels=new HashSet<String>();
        properties=new HashMap<>();
    }

    String getidYARSPGFormat(){
        if (!id.isEmpty()) {
            String temp = "<\""+id+"\">";
            return temp;
        } else {
            return "";
        }
    }

    @Override
    public String toString() {
        return "(\"" + vertex_start + "\")" + getidYARSPGFormat() +
                "" + getLabelsYARSPGFormat() +
                getpropertiesYARSPGFormat() + "->(\"" + vertex_end + "\")";
    }


}
