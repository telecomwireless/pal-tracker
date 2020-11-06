package io.pivotal.pal.tracker;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TimeEntryController {

    private TimeEntryRepository timeRepo;

    public TimeEntryController(TimeEntryRepository timeRepo){
        this.timeRepo = timeRepo;
    }

    @PostMapping("/time-entries")
    public ResponseEntity create(@RequestBody TimeEntry timeEntry){
        TimeEntry newEntry = timeRepo.create(timeEntry);
        ResponseEntity<TimeEntry> response = new ResponseEntity<TimeEntry>(newEntry,HttpStatus.CREATED);
        return response;
    }

    @GetMapping("/time-entries/{id}")
    public ResponseEntity read(@PathVariable("id") Long id){
        TimeEntry timeEntry = timeRepo.find(id);
        if(timeEntry == null){
            return ResponseEntity.notFound().build();
        }
        ResponseEntity<TimeEntry> response = new ResponseEntity<TimeEntry>(timeEntry,HttpStatus.OK);
        return response;
    }

    @GetMapping("/time-entries")
    public ResponseEntity list() {
        List<TimeEntry> timeEntries = timeRepo.list();
        ResponseEntity<List<TimeEntry>> repsonse = new ResponseEntity<>(timeEntries,HttpStatus.OK);
        return repsonse;
    }

    @PutMapping("/time-entries/{id}")
    public ResponseEntity update(@PathVariable Long id, @RequestBody TimeEntry timeEntry){
        TimeEntry newEntry = timeRepo.update(id,timeEntry);
        if(newEntry == null){
            return ResponseEntity.notFound().build();
        }
        ResponseEntity<TimeEntry> response = new ResponseEntity<TimeEntry>(newEntry,HttpStatus.OK);
        return response;
    }

    @DeleteMapping("/time-entries/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        timeRepo.delete(id);
        ResponseEntity response = ResponseEntity.noContent().build();
        return response;
    }

}
