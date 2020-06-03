package property_graph;

import java.util.HashSet;
import java.util.Map;
import java.util.Objects;

public abstract class element {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof element)) return false;
        element element = (element) o;
        return Objects.equals(id, element.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }


    String id;
    HashSet<String> labels;
    public Map<String, String> properties;

    public String getId() {
        return id;
    }

    public HashSet<String> getLabels() {
        return labels;
    }

    public Map<String, String> getProperties() {
        return properties;
    }


    public void setId(String id) {
        this.id = id;
    }

    public void setLabels(HashSet<String> labels) {
        this.labels = labels;
    }

    public void setLabel(String label) {
        this.labels.add(label);
    }

    public void setProperties(Map<String, String> properties) {
        this.properties = properties;
    }

    public Object getElement(String key){
     if(key.equals("id")){
         return id;
     }
     else if(key.equals("label")){
         return labels;
     }
     else {
         return properties.get(key);
     }
     }

    public void setProperty(String key, String value) {

        properties.put(key,value);

    }

    String getLabelsYARSPGFormat() {
        if (!labels.isEmpty()) {
            String temp = "{";
            for (String s : labels) {
                temp += "\"" + s + "\",";
            }
            temp=temp.substring(0, temp.length() - 1);
            temp += "}";
            return temp;
        } else {
            return "";
        }
    }

    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    String getpropertiesYARSPGFormat() {
        String value="";
        if (!properties.isEmpty()) {
            String temp = "[";
            for (Map.Entry<String, String> entry : properties.entrySet()) {


                if(isNumeric(entry.getValue())){
                    value=entry.getValue();
                }
                else{
                    value="\"" +entry.getValue()+ "\"";
                }

                temp += "\"" +entry.getKey() +"\"" + ":"+ value+",";
            }
            temp=temp.substring(0, temp.length() - 1);
            temp += "]";
            return temp;
        } else {
            return "";
        }
    }



}
