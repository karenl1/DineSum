# DineSum 


We implemented JUnit integration test cases for the main functionalities of the application. We have described our test classes and specific test cases below. These tests will execute on an Android device.

---
## NewRequestCreationTest
Instrumentation test for creating new requests.

### createNewRequest()
Create a valid new request. A new request will be created with no errors and should appear in the Request Feed and User Requests List.

### createNewEndTimePastRequest()
Create an invalid new request with an End Time that has already passed. A new request will be created with no errors but should not appear in the Request Feed. It should appear User Requests List, and will be deleted after confirming its presence.

### createNewLocationOutsideRequest()
Create an invalid new request with a location outside of Los Angeles. A new request will be created with no errors but should not appear in the Request Feed. It should appear User Requests List, and will be deleted after confirming its presence.

---
## RequesterCompleteTest
Instrumentation tests for completing a request.

### completeRequest()
Complete a claimed request. A claimed request will be marked as completed with no errors. The database should be updated and the changes should be reflected in the Requests List.

---
## RequesterMarkAsPendingTest
Instrumentation tests for marking requests as "pending".

### markRequestAsPending()
Mark a claimed request as pending, removing the reserver from the request. A claimed request will be marked as pending with no errors. The database should be updated and the changes should be reflected in the Requests List. The Request Feed should also contain the request again since it has been returned to the pending state.

---
## RequesterRemoveTest
Instrumentation test for removing pending requests.

### removeRequest()
Remove a pending request from the database. A pending request will be removed with no errors. The database should be updated and the changes should be reflected in the Requests List and Request Feed. The request should no longer appear in either of the two lists.

---
## ReserverClaimTest
Instrumentation test for claiming new requests.

### claimRequest()
Claim a pending request from the Request Feed. A pending request will be claimed with no errors. The database should be updated and the changes should be reflected in the Reserver List. The Request Feed should not contain the request.

---
## ReserverMarkAsPaidTest
Instrumentation test for marking completed requests as paid.

### markRequestAsPaid()
Mark a completed request as paid. A completed request will be marked as paid with no errors. The database should be updated and the changes should be reflected in the Reserver List.

---
## ReserverUnclaimTest
Instrumentation test for unclaiming requests the user has previously claimed.

### unclaimRequest()
Unclaim a request the user has previously claimed. A claimed request will be marked as pending with no errors. The database should be updated and the changes should be reflected in Request Feed. The Request Feed should contain the request again since it has been returned to the pending state.
