package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.Page.*;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.services.EncryptionService;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CloudStorageApplicationTests {



	@LocalServerPort
	private Integer Port;
	private String Base_URL="http://localhost:";

	private WebDriver driver;

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
	public void getLoginPage() {
		driver.get(Base_URL + this.Port + "/login");
		Assertions.assertEquals("Login", driver.getTitle());
	}

	@Test
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
		loginPage.login(username,password);
		Assertions.assertEquals("Home", driver.getTitle());


	}

	@Test
	public void testUserLogout(){
		testUserSignupAndLogin();
		HomePage homepage= new HomePage(driver);
		homepage.logout();
		Assertions.assertEquals("Login", driver.getTitle());

	}

	@Test
	public void preventUnauthorizedAccess(){
		driver.get(Base_URL + this.Port + "/home");
		Assertions.assertEquals("Login", driver.getTitle());
		driver.get(Base_URL + this.Port + "/signup");
		Assertions.assertEquals("Sign Up", driver.getTitle());
	}





	@Test
	public void testAddingNewNote() {
		String title="Test";
		String description="efwfdsfs";
		testUserSignupAndLogin();
		NotesPage notesPage = new NotesPage(driver);
		notesPage.addNewNote(driver,title,description);
		Assertions.assertEquals("Result", driver.getTitle());




	}

	@Test
	public void deleteANote() {
		testAddingNewNote();
		NotesPage notesPage = new NotesPage(driver);
		notesPage.deleteNote(driver);
		Assertions.assertEquals("Result", driver.getTitle());


	}

	@Test
	public void modifyNote() {
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
	public void createCredentialAndVerifyPasswordEncrypted() throws Exception {
		String url ="www.facebook.com";
		String username = "rizwan";
		String password ="testpassword";
		Integer userId=1;
		EncryptionService encryptionService = new EncryptionService();
		CredentialsPage credentialsPage = new CredentialsPage(driver);


		testUserSignupAndLogin();
		credentialsPage.addCredential(driver,url,username,password);
		Credential credential = credentialsPage.getFirstCredential(driver);
		String encryptedPassword =credential.getPassword();

		String passwordInDB= credentialsPage.getEncryptedPassword(1);

		Assertions.assertEquals(encryptedPassword,passwordInDB);

	}

	@Test
	public void deleteACredential() throws Exception {
		createCredentialAndVerifyPasswordEncrypted();
		CredentialsPage credentialsPage = new CredentialsPage(driver);
		credentialsPage.deleteCredential(driver);
		Assertions.assertEquals("Result", driver.getTitle());


	}

}
