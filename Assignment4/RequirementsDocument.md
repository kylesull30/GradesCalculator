# **Requirements Document -- Kyle Sullivan (ksullivan40@gatech.edu)**

##1 User Requirements

###1.1 User Characteristics

* User technical expertise is **diverse** and ranges from no technical expertise to technical proficiency. 
  * To accommodate the **lower end** of the spectrum the system should be usable by those users with no technical expertise.
  * It will be assumed that a user with no technical expertise can operate a program from the **Command Line** on either Windows, Linux, or 	Mac.
  * The user manual must be written in a **very detailed, step-by-step** manner to account for this variation in experience level.
  * Error messages will be **user-friendly** non-technical in nature, and provide hints to avoid the error.

* All users are enrolled in a university.
  * It will be assumed that all users have a minimum of a High School education.
  * It will be assumed that all users can **read and write basic English**.

* The estimated number of users is six classes of approximately 45 students per semester (total of **270 users** per semester).
  * It will be assumed that each semester is 3-4 months in length.
  * It will be assumed that the first iteration of the tool will be used by the students for the entire semester. 
  * It will be assumed that subsequent updates to the system will occur on a **per semester basis**.

* It is not known what platforms will be used by the users.
  * It will be assumed that the users will have access to a laptop or desktop system capable of running on **Windows, Linux, or Mac**.
  * It will be assumed the user will have access to an operating system and hardware that was produced **within the past five years**.
  * It will be assumed the user will have access to system with at least **1 GB of RAM** and a **1 Ghz** processor.

###1.2 System's functionality

The system must provide a means of processing an essay to determine the *average number of words per sentence* in that essay. In addition, the system must provide *flexibility* to the user in order to define rules that refine the program's analysis of the essay. It must be *simple* enough to allow users of all experience levels to learn and then operate the system with relative ease. Due to wide variability in the platforms used by the students, the system should be developed in a *platform-independent* manner that will allow it to be used easily with multiple common modern operating systems.

1. The system must provide a means of processing an essay to determine the **average number of words per sentence** in that essay.
  * The system must receive the **input** essay as a **raw text file** as defined by the user.
  * The system must **output** the **average sentence length to the user**.

2. The system must provide **flexibility** to the user to **define rules** impacting the program's analysis of the document.
  * The system must allow the user to specify **how sentences are separated**.
  * The system must allow the user to specify a **lower limit for word length**.
  * There is **no maximum or minimum limit** to the size of the essays to be analyzed.

3. The system must be **platform-independent**.

4. The system must be simple enough to allow users of all experience levels to **learn and operate** the system with relative **ease**. 
  * To minimize complexity, the system must be executable from the **Command Line on multiple platforms**.
  * The system will handle common error conditions by producing **friendly messages in plain English**.
  * The documentation for the tool will include **installation** of the system on the three most common operating systems.
  * The documentation for the tool will include **step-by-step instructions for using** the tool and all of its user-defined properties.

###1.3 User Interfaces

* The system must be executable from the Command line using the "java" command.

* The user will only provide **input** to the system on the **initial call** and will not have the option to provide more input to the system after that call.

* If the user fails to specify an optional user-defined property then the user will have to wait for the program to terminate and then make a new call to the program from the command line.

* The system will only provide two types of output:
  1. Average sentence length computation.
  2. User-friendly error messages with detailed descriptions of the problems and potential solutions to those problems.

##2 System Requirements

###2.1 Functional Requirements

1.1 The input file will be provided by the user as raw text file with **ASCII encoding**.

1.2 The system will only interact with the user during the **initial execution** and will not prompt the user for more input following the initial execution.

1.3 The user will execute the program by calling the **program name** from the command line on their system, followed by the **complete path** to the raw text file (without quotes), and then followed by any **optional flags**.

1.4 The input file will contain **no header or footer text** and the entire file and every character in the file must be processed.

1.5 The average sentence length must be calculated by adding all sentence lengths (**ignoring those below the minimum threshold**) and dividing by the total number of sufficient length sentences. It is critical to not include those sentences below the threshold in the calculation.

1.6 Every error message in the system must communicate to the user what portion of the input caused the error, why the input caused the error, and at least **one potential solution** to the error.

1.7 The system must handle and produce error messages for every user-defined argument including: file path, delimiters, and minimum word length. 

1.8 Error messages must be produced when a file can't be found, when an upper case letter, lower case letter, or number is used as a delimiter, and when the the minimum word length argument is outside of the 1-200 range. Illegal arugment input must also be addressed for all user input.

1.9 The output of the system to the command line must be the average length of the sentences in the essay as defined by the user (or default values if not defined) rounded down to the nearest integer and printed in the format: **"Average Sentence Length: X"** in the Command Line.

1.10 The system must handle **empty files** and files that have **zero sentences above the threshold** appropriately by communicating to the user that there are zero sentences that meet the threshold and stating the reason for that result (empty file or sentence length).

1.11 The system must exit, without input from the user, after printing the average sentence length or error message to the screen.

2.1 The user specifies sentence delimiters with the flag **-d** followed by the desired delimiters encapsulated in quotes and separated with commas such as: "., !, ?"

2.2 The **default delimiters** to be used in the case when no user-defined delimiters are presented are: ".", "?", "!", "," , ";"

2.3 The user specifies the lower word length limit with the flag **-l** followed by a whole number between 1 and 200.

2.4 The **default** lower word length limit will be set to **3** in the case that the user does not define a lower word length limit.

###2.2 Non-Functional Requirements

2.5 The system must provide the user **flexibility** to modify evaluation criteria.

2.6 The system must be **scalable** enough to handle a spectrum of file sizes from large files to empty files.

3.1 The system must be **platform-independent**. To accomplish this the system will be developed in Core Java 1.6 with no non-standard libraries and the system must compile with javac and not use any optional compilation modifiers.

4.1 The system must be **simple** and allow novice users to **quickly learn** the system (it must be executable from the Command Line and it must contain a Main() method).

4.2 The system must allow novice users to be **comfortable** with the system and produce **user-friendly error messages** to the users by providing a detailed explanation all errors in plain English and then provide specific solutions to resolve those errors.

4.3 The documentation for the system must be **comprehensive** enough that a novice user can read the documentation and then successfully use the system. To accomplish this the documentation must be written in English and provide step-by-step instructions for **installation, use, and troubleshooting** on standard Windows, Linux, and Mac Operating Systems from the past five years.
