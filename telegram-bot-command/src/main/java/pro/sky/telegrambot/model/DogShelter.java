package pro.sky.telegrambot.model;

import org.springframework.stereotype.Component;
import pro.sky.telegrambot.constant.FileNames;

@Component
public class DogShelter extends Shelter {

    private String cynologystsAdvices;
    private String approvedCynologysts;

    private String approvedCynologystsFileName = FileNames.APPROVED_CYNOLOGYSTS_DOG_SHELTER;
    private String cynologystsAdvicesFileName = FileNames.CYNOLOGYSTS_ADVICES_DOG_SHELTER;

    public DogShelter() {
        super(FileNames.GREETINGS_DOG_SHELTER, FileNames.DESCRIPTION_DOG_SHELTER,
                FileNames.SCHEDULE_AND_ADDRESS_DOG_SHELTER,
                FileNames.DOCUMENTS_FOR_ADOPTION_DOG_SHELTER, FileNames.SAFETY_PRECUATIONS_DOG_SHELTER,
                FileNames.DECLINE_REASONS_DOG_SHELTER, FileNames.MEETING_RULES_DOG_SHELTER,
                FileNames.TRANSPORTATION_RECOMMENDATIONS_DOG_SHELTER, FileNames.HOME_IMPROVEMENTS_FOR_PUPPIES_DOG_SHELTER,
                FileNames.HOME_IMPROVEMENTS_FOR_DISABLED_DOG_SHELTER, FileNames.HOME_IMPROVEMENTS_FOR_ADULTS_DOG_SHELTER);
        this.cynologystsAdvices = readStringsFromFile(cynologystsAdvicesFileName);
        this.approvedCynologystsFileName = readStringsFromFile(cynologystsAdvicesFileName);

    }

    @Override
    public void updateInfoAboutShelter() {
        super.updateInfoAboutShelter();
        this.cynologystsAdvices = readStringsFromFile(cynologystsAdvicesFileName);
        this.approvedCynologystsFileName = readStringsFromFile(cynologystsAdvicesFileName);
    }


    public String getCynologystsAdvices() {
        return cynologystsAdvices;
    }

    public String getApprovedCunologysts() {
        return approvedCynologysts;
    }
}
