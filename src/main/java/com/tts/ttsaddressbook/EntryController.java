package com.tts.ttsaddressbook;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Component
@ComponentScan("com.tts.ttsaddressbook.EntryService")
@Controller
public class EntryController
{
    // Returns main page index when visiting the site
    @GetMapping
    public String index(Entry entry)
    {
        return "index";
    }

    // Returns add.html
    @RequestMapping("/add")
    public String add(Entry entry)
    {
        return "add";
    }

//    // Returns addresult.html
//    @RequestMapping("/addresult")
//    public String addresult(Entry entry)
//    {
//        return "addresult";
//    }
    
    // Returns remove.html
    @RequestMapping("/remove")
    public String remove(Entry entry)
    {
        return "remove";
    }

//    // Returns removeresult.html
//    @RequestMapping("/removeresult")
//    public String removeresult(Entry entry)
//    {
//        return "removeresult";
//    }
    
    // Returns delete.html
    @RequestMapping("/delete")
    public String delete(Entry entry)
    {
        return "delete";
    }
    
    @RequestMapping("/print")
    public String print(Entry entry)
    {
        return "print";
    }

}
