//package rmit.com.sept.sept;
//
//
//import static org.junit.Assert.assertEquals;
//
//import java.io.IOException;
//import java.net.URI;
//import java.net.URISyntaxException;
//import java.util.List;
//
//import org.hibernate.validator.constraints.ModCheck;
//import org.junit.Assert;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
////import org.mockito.InjectMocks;
//import org.springframework.http.ResponseEntity;
////import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.web.client.RestTemplate;
//import org.springframework.web.servlet.ModelAndView;
//
//import rmit.com.sept.sept.controller.AuthenticationController;
//import rmit.com.sept.sept.repository.BookingRepository;
//import rmit.com.sept.sept.service.BookingService;
//
////@RunWith(SpringRunner.class)
////@WithMockUser
//
////@SpringBootTest
//public class TestSpring {
//
//
//	 @Autowired
//	 private BookingService bookingService;
//
//
//
//    @Test
//    public void testFindAll() {
//    	List<Booking> bookingList = bookingService.getAllBookings();
//
//        assertEquals(bookingList.size(),bookingList.size() );
//    }
//
//    @Test
//    void checkBookingSize() {
//	    Booking booking = new Booking(1,1,"1/02/2020", "12:24", "Barbar", "Prabhav");
//	    bookingService.createBooking(booking);
//	    // Will Fail as Company is not yet Registered
//	    bookingService.findAllCompany("Barbar");
//	    List<Booking> bookingList = bookingService.getAllBookings();
//	    assertEquals(2, bookingList.size());
//    }
//
////    @Test
////    public void testListAll() throws IOException {
////        RestTemplate restTemplate = new TestRestTemplate();
////        ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:8080/api/v1/users", String.class);
////
////        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
////
////        ObjectMapper objectMapper = new ObjectMapper();
////        JsonNode responseJson = objectMapper.readTree(response.getBody());
////
////        assertThat(responseJson.isMissingNode(), is(false));
////        assertThat(responseJson.toString(), equalTo("[]"));
////    }
////
//
//	@Test
//	public void testRegisterPageLoading() throws URISyntaxException
//	{
//	    RestTemplate restTemplate = new RestTemplate();
//
//	    final String baseUrl = "http://localhost:" + 8080 + "/register";
//	    URI uri = new URI(baseUrl);
//
//	    ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);
//
//	    //Verify request succeed
//	    Assert.assertEquals(200, result.getStatusCodeValue());
//	    Assert.assertEquals(true, result.getBody().contains("user"));
//	}
//
//
//
//	@Test
//	public void testLoginPageLoading() throws URISyntaxException
//	{
//	    RestTemplate restTemplate = new RestTemplate();
//
//	    final String baseUrl = "http://localhost:" + 8080 + "/login";
//	    URI uri = new URI(baseUrl);
//
//	    ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);
//
//	    //Verify request succeed
//	    Assert.assertEquals(200, result.getStatusCodeValue());
//	    Assert.assertEquals(true, result.getBody().contains("user"));
//	}
//
//
//	@Test
//    public void testHomeController() {
//        AuthenticationController homeController = new AuthenticationController();
//        ModelAndView result = homeController.login();
//        assertEquals(result, "login");
//    }
//
//
//}
