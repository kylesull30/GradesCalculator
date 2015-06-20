# Use Case Model

**Author**: \<Kyle Sullivan (ksullivan40@gatech.edu)\>

## 1 Use Case Diagram

![Use Case Diagram v 0.3](UseCaseDiagram.png)


## 2 Use Case Descriptions

### Definitions:

 * Application: Refers to the EZ Shop App running on Android on the customer's phone.

 * POS System: Refers to the Point of Sale System running on the cashier's machine.

 * Grocery Shopping Mode: Refers to the screen containing the "+", "-", and "Pay" buttons.

###1. Launch App
   * **Requirements:** The Launch App use case must allow the customer to launch the EZ Shop Application, scan their card, initialize all customer data, and enter into grocery shopping mode.

   * **Pre-conditions:** The cusomter's phone is on and the EZ Shop Application is not running.

   * **Post-Conditions:** The cusomter's information has been intialized, an empty shopping cart has been created in the application, and the application is in grocery shopping mode.

   * **Scenarios:** 

      **1. Normal Scenario**

	1.1 The customer touches the EZ Shop App icon on their phone.

	1.2 The application launches.

	1.3 The application's QR Code Scanner opens from within the app.

	1.4 The customer holds their customer card QR code in front of the phone's camera.

	1.5 The application identifies the QR Code and reads its data.

	1.6 The application initializes the customer's first name, last name, zip code, and e-mail address within the application using the QR code data.

	1.7 The application creates an empty shopping cart within the customer object.

	1.8 The application enters the grocery shopping mode screen and the QR Code Scanner closes.

   **2. Exceptional Scenario: Non Customer QR Code Scenario**

	2.1 The customer touches the EZ Shop App icon on their phone.
		      
	2.2 The application launches.

	2.3 The application's QR Code Scanner opens from within the app.

	2.4 The customer holds a QR code for some item other than a customer card in front of the phone's camera.

	2.5 The application identifies the QR Code and reads its data.

	2.6 The application produces an error message: "That is not a customer card QR code. Please scan a customer card QR code or close the application."

	2.7 The customer selects the "X" on the error message to acknowledge and close the error message.

	2.8 The QR Code Scanner remains open awaiting a customer card QR code.

	2.9 The customer either scans a customer card QR code or closes the application.

###2. Add Product
   * **Requirements:** The Add Product use case must allow the customer to press the "+" button on the application, scan a product's QR code, and add one instance of the item's ID and price to the customer's list of items in the customer's shopping cart in the application.

   * **Pre-conditions:** The EZ Shop Application is in grocery shopping mode and the customer is holding a *product* with a QR code.

   * **Post-Conditions:** The product's QR code has been scanned, the item's price and ID have been added to the the customer's list of items in the customer's shopping cart in the application, and the application returns to the grocery shopping mode screen.

   * **Scenarios:** 

      **1. Normal Scenario: Product not yet scanned**

	1.1 The customer touches the "+" button on the grocery shopping mode screen.

	1.2 The application's QR Code Scanner opens from within the app.

	1.3 The customer holds a product's QR code in front of the phone's camera.

	1.4 The application reads the product's ID and price from the scanned QR code.

	1.5 The application adds one instance of the product's ID and price to the customer's list of items in the customer's shopping cart in the application.

	1.6 The application prints a confirmation for three seconds that "Product **X** was added to the cart and a special tax of **$** was added."

	1.7 The application enters the grocery shopping mode screen and the QR Code Scanner closes.

     **2. Alternate Scenario: Alcoholic Beverage**

	2.1 The customer touches the "+" button on the grocery shopping mode screen.
	
	2.2 The application's QR Code Scanner opens from within the app.

	2.3 The customer holds an alcoholic beverage's QR code in front of the phone's camera.

	2.4 The application reads the product's ID and price from the scanned QR code.

	2.5 The application adds the product's ID and price to the customer's list of items in the customer's shopping cart in the application.

	2.6 The application calculates a special tax for the beverage and adds that tax to the specialTax field of the item in the cutomer's shopping cart in the application.

	2.7 The application prints a confirmation for three seconds that "Alcoholic bevarage **X** was added to the cart."

	2.8 The application enters the grocery shopping mode screen and the QR Code Scanner closes.

      **3. Alternate Scenario: Product already scanned at least once**

	3.1 The customer touches the "+" button on the grocery shopping mode screen.
	
	3.2 The application's QR Code Scanner opens from within the app.

	3.3 The customer holds a product's QR code in front of the phone's camera.

	3.4 The application reads the product's ID and price from the scanned QR code.

	3.5 The application adds one instance of the product's ID and price to the customer's list of items in the customer's shopping cart in the application.

	3.6 The application prints a confirmation for three seconds that "Product **X** was added to the cart. There are **#** product **X** now in the cart. "

	3.7 The application enters the grocery shopping mode screen and the QR Code Scanner closes.

###3. Add Coupon
   * **Requirements:** The Add Coupon use case must allow the customer to press the "+" button on the application, scan a coupon's QR code, and add the ID and discount to the list of coupons in the customer's shopping cart in the application.

   * **Pre-conditions:** The EZ Shop Application is in grocery shopping mode and the customer is holding a *coupon* with a QR code.

   * **Post-Conditions:** The coupon's QR code has been scanned, the coupon has been added the list of coupons in the customer's shopping cart, and the application returns to the grocery shopping mode screen.

   * **Scenarios:** 

      **1. Normal Scenario: Product with matching ID already scanned but coupon not yet scanned.**

	1.1 The customer touches the "+" button on the grocery shopping mode screen.

	1.2 The application's QR Code Scanner opens from within the app.

	1.3 The customer holds a coupon's QR code in front of the phone's camera.

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

	2.7 The application prints a confirmation message to the screen indicating: "A coupon was added for product **X** but product **X** has not yet been added to the cart" for three seconds. 

	2.8 The application enters the grocery shopping mode screen and the QR Code Scanner closes.

      **3. Exceptional Scenario: Coupon already scanned.**

	3.1 The customer touches the "+" button on the grocery shopping mode screen.

	3.2 The application's QR Code Scanner opens from within the app.

	3.3 The customer holds a coupon's QR code in front of the phone's camera.

	3.4 The application reads the product's ID and discount from the scanned QR code.

	3.5 The application determines that the product's ID and discount has already been added to the customer's list of coupons in the customer's shopping cart in the application.

	3.6 The application prints an error message to the screen indicating: "That coupon has already been scanned."

	3.7 The customer selects the "X" on the error message to acknowledge and close the error message.

	3.8 The application enters the grocery shopping mode screen and the QR Code Scanner closes.

###4. Remove Product
   * **Requirements:** The Remove Product use case must allow the customer to press the "-" button on the application, scan a products's QR code, and remove one instance of the item ID and price from the list of products the customer's shopping cart in the application.

   * **Pre-conditions:** The EZ Shop Application is in grocery shopping mode, the customer is holding a *product* with a QR code, and at least one instance of the product is listed in the customer's shopping cart list of items in the application.

   * **Post-Conditions:** The product's QR code has been scanned, the product has been removed from the list of items in the customer's shopping cart, and the application returns to the grocery shopping mode screen.

   * **Scenarios:** 

      **1. Normal Scenario: Product with matching ID previously scanned.**

	1.1 The customer touches the "-" button on the grocery shopping mode screen.

	1.2 The application's QR Code Scanner opens from within the app.

	1.3 The customer holds a product's QR code in front of the phone's camera.

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

	2.6 The application prints an error message to the screen indicating: "Product **X** not in your cart."

	2.7 The customer selects the "X" on the error message to acknowledge and close the error message.

	2.8 The application enters the grocery shopping mode screen and the QR Code Scanner closes.

###5. Generate Bill
   * **Requirements:** The Generate Bill use case must allow the customer to press the "Pay" button on the application and generate a customer QR Code that encodes the customer's information and all products and coupons in the shopping cart that can be scanned by the cashier and decoded on the POS System.

   * **Pre-conditions:** The EZ Shop Application is in grocery shopping mode and the customer has at least one product in their shopping cart in the application.

   * **Post-Conditions:** The EZ Shop app has produced and displayed a customer QR Code that encodes all customer shopping cart information,   the cashier has scanned the code and decoded the information on the POS System, and the POS System displays all of the information encoded in the QR Code.

   * **Scenarios:** 

      **1. Normal Scenario**

	1.1 The customer touches the "Pay" button on the grocery shopping mode screen.

	1.2 The application caclulates the total value of the cart by calculating the sum of all item prices, coupon discounts (applied to each item with a particular ID), special taxes, and regular taxes (if applicable).

	1.3 The application generates a customer QR Code that encodes the customer's first name, last name, zip code, e-mail address, the product list, the coupon list, and the total value in the customer's shopping cart.

	1.4 The customer hands the phone to the cashier.

	1.5 The cashier scans the QR Code displayed on the phone with the POS System scanner.

	1.6 The POS System decodes the QR Code and displays all of the customer's information, the list of products (to include special taxes), the list of coupons, and the total value of the cart on the POS System Display screen.

	1.7 The cashier hands the phone back to the customer.

	1.8 The customer closes the EZ Shop App by touching the "X" button.

      **2. Exceptional Scenario: Empty Shopping Cart**

	2.1 The customer touches the "Pay" button on the grocery shopping mode screen.

	2.2 The application attempts to generate the customer QR Code but determines the customer's shopping cart is empty.

	2.3 The application generates an error code: "Cannot pay because your shopping cart is empty. Scan a product to buy."

	2.4 The customer selects the "X" on the error message to acknowledge and close the error message.

	2.5 The application enters the grocery shopping mode screen and the QR Code Scanner closes.

###6. Pay
   * **Requirements:** The Pay use case must allow the cashier to verify the accuracy of the bill by pressing the "verify" button, the customer to select a payment method by choosing "Cash", "Credit" or "Debit" and communicating it their selection verbally to the cashier (who selects the correct button on the POS System), the customer to hand the payment medium (cash or card) to the cashier, the cashier to process the payment, and the POS System to e-mail the receipt to the customer.

   * **Pre-conditions:** The EZ Shop Application is in grocery shopping mode and the customer has at least one product in their shopping cart in the application.

   * **Post-Conditions:** The cashier has verified the contents of the cart match the bill, the customer has paid for the bill, and the POS System has e-mailed the receipt to the customer.

   * **Scenarios:** 

      **1. Normal Scenario: Credit Card and Contents Match**

	1.1 The cashier visually verifies that the contents in the cart match the bill displayed on the POS System.

	1.2 The cashier determines the contents match and touches the "Verify" button on the POS System.

	1.3 The POS System asks the cashier to select the payment method by displaying options of: "Cash" or "Credit".

	1.4 The cashier asks the customer how they want to pay.

	1.5 The customer responds with credit and the cashier selects the "Credit" button.

	1.6 The customer hands their credit card to the cashier.

	1.7 The cashier swipes the credit card.

	1.8 The cashier asks the customer to sign the signature pad that is connected to the POS System.

	1.9 The customer signs on the signature pad.

	1.10 The cashier visually verifies the signature and touches the "Accept" button on the POS System.

	1.11 The POS System e-mails a receipt to the customer by utilizing the data listed on the bill and the POS System returns to the scan screen (awaiting the next bill).

	1.12 The cashier hands the credit card to the customer.

      **2. Alternate Scenario: Cash and Contents Match**

	2.1 The cashier visually verifies that the contents in the cart match the bill displayed on the POS System.

	2.2 The cashier determines the contents match and touches the "Verify" button on the POS System.

	2.3 The POS System asks the cashier to select the payment method by displaying options of: "Cash" or "Credit".

	2.4 The cashier asks the customer how they want to pay.

	2.5 The customer responds with cash and the cashier selects the "Cash" button.

	2.6 The customer hands cash in the amount greater than or equal to the total cost of the bill to the cashier.

	2.7 The cashier accepts the cash from the customer.

	2.8 The cashier enters the amount of cash in the POS System by using the on-screen numberered key pad.

	2.9 The POS System calculates the change due, prints the change due amount on the screen of the POS System, and opens the cash drawer.

	2.10 The cashier puts the cash in the drawer and draws out the exact amount of change due.

	2.11 The cashier hands the cash to the customer and closes the cash drawer.

	2.12 The POS System e-mails a receipt to the customer by utilizing the data listed on the bill and the POS System returns to the scan screen (awaiting the next bill).

      **3. Exceptional Scenario: Contents Don't Match the Bill**

	2.1 The cashier visually verifies that the contents in the cart match the bill displayed on the POS System.

	2.2 The cashier determines the contents of the shopping cart don't match, touches the "Cancel" button on the POS System, and the POS System returns to the scan screen (awaiting the next bill) .

	2.3 The cashier informs the customer.

	2.4 The customer returns to the store to return unscanned items or to remove items from the list.

###7. Scan QR Code
   * **Requirements:** A customer or cashier presents a QR Code to device's scanner (a camera on a phone or a scanner on a POS System) and the scanner reads and then decodes the QR Code into the correct format.

   * **Pre-conditions:** The application or POS System has communicated the format of the code that it expects to the QR Code Scanner (Customer Card QR Code (no shopping cart), Customer Card QR Code (Shopping Cart included), Product QR Code, or Coupon QR Code.

   * **Post-Conditions:** The QR Code Scanner has read the code and decoded the code into the expcted format.

     **1. Normal Scenario: Incoming Code Matches Expectation**

	1.1 The customer or cashier opens the QR Code Scanner and communicates the expected format of the incoming code via the application or POS System.

	1.2 The scanner looks for a code to be presented to its sensor.

	1.3 The customer or cashier holds a QR code (of the correct format) in front of their device's sensor.

	1.4 The QR Code Scanner reads the QR Code and decodes the code into the expcted text format.

	1.5 The QR Code Scanner returns the textual formatted code back to the applicaiton or POS System.

     **2. Exceptional Scenario: Incoming Code Doesn't Match Expectation**

	2.1 The customer or cashier opens the QR Code Scanner and communicates the expected format of the incoming code via the application or POS System.

	2.2 The scanner looks for a code to be presented to its sensor.

	2.3 The customer or cashier holds a QR code (of the incorrect format) in front of their device's sensor.

	2.4 The QR Code Scanner reads the QR Code and determines it is not the correct format.

	2.5 The application produces an error message: "That is not a **QR CODE TYPE** QR code. Please scan a **QR CODE TYPE** QR code."

	2.6 The user selects the "X" on the error message to acknowledge and close the error message.

	2.7 The QR Code Scanner remains open awaiting a correctly formatted QR code.

	2.8 The customer or cahsier either scans a correctly formatted QR code or closes the application.

###8. Close App
   * **Requirements:** A customer wants to exit the application from one of many possible states.

   * **Pre-conditions:** The application is open.

   * **Post-Conditions:** The application is closed.

     **1. Normal Scenario: Shopping Mode Screen**

	1.1 The application is on the shopping mode screen.

	1.2 The user touches the "X" button.

	1.3 The application closes.

      **2. Alternate Scenario: QR Code Scanner Screen**

	2.1 The application is on the QR Code Scanner screen after the user presses the "+" or "-" button.

	2.2 The user touches the "X" button.

	2.3 The QR Code Scanner closes.
	
	2.4 The application returns to the shopping mode screen.

	2.5 The user touches the "X" button.

	2.6 The application closes.

      **3. Alternate Scenario: Bill QR Code Screen**

	3.1 The application is on the Bill QR Code screen.

	3.2 The user touches the "X" button.

	3.3 The application returns to the shopping mode screen.

	3.4 The user touches the "X" button.

	3.5 The application closes.

      **4. Exceptional Scenario: Launch QR Code Scanner Screen**

	4.1 The application is on the Launch QR Code Scanner screen.

	4.2 The user touches the "X" button.

	4.3 The application closes.





