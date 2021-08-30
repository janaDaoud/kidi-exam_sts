package com.example.demo.pckg1;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;




@RestController
public class KidiController {
	
	@Autowired
	Parent_repository repoParent; 
	@Autowired
	CourseRepository courseRepo;
	
	@Autowired
	KidRepository kidRepo;
	@Autowired
	CategoryRepository categoryRepo;
	@Autowired
	MeetingRepository meetingRepo;
//	@Autowired
	//test1 testt; 
	long DAY_IN_MS = 1000 * 60 * 60 * 24;
	
	@PostMapping("/addNewParent")
	public Parent addNewParent (@RequestBody Parent parent){
		return repoParent.addNewParent(parent);
	}
	
	@GetMapping("/getAllUsers")
	public List <Parent> getAllparents (){
		return  repoParent.getAllParents();
	}
	
	@GetMapping ("/getAllActiveUsers")
	public List<Parent> getAllActiveParents(){
		return repoParent.getAllActiveparents();
	}
	@GetMapping("/getSpecificParent/{email}/{password}")
	public Parent getSpecificParent (@PathVariable String email,@PathVariable String password) {
		return repoParent.getSpecificParent(email, password);
	}
	
/*	@PutMapping("/addNewKid/{email}")
	public Parent addNewKid (@PathVariable String email,@RequestBody Kid kid) {
		return repoParent.addNewKid(email, kid);
		
	}*/
	@GetMapping ("/GetAllKidsOfParent/{id}")
	public List<Kid> GetAllKidsOfParent (@PathVariable String id) {
		return repoParent.GetAllKidsOfParent(id);
	}
	@PutMapping("/changeEmail/{id}/{newEmail}")
	public Parent changeEmail (@PathVariable String id,@PathVariable String newEmail) {
		return repoParent.changeEmail(id, newEmail);
	}
	@DeleteMapping ("/deleteParent/{id}")
	public List<Parent> deleteParent (@PathVariable String id){
		return repoParent.deleteParent(id);
	}
	@Autowired
	IkidRepository kRep;
	//----------------- KID -----------------
	@PostMapping("/initiateRepository")
	public ResponseEntity<String> intiate() {
		
		
		
		 String[] firstNames = {"Dina", "Tasneem", "Walaa", "Guy", "Oliver",
				"Jay", "Yuval", "Eli", "Nastia", "Asad", "Abed", "sheli", "Maya", "Or"};
		 String[] lastNames = {"Dina", "Tasneem", "Walaa", "Guy", "Oliver",
					"Jay", "Yuval", "Eli", "Nastia", "Asad", "Abed", "sheli", "Maya", "Or"};
		String[] category = {"Draw", "Space", "Art", "Math", "Sport"};
	
       String[] gmail ={ "Aaron@gmail.com",
               "Shadow@gmail.com",
               "Usman@gmail.com",  "Owen@gmail.com",
               "Pablo@gmail.com","Pardeepraj@gmail.com"};
       String[] pass ={ "Aaron12!",
               "Shadow14!",
               "Usman15!",  "Owen@17!",
               "Pablo18!","Pardee19!"};
       
		String[] s=courseRepo.getAllCoursesIDs();
		Date d=new Date();
		Date[] dates= new Date[40];
		for(int i=0;i<40;i++) {
			int j =(int)(Math.random() * 40);
			dates[i]=new Date(d.getTime() - j*(DAY_IN_MS));
		}
		
		for (int i=0;i<40;i++){
	          int j = (int)(Math.random() * 10);
	          Parent p = new Parent(firstNames[j],lastNames[j],gmail[j],pass[j]);
	          p.setActiveDate(dates[j]);
	          repoParent.addNewParent(p);
	    }
		
		for (int i=0;i<100;i++){
	          int j = (int)(Math.random() * 14);
	          Kid p = new Kid(firstNames[j],dates[j],Gender.NotRelevant );
	          kidRepo.addKid(p);
	    }
		
		
		for(int i=0;i<150;i++) {
			int j =(int)(Math.random() * 40);
			Meeting m = new Meeting(s[j],dates[j]);
			meetingRepo.addNewMeeting(m);
		}
		
		
		for(Kid k : lstKids) {
			  int j = (int)(Math.random() * 40);
			  kidRepo.addCourseToKid(k.getId(), s[j]);
		}
		
		
		return new ResponseEntity<>("initiateRepository", HttpStatus.OK);	
	}
	
	
	@PostMapping("/addcoursetokids")
	public int addcoursetokids() {
		return kidRepo.addCoursetokids();
	}
	
	
	
	@PostMapping("/AddNewKid")
	public Kid addNewKid(@RequestBody Kid kid) {
		return kidRepo.addNewKid(kid);
	}
	
	@GetMapping("/getAllKids")
	public ArrayList<Kid> getAllKids(){
		return kidRepo.getAllKids();
	}
	
	@DeleteMapping("/clearContent")
	public void clearContent() {
		kidRepo.clearAllDocuments();
	}
	
	@GetMapping("/getparentsnumber")
	public int getparentsnumber(){
		return repoParent.getAllActiveParentsNumber();
	}
	
	@GetMapping("/getWeaklyNewparentsnumber")
	public int getWeaklyNewparentsnumber(){
		return repoParent.getWeaklyNewParentsNumber();
	}
	
	
	
	@GetMapping("/getMonthlyNewparentsnumber")
	public int getMonthlyNewparentsnumber(){
		return repoParent.getMonthlyNewParentsNumber();
	}
	
	@GetMapping("/getYearlyNewparentsnumber")
	public int getYearlyNewparentsnumber(){
		return repoParent.getYearlyNewParentsNumber();
	}
	
	
	@GetMapping("/getweaklynewkidsnumber")
	public int getweaklynewkidsnumber(){
		return kidRepo.getWeaklyNewKids();
	}
	
	
	@GetMapping("/getmonthlynewkidsnumber")
	public int getmonthlynewkidsnumber(){
		return kidRepo.getMonthlyNewKids();
	}
	
	
	
	
	
	@GetMapping("/getyearlynewkidsnumber")
	public int getyearlynewkidsnumber(){
		return kidRepo.getYearlyNewKids();
	}
	
	@GetMapping("/getactivieparentsnumber")
	public int getactivieparentsnumber(){
		return repoParent.getAllActiveParentsNumber();
	}
	@PostMapping("/setactivedate")
	public void setactivedate() {
		 kidRepo.setactiveDate();
	}
	
	 //*******************************************************************************jana
	@GetMapping("/getkidsactivenumber")
	public int getkidsactivenumber(){
		return kidRepo.getAllActiveKidsNumber();
	}
	//------------------------------- COURSE ----------------------------------------
	@PostMapping("addNewCourse")
	public List<Course> addCourse(Course course) {
		return courseRepo.addANewCourse(course);
	}
	
	
	//------------------------------- CATEGORY ----------------------------------------

	@PostMapping("addNewCategory")
	public List<Category> addNewCategory(Category category) {
		return categoryRepo.addCategory(category);
	}
	
	

	//-------------------------- FOR STATISTICS ----------------------------------------
	@GetMapping("/getNewKids/{period}")
	public HashMap<String,Integer> getNewKids(@PathVariable int period){
		return kidRepo.getNewKids(period);
	}
	@GetMapping("/getNewParents/{period}")
	public HashMap<String,Integer> getNewParents(@PathVariable int period){
		return repoParent.getNewParents(period);
	}
	
	@GetMapping("/getKidsCountByCategory/{period}")
	public HashMap<String,Integer> getKidsCountByCategory(@PathVariable int period){
		return categoryRepo.getKidsCountByCategory(period);
	}
	


	
	@GetMapping("/getcoursecategoryid/{id}")
	public String getcoursecategoryid(@PathVariable String id){
		return courseRepo.getCourseCategoryId(id);
	}
	
	
	@GetMapping("/getkidsnumberincategory/{id}")
	public int getkidsnumberincategory(@PathVariable String id){
		return kidRepo.membersInCategory(id);
	}
	
	

	
	
	
	
	
	@GetMapping("/checkifcourseactineinperiod/{id}/{period}")
	public Boolean checkifcourseactineinperiod(@PathVariable String id , @PathVariable int period){
		return courseRepo.checkIfCourseIsActive(id , period);
	}
	
	
	
	

	//***************************************************************************************************************************************
	

	
	@GetMapping("/getpercentofnewkids/{period}")
	public double getpercentofnewkids(@PathVariable int period){
		return kidRepo.getPercentOfNewKids(period);
	}
	
	@GetMapping("/getpercentofnewparents/{period}")
	public double getpercentofnewparents(@PathVariable int period){
		return repoParent.getPercentOfNewParents(period);
	}
	
	@GetMapping("/getkidsnumincategory/{d1}/{d2}")
	public HashMap<String, Integer> getkidsnumincategory(@PathVariable Date d1 , @PathVariable Date d2){
		return kidRepo.getkidsNumInCategory(d1,d2);
	}
	
	
	@GetMapping("/getcategorycourses/{cattid}")
	public ArrayList<Course> getcategorycourses(@PathVariable String cattid){
		return categoryRepo.getCategoryCourses(cattid);
	}
	
	
	
	/*@GetMapping("/getkidsnumincategoryperperiod/{period}")
	public HashMap<String, Integer> getkidsnumincategoryperperiod(@PathVariable int period){
		return kidRepo.getkidsNumInCategoryPerPeriod(period);
	}*/
	
	
	
	@GetMapping("/getcategoryweeklytrend")
	public HashMap<Category, HashMap<Integer, Integer>> getcategoryweeklytrend(){
		return kidRepo.getCategoryWeeklytrend();
			}
	
	
	@GetMapping("/getcategoryweaklytrend")
	public HashMap<Category, HashMap<Integer, Integer>> getcategoryweaklytrend(){
		return kidRepo.getCategoryWeeklyKidsNumber();
			}
	
	@GetMapping("/getcategoryyearlytrend")
	public HashMap<Category, HashMap<Integer, Integer>> getcategoryyearlytrend(){
		return kidRepo.getCategoryYearlytrend();
			}
	
	
	
	@GetMapping("/getcategorymonthlytrend")
	public HashMap<Category, HashMap<Integer, Integer>> getcategorymonthlytrend(){
		return kidRepo.getCategoryMonthlytrend();
			}
	
	
	
	
	@GetMapping("/getallmeetingsnumbers")
	public int getallmeetingsnumbers(){
		return meetingRepo.getAllMeetingsNumbers();
			}
	
	
	@GetMapping("/getallmeetingsperperiod/{period}")
	public double getallmeetingsperperiod(@PathVariable int period){
		return meetingRepo.getAllMeetingsPerPeriod(period);
			}
	
	
	@GetMapping("/gethappenedmeetingsnumberperperiod/{period}")
	public double gethappenedmeetingsnumberperperiod(@PathVariable int period){
		return meetingRepo.getHappenedMeetingsNumberPerPeriod(period);
			}
	@GetMapping("/getmeetingpercent/{period}")
	public double getmeetingpercent(@PathVariable int period){
		return meetingRepo.getMeetingPercent(period);
			}
	
	
	/*@GetMapping("/getnewkidsnumpercategory/{period}")
	public int getnewkidsnumpercategory(@PathVariable int period){
		return kidRepo.getNewKidsNumPerPeriod(period);
			}*/
	
	@GetMapping("/getnewparentsnumpercategory/{period}")
	public int getnewparentsnumpercategory(@PathVariable int period){
		return repoParent.getNewParentsNumPerPeriod(period);
			}
//**************************************************************************************************************
	@GetMapping("/kidschart/{period}")
	public double[] kidschart(@PathVariable int period){
		return kidRepo.kidsChart(period);
			}
	
	@GetMapping("/parentschart/{period}")
	public double[] parentschart(@PathVariable int period){
		return repoParent.parentsChart(period);
			}
	
	@GetMapping("/meetingrepo/{period}")
	public double[] meetingrepo(@PathVariable int period){
		return meetingRepo.meetingsChart(period);
			}
	
	@GetMapping("/getkidsnumincategoryperperiod/{period}")
	public 	HashMap<String, Integer> getkidsnumincategoryperperiod(@PathVariable int period){
		return kidRepo.getkidsNumInCategoryPerPeriod(period);
			}
	

	@GetMapping("/barchartresults/{period}")
	public 	HashMap<Category, HashMap<Integer, Integer>> barchartresults(@PathVariable int period){
		return kidRepo.barChartResults(period);
			}

	
	
}