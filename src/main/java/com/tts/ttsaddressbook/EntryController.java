package com.tts.ttsaddressbook;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EntryController
{

    @Autowired
    private EntryRepository entryRepository;
    private EntryService entryService;

    @Autowired
    public void setEntryService(EntryService entryService)
    {
        this.entryService = entryService;
    }

    // Returns main page index when visiting the site
    @GetMapping
    public String index(Entry entry)
    {
        return "index";
    }

    @GetMapping(value = "/add")
    public String addEntry(Entry entry, Model model)
    {
        // Adds place holders to add.html form
        entry.setFirstName("John");
        entry.setLastName("Doe");
        entry.setPhoneNumber("555-555-5555");
        entry.setEmail("fake@email.com");

        return "add";
    }

    @PostMapping(value = "/addresult")
    public String displayForm(Entry entry, Model model)
    {
        // Creates a list of entries and stores entries with that email
        Iterable<Entry> entries = entryRepository.findAll();

        // Checks each entry in entries
        for(Entry i : entries)
        {
            // If email matches what is already in the list, return error page
            if(i.getEmail().equals(entry.getEmail()))
            {
                return "adderror";
            }
        }

        // Save a new entry in the repository
        entryRepository.save(new Entry(entry.getFirstName(), entry.getLastName(), entry.getPhoneNumber(), entry.getEmail(), entry.getEntryRecorded()));

        // Sends fields to addresult.html
        model.addAttribute("firstName", entry.getFirstName());
        model.addAttribute("lastName", entry.getLastName());
        model.addAttribute("phoneNumber", entry.getPhoneNumber());
        model.addAttribute("email", entry.getEmail());
        model.addAttribute("entryRecorded", entry.getEntryRecorded());

        return "addresult";
    }

    @GetMapping(value = "/remove")
    public String removeSubscriber(Entry entry, Model model)
    {
        // Adds place holder to remove.html
        entry.setEmail("fake@email.com");

        return "remove";
    }

    @PostMapping(value = "/removeresult")
    public String remove(Entry entry, Model model)
    {
        // Creates a list of entries and stores entries with that email
        List<Entry> entries = entryRepository.findByEmail(entry.getEmail());

        // If there are no entries in the list, return the error page
        if(entries.size() == 0)
        {
            return "error";
        }

        // Grab the first entry of that list
        Entry firstEntry = entries.get(0);
        // Delete the first entry
        entryRepository.delete(firstEntry);

        return "removeresult";
    }

    @GetMapping(value = "/search")
    public String search(Entry entry, Model model)
    {
        // Adds place holder to search.html
        entry.setEmail("fake@email.com");

        return "search";
    }

    @PostMapping(value = "/searchresult")
    public String searchResult(Entry entry, Model model)
    {
        // Creates a list of entries and stores entries with that email
        List<Entry> entries = entryRepository.findByEmail(entry.getEmail());

        // If there are no entries in the list, return the error page
        if(entries.size() == 0)
        {
            return "error";
        }

        // Grab the first entry of that list
        Entry firstEntry = entries.get(0);

        // Add the attributes of the first entry to output in searchresult.html
        model.addAttribute("firstName", firstEntry.getFirstName());
        model.addAttribute("lastName", firstEntry.getLastName());
        model.addAttribute("phoneNumber", firstEntry.getPhoneNumber());
        model.addAttribute("email", firstEntry.getEmail());

        return "searchresult";
    }

    @GetMapping(value = "/print")
    public String printAll(Entry entry, Model model)
    {
        // List all entries from entryService
        model.addAttribute("entries", entryService.listAllEntries());

        return "print";
    }

    @GetMapping(value = "/delete")
    public String deleteAll(Entry entry, Model model)
    {
        // Deletes all entries in repository
        entryRepository.deleteAll();

        return "delete";
    }

}
