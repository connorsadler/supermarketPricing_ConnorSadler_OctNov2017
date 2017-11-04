
Supermarket Pricing Project - By Connor Sadler, November 2017
-------------------------------------------------------------

My initials are "CFS" so all the code is in package "cfs.supermarketpricing" and sub-packages of that. 



*************
** WARNING **
*************
This is a work in progress and doesn't work quite yet
Please bear with me!




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

Offers
- cfstodo: notes

Catalog
- cfstodo: notes - not sure if I need this yet


Inventory
- for future enhancement, please see code comments



