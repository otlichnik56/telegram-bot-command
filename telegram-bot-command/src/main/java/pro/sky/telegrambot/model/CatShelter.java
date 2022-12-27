package pro.sky.telegrambot.model;

import org.springframework.stereotype.Component;
import pro.sky.telegrambot.constant.FileNames;
@Component
public class CatShelter extends Shelter{

    public CatShelter() {
        super(FileNames.GREETINGS_CAT_SHELTER, FileNames.DESCRIPTION_CAT_SHELTER, FileNames.SCHEDULE_AND_ADDRESS_CAT_SHELTER,
                FileNames.DOCUMENTS_FOR_ADOPTION_CAT_SHELTER, FileNames.SAFETY_PRECUATIONS_CAT_SHELTER, FileNames.DECLINE_REASONS_CAT_SHELTER,
                FileNames.MEETING_RULES_CAT_SHELTER, FileNames.TRANSPORTATION_RECOMMENDATIONS_CAT_SHELTER,
                FileNames.HOME_IMPROVEMENTS_FOR_PUPPIES_CAT_SHELTER, FileNames.HOME_IMPROVEMENTS_FOR_DISABLED_CAT_SHELTER, FileNames.HOME_IMPROVEMENTS_FOR_ADULTS_CAT_SHELTER);
        }
}
