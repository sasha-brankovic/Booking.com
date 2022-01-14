Feature: Booking Test
  Scenario: Booking Test
  Given Open Booking.com home page
    When Change language to "Srpski"
    And Search for destination place "Istanbul"
    And Select CheckIn and CheckOut dates "31 mart 2022" "10 maj 2022"
    And Enter number of Adults, Children, Children age and Rooms "1" "4" "6" "5" "2" "10" "3"
    And Click on Search
    Then Compare search result parameters with entered parameters "Istanbul" "31 mart 2022" "10 maj 2022" "1" "4" "3"