# Use Case Model

*This is the template for your use case model. The parts in italics are concise explanations of what should go in the corresponding sections and should not appear in the final document.*

**Author**: Kyle Sullivan (ksullivan40@gatech.edu)

## 1 Use Case Diagram

*This section should contain a use case diagram with all the actors and use cases for the system, suitably connected.*

![Use Case Diagram v 0.1](UseCaseDiagram.png)


## 2 Use Case Descriptions

*For each use case in the use case diagram, this section should contain a description, with the following elements:*

- *Requirements: High-level description of what the use case must allow the user to do.*
- *Pre-conditions: Conditions that must be true before the use case is run.*
- *Post-conditions Conditions that must be true once the use case is run.*
- *Scenarios: Sequence of events that characterize the use case. This part may include multiple scenarios, for normal, alternate, and exceptional event sequences. These scenarios may be expressed as a list of steps in natural language or as sequence diagrams.*

### Definitions:

 * Application: Refers to the EZ Shop App running on Android on the customer's phone.

 * POS System: Refers to the Point of Sale System running on the cashier's machine.

 * Grocery Shopping Mode: Refers to the screen containing the "+", "-", and "Pay" buttons.

###1. Launch App
   * **Requirements:** The Launch App use case must allow the customer to launch launch the EZ Shop Application, scan their card, initialize all customer data, and enter into grocery shopping mode.

   * **Pre-conditions:** The cusomter's phone is on and the EZ Shop Application is not running.

   * **Post-Conditions:** The cusomter's information has been intialized, an empty shopping cart has been created in the application, and the application is in grocery shopping mode.

   * **Scenarios:** 

      **1. Normal Scenario**

	1.1 The customer touches the EZ Shop App icon on their phone.

	1.2 The application launches.

	1.3 The application's QR Code Scanner opens from within the app.

	1.4 The customer holds their customer card's QR code in front of the phone's camera.

	1.5 The application identifies the QR Code and reads its data.

	1.6 The application initializes the customer's first name, last name, zip code, and e-mail address within the application using the QR code data.

	1.7 The application creates an empty shopping cart within the customer object.

	1.8 The application enters the grocery shopping mode screen and the QR Code Scanner closes.

   **2. Exceptional Scenario: Non Customer QR Code Scenario**

	2.1 The customer touches the EZ Shop App icon on their phone.
		      
	2.2 The application launches.

	2.3 The application's QR Code Scanner opens from within the app.

	2.4 The customer holds a QR for some item other than a customer card in front of the phone's camera.

	2.5 The application identifies the QR Code and reads its data.

	2.6 The application produces an error message: "That is not a customer card QR code. Please scan a customer card QR code or close the application."

	2.7 The user selects the "X" on the error message to acknowledge and close the error message.

	2.8 The QR Code Scanner remains open awaiting a customer card QR code.

	2.9 The customer either scans a customer card QR code or closes the application.

###2. Add Product
   * **Requirements:** The Add Product use case must allow the customer to press the "+" button on the application, scan a product's QR code, and add the item's ID and price to the customer's list of items in the customer's shopping cart in the application.

   * **Pre-conditions:** The EZ Shop Application is is in grocery shopping mode and the customer is holding a *product* with a QR code.

   * **Post-Conditions:** The product's QR code has been scanned, the item's price and ID have been added to the the customer's list of items in the customer's shopping cart in the application, and the application returns to the grocery shopping mode screen.

   * **Scenarios:** 

      **1. Normal Scenario: Product not yet scanned**

	1.1 The customer touches the "+" button on the grocery shopping mode screen.

	1.2 The application's QR Code Scanner opens from within the app.

	1.3 The customer holds a product's QR code in front of of the phone's camera.

	1.4 The application reads the product's ID and price from the scanned QR code.

	1.5 The application adds the product's ID and price to the customer's list of items in the customer's shopping cart in the application.

	1.6 The application prints a confirmation for three seconds that "Product **X** was added to the cart."

	1.7 The application enters the grocery shopping mode screen and the QR Code Scanner closes.

     **2. Alternate Scenario: Alcoholic Beverage**

	2.1 The customer touches the "+" button on the grocery shopping mode screen.
	
	2.2 The application's QR Code Scanner opens from within the app.

	2.3 The customer holds an alcoholic beverage's QR code in front of of the phone's camera.

	2.4 The application reads the product's ID and price from the scanned QR code.

	2.5 The application adds the product's ID and price to the customer's list of items in the customer's shopping cart in the application.

	2.6 The application calculates a sepcial tax for the beverage and adds that tax to the specialTax field of the item in the cutomer's shopping cart in the application.

	2.7 The application prints a confirmation for three seconds that "Alcoholic bevarage **X** was added to the cart."

	2.8 The application enters the grocery shopping mode screen and the QR Code Scanner closes.

      **3. Alternate Scenario: Product already scanned at least once**

	3.1 The customer touches the "+" button on the grocery shopping mode screen.
	
	3.2 The application's QR Code Scanner opens from within the app.

	3.3 The customer holds a product's QR code in front of of the phone's camera.

	3.4 The application reads the product's ID and price from the scanned QR code.

	3.5 The application adds the product's ID and price to the customer's list of items in the customer's shopping cart in the application.

	3.6 The application prints a confirmation for three seconds that "Product **X** was added to the cart. There are **#** product **X** now in the cart. "

	3.7 The application enters the grocery shopping mode screen and the QR Code Scanner closes.



###3. Add Coupon
   * **Requirements:** The Add Coupon use case must allow the customer to press the "+" button on the application, scan a coupon's QR code, and add the ID and discount to the list of coupons the customer's shopping cart in the application.

   * **Pre-conditions:** The EZ Shop Application is is in grocery shopping mode and the customer is holding a *coupon* with a QR code.

   * **Post-Conditions:** The coupon's QR code has been scanned, the coupon has been added the list of coupons in the customer's shopping cart, and the application returns to the grocery shopping mode screen.

   * **Scenarios:** 

      **1. Normal Scenario: Product with matching ID already scanned coupon not yet scanned.**

	1.1 The customer touches the "+" button on the grocery shopping mode screen.

	1.2 The application's QR Code Scanner opens from within the app.

	1.3 The customer holds a coupon's QR code in front of of the phone's camera.

	1.4 The application reads the product's ID and discount from the scanned QR code.

	1.5 The application adds the product's ID and discount to the customer's list of coupons in the customer's shopping cart in the application.

	1.6 The application checks to see if a product matching the coupon's ID is in the customer's list of items in the shopping cart.

	1.7 The application prints a confirmation message to the screen indicating: "A coupon was added for product **X**" for three seconds. 

	1.8 The application enters the grocery shopping mode screen and the QR Code Scanner closes.

      **2. Alternate Scenario: Product with matching ID not already scanned and coupon not yet scanned.**

	2.1 The customer touches the "+" button on the grocery shopping mode screen.

	2.2 The application's QR Code Scanner opens from within the app.

	2.3 The customer holds a coupon's QR code in front of of the phone's camera.

	2.4 The application reads the product's ID and discount from the scanned QR code.

	2.5 The application adds the product's ID and discount to the customer's list of coupons in the customer's shopping cart in the application.

	2.6 The application checks to see if a product matching the coupon's ID is in the customer's list of items in the shopping cart.

	2.7 The application prints a confirmation message to the screen indicating: "A coupon was added for product **X** but product **X** has not been added to the cart" for three seconds. 

	2.8 The application enters the grocery shopping mode screen and the QR Code Scanner closes.

      **3. Exceptional Scenario: Coupon already scanned.**

	3.1 The customer touches the "+" button on the grocery shopping mode screen.

	3.2 The application's QR Code Scanner opens from within the app.

	3.3 The customer holds a coupon's QR code in front of of the phone's camera.

	3.4 The application reads the product's ID and discount from the scanned QR code.

	3.5 The application determines that the product's ID and discount has already been added to the customer's list of coupons in the customer's shopping cart in the application.

	3.6 The application prints an error message to the screen indicating: "That coupon has already been scanned."

	3.7 The customer selects the "X" on the error message to acknowledge and close the error message.

	3.8 The application enters the grocery shopping mode screen and the QR Code Scanner closes.

###4. Remove Product
   * **Requirements:** The Remove Product use case must allow the customer to press the "-" button on the application, scan a products's QR code, and remove the item ID and price from the list of products the customer's shopping cart in the application.

   * **Pre-conditions:** The EZ Shop Application is in grocery shopping mode, the customer is holding a *product* with a QR code, and at least one instance of the product is listed in the customer's shopping cart list of items in the application.

   * **Post-Conditions:** The product's QR code has been scanned, the product has been removed the list of items in the customer's shopping cart, and the application returns to the grocery shopping mode screen.

   * **Scenarios:** 

      **1. Normal Scenario: Product with matching ID previously scanned.**

	1.1 The customer touches the "-" button on the grocery shopping mode screen.

	1.2 The application's QR Code Scanner opens from within the app.

	1.3 The customer holds a product's QR code in front of of the phone's camera.

	1.4 The application reads the product's ID and price from the scanned QR code.

	1.5 The application removes one instance of the product's ID and price from the customer's list of products in the customer's shopping cart in the application.

	1.6 The application prints a confirmation message to the screen indicating: "One instance of product **X** was removed. There are **#** of product **X** remaining in the cart." for three seconds. 

	1.7 The application enters the grocery shopping mode screen and the QR Code Scanner closes.

      **2. Exceptional Scenario: Product with matching ID not previously scanned.**

	2.1 The customer touches the "-" button on the grocery shopping mode screen.

	2.2 The application's QR Code Scanner opens from within the app.

	2.3 The customer holds a product's QR code in front of of the phone's camera.

	2.4 The application reads the product's ID and price from the scanned QR code.

	2.5 The application determines there are no instances of the product's ID and price in the customer's list of products in the customer's shopping cart in the application.

	2.6 The application prints a confirmation message to the screen indicating: "Product **X** not in your cart."  

	2.7 The customer selects the "X" on the error message to acknowledge and close the error message.

	1.8 The application enters the grocery shopping mode screen and the QR Code Scanner closes.


