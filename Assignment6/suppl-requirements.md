# Supplementary Requirements

**Author**: \<Kyle Sullivan (ksullivan40@gatech.edu)\>

## Nonfunctional Requirements

* The user interface should be easily opertaed by a novice android user and contain as few buttons as possible to still accomplish the funcitonal requirements.
* No customer-related personal or financial data will be saved on the android device.
* No financial data(credit card numbers) will be transfered outside of the payment processing system.
* The QR Code Scanner should operate in such a way that there is minimal user interaction (scanner should sense and capture automatically when it views a complete QR Code)
* The operating systems for both the POS System and EZ Shop App will be linux and Android, respectively. It can be assumed both have the latest security patches and were produced in the past two years.
* The EZ Shop App should be built in such a way that speed is less important than usability. It will be assumed that cusomers are only buying small lists of items.
* It will be assumed that the Android phone has an integrated camera that can be used to scan QR Codes.
* It will be assumed that the POS System has an attached QR Code scanner and associated drivers.
* The customer should have minimal interaction with the payment system and provide only the payment method and the bill QR Code to the system.
* The Point of Sale System should be developed for a novice computer user and provide limited functionality to the cashier.
* When error conditions are met during QR Code Scanning either the POS System or the EZ Shop App should produce suer-friendly error messages describing why the scan failed.
* The customer should be able to exit the application at anytime regardless of the application's state.
