package guru.springframework.spring5recipeapp.converts;

import guru.springframework.spring5recipeapp.commands.NotesCommand;
import guru.springframework.spring5recipeapp.domain.Notes;
import org.springframework.lang.Nullable;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class NotesToNotesCommand implements Converter<Notes,NotesCommand> {

    @Nullable
    @Override
    public NotesCommand convert(Notes notes) {
        if(notes==null){
            return null;
        }

        NotesCommand notesCommand=new NotesCommand();

        notesCommand.setId(notes.getId());
        notesCommand.setRecipeNotes(notes.getRecipeNotes());

        return notesCommand;

    }
}
