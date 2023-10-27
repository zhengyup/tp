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
(GUI). If you're a swift typist, **LetsGetHired** can streamline your internship tracking faster than many standard
GUI-based tools available.

### Purpose of this guide
This user guide is designed to help you get started with the LetsGetHired Intern Tracker, a powerful tool for managing your internship applications. Whether you're a tech-savvy student or a professional looking to land that dream internship, this guide is here to assist you every step of the way.

This app is perfect for you if you are a **Computer Science undergraduate student**

This user guide is primarily designed for Computer Science undergraduates currently engaged in the internship finding process. Whether you are seeking you first internship or have prior experience, this guide is tailored to cater to your specific needs.

### Prerequisite Knowledge

While this guide is accessible to a broad audience, we assume that you have a basic understanding of the internship-finding process. If you're new to this journey, don't worry! We've got you covered. Feel free to check out these [helpful resources](https://www.techinterviewhandbook.org/software-engineering-interview-guide/) or refer to our [glossary](#glossary) page for additional information to get you up to speed.

Our goal is to make your internship application experience as smooth and successful as possible, regardless of your level of experience. Let's embark on this journey together and make your internship goals a reality.

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
<md>
1. How to install **LetsGetHired**<br> 
2. How to **add** your internship applications<br> 
3. How to **edit** your internship applications<br> 
4. How to **add notes** to your internship applications<br> 
</md>
</box>

<box type="tip">
<md>If you are already an advanced user, feel free to jump to the [_Commands_ section of the **User Guide**](#commands)</md>
</box>

### Installation
1. Ensure that you have **Java 11 or above** installed in your Computer
   <box type="info">
    You can download it from <md>[Java SE 11 Downloads](https://www.oracle.com/sg/java/technologies/javase/jdk11-archive-downloads.html)</md> if you do not have it already.
   </box>

1. Download the **latest** `letsgethired.jar` from [**our GitHub Releases**](https://github.com/AY2324S1-CS2103T-W17-2/tp/releases).

    ![Zip file download](images/downloadJar.jpg)

1. Locate the `letsgethired.jar` file
   * After downloading, find the `letsgethired.jar` file on your computer. It might be in your 'Downloads' folder or wherever you chose to save it.

1. Choose Your **Home Folder**
   * Decide on a folder where you want all your **LetsGetHired** data to reside. This can be an existing folder or you can create a new one specifically for **LetsGetHired**.

1. Copying the File: 
   * Right-click on the `letsgethired.jar` file and select 'Copy'.
   * Navigate to your chosen home folder.
   * Right-click on an empty space inside the folder and select 'Paste'.

1. Verify the File Location:
   * Ensure that the `letsgethired.jar` file is now present in your chosen home folder.

<box type="tip">
<md>**Bookmark** or **create a shortcut** to this folder for easy access in the future!</md>
</box>

___

### Launching LetsGetHired for the First Time
1. Open your command terminal, `cd` into your chosen home folder, and type in the following command:
```shell
java -jar letsgethired.jar
```  
You should see a graphical user interface (GUI) pop up on your screen (as shown in the picture below).

<box type="info">
<md>
Please note that the app comes preloaded with some **sample internship data**. <br>
This is to help you get a feel for how the app works and how your entries will appear.
</md>
</box>
<br>
   
![Ui](images/Ui.jpg)

1. Here's what you'll see when you start the app for the first time! We've added some intern applications already. Enter your commands in the **Command Box** on the top of the app. On the bottom left you'll see a **List View** of all your applications ordered by the date you added them. On the right, the **Select View** shows you a more in depth view of your selected application.

1. Type the command in the Command Box and press `Enter` to execute it. e.g. typing **`help`** and pressing `Enter` will open the help window. Here are some example commands you can try:

    * `list` : Lists all internship applications.

    * `add n/Google c/Summer r/Full Stack Developer ` : Adds a new internship application entry to the internship list.

    * `delete 1` : Deletes the first internship application entry shown in the current list.

    * `edit 1 s/Accepted` : Updates the status of the first internship application entry shown in the current list as `Accepted`.

1. Refer to the [Features](#features) below for details of each command.
1. Follow the [Tutorial](#tutorial---managing-your-internship-applications) below to use LetsGetHired for your first internship application.

--------------------------------------------------------------------------------------------------------------------

## Tutorial - Managing Your Internship Applications

In this tutorial, we'll walk you through a typical workflow for using LetsGetHired to manage your internship applications. Let's imagine that you want to add an entry for a software engineering internship at Google for the summer of 2024.

### Step 1: Adding Your Internship

**Context:** You're starting your internship application journey, and you want to add a new entry.

To add your internship, go to the input box and type the following command:
   ```
   add n/Google r/Full Stack Developer c/Summer 2024
   ```
This command specifies the company (_Google_), the role (_Full Stack Developer_), and the cycle (_Summer 2024_) for the internship that you have applied for.

After hitting `Enter`, your new internship entry will appear in the bottom left panel (List View) of the application, allowing you to keep track of your application details effortlessly.

### Step 2: Editing Company Name

**Context:** You realize that you made a mistake in the company name and need to correct it.

To edit the company name, use the `edit` command, followed by the entry number (in this case, it's 1) and the corrected company name:
   ```
   edit 1 n/Google Inc
   ```
   This command updates the company name to "Google Inc."

   Always ensure your application details are accurate to avoid confusion in the future.

### Step 3: Updating Application Status

**Context:** You've submitted your application, and you want to update the application status.

To update the status of your internship application, use the `edit` command. For example, to mark your application as "Accepted," enter:
   ```
   edit 1 s/Accepted
   ```
   This command changes the status of the first internship application to "Accepted."

   Keeping your application status up-to-date helps you to stay organized and informed.

### Step 4: Adding Notes

**Context:** You want to keep track of additional information about the internship, such as job requirements.

To add notes to your internship entry, use the `note` command, specifying the entry number (in this case, it's 1) and providing the relevant details:
   ```
   note i/1 Additional information about job requirements and expectations...
   ```
   This command allows you to include any supplementary information you need to remember about the internship, making it a valuable resource when preparing for interviews or follow-ups.

### Step 5: Start Using LetsGetHired

**Congratulations**! You've successfully managed your internship application using LetsGetHired. These steps demonstrate the core functions of the application, helping you stay organized and informed throughout your internship application journey.

--------------------------------------------------------------------------------------------------------------------

## **Commands**

This section explains the detailed list of the commands (and its usages) which are available for you to use.

<box type="info" seamless>

**Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/COMPANY_NAME r/ROLE c/CYCLE`, `COMPANY_NAME`, `ROLE` and `CYCLE` are parameters which can be used as `add n/Google r/Full Stack Developer c/Summer 2024`.

* Items in square brackets are optional.<br>
  e.g. `c/CYCLE [s/STATUS]` can be used as `c/Summer s/Accepted` or as `c/Summer`.

* Parameters can be in any order.<br>
  e.g. if the command specifies `c/CYCLE r/ROLE`, `r/ROLE c/CYCLE` is also acceptable.

* Irrelevant parameters for commands that do not take in parameters (such as `help` and `list`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

* If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines as space characters surrounding line-breaks may be omitted when copied over to the application.
  </box>

### Viewing help : `help`

Shows a message explaining how to access this User Guide.

![help message](images/helpMessage.png)

![help command example](images/helpExample.jpg)

**Format**: `help`

### Adding internship entry : `add`

Adds an internship application entry to the internship list.

**Format**: `add n/COMPANY_NAME r/ROLE c/CYCLE [s/STATUS] [d/DEADLINE]`

**Parameters**:
* `n/COMPANY_NAME`: Company name, such as `Jane Street` or `Google`.
* `r/ROLE`: Internship role, such as `Software Engineering Intern`.
* `c/CYCLE`: Internship cycle, such as `Summer` or `Winter`.
* `s/STATUS`: Status of your internship application.
  * You can only select `STATUS` from these options: `Pending`, `Accepted`, `Rejected`.
* `d/DEADLINE`: Deadline for the next part of your internship application.
  * `DEADLINE` must be a valid date in the `dd MMM yyyy` format, for example, `25 Oct 2023`.

**Examples**:
* `add n/Jane street r/Full Stack Developer c/Summer s/Pending d/25 Oct 2023`<br>
* `add n/OCBC r/Software Engineering Intern c/Winter s/Rejected`<br>
* `add n/Apple r/Data Analyst c/Summer s/Accepted`<br>

**Expected Output**:
![add command example 1](images/addExample1.jpg)

![add command example 2](images/addExample2.jpg)

### Listing all internships : `list`

Shows a list of all the existing internship application entries.

**Format**: `list`

**Expected Output**:
![list command example](images/listExample.jpg)

### Viewing a specific internship : `view`

Shows a specific internship application entry and its details.

**Format**: `view INDEX`

**Parameters**:
* `INDEX`: Index of internship application entry to be displayed.
    * `INDEX` must be a positive integer, such as, `1`, `2`, `3`, etc.

**Examples**:
* `view 1` shows the first internship application entry (in the list) and its details

**Expected Output**:
![note command example](images/viewExample.jpg)

### Searching an internship application : `find`

Finds internship applications whose fields contain the corresponding given keywords (any match with any of the field and keyword pairs is displayed).

**Format**: `find [n/SEARCH_STRING_COMPANY] [r/SEARCH_STRING_ROLE] [c/SEARCH_STRING_CYCLE] [s/SEARCH_STRING_STATUS] [d/SEARCH_STRING_DEADLINE][i/SEARCH_STRING_NOTE]`

**Parameters**:
* At least one of the optional fields must be provided.

**Examples**:
* `find n/Google` displays all internship applications whose company name contains `Google`
* `find n/Apl` displays all internship applications whose company name contains `Apl`
  * These include `Apple`, `apple` and `Alphabet Limited`.
* `find c/Summer` displays all internship applications whose cycle contains `Summer`
* `find n/Google c/Summer s/Pending` displays all internship applications which meets at least one of the following conditions:
  * Company name contains `Google`
  * Cycle contains `Summer`
  * Status is `Pending`

**Expected Output**:
![find command example](images/findExample.jpg)

### Editing an internship application : `edit`

Edits an internship application in the internship application list.

**Format**: `edit INDEX [n/COMPANY_NAME] [r/ROLE] [c/CYCLE] [s/STATUS] [d/DEADLINE]`

**Parameters**:
* `INDEX`: Index of internship application entry to be edited
    * `INDEX` must be a positive integer, such as, `1`, `2`, `3`, etc.
* At least one of the optional fields must be provided.
    * Existing values will be updated to the input values.

**Examples**:
* `edit 1 s/Applied` edits the status of the first internship application entry (in the list) as `Applied`

**Expected Output**:
![edit command example](images/editExample.jpg)

### Adding a note to a specific internship : `note`

Adds the specified note to an internship application.

**Format**: `note INDEX [i/NOTE]`

**Parameters**:
* `INDEX`: Index of internship application entry to be updated
    * `INDEX` must be a positive integer, such as, `1`, `2`, `3`, etc.
* `i/NOTE`: Note to be added to the internship application

**Examples**:
* `note 1 i/Jane Street is the leading market maker in the APAC region` adds a note to first internship application entry (in the list).

**Expected Output**:
![note command example](images/noteExample.jpg)

### Deleting an internship application entry : `delete`

Deletes the specified internship application entry from the internship list.

**Format**: `delete INDEX`

**Parameters**:
* `INDEX`: Index of internship application entry to be deleted.
    * `INDEX` must be a positive integer, such as, `1`, `2`, `3`, etc.

Examples:
* `delete 1` deletes the first internship application in the list.

**Expected Output**: 
![delete command example](images/deleteExample.jpg)

### Clearing all entries : `clear`

Clears all entries from the internship application list.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

LetsGetHired data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

LetsGetHired data are saved automatically as a JSON file `[JAR file location]/data/letsgethired.json`. Advanced users are welcome to update data directly by editing that data file.

<box type="warning" seamless>

**Caution:**
If your changes to the data file makes its format invalid, LetsGetHired will discard all data and start with an empty data file at the next run.  Hence, it is recommended to take a backup of the file before editing it.

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
