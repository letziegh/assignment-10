package com.coderscampus.Assignment10.web;




import com.coderscampus.Assignment10.config.SpoonacularConfig;
import com.coderscampus.Assignment10.domain.DayResponse;
import com.coderscampus.Assignment10.domain.WeekResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping(value = "/mealplanner", produces = "application/json")
public class MealPlannerController {

    @Autowired
    private SpoonacularConfig config;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/week")
    public ResponseEntity<WeekResponse> getWeekMeals(
            @RequestParam(value = "numCalories", required = false) String numCalories,
            @RequestParam(required = false) String diet,
            @RequestParam(required = false) String exclusions) {

        // Build the URL with query parameters
        String url = UriComponentsBuilder.fromHttpUrl(config.getBaseUrl() + config.getMealplanEndpoint())
                .queryParam("timeFrame", "week")
                .queryParam("targetCalories", numCalories)
                .queryParam("diet", diet != null ? diet : "")
                .queryParam("exclude", exclusions != null ? exclusions : "")
                .queryParam("apiKey", config.getApiKey())
                .toUriString();

        // Log the generated URL for debugging
        // System.out.println("Generated URL: " + url);

        //  try {
        WeekResponse response = restTemplate.getForObject(url, WeekResponse.class);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/day")
    public ResponseEntity<DayResponse> getDayMeals(
            @RequestParam(value = "numCalories", required = false) String numCalories,
            @RequestParam(required = false) String diet,
            @RequestParam(required = false) String exclusions) {

        String url = UriComponentsBuilder.fromHttpUrl(config.getBaseUrl() + config.getMealplanEndpoint())
                .queryParam("timeFrame", "day")
                .queryParam("targetCalories", numCalories)
                .queryParam("diet", diet != null ? diet : "")
                .queryParam("exclude", exclusions != null ? exclusions : "")
                .queryParam("apiKey", config.getApiKey())
                .toUriString();

        // System.out.println("Generated URL: " + url);


            DayResponse response = restTemplate.getForObject(url, DayResponse.class);
            return ResponseEntity.ok(response);

    }
}


//@GetMapping("/week")
//    public ResponseEntity<WeekResponse> getWeekMeals(
//            @RequestParam(value = "numCalories", required = false) String numCalories,
//            @RequestParam(value = "diet", required = false) String diet,
//            @RequestParam(value = "exclusions", required = false) String exclusions) {
//
//        String url = UriComponentsBuilder.fromHttpUrl(config.getBaseUrl() + config.getMealplanEndpoint())
//                .queryParam("timeFrame", "week")
//                .queryParam("targetCalories", numCalories)
//                .queryParam("diet", diet != null ? diet : "")
//                .queryParam("exclude", exclusions != null ? exclusions : "")
//                .queryParam("apiKey", config.getApiKey())
//                .toUriString();
//
//      //  System.out.println("Generated URL: " + url);
//
//        try {
//            WeekResponse response = restTemplate.getForObject(url, WeekResponse.class);
//            return ResponseEntity.ok(response);
//        } catch (HttpClientErrorException e) {
//            if (e.getStatusCode().is4xxClientError()) {
//                System.err.println("Client error: " + e.getStatusCode() + " - " + e.getResponseBodyAsString());
//            } else {
//                System.err.println("Server error: " + e.getStatusCode() + " - " + e.getResponseBodyAsString());
//            }
//            return ResponseEntity.status(e.getStatusCode()).body(null);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return ResponseEntity.status(500).body(null);
//        }
//    }


//@RestController
//@RequestMapping("/mealplanner")
//public class MealPlannerController {
//
//    @Value("${spoonacular.api.key}")
//    private String apiKey;

//    @Value("${spoonacular.urls.mealplan")
//    private String urlmealplanner;
//
//    @Value("${spoonacular.urls.base}")
//    private String urlBase;

//    private RestTemplate restTemplate = new RestTemplate();
//    private String spoonacularUrl = "https://api.spoonacular.com";
//
//    @GetMapping("/week")
//    public ResponseEntity<WeekResponse> getWeekMeals(
//            @RequestParam(value = "numCalories", required = false) String numCalories,
//            @RequestParam(value = "diet", required = false) String diet,
//            @RequestParam(value = "exclusions", required = false) String exclusions) {
//
//        String url = UriComponentsBuilder.fromHttpUrl(spoonacularUrl)
//                .queryParam("timeFrame", "week")
//                .queryParam("targetCalories", numCalories) // use numCalories variable
//                .queryParam("diet", diet != null ? diet : "")  // use diet variable
//                .queryParam("exclude", exclusions != null ? exclusions : "")
//                .queryParam("apiKey", apiKey)
//                .build()
//                .toUriString();
//
//        try {
//            WeekResponse response = restTemplate.getForObject(url, WeekResponse.class);
//            return ResponseEntity.ok(response);
//        } catch (Exception e) {
//            return ResponseEntity.status(500).body(null);
//        }
//    }
//
//        @GetMapping("/day")
//        public ResponseEntity<DayResponse> getDayMeals (
//                @RequestParam(value = "numCalories", required = false) String numCalories,
//                @RequestParam(required = false) String diet,
//                @RequestParam(required = false) String exclusions){
//
//            String url = UriComponentsBuilder.fromHttpUrl(spoonacularUrl)
//                    .queryParam("timeFrame", "day")
//                    .queryParam("targetCalories", numCalories)
//                    .queryParam("diet", diet != null ? diet : "")
//                    .queryParam("exclude", exclusions != null ? exclusions : "")
//                    .queryParam("apiKey", apiKey)
//                    .toUriString();
//
//            DayResponse response = restTemplate.getForObject(url, DayResponse.class);
//            return ResponseEntity.ok(response);
//        }
//    }


//    @GetMapping("/week")
//    public ResponseEntity<String> weekResponse(@RequestParam(value = "numCalories", required = false) String numCalories,
//                                               @RequestParam(value = "diet", required = false) String diet,
//                                               @RequestParam (value = "exclusions", required = false)String exclusions)
//
//    {
//
//        RestTemplate rt = new RestTemplate();
//        URI uri = UriComponentsBuilder.fromHttpUrl(spoonacularUrl)
//                .queryParam("timeFrame", "week")
//                .queryParam("targetCalories", numCalories) // use numCalories variable
//                .queryParam("diet", diet != null ? diet : "")  // use diet variable
//                .queryParam("exclude", exclusions != null ? exclusions : "")
//                .queryParam("apiKey", apiKey)
//                .build()
//                .toUri();
//
//        ResponseEntity<String> response = rt.getForEntity(uri, String.class);
//        System.out.println(response);
//        return response;
//
//    }
//}




