package sha2ya3n.the2gen3tel4man.recepie.converters;

import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import sha2ya3n.the2gen3tel4man.recepie.commands.NoteCommand;
import sha2ya3n.the2gen3tel4man.recepie.model.Note;

@Component
public class NoteCommandToNote implements Converter<NoteCommand, Note> {

    @Synchronized
    @Nullable
    @Override
    public Note convert(NoteCommand source) {
        if(source == null){
            return null;
        }

        final Note note = new Note();
        note.setRecipeNotes(source.getRecipeNotes());
        note.setId(source.getId());

        return note;
    }
}
