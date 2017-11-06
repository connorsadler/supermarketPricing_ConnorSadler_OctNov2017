
Supermarket Pricing Project - By Connor Sadler, November 2017
-------------------------------------------------------------

My initials are "CFS" so all the code is in package "cfs.supermarketpricing" and sub-packages of that. 

This code now works.
There are various test cases in the "test" folder.
The main test for the scenario in the txt file is:
	CheckoutCalculatorTest.testCheckout1_scenarioFromRequirementsDocument

The CheckoutCalculatorTest now outputs a Checkout summary to the console to give developers more information.
This shows the information is there to produce a receipt, which could be done in future.


Running the tests
-----------------
I've now added the gradle wrapper to this project.
You can build the code and run the tests with gradle.
You'll need a command prompt open and cd to the project root directory
i.e. the directory which contains build.gradle
Please then run the command:
	gradlew build test

This will create a report file in this directory:
	(project root)/build/reports/tests/test/index.html


Details
-------
Here's some notes about the classes I've created.
I've tried to comment each class/interface also, so please check the .java file comments also.

MonetaryAmount / MoneySystem
- An amount of money
- I introduced a MoneySystem (aka Currency) to which monetary amounts belong e.g. Sterling

ShoppingBasket/ShoppingBasketItem
- I kept this simple so that if you want to buy multiple tins of beans you'll add more than one
  item to your basket. But that could be changed in future if required
  i.e. we could add a "numberOfItems" property to ShoppingBasketItem
- A ShoppingBasket works in a MoneySystem, and will only accept items from the same MoneySystem
  This avoids any currency conversion - this could be added in future

Pricing Calculation
- Done in CheckoutCalculator, please see comments in that class

SalesPromotions and Discounts
- A SalesPromotion is the offer itself
- A Discount is a deduction because a SalesPromotion has been applied
- See comments in appropriate class files
- There are some tests here:
   XForYSalesPromotionTest
   XForFixedAmountSalesPromotionTest

Note: At present a basket item can count for two or more sales promotions.
      This may not be what is required so could be changed in future.
      This wouldn't be ideal if there was a "3 for 2" and a "2 for 1" running at the same time!
      See XForYSalesPromotionTest.testMultipleSalesPromotionsForSameItem
      To change this, we could keep track of which ShoppingBasketItem has been used when checking
      a SalesPromotion, and only use it once, or otherwise restrict it's use across multiple promotions.
    

Catalog
- for future enhancement, I didn't need this for the moment

Inventory
- for future enhancement, please see code comments



