# *Simplified* Car Rental Application

### *Project made by Okasha Huda*


- **What will the application do?**
  - this application will be a simple car rental application. user will have the option  
  to select a car to rent, choose when to pick up the car and when to drop off the car.  
  the user will then be able to save the booking and come back to it to make any  
  changes.


- **Who will use it?**
  - this project is made for fun, but if it were to be scaled, then potentially large or small  
  rental car companies can implement a similar design. the idea can also be manipulated  
  to incorporate hotels or flights and if scaled even further can turn into vacation booking  
  application.


- **Why is this project of interest to you?**
  - I like the idea of this project because I just have an interest in cars and driving cars in  
  general. other than that, this project is a good way to showcase what I have learned.

# User Stories

- as a user, I want to be able to create a new booking for a rental car (arbitrary number of times)
- as a user, I want to be able to edit my current booking
- as a user, I want to be able to cancel my current booking
- as a user, I want to be able to view the details of my booking
- as a user, I want to be able to save my bookings and have the option to do or not*
- as a user, I want to be able to load my bookings from file and have the option to do or not*

**the bulk of saving/loading data from a file is adapted from the sample application given in the 
JsonSerializationDemo*

*phase 3 of the project required the use of external sources:*
- https://docs.oracle.com/javase/tutorial/uiswing/layout/gridbag.html
- https://www.guru99.com/java-swing-gui.html
- https://www.javatpoint.com/java-jcombobox
- https://www.youtube.com/watch?v=5o3fMLPY7qY
- https://www.youtube.com/watch?v=ywLKpHw1MjQ
- https://docs.oracle.com/javase/tutorial/uiswing/components/list.html (specifically ListDemo.java)
- https://www.reddit.com/r/ProgrammerHumor/comments/124tawf/starbucks_intern_hard_at_work/ (img requirement used when 
'Add' button clicked)
- https://stackoverflow.com/questions/18027833/adding-image-to-jframe

# Instructions for Grader

- You can generate the first required action related to adding Xs to a Y by filling out all the fields on the window 
then clicking the 'Add' button
- You can generate the second required action related to adding Xs to a Y by selecting an entry from the left panel and 
clicking the 'Cancel' button
- You can locate my visual component by filling out all fields, clicking 'Add' which will open a pop-up window with an 
image
- You can save the state of my application by clicking the 'Save' button which will write all the data to the 
booking.json file
- You can reload the state of my application by clicking the 'Load' button (upon restart) which will read all the data 
from the booking.json file and add to the panel on the left

## Phase 4: Task 2
Wed Apr 12 21:04:31 PDT 2023 </br>
ALL BOOKINGS CLEARED.

Wed Apr 12 21:04:31 PDT 2023 </br>
BOOKING ADDED.

Wed Apr 12 21:04:31 PDT 2023 </br>
BOOKING ADDED.

Wed Apr 12 21:04:35 PDT 2023 </br>
BOOKING REMOVED.

## Phase 4: Task 3
Currently, my project has two different ways to store the rental. The GUI stores the rentals slightly differently than 
the console-based UI. When refactoring, that would likely be the first thing that I work on. I would refactor this 
because that would simplify the code significantly and I were to ever scale this application in the future, it would be 
easier. Additionally, there are a few instances of code duplication in my project (like in Booking and Rental). This can 
be solved by using abstract class and/or interfaces. I would refactor this because it would make debugging SO much 
easier. I experienced this while creating this project, there would be a bug, but it would take longer to debug because 
IntelliJ says that the bug is in one class, but it's talking to a second class that's implementing a method incorrectly, 
thus leading to the bug. If it were an abstract class or interface, it would be simpler to debug and easier to read.