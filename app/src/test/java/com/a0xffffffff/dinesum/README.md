# DineSum 


We implemented JUnit test cases for our four main back-end classes: User, UserTracker, Request, RequestTracker. We have described our test classes and specific test cases below.

---
## RequestTest
Request objects contain all of the information associated with requests and reservations. Our ability to read, write, and copy Request objects is important to the functionality of the application’s UI and database.

### setup()
Initialize request objects to be used in tests.

### testRequestCreatedSuccessfully()
Checks if a Request object is successfully initialized with the expected information. The object should not be null.

### testRequestID()
Checks if the Request ID string can be written and read in Request objects.

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

### setup()
Initialize lists of Request objects to be used in tests.

### testNearbyRequestsList()
Checks if Request objects can be added and removed from the Nearby Requests List if the location is the same as the user.

### testAllRequestsList()
Checks if Request objects can be added and removed from the All Requests List

### testUserRequestsList()
Checks if Request objects belonging to the user can be added and removed from the RequestTracker.

### testUserReservationsList()
Checks if Request objects claimed by the user can be added and removed from the RequestTracker.

### teardown()
Reset the lists of Request objects to empty lists to prepare for other tests.

---
## UserTest
The User class is responsible for containing User ID's and Points.

### setup()
Create a new default instance of the User class for use in other tests.

### testUserCreatedSuccessfully()
Check that the new default instance was successfully created. Check the point value fo the default instance is correct.

### testUserIDSetSuccessfully()
Check that the User ID of the User is set and retrieved successfully.

### testUserPointsSetSuccessfully()
Check that the User Points of the User is set and retrieved successfully.

---
## UserTrackerTest
The UserTracker class is responsible for keeping track of Users' points for displaying of ratings in request info pages.

### setup()
Initialize lists of User objects to be used in tests.

### testAllUsersList()
Checks that the list of Users can be set and retrieved successfully.

### testGetUserPoints()
Checks that the UserTracker can return the points associated with a specific User ID.

### testUpdateUserPoints()
Checks that the UserTracker can successfully update the points associated with a specific User ID.

### teardown()
Reset the lists of User objects to empty lists to prepare for other tests.
