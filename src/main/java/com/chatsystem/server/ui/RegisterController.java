package com.chatsystem.server.ui;


import com.chatsystem.server.service.ChatEndpointService;
import com.chatsystem.server.service.UserService;
import com.chatsystem.server.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import java.io.IOException;


/**
 * Данный контроллер обрабатывает запросы связанные с регистрацией пользователя
 */
@Controller
public class RegisterController {


    private final UserService userService;

    private final ChatEndpointService chatEndpointService;


    @Autowired
    public RegisterController(final UserService userService, ChatEndpointService chatEndpointService) {

        this.userService = userService;
        this.chatEndpointService = chatEndpointService;
    }



    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public ModelAndView getRegistrationForm() {
        return new ModelAndView("registration", "user", new User());
    }


    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ModelAndView doDeveloperRegistration(@ModelAttribute("user") User user,
                                                //@RequestParam("file") MultipartFile file,
                                                @RequestParam("userRole") String userRole) {
        ModelAndView modelAndView = new ModelAndView("registration");
        User savedUser = userService.findByEmail(user.getEmail());
        if (savedUser != null) {
            modelAndView.addObject("alreadyRegisteredMessage","Oops!  There is already a user registered with the email provided.");
            modelAndView.addObject("user", new User());
        }else{
            switch (userRole){
                case UserRole.MANAGER: savedUser = new Manager(); savedUser.setUserStatus(UserStatus.ENABLED); break;
                case UserRole.BUYER:
                default: savedUser = new Buyer(); savedUser.setUserStatus(UserStatus.DISABLED);
            }
//            if (!file.isEmpty()) {
//                try {
//                    savedUser.setAvatar(file.getBytes());
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
            savedUser.setUsername(user.getUsername());
            savedUser.setEmail(user.getEmail());
            savedUser.setPassword(user.getPassword());
            userService.add(savedUser);
            modelAndView.addObject("confirmationMessage","You has been registered successfully.");
            if(userRole.equals(UserRole.BUYER)){
                chatEndpointService.sendNewBuyerDataToManagers(savedUser.getUsername());
            }
        }
        return modelAndView;
    }

}