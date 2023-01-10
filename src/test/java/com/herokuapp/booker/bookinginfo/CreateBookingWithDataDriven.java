package com.herokuapp.booker.bookinginfo;

import com.herokuapp.booker.model.BookingDates;
import com.herokuapp.booker.testbase.TestBase;
import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import net.thucydides.junit.annotations.Concurrent;
import net.thucydides.junit.annotations.UseTestDataFrom;
import org.junit.Test;
import org.junit.runner.RunWith;

@Concurrent(threads = "8X")
@UseTestDataFrom("src/test/java/resources/testdata/bookinginfo.csv")
@RunWith(SerenityParameterizedRunner.class)
public class CreateBookingWithDataDriven extends TestBase {

    private String firstname;
    private String lastname;
    private int totalprice;
    private boolean depositpaid;
    private String checkIn;
    private String checkOut;
    private BookingDates bookingdates;
    private String additionalneeds;


    @Steps
    BookingSteps bookingSteps;

    @Title("Data driven test for adding multiple booking to the application")
    @Test
    public void createMultipleBookind() {
        bookingSteps.createBooking(firstname, lastname, totalprice, depositpaid, checkIn, checkOut, additionalneeds).statusCode(200);
    }
}