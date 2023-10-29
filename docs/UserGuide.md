---
  layout: default.md
  title: "User Guide"
  pageNav: 3
---

# **User Guide**

<page-nav-print />

<div style="page-break-after: always;"></div>

## **Introduction**

_LetsGetHired_ is a **desktop application** designed to assist you in efficiently **tracking and managing your 
internship applications and their progress**.

With _LetsGetHired_, you can benefit from:
- **Swift Viewing**: Swiftly view your internship applications and monitor their status.
- **Status Updates**: Stay updated on the progress of each application, from application sent to interview stages.
- **Organization**: Organize your internship applications according to your needs for better clarity.
- **Notes**: Add personalized notes to each internship application, ensuring you never forget key details.
- **Deadlines**: Set and track deadlines for your internship applications, so you never miss an opportunity.

In addition to these capabilities, we recognize the importance of **speed and efficiency**. Thus, _LetsGetHired_ is
**optimized for use via a Command Line Interface (CLI)**, but you still enjoy the advantages of a Graphical User Interface
(GUI). If you are a swift typist, _LetsGetHired_ can streamline your internship tracking **faster than many standard
GUI-based tools available.**

### Purpose
This User Guide is designed to help you get started with _LetsGetHired_, a powerful tool for managing your internship applications. 
Whether you are a tech-savvy student or a professional looking to land that dream internship, this guide is here to assist you every step of the way.

**_LetsGetHired_ is perfect for you if you are a Computer Science undergraduate student**

This user guide is primarily designed for Computer Science undergraduates currently engaged in the internship finding process. 
Whether you are seeking you first internship or have prior experience, this guide is tailored to cater to your specific needs.

### Prerequisite Knowledge

While this guide is accessible to a broad audience, we assume that you have a basic understanding of the internship-finding process. 
If you are new to this journey, don't worry! We've got you covered. Feel free to check out these [helpful resources](https://www.techinterviewhandbook.org/software-engineering-interview-guide/) or refer to the [_Glossary_ section of the **User Guide**](#glossary) for additional information to get you up to speed.

Our goal is to make your internship application experience as smooth and successful as possible, regardless of your level of experience. 
Let's embark on this journey together and make your internship goals a reality.

__________________________________________________________________________________________________________________

<div style="page-break-after: always;"></div>

## **Navigating the User Guide**

This section will explain the different elements used in this User Guide and what they mean.

### Code

`A code in the User Guide will be displayed in this format.`

### Code Blocks
```
Sometimes, larger blocks of
code can be displayed in this
way too!
```

### Links

[Hyperlinks will be displayed in this format.](#links)

[**Sometimes it can also be bolded.**](#links)

[_Or italicized._](#links)

[**Or** a _mix_ of _**both!**_](#links)

### Boxes

<box>

This is a box. Some information may be put here.

There are three types of boxes used in this User Guide:
* [Additional Information](#additional-information)
* [Warning](#warning)
* [Tips](#tips)

</box>

### Additional Information

<box type="info">

This is additional information.

</box>

<box type="info" light>

This is also additional information.

</box>

<box type="info" seamless>

This is also additional information.

</box>

### Warning

<box type="warning">

This is a warning. Watch out for these!

</box>

<box type="warning" light>

This is also a warning. Watch out for these too!

</box>

<box type="warning" seamless>

This is also a warning. Watch out for these too!

</box>

### Tips

<box type="tip">

This is some helpful tips. Skip these if you are in a hurry!

</box>

<box type="info" light>

This is also some helpful tips. Skip these if you are in a hurry!

</box>

<box type="info" seamless>

This is also some helpful tips. Skip these if you are in a hurry!

</box>

Now that you are familiar with the User Guide, let's get right into it!

__________________________________________________________________________________________________________________

<div style="page-break-after: always;"></div>

## **Getting Started**

Welcome to the **LetsGetHired User Guide**! This guide will walk you through the basics of [**setting up**](#installation) and **[using the 
application](#tutorial-managing-your-internship-applications)** to manage your internship applications.

<box light header="By the end of this section, **you will learn:**">

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

__________________________________________________________________________________________________________________

### Launching LetsGetHired

1. Open your **Command Terminal**.
    * On _Windows_: Press `Windows + R` keys simultaneously, type `cmd` and press `Enter`. 
      * Alternatively, press `Windows` key, type `Terminal` and press `Enter`.
    * On _macOS_: Press `Command + Space` to open Spotlight search, type `Terminal`, and press `Enter`.

1. `cd` into your chosen home folder, and type in the following command:
    ```
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

__________________________________________________________________________________________________________________

<div style="page-break-after: always;"></div>

## **Tutorial - Managing Your First Internship Application**

In this tutorial, we will walk you through a typical workflow for using LetsGetHired to manage your internship applications. <br>

### Step 1: Adding Your Internship Application

**Context:** You are starting your internship application journey, and you want to add a new internship application.

Imagine that you have applied for a **Software Engineering Internship** at **Google** for the **Summer of 2024**.

To **add your internship application**, go to the _Command Box_ and type the following command:
```
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

__________________________________________________________________________________________________________________

<div style="page-break-after: always;"></div>

## **Commands**

This section explains the detailed list of the commands (and its usages) which are available for you to use.

<box type="tip" seamless>

If you have been using **LetsGetHired** and just need a **quick refresher** on the commands available, you can jump to 
the [_Command Summary_ section of the **User Guide**](#command-summary) below.

</box>

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

* All parameters should be preceded by its respective **prefix**.<br>
    ```
    add n/COMPANY_NAME r/ROLE c/CYCLE
    ``` 
    For example, `n/` is the prefix for the `COMPANY_NAME` parameter. `r/` is the prefix for `ROLE` and `c/` is the prefix for `CYCLE`.

    <box type="warning" light>
  <md>
  **Exception**: The `INDEX` parameter does not require any prefix.
  </md>
    </box>

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

The `help` command is designed to offer you **instant guidance**. 

If you ever find yourself unsure about how to use the system or need a quick reminder of the available commands, 
the `help` command will provide **direct access** to this **User Guide**, ensuring you have all the information you need at your fingertips.

#### Command Usage

**Command**:
```
help
```

#### Parameters:

<box type="important" seamless>

The `help` command does **not** require any parameters.

</box>

#### Example:

If you wish to access the **User Guide**, simply type:
```
help
```

![help message](images/helpMessage.png)

Upon execution, the system will display the link to this **User Guide**, offering details and examples for all available commands.

<box type="tip" light>

* Utilize the `help` command whenever you're uncertain about the functionalities or need a refresher on how to use certain commands.
* As you become more accustomed to the system, you might find that you use the `help` command less frequently. Nevertheless, it's always there as a reliable resource should you need it.

</box>

__________________________________________________________________________________________________________________

### Listing Your Internship Applications: `list`

The `list` command is your quick snapshot tool, providing a **comprehensive view** of all your internship applications. 

It conveniently presents every application you've added, helping you gain a holistic perspective of your internship search and manage your applications effectively.

#### Command Usage

**Command**:
```
list
```

#### Parameters:

<box type="important" seamless>

The `list` command does **not** require any parameters.

</box>

#### Example:

To view all your internship applications, simply type:
```
list
```

**_UPDATE PICTURE HERE_**
![list command example](images/listExample.jpg)

Upon execution, the system will display a **complete list** of all your recorded internship applications.

This display provides a comprehensive overview of your applications, detailing the company name, role, cycle, status, and deadline for each. 
By glancing through the list, you can quickly assess your application status, the diversity of roles you've applied for, and any impending deadlines.

<box type="tip" light>

* Use the `list` command frequently to keep track of your applications and ensure you're on top of any required follow-ups or upcoming interviews.
* Combined with the [**`sort`**](#sorting-your-internship-applications-sort) command, the `list` function becomes an even more powerful tool, enabling you to customize the view of your applications based on specific criteria.

</box>

__________________________________________________________________________________________________________________

### Adding a New Internship Application: `add`

The `add` command is an integral part of managing your internship applications. 

It enables you to seamlessly **input new applications** into your record. By keeping all your applications up-to-date, 
you can efficiently keep track of your progress, upcoming interviews, and crucial deadlines.

#### Command Usage

**Command**:
```
add n/COMPANY_NAME r/ROLE c/CYCLE [s/STATUS] [d/DEADLINE]
```

#### Parameters:

1. **n/COMPANY_NAME**: The company's name where you've applied.
   * Example: `Jane Street`, `Google`

2. **r/ROLE**: The role you've applied for.
    - Example: `Software Engineering Intern`

3. **c/CYCLE**: The internship cycle, which typically corresponds to the season or term.
    - Example: `Summer`, `Winter`

4. **s/STATUS** (Optional): The current status of your application.
   <box type="info" seamless>

   Choose your application `STATUS` from the following:
    - `Pending`
    - `Interview`
    - `Assessment`
    - `Offered`
    - `Rejected`

    </box>

5. **d/DEADLINE** (Optional): The application deadline or any other crucial dates.
   <box type="info" seamless>

   The `DEADLINE` should adhere to the `dd MMM yyyy` format. For instance: `25 Oct 2023`.
   </box>

#### Example:

To add an internship application for a **Software Engineering** role at **Google** for the **Summer 2024** cycle with an **Offered** 
status and a deadline on **25th October 2023**, you can type:
```
add n/Google r/Software Engineering c/Summer 2024 s/Offered d/25 Oct 2023
```

**_UPDATE PICTURE HERE_**

![add command example 1](images/addExample1.jpg)

By executing the `add` command, your application details are recorded and presented in the list. 
This ensures that as you apply to more roles and receive updates on your applications, every piece of information is 
systematically stored, making tracking and follow-ups more manageable.

<box type="tip" light>

* Regularly adding applications immediately after submission ensures you don't miss out on tracking any application.
* Remember to update the `STATUS` of your applications as they progress. This way, you'll always be aware of which stage each application is at.

</box>

__________________________________________________________________________________________________________________

### Editing an internship application : `edit`

The `edit` command offers flexibility in managing your internship applications. 

As the status of your applications change, or if you notice inaccuracies in the details you've entered, 
the `edit` command ensures you can **update any part of your application** without any hassle.

#### Command Usage

**Command**:
```
edit INDEX [n/COMPANY_NAME] [r/ROLE] [c/CYCLE] [s/STATUS] [d/DEADLINE]
```

#### Parameters:

<box type="important" seamless>

At least **one** of the optional parameters must be provided.
</box>

1. **INDEX**: Represents the position of the internship application in your list that you wish to update.
   <box type="info" seamless>

   The `INDEX` should be a positive integer. For example: `1`, `2`, `3`, etc. This corresponds to the application's position in your list.
   </box>

2. **n/COMPANY_NAME** (Optional): The updated company name.
3. **r/ROLE** (Optional): The updated internship role.
4. **c/CYCLE** (Optional): The updated internship cycle.
5. **s/STATUS** (Optional): The updated status of your application.
6. **d/DEADLINE** (Optional): The updated deadline or any related dates.

#### Example:

Let's say you want to edit the second internship application in your list to change the company name to **Amazon**, 
update the role to **Data Analyst Intern**, and modify the status to **Interview**. The command would look like this:

```
edit 2 n/Amazon r/Data Analyst Intern s/Interview
```

**_UPDATE PICTURE HERE_**

![edit command example](images/editExample.jpg)

You can see how the original details of the application are updated after executing the `edit` command. 
This ensures your application list remains current and accurate, reflecting the latest changes or corrections you've made.

<box type="tip" light>

* Always confirm the `INDEX` of the application you want to edit by using the `list` command before executing the `edit` command. This helps avoid unintended modifications.
* While it's crucial to keep your applications updated, make sure to double-check the changes you're making to ensure accuracy.

</box>

__________________________________________________________________________________________________________________

### Adding a Note : `note`

The `note` command is a beneficial tool designed to enrich your internship applications with **additional details**. 

As you progress through your application process, there might be specific information, feedback, or reminders 
you'd like to attach to an application for future reference. The `note` command allows you to effortlessly do just that.

#### Command Usage

**Command**:
```
note INDEX [i/NOTE]
```

#### Parameters:

1. **INDEX**: Represents the position of the internship application in your list to which you wish to attach a note.
   <box type="info" seamless>

   The `INDEX` should be a positive integer. For example: `1`, `2`, `3`, etc. This corresponds to the application's position in your list.
   </box>

2. **i/NOTE**: The note or information you'd like to add. This could be feedback from an interview, reminders for follow-up, or any other relevant details.

#### Example:

Suppose you just had an interview for the third internship application on your list, and you'd like to add a note 
mentioning that the **interviewer emphasized a follow-up in two weeks**. The command would look like this:

```
note 3 i/Interviewer emphasized follow-up in two weeks.
```

**_UPDATE PICTURE HERE_**

![note command example](images/noteExample.jpg)

You can see how the note gets attached to the specified internship application. 
This ensures you have a comprehensive view of each application, complete with any supplementary details or reminders you've deemed important.

<box type="tip" light>

* Making a habit of attaching notes, especially after interviews or significant interactions, will ensure you don't forget crucial details.
* Reviewing notes can be particularly helpful when preparing for subsequent rounds of interviews or follow-ups.

</box>

__________________________________________________________________________________________________________________

### Deleting an internship application : `delete`

The `delete` command is an essential tool in your internship application management toolkit, designed to help you **maintain a clean and relevant list** of applications. 

As situations change, such as when you decide not to pursue a particular opportunity or when an application is no longer relevant, 
the `delete` command ensures you can easily remove it from your records.

#### Command Usage

**Command**:
```
delete INDEX
```

#### Parameters:

1. **INDEX**: Represents the position of the internship application in your list that you wish to delete.
   <box type="info" seamless>

   The `INDEX` should be a positive integer. For example: `1`, `2`, `3`, etc. This corresponds to the application's position in your list.
   </box>

#### Example:

If you decide that the fourth internship application on your list is no longer relevant, and you want to remove it, the command would look like this:

```
delete 4
```

**_UPDATE PICTURE HERE_**

![delete command example](images/deleteExample.jpg)

You can see how the specified internship application is removed from the list. 
The remaining applications adjust their index numbers accordingly, ensuring a continuous sequence.

<box type="warning" light>

* Before executing the `delete` command, always double-check the `INDEX` to ensure you are removing the correct application.
*  Consider confirming your decision to delete an application, as **this action is irreversible**. You'd have to manually re-add the application if you mistakenly delete it.

</box>

__________________________________________________________________________________________________________________

### Viewing Your Internship Application: `view`

The `view` command is designed to offer a **detailed insight** into your individual internship applications. 

While a summarized list gives you an overview of all your applications, the `view` command provides an in-depth look 
at each application, showcasing the specifics and any attached notes. This ensures you have comprehensive access to the 
data when preparing for interviews, follow-ups, or making decisions.

#### Command Usage

**Command**:
```
view INDEX
```

#### Parameters:

1. **INDEX**: Represents the position of the internship application in your list that you wish to view in detail.
   <box type="info" seamless>

   The `INDEX` should be a positive integer. For example: `1`, `2`, `3`, etc. This corresponds to the application's position in your list.
   </box>

#### Example:

If you wish to view the details of the first internship application on your list, the command would be:

```
view 1
```

![note command example](images/viewExample.jpg)

The provided image showcases a _Select View_ of the specific internship application. Here, you can observe all the particulars of the application, including the company's name, role, cycle, status, deadline, and any attached notes. This meticulous breakdown ensures you're fully informed and prepared for any next steps associated with that particular application.

<box type="tip" light>

* Utilize the `view` command prior to interviews or follow-ups to refresh your memory about the specifics of the application.
* Regularly reviewing applications in detail can help you stay on top of any action items or next steps.

</box>

__________________________________________________________________________________________________________________

### Searching Your Internship Application: `find`

The `find` command is a powerful tool designed to help you **quickly search and locate specific internship applications** 
based on various criteria. 

Whether you're trying to find all applications for a specific company or filter applications by their status, the `find` 
command ensures you can effortlessly pinpoint the relevant records.

#### Command Usage

**Command**:
```
find [n/COMPANY_NAME] [r/ROLE] [c/CYCLE] [s/STATUS] [d/DEADLINE] [i/NOTE]
```

#### Parameters:

1. **n/COMPANY_NAME**: Represents the company's name you're trying to search for.
2. **r/ROLE**: Refers to the specific internship role you wish to locate.
3. **c/CYCLE**: Identifies the internship cycle (e.g., `Summer`, `Winter`) you want to search for.
4. **s/STATUS**: Helps filter applications based on their current status.
5. **d/DEADLINE**: Locates applications by their deadline.
6. **i/NOTE**: Searches applications based on specific notes attached to them.

<box type="important" seamless>

Remember, at least **one** of the optional parameters must be provided to execute the `find` command.
</box>

#### Example:

Suppose you want to quickly locate all your applications for the company **Google**. Your command would be:

```
find n/Google
```

**_UPDATE PICTURE HERE_**

![find command example](images/findExample.jpg)
You can see that all the applications where the company name matches or contains the keyword `Google`.

<box type="info" light>

**Note about `find` command:**

1. **Partial Matches**: The `find` command is robust and recognizes partial matches. So, if you search for `Apl`, it'll show applications for `Apple`, `apple`, and even `Alphabet Limited`.

2. **Multiple Criteria**: By adding multiple parameters, you can refine your search further. For instance, `find n/Google c/Summer s/Pending` would display applications that meet at least one of the following criteria:
    - Company name contains `Google`
    - Cycle contains `Summer`
    - Status is `Pending`

</box>

<box type="tip" light>

This feature is particularly useful when you need a more refined list or when you're unsure about the exact details but remember a few keywords.

</box>

__________________________________________________________________________________________________________________

### Sorting Your Internship Applications: `sort`

The `sort` command is designed to help you **quickly and conveniently organize** your internship applications. 

With this command, you can prioritize and manage your applications according to a variety of criteria.

#### Command Usage

**Command**:
```
sort [n/COMPANY_NAME] [r/ROLE] [c/CYCLE] [s/STATUS] [d/DEADLINE]
```

#### Parameters:

<box type="important" seamless>

* At least **one** of the optional parameters must be provided.
* Each parameter can only be replaced by either `a` (for ascending order) or `d` (for descending order). 
  * If no order is specified, the default is ascending.

</box>

* **n/COMPANY_NAME**: Sorts applications by the company name.
* **r/ROLE**: Sorts applications by the role.
* **c/CYCLE**: Sorts applications by the cycle.
* **s/STATUS**: Sorts applications by their status.
* **d/DEADLINE**: Sorts applications by their deadline.

#### Examples:

1. **Sort by Company Name (Ascending)**
   ```
   sort n/a
   ```
   This command will sort your internship application list in alphabetical order based on company names.

2. **Sort by Cycle (Descending)**
   ```
   sort c/d
   ```
   This command will sort your internship application list in reverse alphabetical order based on cycle.

3. **Sort by Deadline (Ascending)**
   ```
   sort d/a
   ```
   This command will sort your internship application list in chronological order based on deadlines.

4. **Sort by Status (Ascending)**
   ```
   sort s/a
   ```
   This command will sort your internship application list in alphabetical order based on their status.

<box type="tip" light>

Regularly sorting and reviewing your applications can help you manage your applications better and not miss any important deadlines.

</box>

__________________________________________________________________________________________________________________

### Clearing all entries : `clear`

The `clear` command provides an efficient way to **erase all internship application** records in your database. 

It's a complete reset, ensuring you have a clean slate to work with. This command can be particularly useful if you've 
been experimenting with sample data or if you want to start fresh for a new application cycle.

#### Command Usage

**Command**:
```
clear
```

#### Parameters:
<box type="important" seamless>

The `clear` command does **not** require any parameters.

</box>

<box type="tip" light>

**Recommended Use**: If you've been familiarizing yourself with **LetsGetHired** using sample data, consider using the `clear` command to wipe the slate clean and input your genuine applications.

</box>

<box type="warning" light>

While the `clear` command is potent and efficient, it's also **irreversible**. Once you clear your data, there's no getting it back. 
Always **ensure you have backups** or have reviewed your data thoroughly before deciding to use this command.

</box>

__________________________________________________________________________________________________________________

### Exiting the program : `exit`

The `exit` command is designed to offer you a smooth and swift way to **close the **LetsGetHired** program**. 

With just a simple command, you can ensure that the application terminates gracefully, safeguarding all the data and changes you've made during your session.

#### Command Usage

**Command**:
```
exit
```

#### Parameters:
<box type="important" seamless>

The `exit` command does **not** require any parameters.

</box>

#### How it Works:

Upon executing the `exit` command, **LetsGetHired** will initiate its shutdown sequence. 
This involves saving any changes you've made during your session and closing the application window. 
It ensures that all your data remains intact the next time you launch the program.

__________________________________________________________________________________________________________________

## **Storage**

This section will help you understand how your data is being stored in your computer.

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

__________________________________________________________________________________________________________________

<div style="page-break-after: always;"></div>

## **FAQ**

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install _LetsGetHired_ in the other computer and overwrite the empty data file it creates with the file that 
contains the data from your previous _LetsGetHired_ home folder.

__________________________________________________________________________________________________________________

## **Known issues**

* **When using multiple screens**, if you move the application to a secondary screen, and later switch to using only the 
primary screen, the GUI will open off-screen. The remedy is to delete the `preferences.json` file created by the application 
before running the application again.

__________________________________________________________________________________________________________________

<div style="page-break-after: always;"></div>

## **Command Summary**

| **Command** | **Usage**                                                                   | **Example**                                                                 |
|-------------|-----------------------------------------------------------------------------|-----------------------------------------------------------------------------|
| **Help**    | `help`                                                                      | `help`                                                                      |
| **List**    | `list`                                                                      | `list`                                                                      |
| **Add**     | `add n/COMPANY_NAME r/ROLE c/CYCLE [s/STATUS] [d/DEADLINE]`                 | `add n/Google r/Software Engineering c/Summer 2024 s/Offered d/25 Oct 2023` |
| **Edit**    | `edit INDEX [n/COMPANY_NAME] [r/ROLE] [c/CYCLE] [s/STATUS] [d/DEADLINE]`    | `edit 1 n/Apple`                                                            |
| **Note**    | `note INDEX [i/NOTE]`                                                       | `note 1 i/Interview on 26 Oct`                                              |
| **Delete**  | `delete INDEX`                                                              | `delete 1`                                                                  |
| **View**    | `view INDEX`                                                                | `view 1`                                                                    |
| **Find**    | `find [n/COMPANY_NAME] [r/ROLE] [c/CYCLE] [s/STATUS] [d/DEADLINE] [i/NOTE]` | `find n/Google`                                                             |
| **Sort**    | `sort [n/COMPANY_NAME] [r/ROLE] [c/CYCLE] [s/STATUS] [d/DEADLINE]`          | `sort n/a`                                                                  |
| **Clear**   | `clear`                                                                     | `clear`                                                                     |
| **Exit**    | `exit`                                                                      | `exit`                                                                      |


This table provides a quick and clear reference to the commands available in **LetsGetHired**. 
You can use it to efficiently navigate through the program and manage your internship applications.

__________________________________________________________________________________________________________________

<div style="page-break-after: always;"></div>

## **Glossary**

* **Mainstream OS**: Windows, Linux, Unix, OS-X
* **Internship Entry:** A record or entry in the system that represents information about an internship opportunity. It includes details such as company name, application status, deadlines, and notes.
* **Application Status:** The current stage or status of an internship application.
* **Sample Data:** Pre-populated data used for demonstration purposes, enabling new users to see how the application functions with realistic examples.
