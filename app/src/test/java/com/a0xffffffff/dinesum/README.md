# DineSum 


We implemented JUnit test cases for our four main back-end classes: User, Request, RequestTracker, and FirebaseManager. We have described our test classes and specific test cases below. These test case descriptions can also be found inside of the README.md found inside of our test folder.

---

## UserTest
The User class is responsible for containing all requests and reservations associated with the User. The functionality of the User class is important for the application’s UI.


### testUserCreatedSuccessfully()
This test checks if a User object is successfully initialized and does not contain any requests or reservations upon creation.

### testRequestAddedRemovedSuccessfully()
This test checks if the User object can successfully add and remove Request objects from its list of requests associated with that user.

### testReservationAddedRemovedSuccessfully()
This test checks if the User object can successfully add and remove Request objects from its list of reservations associated with that user.

---
## RequestTest
Request objects contain all of the information associated with requests and reservations. Our ability to read, write, and copy Request objects is important to the functionality of the application’s UI and database.


### testRequestCreatedSuccessfully()
This test checks if a Request object is successfully initialized with the expected information. The object should not be null.

### testRequestID()
This test checks if the Request ID string can be written and read in Request objects.

### testRequesterID()
Checks if the Requester ID (the ID of the user who created the Request) can be written and read.

### testReserverID()
Checks if the Reserver ID (the ID of the user who claimed the Request) can be written and read.

### testRequestState()
Checks if the Request State transitions properly upon being claimed, completed, and paid.

### testRequestData()
Checks if the Request Data object can be written and read.

---
## RequestTrackerTest
The RequestTracker class is responsible for storing all data received from the Firebase Realtime Database. It is important that RequestTracker can be written and read for the application UI to display Request objects in the request feed.


### testNearbyRequestsList()
This test checks if Request objects can be added and removed from the Nearby Requests List if the location is the same as the user.

### testAllRequestsList()
This test checks if Request objects can be added and removed from the All Requests List

### testUserRequestsList()
This test checks if Request objects belonging to the user can be added and removed from the RequestTracker.

### testUserReservationsList()
This test checks if Request objects claimed by the user can be added and removed from the RequestTracker.

### testFilterAllRequestsList()
Checks if the RequestTracker object can filter the All Requests List and return only the Request objects with the specified location. 

---
## FirebaseManagerTest
Since FirebaseManager acts as the interface between our application and the Firebase Realtime Database, its core functionality is to be able to read and write data from our application to the database. The following test cases evaluate whether nearby requests and user created requests can be written and read by event listeners correctly.


### testUserWriteRequestSuccessfully()
This test checks if a request created by the user is successfully written to Firebase and if the event listener for the requests created by the current user correctly returns the list of this user's created requests.

### testNearbyWriteRequestSuccessfully()
This test checks if a nearby request (in the same city as the user) is successfully written to Firebase and if the event listener for the nearby requests correctly returns this user's nearby requests.

### testFirebaseKeyGeneration()
Check if Firebase returns string ID for requests

