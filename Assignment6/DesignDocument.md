# Design Document

**Author**: \<Kyle Sullivan (ksullivan40@gatech.edu)\>

## 1 Design Considerations

### 1.1 Assumptions

*Describe any assumption, background, or dependencies of the software, its use, the operational environment, or significant project issues.*

* It will be assumed that all customer data including shopping cart lists of coupons and products can fit in one QR Code.

### 1.2 Constraints

*Describe any constraints on the system that have a significant impact on the design of the system.*

* The system is constrained by the amount of data that can fit into a QR Code.

### 1.3 System Environment

*Describe the hardware and software that the system must operate in and interact with.*

## 2 Architectural Design

### 2.1 Component Diagram

The two major components in this system are the EZ Shop App and the POS System. There are three provided components: the QR Code Scanner, Payment Processing, and E-mail Processing. The EZ Shop App provides the interface between itself and the POS System in the form of a QR Code rendered on its screen that can be scanned by the POS System. The POS System uses the EX Shop App to read and then decode a QR code that has all the information necessary to display a bill on the POS System screen. Both the EZ Shop App and the POS System use the QR Code Scanner component. The QR Code Scanner provides the interface with both by decoding the QR Codes it scans into text format that can be transmitted to those components. 

![Component Diagram v 0.1](ComponentDiagram.png)

### 2.2 Deployment Diagram

The two major devices in the system are the customer's Android phone and the cashier's cash register. The QR Code Scanner Component is present on both pieces of hardware.

![Deployment Diagram v 0.1](DeploymentDiagram.png)

## 3 Low-Level Design

### 3.1 Class Diagram
 
#### 3.11 EZ Shop App

![EZ Shop App UML: Diagram v 0.1](EZShopUML.png)

#### 3.12 POS System


![POS Sys UML: Diagram v 0.1](POSSysUML.png)

### 3.2 Other Diagrams

*<u>Optionally</u>, you can decide to describe some dynamic aspects of your system using one or more behavioral diagrams, such as sequence and state diagrams.*

## 4 User Interface Design
*For GUI-based systems, this section should provide the specific format/layout of the user interface of the system (e.g., in the form of graphical mockups).*

