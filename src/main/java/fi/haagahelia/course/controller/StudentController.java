package fi.haagahelia.course.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import fi.haagahelia.course.model.Student;
import fi.haagahelia.course.repository.StudentRepository;

@Controller
public class StudentController {
	@Autowired
    private StudentRepository repository; 
	
	@RequestMapping("/")
	public String index(Model model) {
		 List<Student> students = (List<Student>) repository.findAll();
		
		model.addAttribute("students", students);
        model.addAttribute("newstudent", new Student());
    	return "index";
    }

    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(Student student){
        repository.save(student);
    	return "redirect:/";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String editStudent(@RequestParam("firstName") String firstname , @RequestParam("lastName") String lastname , Model model) {
        System.out.println(firstname + lastname);
        return "redirect:/";
    }
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteStudent(@PathVariable("id") Long studentId, Model model) {
    	repository.delete(studentId);
        return "redirect:/";
    }    
}
