package sha2ya3n.the2gen3tel4man.recepie.services;

import org.springframework.stereotype.Service;
import sha2ya3n.the2gen3tel4man.recepie.commands.UnitOfMeasureCommand;
import sha2ya3n.the2gen3tel4man.recepie.converters.UnitOfMeasureToUnitOfMeasureCommand;
import sha2ya3n.the2gen3tel4man.recepie.repository.UnitOfMeasureRepository;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UnitOfMeasureServiceImpl implements UnitOfMeasureService {

    private final UnitOfMeasureRepository unitOfMeasureRepository;
    private final UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand;

    public UnitOfMeasureServiceImpl(UnitOfMeasureRepository unitOfMeasureRepository, UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand) {
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.unitOfMeasureToUnitOfMeasureCommand = unitOfMeasureToUnitOfMeasureCommand;
    }

    @Override
    public Set<UnitOfMeasureCommand> listOfuom() {
        return StreamSupport.stream(unitOfMeasureRepository.findAll()
        .spliterator(), false)
        .map(unitOfMeasureToUnitOfMeasureCommand::convert)
        .collect(Collectors.toSet());

    }
}
