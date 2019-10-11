package sha2ya3n.the2gen3tel4man.recepie.converters;

import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import sha2ya3n.the2gen3tel4man.recepie.commands.NoteCommand;
import sha2ya3n.the2gen3tel4man.recepie.model.Note;

@Component
public class NoteToNoteCommand implements Converter<Note, NoteCommand> {

    @Synchronized
    @Nullable
    @Override
    public NoteCommand convert(Note source) {
        if(source == null){
            return null;
        }

        final  NoteCommand noteCommand = new NoteCommand();
        noteCommand.setRecipeNotes(source.getRecipeNotes());
        noteCommand.setId(source.getId());

        return noteCommand;

    }
}
