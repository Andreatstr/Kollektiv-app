package view;

import org.htmlunit.WebClient;
import org.htmlunit.html.HtmlPage;
import org.htmlunit.html.HtmlTextInput;
import org.htmlunit.html.HtmlButton;

public class WastePlaceScrape {
    public String performSearch(String address) {
        // Create a WebClient (browser-like behavior)
        try (final WebClient webClient = new WebClient()) {
            // Disable CSS and enable JavaScript to match the behavior of the site
            webClient.getOptions().setCssEnabled(false);
            webClient.getOptions().setJavaScriptEnabled(false);

            // Load the page (initial page load)
            HtmlPage page = webClient.getPage("https://trv.no/plan/");

            // Locate the search box input field by its id
            HtmlTextInput searchBox = page.getFirstByXPath("//input[@id='adress-searchquery']");

            // Locate the submit button by finding the nearest button element
            HtmlButton searchButton = page.getFirstByXPath("//button[@type='submit']");

            // Check if search box and button are present
            if (searchBox == null || searchButton == null) {
                throw new IllegalStateException("Search box or button not found on the page.");
            }

            // Enter the address in the search box
            searchBox.setValueAttribute(address);

            // Submit the form by clicking the search button
            HtmlPage resultPage = searchButton.click();

            // Return the URL of the result page
            return resultPage.getUrl().toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
