# _BuildingBuddy_ (Ver 1.0)

<img src="./images/BB_icon.png"  width="40%" alt="BuildingBuddy App Logo">

## Introduction
_BuildingBuddy_ is a desktop indoor navigation app designed to help students at Western University navigate through different floors in three buildings: Middlesex College, Kresge Building, and Physics & Astronomy Building. The entire development process started on January 25, 2023, and ended on April 6, 2023. Major coding work started on March 7, 2023. _BuildingBuddy_ is developed by Team 14 enrolled in CompSci 2212B at Western University.

## Configurations
_BuildingBuddy_ is a Maven project written in Java. The integrated development environment in use is IntelliJ IDEA, with OpenJDK 19 (Java version 19.0.2).

For better user experience, set the display resolution to 1496 * 967 or higher.

If you run from IntelliJ IDEA, the easiest way to configure this app is to create a **New Project from Version Control**. Choose **Git** as your version control, and add **URL**: https://github.com/dan1el5/BuildingBuddy.git.

Alternatively, you may create a new project using all the source files and build the app yourself. Be sure to select `Java 19 SDK of 'BuildingBuddy' Module` and `ca.uwo.csteam14.Main` as the Main class.

If you try to run the `BuildingBuddy.jar` file, make sure it is located at the same directory as the `data`, `maps`, and `images` folders (see screenshot below).

![Jar file](./images/jar.png)


If you come across issues with dependencies, read the next section. To minimize issues you may encounter running the program, you are encouraged to read the ensuing content (including the FAQ section).

## Dependencies
Thanks to Maven, the program should load dependencies automatically. In case it doesn't, add dependencies on your own. All dependencies are stored locally in the `./lib` directory. Choose **File** – **Project Structure** – **Modules** – **Dependencies**, click the **+** icon, choose **JARs or Directories**, and add the entire `./lib` directory as dependencies.
If you cannot locate them, you may also download the entire `lib` directory [here](https://jasonshew.ca/_silo/lib.zip) or from [GitHub](https://github.com/dan1el5/BuildingBuddy/raw/master/dev_backup_data_files/lib.zip) as a zip file. Unzip this downloaded file and add the entire folder as dependencies to the aforesaid location. Refer to the screenshot below.

![Add dependencies](./images/dependencies.png)

## Exploration Mode
Enter this mode by clicking the **Explore** button from the splash screen (opening screen). This interface allows the user to both view existing POIs and create new POIs, which are called My Locations in this app.

## Bookmark Manager
Enter this mode by clicking **View** – **Bookmarks** or press `Ctrl + B`. This interface is designed to manage the user's favourite POIs (both built-in and user-created). Please note that the user cannot create new POIs in this mode.

## My Locations Manager
Enter this mode by clicking **View** – **My Locations** or press `Ctrl + L`. This interface is designed to manage user-created POIs. Please note that the user cannot create new POIs in this mode.

## Discovery Mode
Enter this mode by clicking **Discovery** (Windows user: <code>Alt + D</code>, Mac user: <code>control + option + D</code>) using the search bar located at the bottom left on all screens except the splash screen. The user can browse all the POIs across all the buildings or search for a specific POI. To view all POIs across the buildings, simply hit <b>Go</b>. The search bar will also take you to any floor when the search phrase is a <i>building + floor code</i> shortcut (e.g. MC2F). Learn more in the FAQ section below.

## Focus Mode
In Bookmark Manager, My Locations Manager, Search Mode, and Discovery Mode, if the user clicks on any POI on the list, that highlighted POI will be the only POI shown on the screen. Despite that, the user can still click on other POIs on this map, even if their icons do not show up until you click. To unhighlight a POI, you may close the POI reader/editor by clicking **Cancel** or **[x]**, or simply clicking on the highlighted POI again. To view all the POIs on a floor map at once, click **Discover** in the menu or use the search bar with the <i>building + floor code</i> shortcut (see the FAQ section below).

## Development Mode
To activate Development Mode in _BuildingBuddy_, choose **More** – **Developer Tool** in the app, or press `Ctrl + X`.

Development Mode is accessible only with a security key. The initial security key is `CS2212BB` (case-sensitive). Since developers are allowed to change the security key, you are encouraged to check the newest security key stored in plain text in `./data/security_key`.

This security key is designed to only prevent regular users from modifying the database accidentally.  

To quit Development Mode, either click [X] or **Exit** to quit the entire program, or click **Logout** to restart the program as a regular user.

## Special Features
* **Display:** The GUI is programmed to occupy a fixed portion of screen estate regardless of screen size.
* **Hotkey:** Key features support hotkey commands for enhanced accessibility.
* **Automatic Reboot:** The app supports automatic rebooting (when user resets the app or developer chooses to log out).
* **Advanced Search Tool:** The advanced search tool returns search results matching against various types of metadata, even including descriptions.
* **Restore Default Settings:** Regular users can choose to reset the program to factory settings in one click.
* **User Data Destruction:** Regular users can choose to delete their entire bookmark collection or all POIs they have defined in one click.
* **Real-Time Refreshing:** When a POI is added, edited, or deleted, the data list and map viewer get synced and refreshed in real time.
* **Responsive Design:** The user editor features responsive design; it's more than just buttons and text fields juxtaposed with each other.
* **Accessible Design:** Albeit partially, the design of the program is accessibility-minded; 
* **Unlimited Data Capacity:** Users and developers can create unlimited numbers of POIs in addition to existing built-in POIs.
* **Easy Reset:** Developers can choose to erase all the built-in data in one click.
* **Change Password:** Developers can change their security key at any time.
* **Consistency:** The program will remember the user's last choice of building and floor and last successful search query before they quit or restart.
* **User-Centered Design:** For example, the POI editor provides default placeholder text and deletes it automatically on user input. When the user has input content, auto deletion will not be triggered.
* **Sustainability:** Users and developers can check if there's a new version available and update their software in one click (or by pressing <code>Ctrl + U</code>).

## FAQ

### What is a POI?
<p>A POI is a point of interest, namely a location on the map.</p>
<hr>

### What is a bookmark?
<p>A bookmark is one of your favourite POIs that you hope to view conveniently at any time.</p>
<hr>

### What is My Location?
<p>My Location is a POI defined by the user, not a built-in POI.</p>
<hr>

### How can I bookmark / unbookmark a location?
<p>Click on any icon that represents a POI on the map and choose <b>Add Bookmark</b> or <b>Remove Bookmark</b> before pressing <b>Save Changes</b>.</p>
<hr>

### How many My Locations are allowed to be created?
<p>Currently, <i>BuildingBuddy</i> allows the user to create an unlimited number of POIs.</p>
<hr>

### How can I explore another building?
<p>Click <b>Start</b> in the menu on top and use the building selector underneath the app logo.</p>
<hr>

### How can I get to a specific floor and see all the locations? (Building + Floor Code Shortcut)
<p>A straightforward way to view a specific floor will be using the **Discover** button in the app menu (Windows user: <code>Alt + D</code>, Mac user: <code>control + option + D</code>). There you will be able to choose any floor you wish to visit.</p>
<p>Alternatively, when you are not on the splash screen (the opening screen), you will see a search bar at the bottom left. Use the <i>building code + floor code</i> shortcut to explore any floor.</p>
<p>A building code is a building abbreviation:</p>
<ul>
<li><b>MC</b> for Middlesex College</li>
<li><b>KB</b> for Kresge Building</li>
<li><b>PAB</b> for Physics & Astronomy Building</li>
</ul>

<p>A floor code is a single-digit number + F:</p>
<ul>
<li><b>0F</b> for ground floor</li>
<li><b>1F</b> for first floor</li>
<li><b>2F</b> for second floor</li>
<li>...</li>
</ul>
<p>For example, if you want to visit the second floor at Middlesex College, just enter <b>MC2F</b> and click <b>Go</b> (or hit <code>Enter</code>).</p>
<p>The search bar remembers your last successful search phrase, so you can simply click <b>Go</b> to stay on that floor.</p>
<hr>

### How can I create a location?
<p>As a user, you can only create a POI when you're in Exploration Mode (where you see a layer filter on the left). Click on any empty spot on the map. Edit the name and description for this location. Click <b>Save Changes</b> when you're done.</p>
<p>When the user is in Bookmark Manager, My Locations Manager, Search mode or Discovery mode, they cannot add new POIs. Those interfaces are designed to manage existing POIs.</p>
<p>You can add a POI by clicking <b>Start</b> in the menu and <b>Explore</b> the building where you hope to add a POI.</p>
<hr>

### Why can't I edit the room numbers for My Locations?
<p>Room numbers are currently not available for My Locations, but you can write room numbers and any useful information in the <b>Description</b> text box. What's cool, they are searchable!</p>
<hr>

### Why do I see only washrooms and elevators in Exploration Mode?
<p>The layer filter on the left panel shows only washrooms and accessible facilities by default. Click on other layers to see more categories of POIs. And yes, you can click on any layer again to toggle it off.</p>
<hr>

### How to view or edit bookmarks?
<p>Enter <b>View</b> – <b>Bookmarks</b>, or press <code>CTRL + B</code>.</p>
<hr>

### How to view or edit My Locations?
<p>Enter <b>View</b> – <b>My Locations</b>, or press <code>CTRL + L</code>.</p>
<hr>

### How many bookmarks can I have?
<p>A user can have as many bookmarks as they want.</p>
<hr>

### Can I bookmark a "My Location"?
<p>Sure! You can bookmark anything on any floor map.</p>
<hr>

### Can I delete My Location?
<p>Sure! You can do that! Just bear in mind that if you delete one of My Locations that has been bookmarked, you lose that bookmark too.</p>
<hr>

### What are "Nuke Bookmarks", "Nuke My Locations", and "Reset BuildingBuddy"?
<p>These three features will delete all your bookmarks, My Locations, and both. Your personalized data will be permanently erased, and the program will be restored to its default settings.</p>
<p>If you only choose to nuke all My Locations, they will also disappear from your Bookmarks if you have bookmarked them.</p>
<p>After you confirm your choice, the program will reboot automatically.</p>
<hr>

### How can I activate "Discovery Mode" to see all the POIs on all the floors in all the buildings?
<p>Use the search bar and click <b>Go</b> right away. You can either keep the placeholder phrase in the text bar or clear it before hitting <b>Go</b>.</p>
<hr>

### Some of my bookmarks are gone unknowingly. Why?
<p>Sorry about that! Our developers are updating this app regularly, so some POIs may have been deleted. When they no longer exist, they disappear from your Bookmarks too.</p>
<hr>

### How to quit the application safely?
<p>Click <b>Exit</b> from the main menu, or just hit <b>[X]</b> on top of the window.</p>
<hr>

### I am a developer. Can I add / edit / delete POIs or browse floor maps the same way?
<p>Yes, you can. Here's a few tips for developers:</p>
<ol>
<li>Select <b>More</b> – <b>Developer Tool</b> (or hit <code>Ctrl + X</code>) and enter the correct security key to activate Development Mode.</li>
<li>You can exit Developer Tool by hitting <b>Logout</b>; you can also click <b>Exit</b> (or <b>[X]</b>) to quit the program.</li>
<li>You can only add, edit, or remove built-in POIs. </li>
<li>POI Name must not be empty; Room Number must be a positive integer; Category must be one of the following (case- and whitespace-sensitive): "Classroom","CompSci Spot", "Restaurant", "Lab", "Stairwell", "Elevator", "Entrance", "Exit", and "Washroom". If a facility is accessible, write in POI Description: "Accessible facility." Other notes also go to Description. (POI descriptions are searchable.)</li>
<li>You can still enter Discovery Mode or take advantage of the search bar and <i>building + floor code</i> shortcut (refer to the <b>MC2F</b> example above) to remain on a certain floor.</li>
<li>Developers cannot view the user's bookmarks, search queries, or any non-built-in POIs.</li>
<li>If you forget your security key, check <code>./data/security_key</code> or shoot us an email at <a href="mailto:jason@shew.cc">jason@shew.cc</a>.</li>
</ol>
<hr>

### I still need help!
<p>If you need further help or have spotted incorrect information, feel free to write us: <a href="mailto:jason@shew.cc">jason@shew.cc</a>.</p>
<hr>

## Developers

(in alphabetic order)

* Arjuna Kadirgamar
* Daniel Gomes
* Jason Shew
* Joshua Cini
* Robert Beemer
## Sources of Visuals

### Icons

* Bookmark – from icons8.com
* Classroom – by Google Classroom
* Lab – from freesvg.org
* CompSci Spot – by 3Majors
* Restaurant – from flaticon.com
* Stairwell / Elevator – from emojipedia.org
* Entrance / Exit – by freepik.com
* My Location – from pngkit.com
* Washroom – from flaticon.com
* Accessibility – from pngitem.com
* Help – from flaticon.com
* Sorry – from flaticon.com
* No Internet - from uxwing.com 
* BuddyBuilding Icon – by Jason Shew

### Background Images

* Middlesex College – from Media Relations at UWO 
* Kresge Building – by Flickr user tpirie
* Physics & Astronomy Building – from Wikipedia


