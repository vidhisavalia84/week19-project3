package com.herokuapp.booker.bookinginfo;

import com.herokuapp.booker.testbase.TestBase;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Title;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.core.annotations.WithTags;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class TagTest extends TestBase {
    @WithTag("bookingfeature:NEGATIVE")
    @Title("Incorrect HTTP method used to access resources and validate response 405")
    @Test
    public void invalidMethod() {
        SerenityRest.given().log().all()
                .when()
                .put("/booking")
                .then()
                .statusCode(404);
    }

    @WithTag("bookingfeature:POSITIVE")
    @Title("Correct HTTP method is used to access resources")
    @Test
    public void validMethod() {
        SerenityRest.given()
                .when()
                .get("/booking")
                .then()
                .statusCode(200).log();
    }

    @WithTags({
            @WithTag("bookingfeature:NEGATIVE"),
            @WithTag("bookingfeature:SMOKE")
    })
    @Title("This test will provide an error code of 400 when user tries to access an invalid resource")
    @Test
    public void invalidresources() {
SerenityRest.given()
        .when()
        .get("/booking123")
        .then()
        .statusCode(404);
    }
}
