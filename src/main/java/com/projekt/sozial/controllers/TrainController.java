package com.projekt.sozial.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

    @RestController
    @RequestMapping("/station")
    public class TrainController {

        @Autowired
        private TrainService trainService;

        @GetMapping("/{ril100}/train/{trainNumber}/waggon/{number}")
        public ResponseEntity<Map<String, List<String>>> getTrainSections(@PathVariable String ril100,
                                                                          @PathVariable Long trainNumber,
                                                                          @PathVariable int number) {
            try {
                Map<String, List<String>> response = trainService.getTrainSections(ril100, trainNumber, number);
                return ResponseEntity.ok(response);
            } catch (Exception e) {
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        }
    }

@Service
public class TrainService {
    private static final String DATA_DIR = "/data"; // Verilerin yüklendiği dizin
    private static final String FILE_EXTENSION = ".xml";

    public Map<String, List<String>> getTrainSections(String ril100, Long trainNumber, int number) throws Exception {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

        // XML dosyasını yüklemek gerek
        File file = new File(DATA_DIR + "/" + ril100 + FILE_EXTENSION);
        Document doc = dBuilder.parse(file);

        // XPath ifadesi oluşturmak gerek
        String xpathExpr = String.format("/stations/station[@ril100='%s']/tracks/track/trains/train[trainNumbers/trainNumber='%d']/wagons/wagon[number='%d']/sections/identifier", ril100, trainNumber, number);
        XPath xPath = XPathFactory.newInstance().newXPath();

        // XPath ifadesini kullanarak Gleisabschnitt bilgilerini almalisin
        NodeList sectionNodes = (NodeList) xPath.compile(xpathExpr).evaluate(doc, XPathConstants.NODESET);

        // Gleisabschnitt bilgilerini liste halinde döndürülmeli
        List<String> sections = new ArrayList<>();
        for (int i = 0; i < sectionNodes.getLength(); i++) {
            Node sectionNode = sectionNodes.item(i);
            sections.add(sectionNode.getTextContent());
        }

        Map<String, List<String>> response = new HashMap<>();
        response.put("sections", sections);
        return response;
    }
}


