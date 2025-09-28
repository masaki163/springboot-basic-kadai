

package com.example.springkadaiform.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.springkadaiform.form.ContactForm;


@Controller
public class ContactFormController {
	
	private static final String FORM_MODEL_KEY = "contactForm"; 
	
	@GetMapping("/form")
	public String form(Model model) { 
        
	    if (!model.containsAttribute(FORM_MODEL_KEY)) {
	        model.addAttribute(FORM_MODEL_KEY, new ContactForm());
	    }
	    
	    return "contactFormView";
	}
	
	@PostMapping("/confirm")
	 public String confirm(RedirectAttributes redirectAttributes,
	            @Validated ContactForm form, BindingResult result, Model model) {

		if (result.hasErrors()) {
            
            redirectAttributes.addFlashAttribute(FORM_MODEL_KEY, form);
            
       
            redirectAttributes.addFlashAttribute(
                BindingResult.MODEL_KEY_PREFIX + FORM_MODEL_KEY, 
                result);

            
            return "redirect:/form";
        }
		
		model.addAttribute(FORM_MODEL_KEY, form); 

        return "confirmView";
    }
}
