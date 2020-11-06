package io.pivotal.pal.tracker;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class InMemoryTimeEntryRepository implements TimeEntryRepository{

    private Map<Long,TimeEntry> entries = new HashMap<Long,TimeEntry>();
    private  AtomicLong idSequence = new AtomicLong(0L);

    public InMemoryTimeEntryRepository(){
//        entries = new HashMap<>();
//        idSequence = new AtomicLong((0L));
    }
    public TimeEntry create(TimeEntry timeEntry) {
        timeEntry.setId(idSequence.addAndGet(1L));
        entries.put(timeEntry.getId(),timeEntry);
        return timeEntry;
    }


    public TimeEntry find(Long id){
        if(entries.containsKey(id)){
            return entries.get(id);
        }
        return null;
    }

    public List<TimeEntry> list(){
        List<TimeEntry> timeEntries = new ArrayList(entries.values());
        return timeEntries;
    }

    public TimeEntry update(Long id, TimeEntry  timeEntry) {
        if (entries.containsKey(id)) {
            timeEntry.setId(id);
            entries.put(id, timeEntry);
            return timeEntry;
        }
        return null;
    }

    public void delete(Long id){
        if(entries.containsKey(id)){
            entries.remove(id);
        }
    }
}