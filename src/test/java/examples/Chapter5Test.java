package examples;

import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class Chapter5Test {

    @Test
    public void requestDeZipCode24848_checkThirdPlaceNameInResponseBody_expectKropp() {

        given().
                when().
                get("http://localhost:9876/de/24848").
                then().
                assertThat().
                body("response.places.place[2].placeName", equalTo("Kropp"));
    }

    @Test
    public void requestDeZipCode24848_checkLatitudeForSecondPlaceInResponseBody_expect5445() {

        given().
                when().
                get("http://localhost:9876/de/24848").
                then().
                assertThat().
                body("response.places.place[1].@latitude", equalTo("54.45"));
    }

    @Test
    public void requestDeZipCode24848_checkNumberOfPlacesWithStateAbbreviationSH_expect4() {

        given().
                when().
                get("http://localhost:9876/de/24848").
                then().
                assertThat().
                body("response.places.place.findAll{it.stateAbbreviation!='SH'}", empty());
    }

}
