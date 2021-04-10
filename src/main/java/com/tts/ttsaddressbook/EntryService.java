
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

    List<Entry> listAllEntries()
    {
        return (List<Entry>) entryRepository.findAll();
    }

    public Entry getEntryByEmail(String email) 
    {
       List<Entry> entries = entryRepository.findByEmail(email);
       if (entries.size() == 0)
       {
          return null; //No entry found.
       }
       return entries.get(0); //return first entry found.
    }
    
//    // Find all - pass through repository
//    public List<Entry> findByEmail()
//    {
//        List<Entry> entries = entryRepository.findByEmail(email);
//        return entries;
//    }
}
