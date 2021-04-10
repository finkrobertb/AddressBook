
package com.tts.ttsaddressbook;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface EntryService
{
    List<Entry> listAllEntries();
    
    Entry getEntryByEmail(String email);
}
