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

    PrintWriter pw;
    BufferedWriter writer;


    String path_to_file;


    private void tabulation() {
       // for (int i=0; i<tab; i++)
           // System.out.print("  ");
    }

    @Override
    public void startDocument() {



        try {
             writer = new BufferedWriter(new FileWriter("test.yarspg", false), 8192 * 4);
             System.out.print("processing...");
        } catch (IOException e) {
            e.printStackTrace();
        }


        tabulation();
       // System.out.println("Starting XML Document");
        tab++;
    }

    @Override
    public void endDocument() {
        tab--;
        tabulation();


        try {
            writer.close();
            System.out.println("\nThe YARS-PG file was created with filename test.yarspg");
        } catch (IOException e) {
            e.printStackTrace();
        }

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
        else if(localName.equals("edge")){
            obj=new edge();


            if (attributes.getLength()>0) {
                for (int i=0; i<attributes.getLength(); i++) {

                    if(attributes.getLocalName(i).toString().equals("id")){
                        ((edge) obj).setId(attributes.getValue(i));
                    }
                    else if(attributes.getLocalName(i).toString().equals("label")) {


                            ((edge) obj).setLabel(attributes.getValue(i));
                    }
                    else if(attributes.getLocalName(i).toString().equals("source")) {

                        ((edge) obj).setVertex_start(attributes.getValue(i));
                    }
                    else if(attributes.getLocalName(i).toString().equals("target")) {

                        ((edge) obj).setVertex_end(attributes.getValue(i));
                    }
                    else if(attributes.getLocalName(i).toString().equals("key")){
                        key=attributes.getValue(i);

                    }
                }
            }

        }

        tab++;
    }

    @Override
    public void endElement(String uri, String localName, String qName)
            throws SAXException {
        tab--;
        tabulation();
        if(localName.equals("node")){
            isVertex=false;
            try {
                writer.write(obj.toString()+"\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(localName.equals("edge")){
            isEdge=false;
            try {
                writer.write(obj.toString()+"\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void characters(char[] ch, int start, int length)
            throws SAXException {
        String content= new String(ch, start, length);
        content= content.replaceAll("[\t\n]", "").trim();

        if (!content.equals("")) {
            tabulation();
            if(isVertex){
                ((vertex) obj).setProperty(key,content);

            }
        }
        else if(isEdge){
            ((edge) obj).setProperty(key,content);

        }
    }
}
