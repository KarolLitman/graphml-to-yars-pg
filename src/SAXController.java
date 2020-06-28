import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import property_graph.edge;
import property_graph.vertex;

import java.io.*;
import java.util.Scanner;

public class SAXController extends DefaultHandler {

    private int tab = 0;
    boolean isVertex=false;
    boolean isEdge=false;
    Object obj;
    String key=null;




    String path_to_file;


    private void tabulation() {
       // for (int i=0; i<tab; i++)
           // System.out.print("  ");
    }

    @Override
    public void startDocument() {






        tabulation();
       // System.out.println("Starting XML Document");
        tab++;
    }

    @Override
    public void endDocument() {
        tab--;
        tabulation();




    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes)
            throws SAXException {
        tabulation();
        String line;

        if(localName.equals("node")) {
            isVertex=true;
            obj=new vertex();
        }
        else if(localName.equals("edge")) {
            isEdge = true;
            obj=new edge();
        }

            if(isVertex){


            if (attributes.getLength()>0) {
                for (int i=0; i<attributes.getLength(); i++) {

                    if(attributes.getLocalName(i).toString().equals("id")){
                        ((vertex) obj).setId(attributes.getValue(i));
                    }
                    else if(attributes.getLocalName(i).toString().equals("labels")) {
                        String[] arrOfStr = attributes.getValue(i).substring(1,attributes.getValue(i).length()).split(":"); //cut first colon

                        for (String a : arrOfStr){

                            ((vertex) obj).setLabel(a);

                        }
                    }
                    else if(attributes.getLocalName(i).toString().equals("key")){
                        key=attributes.getValue(i);

                    }
                }
            }


        }
        else if(isEdge){


            if (attributes.getLength()>0) {
                for (int i=0; i<attributes.getLength(); i++) {


                    if(attributes.getLocalName(i).equals("id")){
                        ((edge) obj).setId(attributes.getValue(i));
                    }
                    else if(attributes.getLocalName(i).equals("label")) {


                            ((edge) obj).setLabel(attributes.getValue(i));
                    }
                    else if(attributes.getLocalName(i).equals("source")) {

                        ((edge) obj).setVertex_start(attributes.getValue(i));
                    }
                    else if(attributes.getLocalName(i).equals("target")) {

                        ((edge) obj).setVertex_end(attributes.getValue(i));
                    }
                    else if(attributes.getLocalName(i).equals("key")){
                        key=attributes.getValue(i);

                    }
                }
            }

        }

        tab++;
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        tab--;
        tabulation();
        if(localName.equals("node")){
            isVertex=false;
            System.out.println(obj.toString());
        }
        else if(localName.equals("edge")){
            isEdge=false;

            System.out.println(obj.toString());
            }
        }


    @Override
    public void characters(char[] ch, int start, int length)
            throws SAXException {
        String content= new String(ch, start, length);
        content= content.replaceAll("[\t\n]", "").trim();

        if (!content.equals("")) {
            tabulation();
            if(isVertex&&key.equals("label")){
                ((vertex) obj).setLabel(content);
            }
            else if(isVertex&&!(key.equals("id")||key.equals("labels"))){

                ((vertex) obj).setProperty(key,content);

            }
            if(isEdge&&!(key.equals("id")||key.equals("labels")||key.equals("label"))){
                ((edge) obj).setProperty(key,content);
            }
        }

    }
}
