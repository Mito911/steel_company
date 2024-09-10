package pl.maciejtuznik.steel_company.Machine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
public class MachineController {
    private final MachineRepository machineRepository;

    private final MachineService machineService;
    @Autowired
    public MachineController(MachineRepository machineRepository, MachineService machineService) {
        this.machineRepository = machineRepository;
        this.machineService = machineService;
    }
    @GetMapping("/machine")
    public ResponseEntity<Iterable<Machine>> findAllMachine(){
        return ResponseEntity.ok(machineService.findAllMachine());
    }

    @GetMapping("/machine/{id}")
    public ResponseEntity<Optional<Machine>> findMachine(@PathVariable Integer id){
        return ResponseEntity.ok(machineService.findMachine(id));
    }

    @PostMapping("/machine")
    public ResponseEntity<Machine> addMachine(@RequestBody Machine machine){
        machineService.saveMachine(machine);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(machine.getId())
                .toUri();


        return  ResponseEntity.created(location).body(machine);
    }

    @PutMapping("/machine/{id}")
    public ResponseEntity<Machine> updateMachine(@PathVariable Integer id,@RequestBody Machine machine){
       return machineService.updateMachine(id,machine)
                .map(ResponseEntity::ok)
                .orElseGet(()->ResponseEntity.notFound().build());
    }

    @PatchMapping("/machine/{id}")
    public ResponseEntity<Machine> partiallyUpdateMachine(@PathVariable Integer id,@RequestBody Machine machine){
        return machineService.partiallyUpdateMachine(id,machine)
                .map(ResponseEntity::ok)
                .orElseGet(()->ResponseEntity.notFound().build());

    }

    @DeleteMapping("machine/{id}")
    public ResponseEntity<Void> deleteMachine(@PathVariable Integer id){
        boolean deleted = machineService.deleteMachine(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

}
