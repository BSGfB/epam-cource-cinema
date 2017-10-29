package com.epam.cinema.web.html;

import com.epam.cinema.model.Event;
import com.epam.cinema.model.User;
import com.epam.cinema.service.EventService;
import com.epam.cinema.service.UserService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/upload")
public class UploadFileController {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private UserService userService;

    @Autowired
    private EventService eventService;

    public UploadFileController() {
        objectMapper.configure(DeserializationFeature.FAIL_ON_MISSING_CREATOR_PROPERTIES, false);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
        objectMapper.findAndRegisterModules();
    }

    @GetMapping({"", "/"})
    public ModelAndView index() {
        ModelAndView index = new ModelAndView("index");
        index.addObject("upload", "upload");
        return index;
    }

    @PostMapping("/files")
    public String singleFileUpload(@RequestParam("files") MultipartFile[] files, RedirectAttributes redirectAttributes) {

        Arrays.stream(files).forEach(multipartFile -> {
            if (multipartFile.getOriginalFilename().toUpperCase().contains("USER")) {
                List<User> users = null;
                try {
                    users = objectMapper.readValue(multipartFile.getBytes(), new TypeReference<List<User>>(){});
                    users.forEach(userService::save);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                System.out.println("Loaded users:");
                users.forEach(System.out::println);
            } else if (multipartFile.getOriginalFilename().toUpperCase().contains("EVENT")) {
                List<Event> events = null;
                try {
                    events = objectMapper.readValue(multipartFile.getBytes(), new TypeReference<List<Event>>(){});
                    events.forEach(eventService::save);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                System.out.println("Loaded events:");
                events.forEach(System.out::println);
            }
        });
        return "redirect:/";
    }
}
