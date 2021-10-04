package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.Page.*;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.EncryptionService;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CloudStorageApplicationTests {

	private CredentialsPage credentialsPage;
	private WebDriver driver;




	@Autowired
	private CredentialService credentialService;

	@Autowired
	private EncryptionService encryptionService;

	@LocalServerPort
	private Integer Port;
	private String Base_URL="http://localhost:";



	@BeforeAll
	static void beforeAll() {
		WebDriverManager.chromedriver().setup();
	}

	@BeforeEach
	public void beforeEach() {
		this.driver = new ChromeDriver();
	}

	@AfterEach
	public void afterEach() {
		if (this.driver != null) {
			driver.quit();
		}
	}




	@Test
	@Order(2)
	public void login() throws InterruptedException {
		String username = "renesa";
		String password ="rjcjehakfur";
		driver.get(Base_URL + this.Port + "/login");
		LoginPage loginPage = new LoginPage(driver);
		loginPage.login(driver,username,password);
		Thread.sleep(2000);
		Assertions.assertEquals("Home", driver.getTitle());
	}

	@Test
	@Order(1)
	public void testUserSignupAndLogin(){
		String firstName = "Rizwan";
		String lastName = "Renesa";
		String username = "renesa";
		String password ="rjcjehakfur";
		driver.get(Base_URL + this.Port + "/signup");
		SignupPage signupPage = new SignupPage(driver);
		signupPage.signup(firstName,lastName,username,password);
		signupPage.navigateToLoginPage();

		LoginPage loginPage = new LoginPage(driver);
		loginPage.login(driver,username,password);
		Assertions.assertEquals("Home", driver.getTitle());


	}






	@Test
	@Order(3)
	public void testUserLogout() throws InterruptedException {
		login();
		HomePage homepage= new HomePage(driver);
		homepage.logout();
		Assertions.assertEquals("Login", driver.getTitle());

	}

	@Test
	@Order(4)
	public void preventUnauthorizedAccess() throws InterruptedException {
		driver.get(Base_URL + this.Port + "/home");
		Assertions.assertEquals("Login", driver.getTitle());
		driver.get(Base_URL + this.Port + "/signup");
		Assertions.assertEquals("Sign Up", driver.getTitle());
	}





	@Test
	@Order(5)
	public void testAddingNewNote() throws InterruptedException {

		String title="Test";
		String description="efwfdsfs";
		login();
		NotesPage notesPage = new NotesPage(driver);
		notesPage.addNewNote(driver,title,description);
		Assertions.assertEquals("Result", driver.getTitle());

	}

	@Test
	@Order(6)
	public void deleteANote() throws InterruptedException {

		testAddingNewNote();
		NotesPage notesPage = new NotesPage(driver);
		notesPage.deleteNote(driver);
		Assertions.assertEquals("Result", driver.getTitle());


	}

	@Test
	@Order(7)
	public void modifyNote() throws InterruptedException {

		String editedTitle ="EditedTest";
		String editedDescription = "EditedDescription";
		testAddingNewNote();
		NotesPage notesPage = new NotesPage(driver);
		notesPage.editNote(driver,editedTitle,editedDescription);
		Note note = notesPage.getFirstNote(driver);
		Assertions.assertEquals(editedTitle,note.getNoteTitle());
		Assertions.assertEquals(editedDescription, note.getNoteDescription());

	}

	@Test
	@Order(8)
	public void createCredentialAndVerifyPasswordEncrypted() throws Exception {

		String url ="www.facebook.com";
		String username = "rizwan";
		String password ="testpassword";
		EncryptionService encryptionService = new EncryptionService();
		credentialsPage = new CredentialsPage(driver,credentialService,encryptionService);



		login();
		credentialsPage.addCredential(driver,url,username,password);
		Credential credential = credentialsPage.getFirstCredential(driver);
		String encryptedPassword =credential.getPassword();

		String passwordInDB= credentialsPage.getEncryptedPassword(username,credentialService);


		Assertions.assertEquals(encryptedPassword,passwordInDB);

	}

	@Test
	@Order(9)
	public void editACredentialAndVerifiesChangesDisplayed() throws Exception {


		String editedurl ="EditedTest";
		String editedusername = "EditedDescription";
		String editedpassword = "EditedPassword";
		login();

		CredentialsPage  credentialsPage  = new CredentialsPage(driver,credentialService,encryptionService);
		credentialsPage.editCredential(driver,editedurl,editedusername,editedpassword);
		Credential credential = credentialsPage.getFirstCredential(driver);
		String decryptedPassword= credentialsPage.getDecryptedPassword(editedusername,credentialService,encryptionService);
		Assertions.assertEquals(editedurl,credential.getUrl());
		Assertions.assertEquals(editedusername, credential.getUsername());
		Assertions.assertEquals(editedpassword,decryptedPassword);


	}

	@Test
	@Order(10)
	public void deleteACredential() throws Exception {
		login();
		CredentialsPage  credentialsPage = new CredentialsPage(driver,credentialService,encryptionService);
		credentialsPage.deleteCredential(driver);
		Assertions.assertEquals("Result", driver.getTitle());


	}

	//Write a test that views an existing set of credentials, verifies that the viewable password is unencrypted,
	// edits the credentials, and verifies that the changes are displayed.



}
