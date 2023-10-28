---
  layout: default.md
  title: "User Guide"
  pageNav: 3
---

# User Guide

<!-- * Table of Contents -->
<page-nav-print />
<div style="page-break-after: always;"></div>

## **Introduction**

**LetsGetHired** is a desktop application designed to assist you in efficiently **tracking and managing** your 
internship applications and their progress.

With LetsGetHired, you can benefit from:
- **Swift Viewing**: Swiftly view your internship applications and monitor their status.
- **Status Updates**: Stay updated on the progress of each application, from application sent to interview stages.
- **Organization**: Organize your internship applications according to your needs for better clarity.
- **Notes**: Add personalized notes to each internship application, ensuring you never forget key details.
- **Deadlines**: Set and track deadlines for your internship applications, so you never miss an opportunity.

In addition to these capabilities, we recognize the importance of **speed and efficiency**. Thus, **LetsGetHired** is
optimized for use via a Command Line Interface (CLI), but you still enjoy the advantages of a Graphical User Interface
(GUI). If you are a swift typist, **LetsGetHired** can streamline your internship tracking faster than many standard
GUI-based tools available.

### Purpose of this guide
This user guide is designed to help you get started with the LetsGetHired Intern Tracker, a powerful tool for managing 
your internship applications. Whether you are a tech-savvy student or a professional looking to land that dream internship, 
this guide is here to assist you every step of the way.

This app is perfect for you if you are a **Computer Science undergraduate student**

This user guide is primarily designed for Computer Science undergraduates currently engaged in the internship finding process. Whether you are seeking you first internship or have prior experience, this guide is tailored to cater to your specific needs.

### Prerequisite Knowledge

While this guide is accessible to a broad audience, we assume that you have a basic understanding of the internship-finding process. 
If you are new to this journey, don't worry! We've got you covered. Feel free to check out these [helpful resources](https://www.techinterviewhandbook.org/software-engineering-interview-guide/) 
or refer to our [glossary](#glossary) page for additional information to get you up to speed.

Our goal is to make your internship application experience as smooth and successful as possible, regardless of your level of experience. 
Let's embark on this journey together and make your internship goals a reality.

### Overview of Main Features

LetsGetHired allows you to **add** and **organise** your internship applications. **View** all applications at a glance, or narrow down your search with **filter** and **search** features. LetsGetHired also allows you to attach customized **notes**, to track any additional information.

___________________________________________

## **Navigating the User Guide**

TO BE ADDED

Follow [this link](https://nus-cs2103-ay2324s1.github.io/website/admin/usingThisWebsite.html#using-this-website)

--------------------------------------------------------------------------------------------------------------------
<div style="page-break-after: always;"></div>

## **Getting Started**

Welcome to the **LetsGetHired User Guide**! This guide will walk you through the basics of [**setting up**](#installation) and **[using the 
application](#tutorial-managing-your-internship-applications)** to manage your internship applications.

<box light header="#### **By the end of this section, you will learn:**">

* How to install **LetsGetHired**
* How to **add** your internship applications
* How to **edit** your internship applications
* How to **add notes** to your internship applications

</box>

<box type="tip" light>

If you are already an advanced user, feel free to jump to the [_Commands_ section of the **User Guide**](#commands)

</box>

### Installation
1. Ensure that you have **Java 11 or above** installed in your Computer
   <box type="info" light>
    <md>
    You can download it from [Java SE 11 Downloads](https://www.oracle.com/sg/java/technologies/javase/jdk11-archive-downloads.html) if you do not have it already.
    </md>
   </box>

1. Download the **latest** `letsgethired.jar` from [**our GitHub Releases**](https://github.com/AY2324S1-CS2103T-W17-2/tp/releases).

    ![Zip file download](images/downloadJar.jpg)

1. Locate the `letsgethired.jar` file
   * After downloading, find the `letsgethired.jar` file on your computer. It might be in your `Downloads` folder or wherever you chose to save it.

1. Choose Your **Home Folder**
   * Decide on a folder where you want all your **LetsGetHired** data to reside. This can be an existing folder or you can create a new one specifically for **LetsGetHired**.

1. Copying the File: 
   * Right-click on the `letsgethired.jar` file and select `Copy`.
   * Navigate to your chosen home folder.
   * Right-click on an empty space inside the folder and select `Paste`.

1. Verify the File Location:
   * Ensure that the `letsgethired.jar` file is now present in your chosen home folder.

<box type="tip" light>

**Bookmark** or **create a shortcut** to this folder for easy access in the future!

</box>

___

### Launching LetsGetHired
1. Open your **Command Terminal**.
    * On _Windows_: Press `Windows + R` keys simultaneously, type `cmd` and press `Enter`. 
      * Alternatively, press `Windows` key, type `Terminal` and press `Enter`.
    * On _macOS_: Press `Command + Space` to open Spotlight search, type `Terminal`, and press `Enter`.
1. `cd` into your chosen home folder, and type in the following command:
    ```shell
    java -jar letsgethired.jar
    ```
    <box type="tip" seamless>
    
    If you are unfamiliar with the commands in the Command Terminal, we suggest reading [this](https://www.digitalcitizen.life/command-prompt-how-use-basic-commands/).
    
    </box>

1. You should now see a graphical user interface (GUI) pop up on your screen (as shown in the picture below).

<box type="info" light>

Please note that the app comes preloaded with some **sample internship data**. <br>
This is to help you get a feel for how the app works and how your entries will appear.

</box>
<br>
   
![Ui](images/Ui.jpg)

| Components              | Description                                                   |
|-------------------------|---------------------------------------------------------------|
| Command Box             | You can type commands here and press `Enter` to execute them. |
| Result Display Box      | Displays the result of executing a command.                   |
| List View               | Displays a list of your internship applications.              |
| Intern Application Card | Displays the fields related to your internship application.   |
| Select View             | Displays more details related to your internship application. |

Now that you are familiar with the user interface of LetsGetHired, let's jump right into managing your 
internship applications in this [**Tutorial**](#tutorial-managing-your-first-internship-application).

--------------------------------------------------------------------------------------------------------------------

## Tutorial - Managing Your First Internship Application

In this tutorial, we will walk you through a typical workflow for using LetsGetHired to manage your internship applications. <br>

### Step 1: Adding Your Internship Application

**Context:** You are starting your internship application journey, and you want to add a new internship application.

Imagine that you have applied for a **Software Engineering Internship** at **Google** for the **Summer of 2024**.

To **add your internship application**, go to the _Command Box_ and type the following command:
```shell
add n/Google r/Software Engineering c/Summer 2024
```
This command specifies the company (_Google_), the role (_Software Engineering_), and the cycle (_Summer 2024_) for the internship that you have applied for.

After hitting `Enter`, your new internship application will appear in the _List View_ of **LetsGetHired**,
allowing you to keep track of your application details effortlessly.

**_PUT PICTURE HERE_**

### Step 2: Editing Your Internship Application

**Context:** You realize that you made a mistake in your internship application and need to correct it.

Imagine that you want to change the company name to "**Google Inc**" instead.

To **edit your internship application**, go to the _Command Box_ and type the `edit` command, 
followed by the index of the internship application to be edited (in this case, it's 1) and the corrected company name:
```
edit 1 n/Google Inc
```
After hitting `Enter`, your internship application will be updated with "_Google Inc_" as the new company name.

**_PUT PICTURE HERE_**

<box type="tip" seamless>

**Always ensure your application details are accurate to avoid confusion in the future.**

</box>

### Step 3: Updating Status

**Context:** You have submitted your application, and you want to update the application status.

Imagine that your internship application to **Google Inc** was a success, and you received an **offer**.

To **update the status of your internship application**, use the `edit` command,
followed by the index of the internship application to be edited (in this case, it's 1) and the updated status:
```
edit 1 s/Offered
```
After hitting `Enter`, your internship application status will be updated to "_Offered_".

**_PUT PICTURE HERE_**

<box type="tip" seamless>

**Keeping your application status up-to-date helps you to stay organized and informed.**

</box>

### Step 4: Adding Notes

**Context:** You want to keep track of additional information about the internship, such as job requirements.

To **add notes to your internship application**, use the `note` command, followed by the index of the internship application
(in this case, it's 1) and your additional notes:
```
note i/1 Additional information about job requirements and expectations...
```
After hitting `Enter`, you have successfully included supplementary information which you need to remember about the 
internship, making it a valuable resource when preparing for interviews or follow-ups.

### Step 5: Start Using LetsGetHired

**Congratulations**! You have successfully managed your first internship application using **LetsGetHired**. 
These steps demonstrate the core functions of the application, helping you stay organized and informed throughout your internship application journey.

<box type="info" seamless>

For other commands, please refer to the [_Commands_ section of the **User Guide**](#commands) below.

</box>

--------------------------------------------------------------------------------------------------------------------

## **Commands**

This section explains the detailed list of the commands (and its usages) which are available for you to use.

<box type="info" light>

**Notes about the command format:**<br>

* Words in `UPPER_CASE` are **parameters** which you will type in.<br>
    ```
    add n/COMPANY_NAME r/ROLE c/CYCLE
    ``` 
    For example, you can replace `COMPANY_NAME`, `ROLE` and `CYCLE` as follows:
    ```
    add n/Google r/Software Engineering c/Summer 2024
    ```

* Words in `[square brackets]` are **optional parameters**.<br>
    ```
    add n/COMPANY_NAME r/ROLE c/CYCLE [s/STATUS] [d/DEADLINE]
    ```
  For example, you can use the above command as:
    ```
    add n/Google r/Software Engineering c/Summer 2024
    ```
  OR
    ```
    add n/Google r/Software Engineering c/Summer 2024 s/Offered
    ```
  OR
    ```
    add n/Google r/Software Engineering c/Summer 2024 d/25 Oct 2023
    ```
  OR
    ```
    add n/Google r/Software Engineering c/Summer 2024 s/Offered d/25 Oct 2023
    ```

* You can type parameters in **any order**.<br>
  e.g. if the command specifies `c/CYCLE r/ROLE`, typing `r/ROLE c/CYCLE` is also acceptable.

* **Irrelevant parameters** for commands that do not take in parameters (such as `help` and `list`) **will be ignored**.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</box>

<box type="warning">

If you are using a **PDF version** of this User Guide, **be careful when copying and pasting commands** that span multiple 
lines as space characters surrounding line-breaks may be omitted when copied over to the application.

</box>

__________________________________________________________________________________________________________________

### Viewing Help: `help`

The `help` command allows you to get quick access to this **User Guide**.

**Format**: 
```
help
```

**Parameters**: None

**Sample Execution**:
```
help
```
**_UPDATE PICTURE HERE_**
![help command example](images/helpExample.jpg)

![help message](images/helpMessage.png)
Some explanation here on what the command does.

__________________________________________________________________________________________________________________

### Listing Your Internship Applications: `list`

The `list` command displays all of your existing internship applications.

**Format**:
```
list
```

**Parameters**: None

**Sample Execution**:
```
list
```
**_UPDATE PICTURE HERE_**
![list command example](images/listExample.jpg)
Some explanation here on what the command does.

__________________________________________________________________________________________________________________

### Adding a New Internship Application: `add`

The `add` command allows you to add a new internship application.

**Format**:
```
add n/COMPANY_NAME r/ROLE c/CYCLE [s/STATUS] [d/DEADLINE]
```

**Parameters**:
* `n/COMPANY_NAME`: Company name, such as `Jane Street` or `Google`.
* `r/ROLE`: Internship role, such as `Software Engineering Intern`.
* `c/CYCLE`: Internship cycle, such as `Summer` or `Winter`.
* `s/STATUS`: Status of your internship application.
  <box type="info" seamless>

  You can only select `STATUS` from these options:
    * `Pending`
    * `Interview`
    * `Assessment`
    * `Offered`
    * `Rejected`

    </box>
* `d/DEADLINE`: Deadline for your internship application.
  <box type="info" seamless>

  `DEADLINE` must be a valid date in the `dd MMM yyyy` format, for example, `25 Oct 2023`.
  </box>

**Sample Execution**:
```
add n/Google r/Software Engineering c/Summer 2024 s/Offered d/25 Oct 2023
```
**_UPDATE PICTURE HERE_**

![add command example 1](images/addExample1.jpg)

![add command example 2](images/addExample2.jpg)

Some explanation here on what the command does.

__________________________________________________________________________________________________________________

### Editing an internship application : `edit`

The `edit` command allows you to edit your internship application.

**Format**: 
```
edit INDEX [n/COMPANY_NAME] [r/ROLE] [c/CYCLE] [s/STATUS] [d/DEADLINE]
```

**Parameters**:
<box type="important" seamless>

At least **one** of the optional parameters must be provided.
</box>

* `INDEX`: Index of the internship application you want to view.
  <box type="info" seamless>

  `INDEX` must be a positive integer, such as, `1`, `2`, `3`, etc.
  </box>

* `n/COMPANY_NAME`: The updated company name.
* `r/ROLE`: The updated internship role.
* `c/CYCLE`: The updated internship cycle.
* `s/STATUS`: The updated status of the internship application.
* `d/DEALINE`: The updated deadline of the internship application.

**Sample Execution**:

**_UPDATE PICTURE HERE_**

![edit command example](images/editExample.jpg)

Some explanation here.

__________________________________________________________________________________________________________________

### Adding a note to a specific internship : `note`

The `note` command allows you to add a note to your internship application.

**Format**: 
```
note INDEX [i/NOTE]
```

**Parameters**:
* `INDEX`: Index of the internship application you want to view.
  <box type="info" seamless>

  `INDEX` must be a positive integer, such as, `1`, `2`, `3`, etc.
  </box>
* `i/NOTE`: Note to be added to the internship application

**Sample Execution**:

**_UPDATE PICTURE HERE_**

![note command example](images/noteExample.jpg)
Some Explanation here

__________________________________________________________________________________________________________________

### Deleting an internship application : `delete`

The `delete` command allows you to delete an internship application.

**Format**: 
```
delete INDEX
```

**Parameters**:
* `INDEX`: Index of the internship application you want to view.
  <box type="info" seamless>

  `INDEX` must be a positive integer, such as, `1`, `2`, `3`, etc.
  </box>

**Sample Execution**:

**_UPDATE PICTURE HERE_**

![delete command example](images/deleteExample.jpg)
Some explanation here

__________________________________________________________________________________________________________________

### Viewing Your Internship Application: `view`

The `view` command allows you to view a specific internship application and its details on the _Select View_.

**Format**: 
```
view INDEX
```

**Parameters**:
* `INDEX`: Index of the internship application you want to view.
  <box type="info" seamless>

  `INDEX` must be a positive integer, such as, `1`, `2`, `3`, etc.
  </box>

**Sample Execution**:
```
view 1
```
![note command example](images/viewExample.jpg)
Some explanation here on what the command does.

__________________________________________________________________________________________________________________

### Searching Your Internship Application: `find`

This command finds internship applications whose fields contain the corresponding given keywords (any match with any of the field and keyword pairs is displayed).

**Format**: 
```
find [n/COMPANY_NAME] [r/ROLE] [c/CYCLE] [s/STATUS] [d/DEADLINE] [i/NOTE]
```

**Parameters**:
<box type="important" seamless>

At least **one** of the optional parameters must be provided.
</box>

* `n/COMPANY_NAME`: The company name you want to search for.
* `r/ROLE`: The internship role you want to search for.
* `c/CYCLE`: The internship cycle you want to search for.
* `s/STATUS`: The status of the internship application you want to search for.
* `d/DEALINE`: The deadline of the internship application you want to search for.
* `i/NOTE`: The note of the internship application you want to search for.

<box type="info" light>

**Note about the `find` command**
* The `find` command will display any internship applications that contains the parameter which you have provided.
  * Examples:
    * `find n/Google` displays your internship applications whose company name contains `Google`
    * `find n/Apl` displays your internship applications whose company name contains `Apl`
        * These include `Apple`, `apple` and `Alphabet Limited`.
    * `find c/Summer` displays all internship applications whose cycle contains `Summer`
* If you provide multiple parameters, the `find` command will display all internship applications which contain at least one of the parameters provided.
  * For example:
  * `find n/Google c/Summer s/Pending` displays all internship applications which meets at least one of the following conditions:
      * Company name contains `Google`
      * Cycle contains `Summer`
      * Status is `Pending`

</box>

**Sample Execution**:

**_UPDATE PICTURE HERE**_

![find command example](images/findExample.jpg)
Some explanation here.

__________________________________________________________________________________________________________________

### Clearing all entries : `clear`

The `clear` command allows you to clear all your internship applications.

<box type="tip" seamless>

Use the `clear` command to clear the sample data in **LetsGetHired** once you are familiar with its usages.

</box>

**Format**: 
```
clear
```

**Parameters**: None

__________________________________________________________________________________________________________________

### Exiting the program : `exit`

The `exit` command allows you to quickly exit **LetsGetHired**.

**Format**: `exit`

**Parameters**: None

__________________________________________________________________________________________________________________

### Saving the data

LetsGetHired data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

LetsGetHired data are saved automatically as a JSON file `[JAR file location]/data/letsgethired.json`. 
If you are an advanced user, you are welcome to update data directly by editing that data file.

<box type="warning" seamless>

**Caution:**
If your changes to the data file makes its format invalid, LetsGetHired will discard all data and start with an empty 
data file at the next run. Hence, it is recommended to take a backup of the file before editing it.

</box>

--------------------------------------------------------------------------------------------------------------------

## **FAQ**

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous LetsGetHired home folder.

--------------------------------------------------------------------------------------------------------------------

## **Known issues**

* **When using multiple screens**, if you move the application to a secondary screen, and later switch to using only the primary screen, the GUI will open off-screen. The remedy is to delete the `preferences.json` file created by the application before running the application again.

--------------------------------------------------------------------------------------------------------------------

## **Command summary**

| Action     | Format, Examples                                                                                                                                           | Examples                                                                    |
|------------|------------------------------------------------------------------------------------------------------------------------------------------------------------|:----------------------------------------------------------------------------|
| **Add**    | `add n/COMPANY_NAME r/ROLE c/CYCLE [s/STATUS] [d/DEADLINE]`                                                                                                | `add n/Jane street r/Full Stack Developer c/Summer s/Pending d/25 Oct 2023` |
| **View**   | `view INDEX`                                                                                                                                               | `view 3`                                                                    |
| **Edit**   | `edit INDEX [n/COMPANY_NAME] [r/ROLE] [c/CYCLE] [s/STATUS] [d/DEADLINE]`                                                                                   | `edit 2 s/Applied`                                                          |
| **Note**   | `note INDEX`                                                                                                                                               | `note 5 i/Jane Street is the leading market maker in the APAC region`       |
| **Delete** | `delete INDEX`                                                                                                                                             | `delete 1`                                                                  |
| **List**   | `list`                                                                                                                                                     | `list`                                                                      |
| **Find**   | `find [n/SEARCH_STRING_COMPANY] [r/SEARCH_STRING_ROLE] [c/SEARCH_STRING_CYCLE] [s/SEARCH_STRING_STATUS] [d/SEARCH_STRING_DEADLINE] [i/SEARCH_STRING_NOTE]` | `find n/Google c/Summer s/Pending`                                          |
| **Help**   | `help`                                                                                                                                                     | `help`                                                                      |
| **Clear**  | `clear`                                                                                                                                                    | `clear`                                                                     |
| **Exit**   | `exit`                                                                                                                                                     | `exit`                                                                      |

## **Glossary**

* **Mainstream OS**: Windows, Linux, Unix, OS-X
* **Internship Entry:** A record or entry in the system that represents information about an internship opportunity. It includes details such as company name, application status, deadlines, and notes.
* **Application Status:** The current stage or status of an internship application.
* **Sample Data:** Pre-populated data used for demonstration purposes, enabling new users to see how the application functions with realistic examples.
