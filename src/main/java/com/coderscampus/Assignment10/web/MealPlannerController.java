package com.coderscampus.Assignment10.web;



//import com.coderscampus.Assignment10.User;
//import com.coderscampus.Assignment10.UserService;
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
//import java.util.logging.Logger;



@RestController
@RequestMapping("/mealplanner")
public class MealPlannerController {

    @Value("${spoonacular.api.key}")
    private String apiKey;

    private RestTemplate restTemplate = new RestTemplate();
    private String spoonacularUrl = "https://api.spoonacular.com/mealplanner/generate";

    @GetMapping("/week")
    public ResponseEntity<WeekResponse> getWeekMeals(
            @RequestParam String numCalories,
            @RequestParam String diet,
            @RequestParam String exclusions) {

        String url = UriComponentsBuilder.fromHttpUrl(spoonacularUrl)
                .queryParam("timeFrame", "week")
                .queryParam("targetCalories", numCalories) // use numCalories variable
                .queryParam("diet", diet != null ? diet : "")  // use diet variable
                .queryParam("exclude", exclusions != null ? exclusions : "")
                .queryParam("apiKey", apiKey)
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

        String url = UriComponentsBuilder.fromHttpUrl(spoonacularUrl)
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



//UPDATE 5
//@RestController
//@RequestMapping("/mealplanner")
//public class MealPlannerController {
//
//    @Autowired
//    private UserService userService;
//
//    @PostMapping("/connectUser")
//    public ResponseEntity<User> connectUser(@RequestParam String username) {
//        try {
//            User user = userService.connectUser(username);
//            return ResponseEntity.ok(user);
//        } catch (Exception e) {
//            return ResponseEntity.status(500).body(null);
//        }
//    }
//
//    @GetMapping("/week")
//    public ResponseEntity<WeekResponse> getWeekMeals(
//            @RequestParam String username,
//            @RequestParam String numCalories,
//            @RequestParam(required = false) String diet,
//            @RequestParam(required = false) String exclusions) {
//
//        User user = userService.getUser(username);
//        String url = UriComponentsBuilder.fromHttpUrl("https://api.spoonacular.com/mealplanner/" + user.getUsername() + "/week")
//                .queryParam("targetCalories", numCalories)
//                .queryParam("diet", diet != null ? diet : "")
//                .queryParam("exclude", exclusions != null ? exclusions : "")
//                .queryParam("apiKey", user.getApiKey())
//                .queryParam("hash", user.getHash())
//                .toUriString();
//
//        try {
//            WeekResponse response = new RestTemplate().getForObject(url, WeekResponse.class);
//            return ResponseEntity.ok(response);
//        } catch (HttpClientErrorException e) {
//            return ResponseEntity.status(e.getStatusCode()).body(null);
//        } catch (Exception e) {
//            return ResponseEntity.status(500).body(null);
//        }
//    }
//
//    @GetMapping("/day")
//    public ResponseEntity<DayResponse> getDayMeals(
//            @RequestParam String username,
//            @RequestParam String numCalories,
//            @RequestParam(required = false) String diet,
//            @RequestParam(required = false) String exclusions) {
//
//        User user = userService.getUser(username);
//        String url = UriComponentsBuilder.fromHttpUrl("https://api.spoonacular.com/mealplanner/" + user.getUsername() + "/day")
//                .queryParam("targetCalories", numCalories)
//                .queryParam("diet", diet != null ? diet : "")
//                .queryParam("exclude", exclusions != null ? exclusions : "")
//                .queryParam("apiKey", user.getApiKey())
//                .queryParam("hash", user.getHash())
//                .toUriString();
//
//        try {
//            DayResponse response = new RestTemplate().getForObject(url, DayResponse.class);
//            return ResponseEntity.ok(response);
//        } catch (HttpClientErrorException e) {
//            return ResponseEntity.status(e.getStatusCode()).body(null);
//        } catch (Exception e) {
//            return ResponseEntity.status(500).body(null);
//        }
//    }
//}


//UPDATE 4
//@RestController
//@RequestMapping("/mealplanner")
//public class MealPlannerController {
//
//    @Autowired
//    private UserService userService;
//
//    @PostMapping("/connectUser")
//    public ResponseEntity<String> connectUser(@RequestParam String username) {
//        try {
//            String response = userService.connectUser(username);
//            return ResponseEntity.ok(response);
//        } catch (HttpClientErrorException e) {
//            return ResponseEntity.status(e.getStatusCode()).body(e.getResponseBodyAsString());
//        } catch (Exception e) {
//            return ResponseEntity.status(500).body("Internal Server Error");
//        }
//    }
//
//    @GetMapping("/week")
//    public ResponseEntity<WeekResponse> getWeekMeals(
//            @RequestParam String username,
//            @RequestParam String numCalories,
//            @RequestParam(required = false) String diet,
//            @RequestParam(required = false) String exclusions) {
//
//        User user = userService.getUser(username);
//        String url = UriComponentsBuilder.fromHttpUrl("https://api.spoonacular.com/mealplanner/" + username + "/week")
//                .queryParam("targetCalories", numCalories)
//                .queryParam("diet", diet != null ? diet : "")
//                .queryParam("exclude", exclusions != null ? exclusions : "")
//                .queryParam("apiKey", user.getApiKey())
//                .toUriString();
//
//        try {
//            WeekResponse response = new RestTemplate().getForObject(url, WeekResponse.class);
//            return ResponseEntity.ok(response);
//        } catch (HttpClientErrorException e) {
//            return ResponseEntity.status(e.getStatusCode()).body(null);
//        } catch (Exception e) {
//            return ResponseEntity.status(500).body(null);
//        }
//    }
//
//    @GetMapping("/day")
//    public ResponseEntity<DayResponse> getDayMeals(
//            @RequestParam String username,
//            @RequestParam String numCalories,
//            @RequestParam(required = false) String diet,
//            @RequestParam(required = false) String exclusions) {
//
//        User user = userService.getUser(username);
//        String url = UriComponentsBuilder.fromHttpUrl("https://api.spoonacular.com/mealplanner/" + username + "/day")
//                .queryParam("targetCalories", numCalories)
//                .queryParam("diet", diet != null ? diet : "")
//                .queryParam("exclude", exclusions != null ? exclusions : "")
//                .queryParam("apiKey", user.getApiKey())
//                .toUriString();
//
//        try {
//            DayResponse response = new RestTemplate().getForObject(url, DayResponse.class);
//            return ResponseEntity.ok(response);
//        } catch (HttpClientErrorException e) {
//            return ResponseEntity.status(e.getStatusCode()).body(null);
//        } catch (Exception e) {
//            return ResponseEntity.status(500).body(null);
//        }
//    }
//}

//UPDATE3
//@RestController
//@RequestMapping("/mealplanner")
//public class MealPlannerController {
//
//    private static final Logger logger = (Logger) LoggerFactory.getLogger(MealPlannerController.class);
//
//    @Value("${spoonacular.api.key}")
//    private String apiKey;
//
//    private final RestTemplate restTemplate = new RestTemplate();
//    private final String spoonacularUrl = "https://api.spoonacular.com/";
//
//    @PostMapping("/connectUser")
//    public ResponseEntity<String> connectUser(@RequestParam String username) {
//        String url = UriComponentsBuilder.fromHttpUrl(spoonacularUrl + "users/connect")
//                .queryParam("apiKey", apiKey)
//                .toUriString();
//
//        String requestBody = String.format("{\"username\": \"%s\"}", username);
//
//        try {
//            String response = restTemplate.postForObject(url, requestBody, String.class);
//            return ResponseEntity.ok(response);
//        } catch (HttpClientErrorException e) {
//            logger.error("Error connecting user: {}", e.getMessage(), e);
//            return ResponseEntity.status(e.getStatusCode()).body(e.getResponseBodyAsString());
//        } catch (Exception e) {
//            logger.error("Unexpected error connecting user: {}", e.getMessage(), e);
//            return ResponseEntity.status(500).body("Internal Server Error");
//        }
//    }
//
//    @GetMapping("/week")
//    public ResponseEntity<WeekResponse> getWeekMeals(
//            @RequestParam String username,
//            @RequestParam String numCalories,
//            @RequestParam(required = false) String diet,
//            @RequestParam(required = false) String exclusions) {
//
//        String url = UriComponentsBuilder.fromHttpUrl(spoonacularUrl + "mealplanner/" + username + "/week")
//                .queryParam("targetCalories", numCalories)
//                .queryParam("diet", diet != null ? diet : "")
//                .queryParam("exclude", exclusions != null ? exclusions : "")
//                .queryParam("apiKey", apiKey)
//                .toUriString();
//
//        try {
//            WeekResponse response = restTemplate.getForObject(url, WeekResponse.class);
//            return ResponseEntity.ok(response);
//        } catch (HttpClientErrorException e) {
//            logger.error("Error fetching week meals: {}", e.getMessage(), e);
//            return ResponseEntity.status(e.getStatusCode()).body(null);
//        } catch (Exception e) {
//            logger.error("Unexpected error fetching week meals: {}", e.getMessage(), e);
//            return ResponseEntity.status(500).body(null);
//        }
//    }
//
//    @GetMapping("/day")
//    public ResponseEntity<DayResponse> getDayMeals(
//            @RequestParam String username,
//            @RequestParam String numCalories,
//            @RequestParam(required = false) String diet,
//            @RequestParam(required = false) String exclusions) {
//
//        String url = UriComponentsBuilder.fromHttpUrl(spoonacularUrl + "mealplanner/" + username + "/day")
//                .queryParam("targetCalories", numCalories)
//                .queryParam("diet", diet != null ? diet : "")
//                .queryParam("exclude", exclusions != null ? exclusions : "")
//                .queryParam("apiKey", apiKey)
//                .toUriString();
//
//        try {
//            DayResponse response = restTemplate.getForObject(url, DayResponse.class);
//            return ResponseEntity.ok(response);
//        } catch (HttpClientErrorException e) {
//            logger.error("Error fetching day meals: {}", e.getMessage(), e);
//            return ResponseEntity.status(e.getStatusCode()).body(null);
//        } catch (Exception e) {
//            logger.error("Unexpected error fetching day meals: {}", e.getMessage(), e);
//            return ResponseEntity.status(500).body(null);
//        }
//    }
//}
//UPDATE 2
//@RestController
//@RequestMapping("/mealplanner")
//public class MealPlannerController {
//
//    @Value("${spoonacular.api.key}")
//    private String apiKey;
//
//    private final RestTemplate restTemplate = new RestTemplate();
//    private final String spoonacularUrl = "https://api.spoonacular.com/";
//
//    @PostMapping("/connectUser")
//    public ResponseEntity<String> connectUser(@RequestParam String username) {
//        String url = UriComponentsBuilder.fromHttpUrl(spoonacularUrl + "users/connect")
//                .queryParam("apiKey", apiKey)
//                .toUriString();
//
//        String requestBody = String.format("{\"username\": \"%s\"}", username);
//        String response = restTemplate.postForObject(url, requestBody, String.class);
//        return ResponseEntity.ok(response);
//    }
//
//    @GetMapping("/week")
//    public ResponseEntity<WeekResponse> getWeekMeals(
//            @RequestParam String username,
//            @RequestParam String numCalories,
//            @RequestParam(required = false) String diet,
//            @RequestParam(required = false) String exclusions) {
//
//        String url = UriComponentsBuilder.fromHttpUrl(spoonacularUrl + "mealplanner/" + username + "/week")
//                .queryParam("targetCalories", numCalories)
//                .queryParam("diet", diet != null ? diet : "")
//                .queryParam("exclude", exclusions != null ? exclusions : "")
//                .queryParam("apiKey", apiKey)
//                .toUriString();
//
//        WeekResponse response = restTemplate.getForObject(url, WeekResponse.class);
//        return ResponseEntity.ok(response);
//    }
//
//    @GetMapping("/day")
//    public ResponseEntity<DayResponse> getDayMeals(
//            @RequestParam String username,
//            @RequestParam String numCalories,
//            @RequestParam(required = false) String diet,
//            @RequestParam(required = false) String exclusions) {
//
//        String url = UriComponentsBuilder.fromHttpUrl(spoonacularUrl + "mealplanner/" + username + "/day")
//                .queryParam("targetCalories", numCalories)
//                .queryParam("diet", diet != null ? diet : "")
//                .queryParam("exclude", exclusions != null ? exclusions : "")
//                .queryParam("apiKey", apiKey)
//                .toUriString();
//
//        DayResponse response = restTemplate.getForObject(url, DayResponse.class);
//        return ResponseEntity.ok(response);
//    }
//}

//FIRST DRAFT
//@RestController
//@RequestMapping("/mealplanner")
//public class MealPlannerController {
//
//    @Value("${spoonacular.api.key}")
//    private String apiKey;
//
//    private final RestTemplate restTemplate = new RestTemplate();
//    private final String spoonacularUrl = "https://api.spoonacular.com/mealplanner/{username}/week/{start-date}";
//
//
//    @GetMapping("/week")
//    public ResponseEntity<WeekResponse> getWeekMeals(
//            @RequestParam int numCalories,
//            @RequestParam String diet,
//            @RequestParam String exclusions) {
//
//        try {
//            URI uri = UriComponentsBuilder.fromHttpUrl(spoonacularUrl)
//                    .queryParam("timeFrame", "week")
//                    .queryParam("targetCalories", numCalories)
//                    .queryParam("diet", diet)
//                    .queryParam("exclude", exclusions)
//                    .queryParam("apiKey", apiKey)
//                    .build()
//                    .toUri();
//
//            WeekResponse response = restTemplate.getForObject(uri, WeekResponse.class);
//           // System.out.println(response);
//            return ResponseEntity.ok(response);
//           // System.out.println(response);
//        } catch (Exception e) {
//    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }
//    }

//    @GetMapping("/day")
//    public ResponseEntity<DayResponse> getDayMeals(
//            @RequestParam String numCalories,
//            @RequestParam String diet,
//            @RequestParam String exclusions) {
//
//        try {
//            String url = UriComponentsBuilder.fromHttpUrl(spoonacularUrl)
//                    .queryParam("timeFrame", "day")
//                    .queryParam("targetCalories", numCalories)
//                    .queryParam("diet", diet)
//                    .queryParam("exclude", exclusions)
//                    .queryParam("apiKey", apiKey)
//                    .toUriString();
//
//            DayResponse response = restTemplate.getForObject(url, DayResponse.class);
//            return ResponseEntity.ok(response);
//        } catch (Exception e){
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }
//    }

