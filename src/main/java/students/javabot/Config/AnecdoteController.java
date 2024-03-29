package students.javabot.Config;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import students.javabot.Model.Anecdote;
import students.javabot.Service.AnecdoteService;
import java.util.List;
@RestController
@RequiredArgsConstructor
@RequestMapping("/anecdotes")
public class AnecdoteController {
    private final AnecdoteService anecdoteService;

    @PostMapping
    ResponseEntity<Anecdote> registerAnecdote(@RequestBody Anecdote anecdote){
        return ResponseEntity.ok(anecdoteService.registerAnecdote(anecdote));
    }

    @GetMapping
    ResponseEntity<List<Anecdote>> getAnecdotes(){
        return ResponseEntity.ok(anecdoteService.getAllAnecdotes());
    }

    @GetMapping("/{id}")
    ResponseEntity<Anecdote> getAnecdoteById(@PathVariable("id") Long id){
        return anecdoteService.getAnecdoteById(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


//    @PutMapping("/{id}")
//    ResponseEntity<Anecdote> updateAnecdoteById(@PathVariable("id") Long id, @RequestBody String text){
//        Anecdote anecdote = anecdoteRepository.getAnecdoteById(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build()).getBody(); // В чем отличие если я просто пропишу getAnecdoteById(id).orElseThrow();?
//        assert anecdote != null;
//        anecdote.setText(text);
//        Date date = new Date();
//        anecdote.setDateOfUpdate(date);
//        return ResponseEntity.ok(anecdoteRepository.save(anecdote));
//        }
    @PutMapping("/{id}")
    ResponseEntity<Anecdote> updateAnecdoteById(@PathVariable("id") Long id, @RequestBody Anecdote anecdote){
        return ResponseEntity.ok(anecdoteService.updateAnecdoteById(id,anecdote));
    }

    @DeleteMapping("/{id}")
    void deleteAnecdoteById(@PathVariable("id") Long id){
        anecdoteService.deleteAnecdoteById(id);
    }
}


