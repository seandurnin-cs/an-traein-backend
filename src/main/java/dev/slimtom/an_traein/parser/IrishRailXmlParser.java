package dev.slimtom.an_traein.parser;

import dev.slimtom.an_traein.model.TrainObservation;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import java.io.StringReader;
import java.time.LocalDateTime;

@Component
public class IrishRailXmlParser {
    
    public List<TrainObservation> parseStationData(String rawXml) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        Document document = builder.parse(
            new InputSource(new StringReader(rawXml))
        );

        document.getDocumentElement().normalize();

        NodeList trainNodes = document.getElementsByTagName("objStationData");

        List<TrainObservation> observations = new ArrayList<>();

        String observedTime = LocalDateTime.now().toString();

        for(int i = 0; i < trainNodes.getLength(); i++) {
            Element trainElement = (Element) trainNodes.item(i);

            TrainObservation observation = new TrainObservation(
                getText(trainElement, "Servertime"),
                observedTime,
                getText(trainElement, "Traincode"),
                getText(trainElement, "Stationfullname"),
                getText(trainElement, "Stationcode"),
                getText(trainElement, "Querytime"),
                getText(trainElement, "Traindate"),
                getText(trainElement, "Origin"),
                getText(trainElement, "Destination"),
                getText(trainElement, "Origintime"),
                getText(trainElement, "Destinationtime"),
                getText(trainElement, "Status"),
                getText(trainElement, "Lastlocation"),
                parseInt(getText(trainElement, "Duein")),
                parseInt(getText(trainElement, "late")),
                getText(trainElement, "Exparrival"),
                getText(trainElement, "Expdepart"),
                getText(trainElement, "Scharrival"),
                getText(trainElement, "Schdepart"),
                getText(trainElement, "Direction"),
                getText(trainElement, "Traintype"),
                getText(trainElement, "Locationtype")
            );

            observations.add(observation);
        }

        return observations;

    }

    private String getText(Element parent, String tagName) {
        NodeList nodes = parent.getElementsByTagName(tagName);

        if(nodes.getLength() == 0 || nodes.item(0) == null) {
            return "";
        }

        return nodes.item(0).getTextContent().trim();
    }

    private int parseInt(String value) {
        if(value == null || value.isBlank()) {
            return 0;
        }

        return Integer.parseInt(value);
    }
}
