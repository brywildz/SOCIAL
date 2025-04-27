package engine.process.builder;

import engine.data.event.Event;
import engine.data.event.PersonalEvent;
import engine.data.event.SocialEvent;
import engine.data.map.Block;
import engine.data.map.Clock;
import engine.data.map.Infrastructure;
import engine.data.map.Time;
import engine.data.person.Person;
import engine.data.person.Personality;
import engine.process.repository.EventRepository;
import engine.process.repository.InfrastructureRepository;
import engine.process.repository.PersonRepository;
import java.util.ArrayList;
import java.util.Iterator;
import static engine.process.builder.GameBuilder.random;
import static engine.process.manager.utils.LifeUtilities.goHome;
import static engine.process.manager.utils.LifeUtilities.goHospital;
import static engine.process.manager.utils.ScoringUtilities.eventScore;
import static engine.process.manager.utils.ScoringUtilities.isCompatible;

public class EventBuilder {
    InfrastructureRepository infraRepo = InfrastructureRepository.getInstance();
    PersonRepository personRepo = PersonRepository.getInstance();
    EventRepository eventRepo = EventRepository.getInstance();
    Person person;
    Event event;

    public EventBuilder(Person person, Event event) {
        this.person = person;
        this.event = event;
    }

    public void build() {
        if(event instanceof PersonalEvent){
            buildPersonalEvent();
        }
        else{
            buildSocialEvent();
        }
        if(event.getId() != null){
            person.setEvent(event);
            if(event instanceof SocialEvent){
                person.setHobby(null);
            }
        }
    }

    /**
     * Construit un événement personnel dépendamment du trait majoritaire de l'individu.
     * Ils se distinguent par trois niveaux : Maladie, Rencontre, Succès (ordre de priorité).
     * Ils sont prioritaires aux événements sociaux.
     */
    private void buildPersonalEvent() {
        double score;
        Personality perso = person.getPersonality();
        /*
         * La maladie est l’événement qui est prioritaire (obligation biologique).
         * Viens ensuite la nouvelle rencontre (probabilité naturelle)
         * Enfin vient la réussite qui est la plus rare et qui a le plus gros impact
         */
        if(person.getLevelSickness() == 1){
            int sickProba = random(0,4);
            //L’individu peut ne pas remarquer qu'il tombe malade.
            if(sickProba == 0){
                buildSick();
                return;
            }
        }
        //Il est vraiment très malade, il doit aller à l’hôpital
        else if(person.getLevelSickness() == 2){
            buildVerySick();
            return;
        }
        else{
            //Pas de maladie, on essaye pour la nouvelle rencontre.
            if(!person.isAtHome() && !person.isWorking() && (person.getHobby()!=null && !person.getHobby().hasStart())){
                int meetProba = random(0,5);
                score = eventScore("meet", perso);
                if(score>=0.7){
                    buildNewMeet();
                    return;
                }
                else if(score>=0.5 && meetProba <= 1){
                    buildNewMeet();
                    return;
                }
            }
        }
        if(person.isWorking() && (person.getHobby()!=null && !person.getHobby().hasStart())){
            int successProba = random(0,10);
            //Quelqu'un de très consciencieux aura plus de chance de réussir dans son domaine.
            score = eventScore("success", perso);
            if(score>=0.7){
                buildSuccess();
            }
            else if(successProba == 0){
                buildSuccess();
            }
        }
    }

    private void buildSuccess() {
        event = new PersonalEvent("success", "", person);

    }

    private void buildNewMeet() {
        if(person.isAtHome()){
            return;
        }
        Infrastructure place = person.getPlace();
        Iterator<Person> it = person.getPlace().getPersons().iterator();
        boolean found = false;
        while(it.hasNext() && !found){
            Person possibleFriend = it.next();
            if(canBeFriend(possibleFriend)){
                person.getRelation().getAmicale().add(possibleFriend);
                possibleFriend.getRelation().getAmicale().add(person);
                personRepo.movePerson(person, possibleFriend);
                event = new PersonalEvent("meet", "", possibleFriend);
                possibleFriend.setEvent(new PersonalEvent("meet", "", person));
                found = true;
            }
        }
    }

    private boolean canBeFriend(Person possibleFriend) {
        return !person.getName().equals(possibleFriend.getName()) && possibleFriend.getHobby()!=null
                && !possibleFriend.getHobby().isFinishedIn(15) && isCompatible(this.person, possibleFriend, "friends");
    }

    private void buildVerySick() {
        goHospital(person);
        person.setSick(true);
        person.getPersonState().getMood().add(-3);
        event = new PersonalEvent("sick", "", person);
    }

    private void buildSick() {
        person.setSick(true);
        person.getPersonState().getMood().add(-2);
        event = new PersonalEvent("sick", "", person);
        if(!person.isAtHome()){
            goHome(person);
        }
    }


    private void buildSocialEvent() {
        double score;
        Personality perso = person.getPersonality();
        Time time = Clock.getInstance().getTime();
        int persoProba = random(0,10);
        //Événement social choisit aléatoirement.
        String day = time.getDate().getDayName();
        int eventProba = random(0,2);
        if(day.equals("Dimanche") && time.getHour()<19 && time.getHour()>12){
            if (true) {
                score = eventScore("family_dinner", perso);
                if(score>=5 || random(3)==0){
                    buildFamilyDinner();
                }
            }
        }
        else if(!Clock.isWeekend() && time.getHour() >= 19 && time.getHour() < 23){
            score = eventScore("work_dinner", perso);
            if(score>=5) {
                buildWorkDinner();
            }
        }
        else if (eventProba == 0) {
            if(time.getHour() < 5 || time.getHour() >= 22){
                score = eventScore("party", perso);
                if(score>=7 && persoProba <= 5){
                    buildParty();
                }
                else if(persoProba <= 3){
                    buildParty();
                }
            }
        } else {
            if(!(time.getHour() < 5 || time.getHour() > 18)){
                score = eventScore("walk", perso);
                if(score>=6 && persoProba <= 5){
                    buildWalk();
                }
                else if((score>=5) && persoProba <= 3){
                    buildWalk();
                }
            }
        }
    }

    private void buildWorkDinner() {
        ArrayList<Person> work = person.getRelation().getPro();
        ArrayList<Person> present = new ArrayList<>();
        Time start = Clock.getInstance().getActualTime();
        Time end = Clock.getInstance().getActualTime();
        end.addHour(2);
        Person proMember;
        SocialEvent proDinner = new SocialEvent("work_dinner", "", present,infraRepo.get("restaurant"));
        proDinner.setStartTime(start);
        proDinner.setEndTime(end);
        Iterator<Person> it = work.iterator();
        personRepo.movePerson(person,infraRepo.get("restaurant").getRandomBlock());
        int line = 0;
        while(it.hasNext()){
            proMember = it.next();
            if(canCome(proMember) && present.size()<3){
                if(eventScore("work_dinner", proMember.getPersonality()) >= 5 || random(0,3)==0){
                    proMember.setEvent(proDinner);
                    proMember.setHobby(null);
                    setLocationWorkDinner(person, proMember, line);
                    present.add(proMember);
                    line++;
                }
            }
        }
        if(!present.isEmpty()){
            event = proDinner;
        }
    }

    private void buildFamilyDinner() {
        ArrayList<Person> family = person.getRelation().getFamiliale();
        ArrayList<Person> present = new ArrayList<>();
        Time start = Clock.getInstance().getActualTime();
        Time end = Clock.getInstance().getActualTime();
        end.addHour(3);
        Person familyMember;
        SocialEvent familyDinner = new SocialEvent("family_dinner", "", present,person.getHouse());
        familyDinner.setStartTime(start);
        familyDinner.setEndTime(end);
        Iterator<Person> it = family.iterator();
        personRepo.movePerson(person,person.getHouse().getRandomBlock());
        int line = 0;
        while(it.hasNext()){
            familyMember = it.next();
            if(canCome(familyMember) && present.size()<3){
                if(eventScore("family_dinner", familyMember.getPersonality()) >= 5 || random(0,3)==0){
                    familyMember.setEvent(familyDinner);
                    familyMember.setHobby(null);
                    setLocationDinner(person, familyMember, line);
                    present.add(familyMember);
                    line++;
                }
            }
        }
        if(!present.isEmpty()){
            event = familyDinner;
        }
    }

    private void setLocationDinner(Person leader, Person familyMember, int line) {
        Block b = leader.getLocation();
        Block neighbour;
        if(line == 1){
            neighbour  = new Block(b.getLine() + 1 , b.getColumn());
        }
        else if(line == 2){
            neighbour  = new Block(b.getLine(), b.getColumn()+1);
        }
        else{
            neighbour  = new Block(b.getLine()+1, b.getColumn()+1);
        }
        personRepo.movePerson(familyMember,neighbour, leader.getHouse());
    }

    private void setLocationWorkDinner(Person leader, Person familyMember, int line) {
        Block b = leader.getLocation();
        Block neighbour;
        if(line == 1){
            neighbour  = new Block(b.getLine() + 1 , b.getColumn());
        }
        else if(line == 2){
            neighbour  = new Block(b.getLine(), b.getColumn()+1);
        }
        else{
            neighbour  = new Block(b.getLine()+1, b.getColumn()+1);
        }
        personRepo.movePerson(familyMember,neighbour, infraRepo.get("restaurant"));
    }

    private void buildWalk() {
        ArrayList<Person> friends = person.getRelation().getAmicale();
        Person friend;
        ArrayList<Person> present = new ArrayList<>();
        SocialEvent walk = new SocialEvent("walk", "", present,infraRepo.get("forest"));
        walk.setLeader(person);
        Time start = Clock.getInstance().getActualTime();
        Time end = Clock.getInstance().getActualTime();
        end.addHour(3);
        walk.setStartTime(start);
        walk.setEndTime(end);
        Iterator<Person> it = friends.iterator();
        while(it.hasNext()){
            friend = it.next();
            if(canCome(friend) && present.size()<3){
                if(eventScore("walk", friend.getPersonality()) >= 5){
                    friend.setEvent(walk);
                    friend.setHobby(null);
                    present.add(friend);
                }
                else if(random(0,3)==0){
                    friend.setEvent(walk);
                    friend.setHobby(null);
                    present.add(friend);
                }
            }
        }
        if(!present.isEmpty()){
            event = walk;
        }
    }

    /**
     * on scan la liste d'amis
     * on fait un event score a chaqe itéartion
     * si ça depasse 50% le boug y va
     * sinon c'est 1 chance sur trois
     * il y a 5 personnes max
     */
    private void buildParty() {
        ArrayList<Person> friends = person.getRelation().getAmicale();
        Person friend;
        ArrayList<Person> present = new ArrayList<>();
        SocialEvent party = new SocialEvent("party", "", present,infraRepo.get("night_club"));
        Time end = new Time (5,0,0);
        party.setEndTime(end);
        Iterator<Person> it = friends.iterator();
        while(it.hasNext() && present.size()<3){
            friend = it.next();
            if(canCome(friend)){
                if(eventScore("party", friend.getPersonality()) >= 0.5){
                    friend.setEvent(party);
                    friend.setHobby(null);
                    present.add(friend);
                }
                else if(random(0,3)==0){
                    friend.setEvent(party);
                    friend.setHobby(null);
                    present.add(friend);}
            }
        }
        if(!present.isEmpty()){
            event = party;
        }
    }

    private boolean canCome(Person friend){
        return !friend.isPreferred() && friend.getEvent() == null && !friend.isSleeping() && !friend.isWorking() && !friend.isSick();
    }



}
