package com.hojin;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class XMLParsingMain {

    public static void main(String[] args) {
	    try{
            URL url=
                new URL("http://openapi.seoul.go.kr:8088/757164717163686a363145416a7a46/xml/ListCulturalAssetsInfo/1/20/");
            InputStream inputStream=
                url.openStream();

            DocumentBuilderFactory documentBuilderFactory=
                DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder=
                documentBuilderFactory.newDocumentBuilder();
            Document document=
                documentBuilder.parse(inputStream);
            document.getDocumentElement().normalize();

            System.out.println("Root element :" + document.getDocumentElement().getNodeName());
            NodeList nodeList=
                document.getElementsByTagName("row");
            System.out.println("-------------------------------");

            for(int temp=0;temp<nodeList.getLength();temp++){
                Node node=nodeList.item(temp);
                if(node.getNodeType()==Node.ELEMENT_NODE){
                    Element element=(Element)node;

                    System.out.println("문화재 일련번호 : "+getTagValue("MANAGE_NUM", element));
                    System.out.println("문화재명칭_한글 : "+getTagValue("NAME_KOR", element));
                    System.out.println("한자 : "+getTagValue("NAME_CNI",element));
                   // System.out.println("영문 : " +getTagValue("NAME_ENG",element));
                    System.out.println("수량ㆍ규모 : "+getTagValue("SCALE",element));
                    System.out.println("규격 :"+getTagValue("STANDARD",element));
                    System.out.println("구조ㆍ형식 : "+getTagValue("FORM", element));
                    System.out.println("재질 : "+getTagValue("MATTER",element));
                    //System.out.println("조성연대 : "+getTagValue("MAKEYEAR",element));
                    //System.out.println("측량성과 : "+getTagValue("FILE_ATT",element));
                    System.out.println("지정 사유(요약) : "+getTagValue("APP_REASON_MINI",element));
                    System.out.println("안내판 본문_한글 : "+getTagValue("BOARD_KOR",element));
                    //System.out.println("영문 : "+getTagValue("BOARD_ENG",element));
                    System.out.println("소재지ㆍ보관장소_자치구 : "+getTagValue("STAND_REGION",element));
                    System.out.println("기타주소 : "+getTagValue("STAND_ADDR",element));
                    //System.out.println("관리청 : "+getTagValue("MANAGE_AGENCY",element));
                    System.out.println("지정일 : "+getTagValue("APP_DATE",element));
                    //System.out.println("지정 사유 : "+getTagValue("APP_REASON",element));    일부는 파싱됨.
                    //System.out.println("해제일 : "+getTagValue("CANCEL_DATE",element));      일부는 파싱됨.



                    System.out.println();
                }
            }



        }catch(Exception exception){
            exception.printStackTrace();
        }
    }

    private static String getTagValue(String sTag, Element eElement){
        NodeList nodeList=eElement.getElementsByTagName(sTag).item(0).getChildNodes();
        Node node=(Node)nodeList.item(0);
        return node.getNodeValue();
    }
}
























