package com.jjmontenegrop.springbootwebscraping.services;

import com.jjmontenegrop.springbootwebscraping.dtos.ResponseDTO;
import lombok.Builder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Builder
@Service
public class ScraperServiceImpl implements ScraperService {
    //Reading data from property file to a list
    @Value("#{'${webscraping.url}'.split(',')}")
    private List<String> urls;

    @Override
    public Set<ResponseDTO> getVehiclesByModel(String model) {
        //Using a set here to only store unique elements
        Set<ResponseDTO> responseDTOS = new HashSet<>();
        //Traversing through the urls
        for (String url : urls) {
            if (url.contains("tucarro")) {
                extractDataFromTucarro(responseDTOS, url + model);
            }

        }
        return responseDTOS;
    }

    private void extractDataFromTucarro(Set<ResponseDTO> responseDTOS, String url) {
        //Extracting data from tucarro
        System.out.println("Extracting data from tucarro");
        try {
            Document document = Jsoup.connect(url).get();
            Element element = document.getElementById(":Rcl5e6:");
            Elements elements = element.getElementsByTag("a");
            //traversing through the elements
            for (Element ads : elements) {
                ResponseDTO responseDTO = new ResponseDTO();

                if (!StringUtils.isEmpty(ads.attr("title"))) {
                    //mapping data to the model class
                    responseDTO.setTitle(ads.attr("title"));
                    responseDTO.setUrl(ads.attr("href"));
                }
                if (responseDTO.getUrl() != null) responseDTOS.add(responseDTO);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
