package com.tts.ttsaddressbook;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntryRepository extends CrudRepository<Entry, Long>
{

    List<Entry> findByEmail(String email);

}
