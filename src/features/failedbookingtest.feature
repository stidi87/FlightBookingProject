Feature: Declined payment
Description: This feature is to test the declined payment funcionality

Scenario: Error message during booking a flight because of a wrong credit card number
Given A user books a flight from Poland/Warsaw to United Kingdom/London from 15-08-2016 to 20-08-2016 for two adults and one child
And Correctly provide the rest of the details up to the payment page
When The user provides invalid credit card number details, i.e. "5575373244793555", "5/2020" and "111"
And Clicks Pay Now
Then He gets an error message saying that the payment was not authorised