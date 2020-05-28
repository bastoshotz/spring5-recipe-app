package guru.springframework.converters;

import guru.springframework.commands.NotesCommand;
import guru.springframework.domain.Notes;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class NotesToNotesCommand implements Converter<Notes, NotesCommand> {

    @Synchronized
    @Nullable
    @Override
    public NotesCommand convert(Notes notes) {
        if(Objects.isNull(notes)){
            return null;
        }
        final NotesCommand notesCommand = new NotesCommand();
        notesCommand.setId(notes.getId());
        notes.setRecipeNotes(notes.getRecipeNotes());
        return notesCommand;
    }
}
