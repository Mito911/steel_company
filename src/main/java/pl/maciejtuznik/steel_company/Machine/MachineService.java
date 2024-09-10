package pl.maciejtuznik.steel_company.Machine;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MachineService {

    private final MachineRepository machineRepository;

    public MachineService(MachineRepository machineRepository) {
        this.machineRepository = machineRepository;
    }

    public Iterable<Machine> findAllMachine(){
        Iterable<Machine> all = machineRepository.findAll();
        return all;
    }

    public Optional<Machine> findMachine(Integer id){
        Optional<Machine> byId = machineRepository.findById(id);

        return byId;

    }

    public Machine saveMachine(Machine machine){
        return machineRepository.save(machine);
    }

    public Optional<Machine> updateMachine(Integer id, Machine machine){
       return machineRepository.findById(id)
                .map(existingMachine ->{
                    existingMachine.setName(existingMachine.getName());
                    existingMachine.setQuantity(existingMachine.getQuantity());

                  return   machineRepository.save(existingMachine);
                });
    }

    public Optional<Machine> partiallyUpdateMachine(Integer id,Machine machine){
      return   machineRepository.findById(id)
                .map(existingMachine->{
                    if(machine.getName()!=null)existingMachine.setName(existingMachine.getName());
                    if(machine.getQuantity()!=null)existingMachine.setQuantity(existingMachine.getQuantity());

                    return machineRepository.save(existingMachine);
                });
    }

    public boolean deleteMachine(Integer id){
        if(machineRepository.existsById(id)){
            machineRepository.deleteById(id);
            return true;
        }
        else {
            return false;
        }
    }


}
