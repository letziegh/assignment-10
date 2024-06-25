package com.coderscampus.Assignment10.web;




import com.coderscampus.Assignment10.domain.DayResponse;
import com.coderscampus.Assignment10.domain.WeekResponse;
import org.junit.Test;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.slf4j.Logger;
import java.net.URI;
import java.net.URL;
//import java.util.logging.Logger;



@RestController
//@RequestMapping("/base")
public class MealPlannerController {

    @Value("${spoonacular.api.key}")
    private String apiKey;
//    @Value("${spoonacular.urls.base}")
//    private String urlBase;

    @Value("${spoonacular.urls.mealplan")
    private String urlmealplanner;

    @Value("${spoonacular.urls.base}")
    private String urlBase;

    private RestTemplate restTemplate = new RestTemplate();
   // private String spoonacularUrl = "https://api.spoonacular.com/mealplanner/generate";

        @GetMapping("/week")
    public ResponseEntity<WeekResponse> getWeekMeals(
            @RequestParam String numCalories,
            @RequestParam String diet,
            @RequestParam String exclusions) {

            String url = UriComponentsBuilder.fromHttpUrl(urlmealplanner)
                    .queryParam("timeFrame", "week")
                    .queryParam("targetCalories", numCalories) // use numCalories variable
                    .queryParam("diet", diet != null ? diet : "")  // use diet variable
                    .queryParam("exclude", exclusions != null ? exclusions : "")
                    .queryParam("apiKey", apiKey)
                    .build()
                    .toUriString();

            try {
                WeekResponse response = restTemplate.getForObject(url, WeekResponse.class);
                return ResponseEntity.ok(response);
            } catch (Exception e) {
                return ResponseEntity.status(500).body(null);
            }
        }

    @GetMapping("/day")
    public ResponseEntity<DayResponse> getDayMeals(
            @RequestParam String numCalories,
            @RequestParam(required = false) String diet,
            @RequestParam(required = false) String exclusions) {

        String url = UriComponentsBuilder.fromHttpUrl(urlmealplanner)
                .queryParam("timeFrame", "day")
                .queryParam("targetCalories", numCalories)
                .queryParam("diet", diet != null ? diet : "")
                .queryParam("exclude", exclusions != null ? exclusions : "")
                .queryParam("apiKey", apiKey)
                .toUriString();

        DayResponse response = restTemplate.getForObject(url, DayResponse.class);
        return ResponseEntity.ok(response);
    }
}

//    @GetMapping("/week")
//    public WeekResponse weekResponse(@RequestParam String numCalories,
//                                     @RequestParam String diet,
//                                     @RequestParam String exclusions){
//
//    RestTemplate rt = new RestTemplate();
//    URI uri = UriComponentsBuilder.fromHttpUrl(spoonacularUrl)
//            .queryParam("timeFrame", "week")
//            .queryParam("targetCalories", numCalories) // use numCalories variable
//            .queryParam("diet", diet != null ? diet : "")  // use diet variable
//            .queryParam("exclude", exclusions != null ? exclusions : "")
//            .queryParam("apiKey", apiKey)
//            .build()
//            .toUri();
//
//    ResponseEntity<String> response = rt.getForEntity(uri, String.class);
//        System.out.println(response);
//        return null;
//
//}




