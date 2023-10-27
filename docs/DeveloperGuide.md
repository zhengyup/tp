---
  layout: default.md
  title: "Developer Guide"
  pageNav: 3
---

# Developer Guide

<!-- * Table of Contents -->
<page-nav-print />

--------------------------------------------------------------------------------------------------------------------

## **Acknowledgements**

_{ list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well }_

--------------------------------------------------------------------------------------------------------------------

## **Setting up, getting started**

Refer to the guide [_Setting up and getting started_](SettingUp.md).

--------------------------------------------------------------------------------------------------------------------

## **Design**

### Architecture

<puml src="diagrams/ArchitectureDiagram.puml" width="280" />

The ***Architecture Diagram*** given above explains the high-level design of the App.

Given below is a quick overview of main components and how they interact with each other.

**Main components of the architecture**

**`Main`** (consisting of classes [`Main`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/status/Main.java) and [`MainApp`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/status/MainApp.java)) is in charge of the app launch and shut down.
* At app launch, it initializes the other components in the correct sequence, and connects them up with each other.
* At shut down, it shuts down the other components and invokes cleanup methods where necessary.

The bulk of the app's work is done by the following four components:

* [**`UI`**](#ui-component): The UI of the App.
* [**`Logic`**](#logic-component): The command executor.
* [**`Model`**](#model-component): Holds the data of the App in memory.
* [**`Storage`**](#storage-component): Reads data from, and writes data to, the hard disk.

[**`Commons`**](#common-classes) represents a collection of classes used by multiple other components.

**How the architecture components interact with each other**

The *Sequence Diagram* below shows how the components interact with each other for the scenario where the user issues the command `delete 1`.

<puml src="diagrams/ArchitectureSequenceDiagram.puml" width="574" />

Each of the four main components (also shown in the diagram above),

* defines its *API* in an `interface` with the same name as the Component.
* implements its functionality using a concrete `{Component Name}Manager` class (which follows the corresponding API `interface` mentioned in the previous point.

For example, the `Logic` component defines its API in the `Logic.java` interface and implements its functionality using the `LogicManager.java` class which follows the `Logic` interface. Other components interact with a given component through its interface rather than the concrete class (reason: to prevent outside component's being coupled to the implementation of a component), as illustrated in the (partial) class diagram below.

<puml src="diagrams/ComponentManagers.puml" width="300" />

The sections below give more details of each component.

### UI component

The **API** of this component is specified in [`Ui.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/status/ui/Ui.java)

<puml src="diagrams/UiClassDiagram.puml" alt="Structure of the UI Component"/>

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `InternApplicationListPanel`, `StatusBarFooter` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class which captures the commonalities between classes that represent parts of the visible GUI.

The `UI` component uses the JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that are in the `src/main/resources/view` folder. For example, the layout of the [`MainWindow`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/status/ui/MainWindow.java) is specified in [`MainWindow.fxml`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

* executes user commands using the `Logic` component.
* listens for changes to `Model` data so that the UI can be updated with the modified data.
* keeps a reference to the `Logic` component, because the `UI` relies on the `Logic` to execute commands.
* depends on some classes in the `Model` component, as it displays `InternApplication` object residing in the
  `Model`.

### Logic component

**API** : [`Logic.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/status/logic/Logic.java)

Here's a (partial) class diagram of the `Logic` component:

<puml src="diagrams/LogicClassDiagram.puml" width="550"/>

The sequence diagram below illustrates the interactions within the `Logic` component, taking `execute("delete 1")` API call as an example.

<puml src="diagrams/DeleteSequenceDiagram.puml" alt="Interactions Inside the Logic Component for the `delete 1` Command" />

<box type="info" seamless>

**Note:** The lifeline for `DeleteCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.
</box>

How the `Logic` component works:

1. When `Logic` is called upon to execute a command, it is passed to an `InternTrackerParser` object which in
   turn creates a parser that matches the command (e.g., `DeleteCommandParser`) and uses it to parse the command.
1. This results in a `Command` object (more precisely, an object of one of its subclasses e.g., `DeleteCommand`) which is executed by the `LogicManager`.
1. The command can communicate with the `Model` when it is executed (e.g. to delete a internApplication).
1. The result of the command execution is encapsulated as a `CommandResult` object which is returned back from `Logic`.

Here are the other classes in `Logic` (omitted from the class diagram above) that are used for parsing a user command:

<puml src="diagrams/ParserClasses.puml" width="600"/>

How the parsing works:
* When called upon to parse a user command, the `InternTrackerParser` class creates an `XYZCommandParser`
  (`XYZ` is a placeholder for the specific command name e.g., `AddCommandParser`) which uses the other
  classes shown above to parse the user command and create a `XYZCommand` object (e.g., `AddCommand`)
  which the `InternTrackerParser` returns back as a `Command` object.
* All `XYZCommandParser` classes (e.g., `AddCommandParser`, `DeleteCommandParser`, ...) inherit from the `Parser` interface so that they can be treated similarly where possible e.g, during testing.

### Model component
**API** : [`Model.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/status/model/Model.java)

<puml src="diagrams/ModelClassDiagram.puml" width="450" />


The `Model` component,

* stores the intern tracker data i.e., all `InternApplication` objects (which are contained in a
  `UniqueApplicationList`
  object).
* stores the currently 'selected' `InternApplication` objects (e.g., results of a search query) as a separate
  _filtered_ list which is exposed to outsiders as an unmodifiable `ObservableList<InternApplication>` that
  can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the 
  data in the list change.
* stores a `UserPref` object that represents the user’s preferences. This is exposed to the outside as a
* `ReadOnlyUserPref` objects.
* does not depend on any of the other three components (as the `Model` represents data entities of the domain, they should make sense on their own without depending on other components)

<box type="info" seamless>

**Note:** An alternative (arguably, a more OOP) model is given below. It has a `Tag` list in the 
`InternTracker`, which `InternApplication` references. This allows `InternTracker` to only require one `Tag` 
object per 
unique tag, instead of each `InternApplication` needing their own `Tag` objects.<br>

<puml src="diagrams/BetterModelClassDiagram.puml" width="450" />

</box>


### Storage component

**API** : [`Storage.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/status/storage/Storage.java)

<puml src="diagrams/StorageClassDiagram.puml" width="550" />

The `Storage` component,
* can save both intern tracker data and user preference data in JSON format, and read them back into
  corresponding objects.
* inherits from both `AddressBookStorage` and `UserPrefStorage`, which means it can be treated as either one (if only the functionality of only one is needed).
* depends on some classes in the `Model` component (because the `Storage` component's job is to save/retrieve objects that belong to the `Model`)

### Common classes

Classes used by multiple components are in the `seedu.addressbook.commons` package.

--------------------------------------------------------------------------------------------------------------------

## **Implementation**

This section describes some noteworthy details on how certain features are implemented.

### Click InternApplication Card

#### Implementation

The mechanism for the Card click is implemented by creating a new TextArea widget beside the InternApplicationListPanel
and a handler function to handle the event of a card click.

The following classes are created:
* `SelectView` - A class representation of the SelectView Text Area that is to display the details of the card

The new method is:
* `SelectView#displayDetails(String details))` - sets the String details as text in the TextArea
* `Messages#formatDisplay(InternApplication internApplication)` - returns a customised string summarising the details of the InternApplication
* `SelectView#handleCardClick()` - extracts out the details from the InternApplication object and sets it to the SelectView widget


The following method is renamed:
* `Messages#format(InternApplication internApplication)` is renamed to `Messages#formatDisplay(InternApplication internApplication)` - returns the feedback from an executed command


The following sequence diagram shows how Card Click feature:

<puml src="diagrams/SelectViewSequenceDiagram.puml" alt="SelectViewSequenceDiagram" />


#### Design Considerations

**Aspect: How feedback and details are returned from CommandResult**

* **Alternative 1 (current choice):** Separate feedback and details into 2 separate strings `feedbackToUser` and `detailsToUser`.
    * Pros: Clearer and intuitive for future developers to know the content which each string parameter should contain.
    * Cons: Additional parameters in the arguments might make code look complicated.
* **Alternative 2:** Have the feedback String contain the content for both feedbackToUser and detailsToUser through parsing.
      * Pros: Easier to implement.
      * Cons: Requires future developers working on the code to be mindful of how the String input should be structured for successful parsing

###  House-keep feature

#### Proposed Implementation

The proposed mechanism is facilitated by the delete button widget below SelectView and Deadlines feature. 
Additionally, it modifies the following operations:

* `InternApplicationUtiltyButton#handleDeleteClick()` — Deletes all entries in the list that is older than 1 year compared to present time.

The following sequence diagram shows how the sort operation works:

<puml src="diagrams/HousekeepSequenceDiagram.puml" alt="HousekeepSequenceDiagram" />


#### Design Considerations

**Aspect: How house-keep is done**

* **Alternative 1 (current choice):** Create a handler function that iterates through each InternApplication in the list and invokes a `DeleteCommand#execute()` if the predicate is satisfied.
    * Pros: Lesser coupling
    * Cons: There is a need to figure out how to make it so the feedback of the DeleteCommand is not shown in ResultDisplay widget.
* **Alternative 2:** Create a handler function that directly calls onto the `Model#deleteInternApplication()` if the predicate passes.
    * Pros: Easier to implement
    * Cons: Increase in coupling and dependencies from the Model class

    
### Undo feature

#### Proposed Implementation

The proposed undo mechanism is facilitated by `VersionedInternTracker`. It extends `InternTracker` with an
undo history, stored internally as a stack `savedStates`. Additionally, it implements the
following operations:

* `VersionedInternTracker#commit()` — Saves the current intern tracker state in its history.
* `VersionedInternTracker#undo()` — Restores the previous intern tracker state from its history.

VersionedInternTracker#undo() is exposed in the `Model` interface as `Model#undoAction()`

Given below is an example usage scenario and how the undo mechanism behaves at each step.

Step 1. The user launches the application for the first time. The `VersionedInternTracker` will be
initialized with an empty `savedStates`.

<puml src="diagrams/UndoState0.puml" alt="UndoState0" />

Step 2. The user executes `delete 5` command to delete the 5th internApplication in the intern tracker.
The `delete` command calls `Model#deleteInternApplication()`, which calls `VersionedInternTracker#commit()
`, adding a copy of the current `internApplications` to `savedStates` before carrying out the delete action.

<puml src="diagrams/UndoState1.puml" alt="UndoState1" />

Step 3. The user executes `add n/Google …​` to add a new internApplication. The `add` command calls
`Model#add()` which also calls `VersionedInternTracker#commit()`, adding a copy of the current `internApplications`
to `savedStates` before carrying out the add action.

<puml src="diagrams/UndoState2.puml" alt="UndoState2" />

<box type="info" seamless>

**Note:** If a command fails its execution, it will not call `VersionedInternTracker#commit()`, so the
`internApplications` state will not be added to `savedStates`.

</box>

Step 4. The user now decides that adding the internApplication was a mistake, and decides to undo that action
by executing the `undo` command. The `undo` command will call `Model#undoAddressBook()`, which pops the latest
`internApplications` state from `savedStates` and assigns it to the current internApplications.

<puml src="diagrams/UndoState3.puml" alt="UndoState3" />


<box type="info" seamless>

**Note:** If the size of `savedStates` is 0, meaning the stack is empty, then there are no previous
internApplications states to restore. The `undo` command calls `VersionedInternTracker#undo()`, which returns
False if there are no states to restore, and displays a message to the user that the latest change has already
been reached.

</box>

The following sequence diagram shows how the undo operation works:

<puml src="diagrams/UndoSequenceDiagram.puml" alt="UndoSequenceDiagram" />

<box type="info" seamless>

**Note:** The lifeline for `UndoCommand` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.

</box>

Step 5. The user then decides to execute the command `list`. Commands that do not modify the intern tracker,
such as `list`, will usually not call `VersionedInternTracker.commit()`, `Model#undoAction()`
Thus, the `savedStates` remains unchanged.

<puml src="diagrams/UndoState4.puml" alt="UndoState4" />

The following activity diagram summarizes what happens when a user executes a new command:

<puml src="diagrams/CommitActivityDiagram.puml" width="250" />

#### Design considerations:

**Aspect: How undo executes:**

* **Alternative 1 (current choice):** Saves the entire intern tracker.
  * Pros: Easy to implement.
  * Cons: May have performance issues in terms of memory usage.

* **Alternative 2:** Individual command knows how to undo by
  itself.
  * Pros: Will use less memory (e.g. for `delete`, just save the internApplication being deleted).
  * Cons: We must ensure that the implementation of each individual command are correct.

**Aspect: How history is implemented:**

* **Alternative 1:** Using a list and pointer to implement version control
    * Pros: Will allow for more features command
    * Cons: Uses more memory than a stack, as saved states are still stored after undo.

* **Alternative 2:** Store the complementary command for each action in the stack
    * Pros: Will use less memory (e.g. for `add`, just save the corresponding `delete`).
    * Cons: We must ensure that the implementation of complementing each individual command are correct.

### \[Proposed\] Data archiving

_{Explain here how the data archiving feature will be implemented}_


--------------------------------------------------------------------------------------------------------------------

## **Documentation, logging, testing, configuration, dev-ops**

* [Documentation guide](Documentation.md)
* [Testing guide](Testing.md)
* [Logging guide](Logging.md)
* [Configuration guide](Configuration.md)
* [DevOps guide](DevOps.md)

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Requirements**

### Product scope

**Target user profile**:

* CS undergraduate
* is looking for tech internships
* needs something to organise their internship applications
* prefer desktop apps over other types
* can type fast
* prefers typing to mouse interactions
* is reasonably comfortable using CLI apps

**Value proposition**: provides a fast and organized way to see internships and its progress, optimized for users who prefer a CLI

### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​                        | I want to …​                                                 | So that I can…​                                                                 |
|----------|--------------------------------|--------------------------------------------------------------|---------------------------------------------------------------------------------|
| `* * *`  | user                           | log the status of my internship applications                 | keep a record of my internship application                                      |
| `* * *`  | user                           | see a list of internships that I have applied for            | keep track of all companies/roles I have applied for                            |
| `* * *`  | user                           | view a specific internship application                       | easily access specific internship applications                                  |
| `* * *`  | user                           | delete an internship application                             | remove internship applications I do not want to track anymore                   |
| `* * *`  | user                           | update the status of the internships that I have applied for | keep track of the progress of the roles I have applied for                      |
| `* * *`  | user                           | open the app with a click of a button or an exe/batch file   | save time and easily access the internship tracker                              |
| `* *`    | diligent user                  | write notes to include background information on the company | easily refresh myself on what the company does before an interview              |
| `*`      | user with many applications    | include contact details in the internship details            | find who to contact for further updates/information                             |
| `* *`    | user with many applications    | quickly search for a application                             | efficiently find the entry I am looking for                                     |
| `* *`    | user with diverse applications | categorize companies by industry                             | organise my applications better                                                 |
| `* *`    | user with many options         | sort by priority level for my applications                   | allocate my time and resources efficiently                                      |              
| `* *`    | conscientious user             | attach notes to each application                             | I can jot down important information about the company or application process   |
| `* *`    | organised user                 | sort my applications                                         | I can easily get an organised view of my applications                           |

*{More to be added}*

### Use cases

(For all use cases below, the **System** is the `LetsGetHired` and the **Actor** is the `User`, unless specified otherwise)

**UC1: Add an internship application**

**MSS**

1.  User enters the details of the internship to be added
2.  LetsGetHired adds the internship to the list of internships
3.  LetsGetHired displays the current number of internship applications

    Use case ends.

**Extensions**

* 1a. The command format is incorrect

    * 1a1. AddressBook shows an error message, guiding users on the correct format.

      Use case ends.

* 1b. The entered cycle is not acceptable

    * 1e1. AddressBook shows an error message, guiding users on acceptable cycle values.

      Use case ends

* 1c. The entered status is not acceptable

    * 1c1. AddressBook shows an error message, guiding users on acceptable status values.

      Use case ends

**UC2: View the list of internship applications**

**MSS**

1.  User requests to list all internship applications
2.  LetsGetHired displays all the internship applications

    Use case ends.

**UC3: View an internship application**

**MSS**

1.  User requests to <ins>list all internship applications (UC2)</ins>
2.  LetsGetHired displays all the internship applications
3.  User requests to view a specific internship application
4.  LetsGetHired displays the required internship application

    Use case ends.

**Extensions**

* 2a. The list is empty

    Use case ends.

* 3a. The command format is incorrect

    * 3a1. AddressBook shows an error message, guiding users on the correct format.

      Use case ends.

* 3b. The index of internship application entered is incorrect

    * 3b1. AddressBook shows an error message.

      Use case ends.

**UC4: Delete an internship application**

**MSS**

1.  User requests to <ins>list all internship applications (UC2)</ins>
2.  LetsGetHired displays all the internship applications
3.  User requests to delete a specific internship application
4.  LetsGetHired deletes the required internship application
5.  LetsGetHired displays the current number of internship applications

Use case ends.

**Extensions**

* 2a. The list is empty
      
  Use case ends.

* 3a. The command format is incorrect

    * 3a1. AddressBook shows an error message, guiding users on the correct format.

      Use case ends.

* 3b. The index of internship application entered is incorrect

    * 3b1. AddressBook shows an error message.

      Use case ends.

**UC5: Update the status of an internship applications**

**MSS**

1.  User requests to <ins>list all internship applications (UC2)</ins>
2.  LetsGetHired displays all the internship applications
3.  User requests to update the status of a specific internship application
4.  LetsGetHired updates the required internship application
5.  LetsGetHired displays the updated internship application

Use case ends.

**Extensions**

* 2a. The list is empty

  Use case ends.

* 3a. The command format is incorrect

    * 3a1. AddressBook shows an error message, guiding users on the correct format.

      Use case ends.

* 3b. The index of internship application entered is incorrect

    * 3b1. AddressBook shows an error message.

      Use case ends.  

* 3c. The entered status is not acceptable

    * 3c1. AddressBook shows an error message, guiding users on acceptable status values.

      Use case ends

**UC6: Search for an application**

**MSS**

1.  User provides the search criteria and requests for a search
2.  LetsGetHired shows a list of matching applications

* 1a. The command format is incorrect.

    * 1a1. LetsGetHired shows an error message, guiding users on the correct command format
      
      Use case resumes at step 1.

**UC7: Edit the details of an application**

**MSS**

1.  User requests to <ins>list all internship applications (UC2)</ins>
2.  LetsGetHired shows a list of applications
3.  User provides new information to update the chosen application
4.  LetsGetHired updates the application
5.  LetsGetHired displays the updated application

       Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.

* 3a. The given index is invalid

    * 3a1. LetsGetHired shows an error message and shows number of current applications.

      Use case resumes at step 2.

* 3b. The command format is incorrect.

    * 3b1. LetsGetHired shows an error message, guiding users on the correct command format

      Use case resumes at step 2.

**UC8: Add a note to an application**

**MSS**

1.  User requests to <ins>list all internship applications (UC2)</ins>
2.  LetsGetHired shows a list of applications
3.  User provides note to add to specific application
4.  LetsGetHired adds the note to the application
5.  LetsGetHired displays the application with the added note

    Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.

* 3a. The given index is invalid.

    * 3a1. LetsGetHired shows an error message and shows number of current applications.

      Use case resumes at step 2.

* 3b. The command format is incorrect.

    * 3b1. AddressBook shows an error message, guiding users on the correct command format

      Use case resumes at step 2.

**UC9: Delete a note on an application**

**MSS**

1.  User requests to <ins>view a specific application (UC3)</ins>
2.  User requests to delete a specific note from the application
3.  LetsGetHired deletes the note from the application 
4.  LetsGetHired displays the application with the updated list of notes

    Use case ends.

**Extensions**


* 1a. The application has no notes.

  Use case ends.

* 2a. The given note index is invalid.

    * 2a1. LetsGetHired shows an error message and shows number of current notes attached to the application.

      Use case resumes at step 3.

* 2b.The command format is incorrect.

    * 2b1. LetsGetHired shows an error message, guiding users on the correct command format.

      Use case resumes at step 1.

**UC10: Sort Applications**

**MSS**

1.  User requests to sort the applications by provided category and order
2.  LetsGetHired sorts and displays the sorted applications
3.  LetsGetHired displays category applications are sorted in

    Use case ends.

**Extensions**

* 1a. Provided category or order is invalid 
  
  * 1a1 LetsGetHired shows an error message and shows the valid categories and orders to choose from 

    Use case ends.


### Non-Functional Requirements

#### Technical

* Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
* The system should not require an internet connection to operate.

#### Performance

* Should be able to hold up to 1000 internships without a noticeable sluggishness in performance for typical usage.
* The system should respond to user interactions within 1 second for normal operations (e.g. adding an internship, searching for an internship) for a smooth user experience.

#### Usability

* A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.
* Users should be able to use shortcuts for frequently performed tasks to save time.

#### Privacy

* A user's application info should be stored offline, and not uploaded to any form of cloud storage.


*{More to be added}*

### Glossary

* **Mainstream OS**: Windows, Linux, Unix, OS-X
* **Internship Entry:** A record or entry in the system that represents information about an internship opportunity. It includes details such as company name, application status, deadlines, and notes.
* **Application Status:** The current stage or status of an internship application.
* **Sample Data:** Pre-populated data used for demonstration purposes, enabling new users to see how the application functions with realistic examples.

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Instructions for manual testing**

Given below are instructions to test the app manually.

<box type="info" seamless>

**Note:** These instructions only provide a starting point for testers to work on;
testers are expected to do more *exploratory* testing.

</box>

### Launch and shutdown

1. Initial launch

   1. Download the jar file and copy into an empty folder

   1. Double-click the jar file Expected: Shows the GUI with a set of sample contacts. The window size may not be optimum.

1. Saving window preferences

   1. Resize the window to an optimum size. Move the window to a different location. Close the window.

   1. Re-launch the app by double-clicking the jar file.<br>
       Expected: The most recent window size and location is retained.

1. _{ more test cases …​ }_

### Deleting a internApplication

1. Deleting a internApplication while all internApplications are being shown

   1. Prerequisites: List all internApplications using the `list` command. Multiple internApplications in the list.

   1. Test case: `delete 1`<br>
      Expected: First contact is deleted from the list. Details of the deleted contact shown in the status message. Timestamp in the status bar is updated.

   1. Test case: `delete 0`<br>
      Expected: No internApplication is deleted. Error details shown in the status message. Status bar remains the same.

   1. Other incorrect delete commands to try: `delete`, `delete x`, `...` (where x is larger than the list size)<br>
      Expected: Similar to previous.

1. _{ more test cases …​ }_

### Saving data

1. Dealing with missing/corrupted data files

   1. _{explain how to simulate a missing/corrupted file, and the expected behavior}_

1. _{ more test cases …​ }_
