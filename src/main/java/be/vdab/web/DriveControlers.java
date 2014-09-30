package be.vdab.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.social.google.api.Google;
import org.springframework.social.google.api.drive.DriveFile;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Controller
@RequestMapping("/drive")
public class DriveControlers {
	Google google;
	
	private String apikey;/* = "AIzaSyDQ-l7yV5HPowepjn3DFKvkYsSs9UOUYxE";*/
	
	@Autowired
	public DriveControlers(@Value("${google.apikey}")String apikey,Google google) {
		this.google = google;
		this.apikey = apikey;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getFiles(){
		return new ModelAndView("/drive/drive").addObject("accestoken", google.getAccessToken());
	}
	
	@RequestMapping(value="/findall", method = RequestMethod.POST ,consumes = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public void findAll(@RequestBody List<String> files){
		List<DriveFile> driveFiles = new ArrayList<DriveFile>();
		for (String string : files) {
			driveFiles.add(google.driveOperations().getFile(string));
		}
		for (DriveFile driveFile : driveFiles) {
			System.out.println(driveFile.getMimeType());
		}
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/apikey")
	@ResponseBody
	public ObjectNode getApikey(){
		ObjectNode node = JsonNodeFactory.instance.objectNode();
		node.put("apikey", apikey);
		node.put("token", google.getAccessToken());
		return node;
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/upload")
	public ModelAndView upload(){
		return new ModelAndView("/drive/upload");
	}
}
