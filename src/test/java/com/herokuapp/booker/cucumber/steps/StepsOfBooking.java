package com.herokuapp.booker.cucumber.steps;

import com.herokuapp.booker.bookinginfo.AuthSteps;
import com.herokuapp.booker.bookinginfo.BookingSteps;
import com.herokuapp.booker.utils.TestUtils;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.ValidatableResponse;
import net.thucydides.core.annotations.Steps;
import static org.hamcrest.CoreMatchers.equalTo;
public class StepsOfBooking {
    static String firstName = "PrimUser" + TestUtils.getRandomValue();
    static String lastName = "PrimeUser" + TestUtils.getRandomValue();
    static int totalPrice = 500;
    static boolean depositPaid = true;
    static String checkIn = "2022-01-07";
    static String checkOut = "2022-01-20";
    static String additionalNeeds = "Breakfast";
    static String username = "admin";
    static String password = "password123";
    static int bookingId;
    static String token;


    static ValidatableResponse response;


    @Steps
    BookingSteps bookingSteps;
    @Steps
    AuthSteps authSteps;
    @When("^I send post request to create booking$")
    public void iSendPostRequestToCreateBooking() {
        response = bookingSteps.createBooking(firstName, lastName, totalPrice, depositPaid, checkIn, checkOut, additionalNeeds);
        bookingId=(int) response.extract().path("bookingid");
        System.out.println(bookingId);
    }

    @And("^I should get back with status code 200$")
    public void iShouldGetBackWithStatusCode() {
        response.statusCode(200);
    }



    @When("^I send get request with bookingId$")
    public void iSendGetRequestWithBookingId() {
        response=bookingSteps.getBookingWithBookingId(bookingId);
    }

    @And("^I should get back with response 200$")
    public void iShouldGetBackWithResponse() {
        response.statusCode(200);
        response.body("firstname",equalTo(firstName),"lastname",equalTo(lastName));
    }


    @When("^when I send post request with bookingId$")
    public void whenISendPostRequestWithBookingId() {
      token=  authSteps.getAuthToken(username,password);
        firstName=firstName+"_added";
        totalPrice=7;

        response=bookingSteps.updateBooking(bookingId,firstName,lastName,totalPrice,depositPaid,checkIn,checkOut,additionalNeeds,token);
    }

    @Then("^I shold see booking is updated and get back with response code 200$")
    public void iSholdSeeBookingIsUpdatedAndGetBackWithResponseCode() {
        response.statusCode(200);
        response.body("firstname",equalTo(firstName),"totalprice",equalTo(totalPrice));
    }

    @When("^I send delete request with bookindId$")
    public void iSendDeleteRequestWithBookindId() {
        response=bookingSteps.deleteBookingWithBookingId(bookingId,token).statusCode(201);
    }

    @Then("^I should see booking is deleted$")
    public void iShouldSeeBookingIsDeleted() {
        response=bookingSteps.deleteBookingWithBookingId(bookingId,token).statusCode(405);
    }
}
