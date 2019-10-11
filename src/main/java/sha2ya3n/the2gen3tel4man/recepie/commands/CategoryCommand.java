package sha2ya3n.the2gen3tel4man.recepie.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@NoArgsConstructor
@Component
public class CategoryCommand {

    private Long id;
    private String  description;

}
