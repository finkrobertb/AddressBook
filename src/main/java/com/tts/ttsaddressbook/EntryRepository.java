package com.tts.ttsaddressbook;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Repository
public interface EntryRepository extends CrudRepository<Entry, Long>
{

    public List<Entry> findByEmail(String email);

    @Controller
    public class EntryController
    {

        @Autowired
        private EntryRepository entryRepository;
        
        private EntryService entryService;
        
        @Autowired
        public void setEntryService(EntryService entryService) {
            this.entryService = entryService;
        }

        @GetMapping(value = "/")
        public String index(Entry entry)
        {
            return "index";
        }

        // GET request to add.html
        @GetMapping(value = "/add")
        public String addEntry(Entry entry, Model model)
        {
            entry.setFirstName("John");
            entry.setLastName("Doe");
            entry.setPhoneNumber("555-555-5555");
            entry.setEmail("fake@email.com");
            return "add";
        }

        // POST to addresult.html
        @PostMapping(value = "/addresult")
        public String displayForm(Entry entry, Model model)
        {
            entryRepository.save(new Entry(entry.getFirstName(), entry.getLastName(), entry.getPhoneNumber(), entry.getEmail(), entry.getEntryRecorded()));
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
            entry.setEmail("fake@email.com");

            return "remove";
        }

        @PostMapping(value = "/removeresult")
        public String remove(Entry entry, Model model)
        {
            // Creates a list of entries and stores entries with that email
            List<Entry> entries = entryRepository.findByEmail(entry.getEmail());
            // Grab the first entry of that list
            Entry firstEntry = entries.get(0);
            // Delete the first entry
            entryRepository.delete(firstEntry);

            return "removeresult";
        }

        @GetMapping(value = "/search")
        public String search(Entry entry, Model model)
        {
            entry.setEmail("fake@email.com");

            return "search";
        }

        @PostMapping(value = "/searchresult")
        public String searchResult(Entry entry, Model model)
        {
            // Creates a list of entries and stores entries with that email
            List<Entry> entries = entryRepository.findByEmail(entry.getEmail());
            // Grab the first entry of that list
            Entry firstEntry = entries.get(0);
            // Add the attributes of the first entry to output in /searchresult
            model.addAttribute("firstName", firstEntry.getFirstName());
            model.addAttribute("lastName", firstEntry.getLastName());
            model.addAttribute("phoneNumber", firstEntry.getPhoneNumber());
            model.addAttribute("email", firstEntry.getEmail());

            return "searchresult";
        }

        // TODO
        @GetMapping(value = "/print")
        public String printAll(Entry entry, Model model)
        {
            model.addAttribute("entries", entryService.listAllEntries());
            
            return "print";
        }

        @GetMapping(value = "/delete")
        public String deleteAll(Entry entry, Model model)
        {
            entryRepository.deleteAll();
            return "delete";
        }

    }

}
