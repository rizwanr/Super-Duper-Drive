package com.udacity.jwdnd.course1.cloudstorage.controller;


import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.EncryptionService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("credential")
public class CredentialController {


    private CredentialService credentialService;
    private UserService userService;
    private EncryptionService  encryptionService;

    public CredentialController(CredentialService credentialService, UserService userService, EncryptionService encryptionService) {
        this.credentialService = credentialService;
        this.userService = userService;
        this.encryptionService = encryptionService;
    }



    @GetMapping
    public String getHome(Authentication auth, @ModelAttribute("credentials") Credential credential, @ModelAttribute("encryptionService") EncryptionService  encryptionService,
                          Model model) {
        User user = userService.getUser(auth.getName());

        model.addAttribute("credentials", this.credentialService.getCredentials(user.getUserId()));
        model.addAttribute("encryptionService", this.encryptionService);
        return "redirect:/home";
    };



    @PostMapping("/add-update-credential")
    public String postNewCredential(Authentication auth, @ModelAttribute("credentials") Credential credential,@ModelAttribute("encryptionService") EncryptionService  encryptionService, Model model){
        String username= auth.getName();
        Integer userId = userService.getUser(username).getUserId();
        Integer credentialId = credential.getCredentialId();
        String newUrl = credential.getUrl();
        String newUsername = credential.getUsername();
        String newPassword = credential.getPassword();
        credential.setKey("Jj4lnbHeFj");
        String encryptedPassword = encryptionService.encryptValue(newPassword,credential.getKey());
        if (credentialId == null){
            credentialService.addCredentials(newUrl,newUsername,credential.getKey() ,encryptedPassword, userId);
        }
        else{
            credentialService.modifyCredential(credentialId,newUrl, newUsername, encryptedPassword);
        }
        model.addAttribute("credentials", credentialService.getCredentials(userId));
        return "redirect:/home";
    }


    @GetMapping("/delete-credential/{credentialId}")
    public String removeCredential(@PathVariable(value = "credentialId") Integer credentialId, Authentication auth,  Model model){
        credentialService.deleteCredential(credentialId);
        return "redirect:/home";

    };






}
