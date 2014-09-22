package be.vdab.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.google.api.Google;
import org.springframework.social.google.api.drive.DriveFile;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/drive")
public class DriveControlers {
	Google google;
	
	@Autowired
	public DriveControlers(Google google) {
		this.google = google;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getFiles(){
		google.driveOperations().createFolder("root", "een nieuwe folder");
		System.out.println(google.getAccessToken());
		return new ModelAndView("/drive/drive").addObject("accestoken", google.getAccessToken());
	}
	
	@RequestMapping(value="/findall", method = RequestMethod.GET)
	public void findAll(@RequestBody List<DriveFile> files){
		for (DriveFile driveFile : files) {
			System.out.println(driveFile.getTitle());
		}
	}
}
