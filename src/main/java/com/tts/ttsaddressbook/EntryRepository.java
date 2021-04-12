package com.tts.ttsaddressbook;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Repository
public interface EntryRepository extends CrudRepository<Entry, Long>
{

    List<Entry> findByEmail(String email);

}
