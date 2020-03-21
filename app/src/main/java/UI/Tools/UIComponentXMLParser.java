package UI.Tools;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import UI.ComponentIndex.UiAttribute;

import Logic.Tools.getProperties;

public class UIComponentXMLParser {
    public final static int CLASS_INTEGER=1;
    public final static int CLASS_FLOART=2;
    public final static int CLASS_DOUBLE=3;
    public final static int CALSS_CHAR=4;
    public final static int CALSS_STRING=5;
    public final static int BOOLEAN=6;
    public final static int CLASS_LONG=7;

    public ArrayList<UiAttribute> parser(String path)  {

        Element element= null;
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(path);
            element = document.getDocumentElement();
            NodeList nodeList=element.getElementsByTagName("module");

            //System.out.println(nodeList.getLength());

            ArrayList<UiAttribute> attributes=new ArrayList<>();

            for(int i=0;i<nodeList.getLength();i++){
                Node node=nodeList.item(i);
                NodeList nodeList1=node.getChildNodes();
                System.out.println("第"+i+"个节点孩子个数"+nodeList1.getLength());

                for(int n=0;n<nodeList1.getLength();n++){
                    //System.out.println(nodeList1.item(n).getNodeType());
                    if(nodeList1.item(n).getNodeType()==Node.TEXT_NODE)continue;
                    else {
                        NamedNodeMap namedNodeMap=nodeList1.item(n).getAttributes();
                            //参数个数判断
                           int count=getParameterNumber(namedNodeMap.getNamedItem("Passingform").getNodeValue());
                           HashMap<Integer,String> parameterType=getParametersType(namedNodeMap.getNamedItem("inputTypeform").getNodeValue());
                           HashMap<Integer,Object> parameters=getParameters(namedNodeMap.getNamedItem("Passingform").getNodeValue(),parameterType);



                            //System.out.println(namedNodeMap.getNamedItem("Passingform").getNodeValue());
                            UiAttribute attributesTemp=new UiAttribute(nodeList.item(i).getAttributes().getNamedItem("name").getNodeValue(),
                                    namedNodeMap.getNamedItem("name").getNodeValue(),
                                    namedNodeMap.getNamedItem("reflectMethod").getNodeValue(),count);
                            attributesTemp.setParameters(parameters);
                            attributesTemp.setParametersType(parameterType);
                            attributes.add(attributesTemp);

                            System.out.println(attributesTemp.toString());


                    }


                }


            }
            return attributes;
        } catch (ParserConfigurationException e) {
            return null;
        } catch (SAXException e) {
            return null;
        } catch (IOException e) {
            return null;
        }
    }

    public static void main(String[] args){
       new UIComponentXMLParser().parser("/Users/yangzhen/StudioProjects/VisualizationPart/app/src/main/java/UI/SimpleProperties.xml");
    }


    /**
     * 根据名字获取节点
     * @param nodeList
     * @param name
     * @return
     */
    private static Node getNoteByName(NodeList nodeList,String name){
        for(int i=0;i<nodeList.getLength();i++){
            if(nodeList.item(i).getNodeName().equals(name))return nodeList.item(i);
        }
        return null;
    }

    public int getParameterNumber(String model){
        String content= model.substring(1,model.length()-1);
        //System.out.println("提取子串："+content);
        String[] parameter=content.split(",");
        return parameter.length;
    }


    /**
     * 获取在配置文件中，已经给出的参数，仅仅允许部分字面量
     * @param model
     * @param type
     * @return
     */
    public HashMap<Integer,Object> getParameters(String model,HashMap<Integer,String> type){
        String content=model.substring(1,model.length()-1);
        String[] parameter=content.split(",");
        HashMap<Integer,Object> objectHashMap=new HashMap<>();
        try {
            for(int i=0;i<parameter.length;i++){
                if(parameter[i].equals("null")){
                    objectHashMap.put(i,null);
                    continue;
                }
                if(parameter[i].equals("#")){
                    objectHashMap.put(i,null);
                    continue;
                }
                switch (type.get(i)){
                    case "int":objectHashMap.put(i,Integer.valueOf(parameter[i]));break;
                    case "char":objectHashMap.put(i,parameter[i].charAt(0));break;
                    case "long":objectHashMap.put(i,Long.valueOf(parameter[i]));break;
                    case "boolean":objectHashMap.put(i,parameter[i].equals("true")?true:false);break;
                    case "String":objectHashMap.put(i,parameter[i]);break;
                    case "float":objectHashMap.put(i,Float.valueOf(parameter[i]));break;
                    case "double":objectHashMap.put(i,Double.valueOf(parameter[i]));break;
                    default:throw new ClassNotDefineException();
                }

            }
        } catch (ClassNotDefineException e) {
            e.printStackTrace();
        }
        return objectHashMap;
    }

    /**
     * 返回参数类型
     * @param modelType
     * @return
     */
    private HashMap<Integer,String> getParametersType(String modelType){
        String content=modelType.substring(1,modelType.length()-1);
        String[] parameter=content.split(",");
        HashMap<Integer,String> objectHashMap=new HashMap<>();

        for(int i=0;i<parameter.length;i++){
            objectHashMap.put(i,parameter[i]);
        }
        return objectHashMap;
    }

}