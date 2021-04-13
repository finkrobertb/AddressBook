
package com.tts.ttsaddressbook;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EntryService
{

    @Autowired
    EntryRepository entryRepository;
    
    @Autowired
    EntryController entryController;

    // Returns a list with all entries
    List<Entry> listAllEntries()
    {
        return (List<Entry>) entryRepository.findAll();
    }

    // Returns first entry of list of all users by email
    public Entry getEntryByEmail(String email) 
    {
       List<Entry> entries = entryRepository.findByEmail(email);
       
       // If list size is 0, no entries were found
       if (entries.size() == 0)
       {
          return null; 
       }
       
       return entries.get(0); 
    }
    

}
