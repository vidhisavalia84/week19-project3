package com.herokuapp.booker.cucumber;

import com.herokuapp.booker.testbase.TestBase;
import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import net.serenitybdd.junit.runners.SerenityRunner;
import org.ccil.cowan.tagsoup.Scanner;
import org.junit.runner.RunWith;


@CucumberOptions(features = "src/test/java/resources/feature")
@RunWith(CucumberWithSerenity.class)
public class CucumberRunner extends TestBase {
}
