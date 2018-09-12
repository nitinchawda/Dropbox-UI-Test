DropBox Functionality Tests
	1. LoginTest.java
		. Login Functionality Test: "loginTest" --> User Credentials parameterized in testng.xml path -> /DropBoxArtifact/testng.xml
		What it does? Uses the credentials passed from the parameters from testng.xml and login to the DropBox Website
		. Logout Functionality Test: "logoutTest" --> User Credentials parameterized in testng.xml path -> /DropBoxArtifact/testng.xml
		What it does? Uses the credentials passed from the parameters from testng.xml and login to the DropBox Website. Logout of the DropBox
	2. FileUploadTest.java
		. File upload functionality Test: "fileUploadTest" --> User Credentials parameterized in testng.xml path -> /DropBoxArtifact/testng.xml
		What it does? Uploads a dummy file (/DropBoxArtifact/src/test/resources/dummy.pdf) for the given user into DropBox
		. File upload and Delete functionality test: "deletedUploadFileTest" --> User Credentials parameterized in testng.xml path -> /DropBoxArtifact/testng.xml
		What it does? Uploads a dummy file (/DropBoxArtifact/src/test/resources/dummy.pdf) for the given user into DropBox. Deletes the uploaded file and verifies in deleted files page
	3. CreateFolderTest.java
		. Create Folder Functionality Test: "createFolderTest" --> User Credentials parameterized in testng.xml path -> /DropBoxArtifact/testng.xml
		What it does? Creates a shared folder and shares among the given user details (Constant.java)

How to run:
Just run "maven clean install" from cmd prompt. 

Note: Tests will run in Chrome Browser. Required driver version is downloaded by Maven. Should be run on latest Chrome Version.

Report: It is UI report for test execution generated after the execution is complete. Please find the report under (/DropBoxArtifact/automationreport.html)

		